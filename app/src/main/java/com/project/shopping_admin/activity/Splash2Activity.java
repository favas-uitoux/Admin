package com.project.shopping_admin.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;


import com.project.shopping_admin.R;
import com.project.shopping_admin.activity.base.BaseActivity;

import in.codeshuffle.typewriterview.TypeWriterListener;

public class Splash2Activity extends BaseActivity implements TypeWriterListener {

    ObjectAnimator animator1;
    private int display_cnt = 0;
    private int cnt = 0;
    //  private de.hdodenhof.circleimageview.CircleImageView civ;
    Handler handler;
    private ConstraintLayout clmain;
    private ImageView civ;
  //  private com.jackandphantom.circularimageview.RoundedImage bigimage;
    private de.hdodenhof.circleimageview.CircleImageView flag;
    //private TextView txt1;

    private in.codeshuffle.typewriterview.TypeWriterView txt1;


   // private Appdb db;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);


     //   db = Room.databaseBuilder(getApplicationContext(), Appdb.class, "PartDB").allowMainThreadQueries().build();


        flag = findViewById(R.id.flag);
   //     bigimage = findViewById(R.id.bigimage);
        clmain = findViewById(R.id.clmain);
        txt1 = findViewById(R.id.txt1);

        txt1.setTypeWriterListener(this);
   //     Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeinfast);
    //    bigimage.startAnimation(fadeInAnimation);


  /*      clmain.getViewTreeObserver().addOnGlobalLayoutListener(new
                                                                       ViewTreeObserver.OnGlobalLayoutListener() {
                                                                           @Override
                                                                           public void onGlobalLayout() {
                                                                               display_cnt = display_cnt + 1;


                                                                               if (display_cnt > 0) {

                                                                                   Rect r = new Rect();
                                                                                   clmain.getWindowVisibleDisplayFrame(r);
                                                                                   int screenHeight = clmain.getRootView().getHeight();
                                                                                   int keypadHeight = screenHeight - r.bottom;
                                                                                   if (keypadHeight > screenHeight * 0.15) {

                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(bigimage, "translationY", -500f);
                                                                                       animator.setDuration(2000);
                                                                                       animator.start();

                                                                                       //   Toast.makeText(LoginActivity.this, "Keyboard is showing", Toast.LENGTH_LONG).show();
                                                                                   } else {


                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(bigimage, "translationY", -1150f);
                                                                                       animator.setDuration(4000);
                                                                                       animator.start();

                                                                                       animator1 = ObjectAnimator.ofFloat(flag, "translationY", 1150f);
                                                                                       animator1.setDuration(4000);
                                                                                       animator1.start();


                                                                                       //Toast.makeText(LoginActivity.this, "keyboard closed"+ display_cnt, Toast.LENGTH_LONG).show();
                                                                                   }

                                                                               }
                                                                           }

                                                                       });

        */


        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                txt1.setDelay(1000);
                txt1.setWithMusic(false);
                txt1.animateText("  Welcome  ");

                //   animator1.cancel();
                //          ObjectAnimator animator22 = ObjectAnimator.ofFloat(flag, "translationY",725f);
                //          animator22.setDuration(2000);
                //         animator22.start();


              //  final Animation anim3 = AnimationUtils.loadAnimation(Splash2Activity.this, R.anim.item_animation);
           //     flag.startAnimation(anim3);


//                anim3.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//
//
//                        txt1.setDelay(4000);
//                        txt1.setWithMusic(false);
//                      txt1.animateText("  Welcomes  vcvcvcvcv vfc");
//
//                        //
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });


                //

            }

        }, 2000);


        /*
         */


    }


    @Override
    public void onTypingStart(String text) {

    }

    @Override
    public void onTypingEnd(String text) {


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            showSnack_W("app does not have permission now");


            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    Splash2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(
                    Splash2Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {


                Request_Perm();
            } else {

                Request_Perm();

            }


        } else {

            goto_next_screen();

//            }

        }


    }

    @Override
    public void onCharacterTyped(String text, int position) {

    }

    @Override
    public void onTypingRemoved(String text) {

    }


    public void Request_Perm() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Splash2Activity.this);
        builder.setCancelable(false);
        builder.setMessage(" Read and Write External" +
                " Storage permissions are required to do the task.");
        builder.setTitle("Please grant these permissions");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(
                        Splash2Activity.this,
                        new String[]{


                                Manifest.permission.WRITE_EXTERNAL_STORAGE,

                                Manifest.permission.READ_EXTERNAL_STORAGE

                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        });


        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // lmain.setVisibility(View.GONE);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permissions are granted

                    showSnack_S(getString(R.string.permission_granted));
                    goto_next_screen();
                } else {

                    //lmain.setVisibility(View.GONE);
                    // Permissions are denied
                    showSnack_E(getString(R.string.permission_denied));
                }
                return;
            }
        }
    }


    private void goto_next_screen() {

          //  Intent in = new Intent(getApplicationContext(), LoginActivity.class);
            Intent in = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(in);
            finish();


    }

}


