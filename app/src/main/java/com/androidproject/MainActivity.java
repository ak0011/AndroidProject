package com.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout=(RelativeLayout)findViewById(R.id.mainLayout);
        mainView=new MainView(this);
        mainLayout.addView(mainView);
    }
}
