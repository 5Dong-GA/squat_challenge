package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Result extends AppCompatActivity {

    String result="";
    String stage ="";
    String where="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        where = intent.getStringExtra("Where");
        result = intent.getStringExtra("Result");
        stage = intent.getStringExtra("Stage");

        ImageView to_home = findViewById(R.id.to_home);

        switch (where){
            case "stage1":{
                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                    Intent intent12 = new Intent(getApplicationContext(), stage1.class);
                    intent12.putExtra("Result",result);
                    intent12.putExtra("Stage",stage);
                    intent12.putExtra("Enter","exit");
                    startActivity(intent12);
                });
            }
            case "stage2":{
                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                    Intent intent12 = new Intent(getApplicationContext(), stage2.class);
                    intent12.putExtra("Result",result);
                    intent12.putExtra("Stage",stage);
                    intent12.putExtra("Enter","exit");
                    startActivity(intent12);
                });
            }
            case "stage3":{
                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                    Intent intent12 = new Intent(getApplicationContext(), stage3.class);
                    intent12.putExtra("Result",result);
                    intent12.putExtra("Stage",stage);
                    intent12.putExtra("Enter","exit");
                    startActivity(intent12);
                });
            }
            case "solo":{
                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                    Intent intent12 = new Intent(getApplicationContext(), solo_speed_play.class);
                    intent12.putExtra("Result",result);
                    intent12.putExtra("Enter","exit");
                    startActivity(intent12);
                });
            }
            case "connect":{
                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                    Intent intent12 = new Intent(getApplicationContext(), ConnectActivity.class);
                    intent12.putExtra("Result",result);
                    intent12.putExtra("Enter","exit");
                    startActivity(intent12);
                });
            }
        }
    }
}