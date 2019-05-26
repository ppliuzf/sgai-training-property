package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 
    * @ClassName: EmConfirm  
    * @Description: (事件核实Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmConfirm extends BoEntity<EmConfirm> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "核实人")
	private String confirmPerson;		// 核实人
	@ApiModelProperty(value = "核实时间")
	private  Date confirmDate;		// 核实时间
	@ApiModelProperty(value = "核实内容")
	private String confirmContent;		// 核实内容
	@ApiModelProperty(value = "是否上报")
	private String isReport;		// 是否上报
	@ApiModelProperty(value = "预案选择")
	private String choosePlan;		// 预案选择
	@ApiModelProperty(value = "是否完成")
	private String endFlag;		// 未完成：N完成：Y
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号
	@ApiModelProperty(value = "修改时间")
	private Date updatedDt;		// 修改时间
	@ApiModelProperty(value = "修改人")
	private String updatedBy;		// 修改人
	@ApiModelProperty(value = "创建日期")
	private Date createdDt;		// 创建日期
	@ApiModelProperty(value = "创建者")
	private String createdBy;		// 创建者

	
	public EmConfirm() {
		super();
	}

	public EmConfirm(String id){
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
	
	@Length(min=0, max=60, message="核实人长度必须介于 0 和 60 之间")
	public String getConfirmPerson() {
		return confirmPerson;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	@Length(min=0, max=60, message="核实内容长度必须介于 0 和 60 之间")
	public String getConfirmContent() {
		return confirmContent;
	}

	public void setConfirmContent(String confirmContent) {
		this.confirmContent = confirmContent;
	}
	
	@Length(min=0, max=60, message="是否上报长度必须介于 0 和 60 之间")
	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}
	
	@Length(min=0, max=60, message="预案选择长度必须介于 0 和 60 之间")
	public String getChoosePlan() {
		return choosePlan;
	}

	public void setChoosePlan(String choosePlan) {
		this.choosePlan = choosePlan;
	}
	
	@Length(min=0, max=1, message="未完成：N完成：Y长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	@Length(min=0, max=1, message="选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}