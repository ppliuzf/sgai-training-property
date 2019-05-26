package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class DefectOrderDetailVo extends DefectOrderVo {
	
	@ApiModelProperty(value = "整改内容")
    private String doContent; //整改内容
    @ApiModelProperty(value = "截止时间")
    private Long doDeadline; //截止时间
    @ApiModelProperty(value = "工单附件")
    List<Map<String, Object>> orderUrls;
    @ApiModelProperty(value = "处理列表")
    List<OrderDetail> detailList;
	public String getDoContent() {
		return doContent;
	}
	public void setDoContent(String doContent) {
		this.doContent = doContent;
	}
	public Long getDoDeadline() {
		return doDeadline;
	}
	public void setDoDeadline(Long doDeadline) {
		this.doDeadline = doDeadline;
	}
	public List<Map<String, Object>> getOrderUrls() {
		return orderUrls;
	}
	public void setOrderUrls(List<Map<String, Object>> orderUrls) {
		this.orderUrls = orderUrls;
	}
	public List<OrderDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<OrderDetail> detailList) {
		this.detailList = detailList;
	}
}
