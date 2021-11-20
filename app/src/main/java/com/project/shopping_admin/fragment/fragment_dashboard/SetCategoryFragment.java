package com.project.shopping_admin.fragment.fragment_dashboard;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.Utils;
import com.project.shopping_admin.activity.SetDashBoardActivity;
import com.project.shopping_admin.adapter.AdapterAddDashboardCategory;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.base.Pojomodelbase;
import com.project.shopping_admin.apiservice.pojos.read_category.DetailsItem;
import com.project.shopping_admin.apiservice.pojos.update_category_status.PojomodelUpdateCategoryStatus;
import com.project.shopping_admin.interfaces.SetCategoryFragmentInterface;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;


/* Fragment used as page 1 */
public class SetCategoryFragment extends Fragment implements SetCategoryFragmentInterface {

    private View rootView;

    private List<DetailsItem> list;
    private List<String> list_sp1;
    private Button btnadd,btn_change,btnadd_offer_section;
    private ACProgressFlower dialog_flower;
    private ImageView iv_up, iv_down;

    Dialog dialog2;

    private EditText edt_display_name;
    private Button btnsave;
    private SetDashBoardActivity activity;
    private SwipeRefreshLayout refresh1;
    private RecyclerView recv;
    private AdapterAddDashboardCategory adp;

    private List<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem> list2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_setcategory, container, false);

        init();

        activity = (SetDashBoardActivity) getActivity();

        read_dashboard_category();


        Constants.setSetCategoryFragmentInterface(this);





        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size = list2.size();
                boolean sel_flag = false;


                for (int j = 0; j < size; j++) {

                    com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);

                    if (row.isIs_ticked()) {
                        sel_flag = true;

                        String slno_to_be_updated=row.getSlno();




                        Dialog dialog_change_display = new Dialog(getActivity());
                        dialog_change_display.setCancelable(true);

                        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chg_display_name, null);
                        dialog_change_display.setContentView(view);

                      //  TextView txt_category = view.findViewById(R.id.txt_category);
                        TextView txt_display = view.findViewById(R.id.txt_display);
                        EditText edit_display_new = view.findViewById(R.id.edit_display_new);
                        Button btnsave= view.findViewById(R.id.btnsave);

