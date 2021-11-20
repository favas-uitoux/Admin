package com.project.shopping_admin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.read_category.Response;
import com.project.shopping_admin.database.appdb.Appdb;
import com.project.shopping_admin.database.entities.ImageEntity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yalantis.ucrop.UCrop;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.project.shopping_admin.Constants.SelectMainCategory;

public class AddGroupActivity extends BaseActivity {

    private Appdb db;
    private androidx.appcompat.widget.AppCompatSpinner sp0, sp1,sp2,sp3,sp4,sp5,sp6,sp7;
    private List<DetailsItem> list_cat,list_sub1,list_sub2,list_comp,list_brand,list_pattern,list_color,list_size;

    private List<String> list_sp0=new ArrayList<>(),list_sp1=new ArrayList<>(),list_sp2=new ArrayList<>(),list_sp3=new ArrayList<>(),list_sp4=new ArrayList<>(),list_sp5=new ArrayList<>(),list_sp6=new ArrayList<>(),list_sp7=new ArrayList<>();

    private TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;

    private ProgressBar prg1,prg2,prg3,prg4,prg5,prg6,prg7;

    private Button btnsave;
    private boolean image_set_flag = false;

    private ImageView img1;

    private  EditText edt_display_name;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);


        init();

        read_category();




        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {
                    show_dialog("group");
                }

            }
        });









        sp0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here


                reset_spinner(sp1, Constants.Choose_sub_category1);
                reset_spinner(sp2, Constants.Choose_sub_category2);

                reset_spinner(sp3,Constants.Choose_Company);

                reset_spinner(sp4,Constants.Choose_Brand);

                reset_spinner(sp5,Constants.Choose_Pattern);

                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here



                reset_spinner(sp2, Constants.Choose_sub_category2);

                reset_spinner(sp3,Constants.Choose_Company);

                reset_spinner(sp4,Constants.Choose_Brand);

                reset_spinner(sp5,Constants.Choose_Pattern);

                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here




                reset_spinner(sp3,Constants.Choose_Company);

                reset_spinner(sp4,Constants.Choose_Brand);

                reset_spinner(sp5,Constants.Choose_Pattern);

                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here






                reset_spinner(sp4,Constants.Choose_Brand);

                reset_spinner(sp5,Constants.Choose_Pattern);

                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here


                reset_spinner(sp5,Constants.Choose_Pattern);

                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here


                reset_spinner(sp6,Constants.Choose_Color);

                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        sp6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here



                reset_spinner(sp7,Constants.Choose_Size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {


                    reset_spinner(sp2, Constants.Choose_sub_category2);

                    reset_spinner(sp3,Constants.Choose_Company);

                    reset_spinner(sp4,Constants.Choose_Brand);

                    reset_spinner(sp5,Constants.Choose_Pattern);

                    reset_spinner(sp6,Constants.Choose_Color);

                    reset_spinner(sp7,Constants.Choose_Size);


                    read_sub1();

                }

            }
        });


        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {


                    reset_spinner(sp3,Constants.Choose_Company);

                    reset_spinner(sp4,Constants.Choose_Brand);

                    reset_spinner(sp5,Constants.Choose_Pattern);

                    reset_spinner(sp6,Constants.Choose_Color);

                    reset_spinner(sp7,Constants.Choose_Size);
                    read_sub2();
                }

            }
        });



        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {

                    reset_spinner(sp4,Constants.Choose_Brand);

                    reset_spinner(sp5,Constants.Choose_Pattern);

                    reset_spinner(sp6,Constants.Choose_Color);

                    reset_spinner(sp7,Constants.Choose_Size);
                    read_sub3();

                }

            }
        });




        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {



                    reset_spinner(sp5,Constants.Choose_Pattern);

                    reset_spinner(sp6,Constants.Choose_Color);

                    reset_spinner(sp7,Constants.Choose_Size);

                    read_sub4();


                }

            }
        });





        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {


                    reset_spinner(sp6,Constants.Choose_Color);

                    reset_spinner(sp7,Constants.Choose_Size);

                    read_sub5();


                }

            }
        });


        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {




                    reset_spinner(sp7,Constants.Choose_Size);

                    read_sub6();


                }

            }
        });


        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_main())
                {


                    read_sub7();


                }

            }
        });



    }





    private void read_sub7()
    {
        prg7.setVisibility(View.VISIBLE);

        String val1="",val2="",val3="",val4="",val5="",val6="";




        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
        {
            val1=sp1.getSelectedItem().toString();
        }
        if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
        {
            val2=sp2.getSelectedItem().toString();
        }
        if(list_sp3.size()>0   &&  !sp3.getSelectedItem().toString().equals(Constants.Choose_Company) )
        {
            val3=sp3.getSelectedItem().toString();
        }

        if(list_sp4.size()>0   &&  !sp4.getSelectedItem().toString().equals(Constants.Choose_Brand) )
        {
            val4=sp4.getSelectedItem().toString();
        }


        if(list_sp5.size()>0   &&  !sp5.getSelectedItem().toString().equals(Constants.Choose_Pattern) )
        {
            val5=sp5.getSelectedItem().toString();
        }

        if(list_sp6.size()>0   &&  !sp6.getSelectedItem().toString().equals(Constants.Choose_Color) )
        {
            val6=sp6.getSelectedItem().toString();
        }





        if(!val1.equals(""))
        {
            val1=Utils.GVCOT(val1);
        }

        if(!val2.equals(""))
        {
            val2=Utils.GVCOT(val2);
        }
        if(!val3.equals(""))
        {
            val3=Utils.GVCOT(val3);
        }

        if(!val4.equals(""))
        {
            val4=Utils.GVCOT(val4);
        }
        if(!val5.equals(""))
        {
            val5=Utils.GVCOT(val5);
        }

        if(!val6.equals(""))
        {
            val6=Utils.GVCOT(val6);
        }

        list_sp7=new ArrayList<>();
        list_size=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_size(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val1,val2,val3,val4,val5,val6);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg7.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp7.add(Constants.Choose_Size);
                    list_size.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_size) {

                        list_sp7.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp7);
                    //         ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp7.setAdapter(aa);


                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showSnack_E(t.getLocalizedMessage());
                prg7.setVisibility(View.GONE);

            }
        });




    }


    private void read_sub6()
    {
        prg6.setVisibility(View.VISIBLE);

        String val1="",val2="",val3="",val4="",val5="";




        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
        {
            val1=sp1.getSelectedItem().toString();
        }
        if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
        {
            val2=sp2.getSelectedItem().toString();
        }
        if(list_sp3.size()>0   &&  !sp3.getSelectedItem().toString().equals(Constants.Choose_Company) )
        {
            val3=sp3.getSelectedItem().toString();
        }

        if(list_sp4.size()>0   &&  !sp4.getSelectedItem().toString().equals(Constants.Choose_Brand) )
        {
            val4=sp4.getSelectedItem().toString();
        }


        if(list_sp5.size()>0   &&  !sp5.getSelectedItem().toString().equals(Constants.Choose_Pattern) )
        {
            val5=sp5.getSelectedItem().toString();
        }






        if(!val1.equals(""))
        {
            val1=Utils.GVCOT(val1);
        }

        if(!val2.equals(""))
        {
            val2=Utils.GVCOT(val2);
        }
        if(!val3.equals(""))
        {
            val3=Utils.GVCOT(val3);
        }

        if(!val4.equals(""))
        {
            val4=Utils.GVCOT(val4);
        }
        if(!val5.equals(""))
        {
            val5=Utils.GVCOT(val5);
        }

        list_sp6=new ArrayList<>();
        list_color=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_color(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val1,val2,val3,val4,val5);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg6.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp6.add(Constants.Choose_Color);
                    list_color.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_color) {

                        list_sp6.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp6);
                    //         ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp6.setAdapter(aa);


                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showSnack_E(t.getLocalizedMessage());
                prg6.setVisibility(View.GONE);

            }
        });




    }

    private void read_sub5()
    {
        prg5.setVisibility(View.VISIBLE);

        String val1="",val2="",val3="",val4="";




        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
        {
            val1=sp1.getSelectedItem().toString();
        }
        if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
        {
            val2=sp2.getSelectedItem().toString();
        }
        if(list_sp3.size()>0   &&  !sp3.getSelectedItem().toString().equals(Constants.Choose_Company) )
        {
            val3=sp3.getSelectedItem().toString();
        }

        if(list_sp4.size()>0   &&  !sp4.getSelectedItem().toString().equals(Constants.Choose_Brand) )
        {
            val4=sp4.getSelectedItem().toString();
        }








        if(!val1.equals(""))
        {
            val1=Utils.GVCOT(val1);
        }

        if(!val2.equals(""))
        {
            val2=Utils.GVCOT(val2);
        }
        if(!val3.equals(""))
        {
            val3=Utils.GVCOT(val3);
        }

        if(!val4.equals(""))
        {
            val4=Utils.GVCOT(val4);
        }


        list_sp5=new ArrayList<>();
        list_pattern=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_pattern(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val1,val2,val3,val4);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg5.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp5.add(Constants.Choose_Pattern);
                    list_pattern.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_pattern) {

                        list_sp5.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp5);
                    //         ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp5.setAdapter(aa);


                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showSnack_E(t.getLocalizedMessage());
                prg5.setVisibility(View.GONE);

            }
        });




    }

    private void read_sub4()
    {
        prg4.setVisibility(View.VISIBLE);

        String val1="",val2="",val3="";




        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
        {
            val1=sp1.getSelectedItem().toString();
        }
        if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
        {
            val2=sp2.getSelectedItem().toString();
        }
        if(list_sp3.size()>0   &&  !sp3.getSelectedItem().toString().equals(Constants.Choose_Company) )
        {
            val3=sp3.getSelectedItem().toString();
        }









        if(!val1.equals(""))
        {
            val1=Utils.GVCOT(val1);
        }

        if(!val2.equals(""))
        {
            val2=Utils.GVCOT(val2);
        }
        if(!val3.equals(""))
        {
            val3=Utils.GVCOT(val3);
        }


        list_sp4=new ArrayList<>();
        list_brand=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_brand(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val1,val2,val3);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg4.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp4.add(Constants.Choose_Brand);
                    list_brand.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_brand) {

                        list_sp4.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp4);
                    //         ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp4.setAdapter(aa);


                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showSnack_E(t.getLocalizedMessage());
                prg4.setVisibility(View.GONE);

            }
        });




    }

    private void read_sub3()
    {
        prg3.setVisibility(View.VISIBLE);

        String val1="",val2="";





        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
            {

                val1=sp1.getSelectedItem().toString();
            }
        if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
            {
                val2=sp2.getSelectedItem().toString();
            }









        if(!val1.equals(""))
        {
            val1=Utils.GVCOT(val1);
        }

        if(!val2.equals(""))
        {
            val2=Utils.GVCOT(val2);
        }


        list_sp3=new ArrayList<>();
        list_comp=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_company(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val1,val2);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg3.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp3.add(Constants.Choose_Company);
                    list_comp.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_comp) {

                        list_sp3.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp3);
           //         ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp3.setAdapter(aa);


                }



            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                showSnack_E(t.getLocalizedMessage());
                prg3.setVisibility(View.GONE);

            }
        });




    }



    private void read_sub2()
    {

        prg2.setVisibility(View.VISIBLE);
        String val="";

        if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
        {



                val=sp1.getSelectedItem().toString();





        }


        if(!val.equals(""))
        {
            val=Utils.GVCOT(val);
        }

        list_sp2=new ArrayList<>();
        list_sub2=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_sub2(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()),val);


        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg2.setVisibility(View.GONE);

                if (response.body().getResult().equals("1")) {


                    list_sp2.add(Constants.Choose_sub_category2);
                    list_sub2.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_sub2) {

                        list_sp2.add(item.getCategory());

                    }




                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp2);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(aa);


                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showSnack_W(t.getLocalizedMessage());
                prg2.setVisibility(View.GONE);
            }
        });


    }

    private void read_sub1()
    {


        prg1.setVisibility(View.VISIBLE);

        list_sp1=new ArrayList<>();
        list_sub1=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_sub1(Constants.api_key, Utils.GVCOT(sp0.getSelectedItem().toString()));


        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                prg1.setVisibility(View.GONE);


                if (response.body().getResult().equals("1")) {



                    list_sp1.add(Constants.Choose_sub_category1);
                    list_sub1.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_sub1) {

                        list_sp1.add(item.getCategory());

                    }


                    ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_sp1);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(aa);


                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showSnack_W(t.getLocalizedMessage());
                prg1.setVisibility(View.GONE);

            }
        });


    }


    private void reset_spinner(androidx.appcompat.widget.AppCompatSpinner spn,String header )
    {

        List<String> list_spn=new ArrayList<>();
      //  list_spn.add(header);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(AddGroupActivity.this, R.layout.spinner_item,list_spn);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(aa);


    }


    private void init() {

        sp0 = findViewById(R.id.sp0);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);
        sp4 = findViewById(R.id.sp4);
        sp5 = findViewById(R.id.sp5);
        sp6 = findViewById(R.id.sp6);
        sp7 = findViewById(R.id.sp7);



        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);

        prg1=findViewById(R.id.prg1);
        prg2=findViewById(R.id.prg2);
        prg3=findViewById(R.id.prg3);
        prg4=findViewById(R.id.prg4);
        prg5=findViewById(R.id.prg5);
        prg6=findViewById(R.id.prg6);
        prg7=findViewById(R.id.prg7);


        btnsave=findViewById(R.id.btnsave);


    }




    private boolean check_main()
    {
        boolean flag=false;
        if(list_sp0.size()>0)
        {

            if(!sp0.getSelectedItem().toString().equals(SelectMainCategory))
            {
                flag=true;
            }
            else
            {
                showSnack_W(getString(R.string.plz_sel_main_category));
            }
        }

        return  flag;

    }



    private void read_category() {
        list_cat = new ArrayList<>();
        list_sp0 = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_category1(Constants.api_key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.body().getResult().equals("1")) {


                    list_sp0.add(SelectMainCategory);
                    list_cat.addAll((response.body().getDetails()));

                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_cat) {

                        list_sp0.add(item.getCategory());

                    }



                    ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, list_sp0);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp0.setAdapter(aa);


                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                showSnack_W(getString(R.string.sme_wrg));

            }
        });

    }



    public void show_dialog(String stkid) {
        Constants.setFrom("4");

        Dialog dialog = new Dialog(AddGroupActivity.this);
        dialog.setCancelable(true);

        View view  = getLayoutInflater().inflate(R.layout.dialog_group_image_upload, null);
        dialog.setContentView(view);

          edt_display_name =  view.findViewById(R.id.edt_display_name);
        Button btndone =  view.findViewById(R.id.btndone);
    img1 =  view.findViewById(R.id.img1);





        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    try {

                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(100000);

                        Constants.setRandom_no("img" + randomInt);

                        CropImage.startPickImageActivity(Objects.requireNonNull(AddGroupActivity.this));
                    } catch (Exception e) {
                      showSnack_E(getString(R.string.error));
                    }



            }
        });




        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });


        dialog.show();









    }


    private void upload() {
        //  btn1.setEnabled(false);
        //      btn1.startAnimation();

        String val="",val2="",val3="",val4="",val5="",val6="",val7="";


        if (!edt_display_name.getText().toString().equals("")) {


            if (image_set_flag) {




//

                String filepath = getApplicationContext().getFilesDir().toString() + "/" + Constants.getRandom_no() + ".jpeg";
                File file = new File(filepath);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("bill", file.getName(), requestBody);


                RequestBody apikey = RequestBody.create(MediaType.parse("text/plain"), Constants.api_key);


                RequestBody by = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT("favas"));

                RequestBody display_name = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(edt_display_name.getText().toString()));



                RequestBody dash_slno = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(Constants.getDash_sl()));

                RequestBody category = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(sp0.getSelectedItem().toString()));

                if(list_sp1.size()>0   &&  !sp1.getSelectedItem().toString().equals(Constants.Choose_sub_category1) )
                {
                    val=sp1.getSelectedItem().toString();
                }

                if(list_sp2.size()>0   &&  !sp2.getSelectedItem().toString().equals(Constants.Choose_sub_category2) )
                {
                    val2=sp2.getSelectedItem().toString();
                }
                if(list_sp3.size()>0   &&  !sp3.getSelectedItem().toString().equals(Constants.Choose_Company) )
                {
                    val3=sp3.getSelectedItem().toString();
                }
                if(list_sp4.size()>0   &&  !sp4.getSelectedItem().toString().equals(Constants.Choose_Brand) )
                {
                    val4=sp4.getSelectedItem().toString();
                }

                if(list_sp5.size()>0   &&  !sp5.getSelectedItem().toString().equals(Constants.Choose_Pattern) )
                {
                    val5=sp5.getSelectedItem().toString();
                }

                if(list_sp6.size()>0   &&  !sp6.getSelectedItem().toString().equals(Constants.Choose_Color) )
                {
                    val6=sp6.getSelectedItem().toString();
                }

                if(list_sp7.size()>0   &&  !sp6.getSelectedItem().toString().equals(Constants.Choose_Size) )
                {
                    val7=sp7.getSelectedItem().toString();
                }


                RequestBody sub_category1 = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val));
                RequestBody sub_category2 = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val2));

                RequestBody company = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val3));
                RequestBody brand = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val4));

                RequestBody pattern = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val5));
                RequestBody color = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val6));

                RequestBody size = RequestBody.create(MediaType.parse("text/plain"), Utils.GVCOT(val7));

                RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Constants.getRandom_no() + ".jpeg");

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call = apiService.save_group_pic(part, apikey, by,display_name,dash_slno,category,sub_category1,sub_category2,company,brand,pattern,color,size, fname);

                call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.save_result_image.Response>() {
                    @Override
                    public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> response) {


                        if (response.body().getResult().equals("1")) {
                            image_set_flag = false;
                            img1.setImageResource(R.drawable.blanc_pic);
                            showSnack_S(getString(R.string.image_uploded_succesfully));


                            File dir = getFilesDir();
                            File file = new File(dir, Constants.getRandom_no() + ".jpeg");
                            boolean deleted = file.delete();



                        }


                    }

                    @Override
                    public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call, Throwable t) {
                        String a = t.getMessage();



                    }
                });


            } else {
                showSnack_W(getString(R.string.plz_sel_pic));
            }

        } else {
            showSnack_W(getString(R.string.plz_choos_item));
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {

            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
                //  Toast.makeText(getApplicationContext(),"Bismillah1",Toast.LENGTH_SHORT).show();

                Uri res = CropImage.getPickImageResultUri(this, data);

                startcrop(res);

            } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                Uri res = UCrop.getOutput(data);



                img1.setImageURI(null);
                img1.setImageURI(res);
                image_set_flag = true;





                try {
                    db = Appdb.getDb_instance(getApplication());


                    List<ImageEntity> list_files = db.getImageEntityDao().get_saved_file_details();


                    for (ImageEntity row : list_files) {

                        long id = row.getId();

                        String fname = row.getSaved_fname();


                        try {
                            File dir = getFilesDir();
                            File file = new File(dir, fname + ".jpeg");
                            boolean deleted = file.delete();

                            db.getImageEntityDao().del(fname);


                        } catch (Exception e) {

                        }


                    }


                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), res);

                    saveReceivedImage(bitmap, Constants.getRandom_no());


                    //save detailsto image table

                    db.getImageEntityDao().insert_image_details(new ImageEntity(0, Constants.getRandom_no()));
                    showSnack_S("" + db.getImageEntityDao().get_count());


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {

        }


    }



}
