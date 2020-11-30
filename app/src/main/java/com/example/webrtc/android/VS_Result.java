package com.example.webrtc.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VS_Result extends AppCompatActivity {

    String result = "";
    String my_count = "";
    String op_count = "";

    String email = "";
    String name = "";
    String photoUrl = "";

    TextView tv_my;
    TextView tv_op;
    ImageView iv_result;
    ImageView game_result;

    DatabaseReference DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_result);
        Intent intent = getIntent();
        my_count = intent.getStringExtra("my_count");
        op_count = intent.getStringExtra("op_count");
        result = intent.getStringExtra("result");

        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        photoUrl = intent.getStringExtra("photoUrl");

        tv_my = findViewById(R.id.tv_my);
        tv_op = findViewById(R.id.tv_op);
        iv_result = findViewById(R.id.iv_result);
        game_result = findViewById(R.id.game_result);

        ImageView to_home = findViewById(R.id.to_home);

        //op_count 불러오기
        DB = FirebaseDatabase.getInstance().getReference("users/gljhan123/now_count");
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int op_count = Integer.parseInt(String.valueOf(snapshot.getValue()));
                tv_op.setText(String.valueOf(op_count));

                //이겼다
                if(Integer.parseInt(my_count) > op_count){
                    game_result.setBackgroundResource(R.drawable.win);
                }
                //졌다
                else{
                    game_result.setBackgroundResource(R.drawable.lose);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tv_my.setText(my_count);

        to_home.setOnClickListener(view -> { // 결과, 몇스테이지인지, 결과창에서 간다는 exit 전달
            Intent intent12 = new Intent(getApplicationContext(), Main.class);
            intent12.putExtra("Email", email);
            intent12.putExtra("name", name);
            intent12.putExtra("photoUrl", photoUrl);

            finish();
            startActivity(intent12);
        });

    }
}