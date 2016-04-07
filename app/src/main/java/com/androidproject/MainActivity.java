package com.androidproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap srcB = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        PuzzleView puzzleView = new PuzzleView(this,srcB,srcB.getWidth()*2,srcB.getHeight()*2,3,3,60,100);
        mainLayout.addView(puzzleView);
    }
}
