package com.zds.readeronline.data;

import android.util.Log;

import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 76933 on 2017/12/27.
 */

public class Book {
    private String name = "";
    private String fenlei = "";
    private String zuozhe = "";
    private String zishu = "";
    private String gengxin = "";
    private String url = "";//图书章节目录


    public void parseData(String netData) {
//        Log.e("zds ", "book parseBookInfo:" + netData.length());
        NetDataHelper.parseBookInfo(netData, this);
    }

    public String getdescription() {
        return "分类:" + fenlei + " 作者:" + zishu + " 字数:" + zishu + " 更新时间:" + gengxin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFenlei(String fenlei) {
        this.fenlei = fenlei;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }

    public void setZishu(String zishu) {
        this.zishu = zishu;
    }

    public void setGengxin(String gengxin) {
        this.gengxin = gengxin;
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

    public String getName() {
        return name;
    }

}
