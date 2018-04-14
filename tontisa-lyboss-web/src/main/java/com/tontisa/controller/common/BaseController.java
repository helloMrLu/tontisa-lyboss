package com.tontisa.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.tontisa.service.UserServer;


public class BaseController {
	@Autowired
	protected UserServer userServer;
	@Autowired
	protected StringRedisTemplate redisTemplate;
}
