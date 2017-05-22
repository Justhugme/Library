package com.selectron.library.service.interfaces;

import com.selectron.library.model.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.ServletException;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);

    public void autologin(User user, HttpServletRequest request) throws ServletException;
}
