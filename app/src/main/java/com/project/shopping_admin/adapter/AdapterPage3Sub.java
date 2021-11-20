package com.project.shopping_admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.ImageBoardActivity;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.delete_pic.Response;
import com.project.shopping_admin.model.model_img_and_stkid;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterPage3Sub extends RecyclerView.Adapter<AdapterPage3Sub.ViewHolderClass>    {




    Context context;
   // List<String> list;
    List<model_img_and_stkid> list2;






    public AdapterPage3Sub(Context context, List<model_img_and_stkid> list2) {
        this.context = context;

        this.list2=list2;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_page2_sub2, parent, false);

        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final model_img_and_stkid cpr = list2.get(position);



        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr.getImage() +"th"+ ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(0.5f)
                .centerCrop()
                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.ivsub);


        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Response> call = apiService.delete_pic(Constants.api_key, cpr.getStkid(), cpr.getImage());
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        if (response.body().getResult().equals("1")) {
                        //    Constants.getAdapterPage2Interface().refresh_adapter(cpr.getImage(),cpr.getStkid());


                            removeAt(holder.getLayoutPosition(),cpr.getStkid(),cpr.getImage());
                        } else {
                            Toast.makeText(context, R.string.sme_wrg, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(context, R.string.sme_wrg, Toast.LENGTH_LONG).show();
                    }
                });





            }
        });





        holder.ivsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                Intent in = new Intent(context, ImageBoardActivity.class);
                in.putExtra(Constants.Parcel1,cpr.getImage());
                context.startActivity(in);

            }

        });












    }

    @Override
    public int getItemCount() {
        return list2.size();
    }



    public class ViewHolderClass extends RecyclerView.ViewHolder {


        de.hdodenhof.circleimageview.CircleImageView ivsub;
        Button btndel;



        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);




            ivsub=itemView.findViewById(R.id.ivsub);
            btndel=itemView.findViewById(R.id.btndel);





        }
    }



//    public void delete(int position) {
//        list2.remove(position);
//
//
//    }

    
    public void removeAt(int position,String stkid,String img) {
        list2.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list2.size());

        Constants.getFrag3Interface().delete_and_refresh(stkid,img);
    }






}
