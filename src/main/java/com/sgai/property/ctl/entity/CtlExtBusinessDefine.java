package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 外部对接系统定义Entity
 * 
 * @author 李伟
 * @version 2018-02-07
 */
public class CtlExtBusinessDefine extends BoEntity<CtlExtBusinessDefine> {

	private static final long serialVersionUID = 1L;
	private String busiIcon; // 应用图标
	private String busiCode; // 系统代码
	private String busiName; // 系统名称
	private String busiDesc; // 描述
	private String busiUri; // busi_uri
	private String enabledFlag; // 1.选项为:'Y':是'N':否默认为'Y'

	public CtlExtBusinessDefine() {
		super();
	}

	public CtlExtBusinessDefine(String id) {
		super(id);
	}

	@Length(min = 0, max = 256, message = "应用图标长度必须介于 0 和 256 之间")
	public String getBusiIcon() {
		return busiIcon;
	}

	public void setBusiIcon(String busiIcon) {
		this.busiIcon = busiIcon;
	}

	@Length(min = 0, max = 60, message = "系统代码长度必须介于 0 和 60 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	@Length(min = 0, max = 128, message = "系统名称长度必须介于 0 和 128 之间")
	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	@Length(min = 0, max = 256, message = "描述长度必须介于 0 和 256 之间")
	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	@Length(min = 0, max = 128, message = "busi_uri长度必须介于 0 和 128 之间")
	public String getBusiUri() {
		return busiUri;
	}

	public void setBusiUri(String busiUri) {
		this.busiUri = busiUri;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}