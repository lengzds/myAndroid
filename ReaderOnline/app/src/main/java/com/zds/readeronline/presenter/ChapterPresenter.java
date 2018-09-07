package com.zds.readeronline.presenter;

import com.zds.readeronline.model.ChapterDataModel;
import com.zds.readeronline.view.MainView;

import java.util.List;

/**
 * Created by 76933 on 2018/6/3.
 */

public class ChapterPresenter {
    private MainView mainView;
    private ChapterDataModel mDataModel;

    public ChapterPresenter(MainView mainView, String name, String url) {
        this.mainView = mainView;
        this.mDataModel = new ChapterDataModel(name, url,this);
    }

    public void refresh() {
        mDataModel.refresh();
    }

    public List<String> getChapterNameList() {
        return mDataModel.getChapterNameList();
    }

    public String getChapterUrl(String chapterName) {
        return mDataModel.getChapterUrl(chapterName);
    }


    public void onRes(String content) {
        mainView.viewRes(content);
    }

    public void notifyList() {
        mainView.notifyList();
    }

    public String getUrl() {
        return mDataModel.getBookUrl();
    }
}
