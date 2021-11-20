package com.project.shopping_admin.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.pojos.show_banner.DetailsItem;

import java.util.List;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.ViewHolderClass> {


    List<com.project.shopping_admin.apiservice.pojos.show_banner.DetailsItem> list;

    Context context;


    public AdapterBanner(Context context, List<com.project.shopping_admin.apiservice.pojos.show_banner.DetailsItem> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_banner, parent, false);


        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final DetailsItem cpr = list.get(position);


        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr.getImage() + ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(0.5f)
                .centerCrop()
                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);



        holder.lott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.remove_item))
                        .setMessage(context.getString(R.string.are_you_sure_remove))

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation

                                Constants.getSetBannerInterface().remove_item(cpr.getDash_slno(), cpr.getImage());
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();




            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        ImageView iv1;
        com.airbnb.lottie.LottieAnimationView lott;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            iv1 = itemView.findViewById(R.id.iv1);
            lott = itemView.findViewById(R.id.lott);


        }
    }


}
