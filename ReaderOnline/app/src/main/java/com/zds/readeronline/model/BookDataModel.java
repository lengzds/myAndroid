package com.zds.readeronline.model;

import com.zds.readeronline.data.Book;
import com.zds.readeronline.data.BookListData;
import com.zds.readeronline.data.HtmlWorker;
import com.zds.readeronline.data.NetReceiveCallback;
import com.zds.readeronline.presenter.MainPresenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 76933 on 2018/1/18.
 */

public class BookDataModel {

    private MainPresenter mPresenter;
    private String SEARCHPATH = "https://www.miaobige.com/search/?s=";

    private BookListData mData = new BookListData();

    public BookDataModel(MainPresenter mainPresenter) {
        this.mPresenter = mainPresenter;
    }

    public void refreshBook(String name) {
        Book book;
        if (mData.getBookNameList().contains(name)) {
            book = mData.getBook(name);
        } else {
            book = new Book();
            try {
                book.setUrl(SEARCHPATH + URLEncoder.encode(name, "gb2312"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mData.addBook(name, book);
        }
        if (book != null) {
            searchBook(name, book.getUrl());
        }
    }

    public void refreshAllBook() {
        for (String name : mData.getBookNameList()) {
            Book book = mData.getBook(name);
            searchBook(name, book.getUrl());
        }
    }

    public List<String> getBookNameList() {
        return mData.getBookNameList();
    }

    public Book getBook(String name) {
        return mData.getBook(name);
    }

    public String getDescription(String name) {
        return mData.getBook(name).getdescription();
    }

    /**
     * see #refreshBook
     */
    public void addBook(String name) {
        Book book = new Book();
        try {
            book.setUrl(SEARCHPATH + URLEncoder.encode(name, "gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mData.addBook(name, book);
        searchBook(name, book.getUrl());
    }

    public void searchBook(final String name, String url) {
        String encodeName = "";
        try {
            encodeName = URLEncoder.encode(name, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        网络回调
        NetReceiveCallback callback = new NetReceiveCallback() {
            @Override
            public void onNetReceive(String receive) {
                mData.getBook(name).parseData(receive);
                mPresenter.notifyList();
            }
        };
        //https://www.miaobige.com/read/18992.html
        if (url.contains("read")) {
            HtmlWorker.httpClientWebData(url, callback);
        } else {
            HtmlWorker.httpClientWebDataWithSearch(SEARCHPATH + encodeName, callback);
        }

    }
}
