package com.sgai.property.quality.vo.toon;

import java.io.Serializable;

public class MessageAck implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 204372794730605159L;
	
	private String msgid;
	
	private String seqid;
	
	private String from;
	
	private String to;

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSeqid() {
		return seqid;
	}

	public void setSeqid(String seqid) {
		this.seqid = seqid;
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

}
