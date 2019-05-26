package com.sgai.property.commonService.vo;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class EmpSearchResult {
	
	@ApiModelProperty(value = "总记录数")
    private Long total;
	@ApiModelProperty(value = "搜索结果列表")
	private List<EmpInfoVo> list;

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<EmpInfoVo> getList() {
		return list;
	}
	public void setList(List<EmpInfoVo> list) {
		this.list = list;
	}
	
}
