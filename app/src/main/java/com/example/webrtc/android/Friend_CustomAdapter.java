package com.example.webrtc.android;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Friend_CustomAdapter extends RecyclerView.Adapter<Friend_CustomAdapter.CustomViewHolder>{
    private ArrayList<FriendUser> arrayList;
    private Context context;


    public Friend_CustomAdapter(ArrayList<FriendUser> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Friend_CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_friend, parent, false);
        Friend_CustomAdapter.CustomViewHolder holder = new Friend_CustomAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Friend_CustomAdapter.CustomViewHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(arrayList.get(position).getProfile())
//                .into(holder.iv_profile);
        holder.friend_id.setText(arrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if(arrayList != null) return arrayList.size();
        else return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView friend_img;
        TextView friend_id;
        ImageView delete_friend;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.friend_img = itemView.findViewById(R.id.friend_img);
            this.friend_id = itemView.findViewById(R.id.friend_id);
            this.delete_friend = itemView.findViewById(R.id.delete_friend);
        }
    }
}
