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

    public User(){}

    public User(String username){
        this.username = username;
        this.speed_time = 180000;
        this.total_count = 0;
        this.team_name = "";
        this.state = "on";
        this.stage_num = 0;
        this.achievement = "스쿼트 꿈나무";
        this.rank_num = 0;
    }
}