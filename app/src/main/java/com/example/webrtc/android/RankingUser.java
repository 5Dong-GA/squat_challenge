package com.example.webrtc.android;

public class RankingUser {
    String email;
    long total_count;

    RankingUser(){}

    RankingUser(String email , long total_count){
        this.email = email;
        this.total_count= total_count;
    }
}
