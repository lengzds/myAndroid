package com.zds.developer.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zds.developer.R;
import com.zds.developer.data.Article;

import java.util.List;

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.RecVH> {
    List<Article> data;
    private View.OnClickListener itemClickListener;

    //构造方法传入数据
    public RecyclerCardAdapter(List<Article> Articles) {
        this.data = Articles;
    }

    //创造ViewHolder
    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //把item的Layout转化成View传给ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main_item, parent, false);
        return new RecVH(view);
    }

    //    将数据放入相应的位置
    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.ivPic.setImageResource(data.get(position).getImg());
        holder.itemView.setOnClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //ViewHolder绑定控件
    public class RecVH extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvTitle;

        public RecVH(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
