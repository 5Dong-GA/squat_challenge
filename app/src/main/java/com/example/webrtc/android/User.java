package com.example.webrtc.android;

public class User {
    public String username;
    public long speed_time;
    public long total_count;
    public String team_name;
    public String state;
    public int stage_num;
    public String achivment;
    public User(){}

    public User(String username){
        this.username = username;
        this.speed_time = 180000;
        this.total_count = 0;
        this.team_name = "";
        this.stage_num = 0;
        this.achivment = "스 쿼 트  초 보";
        this.state = "on";
    }
}