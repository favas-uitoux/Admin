package com.project.shopping_admin.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.shopping_admin.BuildConfig;
import com.project.shopping_admin.Constants;

import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.SetPhotoActivity;
import com.project.shopping_admin.activity.SetPhotoActivity2;
import com.project.shopping_admin.adapter.AdapterPage3;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response;
import com.project.shopping_admin.interfaces.Frag3Interface;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;
import static com.project.shopping_admin.Constants.SelectMainCategory;
import static com.project.shopping_admin.Constants.SelectSubCategory;
import static com.project.shopping_admin.Constants.TAKE_PIC;


/* Fragment used as page 2 */
public class Page3Fragment extends DialogFragment implements Frag3Interface {

    private androidx.appcompat.widget.AppCompatSpinner sp1, sp2, sp3;
    private Button btn;
    private View rootView;
    private List<DetailsItem> list;

    private RecyclerView recv;
    private AdapterPage3 adp;
    private boolean image_set_flag = false;
    private TextView txt1;
    private SetPhotoActivity2 activity;
    private List<String> list_sp1, list_sp2, list_sp3;

    private List<com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem> list_cat, list_sub_cat1, list_sub_cat2;

    private de.hdodenhof.circleimageview.CircleImageView img1;

    private boolean spn_flag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_page3, container, false);
        init();
        activity = (SetPhotoActivity2) getActivity();


        Constants.setFrag3Interface(this);


        read_category();


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (!sp1.getSelectedItem().toString().equals(SelectMainCategory)) {

                    spn_flag = true;
                    list_sp2 = new ArrayList<>();
                    list_sp3 = new ArrayList<>();

                    sp2.setAdapter(null);
                    sp3.setAdapter(null);
                    list_sub_cat1 = new ArrayList<>();
                    read_category2();

                } else {
                    spn_flag = false;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                sp3.setAdapter(null);
                list_sp3 = new ArrayList<>();


                list_sub_cat2 = new ArrayList<>();
                read_category3();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spn_flag) {



                    list = new ArrayList<>();
                    recv.setAdapter(null);

                    String main_category = sp1.getSelectedItem().toString();
//
//
//


                    String sub1 = "", sub2 = "";

                    try {
                        sub1 = sp2.getSelectedItem().toString();
                    } catch (Exception e) {

                    }

                    try {
                        sub2 = sp3.getSelectedItem().toString();
                    } catch (Exception e) {

                    }

                    if (!sub1.trim().equals("")) {
                        sub1 = Utils.GVCOT(sub1);
                    }
                    if (!sub2.trim().equals("")) {
                        sub2 = Utils.GVCOT(sub2);
                    }


                    Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                    Call<Response> call = apiService.read_item_by_category(Constants.api_key, Utils.GVCOT(main_category), sub1, sub2);


                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                            if (response.body().getResult().equals("1")) {
                                list.addAll(response.body().getDetails());


                                adp = new AdapterPage3(getActivity(), list);


                                LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                recv.setLayoutManager(lm);
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                                recv.addItemDecoration(dividerItemDecoration);

                                recv.setAdapter(adp);

                            }


                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            String a = "dfd";
                        }
                    });

                }


            }
        });


        return rootView;
    }


    private void read_category() {


        list_cat = new ArrayList<>();
        list_sp1 = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call = apiService.read_category1(Constants.api_key);

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.read_category.Response> response) {

                if (response.body().getResult().equals("1")) {


                    list_sp1.add(SelectMainCategory);
                    list_cat.addAll((response.body().getDetails()));
//
                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_cat) {

                        list_sp1.add(item.getCategory());

                    }


//
                    ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_sp1);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(aa);




                }

            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, Throwable t) {


                activity.showSnack_E(t.getLocalizedMessage());

            }
        });


    }


    private void read_category2() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call = apiService.read_sub1(Constants.api_key, Utils.GVCOT(sp1.getSelectedItem().toString()));
        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.read_category.Response> response) {
                if (response.body().getResult().equals("1")) {
                    // list_sp2.add(SelectSubCategory);
                    list_sub_cat1.addAll((response.body().getDetails()));
//
                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_sub_cat1) {

                        list_sp2.add(item.getCategory());

                    }


//
                    ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_sp2);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    sp2.setAdapter(aa);

                }
            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, Throwable t) {
                activity.showSnack_E(t.getLocalizedMessage());

            }
        });


    }

    private void read_category3() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call = apiService.read_category3(Constants.api_key, Utils.GVCOT(sp1.getSelectedItem().toString()), Utils.GVCOT(sp2.getSelectedItem().toString()));
        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.read_category.Response> response) {
                if (response.body().getResult().equals("1")) {
                    // list_sp2.add(SelectSubCategory);
                    list_sub_cat2.addAll((response.body().getDetails()));
//
                    for (com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem item : list_sub_cat2) {

                        list_sp3.add(item.getCategory());

                    }


//
                    ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_sp3);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    sp3.setAdapter(aa);

                }
            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_category.Response> call, Throwable t) {
                activity.showSnack_E(t.getLocalizedMessage());

            }
        });


    }

    private void init() {
        btn = rootView.findViewById(R.id.btn);
        sp1 = rootView.findViewById(R.id.sp1);
        sp2 = rootView.findViewById(R.id.sp2);
        sp3 = rootView.findViewById(R.id.sp3);
        recv = rootView.findViewById(R.id.recv);

    }


    @Override
    public void delete_and_refresh(String stkid, String img) {

        String fnames_b = "";
        int size = list.size();

        for (int j = 0; j < size; j++) {
            DetailsItem row = list.get(j);

            if (row.getStkidFromServer().equals(stkid)) {


                DetailsItem rowb = new DetailsItem();


                rowb.setColor(row.getColor());
                rowb.setSize(row.getSize());

                rowb.setItemname(row.getItemname());
                rowb.setCompany(row.getCompany());
                rowb.setCategory(row.getCategory());
                rowb.setSubCategory1(row.getSubCategory1());
                rowb.setSubCategory2(row.getSubCategory2());
                rowb.setBrand(row.getBrand());
                rowb.setStkidFromServer(row.getStkidFromServer());

                String fnames = row.getFname();
                if (!fnames.equals("")) {
                    if (fnames.contains(",")) {


                        String[] parts = fnames.split(",");


                        for (String fname : parts) {

                            if (!fname.equals(img)) {
                                if (fnames_b.equals("")) {
                                    fnames_b = fname;
                                } else {
                                    fnames_b = fnames_b + "," + fname;
                                }


                            }

                        }
                        rowb.setFname(fnames_b);

                    } else {
                        rowb.setFname("");
                    }


                }


                list.set(j, rowb);
               // Toast.makeText(getActivity(), stkid + "-" + list.get(j).getFname(), Toast.LENGTH_LONG).show();

                Toast.makeText(getActivity(),  "Deleted" , Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void show_dialog(String stkid) {
        Constants.setFrom("3");

        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_image_upload, null);
        dialog.setContentView(view);

        txt1 = view.findViewById(R.id.txt1);
        Button btn1 = view.findViewById(R.id.btn1);
        img1 = view.findViewById(R.id.img1);

        txt1.setText(stkid);


        img1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                if (!txt1.getText().toString().equals("")) {

                    try {
                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(100000);

                        Constants.setRandom_no("img" + randomInt);




                        CropImage.startPickImageActivity(Objects.requireNonNull(getActivity()));
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(getActivity(), getString(R.string.plz_choos_item), Toast.LENGTH_LONG).show();

                }


            }
        });

        img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                                        Intent takepicIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takepicIntent.resolveActivity(getActivity().getPackageManager());
                        File photofile= new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"100.jpg");
                     // Uri furi=  FileProvider.getUriForFile(getActivity(),getActivity().getPackageName()+".file_provider_paths",photofile);
                        Uri furi=       FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
                                BuildConfig.APPLICATION_ID + ".provider", photofile);


                        takepicIntent.putExtra(MediaStore.EXTRA_OUTPUT,furi);
                        getActivity().startActivityForResult(takepicIntent,TAKE_PIC);

                return true;
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });


        dialog.show();


    }

