package com.project.shopping_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem;
import com.project.shopping_admin.interfaces.AdapterPage2Interface;
import com.project.shopping_admin.model.model_img_and_stkid;

import java.util.ArrayList;
import java.util.List;

public class AdapterAddDashboardCategory extends RecyclerView.Adapter<AdapterAddDashboardCategory.ViewHolderClass>  {


    List<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem> list;

    Context context;





    public AdapterAddDashboardCategory(Context context, List<com.project.shopping_admin.apiservice.pojos.add_dashboard_category.DetailsItem> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_set_category, parent, false);



        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {





        final DetailsItem cpr = list.get(position);


        holder.txt_order.setText(cpr.getOrderNo());

        holder.txt_display.setText(cpr.getDisplayName());
        holder.swtch.setColorOn(R.color.color3);
        holder.swtch.setColorBorder(R.color.color3);

        if(cpr.getStatus().equals("1"))
        {
            holder.swtch.setOn(true);

        }
        else
        {
            holder.swtch.setOn(false);

        }

        holder.chk1.setChecked(cpr.isIs_ticked());
        if(cpr.isIs_ticked())
        {
            //set background
            holder.ll1.setBackgroundColor(context.getResources().getColor(R.color.lightash));
        }
        else
        {
            holder.ll1.setBackgroundColor(context.getResources().getColor(R.color.white));
        }




//        if(cpr.getSlno().equals(Constants.getTick_slno()))
//        {
//           // holder.chk1.setChecked(true);
//
//            Constants.setTick_slno("");
//           // Constants.getSetCategoryFragmentInterface().update_chk_boxes(cpr.getSlno(), true);
//        }


        holder.swtch.setOnToggledListener(new OnToggledListener() {

            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {

                String st="";
             if(isOn)
             {
                 st="1";
             }
             else
             {
                 st="0";
             }
                Constants.getSetCategoryFragmentInterface().update_status(    cpr.getSlno(),st);


            }


        });


        holder.chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(context," ",Toast.LENGTH_LONG).show();
                Constants.getSetCategoryFragmentInterface().update_chk_boxes(cpr.getSlno(), holder.chk1.isChecked());

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


   CheckBox chk1;
   TextView txt_category,txt_order,txt_display;
   com.github.angads25.toggle.widget.LabeledSwitch swtch;
   LinearLayout ll1;






        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            chk1=itemView.findViewById(R.id.chk1);

            txt_category = itemView.findViewById(R.id.txt_category);
            txt_order=itemView.findViewById(R.id.txt_order);

            txt_display=itemView.findViewById(R.id.txt_display);
            swtch=itemView.findViewById(R.id.swtch);
            ll1=itemView.findViewById(R.id.ll1);

        }
    }




}
