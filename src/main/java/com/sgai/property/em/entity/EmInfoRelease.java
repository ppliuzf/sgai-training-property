package com.sgai.property.em.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * @ClassName: EmInfoRelease  
    * @com.sgai.property.commonService.vo;(信息发布Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmInfoRelease extends BoEntity<EmInfoRelease> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// 流程ID
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "发布内容")
	private String releaseContent;		// 发布内容
	@ApiModelProperty(value = "发布渠道")
	private String release;		// 发布渠道
	@ApiModelProperty(value = "发布时间")
	private Date releaseTime;		// 发布时间
	@ApiModelProperty(value = "是否完成")
	private String endFlag;		// 未完成：N完成：Y
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
	
	public EmInfoRelease() {
		super();
	}

	public EmInfoRelease(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程ID长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=512, message="发布内容长度必须介于 0 和 512 之间")
	public String getReleaseContent() {
		return releaseContent;
	}

	public void setReleaseContent(String releaseContent) {
		this.releaseContent = releaseContent;
	}
	
	@Length(min=0, max=32, message="发布渠道长度必须介于 0 和 32 之间")
	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Length(min=0, max=1, message="未完成：N完成：Y长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
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