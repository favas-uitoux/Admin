package com.project.shopping_admin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;

import com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.read_category.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.project.shopping_admin.Constants.SelectMainCategory;
import static com.project.shopping_admin.Constants.SelectSubCategory;
import static com.project.shopping_admin.Constants.api_key;

public class SetPhotoActivity extends BaseActivity {

    private Spinner sp1,sp2,sp3;
    private List<DetailsItem> list,list2,list3;
    private  List<String> list_sp1,list_sp2,list_sp3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setphoto);


        init();

        read_category();


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (!sp1.getSelectedItem().toString().equals(SelectMainCategory)) {

                    list_sp2=new ArrayList<>();
                    list_sp3=new ArrayList<>();

                    sp2.setAdapter(null);
                    sp3.setAdapter(null);


                    list2=new ArrayList<>();
                    read_category2();

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }



    private void read_category()
    {



        list=new ArrayList<>();
        list_sp1=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_category1(api_key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.body().getResult().equals("1"))
                {


                    list_sp1.add(SelectMainCategory);
                    list.addAll((response.body().getDetails()));
//
                    for (DetailsItem item :list) {

                        list_sp1.add(item.getCategory());

                    }


//
                    ArrayAdapter aa = new ArrayAdapter(SetPhotoActivity.this, android.R.layout.simple_spinner_item, list_sp1);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    sp1.setAdapter(aa);


                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {


                showSnack_E(t.getLocalizedMessage());

            }
        });




    }



    private  void read_category2()
    {



        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_sub1(api_key,sp1.getSelectedItem().toString());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.body().getResult().equals("1"))
                {
                    list_sp2.add(SelectSubCategory);
                    list2.addAll((response.body().getDetails()));
//
                    for (DetailsItem item :list2) {

                        list_sp2.add(item.getCategory());

                    }


//
                    ArrayAdapter aa = new ArrayAdapter(SetPhotoActivity.this, android.R.layout.simple_spinner_item, list_sp2);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    sp2.setAdapter(aa);

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showSnack_E(t.getLocalizedMessage());

            }
        });



    }


    private  void init()
    {

        sp1=findViewById(R.id.sp1);
        sp2=findViewById(R.id.sp2);
        sp3=findViewById(R.id.sp3);


    }

}
