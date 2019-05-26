package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 处理工单Dto
 * @author Administrator
 *
 */
public class DealOrderDto {

	@ApiModelProperty(value = "工单ID")
    private String oId; //工单ID
	
	@ApiModelProperty(value = "完工时间")
    private Long odFinishTime; //完工时间
	
	@ApiModelProperty(value = "备注")
    private String odContent; //备注
	
	@ApiModelProperty(value = "评价等级")
    private Integer odLevel; //评价等级
	
	@ApiModelProperty(value = "评价标签")
    private String odTitle; //评价标签
	
	@ApiModelProperty(value = "附件地址数组")
	private List<String> urlList; //附件地址数组

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public Long getOdFinishTime() {
		return odFinishTime;
	}

	public void setOdFinishTime(Long odFinishTime) {
		this.odFinishTime = odFinishTime;
	}

	public String getOdContent() {
		return odContent;
	}

	public void setOdContent(String odContent) {
		this.odContent = odContent;
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

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	
}
