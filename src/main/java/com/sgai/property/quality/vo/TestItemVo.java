package com.sgai.property.quality.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgai.property.common.util.QtBaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlTransient;

public class TestItemVo extends QtBaseEntity<TestItemVo>{

	@ApiModelProperty(value = "主键")
    private String tiId; //主键
	@ApiModelProperty(value = "检验项名称")
	private String tiName; // 检验项名称
	@ApiModelProperty(value = "专业范畴id")
	private String pcId; // 专业范畴id
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "检查标准")
	private String tiStandard; // 检查标准
	@ApiModelProperty(value = "答题类型(0:数字型,1:选择型)")
	private Integer tiIsInput; // 答题类型(0:数字型,1:选择型)
	@ApiModelProperty(value = "是否删除(0未删除,1已删除)")
    private Integer valid; //是否删除(0未删除,1已删除)
	@ApiModelProperty(value = "创建人名称")
    private String createEiName; //创建人名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间

	//冗余字段，禁止序列化
	protected Long comId;
	protected String itemName;
	protected Long startDate;
	protected Long endDate;

	private String comCode; //机构代码
	private String moduCode; //模块代码

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer typeFlag;

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getModuCode() {
		return moduCode;
	}

	public void setModuCode(String moduCode) {
		this.moduCode = moduCode;
	}

	public String getTiId() {
		return tiId;
	}
	public void setTiId(String tiId) {
		this.tiId = tiId;
	}
	public String getTiName() {
		return tiName;
	}
	public void setTiName(String tiName) {
		this.tiName = tiName;
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
	public String getTiStandard() {
		return tiStandard;
	}
	public void setTiStandard(String tiStandard) {
		this.tiStandard = tiStandard;
	}
	public Integer getTiIsInput() {
		return tiIsInput;
	}
	public void setTiIsInput(Integer tiIsInput) {
		this.tiIsInput = tiIsInput;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getCreateEiName() {
		return createEiName;
	}
	public void setCreateEiName(String createEiName) {
		this.createEiName = createEiName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}
}