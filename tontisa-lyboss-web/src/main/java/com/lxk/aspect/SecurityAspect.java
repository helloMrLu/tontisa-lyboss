package com.lxk.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lxk.constant.MagConstant;
import com.lxk.controller.authority.MagUserInfoHolder;
import com.lxk.entity.User;
import com.lxk.util.Exception;
import com.lxk.util.TokenUtils;
import com.lxk.web.util.CookieUtils;

import net.sf.json.JSONObject;


@Aspect
@Order(-99)
@Component 
public class SecurityAspect {
	private final Logger log = LoggerFactory.getLogger(getClass());
	 @Autowired
	 private StringRedisTemplate redisTemplate;
	 
    @Pointcut("execution(public * com.lxk.controller.*.*.*(..))" +
    "&& !execution(public * com.lxk.controller.authority.*.*(..))")
    public void verify() {
    	System.out.println("11111111111111111111111111111");
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("111111111111111111111111111112");
       /* //查询cookie
        Cookie cookie = CookieUtils.get(request,MagConstant.TOKEN_NAME);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw Exception.makeServiceException("20008");
        }

        //去redis里查询
    	StringBuilder tokenNoBuilder = new StringBuilder();
        String finalToken = tokenNoBuilder.append(TokenUtils.TOKEN_PREFIX).append(":").append(cookie.getValue()).toString();
        String tokenValue = redisTemplate.opsForValue().get(finalToken);
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw Exception.makeServiceException("20007");
        }
        putUserInfo(tokenValue); */
    }

	/**
	 * @param tokenValue
	 */
	private void putUserInfo(String tokenValue) {
		JSONObject o=JSONObject.fromObject(tokenValue);
        User u=new User();
        u.setId(o.getLong("id"));
        u.setNickName(o.getString("nickName"));
        u.setCompany(o.getString("company"));
        u.setPhone(o.getString("phone"));
        MagUserInfoHolder.setCurrentMagUser(u);
	}
}
