package com.example.webrtc.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class stage3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage3);

        ImageView downto_stage2 = findViewById(R.id.downto_stage2); // 화살표로 페이지 이동
        downto_stage2.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), stage2.class);
            intent13.putExtra("Enter" , "enter");
            startActivity(intent13);
        });

        ImageView home3 = findViewById(R.id.home3);
        home3.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), Main.class);
            startActivity(intent14);
        });
    }
}