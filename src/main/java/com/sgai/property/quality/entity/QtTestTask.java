package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtTestTask  extends BoEntity<QtTestTask> {
	public static final Integer NO_START = 0;// 未开始
	public static final Integer STARTING = 1;// 进行中
	public static final Integer AUDITING = 2;// 审核中
	public static final Integer FINISH = 3;// 已完结
	@ApiModelProperty(value = "任务名称")
	private String ttName; // 任务名称
	@ApiModelProperty(value = "检验方案id")
	private String tpId; // 检验方案id
	@ApiModelProperty(value = "检验方案名称")
	private String tpName; // 检验方案名称
	@ApiModelProperty(value = "专业范畴id")
	private String pcId; // 专业范畴id
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "检测对象")
	private String ttObjName; // 检测对象
	@ApiModelProperty(value = "检测对象id")
	private String oId; // 检测对象id
	@ApiModelProperty(value = "对象类型id")
	private String tId; // 对象类型id
	@ApiModelProperty(value = "创建人id")
	private Long createEiId; // 创建人id
	@ApiModelProperty(value = "创建人名称")
	private String createName; // 创建人名称
	@ApiModelProperty(value = "任务状态(0:未开始,1:处理中,2审核中,3已完结)")
	private Integer ttStatus; // 任务状态(0:未开始,1:处理中,2审核中,3已完结)
	@ApiModelProperty(value = "任务开始时间")
	private Long ttStartTime; // 任务开始时间
	@ApiModelProperty(value = "任务结束时间")
	private Long ttFinishTime; // 任务结束时间
	@ApiModelProperty(value = "提交审核人id")
	private Long ttSubmitId; // 提交审核人id
	@ApiModelProperty(value = "提交审核人id")
	private String arId; // 审批批次id
	@ApiModelProperty(value = "提交审核人名字")
	private String ttSubmitName; // 提交审核人名字
	@ApiModelProperty(value = "提交审核时间")
	private Long ttSubmitTime; // 提交审核时间
	@ApiModelProperty(value = "审核人id")
	private Long ttCheckId; // 审核人id
	@ApiModelProperty(value = "审核人名字")
	private String ttCheckName; // 审核人名字
	@ApiModelProperty(value = "审核通过时间")
	private Long ttCheckTime; // 审核通过时间
	@ApiModelProperty(value = "任务图片url")
	private String ttIcon; // 任务图片url
	@ApiModelProperty(value = "创建时间")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "组织ID")
	private Long comId; // 组织ID
	@ApiModelProperty(value = "是否删除(0未删除，1已删除)")
	private Integer valid; // 是否删除(0未删除，1已删除)

	public String getTtName() {
		return ttName;
	}

	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getTtObjName() {
		return ttObjName;
	}

	public void setTtObjName(String ttObjName) {
		this.ttObjName = ttObjName;
	}

	public String getOId() {
		return oId;
	}

	public void setOId(String oId) {
		this.oId = oId;
	}

	public String getTId() {
		return tId;
	}

	public void setTId(String tId) {
		this.tId = tId;
	}

	public Long getCreateEiId() {
		return createEiId;
	}

	public void setCreateEiId(Long createEiId) {
		this.createEiId = createEiId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getTtStatus() {
		return ttStatus;
	}

	public void setTtStatus(Integer ttStatus) {
		this.ttStatus = ttStatus;
	}

	public Long getTtStartTime() {
		return ttStartTime;
	}

	public void setTtStartTime(Long ttStartTime) {
		this.ttStartTime = ttStartTime;
	}

	public Long getTtFinishTime() {
		return ttFinishTime;
	}

	public void setTtFinishTime(Long ttFinishTime) {
		this.ttFinishTime = ttFinishTime;
	}

	public Long getTtSubmitId() {
		return ttSubmitId;
	}

	public void setTtSubmitId(Long ttSubmitId) {
		this.ttSubmitId = ttSubmitId;
	}

	public String getTtSubmitName() {
		return ttSubmitName;
	}

	public void setTtSubmitName(String ttSubmitName) {
		this.ttSubmitName = ttSubmitName;
	}

	public Long getTtSubmitTime() {
		return ttSubmitTime;
	}

	public void setTtSubmitTime(Long ttSubmitTime) {
		this.ttSubmitTime = ttSubmitTime;
	}

	public Long getTtCheckId() {
		return ttCheckId;
	}

	public void setTtCheckId(Long ttCheckId) {
		this.ttCheckId = ttCheckId;
	}

	public String getTtCheckName() {
		return ttCheckName;
	}

	public void setTtCheckName(String ttCheckName) {
		this.ttCheckName = ttCheckName;
	}

	public Long getTtCheckTime() {
		return ttCheckTime;
	}

	public void setTtCheckTime(Long ttCheckTime) {
		this.ttCheckTime = ttCheckTime;
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

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getTtIcon() {
		return ttIcon;
	}

	public void setTtIcon(String ttIcon) {
		this.ttIcon = ttIcon;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

}