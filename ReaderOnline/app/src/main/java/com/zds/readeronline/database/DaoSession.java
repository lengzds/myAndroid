package com.zds.readeronline.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig areaDaoConfig;
    private final DaoConfig peopleDaoConfig;

    private final BookDao bookDao;
    private final ChapterDao chapterDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        areaDaoConfig = daoConfigMap.get(BookDao.class).clone();
        areaDaoConfig.initIdentityScope(type);

        peopleDaoConfig = daoConfigMap.get(ChapterDao.class).clone();
        peopleDaoConfig.initIdentityScope(type);


        bookDao = new BookDao(areaDaoConfig, this);
        chapterDao = new ChapterDao(peopleDaoConfig, this);

        registerDao(Book.class, bookDao);
        registerDao(Chapter.class, chapterDao);
    }

    public void clear() {
        areaDaoConfig.clearIdentityScope();
        peopleDaoConfig.clearIdentityScope();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public ChapterDao getChapterDao() {
        return chapterDao;
    }

}
