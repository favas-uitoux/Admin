package com.project.shopping_admin.fragment.fragment_dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.fragment.app.DialogFragment;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.AddGroupActivity;
import com.project.shopping_admin.activity.AddItemActivity;
import com.project.shopping_admin.activity.SetDashBoardActivity;
import com.project.shopping_admin.adapter.AdapterDashboardGridview;
import com.project.shopping_admin.adapter.AdapterAdditem;
import com.project.shopping_admin.adapter.AdapterDashboardGridview_style1;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.interfaces.SetItemsFragmentInterface;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;

import static com.project.shopping_admin.Constants.SelectDashbboardCategory;




/* Fragment used as page 2 */
public class SetItemsFragment extends DialogFragment implements  SetItemsFragmentInterface{


    private View rootView;
    private androidx.appcompat.widget.AppCompatSpinner sp1;
    private ACProgressFlower dialog_flower;
    private List<String> list_sp1;
    private List<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem> list;


    private SetDashBoardActivity activity;

    private List<com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.DetailsItem> list2;


    private Button btnshw;
    private List<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.DetailsItem> list3;
    private AdapterDashboardGridview_style1 adp3;
//    private com.project.shopping_admin.custom.MyGridView gridview;
    private ImageView img_add;

    private com.project.shopping_admin.custom.MyGridView grid;

    private LinearLayout ll1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_items, container, false);
        init();
        Constants.setSetItemsFragmentInterface(this);

              activity = (SetDashBoardActivity) getActivity();

            grid = new com.project.shopping_admin.custom.MyGridView(getActivity());


        show_spinner_items();

        btnshw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (list_sp1.size() > 0) {
                    if (!sp1.getSelectedItem().toString().equals(SelectDashbboardCategory)) {


                        String dash_slno = "";


                        for (com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row : list) {
                            if (row.getDisplayName().equals(sp1.getSelectedItem().toString())) {

                                dash_slno = row.getSlno();
                                break;

                            }

                        }


                        if (!dash_slno.equals("")) {


                            show_selected_dashboard_category(dash_slno);


                        }


                    } else {
                        activity.showSnack_W(getString(R.string.plz_choos_item));
                    }
                }

            }
        });


        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (list_sp1.size() > 0) {

                    if (!sp1.getSelectedItem().toString().equals(SelectDashbboardCategory)) {


                        String dash_slno = "";


                        for (com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row : list) {
                            if (row.getDisplayName().equals(sp1.getSelectedItem().toString())) {

                                dash_slno = row.getSlno();
                                Constants.setDash_sl(dash_slno);

                                break;

                            }

                        }



                        Dialog dialog_add = new Dialog(getActivity());
                        dialog_add.setCancelable(true);

                        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_item, null);
                        dialog_add.setContentView(view);

                        RadioGroup radiogroup = view.findViewById(R.id.radiogroup);

                        RadioButton radio_btn_group = view.findViewById(R.id.radio_btn_group);

                        RadioButton radio_btn_item = view.findViewById(R.id.radio_btn_item);


                        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {

                                if (radio_btn_item.isChecked()) {

                                    Intent in = new Intent(getActivity(), AddItemActivity.class);
                                    startActivity(in);


                                } else if (radio_btn_group.isChecked()) {
                                    Intent in = new Intent(getActivity(), AddGroupActivity.class);
                                    startActivity(in);

                                }


                            }
                        });




                        dialog_add.show();

                        DisplayMetrics metrics = getResources().getDisplayMetrics();
                        int width = metrics.widthPixels;
                        int height = metrics.heightPixels;

                        dialog_add.getWindow().setLayout((6 * width) / 7, (4 * height) / 10);

                    }
                    else {
                        activity.showSnack_W(getString(R.string.plz_choos_item));
                    }

                }
            }
        });





        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                list3 = new ArrayList<>();
                grid.setAdapter(null);
                ll1.setVisibility(View.GONE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (list_sp1.size() == 0) {
            //  read_dashboard_category();
        }

    }

    private void init() {
        sp1 = rootView.findViewById(R.id.sp1);
        btnshw = rootView.findViewById(R.id.btnshw);
       // gridview = rootView.findViewById(R.id.gridview);
        img_add = rootView.findViewById(R.id.img_add);

        ll1=rootView.findViewById(R.id.ll1);
    }


    private void show_selected_dashboard_category(String dash_slno) {

        list3 = new ArrayList<>();


        grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        grid.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
        grid.setNumColumns(3);
        grid.setColumnWidth(GridView.AUTO_FIT);
        grid.setVerticalSpacing(0);
        grid.setHorizontalSpacing(0);
        grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

       
        ll1.removeView(grid);
        ll1.addView(grid);
        adp3 = new AdapterDashboardGridview_style1(getActivity(), list3);
        grid.setAdapter(adp3);



      //  grid.setAdapter(null);

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call = apiService.show_dash_gridview(Constants.api_key, Utils.GVCOT(dash_slno));

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> response) {

                if (response.body().getResult().equals("1")) {

                    ll1.setVisibility(View.VISIBLE);

                    list3.addAll(response.body().getDetails());
                    adp3 = new AdapterDashboardGridview_style1(getActivity(), list3);
                    grid.setAdapter(adp3);
                }
                else
                {
                    ll1.setVisibility(View.VISIBLE);
                    activity.showSnack_W(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call, Throwable t) {


                activity.showSnack_W(getString(R.string.sme_wrg));

            }
        });


    }

    private void show_spinner_items() {
        list = new ArrayList<>();
        list_sp1 = new ArrayList<>();

        dialog_flower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text(getString(R.string.plz_wait))
                .fadeColor(Color.DKGRAY).build();

        dialog_flower.show();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_dashboard_category(Constants.api_key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                dialog_flower.dismiss();


                list_sp1.add(SelectDashbboardCategory);
                list.addAll((response.body().getDetails()));

                for (DetailsItem item : list) {

                    if(!item.getDisplayName().toLowerCase().contains("banner"))
                    {
                        list_sp1.add(item.getDisplayName());
                    }


                }


                ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_sp1);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                sp1.setAdapter(aa);


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                dialog_flower.dismiss();

            }
        });


    }


    @Override
    public void remove_item(String dash_slno, String image) {

      //  activity.showSnack_W(dash_slno+" "+image);



        list3=new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call = apiService.remove_item_from_dash_category(Constants.api_key,Utils.GVCOT(dash_slno),image);

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> response) {

                if(response.body().getResult().equals("1"))
                {
                    list3.addAll(response.body().getDetails());

                    adp3=new AdapterDashboardGridview_style1(getActivity(), list3);
                    grid.setAdapter(adp3);







                 //   activity.showSnack_S(response.body().getMessage());
                }
                else
                {

                    grid.setAdapter(adp3);

                }
            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.show_dash_gridview.Response> call, Throwable t) {
                activity.showSnack_S(t.getMessage());
            }
        });

    }




}