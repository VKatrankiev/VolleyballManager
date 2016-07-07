package com.example.user1.volleyballmanager20.cmn;

/**
 * Created by user1 on 7.7.2016 г..
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user1 on 6.7.2016 г..
 */
public class DrawingView extends View {
    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private Paint canvasPaint;
    private ArrayList<Canvas> canvases;
    private ArrayList<Bitmap> canvasBitmaps;
    private int width;
    private int height;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(6);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasBitmaps = new ArrayList<>();

        canvases = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for(Path p: paths){

            canvas.drawBitmap(canvasBitmap, 0,  0, canvasPaint);
            canvas.drawPath(p, drawPaint);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        invalidate();
        return true;

    }

    private void touch_up() {
        drawPath.lineTo(mX, mY);
        drawCanvas.drawPath(drawPath, drawPaint);// commit the path to our offscreen
        paths.add(drawPath);
        drawPath = new Path();

    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            drawPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_start(float x, float y) {
        canvases.add(drawCanvas);
        canvasBitmaps.add(canvasBitmap);
        //!!!
        canvasBitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888 );

        undonePaths.clear();
        drawPath.reset();
        drawPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    public void onClickUndo() {
        if (paths.size() > 0 ) {
            Log.e("Adding path", String.valueOf(paths.size()));
            Log.e("Adding path", String.valueOf(canvases.size()) + " canvas");


            paths.remove(paths.size() - 1);
            drawCanvas = canvases.get(canvases.size()-1);
            canvases.remove(canvases.size()-1);
            invalidate();
            canvasBitmaps.remove(canvasBitmaps.size()-1);
        } else Toast.makeText(getContext(), "nothing more to undo", Toast.LENGTH_SHORT).show();
    }
}
