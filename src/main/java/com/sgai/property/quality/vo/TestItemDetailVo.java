package com.sgai.property.quality.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TestItemDetailVo {

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
	@ApiModelProperty(value = "待选项(多个选项拼接)")
	private String tiOptions; // 待选项(多个选项拼接)
	@ApiModelProperty(value = "合格选项")
	private String tiStandardOption; // 合格选项
	@ApiModelProperty(value = "上限")
	private String tiMax; // 上限
	@ApiModelProperty(value = "下限")
	private String tiMin; // 下限
	@ApiModelProperty(value = "单位")
	private String tiUnit; // 单位
	@ApiModelProperty(value = "完成时限")
	private Integer tiLimitTime; // 完成时限
	@ApiModelProperty(value = "完成时限单位(1天,2小时,3分钟)")
	private Integer tiFinshUnit; // 完成时限单位(1天,2小时,3分钟)
	@ApiModelProperty(value = "缺陷责任岗位id")
	private String tiPostId; // 缺陷责任岗位id
	@ApiModelProperty(value = "缺陷责任岗位名称")
	private String tiPostName; // 缺陷责任岗位名称
	@ApiModelProperty(value = "整改要求")
	private String tiRectificationRequirements; // 整改要求
	@ApiModelProperty(value = "创建人名称")
    private String createEiName; //创建人名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
	@ApiModelProperty(value = "是否删除(0未删除,1已删除)")
    private Integer valid; //是否删除(0未删除,1已删除)
	@ApiModelProperty(value = "操作附件")
	private String enclosures; // 操作附件
	
	@ApiModelProperty(value = "填入的检测结果")
    private String tiInputResult;
	
    @ApiModelProperty(value = "状态(0未检查,1合格,2缺陷)")
    private Integer tiStatus;
    @ApiModelProperty(value = "是否答题(0未检测,1已检测)")
    private Integer tiIsSubmit;
	@ApiModelProperty(value = "是否提交缺陷整改(0:未提交整改,1:已提交整改)")
    private Integer tiHasDefect;
	
	@ApiModelProperty(value = "缺陷整改责任人(多个)")
    private List<String> dutyEmpNames;

	@ApiModelProperty(value = "计划id")
	private String recordId; //计划id
	@ApiModelProperty(value = "计划名称")
	private String recordName; //计划名称
	
	public List<String> getDutyEmpNames() {
		return dutyEmpNames;
	}
	public void setDutyEmpNames(List<String> dutyEmpNames) {
		this.dutyEmpNames = dutyEmpNames;
	}
	public String getCreateEiName() {
		return createEiName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public String getEnclosures() {
		return enclosures;
	}
	public String getPcId() {
		return pcId;
	}
	public String getPcName() {
		return pcName;
	}
    public Integer getTiFinshUnit() {
		return tiFinshUnit;
	}

	
	
	public Integer getTiHasDefect() {
		return tiHasDefect;
	}
	public String getTiId() {
		return tiId;
	}
	public String getTiInputResult() {
		return tiInputResult;
	}
	public Integer getTiIsInput() {
		return tiIsInput;
	}
	public Integer getTiIsSubmit() {
		return tiIsSubmit;
	}
	public Integer getTiLimitTime() {
		return tiLimitTime;
	}
	public String getTiMax() {
		return tiMax;
	}
	public String getTiMin() {
		return tiMin;
	}
	public String getTiName() {
		return tiName;
	}
	public String getTiOptions() {
		return tiOptions;
	}
	public String getTiPostId() {
		return tiPostId;
	}
	public String getTiPostName() {
		return tiPostName;
	}
	public String getTiRectificationRequirements() {
		return tiRectificationRequirements;
	}
	public String getTiStandard() {
		return tiStandard;
	}
	public String getTiStandardOption() {
		return tiStandardOption;
	}
	public Integer getTiStatus() {
		return tiStatus;
	}
	public String getTiUnit() {
		return tiUnit;
	}
	public Integer getValid() {
		return valid;
	}
	public void setCreateEiName(String createEiName) {
		this.createEiName = createEiName;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public void setEnclosures(String enclosures) {
		this.enclosures = enclosures;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public void setTiFinshUnit(Integer tiFinshUnit) {
		this.tiFinshUnit = tiFinshUnit;
	}
	public void setTiHasDefect(Integer tiHasDefect) {
		this.tiHasDefect = tiHasDefect;
	}
	public void setTiId(String tiId) {
		this.tiId = tiId;
	}
	public void setTiInputResult(String tiInputResult) {
		this.tiInputResult = tiInputResult;
	}
	public void setTiIsInput(Integer tiIsInput) {
		this.tiIsInput = tiIsInput;
	}
	public void setTiIsSubmit(Integer tiIsSubmit) {
		this.tiIsSubmit = tiIsSubmit;
	}
	public void setTiLimitTime(Integer tiLimitTime) {
		this.tiLimitTime = tiLimitTime;
	}
	public void setTiMax(String tiMax) {
		this.tiMax = tiMax;
	}
	public void setTiMin(String tiMin) {
		this.tiMin = tiMin;
	}
	public void setTiName(String tiName) {
		this.tiName = tiName;
	}
	public void setTiOptions(String tiOptions) {
		this.tiOptions = tiOptions;
	}
	public void setTiPostId(String tiPostId) {
		this.tiPostId = tiPostId;
	}
	public void setTiPostName(String tiPostName) {
		this.tiPostName = tiPostName;
	}
	public void setTiRectificationRequirements(String tiRectificationRequirements) {
		this.tiRectificationRequirements = tiRectificationRequirements;
	}
	public void setTiStandard(String tiStandard) {
		this.tiStandard = tiStandard;
	}
	public void setTiStandardOption(String tiStandardOption) {
		this.tiStandardOption = tiStandardOption;
	}
	public void setTiStatus(Integer tiStatus) {
		this.tiStatus = tiStatus;
	}
	public void setTiUnit(String tiUnit) {
		this.tiUnit = tiUnit;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
}