package com.example.webrtc.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ranking_CustomAdapter extends RecyclerView.Adapter<Ranking_CustomAdapter.CustomViewHolder> {

    private ArrayList<RankingUser> arrayList;
    private Context context;


    public Ranking_CustomAdapter(ArrayList<RankingUser> arrayList, Context context) {
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
        holder.tv_record.setText(String.valueOf(arrayList.get(position).getTotal_count() * -1));
        holder.tv_rank.setText(String.valueOf(arrayList.get(position).getRank()));
    }

    @Override
    public int getItemCount() {
        if(arrayList != null) return arrayList.size();
        else return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_record;
        TextView tv_rank;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_record = itemView.findViewById(R.id.tv_record);
            this.tv_rank = itemView.findViewById(R.id.tv_rank);
        }
    }
}