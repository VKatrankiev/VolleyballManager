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
    public static Drawable loadOnTactics;

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
        switch (view.getId()) {
            case R.id.btn_erase: {
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
        loadOnTactics = new BitmapDrawable(getResources(), GalleryActivity.loadedBitmap);
        TacticsActivity.hasExternalPicture = true;
        startActivity(new Intent(SinglePictureActivity.this, TacticsActivity.class));
    }

    private void deletePic() {
        dbUtils = DBUtils.getInstance(SinglePictureActivity.this);
        GalleryActivity.bitmapList.remove(GalleryActivity.loadedBitmap);
        dbUtils.deletePhoto(GalleryActivity.loadedBitmap);
        startActivity(new Intent(SinglePictureActivity.this, GalleryActivity.class));
    }
}

