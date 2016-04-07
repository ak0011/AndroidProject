package com.androidproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    public static Activity activity;
    public static Button shufflerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap srcB = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        final PuzzleView puzzleView = new PuzzleView(this,srcB,srcB.getWidth()*2,srcB.getHeight()*2,3,3,60,100);
        mainLayout.addView(puzzleView);

        shufflerButton=(Button)findViewById(R.id.shuffleButton);
        shufflerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puzzleView.shuffle();
            }
        });
        activity=this;
    }
}
