package com.example.webrtc.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    String result="";
    String time = "";
    String count = "";
    String stage ="";
    String where="";
    String email="";
    String name="";
    String photoUrl="";
    TextView tv_time;
    TextView tv_count;
    ImageView iv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        where = intent.getStringExtra("Where");
        time = intent.getStringExtra("Time");
        count = intent.getStringExtra("Count");
        result=intent.getStringExtra("Result");
        email = intent.getStringExtra("Email");
        name = intent.getStringExtra("name");
        photoUrl = intent.getStringExtra("photoUrl");

        tv_time = findViewById(R.id.tv_time);
        tv_count = findViewById(R.id.tv_count);
        iv_result = findViewById(R.id.iv_result);
        ImageView to_home = findViewById(R.id.to_home);
        if (where.equals("solo")){
            if(result.equals("false")){
                iv_result.setBackgroundResource(R.drawable.fail);
            }
            else{
                iv_result.setBackgroundResource(R.drawable.clear);
            }
            //time parsing
            long overTime = Long.parseLong(time);
            long m = overTime / 1000 / 60;
            long s = (overTime / 1000) % 60;
            long ms = overTime % 1000;
            String recTime = String.format("%d:%02d:%03d", m, s, ms);
            tv_count.setText(count);
            tv_time.setText(recTime);

            to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                Intent intent12 = new Intent(getApplicationContext(), Main.class);
                intent12.putExtra("Email" , email);
                intent12.putExtra("name" , name);
                intent12.putExtra("photoUrl" , photoUrl);


                finish();
                startActivity(intent12);
            });
        }
        if (where.equals("stage_sec_pg")){
            to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
                Intent intent12 = new Intent(getApplicationContext(), stage2.class);
                intent12.putExtra("Result",result);
                intent12.putExtra("Stage",stage);
                intent12.putExtra("Email" , email);
                intent12.putExtra("Enter","exit");

                startActivity(intent12);
            });
        }
//        switch (where){
//            case "stage_fst_pg":{
//                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
//                    Intent intent12 = new Intent(getApplicationContext(), stage1.class);
//                    intent12.putExtra("Result",result);
//                    intent12.putExtra("Stage",stage);
//                    intent12.putExtra("Enter","exit");
//                    startActivity(intent12);
//                });
//            }
//            case "stage_sec_pg":{
//                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
//                    Intent intent12 = new Intent(getApplicationContext(), stage2.class);
//                    intent12.putExtra("Result",result);
//                    intent12.putExtra("Stage",stage);
//                    intent12.putExtra("Enter","exit");
//                    startActivity(intent12);
//                });
//            }
//            case "stage_trd_pg":{
//                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
//                    Intent intent12 = new Intent(getApplicationContext(), stage3.class);
//                    intent12.putExtra("Result",result);
//                    intent12.putExtra("Stage",stage);
//                    intent12.putExtra("Enter","exit");
//                    startActivity(intent12);
//                });
//            }
//            case "solo":{
//                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
//                    Intent intent12 = new Intent(getApplicationContext(), Play_selection.class);
//                    intent12.putExtra("Result",result);
//                    intent12.putExtra("Enter","exit");
//                    startActivity(intent12);
//                });
//            }
//            case "connect":{
//                to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
//                    Intent intent12 = new Intent(getApplicationContext(), Play_selection.class);
//                    intent12.putExtra("Result",result);
//                    intent12.putExtra("Enter","exit");
//                    startActivity(intent12);
//                });
//            }
//        }
    }
}