package com.tontisa.util;

public class Exception extends RuntimeException{
	private String code;

    public Exception(String code) {
        super(Error.code(code));
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Exception makeServiceException(String code){
    	return new Exception(code);
    }
}
