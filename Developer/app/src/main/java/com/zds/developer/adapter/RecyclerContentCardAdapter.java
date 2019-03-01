package com.zds.developer.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zds.developer.R;
import com.zds.developer.data.Content;

import java.util.List;

public class RecyclerContentCardAdapter extends RecyclerView.Adapter<RecyclerContentCardAdapter.RecVH> {
    List<Content> data;

    //构造方法传入数据
    public RecyclerContentCardAdapter(List<Content> Contents) {
        this.data = Contents;
    }

    //创造ViewHolder
    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content_item, parent, false);
        return new RecVH(view);
    }

    //    将数据放入相应的位置
    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        if (data.get(position).getType() == 0) {
            holder.tvTitle.setText(data.get(position).getText());
            holder.ivPic.setVisibility(View.GONE);
        }
        if (data.get(position).getType() == 1) {
            holder.tvTitle.setVisibility(View.GONE);
            holder.ivPic.setImageResource(data.get(position).getImg());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Content> data) {
        this.data = data;
    }

    //ViewHolder绑定控件
    public class RecVH extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvTitle;

        public RecVH(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.imgContent);
            tvTitle = (TextView) itemView.findViewById(R.id.textContent);
        }
    }
}
