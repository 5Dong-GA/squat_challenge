package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Play_selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_selection);

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");

        TextView to_speed = findViewById(R.id.to_speed);
        to_speed.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), ReadytoPlay.class);
            intent13.putExtra("Email" , email);       //우선 id만 넘겨준다 가정
            startActivity(intent13);
        });

        TextView to_stage = findViewById(R.id.to_stage);
        to_stage.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), stage1.class);
            intent14.putExtra("Email" , email);       //우선 id만 넘겨준다 가정
            startActivity(intent14);
        });

        TextView to_team = findViewById(R.id.to_team);
        to_team.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), ConnectActivity.class);
            intent14.putExtra("Email" , email);       //우선 id만 넘겨준다 가정
            startActivity(intent14);
        });
    }
}