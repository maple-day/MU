package com.maplechen.mu.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maplechen.mu.Domain.ImgInfo;
import com.maplechen.mu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.BeautyHolder> {
    private final Context context;
    List<ImgInfo.Gallery> datalist;

    public ImgAdapter(List<ImgInfo.Gallery> datalist, Context context) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public BeautyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
        return new BeautyHolder(view);
    }

    @Override
    public void onBindViewHolder(BeautyHolder holder, int position) {
        //http://tnfs.tngou.net/img
        String url ="http://tnfs.tngou.net/img/";
        ImgInfo.Gallery newList = datalist.get(position);
        Glide.with(context).load(url+newList.img).into(holder.ivbeauty);
       // holder.timetv.setText(newList.count);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class BeautyHolder extends RecyclerView.ViewHolder{

        ImageView ivbeauty;
        TextView timetv;

        public BeautyHolder(View itemView) {
            super(itemView);
            ivbeauty = (ImageView) itemView.findViewById(R.id.item_iv_img);
            timetv = (TextView) itemView.findViewById(R.id.item_tv_time);
        }
    }
}
