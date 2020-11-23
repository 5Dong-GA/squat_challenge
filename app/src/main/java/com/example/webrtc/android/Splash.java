package com.example.webrtc.android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import javax.annotation.Nullable;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 2250; // 화면 넘어가는 값

    ImageView splash_img1,splash_img2,splash_img3,splash_img4,splash_img5;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_img1 = findViewById(R.id.splash_img1);
        splash_img2 = findViewById(R.id.splash_img2);
        splash_img3 = findViewById(R.id.splash_img3);
        splash_img4 = findViewById(R.id.splash_img4);
        splash_img5 = findViewById(R.id.splash_img5);

        splashAnimation();

        new Handler().postDelayed(() -> { // splashscreen 값만큼 후에 엑티비티 전환
            Intent intent = new Intent(Splash.this, LoginActivity.class);
            startActivity(intent);
        },SPLASH_SCREEN);
    }

    public void splashAnimation(){

        Animation imgAnim = AnimationUtils.loadAnimation(this, R.anim.popup);
        Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.popup1);
        Animation imgAnim2 = AnimationUtils.loadAnimation(this, R.anim.popup2);
        Animation imgAnim2_1 = AnimationUtils.loadAnimation(this, R.anim.popup2_1);
        Animation imgAnim3 = AnimationUtils.loadAnimation(this, R.anim.popup3);

        splash_img1.setAnimation(imgAnim);
        splash_img2.setAnimation(imgAnim1);
        splash_img3.setAnimation(imgAnim2);
        splash_img5.setAnimation(imgAnim2_1);
        splash_img4.setAnimation(imgAnim3);
    }
}