package com.sgai.property.wf.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
/**
 * 流程权限Entity
 * @author 王天尧
 * @version 2017-12-05
 */
public class WfUserRight extends BoEntity<WfUserRight> {
	
	private static final long serialVersionUID = 1L;
	private String corrCode;		// 角色/用户对应代码
	private String category;		// R:表示角色U:表示用户
	private String stepCode;		// WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public WfUserRight() {
		super();
	}

	public WfUserRight(String id){
		super(id);
	}

	@Length(min=1, max=30, message="角色/用户对应代码长度必须介于 1 和 30 之间")
	public String getCorrCode() {
		return corrCode;
	}

	public void setCorrCode(String corrCode) {
		this.corrCode = corrCode;
	}
	
	@Length(min=1, max=1, message="R:表示角色U:表示用户长度必须介于 1 和 1 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=32, message="WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色长度必须介于 0 和 32 之间")
	public String getStepCode() {
		return stepCode;
	}

	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}