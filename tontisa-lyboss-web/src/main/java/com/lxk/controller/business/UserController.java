package com.lxk.controller.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxk.base.Result;
import com.lxk.controller.common.BaseController;
import com.lxk.entity.User;
import com.lxk.util.Exception;
import com.lxk.util.ResultUtil;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	


	
	@RequestMapping("/getInfo/{id}")
	public Result getInfo(@PathVariable Long id){
		String s=this.config.getAccessKey();
		String s1=this.config.getDefaultBucket();
		String s2=this.config.getDomain();
		String s3=this.config.getSecretKey();
		User u=userServer.getInfo(id);
		if(u==null){
			throw Exception.makeServiceException("10001");
		}
		return ResultUtil.success(u);
	}
}
