package com.zds.readeronline.model;

import android.util.Log;

import com.zds.readeronline.data.HtmlWorker;
import com.zds.readeronline.data.NetReceiveCallback;
import com.zds.readeronline.data.chapterData;
import com.zds.readeronline.presenter.ChapterPresenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 76933 on 2018/1/18.
 */

public class ChapterDataModel {

    private String SEARCHPATH = "https://www.miaobige.com/search/?s=";

    private ChapterPresenter mPresenter;
    private chapterData mData;
    private String name;
    private String mUrl;


    public ChapterDataModel(String name, String url, ChapterPresenter chapterPresenter) {
        this.name = name;
        this.mPresenter = chapterPresenter;
        this.mUrl = url;
        if (mData == null) {
            mData = new chapterData();
            mData.setRealUrl(url);
        }
    }

    public void refresh() {
        if ("".equals(name) || mData == null) {
            return;
        }
        searchBook(name);
    }


    public chapterData getChapterData() {
        return mData;
    }

    //        网络回调
    NetReceiveCallback callback = new NetReceiveCallback() {
        @Override
        public void onNetReceive(String receive) {
            mData.parseData(receive);
            Log.e("zds ChapterDataModel",mData.getUrl()+" "+mData.getChapterList().size());
            mPresenter.notifyList();
        }
    };

    private void searchBook(String name) {
        String encodeName = "";
        try {
            encodeName = URLEncoder.encode(name, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //https://www.miaobige.com/read/18992.html
        if (mData.getUrl() != null)
            HtmlWorker.httpClientWebData(mData.getUrl(), callback);
        if (mUrl != null)
            HtmlWorker.httpClientWebData(mUrl, callback);
    }

    public List<String> getChapterNameList() {
        return mData.getChapterNameList();
    }

    public String getChapterUrl(String chapterName) {
        String url=mData.getChapterList().get(chapterName);
        return url;
    }

    public String getBookUrl() {
        return mUrl;
    }
}
