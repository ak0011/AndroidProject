package com.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;


public class MainView extends View {
    private Bitmap image;
    private float imageX;
    private float imageY;

    public MainView(Context context) {
        super(context);
        imageX = 0;
        imageY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        image = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        canvas.drawBitmap(image, imageX, imageY, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
                imageX = 0;
                imageY = 0;
                break;
            default:
                if(isImageTouched(motionEvent)){
                    imageX = motionEvent.getX() - image.getWidth() / 2;
                    imageY = motionEvent.getY() - image.getHeight() / 2;
                }
                break;
        }
        invalidate();
        return true;
    }

    private boolean isImageTouched(MotionEvent motionEvent){
        if(
                imageX<=motionEvent.getX() && motionEvent.getX()<=imageX+image.getWidth() &&
                imageY<=motionEvent.getY() && motionEvent.getY()<=imageY+image.getHeight()
                )
            return true;
        return false;
    }
}
