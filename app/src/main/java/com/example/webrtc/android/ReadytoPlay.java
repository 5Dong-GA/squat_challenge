package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class ReadytoPlay extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 5010; // 5.01초 후에 화면 전환

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readytoplay);

        TextView count1, count2, count3, count4, count5;

        count1 = findViewById(R.id.count1);
        count2 = findViewById(R.id.count2);
        count3 = findViewById(R.id.count3);
        count4 = findViewById(R.id.count4);
        count5 = findViewById(R.id.count5);

        count1.animate().alpha(0).setDuration(10).setStartDelay(1000);
        count2.animate().alpha(1).setDuration(10).setStartDelay(1000);
        count2.animate().alpha(0).setDuration(10).setStartDelay(2000);
        count3.animate().alpha(1).setDuration(10).setStartDelay(2000);
        count3.animate().alpha(0).setDuration(10).setStartDelay(3000);
        count4.animate().alpha(1).setDuration(10).setStartDelay(3000);
        count4.animate().alpha(0).setDuration(10).setStartDelay(4000);
        count5.animate().alpha(1).setDuration(10).setStartDelay(4000);
        count5.animate().alpha(0).setDuration(10).setStartDelay(5000);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ReadytoPlay.this, solo_speed_play.class);
            startActivity(intent);
        }, SPLASH_SCREEN);
    }
}