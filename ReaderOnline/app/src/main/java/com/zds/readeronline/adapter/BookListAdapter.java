package com.zds.readeronline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zds.readeronline.R;
import com.zds.readeronline.presenter.MainPresenter;
import com.zds.readeronline.view.ChapterActivity;

/**
 * Created by 76933 on 2018/1/18.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private final MainPresenter mPresenter;
    private Context mContext;


    public BookListAdapter(Context context, MainPresenter presenter) {
        this.mPresenter = presenter;
//        mPresenter.searchBook("大道朝天");
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String bookName = mPresenter.getBookNameList().get(position);

        holder.bookTitle.setText(bookName);
        holder.bookDescription.setText(mPresenter.getDes(bookName));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("zds", "jump to book : " + bookName);
                Intent intent = new Intent(mContext, ChapterActivity.class);
                intent.putExtra("name", bookName);
                intent.putExtra("url", mPresenter.getBook(bookName).getBookURl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPresenter.getBookNameList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View bookCurrent;
        public TextView bookTitle;
        public TextView bookDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            bookCurrent = itemView.findViewById(R.id.book_current);
            bookTitle = (TextView) itemView.findViewById(R.id.book_title);
            bookDescription = (TextView) itemView.findViewById(R.id.book_description);
        }
    }
}
