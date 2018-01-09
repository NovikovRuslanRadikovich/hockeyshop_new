package com.fujitsu.fs.rnovikov.services;

import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.UserDao;
import com.fujitsu.fs.rnovikov.entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 06.01.2018.
 */
public class UserService implements Service<User> {

    UserDao<User> myDao;

    public UserService(UserDao<User> userDao) {
        myDao = userDao;
    }

    public boolean isRegistred(String username)  {


        List<User> allUsers = null;
        try {
            allUsers = myDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(User user2 : allUsers) {
            String user2name = user2.getName();
            if (user2name.equals(username)) {
                return true;
            }
        }
        return false;
    }
}
