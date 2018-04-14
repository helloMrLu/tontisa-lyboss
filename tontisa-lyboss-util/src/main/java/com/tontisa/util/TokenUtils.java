package com.tontisa.util;

/**
 * token工具
 * @author renlei
 *
 */
public class TokenUtils {
	//api ajax调用请求token在请求头的名称
	public  static final String TOKEN_NAME = "access-token";
	//token前缀 lyBoss:tk:
	public static final String TOKEN_PREFIX = "lyBoss:tk";
	//token过期时间 30分钟
	public static final int TOKEN_EXPIRE = 1800;
	
	/**
	 * 获取用户关联的token key
	 * @param erpId
	 * @param admId
	 * @return
	 */
	public static String getMagUserTokenCacheKey(String userId){
		StringBuilder userTokenBuilder = new StringBuilder();
		userTokenBuilder.append(TOKEN_PREFIX).append(":").append(userId);
		return userTokenBuilder.toString();
	}
}
