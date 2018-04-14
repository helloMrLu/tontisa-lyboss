package com.tontisa.constant;

public class MagConstant {

	public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// Cookie过期时间
	public static int cookieExpiry = 60 * 60;
	public static int redisExpiry = 12 * 60 * 60;
	
	//cookie域名后缀
	//public static String COOKIE_DOMAIN = ".op110.com.cn" ;
	
	//正则表达式
	public static String EMAIL_METCH = "^\\w+@(\\w+.)+[a-z]{2,3}$" ;//验证邮箱格式的正则表达式
	public static String MOBILE_METCH = "^[1][3,4,5,8,7][0-9]{9}$"; //验证手机号码格式的正则表达式
	
	public static String LOGIN_COOKIE_COUNT_NAME = "ttx_user_login_count" ;
	public static int LOGIN_FAIL_NUMBER = 2 ;
	
	//验证码长度
	public static Integer CODE_LENGHT_4 = 4 ;
	public static Integer CODE_LENGHT_6 = 6 ;
	public static Integer CODE_LENGHT_8 = 8 ;
	
	public static String MOBILE_TYPE_NAME = "mobile" ;
	public static String EMAIL_TYPE_NAME = "email" ;
	public static String MAG_SECRET_KEY = "ttx_mag_line_secret_key" ;//密钥
	
	//验证码的有效时间/30分钟
	public static Integer MOBILE_CODE_ACTIVE_TIME = 30 * 60;
	public static Integer EMAIL_CODE_ACTIVE_TIME = 30 * 60 ;
	public static final int VALIDATE_CODE_ACTIVE_TIME = 30 * 60;
	public static final int TOKEN_ACTIVE_TIME= 120 * 60;
	
	/**
	 * 盐的长度
	 */
	public static int SALT_LENGHT = 8;
	
	public static int RESET_PASSWORD_MOBILE_CODE_TIME = 30 * 60 ;
	public static final int RESET_PASSWORD_EMAIL_ACTION_TIME = 1 * 60 * 60;
	public static int RESET_PASSWORD_MOBILE_CODE_LENGHT = 8;
	
	//加密多少次
	public static int BASE64_ENCODE_NUM = 3;
	
	//存放登录成功之后,转跳到上一个页面的cookie值
	public static final String fallbackUrl = "ttx_fallback_url";

	public static final String MAG_ERP_AUTH = "op114.erp.redirect.from:";
	
	//当前用户打开网站的session id
	public static String MAG_SESSION_ID = "lyboss_session_id";
	//登录标识key
	public static String TOKEN_NAME = "lyboss_login_id";
	
	//绑定
	public static String MAG_auth_ID = "auth";
	
	
	//注册来源
	/**
	 * 网站注册
	 */
	public static final Short REG_TYPE_SITE = 1;
	/**
	 * 移动端注册
	 */
	public static final Short REG_TYPE_MOBILE = 2;
	/**
	 * ERP注册
	 */
	public static final Short REG_TYPE_ERP = 3;
}
