package com.project.shopping_admin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.project.shopping_admin.BuildConfig;
import com.project.shopping_admin.Constants;
import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.base.BaseActivity;
import com.project.shopping_admin.database.appdb.Appdb;
import com.project.shopping_admin.database.entities.ImageEntity;
import com.project.shopping_admin.fragment.Page1Fragment;
import com.project.shopping_admin.fragment.Page2Fragment;
import com.project.shopping_admin.fragment.Page3Fragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import timber.log.Timber;

import static com.project.shopping_admin.Constants.TAKE_PIC;

public class SetPhotoActivity2 extends BaseActivity {


    private Appdb db;
    //  private final String LOG_TAG = TestActivity.class.getSimpleName();

    // Titles of the individual pages (displayed in tabs)
    private final String[] PAGE_TITLES = new String[]{
            "Stock id",
            "Itemname",
            "Category"
    };

    // The fragments that are used as the individual pages
    private final Fragment[] PAGES = new Fragment[]{
            new Page1Fragment(),
            new Page2Fragment(),
            new Page3Fragment()
    };

    // The ViewPager is responsible for sliding pages (fragments) in and out upon user input
    private androidx.viewpager.widget.ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the Toolbar as the activity's app bar (instead of the default ActionBar)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Connect the ViewPager to our custom PagerAdapter. The PagerAdapter supplies the pages
        // (fragments) to the ViewPager, which the ViewPager needs to display.
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // Connect the tabs with the ViewPager (the setupWithViewPager method does this for us in
        // both directions, i.e. when a new tab is selected, the ViewPager switches to this page,
        // and when the ViewPager switches to a new page, the corresponding tab is selected)
        com.google.android.material.tabs.TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }


    /* PagerAdapter for supplying the ViewPager with the pages (fragments) to display. */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {

            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
                //  Toast.makeText(getApplicationContext(),"Bismillah1",Toast.LENGTH_SHORT).show();

                Uri res = CropImage.getPickImageResultUri(this, data);

                startcrop(res);

            } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                Uri res = UCrop.getOutput(data);

                if (Constants.getFrom().equals("3")) {
                    Constants.getFrag3Interface().setImageView(res);
                }
                else if (Constants.getFrom().equals("2")) {
                    Constants.getPage2FragInterface().setImageView(res);
                } else {
                    Constants.getFrag1Interface().setImageView(res);
                }


                try {
                    db = Appdb.getDb_instance(getApplication());


                    List<ImageEntity> list_files = db.getImageEntityDao().get_saved_file_details();


                    for (ImageEntity row : list_files) {

                        long id = row.getId();

                        String fname = row.getSaved_fname();


                        try {
                            File dir = getFilesDir();
                            File file = new File(dir, fname + ".jpeg");
                            boolean deleted = file.delete();

                            db.getImageEntityDao().del(fname);


                        } catch (Exception e) {

                        }


                    }


                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), res);

                    saveReceivedImage(bitmap, Constants.getRandom_no());


                    //save detailsto image table

                    db.getImageEntityDao().insert_image_details(new ImageEntity(0, Constants.getRandom_no()));
                    showSnack_S("" + db.getImageEntityDao().get_count());


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else if(requestCode==TAKE_PIC   && resultCode == RESULT_OK )
            {
                try {
                    File photofile=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"100.jpg");

                    Uri furi=       FileProvider.getUriForFile(Objects.requireNonNull(this),
                            BuildConfig.APPLICATION_ID + ".provider", photofile);

                    if(furi !=null)
                    {
                       // Uri res = CropImage.getPickImageResultUri(this, data);

                        startcrop(furi);
                    }

                    Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"not ",Toast.LENGTH_LONG).show();
                }



            }
        } catch (Exception e) {

        }


    }



}
