package com.maplechen.mu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maplechen.mu.Domain.NewInfo;
import com.maplechen.mu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class NewItAdapter extends RecyclerView.Adapter<NewItAdapter.myNewHolder> {

    List<NewInfo.NewList> datalist;
    Context context;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public NewItAdapter(List<NewInfo.NewList> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public myNewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new myNewHolder(view);
    }

    @Override
    public void onBindViewHolder(myNewHolder holder, int position) {
        NewInfo.NewList newList = datalist.get(position);
        Glide.with(context).load(newList.picUrl).into(holder.ivImg);
        holder.tvTitle.setText(newList.title);
        holder.tvSource.setText(newList.description);
        holder.tvTime.setText(newList.ctime);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class myNewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivImg;
        TextView tvTitle;
        TextView tvSource;
        TextView tvTime;
        public myNewHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
