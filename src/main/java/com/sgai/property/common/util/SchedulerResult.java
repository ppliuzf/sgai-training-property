package com.sgai.property.common.util;

import java.io.Serializable;

/**
 * Ajax处理结果对象
 * @FileName:Result.java
 * @Version: 1.0
 * @Author: liulianghui 
 * @CreateDate: 2015-10-01 上午11:21:28
 */
public class SchedulerResult implements Serializable {
	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = 5905715228490291386L;
	/**
	 * @fields status  状�?�信息，正确返回OK，否则返�? ERROR，如果返回ERROR则需要填写Message信息
	 */
	private Status status;
	/**
	 * @fields record 消息对象
	 */
	private Object message;
	
	private Object tipMsg;

	public SchedulerResult() {
		super();
	}

	/**
	 * @description 
	 * @param status 状�??
	 * @param message 消息
	 */
	public SchedulerResult(Status status, Object message) {
		this.status = status;
		this.message = message;
	}
	
	public SchedulerResult(Status status, Object message, Object tipMsg) {
		this.status = status;
		this.message = message;
		this.tipMsg=tipMsg;
	}

	/**
	 * 结果类型信息
	 * @author LiuJunGuang
	 * @date 2014�?3�?7日下�?4:20:23
	 */
	public enum Status {
		SUCCESS, ERROR
	}

	/**
	 * 添加成功结果信息
	 * @param record
	 */
	public void addSuccess(Object message) {
		this.message = message;
		this.status = Status.SUCCESS;
	}

	/**
	 * 添加错误消息
	 * @param message
	 */
	public void addError(Object message) {
		this.message = message;
		this.status = Status.ERROR;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getTipMsg() {
		return tipMsg;
	}

	public void setTipMsg(Object tipMsg) {
		this.tipMsg = tipMsg;
	}
	
	

}
