package com.project.shopping_admin.adapter;

import android.content.Context;
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
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.apiservice.Endpoint;
import com.project.shopping_admin.apiservice.pojos.delete_pic.Response;
import com.project.shopping_admin.model.model_img_and_stkid;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterAdditemSub extends RecyclerView.Adapter<AdapterAdditemSub.ViewHolderClass>    {




    Context context;
   // List<String> list;
    List<model_img_and_stkid> list2;






    public AdapterAdditemSub(Context context, List<model_img_and_stkid> list2) {
        this.context = context;

        this.list2=list2;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_additem_sub, parent, false);

        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final model_img_and_stkid cpr = list2.get(position);



        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr.getImage()+"th" + ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1f)
                .centerCrop()
                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.ivsub);


        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,""+cpr.getStkid()+"  "+cpr.getImage(),Toast.LENGTH_LONG).show();

                Constants.setFrom("");
                    Constants.getAdditemActivitytInterface().set_item(cpr.getStkid(),cpr.getImage());






            }
        });



//
















    }

    @Override
    public int getItemCount() {
        return list2.size();
    }



    public class ViewHolderClass extends RecyclerView.ViewHolder {


        de.hdodenhof.circleimageview.CircleImageView ivsub;
        Button btnadd;



        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);




            ivsub=itemView.findViewById(R.id.ivsub);
            btnadd=itemView.findViewById(R.id.btnadd);





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

        Constants.getPage2FragInterface().delete_and_refresh(stkid,img);
    }






}
