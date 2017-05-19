package com.selectron.library.service.interfaces;

import com.selectron.library.model.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}
