package com.project.shopping_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.ImageBoardActivity;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.delete_pic.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class AdapterPage1 extends BaseAdapter {


    Context context;
    List<String> list;
    String stkid_from_server;


    public AdapterPage1(Context context, List<String> list, String stkid_from_server) {
        this.context = context;
        this.list = list;
        this.stkid_from_server = stkid_from_server;

    }

    private class ViewHolder {
        CardView card1;
        ImageView iv1;
        Button btndel;
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

            view = inflater.inflate(R.layout.model_page1, null);


            holder = new ViewHolder();

            holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.btndel = view.findViewById(R.id.btndel);
            holder.cl1 = view.findViewById(R.id.cl1);

            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final String cpr = list.get(position);
        //   holder.txt1.setText(cpr);

        //   String fnames= cpr.getFname();


        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr +"th"+ ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(0.5f)
                .centerCrop()
                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                Intent in = new Intent(context, ImageBoardActivity.class);
                in.putExtra(Constants.Parcel1,cpr);
                context.startActivity(in);

            }

        });


        //      holder.iv1.setImageResource(R.drawable.ic_gallery);


        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Response> call = apiService.delete_pic(Constants.api_key, stkid_from_server, cpr);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        if (response.body().getResult().equals("1")) {
                            Constants.getFrag1Interface().refresh_adapter(response.body().getSlno());
                          //  Toast.makeText(context, "Bismillah", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, R.string.sme_wrg, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });


            }


        });


        return view;
    }


}
