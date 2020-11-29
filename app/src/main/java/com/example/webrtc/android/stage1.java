package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class stage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        Intent intent = getIntent();
        // 들어올때 플레이하러 들어온건지 아님 플레이 끝나고 결과창에서 온건지 판단
        final String isentering = intent.getStringExtra("Enter");
        final String result = intent.getStringExtra("Result");
        final String stage = intent.getStringExtra("Stage");

        // 결과창에서 온거면 몇 스테이지 깬건지 받아서 상태 변경
        if (isentering.equals("exit")){
            stage_clear(result, stage);
        }

        ImageView upto_stage2 = findViewById(R.id.upto_stage2); // 화살표로 페이지 이동
        upto_stage2.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), stage2.class);
            intent12.putExtra("Enter" , "enter");
            startActivity(intent12);
        });

        TextView stage1 = findViewById(R.id.stage1);
        stage1.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), ConnectActivity.class);
            startActivity(intent13);
        });

        TextView stage2 = findViewById(R.id.stage2);
        stage1.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), ConnectActivity.class);
            startActivity(intent13);
        });

        TextView stage3 = findViewById(R.id.stage3);
        stage1.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), ConnectActivity.class);
            startActivity(intent13);
        });


        ImageView home1 = findViewById(R.id.home1);
        home1.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), Main.class);
            startActivity(intent14);
        });
    }

    public void stage_clear(String result, String stage){ // 깼으면 해당 스테이지 체크로 변환
        if (result.equals("clear")){
            if (stage.equals("1")) {
                TextView textview = findViewById(R.id.stage1);
                textview.setBackgroundResource(R.drawable.check1);
                textview.setText("");
            }
        }
    }
}