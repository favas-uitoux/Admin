package com.project.shopping_admin.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.project.shopping_admin.activity.SetPhotoActivity2;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import timber.log.Timber;

public class BaseActivity  extends AppCompatActivity {



    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }



    public void startcrop(@NonNull Uri uri) {

        try {
            String destn = "profile2.jpg";

            UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destn)));
            uCrop.withAspectRatio(1, 1);
            uCrop.withMaxResultSize(750, 750);
            uCrop.withOptions(getoptions());
            uCrop.start(BaseActivity.this);
        } catch (Exception e) {
            Timber.e("error");
        }

    }


    public UCrop.Options getoptions() {
        UCrop.Options opt = new UCrop.Options();
        opt.setCompressionQuality(100);
      //  opt.setMaxBitmapSize(100000);
        //  opt.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        opt.setHideBottomControls(false);
        opt.setFreeStyleCropEnabled(true);

        return opt;
    }


    public void saveReceivedImage(Bitmap bitmap, String imageName) {
        try {


            File path = new File(getApplicationContext().getFilesDir(), File.separator);
            //     File path = new File(getApplicationContext().getFilesDir(), "Recycler" + File.separator + "Images");
            if (!path.exists()) {
                path.mkdirs();
            }
            File outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            // Log.e(TAG, "Saving received message failed with", e);
        } catch (IOException e) {
            //   Log.e(TAG, "Saving received message failed with", e);
        }
    }





    public void showSnack_E(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }



    public void showSnack_S(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }

    public void showSnack_W(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }

}
