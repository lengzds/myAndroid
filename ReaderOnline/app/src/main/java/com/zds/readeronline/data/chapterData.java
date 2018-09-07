package com.zds.readeronline.data;

import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 76933 on 2018/6/3.
 */

public class chapterData {
    private String url = "";//图书章节目录
    private HashMap<String, String> mChapterList = new HashMap<>();//章节名-url
    private List<String> mCahpterName = new ArrayList<>();

    public void parseData(String netData) {
        NetDataHelper.parseChapterList(netData, this);
    }

    public void addChapterList(String name, String url) {
        mChapterList.put(name, url);
    }

    public void addChapterNameList(String name) {
        mCahpterName.add(name);
    }

    public HashMap<String, String> getChapterList() {
        return mChapterList;
    }

    public List<String> getChapterNameList() {
        return mCahpterName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url0) {
        HtmlWorker.httpClientGetUrl(url0, new NetReceiveCallback() {
            @Override
            public void onNetReceive(String real) {
                url = real;
            }
        });
    }

    public void setRealUrl(String realUrl) {
        this.url = realUrl;
    }

}
