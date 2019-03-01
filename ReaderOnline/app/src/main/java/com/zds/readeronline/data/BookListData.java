package com.zds.readeronline.data;

import com.zds.readeronline.database.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 76933 on 2018/6/2.
 */

public class BookListData {
    private HashMap<String, Book> mBooks = new HashMap<>();
    private List<String> mBookNameList = new ArrayList<>();

    public void addBook(String name, Book book) {
        mBookNameList.add(name);
        mBooks.put(name, book);
    }

    public Book getBook(String name) {
        return mBooks.get(name);
    }

    public List<String> getBookNameList() {
        return mBookNameList;
    }

    public void setBookNameList(List<String> mBookNameList) {
        this.mBookNameList = mBookNameList;
    }
}
