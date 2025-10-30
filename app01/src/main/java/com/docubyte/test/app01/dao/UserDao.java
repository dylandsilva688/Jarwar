package com.docubyte.test.app01.dao;

import com.docubyte.test.app01.beans.User;


public interface UserDao {
    public String add(User user);
    public User search(int id);
    public String update(User user);
    public String delete(int user);
}
