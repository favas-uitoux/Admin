package com.project.shopping_admin.fragment.fragment_dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.AddGroupActivity;
import com.project.shopping_admin.activity.AddItemActivity;
import com.project.shopping_admin.activity.SetDashBoardActivity;
import com.project.shopping_admin.activity.SetPhotoActivity2;
import com.project.shopping_admin.adapter.AdapterBanner;
import com.project.shopping_admin.adapter.AdapterDashboardGridview_style1;
import com.project.shopping_admin.adapter.AdapterPage2;
import com.project.shopping_admin.adapter.AdapterPage3;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.show_banner.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.show_banner.Response;
import com.project.shopping_admin.interfaces.Frag3Interface;
import com.project.shopping_admin.interfaces.SetBannerInterface;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.project.shopping_admin.Constants.SelectDashbboardCategory;
import static com.project.shopping_admin.Constants.SelectMainCategory;
import static com.project.shopping_admin.Constants.api_key;


/* Fragment used as page 2 */
public class SetBannerFragment extends DialogFragment implements SetBannerInterface {


    private View rootView;
    private List<com.project.shopping_admin.apiservice.pojos.read_banner.DetailsItem> list_sp1;
    private androidx.appcompat.widget.AppCompatSpinner sp1;
    private Button btnshw;
    private SetDashBoardActivity activity;
    private RecyclerView recv;
    private LinearLayout ll1;
    private ImageView img_add;

    private TextView txt1;
    private  de.hdodenhof.circleimageview.CircleImageView  img1;
    private boolean image_set_flag = false;

    private List<DetailsItem> list;
    private AdapterBanner adp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_banner, container, false);
        init();

        Constants.setSetBannerInterface(this);

        activity = (SetDashBoardActivity) getActivity();

        show_spinner_items();

        btnshw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sp1.getSelectedItem().toString().equals(getString(R.string.choose_banner))) {



                    list=new ArrayList<>();
                    recv.setAdapter(null);
                    Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                    Call<Response> call = apiService.show_banner(Constants.api_key, sp1.getSelectedItem().toString());

                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            if(response.body() !=null)
                            {
                                if(response.body().getResult().equals("1"))
                                {
                                    list=new ArrayList<>();
                                    recv.setAdapter(null);

                                    list.addAll(response.body().getDetails());


                                    adp = new AdapterBanner(getActivity(), list);


                                    LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                    recv.setLayoutManager(lm);
                                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                                    recv.addItemDecoration(dividerItemDecoration);

                                    recv.setAdapter(adp);




                                    ll1.setVisibility(View.VISIBLE);
                                }
                                else
                                {
                                    list=new ArrayList<>();
                                    recv.setAdapter(null);
                                    activity.showSnack_W(response.body().getMessage());

                                  

                                    ll1.setVisibility(View.VISIBLE);

                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });





                } else {
                    activity.showSnack_W(getString(R.string.choose_banner));
                }


            }
        });
//
//
//
//        Constants.setFrag3Interface(this);
//
//
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


             //   list3 = new ArrayList<>();
             //   grid.setAdapter(null);
                ll1.setVisibility(View.GONE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                show_dialog("Banner");

//                String dash_slno = "";
//
//
//                if (list_sp1.size() > 0) {
//
//                    if (!sp1.getSelectedItem().toString().equals(getString(R.string.choose_banner))) {
//
//                        for (com.project.shopping_admin.apiservice.pojos.read_banner.DetailsItem row : list_sp1) {
//                            if (row.getDisplayName().equals(sp1.getSelectedItem().toString())) {
//
//                                dash_slno = row.getDashSlno();
//                                Constants.setDash_sl(dash_slno);
//
//                                break;
//
//                            }
//
//                        }
//
//
//                    }
//
//                }
//
//
//                if (!dash_slno.equals("")) {
//
//
//
//                Dialog dialog_add = new Dialog(getActivity());
//                dialog_add.setCancelable(true);
//
//                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_item_banner, null);
//                dialog_add.setContentView(view);
//
//                RadioGroup radiogroup = view.findViewById(R.id.radiogroup);
//
//                RadioButton radio_btn_group = view.findViewById(R.id.radio_btn_group);
//                RadioButton radio_btn_item = view.findViewById(R.id.radio_btn_item);
//                RadioButton radio_btn_none = view.findViewById(R.id.radio_btn_none);
//
//
//                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                        if (radio_btn_item.isChecked()) {
//                            Constants.setFrom("5");
//                            Intent in = new Intent(getActivity(), AddItemActivity.class);
//                            startActivity(in);
//
//
//                        } else if (radio_btn_group.isChecked()) {
//                            Intent in = new Intent(getActivity(), AddGroupActivity.class);
//                            startActivity(in);
//
//                        } else if (radio_btn_none.isChecked()) {
//                            show_dialog("Banner");
//                        }
//
//
//                    }
//                });
//
//                dialog_add.show();
//
//                DisplayMetrics metrics = getResources().getDisplayMetrics();
//                int width = metrics.widthPixels;
//                int height = metrics.heightPixels;
//
//                dialog_add.getWindow().setLayout((6 * width) / 7, (4 * height) / 10);
//
//            }

            }
        });


