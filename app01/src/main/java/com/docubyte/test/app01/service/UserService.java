package com.docubyte.test.app01.service;

import com.docubyte.test.app01.beans.User;


public interface UserService {
    public String addUser(User user);
    public User searchUser(int id);
    public String updateUser(User user);
    public String deleteUser(int id);
}


