package com.sgai.property.notice.param;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class InfoCommitParam {
	
	@ApiModelProperty(value = "公告id")
	private String id;
	@ApiModelProperty(value = "标题")
	private String infoTitle;
	@ApiModelProperty(value = "摘要")
	private String infoSummary;
	@ApiModelProperty(value = "标签")
	private String infoLabel;
	@ApiModelProperty(value = "封皮")
	private String infoCover;
//	@ApiModelProperty(value = "正文第一张图片")
//	private String infoPicture;
	@ApiModelProperty(value = "正文")
	private String infoContent;
	@ApiModelProperty(value = "公开全部可见(0:否，1:是)")
	private Long infoScopeIsAll;
//	@ApiModelProperty(value = "是否需要回执(0:否，1:是)")
//	private String infoReceiptFlag;
	@ApiModelProperty(value = "是否需要审核(0:否，1:是)")
	private Long infoApprovalFlag;
	@ApiModelProperty(value = "可见范围？(App，PC,投屏)")
	private String visibilityScope; //可见范围？(App，PC,投屏)
	@ApiModelProperty(value = "审批人id")
	private String approvalEmpId;
	@ApiModelProperty(value = "审批人名称")
	private String approvalEmpName;
	@ApiModelProperty(value = "发布范围人员id")
	private List<String> infoScope;
	@ApiModelProperty(value = "发布范围部门id")
	private List<String> infoScopeDeparts;
	@ApiModelProperty(value = "发布范围对象")
    private String infoScopeObject; //发布范围对象
    @ApiModelProperty(value = "发布范围所选人数")
    private Long infoScopeEmpNum; //发布范围所选人数
    

    @ApiModelProperty(value = "发布时间")
	private Long publishTime; //发布时间
	@ApiModelProperty(value = "是否定时发布（0：不是，1：是）")
	private Long infoTimePublish; //是否定时发布（0：不是，1：是）
	@ApiModelProperty(value = "紧急类型（0：一般，1：紧急）")
	private Long infoUrgency; //紧急类型（0：一般，1：紧急）
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getInfoSummary() {
		return infoSummary;
	}
	public void setInfoSummary(String infoSummary) {
		this.infoSummary = infoSummary;
	}
	public String getInfoLabel() {
		return infoLabel;
	}
	public void setInfoLabel(String infoLabel) {
		this.infoLabel = infoLabel;
	}
	public String getInfoCover() {
		return infoCover;
	}
	public void setInfoCover(String infoCover) {
		this.infoCover = infoCover;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public Long getInfoScopeIsAll() {
		return infoScopeIsAll;
	}
	public void setInfoScopeIsAll(Long infoScopeIsAll) {
		this.infoScopeIsAll = infoScopeIsAll;
	}
	public Long getInfoApprovalFlag() {
		return infoApprovalFlag;
	}
	public void setInfoApprovalFlag(Long infoApprovalFlag) {
		this.infoApprovalFlag = infoApprovalFlag;
	}

	public String getApprovalEmpId() {
		return approvalEmpId;
	}

	public void setApprovalEmpId(String approvalEmpId) {
		this.approvalEmpId = approvalEmpId;
	}

	public String getApprovalEmpName() {
		return approvalEmpName;
	}
	public void setApprovalEmpName(String approvalEmpName) {
		this.approvalEmpName = approvalEmpName;
	}
	public List<String> getInfoScope() {
		return infoScope;
	}
	public void setInfoScope(List<String> infoScope) {
		this.infoScope = infoScope;
	}
	public List<String> getInfoScopeDeparts() {
		return infoScopeDeparts;
	}
	public void setInfoScopeDeparts(List<String> infoScopeDeparts) {
		this.infoScopeDeparts = infoScopeDeparts;
	}
	public String getInfoScopeObject() {
		return infoScopeObject;
	}
	public void setInfoScopeObject(String infoScopeObject) {
		this.infoScopeObject = infoScopeObject;
	}
	public Long getInfoScopeEmpNum() {
		return infoScopeEmpNum;
	}
	public void setInfoScopeEmpNum(Long infoScopeEmpNum) {
		this.infoScopeEmpNum = infoScopeEmpNum;
	}
	public Long getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}
	public Long getInfoTimePublish() {
		return infoTimePublish;
	}
	public void setInfoTimePublish(Long infoTimePublish) {
		this.infoTimePublish = infoTimePublish;
	}
	public Long getInfoUrgency() {
		return infoUrgency;
	}
	public void setInfoUrgency(Long infoUrgency) {
		this.infoUrgency = infoUrgency;
	}

	public String getVisibilityScope() {
		return visibilityScope;
	}

	public void setVisibilityScope(String visibilityScope) {
		this.visibilityScope = visibilityScope;
	}
}
