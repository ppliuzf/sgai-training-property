package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 
    * @ClassName: EmProcess  
    * @Description: (事件处理(含更新)Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmProcess extends BoEntity<EmProcess> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "办理人")
	private String procPerson;		// 办理人
	@ApiModelProperty(value = "维修内容")
	private String repairContent;		// 维修内容
	@ApiModelProperty(value = "维修时间")
	private Date repairDatetime;		// 维修时间
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
	
	
	@ApiModelProperty(value = "处理人")
	private String handlePerson;		// 处理人
	@ApiModelProperty(value = "处置结果")
	private String handleContent;		// 处置结果
	@ApiModelProperty(value = "处理时间")
	private Date handleDatetime;		// 处理时间
	
	@ApiModelProperty(value = "回访人")
	private String visitPerson;		// 回访人
	@ApiModelProperty(value = "回访内容")
	private String visitContent;	// 回访内容
	@ApiModelProperty(value = "回访时间")
	private Date visitDatetime;		// 回访时间
	
	public EmProcess() {
		super();
	}

	public EmProcess(String id){
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
	
	@Length(min=0, max=60, message="办理人长度必须介于 0 和 60 之间")
	public String getProcPerson() {
		return procPerson;
	}

	public void setProcPerson(String procPerson) {
		this.procPerson = procPerson;
	}
	
	@Length(min=0, max=60, message="维修内容长度必须介于 0 和 60 之间")
	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairDatetime() {
		return repairDatetime;
	}

	public void setRepairDatetime(Date repairDatetime) {
		this.repairDatetime = repairDatetime;
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
	
	@Length(min=0, max=60, message="办理人长度必须介于 0 和 60 之间")
	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	@Length(min=0, max=60, message="维修内容长度必须介于 0 和 60 之间")
	public String getHandleContent() {
		return handleContent;
	}

	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHandleDatetime() {
		return handleDatetime;
	}

	public void setHandleDatetime(Date handleDatetime) {
		this.handleDatetime = handleDatetime;
	}

	@Length(min=0, max=60, message="办理人长度必须介于 0 和 60 之间")
	public String getVisitPerson() {
		return visitPerson;
	}

	public void setVisitPerson(String visitPerson) {
		this.visitPerson = visitPerson;
	}

	@Length(min=0, max=60, message="维修内容长度必须介于 0 和 60 之间")
	public String getVisitContent() {
		return visitContent;
	}

	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVisitDatetime() {
		return visitDatetime;
	}

	public void setVisitDatetime(Date visitDatetime) {
		this.visitDatetime = visitDatetime;
	}
}