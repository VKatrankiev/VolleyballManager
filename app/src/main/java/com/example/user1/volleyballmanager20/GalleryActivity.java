package com.example.user1.volleyballmanager20;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.Adapters.ImageAdapter;
import com.example.user1.volleyballmanager20.Database.DBHelper;
import com.example.user1.volleyballmanager20.Database.DBUtils;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    public static ArrayList<Bitmap> bitmapList;
    DBUtils dbUtils;
    SQLiteDatabase db;
    public static Bitmap loadedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dbUtils = DBUtils.getInstance(this);
        db = this.openOrCreateDatabase("image_storage.db", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists tb (a blob)");
        readDB();
        GridView gridview = (GridView) findViewById(R.id.gallery_grid);
        gridview.setAdapter(new ImageAdapter(this, bitmapList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                loadedBitmap = bitmapList.get(position);
                startActivity(new Intent (GalleryActivity.this, SinglePictureActivity.class));

            }
        });

    }

    private void readDB() {
        bitmapList = new ArrayList<>();
        Cursor cursor = dbUtils.readPhotos();
        if (cursor.moveToFirst()) {
            do {
                Bitmap tempBitmap = stringToBitMap(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PICTURE)));
                bitmapList.add(tempBitmap);
            } while (cursor.moveToNext());
        }
    }
    public Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            //Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, 300 , 300 , true);
            return bitmap1;
        } catch (Exception e) {
            //e.getMessage();
            return null;
        }
    }
}
