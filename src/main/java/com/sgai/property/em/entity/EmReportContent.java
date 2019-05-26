package com.sgai.property.em.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
    * @ClassName: EmReportContent  
    * @com.sgai.property.commonService.vo;(报案内容Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmReportContent extends BoEntity<EmReportContent> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "业务主键id")
	private String stepRecordId;		// 业务主键id
	@ApiModelProperty(value = "文字")
	private String reportContent;		// 文字
	@ApiModelProperty(value = "图片")
	private String reportPic;		// 图片
	@ApiModelProperty(value = "视频")
	private String reportVideo;		// 视频
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
	
	public EmReportContent() {
		super();
	}

	public EmReportContent(String id){
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
	
	@Length(min=0, max=60, message="业务主键id长度必须介于 0 和 60 之间")
	public String getStepRecordId() {
		return stepRecordId;
	}

	public void setStepRecordId(String stepRecordId) {
		this.stepRecordId = stepRecordId;
	}
	
	@Length(min=0, max=256, message="文字长度必须介于 0 和 256 之间")
	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	
	@Length(min=0, max=256, message="图片长度必须介于 0 和 256 之间")
	public String getReportPic() {
		return reportPic;
	}

	public void setReportPic(String reportPic) {
		this.reportPic = reportPic;
	}
	
	@Length(min=0, max=256, message="视频长度必须介于 0 和 256 之间")
	public String getReportVideo() {
		return reportVideo;
	}

	public void setReportVideo(String reportVideo) {
		this.reportVideo = reportVideo;
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