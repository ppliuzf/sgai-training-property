package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class OrderDetail {

	@ApiModelProperty(value = "详情ID")
	private String odId; // 详情ID
	@ApiModelProperty(value = "工单ID")
	private String oId; // 工单ID
	@ApiModelProperty(value = "处理人ID")
	private String odCreateId; // 处理人ID
	@ApiModelProperty(value = "处理人姓名")
	private String odCreateName; // 处理人姓名
	@ApiModelProperty(value = "处理人头像")
	private String odCreateIcon; // 处理人头像
	@ApiModelProperty(value = "处理时间")
	private Long odCreateTime; // 处理时间
	@ApiModelProperty(value = "操作 0创建工单 1改派 2反馈 3评价 4撤消")
	private Integer odOperation; // 操作 0创建工单 1改派 2反馈 3评价 4撤消
	@ApiModelProperty(value = "当前执行人ID")
	private String odExecutorId; // 当前执行人ID
	@ApiModelProperty(value = "当前执行行姓名")
	private String odExecutorName; // 当前执行行姓名
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "备注")
	private String odContent; // 备注
	@ApiModelProperty(value = "完工时间")
	private Long odFinishTime; // 完工时间
	@ApiModelProperty(value = "评价等级")
	private Integer odLevel; // 评价等级
	@ApiModelProperty(value = "评价标签")
	private String odTitle; // 评价标签
	@ApiModelProperty(value = "操作附件")
	private List<Map<String, Object>> detailUrls; // 操作附件

	public String getOdId() {
		return odId;
	}

	public void setOdId(String odId) {
		this.odId = odId;
	}

	public String getOId() {
		return oId;
	}

	public void setOId(String oId) {
		this.oId = oId;
	}

	public String getOdCreateId() {
		return odCreateId;
	}

	public void setOdCreateId(String odCreateId) {
		this.odCreateId = odCreateId;
	}

	public String getOdCreateName() {
		return odCreateName;
	}

	public void setOdCreateName(String odCreateName) {
		this.odCreateName = odCreateName;
	}

	public Long getOdCreateTime() {
		return odCreateTime;
	}

	public void setOdCreateTime(Long odCreateTime) {
		this.odCreateTime = odCreateTime;
	}

	public Integer getOdOperation() {
		return odOperation;
	}

	public void setOdOperation(Integer odOperation) {
		this.odOperation = odOperation;
	}

	public String getOdExecutorId() {
		return odExecutorId;
	}

	public void setOdExecutorId(String odExecutorId) {
		this.odExecutorId = odExecutorId;
	}

	public String getOdExecutorName() {
		return odExecutorName;
	}

	public void setOdExecutorName(String odExecutorName) {
		this.odExecutorName = odExecutorName;
	}

	public String getOdCreateIcon() {
		return odCreateIcon;
	}

	public void setOdCreateIcon(String odCreateIcon) {
		this.odCreateIcon = odCreateIcon;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getOdContent() {
		return odContent;
	}

	public void setOdContent(String odContent) {
		this.odContent = odContent;
	}

	public Long getOdFinishTime() {
		return odFinishTime;
	}

	public void setOdFinishTime(Long odFinishTime) {
		this.odFinishTime = odFinishTime;
	}

	public Integer getOdLevel() {
		return odLevel;
	}

	public void setOdLevel(Integer odLevel) {
		this.odLevel = odLevel;
	}

	public String getOdTitle() {
		return odTitle;
	}

	public void setOdTitle(String odTitle) {
		this.odTitle = odTitle;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public List<Map<String, Object>> getDetailUrls() {
		return detailUrls;
	}

	public void setDetailUrls(List<Map<String, Object>> detailUrls) {
		this.detailUrls = detailUrls;
	}

}