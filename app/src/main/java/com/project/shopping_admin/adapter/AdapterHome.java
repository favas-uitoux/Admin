package com.project.shopping_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.SetDashBoardActivity;
import com.project.shopping_admin.activity.SetPhotoActivity2;

import java.util.List;

import static com.project.shopping_admin.Constants.BestBuy;

import static com.project.shopping_admin.Constants.SetDash;
import static com.project.shopping_admin.Constants.SetPhoto;


public class AdapterHome extends BaseAdapter {


    Context context;
    List<String> list;


    public AdapterHome(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
        CardView card1;
        ImageView iv1;
        TextView txt1;
        ConstraintLayout cl1;


    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;
        final ViewHolder holder;
        if (view == null) {



      /*      imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


       */

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_home_style, null);


            holder = new ViewHolder();

            holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.txt1 = view.findViewById(R.id.txt1);
            holder.cl1 = view.findViewById(R.id.cl1);

            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final String cpr = list.get(position);
        holder.txt1.setText(cpr);

        if (cpr.equals(SetPhoto)) {
            holder.iv1.setImageResource(R.drawable.ic_gallery);
        }

        else if (cpr.equals(BestBuy)) {
            holder.iv1.setImageResource(R.drawable.ic_best);
        }
        else if (cpr.equals(SetDash)) {
            holder.iv1.setImageResource(R.drawable.ic_dashboard);
        }


        holder.cl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.txt1.getText().toString().trim().equals(SetPhoto)) {
                    // Toast.makeText(context,"add teacher",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(context, SetPhotoActivity2.class);

                    context.startActivity(in);

                }
               else if (holder.txt1.getText().toString().trim().equals(SetDash)) {
                    // Toast.makeText(context,"add teacher",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(context, SetDashBoardActivity.class);

                    context.startActivity(in);

                }

            }
        });


        return view;
    }




}
