package com.example.webrtc.android;

public class User {
    public String username;
    public long speed_time;
    public long total_count;
    public String team_name;
    public String state;
    public int stage_num;
    public String achievement;
    public int rank_num;

    //VS모드를 위해
    public int now_count;
    public String op_id;

    public User(){}

    public User(String username){
        this.username = username;
        this.speed_time = 180000;
        this.total_count = 0;
        this.team_name = "";
        this.state = "on";
        this.stage_num = 0;
        this.achievement = "스 쿼 트 꿈 나 무";
        this.rank_num = 0;
        this.op_id="";
        this.now_count=0;
    }
}