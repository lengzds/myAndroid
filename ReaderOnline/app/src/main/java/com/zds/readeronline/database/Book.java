package com.zds.readeronline.database;

import com.zds.readeronline.data.HtmlWorker;
import com.zds.readeronline.data.NetDataHelper;
import com.zds.readeronline.data.NetReceiveCallback;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zds.
 */

@Entity
public class Book {
    @Id
    private String BookName = "";
    private String BookDes = "";
    private String BookURl = "";
    private String BookContent = "";

    @Generated(hash = 262290694)
    public Book(String BookName, String BookDes, String BookURl, String bookContent) {
        this.BookName = BookName;
        this.BookDes = BookDes;
        this.BookURl = BookURl;
        this.BookContent = bookContent;
    }

    @Generated(hash = 179626505)
    public Book() {
    }

    public void parseData(String netData) {
//        Log.e("zds ", "book parseBookInfo:" + netData.length());
        NetDataHelper.parseBookInfo(netData, this);
    }

//        return "分类:" + BookFenlei + " 作者:" + BookZishu + " 字数:" + BookZishu + " 更新时间:" + BookGengxin;


    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookURl() {
        return BookURl;
    }

    public void setBookURl(String bookURl) {
        BookURl = bookURl;
    }

    public String getBookContent() {
        return BookContent;
    }

    public void setBookContent(String bookContent) {
        BookContent = bookContent;
    }

    public String getBookDes() {
        return BookDes;
    }

    public void setBookDes(String bookDes) {
        BookDes = bookDes;
    }

}
