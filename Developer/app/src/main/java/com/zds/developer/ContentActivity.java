package com.zds.developer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AndroidException;

import com.zds.developer.adapter.RecyclerContentCardAdapter;
import com.zds.developer.data.DataManager;

public class ContentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private RecyclerContentCardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager.getInstance().initTestCon();
        initView();
    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("CSDN");
        mToolbar.setSubtitle("假装是csdn的一篇文章呵呵呵呵呵呵呵呵呵呵呵呵哈哈哈");
//        mToolbar.setNavigationIcon(android.R.drawable.ic_btn_speak_now);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置adapter
        mAdapter = new RecyclerContentCardAdapter(DataManager.getInstance().getContents());
        mRecyclerView.setAdapter(mAdapter);
    }

}
