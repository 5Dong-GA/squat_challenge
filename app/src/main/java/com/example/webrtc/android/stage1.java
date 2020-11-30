package com.example.webrtc.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class stage1 extends AppCompatActivity {

    DatabaseReference DB;

    String email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        Intent intent = getIntent();
        // 들어올때 플레이하러 들어온건지 아님 플레이 끝나고 결과창에서 온건지 판단
        email = intent.getStringExtra("Email");
        final String isentering = intent.getStringExtra("Enter");
        final String result = intent.getStringExtra("Result");
        final String stage = intent.getStringExtra("Stage");


        // 결과창에서 온거면 몇 스테이지 깬건지 받아서 상태 변경
//        DB = FirebaseDatabase.getInstance().getReference("users");
//        if (isentering.equals("exit")){
//            int current_stage = Integer.parseInt(stage); // 깬 스테이지 int 변환
//            if (result.equals("clear")){
//                DatabaseReference mystage_DB = DB.child(email).child("stage_num");
//                mystage_DB.addListenerForSingleValueEvent(new ValueEventListener()   {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        long ranked_stage; // DB에 있는 최고 스테이지
//                        ranked_stage = (long) dataSnapshot.getValue();
//                        if(ranked_stage<current_stage){
//                            DB.setValue(stage); // DB에 있는거 보다 높으면 최신화
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        }

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
        stage2.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), Result.class);
            intent13.putExtra("Where" , "stage_fst_pg");
            intent13.putExtra("Email",email);
            startActivity(intent13);
        });

        TextView stage3 = findViewById(R.id.stage3);
        stage3.setOnClickListener(view -> {
            Intent intent13 = new Intent(getApplicationContext(), ConnectActivity.class);
            startActivity(intent13);
        });


        ImageView home1 = findViewById(R.id.home1);
        home1.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), Main.class);
            startActivity(intent14);
        });
    }

    public void stage_cleared(String result, String stage){

    }
}