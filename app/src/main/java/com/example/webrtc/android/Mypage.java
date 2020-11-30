package com.example.webrtc.android;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//임의로 설정버튼을 눌렀을때 db가 정렬되도록 한다

public class Mypage extends AppCompatActivity {
    private Button ach1,ach2,ach3;
    private Button rec1,rec2,rec3,rec4;
    private TextView tv_myach;
    private LinearLayout achiv,rec,mypage_linear;
    private String email;
    DatabaseReference DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String nickName = intent.getStringExtra("name");
        final String photoUrl = intent.getStringExtra("photoUrl");
        email = intent.getStringExtra("Email");

        TextView tv_name = findViewById(R.id.tv_name);
        tv_name.setText(nickName);
        tv_myach = findViewById(R.id.tv_myach);

        achiv = findViewById(R.id.achiv);
        rec = findViewById(R.id.rec);
        mypage_linear = findViewById(R.id.mypage_linear);
        ImageView btn_ach = findViewById(R.id.btn_ach);
        ImageView btn_rec = findViewById(R.id.btn_record);
        ImageView achiv_back = findViewById(R.id.achiv_back);
        ImageView rec_back = findViewById(R.id.rec_back);

        btn_ach.setOnClickListener(view -> { // 업적이나 기록 누르면 화면 전환
            mypage_linear.setVisibility(View.INVISIBLE);
            rec.setVisibility(View.INVISIBLE);
            achiv.setVisibility(View.VISIBLE);
        });

        btn_rec.setOnClickListener(view -> { // back 누르면 원상복귀
            mypage_linear.setVisibility(View.INVISIBLE);
            rec.setVisibility(View.VISIBLE);
            achiv.setVisibility(View.INVISIBLE);
        });

        rec_back.setOnClickListener(view -> {
            mypage_linear.setVisibility(View.VISIBLE);
            rec.setVisibility(View.INVISIBLE);
            achiv.setVisibility(View.INVISIBLE);
        });

        achiv_back.setOnClickListener(view -> {
            mypage_linear.setVisibility(View.VISIBLE);
            rec.setVisibility(View.INVISIBLE);
            achiv.setVisibility(View.INVISIBLE);
        });

        ImageView iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);

        ach1 = findViewById(R.id.ach1);
        ach2 = findViewById(R.id.ach2);
        ach3 = findViewById(R.id.ach3);
        ach1.setOnClickListener(view -> select_myach(ach1));
        ach2.setOnClickListener(view -> select_myach(ach2));
        ach3.setOnClickListener(view -> select_myach(ach3));

        rec1 = findViewById(R.id.rec1);
        rec2 = findViewById(R.id.rec2);
        rec3 = findViewById(R.id.rec3);
        rec4 = findViewById(R.id.rec4);

        rec1.setOnClickListener(view -> { // 각 기록 누르면 db가서 값 가져옴
            DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/total_count");
            DB.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long c = (long) snapshot.getValue();
                    String cnt = c + "개";
                    rec1.setText("-"+cnt);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
        rec2.setOnClickListener(view -> {
            DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/speed_time");
            DB.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long t = (long) snapshot.getValue();
                    long m = t / 1000 / 60;
                    long s = (t / 1000) % 60;
                    long ms = t % 1000;

                    String recTime = String.format("%d:%02d:%03d", m, s, ms);
                    String time = recTime;
                    rec2.setText(time);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
        rec3.setOnClickListener(view -> {
            String stage;
            stage = "스테이지\n3";
            rec3.setText(stage);
        });
        rec4.setOnClickListener(view -> {
            String playnum;
            playnum = "11회";
            rec4.setText(playnum);
        });

        ImageView backto_main = findViewById(R.id.backto_main);
        backto_main.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), Main.class);
            intent1.putExtra("name", nickName);
            intent1.putExtra("photoUrl", photoUrl);
            intent1.putExtra("Email", email);
            startActivity(intent1);
        });
    }

    public void select_myach(Button button){ // 업적 눌렀을때 눌린 버튼만 강조 및 내 업적으로 설정
        button.setOnClickListener(view -> { // 나머지는 평범하게
            if(button == findViewById(R.id.ach1)) {
                button.setTextColor(0xAAFFFFFF);
                button.setBackgroundColor(0xAA3d65d3);
                tv_myach.setText("매 일 노 력 하 는 자");

                DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/achievement");         //완탐을 위해
                DB.setValue("매 일 노 력 하 는 자");

                button.setBackgroundResource(R.drawable.buttonshape9);
                ach2.setTextColor(0xAA3d65d3);
                ach2.setBackgroundColor(0xAAFFFFFF);
                ach2.setBackgroundResource(R.drawable.buttonshape8);
                ach3.setTextColor(0xAA3d65d3);
                ach3.setBackgroundColor(0xAAFFFFFF);
                ach3.setBackgroundResource(R.drawable.buttonshape8);
            }
            else if(button == findViewById(R.id.ach2)) {
                button.setTextColor(0xAAFFFFFF);
                button.setBackgroundColor(0xAA3d65d3);
                tv_myach.setText("스 쿼 트  중 수");

                DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/achievement");       //완탐을 위해
                DB.setValue("스 쿼 트 중 수");

                button.setBackgroundResource(R.drawable.buttonshape9);
                ach1.setTextColor(0xAA3d65d3);
                ach1.setBackgroundColor(0xAAFFFFFF);
                ach1.setBackgroundResource(R.drawable.buttonshape8);
                ach3.setTextColor(0xAA3d65d3);
                ach3.setBackgroundColor(0xAAFFFFFF);
                ach3.setBackgroundResource(R.drawable.buttonshape8);
            }
            else {
                button.setTextColor(0xAAFFFFFF);
                button.setBackgroundColor(0xAA3d65d3);
                tv_myach.setText("타 임 어 택  강 자");

                DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/achievement");    //완탐을 위해
                DB.setValue("타 임 어 택 강 자");

               button.setBackgroundResource(R.drawable.buttonshape9);
                ach1.setTextColor(0xAA3d65d3);
                ach1.setBackgroundColor(0xAAFFFFFF);
                ach1.setBackgroundResource(R.drawable.buttonshape8);
                ach2.setTextColor(0xAA3d65d3);
                ach2.setBackgroundColor(0xAAFFFFFF);
                ach2.setBackgroundResource(R.drawable.buttonshape8);
            }
        });
    }
}