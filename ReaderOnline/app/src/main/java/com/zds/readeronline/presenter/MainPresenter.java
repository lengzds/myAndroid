package com.zds.readeronline.presenter;

import com.zds.readeronline.database.Book;
import com.zds.readeronline.model.BookDataModel;
import com.zds.readeronline.view.MainView;

import java.util.List;

/**
 * Created by 76933 on 2018/1/18.
 */

public class MainPresenter {
    private MainView mainView;
    private BookDataModel mDataModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mDataModel = new BookDataModel(this);
    }

    public void onDestroy() {
        mainView = null;
    }

    public void addBook(String name) {
        mDataModel.addBook(name);
//        if(s.length()>20){
//            onSearh(s.substring(0,20));
//        }else {
//            onSearh("error");
//        }

    }

    public List<String> getBookNameList() {
        return mDataModel.getBookNameList();
    }

    public String getDes(String name) {
        return mDataModel.getDescription(name);
    }

    public void refreshBook(String name) {
        mDataModel.refreshBook(name);
    }

    public void refreshAllBook() {
        mDataModel.refreshAllBook();
    }

    public void onSearh(String substring) {
        if (mainView != null) {
            mainView.viewRes(substring);
        }

    }

    public void notifyList() {
        mainView.notifyList();
    }

    public Book getBook(String name) {
        return mDataModel.getBook(name);
    }
}
