package com.loginproject.auth.service;

import com.loginproject.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