//    protected void dispatchTakePictureIntent() {
//
//        // Check if there is a camera.
//        Context context = getActivity();
//        PackageManager packageManager = context.getPackageManager();
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
//            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
//                    .show();
//            return;
//        }
//
//        // Camera exists? Then proceed...
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        // Ensure that there's a camera activity to handle the intent
//        SetPhotoActivity2 activity = (SetPhotoActivity2)getActivity();
//        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
//            // Create the File where the photo should go.
//            // If you don't do this, you may get a crash in some devices.
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Toast toast = Toast.makeText(activity, "There was a problem saving the photo...", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri fileUri = Uri.fromFile(photoFile);
//            //    activity.setCapturedImageURI(fileUri);
//             //   activity.setCurrentPhotoPath(fileUri.getPath());
////                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
////                        activity.getCapturedImageURI());
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }


    @Override
    public void setImageView(Uri res) {
        //  dialog.getContext().

        img1.setImageURI(null);
        img1.setImageURI(res);
        image_set_flag = true;
    }

    @Override
    public void add_and_refresh(String stkid, String img) {


        int size = list.size();

        for (int j = 0; j < size; j++) {
            String fnames_b = "";
            DetailsItem row = list.get(j);

            if (row.getStkidFromServer().equals(stkid)) {


                DetailsItem rowb = new DetailsItem();


                rowb.setColor(row.getColor());
                rowb.setSize(row.getSize());

                rowb.setItemname(row.getItemname());
                rowb.setCompany(row.getCompany());
                rowb.setCategory(row.getCategory());
                rowb.setSubCategory1(row.getSubCategory1());
                rowb.setSubCategory2(row.getSubCategory2());
                rowb.setBrand(row.getBrand());
                rowb.setStkidFromServer(row.getStkidFromServer());

                String fnames = row.getFname();

                if (fnames.trim().equals("")) {
                    rowb.setFname(img);
                } else {
                    fnames_b = fnames + "," + img;
                    rowb.setFname(fnames_b);
                }


//                if (!fnames.equals("")) {
//                    if (fnames.contains(",")) {
//
//                        fnames_b=fnames+","+img;
//
//
//
//
//                        rowb.setFname(fnames_b);
//
//                    } else {
//                        rowb.setFname(img);
//                    }
//
//
//                }


                list.set(j, rowb);
                adp.notifyDataSetChanged();

                //   Toast.makeText(getActivity(), stkid + "-" + list.get(j).getFname(), Toast.LENGTH_LONG).show();
            }
        }


    }


    private void upload() {
        //  btn1.setEnabled(false);
        //      btn1.startAnimation();


        if (!txt1.getText().toString().equals("")) {


            if (image_set_flag) {


//

                String filepath = getActivity().getApplicationContext().getFilesDir().toString() + "/" + Constants.getRandom_no() + ".jpeg";
                File file = new File(filepath);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("bill", file.getName(), requestBody);

                RequestBody by = RequestBody.create(MediaType.parse("text/plain"), "favas");

                RequestBody stockid = RequestBody.create(MediaType.parse("text/plain"), txt1.getText().toString().trim());
                RequestBody apikey = RequestBody.create(MediaType.parse("text/plain"), Constants.api_key);
                RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Constants.getRandom_no() + ".jpeg");

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call = apiService.save_item_pic(part, stockid, by, fname,apikey);

                call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.save_result_image.Response>() {
                    @Override
                    public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> response) {


                        if (response.body().getResult().equals("1")) {
                            image_set_flag = false;
                            img1.setImageResource(R.drawable.blanc_pic);
                            activity.showSnack_S(getString(R.string.image_uploded_succesfully));


                            File dir = getActivity().getFilesDir();
                            File file = new File(dir, Constants.getRandom_no() + ".jpeg");
                            boolean deleted = file.delete();

                            add_and_refresh(txt1.getText().toString(), response.body().getSlno());

                            //     activity.showSnack_S("" + deleted);

                            //     list2.add(response.body().getSlno());
                            //   adp.notifyDataSetChanged();


                        }


                        // showSnack_S(response.body().getMessage());


//                reset();
//                btn1.stopAnimation();
//                btn1.revertAnimation();
//                btn1.setEnabled(true);

                    }

                    @Override
                    public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call, Throwable t) {
                        String a = t.getMessage();
//                btn1.stopAnimation();
//                btn1.revertAnimation();
//                btn1.setEnabled(true);


                    }
                });


            } else {
                activity.showSnack_W(getString(R.string.plz_sel_pic));
            }

        } else {
            activity.showSnack_W(getString(R.string.plz_choos_item));
        }

    }


}