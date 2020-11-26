package com.example.webrtc.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<RankingUser> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<RankingUser> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ranking, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(arrayList.get(position).getProfile())
//                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getEmail());
        holder.tv_total_count.setText(String.valueOf(arrayList.get(position).getTotal_count()));
        holder.tv_speed_time.setText(String.valueOf(arrayList.get(position).getSpeed_time()));
    }

    @Override
    public int getItemCount() {
        if(arrayList != null) return arrayList.size();
        else return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_total_count;
        TextView tv_speed_time;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_total_count = itemView.findViewById(R.id.tv_total_count);
            this.tv_speed_time = itemView.findViewById(R.id.tv_speed_time);
        }
    }
}