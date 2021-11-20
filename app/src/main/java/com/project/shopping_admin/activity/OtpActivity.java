package com.project.shopping_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.base.BaseActivity;

public class OtpActivity extends BaseActivity {

    private EditText edt1,edt2,edt3,edt4,edt5,edt6;
    private Button btn1;
    private String otp="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("otp")!= null)
        {
            otp=bundle.getString("otp");
        }



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edt1.getText().toString().trim().equals("") &&  !edt2.getText().toString().trim().equals("") && !edt3.getText().toString().trim().equals("") &&  !edt4.getText().toString().trim().equals("") && !edt5.getText().toString().trim().equals("") &&  !edt6.getText().toString().trim().equals("")    )
                {
                    String typed=edt1.getText().toString().trim() +edt2.getText().toString().trim() +edt3.getText().toString().trim()
                            +edt4.getText().toString().trim() +edt5.getText().toString().trim()+edt6.getText().toString().trim();



                    if(typed.equals(otp) )
                    {
                        Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(in);
                        finish();

                    }
                    else
                    {
                        hideSoftKeyboard(OtpActivity.this,v);
                        showSnack_W("Not valid otp");
                    }

                }




            }
        });





        edt1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt1.getText().toString().length() == 1)     //size as per your requirement
                {
                    edt2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {


            }

            public void afterTextChanged(Editable s) {

            }


        });

        edt2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt2.getText().toString().length() == 1)     //size as per your requirement
                {
                    edt3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            public void afterTextChanged(Editable s) {

            }
        });

        edt3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt3.getText().toString().length() == 1)     //size as per your requirement
                {
                    edt4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            public void afterTextChanged(Editable s) {

            }
        });

        edt4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt4.getText().toString().length() == 1)     //size as per your requirement
                {
                    edt5.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            public void afterTextChanged(Editable s) {

            }
        });

        edt5.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt5.getText().toString().length() == 1)     //size as per your requirement
                {
                    edt6.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            public void afterTextChanged(Editable s) {

            }
        });

        edt6.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edt6.getText().toString().length() == 1)     //size as per your requirement
                {
                    hideSoftKeyboard(OtpActivity.this,findViewById(android.R.id.content).getRootView());
                    btn1.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) { }

            public void afterTextChanged(Editable s) {

            }
        });



    }


    private void init()
    {
        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        edt5=findViewById(R.id.edt5);
        edt6=findViewById(R.id.edt6);
        btn1=findViewById(R.id.btn1);






    }

}
