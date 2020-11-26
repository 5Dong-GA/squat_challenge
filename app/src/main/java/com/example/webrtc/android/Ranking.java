package com.example.webrtc.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ranking extends AppCompatActivity {
    public DatabaseReference DB;
    public DatabaseReference tmp;
    public List <RankingUser> RUlist = new ArrayList<>();
    public TextView tv_rank1;
    public TextView tv_rank2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tv_rank1 = findViewById(R.id.tv_rank1);
        tv_rank2 = findViewById(R.id.tv_rank2);

        //완탐을 해서 이메일 , total_count를 가져온다
        DB = FirebaseDatabase.getInstance().getReference("users");
        Query Q = DB.orderByChild("total_count");
        Q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterator<DataSnapshot> child = snapshot.getChildren().iterator();
                while (child.hasNext()) {
                    DataSnapshot dataSnapshot = child.next();

                    String email = dataSnapshot.getKey();
                    long total_count = (long) dataSnapshot.child("total_count").getValue();

                    RankingUser RU = new RankingUser(email , total_count);
                    RUlist.add(RU);
                }

                //RUlist에 모든게 담김
                if(!child.hasNext()){
                    for(int i=0;i<RUlist.size();i++){
                        if(i==0){
                            tv_rank1.setText(RUlist.get(i).email);
                        }
                        if(i==1){
                            tv_rank2.setText(RUlist.get(i).email);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}