package com.tontisa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tontisa.lyboss.base.Result;

import com.tontisa.util.Exception;
import com.tontisa.util.ResultUtil;

@ControllerAdvice
public class ExceptionHandle{
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Handle(Exception e){

        if (e instanceof Exception){
        	Exception exception = (Exception) e;
            return ResultUtil.error(Integer.parseInt(exception.getCode()),exception.getMessage());
        }else {
        	//将系统异常以打印出来
            logger.info("[系统异常]{}",e);
            return ResultUtil.error(-1,"未知错误");
        }

    }
}
