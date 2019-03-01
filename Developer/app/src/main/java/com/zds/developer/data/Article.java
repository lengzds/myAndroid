package com.zds.developer.data;

public class Article {
    private String title;
    private int image;

    public Article(int i, String t) {
        this.title = t;
        this.image = i;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return image;
    }

    public void setImg(int image) {
        this.image = image;
    }
}
