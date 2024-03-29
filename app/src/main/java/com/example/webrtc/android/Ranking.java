package com.example.webrtc.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {
//    public DatabaseReference DB;
//    public DatabaseReference tmp;
//    public List <RankingUser> RUlist = new ArrayList<>();

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RankingUser> arrayList;
    private DatabaseReference DB;
    private int rank =1;
    private int User_num=0;
    private String email;


    TextView tv_id;
    TextView tv_count;
    TextView tv_rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        final String nickName = intent.getStringExtra("name");        //loginactivity로부터 닉네임 전달받음
        final String photoUrl = intent.getStringExtra("photoUrl");        //loginactivity로부터 프로필사진 Url전달받음
        email = intent.getStringExtra("Email");


        //FIND
        tv_id=findViewById(R.id.tv_id);
        tv_count=findViewById(R.id.tv_count);
        tv_rank=findViewById(R.id.tv_rank);

        tv_id.setText(email);

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // 아디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        //완탐을 해서 이메일 , total_count를 가져온다
        DB = FirebaseDatabase.getInstance().getReference("users");

        Query Q = DB.orderByChild("total_count");

        Q.addListenerForSingleValueEvent(new ValueEventListener()   {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    String email = snapshot.getKey();
                    long total_count = (long) snapshot.child("total_count").getValue();
                    long speed_time = (long) snapshot.child("speed_time").getValue();
                    RankingUser RU = new RankingUser(email , total_count , speed_time , rank);
                    arrayList.add(RU); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    rank++;
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        adapter = new Ranking_CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결


        //자기정보 가져오기
        DatabaseReference tmp = DB.child(email);
        tmp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    String key = datasnapshot.getKey();
                    if(key.equals("rank_num")){
                        String tmp_s = String.valueOf(datasnapshot.getValue());
                        tv_rank.setText(tmp_s);
                    }
                    if(key.equals("total_count")){
                        long tmp_s =  Long.parseLong( String.valueOf(datasnapshot.getValue()));
                        tmp_s *=-1;
                        tv_count.setText(String.valueOf(tmp_s));
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}