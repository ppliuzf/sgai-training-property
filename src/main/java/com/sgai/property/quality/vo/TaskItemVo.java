package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskItemVo {
	// 未检
	public static final Integer NO_CHECK = 0;
	// 合格
	public static final Integer QUALIFIED = 1;
	// 不合格
	public static final Integer UNQUALIFIED = 2;
	// 缺陷整改
	public static final Integer HAS_DEFACT = 3;
	@ApiModelProperty(value = "检查项id")
	private String id;
	@ApiModelProperty(value = "检查项名称")
	private String name;
	@ApiModelProperty(value = "在组中第几项")
	private Integer sort;
	@ApiModelProperty(value = "是否检测(0:未检测,1:已检测)")
	private Integer isSubmit;
	@ApiModelProperty(value = "检查项状态(0:其他,1:合格,2:缺陷整改)")
	private Integer status;
	@ApiModelProperty(value = "题目类型(0:数字型,:1:选择型)")
	private Integer type;
	@ApiModelProperty(value = "选择型题目的选项")
	private List<String> options;
	@ApiModelProperty(value = "检查标准")
	private String standard;
	@ApiModelProperty(value = "选择型题目的标准选项")
	private String standardOption;
	@ApiModelProperty(value = "输入型值最小范围")
	private BigDecimal min;
	@ApiModelProperty(value = "输入型值最大范围")
	private BigDecimal max;
	@ApiModelProperty(value = "单位")
	private String unit;
	@ApiModelProperty(value = "答题结果")
	private String inputResult;
	@ApiModelProperty(value = "文字备注")
	private String remark;
	@ApiModelProperty(value = "检查项图片")
	private String url;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getStandardOption() {
		return standardOption;
	}
	public void setStandardOption(String standardOption) {
		this.standardOption = standardOption;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public String getInputResult() {
		return inputResult;
	}
	public void setInputResult(String inputResult) {
		this.inputResult = inputResult;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
}
