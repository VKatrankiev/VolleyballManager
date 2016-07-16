package com.example.user1.volleyballmanager20.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 package com.example.user1.volleyballmanager20.Database;

 import android.content.ContentValues;
 import android.content.Context;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;
 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.util.Base64;

 import java.io.ByteArrayOutputStream;

 /**
 * Created by user1 on 10.7.2016 г..
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_PHOTOS = "Photos";
    public static final String KEY_ID = "id";
    public static final String KEY_PICTURE = "picture";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, TABLE_PHOTOS, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PHOTOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  KEY_PICTURE + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
        onCreate(db);
    }

    public boolean insertPhoto(Bitmap bitmap){
        SQLiteDatabase db = getWritableDatabase();
        String bitmapStr = bitMapToString(bitmap);
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PICTURE, bitmapStr);
        db.insert(TABLE_PHOTOS, null, contentValues);
        return true;
    }
    public Cursor getPhoto() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PHOTOS, new String[]{KEY_PICTURE}, null, null, null, null, null);
    }


    public void deletePhoto(Bitmap bitmap) {
        SQLiteDatabase db = this.getWritableDatabase();
        String str = bitMapToString(bitmap);
        db.delete(TABLE_PHOTOS, KEY_PICTURE + " = ?", new String[]{str});
    }

    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}
