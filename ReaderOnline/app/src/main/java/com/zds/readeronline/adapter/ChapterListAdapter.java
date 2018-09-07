package com.zds.readeronline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zds.readeronline.R;
import com.zds.readeronline.data.NetDataHelper;
import com.zds.readeronline.data.NetReceiveCallback;
import com.zds.readeronline.presenter.ChapterPresenter;
import com.zds.readeronline.presenter.MainPresenter;

/**
 * Created by 76933 on 2018/1/18.
 */

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {

    private final ChapterPresenter mPresenter;
    private Context mContext;


    public ChapterListAdapter(Context context, ChapterPresenter presenter) {
        this.mPresenter = presenter;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.chapter_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String chapterName = mPresenter.getChapterNameList().get(position);

        holder.chapterTitle.setText(chapterName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mPresenter.getChapterUrl(chapterName);

                NetReceiveCallback callback=new NetReceiveCallback() {
                    @Override
                    public void onNetReceive(String s) {
                        Log.e("zds ChapterAdapter",s);
                        mPresenter.onRes(s);
                    }
                };
                NetDataHelper.getContent(mPresenter.getUrl()+url,callback);
                Log.e("zds ChapterListAdapter",mPresenter.getUrl()+url+" "+chapterName+" "+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPresenter.getChapterNameList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView chapterTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            chapterTitle = (TextView) itemView.findViewById(R.id.chapter_title);
        }
    }
}
