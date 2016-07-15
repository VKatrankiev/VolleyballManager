package com.example.user1.volleyballmanager20;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user1.volleyballmanager20.Database.DBUtils;

import java.io.ByteArrayOutputStream;

public class SinglePictureActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgPic;
    Button btnErase;
    Button btnSendBack;
    DBUtils dbUtils;
    public static Bitmap loadOnTactics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_picture);
        imgPic = (ImageView) findViewById(R.id.single_picture);
        btnErase = (Button) findViewById(R.id.btn_erase);
        btnSendBack = (Button) findViewById(R.id.btn_load_on_tactics);
        Drawable d = new BitmapDrawable(getResources(), GalleryActivity.loadedBitmap);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPic.setBackground(d);
        }

        btnErase.setOnClickListener(this);
        btnSendBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_erase:{
                deletePic();
                break;
            }
            case R.id.btn_load_on_tactics: {
                loadOnTactics();
                break;
            }
        }

    }

    private void loadOnTactics() {
        imgPic.buildDrawingCache();
        loadOnTactics = imgPic.getDrawingCache();
        TacticsActivity.hasExternalPicture = true;
        startActivity(new Intent(SinglePictureActivity.this, TacticsActivity.class));
    }

    private void deletePic() {
        dbUtils = DBUtils.getInstance(SinglePictureActivity.this);
        imgPic.buildDrawingCache();
        Bitmap bmap = imgPic.getDrawingCache();
        GalleryActivity.bitmapList.remove(bmap);
        GalleryActivity.bitmapStrings.remove(getEncoded64ImageStringFromBitmap(bmap));
        dbUtils.deletePhoto(bmap);
    }
    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }
}

