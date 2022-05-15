package com.project.shopping_admin.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.project.shopping_admin.BuildConfig;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.SetPhotoActivity2;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.adapter.AdapterHome;
import com.project.shopping_admin.adapter.AdapterPage1;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response;
import com.project.shopping_admin.interfaces.Frag1Interface;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.project.shopping_admin.Constants.TAKE_PIC;


/* Fragment used as page 1 */
public class Page1Fragment extends Fragment implements Frag1Interface {

    private View rootView;
    private Button btn;
    private ACProgressFlower dialog;
    private TextView txt_itemname;
    private EditText edt_stkid;
    private de.hdodenhof.circleimageview.CircleImageView iv1;

    private Button btnsave;

    private SetPhotoActivity2 activity;
    private String stkid_from_server = "";
    private boolean image_set_flag = false;
    private GridView gridview;
    private List<String> list;

    private AdapterPage1 adp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_page1, container, false);

        init();
        activity = (SetPhotoActivity2) getActivity();
        Constants.setFrag1Interface(this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getActivity(),"bismillah",Toast.LENGTH_LONG).show();


                if (!edt_stkid.getText().toString().trim().equals("")) {
                    gridview.setAdapter(null);
                    stkid_from_server = "";
                    read();
                } else {


                    activity.showSnack_W(getString(R.string.enter_barcode));


                }
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.setFrom("1");
                if (!stkid_from_server.equals("")) {

                    try {

                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(100000);

                        Constants.setRandom_no("img" + randomInt);

                        CropImage.startPickImageActivity(Objects.requireNonNull(getActivity()));
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
                    }
                } else {
                    activity.showSnack_W(getString(R.string.plz_choos_item));
                }


            }
        });

        iv1.setOnLongClickListener(new View.OnLongClickListener() {
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



        return rootView;
    }


    private void init() {
        btn = rootView.findViewById(R.id.btn);
        txt_itemname = rootView.findViewById(R.id.txt_itemname);
        edt_stkid = rootView.findViewById(R.id.edt_stkid);
        iv1 = rootView.findViewById(R.id.iv1);
        btnsave = rootView.findViewById(R.id.btnsave);
        gridview = rootView.findViewById(R.id.gridview);

    }


    private void read() {

        list = new ArrayList<>();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text(getString(R.string.plz_wait))
                .fadeColor(Color.DKGRAY).build();

        dialog.show();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response> call = apiService.read_item_by_stkid(Constants.api_key, edt_stkid.getText().toString().trim());

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response> call, retrofit2.Response<Response> response) {

                dialog.dismiss();

                if (response.body().getResult().equals("1")) {
                    txt_itemname.setText(response.body().getDetails().get(0).getItemname());


                    stkid_from_server = response.body().getDetails().get(0).getStkid_from_server();


                    String fnames = response.body().getDetails().get(0).getFname();

                    if (!fnames.equals("")) {
                        if (fnames.contains(",")) {


                            String[] parts = fnames.split(",");


                            for (String fname : parts) {
                                list.add(fname);
                            }


                        } else {
                            list.add(fnames);
                        }


                    }


                    adp = new AdapterPage1(getActivity(), list, stkid_from_server);
                    gridview.setAdapter(adp);


                } else {
                    activity.showSnack_W(response.body().getMessage());
                }

                //   Toast.makeText(getActivity(),"suc",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.read_item_by_stkid.Response> call, Throwable t) {
                dialog.dismiss();

                Toast.makeText(getActivity(), getString(R.string.sme_wrg), Toast.LENGTH_LONG).show();
            }
        });

    }


    private void upload() {
        //  btn1.setEnabled(false);
        //      btn1.startAnimation();


        if (!stkid_from_server.equals("")) {


            if (image_set_flag) {


//

                String filepath = getActivity().getApplicationContext().getFilesDir().toString() + "/" + Constants.getRandom_no() + ".jpeg";
                File file = new File(filepath);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("bill", file.getName(), requestBody);

                RequestBody by = RequestBody.create(MediaType.parse("text/plain"), "favas");

                RequestBody stockid = RequestBody.create(MediaType.parse("text/plain"), stkid_from_server);

                RequestBody apikey = RequestBody.create(MediaType.parse("text/plain"), Constants.api_key);

                RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Constants.getRandom_no() + ".jpeg");

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call = apiService.save_item_pic(part, stockid, by, fname,apikey);

                call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.save_result_image.Response>() {
                    @Override
                    public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.save_result_image.Response> response) {


                        if (response.body().getResult().equals("1")) {
                            image_set_flag = false;
                            iv1.setImageResource(R.drawable.blanc_pic);
                            activity.showSnack_S(getString(R.string.image_uploded_succesfully));


                            File dir = getActivity().getFilesDir();
                            File file = new File(dir, Constants.getRandom_no() + ".jpeg");
                            boolean deleted = file.delete();

                         //   activity.showSnack_S("" + deleted);

                            list.add(response.body().getSlno());
                            adp.notifyDataSetChanged();


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


    @Override
    public void setImageView(Uri res) {

        // Toast.makeText(getActivity(),"ddsfd",Toast.LENGTH_LONG).show();

        iv1.setImageURI(null);
        iv1.setImageURI(res);
        image_set_flag = true;

    }

    @Override
    public void refresh_adapter(String slno) {

        list.remove(new String(slno));

        adp.notifyDataSetChanged();
    }
}
