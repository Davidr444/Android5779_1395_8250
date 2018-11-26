package com.jct.davidandyair.android5779_1395_8250.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.jct.davidandyair.android5779_1395_8250.R;

public class OpeningActivity extends Activity {
    AnimationDrawable openingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        ImageView imageView = (ImageView)findViewById(R.id.animImage);
        imageView.setBackgroundResource(R.drawable.opening_animation);
        openingAnimation = (AnimationDrawable) imageView.getBackground();
        openingAnimation.start();

        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(4500);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }


}
