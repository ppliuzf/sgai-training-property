package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgai.property.common.util.QtBaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

public class UnLinkItemVo extends QtBaseEntity<UnLinkItemVo> {
	@ApiModelProperty(value = "检查项id")
	private String id;
	@ApiModelProperty(value = "检查项名称")
	private String name;
	@ApiModelProperty(value = "标准")
	private String standard;
	@ApiModelProperty(value = "comId")
	private Long comId;
	@ApiModelProperty(value = "答案类型")
	private Integer answerType;
	@ApiModelProperty(value = "答案类型描述")
	private String answerTypeDesc;
	@ApiModelProperty(value = "创建人")
	private String createName;
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;


	//冗余字段，禁止序列化
	protected String pcId;
	private List<String> idList;
	protected Integer valid;

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer typeFlag;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Integer getAnswerType() {
		return answerType;
	}
	public void setAnswerType(Integer answerType) {
		this.answerType = answerType;
	}
	public String getAnswerTypeDesc() {
		return answerTypeDesc;
	}
	public void setAnswerTypeDesc(String answerTypeDesc) {
		this.answerTypeDesc = answerTypeDesc;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	@JsonIgnore
	@XmlTransient
	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	@JsonIgnore
	@XmlTransient
	public Integer getValid() {
		return valid;
	}


	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}
}
