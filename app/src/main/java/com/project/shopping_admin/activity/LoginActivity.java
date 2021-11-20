package com.project.shopping_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.read_admin_mob.DetailsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    private EditText edt1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


    }


    private void init() {
        edt1 = findViewById(R.id.edt1);


    }


    public void goto_otp_activity(View view) {

        List<DetailsItem> list = new ArrayList<>();

        String mob = edt1.getText().toString().trim();

        if (mob != null) {
            if (mob.length() == 10) {


                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response> call = apiService.read_admin_mobs(Constants.api_key);

                call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response>() {
                    @Override
                    public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response> call, Response<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response> response) {

                        if (response.body() != null) {
                            if (response.body().getResult().equals("1")) {
                                list.addAll(response.body().getDetails());

                                if (list.size() > 0) {
                                    boolean found = false;
                                    for (DetailsItem row : list) {
                                        if (row.getMob().equals(mob)) {
                                            found = true;
                                            break;
                                        }
                                    }

                                    hideSoftKeyboard(LoginActivity.this, view);

                                    if (found) {

                                        //generate 4 digit otp
                                        Random randomGenerator = new Random();

                                        int number = randomGenerator.nextInt(999999);

                                        String generated_otp = String.format("%06d", number);


                                        Call<Pojomodelbase> call2 = apiService.send_sms(Constants.api_key, mob, generated_otp);

                                        call2.enqueue(new Callback<Pojomodelbase>() {
                                            @Override
                                            public void onResponse(Call<Pojomodelbase> call2, Response<Pojomodelbase> response) {

                                            }

                                            @Override
                                            public void onFailure(Call<Pojomodelbase> call2, Throwable t) {

                                            }
                                        });
                                        showSnack_W("An OTP has been sent your mobile");
                                        Intent i=new Intent(getApplicationContext(),OtpActivity.class);
                                        i.putExtra("otp",generated_otp);
                                        startActivity(i);





                                    } else {

                                        showSnack_W("Not a valid Mob mo.");
                                    }


                                }


                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_admin_mob.Response> call, Throwable t) {
                        showSnack_W("error");
                    }
                });


            }
        }


    }


}
