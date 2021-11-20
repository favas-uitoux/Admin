package com.project.shopping_admin.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.adapter.AdapterAdditem;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.Response;
import com.project.shopping_admin.interfaces.AdditemActivitytInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AddItemActivity extends BaseActivity implements AdditemActivitytInterface {


    private RecyclerView recv;
    private Button btnser;
    private EditText edtser;
    private RadioGroup radiogroup;
    private RadioButton radio_btn_itemname, radio_btn_barcode;
    private List<DetailsItem> list;
    private AdapterAdditem adp;
    private String type = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        init();

        Constants.setAdditemActivitytInterface(this);

        btnser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSoftKeyboard(AddItemActivity.this,v);
                list=new ArrayList<>();
                recv.setAdapter(null);

                if (radiogroup.getCheckedRadioButtonId() != -1) {

                    if (!edtser.getText().toString().equals("")) {


                        String itemname = edtser.getText().toString().trim();

                        if (!type.equals("barcode")) {
                            itemname = "%" + itemname + "%";


                        }
                        itemname = Utils.GVCOT(itemname);


                        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                        Call<Response> call = apiService.get_product_by_barcode_or_itemname(Constants.api_key, itemname, type);
                        call.enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                if (response.body().getResult().equals("1")) {

                                    list.addAll(response.body().getDetails());
//

                                    adp = new AdapterAdditem(getApplicationContext(), list);


//
                                    LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                    recv.setLayoutManager(lm);
                                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                                    recv.addItemDecoration(dividerItemDecoration);

                                    recv.setAdapter(adp);


                                }


                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });


                    }


                } else {
                    showSnack_W(getString(R.string.plz_sel_itemname_or_bar));
                }

            }
        });


        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (radio_btn_barcode.isChecked()) {
                    type = "barcode";


                } else if (radio_btn_itemname.isChecked()) {
                    type = "itemname";
                }


            }
        });


    }


    private void init() {
        btnser = findViewById(R.id.btnser);
        edtser = findViewById(R.id.edtser);
        radiogroup = findViewById(R.id.radiogroup);
        radio_btn_itemname = findViewById(R.id.radio_btn_itemname);
        radio_btn_barcode = findViewById(R.id.radio_btn_barcode);
        recv = findViewById(R.id.recv);
    }

    @Override
    public void set_item(String stkid, String image) {

        String dash_slno = Constants.getDash_sl();

        if(Constants.getFrom().equals("5"))
        {






            Dialog dialog_add = new Dialog(AddItemActivity.this);
            dialog_add.setCancelable(true);

            View view =this. getLayoutInflater().inflate(R.layout.dialog_banne_captionr, null);
            dialog_add.setContentView(view);

            EditText edt_cap = dialog_add.findViewById(R.id.edt_cap);
            Button btn_save = dialog_add.findViewById(R.id.btn_save);

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog_add.dismiss();
                    hideSoftKeyboard(AddItemActivity.this,v);
                    Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                    Call<Pojomodelbase> call = apiService.save_banner_items(Constants.api_key, Utils.GVCOT(dash_slno), Utils.GVCOT(stkid), Utils.GVCOT(image),Utils.GVCOT(edt_cap.getText().toString().trim()));

                    call.enqueue(new Callback<Pojomodelbase>() {
                        @Override
                        public void onResponse(Call<Pojomodelbase> call, retrofit2.Response<Pojomodelbase> response) {

                            showSnack_W(response.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<Pojomodelbase> call, Throwable t) {

                            showSnack_W(t.getMessage());
                        }
                    });




                }
            });

            dialog_add.show();

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;

            dialog_add.getWindow().setLayout((6 * width) / 7, (4 * height) / 10);

        }
        else {

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            Call<Pojomodelbase> call = apiService.save_dash_items(Constants.api_key, Utils.GVCOT(dash_slno), Utils.GVCOT(stkid), Utils.GVCOT(Constants.item), Utils.GVCOT(image));

            call.enqueue(new Callback<Pojomodelbase>() {
                @Override
                public void onResponse(Call<Pojomodelbase> call, retrofit2.Response<Pojomodelbase> response) {

                    if (response.body().getResult().equals("1")) {
                        showSnack_W(response.body().getMessage());
                    }
                    else
                    {
                        showSnack_W(response.body().getMessage());
                    }

                }

                @Override
                public void onFailure(Call<Pojomodelbase> call, Throwable t) {

                }
            });
        }







    }
}
