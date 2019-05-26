package com.sgai.property.quality.vo.toon;

import com.sgai.property.commonService.vo.toon.InstanceMessageResult;

import java.io.Serializable;

public class SendInstanceMessageResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6602807312469486812L;
	
	private Integer status;
	
	private String message;
	
	private InstanceMessageResult result;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InstanceMessageResult getResult() {
		return result;
	}

	public void setResult(InstanceMessageResult result) {
		this.result = result;
	}

}
