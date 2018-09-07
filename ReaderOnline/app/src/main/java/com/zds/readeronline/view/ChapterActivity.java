package com.zds.readeronline.view;

import android.app.Activity;
import android.os.Bundle;

import com.zds.readeronline.R;
import com.zds.readeronline.adapter.BookListAdapter;
import com.zds.readeronline.adapter.ChapterListAdapter;
import com.zds.readeronline.presenter.ChapterPresenter;
import com.zds.readeronline.presenter.MainPresenter;

import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by 76933 on 2018/6/2.
 */

public class ChapterActivity extends Activity implements MainView {
    private ChapterPresenter mPresenter;
    private NavigationView mNavigationView;
    private RecyclerView mChapterListView;
    public TextView contentView;
    private ChapterListAdapter mChapterListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        mPresenter = new ChapterPresenter(this, name, url);
        mPresenter.refresh();
        mNavigationView = findViewById(R.id.navi_all);
        mChapterListView = (RecyclerView) this.findViewById(R.id.chapter_list);
        contentView = findViewById(R.id.content);
        contentView.setMovementMethod(ScrollingMovementMethod.getInstance());
        mChapterListAdapter = new ChapterListAdapter(this, mPresenter);
        mChapterListView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mChapterListView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mChapterListView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERT
        mChapterListView.setItemAnimator(new DefaultItemAnimator());
        mChapterListView.setAdapter(mChapterListAdapter);
    }

    @Override
    public void viewRes(String res) {
        contentView.setText(res);
    }

    @Override
    public void notifyList() {
        mChapterListAdapter.notifyDataSetChanged();
    }
}
