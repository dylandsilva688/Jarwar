package com.docubyte.test.app01.factory;

import com.docubyte.test.app01.dao.UserDao;
import com.docubyte.test.app01.dao.UserDaoImpl;


public class UserDaoFactory {
    private static final UserDao userDao;
    static {
        userDao = new UserDaoImpl();
    }


    public static UserDao getUserDao() {
        return userDao;
    }
    
}
