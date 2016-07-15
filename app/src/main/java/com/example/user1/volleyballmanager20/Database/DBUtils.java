package com.example.user1.volleyballmanager20.Database;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;

/**
 * Created by user1 on 11.7.2016 г..
 */
public class DBUtils {
    private static DBUtils instance;
    private DBHelper db;

    private DBUtils (Context context){
        initDB(context);
    }

    private DBHelper initDB(Context context) {
        if(db == null){
            db = new DBHelper(context);
        }
        return db;
    }

    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void deletePhoto(Bitmap bitmap){
        db.deletePhoto(bitmap);
    }

    public void writePhoto(Bitmap bitmap){
        db.insertPhoto(bitmap);
    }
    public Cursor readPhotos(){
        return db.getPhoto();
    }
}
