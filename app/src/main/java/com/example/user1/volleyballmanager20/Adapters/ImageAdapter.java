package com.example.user1.volleyballmanager20.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.user1.volleyballmanager20.GalleryActivity;

import java.util.ArrayList;

/**
 * Created by user1 on 12.7.2016 г..
 */
public class ImageAdapter extends BaseAdapter {
    public ImageAdapter(Context mContext, ArrayList<Bitmap> bitmaps) {
        this.mContext = mContext;
        this.bitmaps = bitmaps;
    }

    private ArrayList<Bitmap> bitmaps;

    private Context mContext;
    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imageView.setBackground(new BitmapDrawable(bitmaps.get(position)));
        }
        return imageView;
    }
}
