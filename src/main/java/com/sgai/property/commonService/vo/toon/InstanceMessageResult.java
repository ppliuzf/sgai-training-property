package com.sgai.property.commonService.vo.toon;

import java.io.Serializable;

public class InstanceMessageResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6655250975884847428L;
	
	private MessageAck msgAck;

	public MessageAck getMsgAck() {
		return msgAck;
	}

	public void setMsgAck(MessageAck msgAck) {
		this.msgAck = msgAck;
	}

}
