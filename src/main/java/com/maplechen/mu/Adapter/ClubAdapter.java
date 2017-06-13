package com.maplechen.mu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maplechen.mu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */
public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubHolder> {
    private List<String> list;

    public ClubAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public ClubHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubHolder(view);
    }

    @Override
    public void onBindViewHolder(ClubHolder holder, int position) {
        String text = list.get(position);
        if(position==22){
            String[] split = text.split("　　");
            holder.clubName.setText(split[0]);
            holder.clubLeader.setText(split[1]);
            holder.clubSummary.setText(split[2]);
        }else if(position==12){
            String[] split = text.split("　　");
            holder.clubName.setText(split[1]);
            holder.clubLeader.setText(split[2]);
            holder.clubSummary.setText(split[3]+split[4]);
        } else if(position==10){
            String[] split = text.split("　　");
            holder.clubName.setText(split[1]);
            holder.clubLeader.setText(split[2]);
            holder.clubSummary.setText(split[3]+split[4]);
        }else if(position==5){
            String[] split = text.split("　　");
            holder.clubName.setText(split[1]);
            holder.clubLeader.setText(split[2]);
            holder.clubSummary.setText(split[3]+split[4]);
        } else {
            String[] split = text.split("　　");
            holder.clubName.setText(split[1]);
            holder.clubLeader.setText(split[2]);
            holder.clubSummary.setText(split[3]);
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ClubHolder extends RecyclerView.ViewHolder  {

        public TextView clubName;
        public TextView clubLeader;
        public TextView clubSummary;
        public ClubHolder(View itemView) {
            super(itemView);
            clubName = (TextView) itemView.findViewById(R.id.club_tv_name);
            clubLeader = (TextView) itemView.findViewById(R.id.club_tv_leader);
            clubSummary = (TextView) itemView.findViewById(R.id.club_tv_sunmary);
        }


    }

}
