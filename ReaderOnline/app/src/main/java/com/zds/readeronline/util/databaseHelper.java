package com.zds.readeronline.util;

import android.content.Context;

import com.zds.readeronline.database.Book;
import com.zds.readeronline.database.Chapter;
import com.zds.readeronline.database.DaoSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class databaseHelper {
    private DaoSession session;

    public void saveBook(Context context, String name, String des, String url, String con) {
        session = GreenDaoHelper.getInstance().getDaoSession(context);
        Book book = new Book(name, des, url, con);
        session.getBookDao().insert(book);
    }

    public Book getBook(Context context, String name) {
        session = GreenDaoHelper.getInstance().getDaoSession(context);
        List<Book> books = session.getBookDao().loadAll();//TODO 如果不行，遍历
        Book book = session.getBookDao().load(name);
        return book;
    }

    public void saveChapters(Context context, String con) {
        session = GreenDaoHelper.getInstance().getDaoSession(context);

        try {
//            JSONObject jsonObject =new JSONObject(con);
            JSONArray jsonArray = new JSONArray(con);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("id");
                String name = object.getString("name");
                String url = object.getString("url");
                String content = object.getString("content");

                Chapter chapter = new Chapter(id, name, url, content);
                session.getChapterDao().insert(chapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public Chapter getChapter(Context context, String id) {
        session = GreenDaoHelper.getInstance().getDaoSession(context);
        List<Chapter> chapters = session.getChapterDao().loadAll();//TODO 如果不行，遍历
        Chapter chapter = session.getChapterDao().load(id);
        return chapter;
    }

    public void deleteAll(Context context) {
        session = GreenDaoHelper.getInstance().getDaoSession(context);
        session.getBookDao().deleteAll();//清空所有记录
    }


}
