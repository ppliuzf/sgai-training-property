package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtTaskItem  extends BoEntity<QtTaskItem> {
	// 未检
	public static final Integer NO_CHECK = 0;
	// 已检验
	public static final Integer CHECKED = 1;
	// 未发起整改
	public static final Integer NO_DEFACT = 0;
	// 发起整改
	public static final Integer HAS_DEFACT = 1;
	// 数字型
	public static final Integer IS_INPUT = 0;
	// 选择型
	public static final Integer NO_INPUT = 1;
	// 无合格状态
	public static final Integer NO_QUALIFIED_STATUS = 0;
	// 合格
	public static final Integer QUALIFIED = 1;
	// 不合格
	public static final Integer UNQUALIFIED = 2;
	
	//拼接分割字符串
	public static final String SPLIT_STR=" \\| ";
	@ApiModelProperty(value = "检验项名称")
	private String tiName; // 检验项名称
	@ApiModelProperty(value = "所属任务id")
	private String ttId; // 所属任务id
	@ApiModelProperty(value = "原始检查项id")
	private String htiId; // 所属任务id
	@ApiModelProperty(value = "待选项(多个选项拼接)")
	private String tiOptions; // 待选项(多个选项拼接)
	@ApiModelProperty(value = "合格选项")
	private String tiStandardOption; // 合格选项
	@ApiModelProperty(value = "上限(整数存储,做乘100处理)")
	private Long tiMax; // 上限(整数存储,做乘100处理)
	@ApiModelProperty(value = "下限(整数存储,做乘100处理)")
	private Long tiMin; // 下限(整数存储,做乘100处理)
	@ApiModelProperty(value = "单位")
	private String tiUnit; // 单位
	@ApiModelProperty(value = "输入类型(0:数字型,:1:选择型)")
	private Integer tiIsInput; // 输入类型(0:数字型,:1:选择型)
	@ApiModelProperty(value = "填入的检测结果")
	private String tiInuputResult; // 填入的检测结果
	@ApiModelProperty(value = "是否答题(0未检测,1已检测)")
	private Integer tiIsSubmit; // 是否答题(0未检测,1已检测)
	@ApiModelProperty(value = "是否合格(0:无,1:合格,2:不合格)")
	private Integer tiStatus; // 是否合格(0:无,1:合格,2:不合格)
	@ApiModelProperty(value = "检查标准")
	private String tiStandard; // 检查标准
	@ApiModelProperty(value = "完成时限")
	private Integer tiLimitTime; // 完成时限
	@ApiModelProperty(value = "完成时限单位(1:天,2:小时,3:分钟)")
	private Integer tiFinshUnit; // 完成时限单位(1:天,2:小时,3:分钟)
	@ApiModelProperty(value = "检验人id")
	private Long tiCheckId; // 检验人id
	@ApiModelProperty(value = "检验人名称")
	private String tiCheckName; // 检验人名称
	@ApiModelProperty(value = "在组中的排序")
	private Integer groupSort; // 在组中的排序
	@ApiModelProperty(value = "任务的检测项组id")
	private String groupId; // 任务的检测项组id
	@ApiModelProperty(value = "备注")
	private String tiRemark; // 备注
	@ApiModelProperty(value = "检测时间")
	private Long tiCheckTime; // 检测时间
	@ApiModelProperty(value = "是否提交缺陷整改(0:未提交整改,1:已提交整改)")
	private Integer tiHasDefect; // 是否提交缺陷整改(0:未提交整改,1:已提交整改)
	@ApiModelProperty(value = "创建时间")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "是否删除(0未删除，1已删除)")
	private Integer valid; // 是否删除(0未删除，1已删除)

	public String getTiName() {
		return tiName;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public String getTiOptions() {
		return tiOptions;
	}

	public void setTiOptions(String tiOptions) {
		this.tiOptions = tiOptions;
	}

	public String getTiStandardOption() {
		return tiStandardOption;
	}

	public void setTiStandardOption(String tiStandardOption) {
		this.tiStandardOption = tiStandardOption;
	}

	public Long getTiMax() {
		return tiMax;
	}

	public void setTiMax(Long tiMax) {
		this.tiMax = tiMax;
	}

	public Long getTiMin() {
		return tiMin;
	}

	public void setTiMin(Long tiMin) {
		this.tiMin = tiMin;
	}

	public String getTiUnit() {
		return tiUnit;
	}

	public void setTiUnit(String tiUnit) {
		this.tiUnit = tiUnit;
	}

	public Integer getTiIsInput() {
		return tiIsInput;
	}

	public void setTiIsInput(Integer tiIsInput) {
		this.tiIsInput = tiIsInput;
	}

	public String getTiInuputResult() {
		return tiInuputResult;
	}

	public void setTiInuputResult(String tiInuputResult) {
		this.tiInuputResult = tiInuputResult;
	}

	public Integer getTiIsSubmit() {
		return tiIsSubmit;
	}

	public void setTiIsSubmit(Integer tiIsSubmit) {
		this.tiIsSubmit = tiIsSubmit;
	}

	public Integer getTiStatus() {
		return tiStatus;
	}

	public void setTiStatus(Integer tiStatus) {
		this.tiStatus = tiStatus;
	}

	public String getTiStandard() {
		return tiStandard;
	}

	public void setTiStandard(String tiStandard) {
		this.tiStandard = tiStandard;
	}

	public Integer getTiLimitTime() {
		return tiLimitTime;
	}

	public void setTiLimitTime(Integer tiLimitTime) {
		this.tiLimitTime = tiLimitTime;
	}

	public Integer getTiFinshUnit() {
		return tiFinshUnit;
	}

	public void setTiFinshUnit(Integer tiFinshUnit) {
		this.tiFinshUnit = tiFinshUnit;
	}

	public Long getTiCheckId() {
		return tiCheckId;
	}

	public void setTiCheckId(Long tiCheckId) {
		this.tiCheckId = tiCheckId;
	}

	public String getTiCheckName() {
		return tiCheckName;
	}

	public void setTiCheckName(String tiCheckName) {
		this.tiCheckName = tiCheckName;
	}

	public Integer getGroupSort() {
		return groupSort;
	}

	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTiRemark() {
		return tiRemark;
	}

	public void setTiRemark(String tiRemark) {
		this.tiRemark = tiRemark;
	}

	public Long getTiCheckTime() {
		return tiCheckTime;
	}

	public void setTiCheckTime(Long tiCheckTime) {
		this.tiCheckTime = tiCheckTime;
	}

	public Integer getTiHasDefect() {
		return tiHasDefect;
	}

	public void setTiHasDefect(Integer tiHasDefect) {
		this.tiHasDefect = tiHasDefect;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getTtId() {
		return ttId;
	}

	public void setTtId(String ttId) {
		this.ttId = ttId;
	}

	public String getHtiId() {
		return htiId;
	}

	public void setHtiId(String htiId) {
		this.htiId = htiId;
	}

}