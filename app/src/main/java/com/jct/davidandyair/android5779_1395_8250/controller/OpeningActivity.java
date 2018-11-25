package com.jct.davidandyair.android5779_1395_8250.controller;

import android.app.Activity;
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
    }


}