//                        txt_category.setText(row.getCategory());
                        txt_display.setText(row.getDisplayName());


                        btnsave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                if(!edit_display_new.getText().toString().trim().equals(""))
                                {

                                    String new_display_name=edit_display_new.getText().toString().trim();



                                    Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                                    Call<Pojomodelbase> call = apiService.change_display_name_category(Constants.api_key, slno_to_be_updated, Utils.GVCOT(new_display_name));

                                    call.enqueue(new Callback<Pojomodelbase>() {
                                        @Override
                                        public void onResponse(Call<Pojomodelbase> call, retrofit2.Response<Pojomodelbase> response) {
                                            dialog_change_display.dismiss();


                                            if(response.body().getResult().equals("1"))
                                            {


                                                for (int j = 0; j < size; j++) {

                                                    com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);

                                                    if(row.getSlno().equals(slno_to_be_updated))
                                                    {

                                                        list2.get(j).setDisplayName(new_display_name);
                                                     //   list2.get(j).setDisplayName(edt_display_name.getText().toString().trim());

                                                       adp.notifyDataSetChanged();
                                                    }



                                                }


                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<Pojomodelbase> call, Throwable t) {
                                            dialog_change_display.dismiss();

                                            activity.showSnack_W(getString(R.string.sme_wrg));

                                        }
                                    });

                                }
                                else
                                {

                                    activity.showSnack_W(getString(R.string.plz_enter_new_display_name));
                                }
                            }
                        });

                        dialog_change_display.show();
                        DisplayMetrics metrics = getResources().getDisplayMetrics();
                        int width = metrics.widthPixels;
                        int height = metrics.heightPixels;

                        //   Dialog yourDialog = dialogFragment.getDialog();
                        dialog_change_display.getWindow().setLayout((6 * width) / 7, (4 * height) / 10);

                        break;

                    }

                }
                if (!sel_flag) {
                    activity.showSnack_W(getString(R.string.you_r_not_selected_any));
                }

            }
        });


        iv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size = list2.size();
                boolean sel_flag = false;


                for (int j = 0; j < size; j++) {

                    com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);

                    if (row.isIs_ticked()) {
                        sel_flag = true;


                        if (!row.getOrderNo().equals("1")) {
                            String slno = row.getSlno();

                            String order_no_old = row.getOrderNo();
                            int order_no_new = Integer.parseInt(row.getOrderNo()) - 1;

                            //  Constants.setTick_slno(slno);


                            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                            Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call = apiService.moveup_category(Constants.api_key, slno, order_no_old, String.valueOf(order_no_new));

                            call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response>() {
                                @Override
                                public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> response) {


                                    if (response.body().getResult().equals("1")) {
                                        list2 = new ArrayList<>();
                                        list2.addAll(response.body().getDetails());


                                        for (int k = 0; k < size; k++) {

                                            com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowk = list2.get(k);

                                            if (rowk.getSlno().equals(slno)) {

                                                com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowkb = new com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem();

                                                rowkb.setSlno(rowk.getSlno());
                                             //   rowkb.setCategory(rowk.getCategory());
                                                rowkb.setDisplayName(rowk.getDisplayName());
                                                rowkb.setOrderNo(rowk.getOrderNo());
                                                rowkb.setIs_ticked(true);
                                                rowkb.setStatus(rowk.getStatus());

                                                list2.set(k, rowkb);


                                                adp = new AdapterAddDashboardCategory(getActivity(), list2);


                                                LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                                recv.setLayoutManager(lm);
                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                                                recv.addItemDecoration(dividerItemDecoration);

                                                recv.setAdapter(adp);

                                                recv.getLayoutManager().scrollToPosition(order_no_new - 1);


                                                break;


                                            }


                                        }


//


                                    }

                                }

                                @Override
                                public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, Throwable t) {

                                }
                            });


                        } else {
                            activity.showSnack_W(getString(R.string.the_item_already_first));
                        }


                        break;
                    }


                }

                if (!sel_flag) {
                    activity.showSnack_W(getString(R.string.you_r_not_selected_any));
                }


            }
        });

        iv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int size = list2.size();
                boolean sel_flag = false;


                for (int j = 0; j < size; j++) {

                    com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);

                    if (row.isIs_ticked()) {
                        sel_flag = true;

                        String size_str = String.valueOf(size);

                        if (!row.getOrderNo().equals(size_str)) {

                            String slno = row.getSlno();

                            String order_no_old = row.getOrderNo();
                            int order_no_new = Integer.parseInt(row.getOrderNo()) + 1;

                            //  Constants.setTick_slno(slno);


                            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                            Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call = apiService.movedown_category(Constants.api_key, slno, order_no_old, String.valueOf(order_no_new));

                            call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response>() {
                                @Override
                                public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> response) {


                                    if (response.body().getResult().equals("1")) {
                                        list2 = new ArrayList<>();
                                        list2.addAll(response.body().getDetails());


                                        for (int k = 0; k < size; k++) {

                                            com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowk = list2.get(k);

                                            if (rowk.getSlno().equals(slno)) {

                                                com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowkb = new com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem();

                                                rowkb.setSlno(rowk.getSlno());
                                                //rowkb.setCategory(rowk.getCategory());
                                                rowkb.setDisplayName(rowk.getDisplayName());
                                                rowkb.setOrderNo(rowk.getOrderNo());
                                                rowkb.setIs_ticked(true);
                                                rowkb.setStatus(rowk.getStatus());

                                                list2.set(k, rowkb);


                                                adp = new AdapterAddDashboardCategory(getActivity(), list2);


                                                LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                                recv.setLayoutManager(lm);
                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                                                recv.addItemDecoration(dividerItemDecoration);

                                                recv.setAdapter(adp);

                                                recv.getLayoutManager().scrollToPosition(order_no_new - 1);


                                                break;


                                            }


                                        }


//


                                    }



                                }

                                @Override
                                public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, Throwable t) {

                                }
                            });

                        } else {
                            activity.showSnack_W(getString(R.string.the_item_already_last));
                        }


                    }


                }

                if (!sel_flag) {
                    activity.showSnack_W(getString(R.string.you_r_not_selected_any));
                }

            }
        });


        btnadd_offer_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               dialog2 = new Dialog(getActivity());
                dialog2.setCancelable(true);

                View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_offer_section, null);
                dialog2.setContentView(view);

              EditText  edt_display_name = view.findViewById(R.id.edt_display_name);

                EditText  edt_count = view.findViewById(R.id.edt_count);

                Spinner spn = view.findViewById(R.id.spn);

             Button   btnsave = view.findViewById(R.id.btnsave);


                dialog2.show();

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                //   Dialog yourDialog = dialogFragment.getDialog();
                dialog2.getWindow().setLayout((6 * width) / 7, (6 * height) / 12);



                List<String> list_spn=new ArrayList<>();

                list_spn.add(Constants.Buy_Qty_Free_Percent);
                list_spn.add(Constants.Buy_Qty_Free_Qty);

                list_spn.add(Constants.Offer);
                list_spn.add(Constants.Today_Offer);


                ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_spn);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spn.setAdapter(aa);




                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        activity.hideSoftKeyboard(getActivity(), v);
                        if (!edt_display_name.getText().toString().trim().equals("")      ) {


                            if(!edt_count.getText().toString().trim().equals("") )
                            {

                                int num =Integer.parseInt(edt_count.getText().toString().trim());

                                if(num>0)
                                {
                                    add_dashboard_offer_section(edt_display_name.getText().toString().trim(),num,spn.getSelectedItem().toString());

                                }
                                else
                                {

                                    activity.showSnack_W(getString(R.string.plz_enter_count_greater));
                                }



                            }
                            else {

                                activity.showSnack_W(getString(R.string.plz_enter_count));
                            }



                        } else {

                            activity.showSnack_W(getString(R.string.plz_give_disp_name));
                        }
                    }
                });




            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {







                            dialog2 = new Dialog(getActivity());
                            dialog2.setCancelable(true);

                            View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_display_name, null);
                            dialog2.setContentView(view);

                            edt_display_name = view.findViewById(R.id.edt_display_name);
                            btnsave = view.findViewById(R.id.btnsave);

                            btnsave.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!edt_display_name.getText().toString().trim().equals("")) {

                                        add_dashboard_section();
                                        activity.hideSoftKeyboard(getActivity(), v);


                                    } else {
                                        activity.showSnack_W(getString(R.string.plz_give_disp_name));
                                    }
                                }
                            });


                            dialog2.show();

                            DisplayMetrics metrics = getResources().getDisplayMetrics();
                            int width = metrics.widthPixels;
                            int height = metrics.heightPixels;

                            //   Dialog yourDialog = dialogFragment.getDialog();
                            dialog2.getWindow().setLayout((6 * width) / 7, (4 * height) / 12);




                } catch (Exception e) {
                    activity.showSnack_W(getString(R.string.plz_choos_item));
                }

            }

        });

        refresh1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                read_dashboard_category();
                refresh1.setRefreshing(false);


            }
        });
