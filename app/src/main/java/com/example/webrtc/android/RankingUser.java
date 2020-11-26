package com.example.webrtc.android;

public class RankingUser {
    String email;
    long total_count;
    long speed_time;
    int rank;

    RankingUser(){}

    RankingUser(String email , long total_count , long speed_time, int rank){
        this.email = email;
        this.total_count= total_count;
        this.speed_time = speed_time;
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public long getSpeed_time() {
        return speed_time;
    }

    public void setSpeed_time(long speed_time) {
        this.speed_time = speed_time;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
