package com.sgai.property.quality.vo.toon;

import com.sgai.property.commonService.vo.toon.InstanceMessageContent;
import com.sgai.property.commonService.vo.toon.ToonAuthInfoReq;

import java.io.Serializable;

public class SendInstanceMessageReq extends ToonAuthInfoReq implements Serializable {

	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5486170805764420337L;

    private String msgid;
    
    private String from;
    
    private String to;
    
    private String toClient;
    
    private String pushInfo;
    
    private InstanceMessageContent content;
    
    private String msgType;
    
    private String priority;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToClient() {
		return toClient;
	}

	public void setToClient(String toClient) {
		this.toClient = toClient;
	}

	public String getPushInfo() {
		return pushInfo;
	}

	public void setPushInfo(String pushInfo) {
		this.pushInfo = pushInfo;
	}

	public InstanceMessageContent getContent() {
		return content;
	}

	public void setContent(InstanceMessageContent content) {
		this.content = content;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
