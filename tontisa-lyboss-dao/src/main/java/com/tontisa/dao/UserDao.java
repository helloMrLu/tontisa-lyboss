package com.tontisa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tontisa.entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	public User findUserById(Long id);

	public User getUserByPhone(String phone);


	public User getUserByPhoneOrNickNameOrRealName(String phone, String nickName, String realName);
}
