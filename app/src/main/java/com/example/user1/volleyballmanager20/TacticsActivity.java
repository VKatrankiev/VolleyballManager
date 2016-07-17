package com.example.user1.volleyballmanager20;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user1.volleyballmanager20.Database.DBUtils;
import com.example.user1.volleyballmanager20.cmn.DrawingView;

public class TacticsActivity extends Activity implements View.OnClickListener {

    public static boolean hasExternalPicture;
    private DrawingView drawView;
    private Button btnUndo;
    private Button btnTakeShot;
    private Button btnGallery;
    private DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tactics);

        btnTakeShot = (Button) findViewById(R.id.btn_screenshot);
        btnGallery = (Button) findViewById(R.id.btn_see_all_screenshots);
        btnUndo = (Button) findViewById(R.id.btn_undo);
        drawView = (DrawingView) findViewById(R.id.drawing);
        if (hasExternalPicture) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                drawView.setBackground(SinglePictureActivity.loadOnTactics);
            }
        } else {
            drawView.setBackgroundResource(R.drawable.volleyball_court_original);
        }
        btnUndo.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnTakeShot.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_undo: {
                drawView.onClickUndo();
                break;
            }
            case R.id.btn_screenshot: {
                saveScreenshot();
                break;
            }
            case R.id.btn_see_all_screenshots: {
                openScrenShotGallery();
                break;
            }
        }

    }

    private void openScrenShotGallery() {
        startActivity(new Intent(TacticsActivity.this, GalleryActivity.class));
    }


    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        Bitmap bitmap = Bitmap.createBitmap(rootView.getWidth(), rootView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        rootView.draw(canvas);
        return bitmap;
    }

    public void saveScreenshot() {
        Bitmap bitmap = takeScreenshot();
        dbUtils = DBUtils.getInstance(TacticsActivity.this);
        dbUtils.writePhoto(bitmap);
    }
}