//


        return rootView;
    }


    private void init() {

        btnadd = rootView.findViewById(R.id.btnadd);
        refresh1 = rootView.findViewById(R.id.refresh1);
        recv = rootView.findViewById(R.id.recv);
        iv_down = rootView.findViewById(R.id.iv_down);
        iv_up = rootView.findViewById(R.id.iv_up);
        btn_change=rootView.findViewById(R.id.btn_change);
        btnadd_offer_section=rootView.findViewById(R.id.btnadd_offer_section);

    }


    private void add_dashboard_offer_section(String disp_name,int count, String offer_type) {


        list2 = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call = apiService.add_dashboard_offer_cat(Constants.api_key, Utils.GVCOT(disp_name),Utils.GVCOT(""+count),Utils.GVCOT(""+offer_type));

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> response) {

                if (response.body().getResult().equals("1")) {

                    list2.addAll(response.body().getDetails());

                   


                    dialog2.dismiss();


                    adp = new AdapterAddDashboardCategory(getActivity(), list2);


                    LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recv.setLayoutManager(lm);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                    recv.addItemDecoration(dividerItemDecoration);

                    recv.setAdapter(adp);


                    //  activity.showSnack_S(response.body().getMessage());

                }


            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, Throwable t) {
                dialog2.dismiss();

                activity.showSnack_W(getString(R.string.sme_wrg));
            }
        });

    }

    private void add_dashboard_section() {


        list2 = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call = apiService.add_dashboard_category(Constants.api_key, Utils.GVCOT(edt_display_name.getText().toString().trim()));

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> response) {

                if (response.body().getResult().equals("1")) {

                    list2.addAll(response.body().getDetails());

                    edt_display_name.setText("");


                    dialog2.dismiss();


                    adp = new AdapterAddDashboardCategory(getActivity(), list2);


                    LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recv.setLayoutManager(lm);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                    recv.addItemDecoration(dividerItemDecoration);

                    recv.setAdapter(adp);


                    //  activity.showSnack_S(response.body().getMessage());

                }


            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, Throwable t) {
                dialog2.dismiss();

                activity.showSnack_W(getString(R.string.sme_wrg));
            }
        });

    }


    private void read_dashboard_category() {


        list2 = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call = apiService.read_dashboard_category(Constants.api_key);

        call.enqueue(new Callback<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response>() {
            @Override
            public void onResponse(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, retrofit2.Response<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> response) {

                if (response.body().getResult().equals("1")) {

                    list2.addAll(response.body().getDetails());


                    adp = new AdapterAddDashboardCategory(getActivity(), list2);


                    LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recv.setLayoutManager(lm);
                    //   DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
                    //    recv.addItemDecoration(dividerItemDecoration,0);
                    // recycle.addItemDecoration(new DividerItemDecoration(context, 0));
                    recv.setAdapter(adp);


                    //  activity.showSnack_S(response.body().getMessage());

                }


            }

            @Override
            public void onFailure(Call<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.Response> call, Throwable t) {


                activity.showSnack_W(getString(R.string.sme_wrg));
            }
        });

    }


    @Override
    public void update_status(String slno, String status) {

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelUpdateCategoryStatus> call = apiService.update_category_status(Constants.api_key, slno, status);

        call.enqueue(new Callback<PojomodelUpdateCategoryStatus>() {
            @Override
            public void onResponse(Call<PojomodelUpdateCategoryStatus> call, retrofit2.Response<PojomodelUpdateCategoryStatus> response) {

                if (response.body().getResult().equals("1")) {
                    activity.showSnack_W(response.body().getMessage() + "-" + response.body().getSlno());


                    int size = list2.size();


                    for (int j = 0; j < size; j++) {
                        com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);


                        if (row.getSlno().equals(slno)) {

                            com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowb = new com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem();

                            rowb.setSlno(row.getSlno());
                         //   rowb.setCategory(row.getCategory());
                            rowb.setDisplayName(row.getDisplayName());
                            rowb.setOrderNo(row.getOrderNo());
                            rowb.setIs_ticked(row.isIs_ticked());
                            rowb.setStatus(status);

                            list2.set(j, rowb);


                            adp.notifyDataSetChanged();
                            break;
                        }


                    }


                    //need to refresh adapter , after changing data
                }
            }

            @Override
            public void onFailure(Call<PojomodelUpdateCategoryStatus> call, Throwable t) {

            }
        });


    }

    @Override
    public void update_chk_boxes(String slno, boolean ticked_or_not) {

        //    activity.showSnack_W("bismillah");

        int size = list2.size();


        for (int j = 0; j < size; j++) {
            com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem row = list2.get(j);

            if (row.getSlno().equals(slno)) {
                com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowb = new com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem();

                rowb.setSlno(row.getSlno());
          //      rowb.setCategory(row.getCategory());
                rowb.setDisplayName(row.getDisplayName());
                rowb.setOrderNo(row.getOrderNo());
                rowb.setStatus(row.getStatus());
                rowb.setIs_ticked(ticked_or_not);
                list2.set(j, rowb);
            } else {
                com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem rowc = new com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem();

                rowc.setSlno(row.getSlno());
            //    rowc.setCategory(row.getCategory());
                rowc.setDisplayName(row.getDisplayName());
                rowc.setOrderNo(row.getOrderNo());
                rowc.setStatus(row.getStatus());
                rowc.setIs_ticked(false);
                list2.set(j, rowc);
            }


        }
        adp.notifyDataSetChanged();


    }
}
