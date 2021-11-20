package com.project.shopping_admin.activity;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.shopping_admin.R;
import com.project.shopping_admin.adapter.AdapterHome;

import java.util.ArrayList;
import java.util.List;

import static com.project.shopping_admin.Constants.BestBuy;

import static com.project.shopping_admin.Constants.SetDash;
import static com.project.shopping_admin.Constants.SetPhoto;

public class HomeActivity extends AppCompatActivity {


    private GridView gridview;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        list = new ArrayList<String>();
        list.add(SetPhoto);
        list.add(BestBuy);
        list.add(SetDash);


        gridview.setAdapter(new AdapterHome(this, list));


    }




    private void init()
    {

        gridview=findViewById(R.id.gridview);
        gridview.setVerticalScrollBarEnabled(false);


    }


}