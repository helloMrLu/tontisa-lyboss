package com.tontisa.service;

import com.tontisa.entity.User;

public interface UserServer {
	public User getInfo(Long id);

	public User getUser(User user);
}
