package com.project.shopping_admin.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.apiservice.ApiClient;
import com.project.shopping_admin.custom.ProportionalImageView;


public class ImageBoardActivity extends AppCompatActivity {

    private ProportionalImageView ivfull;
    private String Parcel1="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_board);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            Parcel1= null;
        } else {
            Parcel1= extras.getString(Constants.Parcel1);
        }






        ivfull=    findViewById(R.id.ivfull);


        String profilepic1= ApiClient.BASE_URL+"zpa/images/images/"+Parcel1+".jpeg";
                Glide.with(ImageBoardActivity.this).load(   profilepic1)
                        .sizeMultiplier(1.0f)

                        .placeholder(R.drawable.blanc_pic)
                        .error(R.drawable.blanc_pic)
                        .fallback(R.drawable.blanc_pic)
                        .dontAnimate()
                        .into(ivfull);


    }
}
