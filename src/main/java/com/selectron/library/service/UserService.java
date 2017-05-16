package com.selectron.library.service;

import com.selectron.library.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
