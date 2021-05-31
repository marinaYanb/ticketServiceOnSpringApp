package com.example.app.service;

import com.example.app.model.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findAllUsers();

    User createUser(User user);

    void updateUser(User user);
}
