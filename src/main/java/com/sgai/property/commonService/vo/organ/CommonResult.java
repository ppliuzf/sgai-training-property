package com.sgai.property.commonService.vo.organ;

import java.util.List;

public class CommonResult<T> {

	private CodeMessage meta;
	private List<T> data;
	
	public CodeMessage getMeta() {
		return meta;
	}
	public void setMeta(CodeMessage meta) {
		this.meta = meta;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
