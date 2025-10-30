package com.docubyte.test.app01.factory;

import com.docubyte.test.app01.service.UserService;
import com.docubyte.test.app01.service.UserServiceImpl;


public class UserServiceFactory {
    private static final UserService userService;
    static{
        userService = new UserServiceImpl();
    }


    public static UserService getUserService() {
        return userService;
    }
}
