package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 
    * ClassName: WfInstanceRecord  
    * Description: (事件流程实例)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
public class WfInstanceRecord extends BoEntity<WfInstanceRecord> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "实例主键")
	private String instanceId;		// 流程实例主键
	@ApiModelProperty(value = "事件编码")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "书序号")
	private String stepSeq;		// 10111220
	@ApiModelProperty(value = "节点代码")
	private String stepCode;		// WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色
	@ApiModelProperty(value = "节点名称")
	private String stepName;		// step_name
	@ApiModelProperty(value = "处理路径")
	private String stepUrl;		// 步骤业务处理需要挂接的URL表单，或逻辑处理代码。
	@ApiModelProperty(value = "节点级别")
	private String stepLevel;		// 节点层级：1
	@ApiModelProperty(value = "节点顺序号")
	private String stepType;		// A:B:C:
	@ApiModelProperty(value = "执行状态")
	private String stepStat;		// 默认：0（初始化）接收：1处理：2转发：3
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "执行人代码")
	private String userCode;		// 执行人代码
	@ApiModelProperty(value = "执行人")
	private String userName;		// 执行人
	public WfInstanceRecord() {
		super();
	}

	public WfInstanceRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例主键长度必须介于 0 和 64 之间")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	@Length(min=0, max=32, message="对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等长度必须介于 0 和 32 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=32, message="保修事件投诉事件报警事件长度必须介于 0 和 32 之间")
	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}
	
	@Length(min=0, max=32, message="10111220长度必须介于 0 和 32 之间")
	public String getStepSeq() {
		return stepSeq;
	}

	public void setStepSeq(String stepSeq) {
		this.stepSeq = stepSeq;
	}
	
	@Length(min=0, max=32, message="WX-A-001:水工角色WX-A-002:木工角色WX-A-003:电工角色长度必须介于 0 和 32 之间")
	public String getStepCode() {
		return stepCode;
	}

	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	
	@Length(min=0, max=32, message="step_name长度必须介于 0 和 32 之间")
	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	
	@Length(min=0, max=32, message="节点层级：1长度必须介于 0 和 32 之间")
	public String getStepLevel() {
		return stepLevel;
	}

	public void setStepLevel(String stepLevel) {
		this.stepLevel = stepLevel;
	}
	
	@Length(min=0, max=32, message="A:B:C:长度必须介于 0 和 32 之间")
	public String getStepType() {
		return stepType;
	}

	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
	@Length(min=0, max=32, message="默认：0（初始化）接收：1处理：2转发：3长度必须介于 0 和 32 之间")
	public String getStepStat() {
		return stepStat;
	}

	public void setStepStat(String stepStat) {
		this.stepStat = stepStat;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	/**
	 * stepUrl.
	 *
	 * @return  the stepUrl
	 * @since   JDK 1.8
	 */
	public String getStepUrl() {
		return stepUrl;
	}

	/**
	 * stepUrl.
	 *
	 * @param   stepUrl    the stepUrl to set
	 * @since   JDK 1.8
	 */
	public void setStepUrl(String stepUrl) {
		this.stepUrl = stepUrl;
	}

	/**
	 * userCode.
	 *
	 * @return  the userCode
	 * @since   JDK 1.8
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * userCode.
	 *
	 * @param   userCode    the userCode to set
	 * @since   JDK 1.8
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * userName.
	 *
	 * @return  the userName
	 * @since   JDK 1.8
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * userName.
	 *
	 * @param   userName    the userName to set
	 * @since   JDK 1.8
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}