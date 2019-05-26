package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

/**
 * 编码规则Entity
 * @author chenxing
 * @version 2017-11-20
 */
public class CtlRuleItem extends BoEntity<CtlRuleItem> {
	
	private static final long serialVersionUID = 1L;
	private String sequCode;		// sequ_code 父类
	private String ruleCode;		// 编码规则CODE
	private String ruleName;		// 编码规则名称
	private String ruleType;		// 类别 const,numbering,timestamp
	private String ruleValue;		// 参数值
	private String paddingSide;		// 	补齐方向
	private String paddingWidth;		// 补齐宽度
	private String paddingChar;		// 填充字符
	private String ruleOrder;		// 	排序
	private String enabledFlag;		// 可用标识
	
	public CtlRuleItem() {
		super();
	}

	public CtlRuleItem(String id){
		super(id);
	}

	public CtlRuleItem(CtlRuleInfo ctlRuleInfo){
		this.sequCode = ctlRuleInfo.getSequCode();
	}

	public String getSequCode() {
		return sequCode;
	}

	public void setSequCode(String sequCode) {
		this.sequCode = sequCode;
	}
	
	@Length(min=0, max=60, message="rule_code长度必须介于 0 和 60 之间")
	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	
	@Length(min=0, max=60, message="rule_name长度必须介于 0 和 60 之间")
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	@Length(min=0, max=60, message="rule_order长度必须介于 0 和 60 之间")
	public String getRuleOrder() {
		return ruleOrder;
	}

	public void setRuleOrder(String ruleOrder) {
		this.ruleOrder = ruleOrder;
	}
	
	@Length(min=0, max=60, message="rule_type长度必须介于 0 和 60 之间")
	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	@Length(min=0, max=60, message="rule_value长度必须介于 0 和 60 之间")
	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	@Length(min=0, max=60, message="padding_side长度必须介于 0 和 60 之间")
	public String getPaddingSide() {
		return paddingSide;
	}

	public void setPaddingSide(String paddingSide) {
		this.paddingSide = paddingSide;
	}
	
	@Length(min=0, max=60, message="padding_width长度必须介于 0 和 60 之间")
	public String getPaddingWidth() {
		return paddingWidth;
	}

	public void setPaddingWidth(String paddingWidth) {
		this.paddingWidth = paddingWidth;
	}
	
	@Length(min=0, max=60, message="padding_char长度必须介于 0 和 60 之间")
	public String getPaddingChar() {
		return paddingChar;
	}

	public void setPaddingChar(String paddingChar) {
		this.paddingChar = paddingChar;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	

}