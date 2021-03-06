package com.zds.readeronline.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "BOOK".
 */
public class BookDao extends AbstractDao<Book, String> {

    public static final String TABLENAME = "BOOK";

    /**
     * Properties of entity Book.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookName = new Property(0, String.class, "BookName", true, "BOOK_NAME");
        public final static Property BookDes = new Property(1, String.class, "BookDes", false, "BOOk_DES");
        public final static Property BookURl = new Property(2, String.class, "BookURl", false, "BOOk_URL");
        public final static Property BookContent = new Property(3, String.class, "BookContent", false, "BOOk_CONTENT");
    }


    public BookDao(DaoConfig config) {
        super(config);
    }

    public BookDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK\" (" + //
                "\"BOOK_NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: BOOK_NAME
                "\"BOOk_DES\" TEXT," + // 1: BOOk_FENLEI
                "\"BOOk_URL\" TEXT," + // 2: BOOk_GENGXIN
                "\"BOOk_CONTENT\" TEXT);"); // 3: BOOk_URL
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Book entity) {
        stmt.clearBindings();

        String BookName = entity.getBookName();
        if (BookName != null) {
            stmt.bindString(1, BookName);
        }

        String BookDes = entity.getBookDes();
        if (BookDes != null) {
            stmt.bindString(2, BookDes);
        }

        String BookURl = entity.getBookURl();
        if (BookURl != null) {
            stmt.bindString(3, BookURl);
        }

        String BookContent = entity.getBookContent();
        if (BookContent != null) {
            stmt.bindString(4, BookContent);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Book entity) {
        stmt.clearBindings();

        String BookName = entity.getBookName();
        if (BookName != null) {
            stmt.bindString(1, BookName);
        }

        String BookDes = entity.getBookDes();
        if (BookDes != null) {
            stmt.bindString(2, BookDes);
        }

        String BookURl = entity.getBookURl();
        if (BookURl != null) {
            stmt.bindString(3, BookURl);
        }

        String BookContent = entity.getBookContent();
        if (BookContent != null) {
            stmt.bindString(4, BookContent);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }

    @Override
    public Book readEntity(Cursor cursor, int offset) {
        Book entity = new Book(
                cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // BookName
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // BookDes
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // BookURl
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // BookURl
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Book entity, int offset) {
        entity.setBookName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setBookDes(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBookURl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBookContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
    }

    @Override
    protected final String updateKeyAfterInsert(Book entity, long rowId) {
        return entity.getBookName();
    }

    @Override
    public String getKey(Book entity) {
        if (entity != null) {
            return entity.getBookName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Book entity) {
        return entity.getBookName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
