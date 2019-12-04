package com.briup.apps.cms.utils;
/**
 * 自定义异常
 * @author lenovo
 *
 */
public class CustomerException extends RuntimeException{
 

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	//编译时不需要try catch
	public CustomerException() 
	{}
	
	
	
	
	
}
