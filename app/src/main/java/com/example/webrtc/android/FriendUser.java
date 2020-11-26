package com.example.webrtc.android;

public class FriendUser {
    String id;

    FriendUser(){}

    FriendUser(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
