package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RecordParamAdd{
	 @ApiModelProperty(value = "计划名称")
	 private String recordName; //计划名称
	 @ApiModelProperty(value = "计划类型id")
	 private String typeId; //计划类型id
	 @ApiModelProperty(value = "计划类型名称")
	 private String typeName; //计划类型名称
    @ApiModelProperty(value = "计划描述")
    private String recordDesc; //计划描述
    @ApiModelProperty(value = "参与者id集合")
    private List<String>  idList;   
    @ApiModelProperty(value = "任务管理者(1:责任人;2:参与者)")
    private Long recordManager; //任务管理者(1:责任人;2:参与者)
    @ApiModelProperty(value = "图片")
    private List<String> imgList;
	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer type;

	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	public Long getRecordManager() {
		return recordManager;
	}
	public void setRecordManager(Long recordManager) {
		this.recordManager = recordManager;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}