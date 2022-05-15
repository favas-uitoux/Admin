package com.project.shopping_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.apiservice.pojos.read_item_by_itemname.DetailsItem;
import com.project.shopping_admin.interfaces.AdapterPage2Interface;
import com.project.shopping_admin.model.model_img_and_stkid;

import java.util.ArrayList;
import java.util.List;

public class AdapterPage3 extends RecyclerView.Adapter<AdapterPage3.ViewHolderClass> implements AdapterPage2Interface {


    List<DetailsItem> list;

    Context context;


    //  TextView txtstkid;


    AdapterPage3Sub adp;
    List<model_img_and_stkid> list3;


    public AdapterPage3(Context context, List<DetailsItem> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_page2, parent, false);

        Constants.setAdapterPage2Interface(this);

        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        list3 = new ArrayList<>();
        final DetailsItem cpr = list.get(position);

        holder.txtname.setText(cpr.getItemname());

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.getFrag3Interface().show_dialog(cpr.getStkidFromServer());


            }
        });


        String fnames = cpr.getFname();


        if (!fnames.equals("")) {
            if (fnames.contains(",")) {


                String[] parts = fnames.split(",");


                for (String fname : parts) {
                    //  list2.add(fname);
                    list3.add(new model_img_and_stkid(fname, cpr.getStkidFromServer()));
                }


            } else {
                //    list2.add(fnames);
                list3.add(new model_img_and_stkid(fnames, cpr.getStkidFromServer()));
            }


        }


        adp = new AdapterPage3Sub(context, list3);


        LinearLayoutManager lm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rcv_images.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(holder.rcv_images.getContext(), lm.getOrientation());
        holder.rcv_images.addItemDecoration(dividerItemDecoration);

        holder.rcv_images.setAdapter(adp);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void refresh_adapter(String image, String stockid) {
        //  list3.remove(new model_img_and_stkid(image,stockid));
        adp.notifyDataSetChanged();

//        int pos=0;
//        for (model_img_and_stkid row: list3)
//        {
//            pos=pos+1;
//
//            if(row.getImage().equals(image) && row.getStkid().equals(stockid))
//            {
//                list3.remove(pos);
//                adp.notifyDataSetChanged();
//                break;
//            }
//
//        }

        int len = list.size();
        for (int j = 0; j < len; j++) {
            DetailsItem main_row = list.get(j);


            List<model_img_and_stkid> list3_copy = new ArrayList<>();

            String fnames_copy = main_row.getFname();


            if (!fnames_copy.equals("")) {
                if (fnames_copy.contains(",")) {


                    String[] parts = fnames_copy.split(",");


                    for (String fname : parts) {
                        //  list2.add(fname);
                        //    list3.add(new model_img_and_stkid(fname,cpr.getStkidFromServer()));


                        if (!fname.equals(image)) {
                            list3_copy.add(new model_img_and_stkid(fname, main_row.getStkidFromServer()));
                        }


                    }


                } else {

                    if (!fnames_copy.equals(image)) {
                        list3_copy.add(new model_img_and_stkid(fnames_copy, main_row.getStkidFromServer()));
                    }


                }


            }


        }


    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {

        //   List<model_img_and_stkid> list3;
        TextView txtname;
        ImageView btnadd;

        RecyclerView rcv_images;
        //  AdapterPage2Sub    adp;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);


            txtname = itemView.findViewById(R.id.txtname);
            rcv_images = itemView.findViewById(R.id.rcv_images);
            // txtstkid=itemView.findViewById(R.id.txtstkid);
            btnadd = itemView.findViewById(R.id.btnadd);


        }
    }


}
