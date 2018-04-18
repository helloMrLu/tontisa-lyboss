package com.lxk.server.imp;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxk.dao.UserDao;
import com.lxk.entity.User;
import com.lxk.service.UserServer;
@Service
public class UserServerImp implements UserServer{
	@Autowired
    private UserDao userDao;

	@Override
	public User getInfo(Long id) {
		User u=userDao.findUserById(id);
		if(u==null){
			u=new User();
		}
		return u;
	}

	@Override
	public User getUser(User user) {
		User back = null;
		if(!Strings.isEmpty(user.getPhone())){
			back=userDao.getUserByPhone(user.getPhone());
		}else if(!Strings.isEmpty(user.getNickName())){
			back=userDao.getUserByPhoneOrNickNameOrRealName(user.getPhone(),user.getNickName(),user.getRealName());
		}
		return back;
	}
}
