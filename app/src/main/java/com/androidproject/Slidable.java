package com.androidproject;


import android.view.MotionEvent;

public interface Slidable {
    public boolean isTouched(MotionEvent motionEvent);
    public boolean wasTouched();
    public void followTouch(MotionEvent motionEvent);
    public void slideUp();
    public void slideRight();
    public void slideLeft();
    public void slideDown();
    public void slideBack();
}
