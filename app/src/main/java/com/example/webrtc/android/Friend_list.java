package com.example.webrtc.android;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Any;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Friend_list extends AppCompatActivity {

    private Button add_friend;
    private Button add_friend_button;
    private LinearLayout add_friend_chang;
    private LinearLayout friend_linear;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference DB;
    DatabaseReference Friend_DB;
    String email="";
    private ArrayList<FriendUser> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");

        add_friend_button = findViewById(R.id.add_friend_button); // 메인의 친구추가 버튼
        friend_linear = findViewById(R.id.friend_linear); // 메인의 상단layer
        add_friend_chang = findViewById(R.id.add_friend_chang); // 친구추가 창
        add_friend = findViewById(R.id.add_friend); // 친구추가 창의 친구추가 버튼
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView); // 아디 연결
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화

        add_friend_button.setOnClickListener(view -> { // 친구추가 누르면 친구추가 창 뜸
            add_friend_chang.setVisibility(View.VISIBLE);
            friend_linear.setVisibility(View.INVISIBLE);
        });

        add_friend.setOnClickListener(view -> { // 친구추가 창에서 아이디 치고 친구추가 누르면
            EditText add_friend_name = findViewById(R.id.add_friend_name); // 화면 돌아오면서 추가
            String _add_friend_name = add_friend_name.getText().toString();
            newfriend(_add_friend_name);
            add_friend_name.setText("");
            add_friend_name.setHint("아이디를 입력해주세요");
            add_friend_chang.setVisibility(View.INVISIBLE);
            friend_linear.setVisibility(View.VISIBLE);
        });



        //db접근해서 friend_lsit를 보여주는 코드!!!!
        friendList = new ArrayList<>();
        //완탐을 해서 친구 이메일을 가져온다
        Friend_DB = FirebaseDatabase.getInstance().getReference("users/" +email+"/friend_list");

        Friend_DB.addListenerForSingleValueEvent(new ValueEventListener()   {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                friendList.clear(); // 기존 배열리스트가 존재하지않게 초기화

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    String email = (String) snapshot.getValue();
                    System.out.println(email+"!!!!!!!!!!!!!");
                    FriendUser FU = new FriendUser(email);
                    friendList.add(FU); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new Friend_CustomAdapter(friendList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결
    }

    public void newfriend(final String fname) {
        DB = FirebaseDatabase.getInstance().getReference("users/" + email + "/friend_list");
        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendList.add(new FriendUser(fname)); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                DB.push().setValue(fname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}