package com.sgai.property.wf.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
import com.sgai.common.utils.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: WfFlowDefine  
    * com.sgai.property.commonService.vo;(事件流程定义)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
public class WfFlowDefine extends BoEntity<WfFlowDefine> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "节点代码")
	private String stepCode;		// WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色
	@ApiModelProperty(value = "节点名称")
	private String stepName;		// step_name
	@ApiModelProperty(value = "事件类别")
	private String flowCode;		// 维修事件：WX投诉事件：TS安保事件：AB应急事件：YJ
	@ApiModelProperty(value = "节点位置")
	private String stepPos;		// ABCD
	@ApiModelProperty(value = "顺序号")
	private String stepSeq;		// 001
	@ApiModelProperty(value = "简称")
	private String stepNameShort;		// 简称
	@ApiModelProperty(value = "节点级别")
	private Integer stepLevel;		// 1：一级节点2：非一级节点
	@ApiModelProperty(value = "节点模板路径")
	private String stepUrl;		// 步骤业务处理需要挂接的URL表单，或逻辑处理代码。
	@ApiModelProperty(value = "是否开通")
	private String isFlag;		// is_flag
	@ApiModelProperty(value = "是否分发")
	private String isDistrFlag;		// 是否分发
	@ApiModelProperty(value = "是否互斥")
	private String isExclusiveFlag;		// 是否互斥
	@ApiModelProperty(value = "流程末节点")
	private String endFlag;		// 是否流程末节点：用来控制是否发货
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public WfFlowDefine() {
		super();
	}

	public WfFlowDefine(String id){
		super(id);
	}

	@Length(min=0, max=32, message="WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色长度必须介于 0 和 32 之间")
	@ExcelField(title="节点编号", align=1, sort=1)
	public String getStepCode() {
		return stepCode;
	}

	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	
	@Length(min=0, max=256, message="step_name长度必须介于 0 和 256 之间")
	@ExcelField(title="节点名称", align=1, sort=2)
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	@Length(min=0, max=32, message="维修事件：WX投诉事件：TS安保事件：AB应急事件：YJ长度必须介于 0 和 32 之间")
	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	
	@Length(min=0, max=32, message="ABCD长度必须介于 0 和 32 之间")
	public String getStepPos() {
		return stepPos;
	}

	public void setStepPos(String stepPos) {
		this.stepPos = stepPos;
	}
	
	@Length(min=0, max=32, message="001长度必须介于 0 和 32 之间")
	public String getStepSeq() {
		return stepSeq;
	}

	public void setStepSeq(String stepSeq) {
		this.stepSeq = stepSeq;
	}
	
	@Length(min=0, max=256, message="简称长度必须介于 0 和 256 之间")
	@ExcelField(title="简称", align=1, sort=3)
	public String getStepNameShort() {
		return stepNameShort;
	}

	public void setStepNameShort(String stepNameShort) {
		this.stepNameShort = stepNameShort;
	}
	
	public Integer getStepLevel() {
		return stepLevel;
	}

	public void setStepLevel(Integer stepLevel) {
		this.stepLevel = stepLevel;
	}
	
	@Length(min=0, max=256, message="步骤业务处理需要挂接的URL表单，或逻辑处理代码。长度必须介于 0 和 256 之间")
	@ExcelField(title="步骤处理逻辑", align=1, sort=8)
	public String getStepUrl() {
		return stepUrl;
	}

	public void setStepUrl(String stepUrl) {
		this.stepUrl = stepUrl;
	}
	
	@Length(min=0, max=1, message="is_flag长度必须介于 0 和 1 之间")
	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	
	@Length(min=0, max=1, message="是否分发长度必须介于 0 和 1 之间")
	@ExcelField(title="是否分发", align=1, sort=4)
	public String getIsDistrFlag() {
		return isDistrFlag;
	}

	public void setIsDistrFlag(String isDistrFlag) {
		this.isDistrFlag = isDistrFlag;
	}
	
	@Length(min=0, max=1, message="是否互斥长度必须介于 0 和 1 之间")
	@ExcelField(title="是否互斥", align=1, sort=5)
	public String getIsExclusiveFlag() {
		return isExclusiveFlag;
	}

	public void setIsExclusiveFlag(String isExclusiveFlag) {
		this.isExclusiveFlag = isExclusiveFlag;
	}
	
	@Length(min=0, max=1, message="是否流程末节点：用来控制是否发货长度必须介于 0 和 1 之间")
	@ExcelField(title="是否末节点", align=1, sort=7)
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	@ExcelField(title="是否开通", align=1, sort=6)
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}