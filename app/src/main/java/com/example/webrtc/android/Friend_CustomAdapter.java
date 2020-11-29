package com.example.webrtc.android;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Friend_CustomAdapter extends RecyclerView.Adapter<Friend_CustomAdapter.CustomViewHolder>{
    private ArrayList<FriendUser> arrayList;
    private Context context;

    DatabaseReference DB;


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
        String email = arrayList.get(position).getId();
        holder.friend_id.setText(email);

        //여기서 DB를 본다음에 만약 state가 on이면 파란불 표시
        DB = FirebaseDatabase.getInstance().getReference("users/"+email+"/state");
        DB.addListenerForSingleValueEvent(new ValueEventListener()   {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().equals("off")){
                    holder.iv_state.setImageResource(R.drawable.state_off);
                }
                else{
                    holder.iv_state.setImageResource(R.drawable.state_on);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        ImageView iv_state;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_state = itemView.findViewById(R.id.iv_state);
            this.friend_img = itemView.findViewById(R.id.friend_img);
            this.friend_id = itemView.findViewById(R.id.friend_id);
            this.delete_friend = itemView.findViewById(R.id.delete_friend);
        }
    }
}
