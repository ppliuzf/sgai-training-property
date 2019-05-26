
package com.sgai.property.ctl.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 消息通知Entity
 * @author admin
 * @version 2018-06-15
 */
public class CtlNotice extends BoEntity<CtlNotice> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 接收人代码
	private String noticeInfo;		// 消息内容
	private Date noticeTime;		// 推送时间
	private String enabledFlag;		// 是否可用
	private String readFlag;        //已读/未读
	public CtlNotice() {
		super();
	}

	public CtlNotice(String id){
		super(id);
	}

	@Length(min=1, max=20, message="user_code长度必须介于 1 和 20 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=512, message="notice_info长度必须介于 0 和 512 之间")
	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
}