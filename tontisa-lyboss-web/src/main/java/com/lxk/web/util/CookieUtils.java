package com.lxk.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.lxk.util.TokenUtils;
import com.tontisa.common.lang.Strings;

public class CookieUtils {

	/**
	 * 根据key获取cookie的值
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String key) {
		String value = null;
		if (StringUtils.isNotBlank(key) && null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 根据key获取cookie
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static Cookie getCookie(Cookie[] cookies, String key) {
		if (StringUtils.isNotBlank(key) && null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	/***
	 * 判断cookie是否存在
	 * 
	 * @param cookies
	 * @param key
	 * @return
	 */
	public static boolean existsCookie(Cookie[] cookies, String key) {
		if (StringUtils.isNotBlank(key) && null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/***
	 * 更新cookie中的过期时间
	 * 
	 * @param cookies
	 * @param key
	 * @param expiry
	 */
	public static void updateExpiry(HttpServletResponse response, Cookie[] cookies, String key, int expiry, String path, String domain) {
		if (StringUtils.isNotBlank(key) && null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					cookie.setMaxAge(expiry);
					if (Strings.isNotEmpty(path)) {
						cookie.setPath(path);
					}else{
						cookie.setPath("/");
					}
					if (Strings.isNotEmpty(domain)) {
						cookie.setDomain(domain);
					}
					response.addCookie(cookie);
				}
			}
		}
	}

	public static void set(HttpServletResponse response, String name, 
			String value,String domain, int tokenExpire) {
		 Cookie cookie = new Cookie(name, value);
		 if(Strings.isNotEmpty(domain)){
			 cookie.setDomain(domain);
		 }
	     cookie.setPath("/");
	     cookie.setMaxAge(tokenExpire);
	     response.addCookie(cookie);
	}

	 /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                           String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    /**
     * 将cookie封装成Map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
