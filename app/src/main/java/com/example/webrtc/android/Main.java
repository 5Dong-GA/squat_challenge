package com.example.webrtc.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {

    //접속중 표시를 위해 DB접근
    DatabaseReference DB;
    private String email;

    TextView myach, myname, mystage, myrank, stage, rank;

    private TextView quest_name1,quest_name2,quest_name3;
    private ImageView reroll1,reroll2,reroll3;
    private int questcnt;
    private int rank_index=1;
    Map<Integer, String> questSet = new HashMap<>();
    Main.quest[] today_quest = new Main.quest[3];

    //카메라 권한을 위해
    private int RESULT_PERMISSIONS = 100;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB = FirebaseDatabase.getInstance().getReference("users");         //완탐을 위해
        DB.child(email).child("state").setValue("off");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intent 받기
        Intent intent = getIntent();
        final String nickName = intent.getStringExtra("name");        //loginactivity로부터 닉네임 전달받음
        final String photoUrl = intent.getStringExtra("photoUrl");        //loginactivity로부터 프로필사진 Url전달받음
        email = intent.getStringExtra("Email");        //구글이메일

        Main.quest q = new Main.quest("", 0, true);
        q.setTodayQuest();


        //myach set!
        myach = findViewById(R.id.myach);
        DB = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference myach_DB = DB.child(email).child("achievement");

        myach_DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myach.setText(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextViewCompat.setAutoSizeTextTypeWithDefaults(myach, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        //name  set!
        myname = findViewById(R.id.myname);
        myname.setText(nickName);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(myname, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        //rank 가져오기!
        myrank = findViewById(R.id.myrank);
        DatabaseReference  myrank_DB = DB;
        Query Q = myrank_DB.orderByChild("total_count");
        Q.addListenerForSingleValueEvent(new ValueEventListener()   {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    String tmp_email = snapshot.getKey();
                    if(email.equals(tmp_email)){
                        myrank.setText(String.valueOf(rank_index));
                        myrank_DB.child(email).child("rank_num").setValue(rank_index);
                        break;
                    }
                    rank_index++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextViewCompat.setAutoSizeTextTypeWithDefaults(myrank, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);


        // STAGE RANK 노란색 글씨 부분
        stage = findViewById(R.id.stage);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(stage, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        rank = findViewById(R.id.rank);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(rank, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);


        mystage = findViewById(R.id.mystage);
        DatabaseReference  mystage_DB = DB.child(email).child("stage_num");
        mystage_DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mystage.setText(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextViewCompat.setAutoSizeTextTypeWithDefaults(mystage, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        // 안드로이드 6.0 이상 버전에서는 CAMERA 권한 허가를 요청한다.
        requestPermissionCamera();


        ImageView Quest = findViewById(R.id.Quest);
        LinearLayout questlist = findViewById(R.id.questlist);
        LinearLayout main_linear = findViewById(R.id.main_linear);

        Quest.setOnClickListener(view -> { // 퀘스트 누르면 위로 오버랩되게 처리
            questlist.setVisibility(View.VISIBLE);
            main_linear.setVisibility(View.INVISIBLE);
        });
        questlist.setOnClickListener(view -> {
            questlist.setVisibility(View.INVISIBLE);
            main_linear.setVisibility(View.VISIBLE);
        });

        reroll1 = findViewById(R.id.reroll1);
        reroll2 = findViewById(R.id.reroll2);
        reroll3 = findViewById(R.id.reroll3);
        reroll1.setOnClickListener(view -> reroll(reroll1));
        reroll2.setOnClickListener(view -> reroll(reroll2));
        reroll3.setOnClickListener(view -> reroll(reroll3));

        ImageView friendlist = findViewById(R.id.Friendlist); // 친구목록으로
        friendlist.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), Friend_list.class);
            intent12.putExtra("Email", email);
            startActivity(intent12);
        });

        LinearLayout mypage = findViewById(R.id.Mypage); // 마이페이지로
        mypage.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), Mypage.class);
            intent1.putExtra("name", nickName);
            intent1.putExtra("photoUrl", photoUrl);
            intent1.putExtra("Email", email);
            startActivity(intent1);
        });

        ImageView Rank = findViewById(R.id.Rank);
        Rank.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), Ranking.class);
            intent1.putExtra("name", nickName);
            intent1.putExtra("photoUrl", photoUrl);
            intent1.putExtra("Email", email);
            startActivity(intent1);
        });

        ImageView play = findViewById(R.id.Play);
        //솔로 플레이 버튼이 눌렸을때 (모드 선택화면)
        play.setOnClickListener(view -> {
            Intent intent14 = new Intent(getApplicationContext(), Play_selection.class);
            intent14.putExtra("Email", email);       //우선 id만 넘겨준다 가정
            intent14.putExtra("name", nickName);       //우선 id만 넘겨준다 가정
            intent14.putExtra("photoUrl", photoUrl);       //우선 id만 넘겨준다 가정
            startActivity(intent14);
        });
    }

    public class quest {
        String name;
        int num;
        Boolean clear;

        quest() {
            this.name = "";
            this.num = -1;
            this.clear = false;
        }

        quest(String q, int n, Boolean c) {
            this.name = q;
            this.num = n;
            this.clear = c;
        }

        void setTodayQuest() { // 퀘스트 랜덤하게 선정
            setQuest();
            int ran = (int) (Math.random() * 9);
            for (int i = 0; i < 3; i++) {
                if (ran >= 8) ran = 0;
                else if (ran <= 0) ran = 0;
                today_quest[i] = new Main.quest(questSet.get(ran), ran + 1, false);
                questSet.remove(ran);
                ran++;
            }
            questcnt = ran;
            quest_name1 = findViewById(R.id.quest_name1);
            quest_name1.setText(today_quest[0].name);
            quest_name2 = findViewById(R.id.quest_name2);
            quest_name2.setText(today_quest[1].name);
            quest_name3 = findViewById(R.id.quest_name3);
            quest_name3.setText(today_quest[2].name);
        }
    }

    ;

    public void setQuest() {
        questSet.put(1, "스쿼트 55회");
        questSet.put(2, "친선전 1회");
        questSet.put(3, "1대1 3회");
        questSet.put(4, "1대1 승리");
        questSet.put(5, "스쿼트 100회");
        questSet.put(6, "친구추가\n1회");
        questSet.put(7, "혼자하기\n3회");
        questSet.put(8, "랭커 영상\n1회 관전");
        questSet.put(0, "친선전 승리");
    }

    public void reroll(ImageView imageView) { // 리롤하면 버튼 사라지고 퀘스트 교체
        imageView.setOnClickListener(view -> {
            if (imageView == findViewById(R.id.reroll1)) {
                if (questcnt >= 8) questcnt = 0;
                quest_name1.setText(questSet.get(questcnt));
                reroll1.setVisibility(View.INVISIBLE);
                questcnt++;
            } else if (imageView == findViewById(R.id.reroll2)) {
                if (questcnt >= 8) questcnt = 0;
                quest_name2.setText(questSet.get(questcnt));
                reroll2.setVisibility(View.INVISIBLE);
                questcnt++;
            } else {
                if (questcnt >= 8) questcnt = 0;
                quest_name3.setText(questSet.get(questcnt));
                reroll3.setVisibility(View.INVISIBLE);
                questcnt++;
            }
        });
    }

    //카메라라
    private boolean requestPermissionCamera() {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Main.this,
                        new String[]{Manifest.permission.CAMERA},
                        RESULT_PERMISSIONS);

            }
//            else {
//                setInit();
//            }
        }
//        else{  // version 6 이하일때
//            setInit();
//            return true;
//        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (RESULT_PERMISSIONS == requestCode) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허가시
                //setInit();
            } else {
                // 권한 거부시
            }
            return;
        }

    }
}