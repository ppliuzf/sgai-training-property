package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.google.common.collect.Lists;

import com.sgai.common.persistence.BoEntity;

/**
 * 编码规则Entity
 * @author chenxing
 * @version 2017-11-20
 */
public class CtlRuleInfo extends BoEntity<CtlRuleInfo> {
	
	private static final long serialVersionUID = 1L;
	private String sequCode;		// 单据规则CODE
	private String sequName;		// 单据规则名称
	private String sequDelimiter;		// 分割符号
	private String sequReset;		// 序号重置规则
	private String isMultipleCom;		// 是否考虑多租户
	private String stepLength;		// 步长
	private String currentNo;		// 当前序号
	private String currentCode;		// 当前编码
	private String currentReset;		// 	当前重置依赖
	private String enabledFlag;		// 可用标识
	private List<CtlRuleItem> ctlRuleItemList = Lists.newArrayList();		// 子表列表
	
	public CtlRuleInfo() {
		super();
	}

	public CtlRuleInfo(String id){
		super(id);
	}

	@NotNull
	@Length(min=1, max=20, message="单据规则CODE填写错误!")
	public String getSequCode() {
		return sequCode;
	}

	public void setSequCode(String sequCode) {
		this.sequCode = sequCode;
	}
	
	@NotNull
	@Length(min=1, max=20, message="单据规则名称填写错误!")
	public String getSequName() {
		return sequName;
	}

	public void setSequName(String sequName) {
		this.sequName = sequName;
	}
	
	@Length(min=0, max=3, message="分割符号填写错误!")
	public String getSequDelimiter() {
		return sequDelimiter;
	}

	public void setSequDelimiter(String sequDelimiter) {
		this.sequDelimiter = sequDelimiter;
	}
	
	public String getSequReset() {
		return sequReset;
	}

	public void setSequReset(String sequReset) {
		this.sequReset = sequReset;
	}
	
	public String getIsMultipleCom() {
		return isMultipleCom;
	}

	public void setIsMultipleCom(String isMultipleCom) {
		this.isMultipleCom = isMultipleCom;
	}
	
	
	@Range(min=1, max=999, message="步长填写错误!")
	public String getStepLength() {
		return stepLength;
	}

	public void setStepLength(String stepLength) {
		this.stepLength = stepLength;
	}
	
	@Length(min=1, max=10, message="当前序号填写错误!")
	public String getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(String currentNo) {
		this.currentNo = currentNo;
	}
	
	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}
	
	@NotNull
	@Pattern(regexp="\\d{8}|\\d{6}|\\d{4}", message="当前重置依赖填写错误!")
	public String getCurrentReset() {
		return currentReset;
	}

	public void setCurrentReset(String currentReset) {
		this.currentReset = currentReset;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	
	public List<CtlRuleItem> getCtlRuleItemList() {
		return ctlRuleItemList;
	}

	public void setCtlRuleItemList(List<CtlRuleItem> ctlRuleItemList) {
		this.ctlRuleItemList = ctlRuleItemList;
	}
}