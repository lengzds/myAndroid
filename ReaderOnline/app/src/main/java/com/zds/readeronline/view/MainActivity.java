package com.zds.readeronline.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zds.readeronline.R;
import com.zds.readeronline.adapter.BookListAdapter;
import com.zds.readeronline.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mBookListView;
    private BookListAdapter mBookListAdapter;
    private ImageView mSearchBtn;
    private EditText mInput;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initUI();
    }

    private void initUI() {

        mBookListAdapter = new BookListAdapter(this, mPresenter);
        mBookListView = (RecyclerView) this.findViewById(R.id.book_list);
        mInput = this.findViewById(R.id.search_input);
        mSearchBtn = (ImageView) this.findViewById(R.id.search_btn);

//        SwipeRefreshLayout swipeRefreshLayout = this.findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.refreshAllBook();
//                mBookListAdapter.notifyDataSetChanged();
//            }
//        });
        mBookListView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERT
        mBookListView.setItemAnimator(new DefaultItemAnimator());
        mBookListView.setAdapter(mBookListAdapter);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mInput.getText().toString();
//                Log.e("zds", "searc click " + name);
                if (mPresenter.getBookNameList() != null && mPresenter.getBookNameList().contains(name)) {
                    mPresenter.refreshBook(name);
//                    Log.e("zds", "search click contains");
                } else {
                    mPresenter.addBook(name);
//                    Log.e("zds", "search click not contains");
                }
//                Log.e("zds", " "+mBookListAdapter.getItemCount());
                mBookListAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void viewRes(String res) {
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyList() {
        mBookListAdapter.notifyDataSetChanged();
    }

}
