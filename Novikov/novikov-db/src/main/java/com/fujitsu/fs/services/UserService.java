package com.fujitsu.fs.services;

public interface UserService {

    boolean isRegistered(String username, String password);

    void save(String username, String password);

    void updatePassword(String username, String newPassword);
}
