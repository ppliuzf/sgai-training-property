package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgai.property.common.util.QtBaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlTransient;

/**
 * 检验成果
 * 
 * @author xuxj
 *
 */
public class TaskResultVo extends QtBaseEntity<TaskResultVo>{
	@ApiModelProperty(value = "任务id")
	private String ttId;
	@ApiModelProperty(value = "任务名称")
	private String ttName;
	@ApiModelProperty(value = "专业范畴id")
	private String pcId;
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName;
	@ApiModelProperty(value = "创建人")
	private String createName;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "提交人")
	private String ttSubmitName;
	@ApiModelProperty(value = "提交时间(检查时间)")
	private Long ttSubmitTime;
	@ApiModelProperty(value = "任务状态")
	private Integer ttStatus;


	//冗余字段，禁止序列化
	protected Long comId;
	protected Long startDate;
	protected Long endDate;
	protected Integer valid;

	public String getTtId() {
		return ttId;
	}

	public void setTtId(String ttId) {
		this.ttId = ttId;
	}

	public String getTtName() {
		return ttName;
	}

	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getTtSubmitName() {
		return ttSubmitName;
	}

	public void setTtSubmitName(String ttSubmitName) {
		this.ttSubmitName = ttSubmitName;
	}

	public Long getTtSubmitTime() {
		return ttSubmitTime;
	}

	public void setTtSubmitTime(Long ttSubmitTime) {
		this.ttSubmitTime = ttSubmitTime;
	}

	public Integer getTtStatus() {
		return ttStatus;
	}

	public void setTtStatus(Integer ttStatus) {
		this.ttStatus = ttStatus;
	}
	@JsonIgnore
	@XmlTransient
	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}
	@JsonIgnore
	@XmlTransient
	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	@JsonIgnore
	@XmlTransient
	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	@JsonIgnore
	@XmlTransient
	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
}
