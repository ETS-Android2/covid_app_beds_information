package com.example.covid_beds_information_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_splash extends AppCompatActivity {
    TextView tv_splash,tv_splash2;
    ImageView im_splash;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getSupportActionBar().hide();
        tv_splash = findViewById(R.id.tv_splash);
        tv_splash2 = findViewById(R.id.tv_splash2);
        im_splash = findViewById(R.id.im_splash);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.a1);
        im_splash.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent a = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(a);
                finish();
            }
        }).start();
    }
}