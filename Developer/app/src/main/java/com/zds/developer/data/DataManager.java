package com.zds.developer.data;

import com.zds.developer.R;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<List<Article>> mArticles = new ArrayList<List<Article>>();
    private final String[] webs = {"csdn", "简书", "开发者头条", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "f", "g", "h", "i", "j", "f", "g", "h", "i", "j", "f", "g", "h", "i", "j"};

    private List<Content> contents = new ArrayList<>();


    private DataManager() {
    }

    private static DataManager dataManager = new DataManager();

    // 静态工厂方法
    public static DataManager getInstance() {
        return dataManager;
    }


    public void initTestData() {
        for (String s : webs) {
            List<Article> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(new Article(R.drawable.csdn, s + " " + i));
            }
            mArticles.add(list);
        }
    }

    public void initTestCon() {
        String s = "        Material Design: RecyclerView配合CardView创建卡片列..._CSDN博客\n" +
                "       2017年9月16日 - 详解Android中利用RecyclerView及CardView来创建卡片列表...\n" +
                "       利用RecyclerView及CardView来创建卡片列表现在卡片有了,我们接着来实现RecyclerView" +
                ",相信大家既然看这篇文章,对于RecyclerView肯定是使用过的,那我就不具体的讲如何使用RecyclerView了" +
                "先贴出效果图: RecyclerView相当于一个更强...";
        for (int i = 0; i < 20; i++) {

            Content content = new Content();
            content.setType(i % 2);
            content.setText(s+s);
            content.setImg(R.drawable.csdn);
            contents.add(content);
        }
    }

    public String[] getWebs() {
        return webs;
    }

    public List<Article> getArticles(int i) {
        return mArticles.get(i);
    }

    public List<Content> getContents() {
        return contents;
    }
}
