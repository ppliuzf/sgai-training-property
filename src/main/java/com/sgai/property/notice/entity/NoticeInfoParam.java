package com.sgai.property.notice.entity;

import io.swagger.annotations.ApiModelProperty;


public class NoticeInfoParam {
 
    @ApiModelProperty(value = "已审核(YSH)/待审核(DSH)/已撤回(YCH)/...")
    private String status;
    @ApiModelProperty(value = "我发起（WFQ）/我审批（WSH）/我收到（WSD）")
    private String flag;
    @ApiModelProperty(value = "关键词")
    private String keyword;
	@ApiModelProperty(value = "审批时间段start")
	private Long startCreateTime;
	@ApiModelProperty(value = "审批时间段end")
	private Long endCreateTime;
	@ApiModelProperty(value = "发布时间段start")
	private Long startPublishTime;
	@ApiModelProperty(value = "发布时间段end")
	private Long endPublishTime;
	@ApiModelProperty(value = "紧急类型 0：一般，1：紧急")
	private Long infoUrgency;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Long startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Long getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Long endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Long getStartPublishTime() {
		return startPublishTime;
	}

	public void setStartPublishTime(Long startPublishTime) {
		this.startPublishTime = startPublishTime;
	}

	public Long getEndPublishTime() {
		return endPublishTime;
	}

	public void setEndPublishTime(Long endPublishTime) {
		this.endPublishTime = endPublishTime;
	}

	public Long getInfoUrgency() {
		return infoUrgency;
	}

	public void setInfoUrgency(Long infoUrgency) {
		this.infoUrgency = infoUrgency;
	}
}

