package com.sgai.property.notice.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/11/10.
 */
public class NoticeResponseInfo extends BoEntity<NoticeResponseInfo> {
    @ApiModelProperty(value = "标题")
    private String infoTitle; //标题
    @ApiModelProperty(value = "是否需要回执(0：不需要回执，1：需要回执)")
    private Long infoReceiptFlag; //是否需要回执(0：不需要回执，1：需要回执)
    @ApiModelProperty(value = "")
    private String infoLabel; //
    @ApiModelProperty(value = "封皮")
    private String infoCover; //封皮
    @ApiModelProperty(value = "是否置顶(0：不需要，1：需要)")
    private Long infoIsTop; //是否置顶(0：不需要，1：需要)
    @ApiModelProperty(value = "置顶时间")
    private Long infoTopTime; //置顶时间
    @ApiModelProperty(value = "是否定时发布（0：不是，1：是）")
    private Long infoTimePublish; //是否定时发布（0：不是，1：是）
    @ApiModelProperty(value = "审核人id")
    private Long approvalEmpId; //审核人id
    @ApiModelProperty(value = "审核人名称")
    private String approvalEmpName; //审核人名称
    @ApiModelProperty(value = "组织id")
    private Long infoOrgId; //组织id
    @ApiModelProperty(value = "创建者类型（0:员工，1:组织名片持有者）")
    private Long infoCreatorType; //创建者类型（0:员工，1:组织名片持有者）
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否需要审核(0：不需要审核，1：需要审核)")
    private Long infoApprovalFlag; //是否需要审核(0：不需要审核，1：需要审核)
    @ApiModelProperty(value = "发起人id")
    private Long infoCreatorId; //发起人id
    @ApiModelProperty(value = "组织名称")
    private String infoOrgName; //组织名称
    @ApiModelProperty(value = "审批意见")
    private String approvalOpinition; //审批意见
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "紧急类型（0：一般，1：紧急）")
    private Long infoUrgency; //紧急类型（0：一般，1：紧急）
    @ApiModelProperty(value = "发起审核时间")
    private Long initApprovalTime; //发起审核时间
    @ApiModelProperty(value = "审批时间")
    private Long approvalTime; //审批时间
    @ApiModelProperty(value = "摘要")
    private String infoSummary; //摘要
    @ApiModelProperty(value = "正文第一张图片")
    private String infoPicture; //正文第一张图片
    @ApiModelProperty(value = "发布时间")
    private Long publishTime; //发布时间
    @ApiModelProperty(value = "撤回时间")
    private Long retractTime; //撤回时间
    @ApiModelProperty(value = "发起人名称")
    private String infoCreatorName; //发起人名称
    @ApiModelProperty(value = "审核内容")
    private String infoContent; //审核内容
    @ApiModelProperty(value = "发布范围全部可见？(0:否，1:是)")
    private Long infoScopeIsAll; //发布范围全部可见？(0:否，1:是)
    @ApiModelProperty(value = "公告状态(1：待审核，2：未通过，4：已通过，8：已发布，16：已撤回)")
    private Long infoStatus; //公告状态(1：待审核，2：未通过，4：已通过，8：已发布，16：已撤回)
    @ApiModelProperty(value = "已审核(YSH)/待审核(DSH)/已撤回(YCH)/...")
    private String status;
    @ApiModelProperty(value = "我发起（WFQ）/我审批（WSP）/我收到（WSD）")
    private String flag;
    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public Long getInfoReceiptFlag() {
        return infoReceiptFlag;
    }

    public void setInfoReceiptFlag(Long infoReceiptFlag) {
        this.infoReceiptFlag = infoReceiptFlag;
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

    public Long getInfoIsTop() {
        return infoIsTop;
    }

    public void setInfoIsTop(Long infoIsTop) {
        this.infoIsTop = infoIsTop;
    }

    public Long getInfoTopTime() {
        return infoTopTime;
    }

    public void setInfoTopTime(Long infoTopTime) {
        this.infoTopTime = infoTopTime;
    }

    public Long getInfoTimePublish() {
        return infoTimePublish;
    }

    public void setInfoTimePublish(Long infoTimePublish) {
        this.infoTimePublish = infoTimePublish;
    }

    public Long getApprovalEmpId() {
        return approvalEmpId;
    }

    public void setApprovalEmpId(Long approvalEmpId) {
        this.approvalEmpId = approvalEmpId;
    }

    public String getApprovalEmpName() {
        return approvalEmpName;
    }

    public void setApprovalEmpName(String approvalEmpName) {
        this.approvalEmpName = approvalEmpName;
    }

    public Long getInfoOrgId() {
        return infoOrgId;
    }

    public void setInfoOrgId(Long infoOrgId) {
        this.infoOrgId = infoOrgId;
    }

    public Long getInfoCreatorType() {
        return infoCreatorType;
    }

    public void setInfoCreatorType(Long infoCreatorType) {
        this.infoCreatorType = infoCreatorType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getInfoApprovalFlag() {
        return infoApprovalFlag;
    }

    public void setInfoApprovalFlag(Long infoApprovalFlag) {
        this.infoApprovalFlag = infoApprovalFlag;
    }

    public Long getInfoCreatorId() {
        return infoCreatorId;
    }

    public void setInfoCreatorId(Long infoCreatorId) {
        this.infoCreatorId = infoCreatorId;
    }

    public String getInfoOrgName() {
        return infoOrgName;
    }

    public void setInfoOrgName(String infoOrgName) {
        this.infoOrgName = infoOrgName;
    }

    public String getApprovalOpinition() {
        return approvalOpinition;
    }

    public void setApprovalOpinition(String approvalOpinition) {
        this.approvalOpinition = approvalOpinition;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getInfoUrgency() {
        return infoUrgency;
    }

    public void setInfoUrgency(Long infoUrgency) {
        this.infoUrgency = infoUrgency;
    }

    public Long getInitApprovalTime() {
        return initApprovalTime;
    }

    public void setInitApprovalTime(Long initApprovalTime) {
        this.initApprovalTime = initApprovalTime;
    }

    public Long getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Long approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getInfoSummary() {
        return infoSummary;
    }

    public void setInfoSummary(String infoSummary) {
        this.infoSummary = infoSummary;
    }

    public String getInfoPicture() {
        return infoPicture;
    }

    public void setInfoPicture(String infoPicture) {
        this.infoPicture = infoPicture;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getRetractTime() {
        return retractTime;
    }

    public void setRetractTime(Long retractTime) {
        this.retractTime = retractTime;
    }

    public String getInfoCreatorName() {
        return infoCreatorName;
    }

    public void setInfoCreatorName(String infoCreatorName) {
        this.infoCreatorName = infoCreatorName;
    }

    public Long getInfoScopeIsAll() {
        return infoScopeIsAll;
    }

    public void setInfoScopeIsAll(Long infoScopeIsAll) {
        this.infoScopeIsAll = infoScopeIsAll;
    }

    public Long getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Long infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
