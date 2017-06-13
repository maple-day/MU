package com.maplechen.mu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maplechen.mu.Domain.Msg;
import com.maplechen.mu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public  class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {


    private List<Msg> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public NoticeAdapter(List<Msg> list) {
        this.list = list;
    }

    @Override
    public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ycunews, parent, false);
        return new NoticeHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeHolder holder, int position) {
        holder.tvNewsDate.setText(list.get(position).date);
        holder.tvNewsMsg.setText(list.get(position).msg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNewsMsg;
        TextView tvNewsDate;
        public NoticeHolder(View itemView) {
            super(itemView);
            tvNewsMsg = (TextView) itemView.findViewById(R.id.tv_news_msg);
            tvNewsDate = (TextView) itemView.findViewById(R.id.tv_news_date);
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

