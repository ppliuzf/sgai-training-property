package com.sgai.property.quality.vo;
import com.sgai.property.common.util.QtBaseEntity;
import io.swagger.annotations.ApiModelProperty;

public class CategoryTypeVo extends QtBaseEntity<CategoryTypeVo> {
	@ApiModelProperty(value = "关联类型")
	private String asId; // 关联类型
	@ApiModelProperty(value = "关联类型名称")
	private String asName; // 关联类型名称

	public String getAsId() {
		return asId;
	}

	public void setAsId(String asId) {
		this.asId = asId;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}
}