package com.tontisa.util;

import org.springframework.beans.BeanUtils;


public class JsonUtil {

    public static Object ObjtoJson(Object object,Object object2) {
    	if(object!=null){
    		BeanUtils.copyProperties(object,object2);
    	}
        return object2;
    }
}
