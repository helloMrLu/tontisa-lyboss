package com.lxk.server.imp;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxk.constant.MagConstant;
import com.lxk.dao.UserDao;
import com.lxk.entity.User;
import com.lxk.service.UserServer;
import com.lxk.util.Exception;
import com.lxk.util.MyMD5;
import com.lxk.util.MyRandomUtils;
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
			back=userDao.getUserByPhoneOrNickNameOrRealName(user.getNickName(),user.getNickName(),user.getNickName());
		}
		return back;
	}

	/**
	 * 注册
	 */
	@Override
	public User registerUser(User u) {
		//查询是否已经注册
		User realUser=getUser(u);
		if(null != realUser){
			throw Exception.makeServiceException("20013");
		}
		//加密 带盐
		String salt = MyRandomUtils.getRandomString(MagConstant.SALT_LENGHT);
		String password = MyMD5.getMD5Code(u.getPassword() + salt);
		//设置加密盐和加密后的密码
		u.setSalt(salt);
		u.setPassword(password);
		//保存注册信息
		try {
			u=userDao.save(u);
		} catch (Exception e) {
			throw Exception.makeServiceException("10019");
		}
		return u;
	}
}
