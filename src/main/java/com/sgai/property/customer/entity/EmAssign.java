package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
    * @ClassName: EmAssign  
    * @Description: (事件指派Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmAssign extends BoEntity<EmAssign> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "处理人")
	private String procPerson;		// 处理人
	@ApiModelProperty(value = "指派时间")
	private Date assignDatetime;		// 指派时间
	@ApiModelProperty(value = "说明")
	private String assignDesc;		// 说明
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "是否完成")
	private String endFlag;		// 未完成：N完成：Y
	
	
	//页面使用
	@ApiModelProperty(value = "指派人")
	private String assignPerson;  // 指派人
	
	public EmAssign() {
		super();
	}

	public EmAssign(String id){
		super(id);
	}

	@Length(min=0, max=64, message="instance_id长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=60, message="处理人长度必须介于 0 和 60 之间")
	public String getProcPerson() {
		return procPerson;
	}

	public void setProcPerson(String procPerson) {
		this.procPerson = procPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAssignDatetime() {
		return assignDatetime;
	}

	public void setAssignDatetime(Date assignDatetime) {
		this.assignDatetime = assignDatetime;
	}
	
	@Length(min=0, max=60, message="说明长度必须介于 0 和 60 之间")
	public String getAssignDesc() {
		return assignDesc;
	}

	public void setAssignDesc(String assignDesc) {
		this.assignDesc = assignDesc;
	}
	
	@Length(min=0, max=60, message="选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=1, message="未完成：N完成：Y长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	@Length(min=0, max=60, message="处理人长度必须介于 0 和 60 之间")
	public String getAssignPerson() {
		return assignPerson;
	}

	public void setAssignPerson(String assignPerson) {
		this.assignPerson = assignPerson;
	}
	
}