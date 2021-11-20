package com.project.shopping_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.pojos.show_dash_gridview.DetailsItem;


import java.util.List;


public class AdapterDashboardGridview extends BaseAdapter {


    Context context;
    List<DetailsItem> list;


    public AdapterDashboardGridview(Context context, List<DetailsItem> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
        //   CardView card1;
        ImageView iv1;
        TextView txt1;
        com.airbnb.lottie.LottieAnimationView lott;
        //    ConstraintLayout cl1;


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

        final ViewHolder holder;
        if (view == null) {



      /*      imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


       */

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_dash_gridview, null);


            holder = new ViewHolder();

            //  holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.txt1 = view.findViewById(R.id.txt1);
            holder.lott = view.findViewById(R.id.lott);

            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final DetailsItem cpr = list.get(position);
        holder.txt1.setText("" + cpr.getDisplay_name());


        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr.getImage() + "th" + ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1.0f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);


        // Glide.with(context).load( ApiClient.BASE_URL +"zpa/images/images/"+cpr.getImage()+".jpeg").fitCenter().into( holder.iv1);

        //     Glide.with(context).load("https://neptonglobal.in/zpa/clothes/"+cpr.getSlno()+".jpg").fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into( holder.iv1);


        holder.lott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.getSetItemsFragmentInterface().remove_item(cpr.getDashSlno(), cpr.getImage());


            }
        });

        return view;
    }


}
