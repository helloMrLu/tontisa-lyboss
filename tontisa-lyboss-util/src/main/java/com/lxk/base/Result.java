package com.lxk.base;
public class Result<T> {
	 	/** 错误码. */
	   private Integer code;
	
	   /** 提示信息. */
	   private String msg;
	
	   /** 具体的内容. */
	   private T data;
	
	   public Integer getCode() {
	       return code;
	   }
		/** 状态. */
	   private Integer executeStatus;
	   public void setCode(Integer code) {
	       this.code = code;
	   }
	
	   public String getMsg() {
	       return msg;
	   }
	
	   public void setMsg(String msg) {
	       this.msg = msg;
	   }
	
	   public T getData() {
	       return data;
	   }
	
	   public void setData(T data) {
	       this.data = data;
	   }

		public Integer getExecuteStatus() {
			return executeStatus;
		}
	
		public void setExecuteStatus(Integer executeStatus) {
			this.executeStatus = executeStatus;
		}

}
