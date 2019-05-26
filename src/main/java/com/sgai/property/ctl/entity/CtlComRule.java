package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * user关联rule队列Entity
 * @author chenxing
 * @version 2017-11-20
 */
public class CtlComRule extends BoEntity<CtlComRule> {
	
	private static final long serialVersionUID = 1L;
	private String sequCode;		// 	单据规则CODE
	private String sequName;		// 	单据规则名称
	private String comCode;		// 	租户
	private String comName;		// 	租户
	private String currentNo;		// 	当前序号
	private String currentCode;		// 	当前编码
	private String currentReset;	// 	当前重置依赖	
	private String enabledFlag;		// enabled_flag
	
	
	public CtlComRule() {
		super();
	}

	public CtlComRule(String id){
		super(id);
	}

	@Length(min=0, max=60, message="sequ_code长度必须介于 0 和 60 之间")
	public String getSequCode() {
		return sequCode;
	}

	public void setSequCode(String sequCode) {
		this.sequCode = sequCode;
	}
	
	@Length(min=0, max=60, message="sequ_name长度必须介于 0 和 60 之间")
	public String getSequName() {
		return sequName;
	}

	public void setSequName(String sequName) {
		this.sequName = sequName;
	}
	
	@Length(min=0, max=60, message="com_code长度必须介于 0 和 60 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=60, message="current_no长度必须介于 0 和 60 之间")
	public String getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(String currentNo) {
		this.currentNo = currentNo;
	}
	
	@Length(min=0, max=60, message="current_code长度必须介于 0 和 60 之间")
	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}
	
	@Length(min=0, max=60, message="current_rest长度必须介于 0 和 60 之间")
	public String getCurrentReset() {
		return currentReset;
	}

	public void setCurrentReset(String currentReset) {
		this.currentReset = currentReset;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
}