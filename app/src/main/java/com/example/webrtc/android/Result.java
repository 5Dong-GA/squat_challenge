package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Result extends AppCompatActivity {

    String result="";
    String stage ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = "clear";
        stage = "1";

        ImageView to_home = findViewById(R.id.to_home);
        to_home.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), stage1.class);
            intent12.putExtra("Result",result);
            intent12.putExtra("Stage",stage);
            intent12.putExtra("Enter","exit");
            startActivity(intent12);
        });
    }
}