//


        return rootView;
    }










    private void show_spinner_items() {

        list_sp1 = new ArrayList<>();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.read_banner.Response> call = apiService.read_banner_types(Constants.api_key);

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_banner.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_banner.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.read_banner.Response> response) {

                if(response.body().getResult().equals("1"))
                {
                    list_sp1.addAll(response.body().getDetails());


                    List<String> list_str=new ArrayList<>();
                    list_str.add( getString(R.string.choose_banner));

                    for(com.project.shopping_admin.apiservice.pojos.read_banner.DetailsItem row:list_sp1)
                    {
                        list_str.add(row.getDisplayName());
                    }


                    ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_str);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    sp1.setAdapter(aa);
                }
            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_banner.Response> call, Throwable t) {

            }
        });







    }


    private void init() {
        sp1 = rootView.findViewById(R.id.sp1);
        btnshw = rootView.findViewById(R.id.btnshw);
        recv = rootView.findViewById(R.id.recv);
        ll1 = rootView.findViewById(R.id.ll1);
        img_add = rootView.findViewById(R.id.img_add);


    }



    public void show_dialog(String heading) {
        Constants.setFrom("5");

        Dialog   dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_image_upload, null);
        dialog.setContentView(view);


        txt1 =  view.findViewById(R.id.txt1);
        Button btn1 =  view.findViewById(R.id.btn1);
         img1 =  view.findViewById(R.id.img1);

        txt1.setText(heading);



        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!txt1.getText().toString().equals("")) {

                    try {

                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(100000);

                        Constants.setRandom_no("img" + randomInt);

                        CropImage.startPickImageActivity(Objects.requireNonNull(getActivity()));
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "aaaa", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(getActivity(),getString(R.string.plz_choos_item),Toast.LENGTH_LONG).show();

                }


            }
        });




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // activity.showSnack_W("uploading ");
                upload();
            }
        });


        dialog.show();









    }



    private void upload() {
        //  btn1.setEnabled(false);
        //      btn1.startAnimation();


        if (!txt1.getText().toString().equals("")) {


            if (image_set_flag) {


                list=new ArrayList<>();
                recv.setAdapter(null);


//

                String filepath = getActivity().getApplicationContext().getFilesDir().toString() + "/" + Constants.getRandom_no() + ".jpeg";
                File file = new File(filepath);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("bill", file.getName(), requestBody);

                RequestBody apikey = RequestBody.create(MediaType.parse("text/plain"), Constants.api_key);
                RequestBody by = RequestBody.create(MediaType.parse("text/plain"), "favas");

                RequestBody bannner_type = RequestBody.create(MediaType.parse("text/plain"), sp1.getSelectedItem().toString());

                RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Constants.getRandom_no() + ".jpeg");

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Response> call = apiService.save_banner_pic(part,apikey,bannner_type,by,fname);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                        if (response.body().getResult().equals("1")) {
                            image_set_flag = false;
                            img1.setImageResource(R.drawable.blanc_pic);
                          //  activity.showSnack_S(getString(R.string.image_uploded_succesfully));


                            File dir = getActivity().getFilesDir();
                            File file = new File(dir, Constants.getRandom_no() + ".jpeg");
                            boolean deleted = file.delete();

                            //add_and_refresh(txt1.getText().toString(),response.body().getSlno());




                            list.addAll(response.body().getDetails());

                            adp = new AdapterBanner(getActivity(), list);


                            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            recv.setLayoutManager(lm);
                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                            recv.addItemDecoration(dividerItemDecoration);

                            recv.setAdapter(adp);



                            ll1.setVisibility(View.VISIBLE);




                        }
                        else
                        {
                            activity.showSnack_W(response.body().getMessage());
                        }




                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

//
                        activity.showSnack_W(getString(R.string.sme_wrg));

                    }
                });


            } else {
                activity.showSnack_W(getString(R.string.plz_sel_pic));
            }

        } else {
            activity.showSnack_W(getString(R.string.plz_choos_item));
        }

    }


    @Override
    public void setImageView(Uri res) {

        img1.setImageURI(null);
        img1.setImageURI(res);
        image_set_flag = true;

    }

    @Override
    public void remove_item(String dash_slno, String image) {


        //  activity.showSnack_W(dash_slno+"  img="+image);


        if(!dash_slno.trim().equals(""))
        {






        list = new ArrayList<>();
        recv.setAdapter(null);


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.remove_from_banner(Constants.api_key, Utils.GVCOT(dash_slno), image, sp1.getSelectedItem().toString());

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body().getResult().equals("1")) {
                    list.addAll(response.body().getDetails());

                    adp = new AdapterBanner(getActivity(), list);


                    LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recv.setLayoutManager(lm);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                    recv.addItemDecoration(dividerItemDecoration);

                    recv.setAdapter(adp);


                    ll1.setVisibility(View.VISIBLE);


                    //   activity.showSnack_S(response.body().getMessage());
                } else {

                    activity.showSnack_W(response.body().getMessage());

                    //       recv.setAdapter(null);

                    //         ll1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                activity.showSnack_S(t.getMessage());
            }
        });

    }

        else
        {
            activity.showSnack_W(""+dash_slno);
        }


    }
}