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

        result = "clear"; // 실험용 디폴트값
        stage = "1";

        ImageView to_home = findViewById(R.id.to_home);
        to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
            Intent intent12 = new Intent(getApplicationContext(), stage1.class);
            intent12.putExtra("Result",result);
            intent12.putExtra("Stage",stage);
            intent12.putExtra("Enter","exit");
            startActivity(intent12);
        });
    }
}