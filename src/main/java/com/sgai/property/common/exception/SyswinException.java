package com.sgai.property.common.exception;

/**
 * <pre>异常类</pre>
 * 
 * 对于接口调用时将在以下情况抛出该异常：
 * <ul>
 * 	<li>请求连接失败</li>
 *  <li>若使用https协议,证书加载失败或证书验证失败</li>
 *  <li>请求成功，但返回状态码不为200</li>
 * </ul>
 * @author 144796
 *
 */
public class SyswinException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123753447396412979L;

	public SyswinException(){
		super();
	}
	
	public SyswinException(String message){
		super(message);
	}
	
	public SyswinException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public SyswinException(Throwable cause) {
        super(cause);
    }
	
	protected SyswinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
