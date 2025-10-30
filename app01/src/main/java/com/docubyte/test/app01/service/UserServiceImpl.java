package com.docubyte.test.app01.service;

import com.docubyte.test.app01.beans.User;
import com.docubyte.test.app01.dao.UserDao;
import com.docubyte.test.app01.factory.UserDaoFactory;

public class UserServiceImpl implements UserService {


        @Override
        public String addUser(User user) {
            UserDao userDao = UserDaoFactory.getUserDao();
            String status = userDao.add(user);
            return status;
        }


        @Override
        public User searchUser(int id) {
            UserDao userDao = UserDaoFactory.getUserDao();
            User user = userDao.search(id);
            return user;
        }


        @Override
        public String updateUser(User user) {
            UserDao userDao = UserDaoFactory.getUserDao();
            String status = userDao.update(user);
            return status;
        }


        @Override
        public String deleteUser(int id) {
            UserDao userDao = UserDaoFactory.getUserDao();
            String status = userDao.delete(id);
            return status;
        }
}
