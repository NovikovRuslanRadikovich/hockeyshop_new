package com.fujitsu.fs.services;

import com.fujitsu.fs.dao.UserDao;

public class UserServiceImpl implements UserService{


   private UserDao userDao;

   public UserServiceImpl(UserDao userDao)  {
       this.userDao = userDao;
   }


    public boolean isRegistered(String username,String password) {
        return userDao.isRegistered(username,password);
    }

    public void save(String username,String password) {
        userDao.save(username,password);
    }

    public void updatePassword(String username,String password) {userDao.updatePassword(username,password);}
}
