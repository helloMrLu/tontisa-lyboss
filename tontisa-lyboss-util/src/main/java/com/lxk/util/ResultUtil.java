package com.lxk.util;

import com.lxk.base.Result;

public class ResultUtil {
	 public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setExecuteStatus(0);
        result.setMsg("success");
        result.setData(object);
        return result;
	 }

	 public static Result success() {
        return success(null);
	 }

	 public static Result error(Integer i, String msg) {
        Result result = new Result();
        result.setCode(i);
        result.setMsg(msg);
        result.setExecuteStatus(1);
        return result;
	 }

}
