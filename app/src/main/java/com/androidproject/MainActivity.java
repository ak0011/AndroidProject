package com.androidproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;
    public static PuzzleView puzzleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap srcB = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        puzzleView = new PuzzleView(this,srcB,srcB.getWidth()*2,srcB.getHeight()*2,3,3,0,0);
        mainLayout.addView(puzzleView);
    }
}
