package com.lxk.service;

import com.lxk.entity.User;

public interface UserServer {
	public User getInfo(Long id);

	public User getUser(User user);
}
