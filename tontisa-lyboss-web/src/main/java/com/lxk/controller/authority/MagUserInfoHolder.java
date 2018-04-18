package com.lxk.controller.authority;

import com.lxk.entity.User;

public abstract class MagUserInfoHolder {
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<User>();
    
    public static User getCurrentMagUser() {
        return threadLocal.get();
    }

    public static void setCurrentMagUser(final User magUser) {
        threadLocal.set(magUser);
    }

    public static void clear() {
        threadLocal.set(null);
    }
    
    /**
     * 获得当前同业用户id
     * 如果是系统内部调用，不是通过登录方式是无法获取id的，必须通过请求参数方式
     * @return
     */
    public static Long getCurrentId() {
    	if (getCurrentMagUser() != null) {
    		return getCurrentMagUser().getId();
		}else{
			return null;
		}
    }
}
