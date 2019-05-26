package com.sgai.property.wy.support;
/**
 * 客户端请求返回信息.
 * @author ppliu
 *
 */

import net.sf.json.JSONObject;

import java.util.List;

public class ResponseResult {
	/**返回信息**/
	private String message;
	private Object data;
	/**是否成功**/
	private boolean success = true;
	/**返回信息**/
	private List<?> list;
	/**构造器**/
	public ResponseResult() {
		// 无参构造
	}
	
	public ResponseResult(List<?> list) {
		super();
		this.list = list;
	}

	/**构造器**/
	public ResponseResult(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	/**构造器**/
	public ResponseResult(String message, boolean success, List<?> list) {
		super();
		this.message = message;
		this.success = success;
		this.list = list;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		//
		return JSONObject.fromObject(this).toString();
	}
}
