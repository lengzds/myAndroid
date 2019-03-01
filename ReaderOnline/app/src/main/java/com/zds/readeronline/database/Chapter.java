package com.zds.readeronline.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by JKWANG-PC on 2016/12/30.
 */
@Entity
public class Chapter {
    @Id
    private String Id;
    private String Name;
    private String Url;
    private String content;

    @Generated(hash = 89649623)
    public Chapter(String Id, String Name, String Url, String Content) {
        this.Id = Id;
        this.Name = Name;
        this.Url = Url;
        this.content = Content;
    }

    @Generated(hash = 1406030881)
    public Chapter() {
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
