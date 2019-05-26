package com.sgai.property.notice.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/12/25.
 * 我发起的查询参数
 *
 */
public class NoticeISendParam extends BoEntity<NoticeInfo> {
    @ApiModelProperty(value = "已审核(YSH)/待审核(DSH)/已撤回(YCH)/已拒绝(未通过)(YJJ)")
    private String status;
    @ApiModelProperty(value = "我发起（WFQ）/我审批（WSP）/我收到（WSD）")
    private String flag;
    @ApiModelProperty(value = "关键词")
    private String keyword;
    @ApiModelProperty(value = "审批时间段start")
    private Long startCreateTime;
    @ApiModelProperty(value = "审批时间段end")
    private Long endCreateTime;
    @ApiModelProperty(value = "发布时间段start")
    private Long startPublishTime;
    @ApiModelProperty(value = "发布时间段end")
    private Long endPublishTime;
    @ApiModelProperty(value = "发起人ID")
    private String empId;
    @ApiModelProperty(value = "发起人类别")
    private String creatorType;
    @ApiModelProperty(value = "公告状态-未通过")
    private String noPass;
    @ApiModelProperty(value = "公告状态（1：待审核，2：未通过，4：已通过，5：已拒绝，8：已发布，16：已撤回）")
    private String infoStatus;
    @ApiModelProperty(value = "公告状态-已撤回")
    private String revocation;
    @ApiModelProperty(value = "公告状态-待审核")
    private String dsh;
    @ApiModelProperty(value = "公告状态-已通过")
    private String pass;
    @ApiModelProperty(value = "公告状态-已发布")
    private String publish;
    @ApiModelProperty(value = "公告ID")
    private String[] receiptArry;
    @ApiModelProperty(value = "紧急类型 0：一般，1：紧急")
    private Long infoUrgency;
    @ApiModelProperty(value = "跳过数")
    private Integer pageNum;
    @ApiModelProperty(value = "一共几条")
    private Integer pageSize;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Long startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Long getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Long endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Long getStartPublishTime() {
        return startPublishTime;
    }

    public void setStartPublishTime(Long startPublishTime) {
        this.startPublishTime = startPublishTime;
    }

    public Long getEndPublishTime() {
        return endPublishTime;
    }

    public void setEndPublishTime(Long endPublishTime) {
        this.endPublishTime = endPublishTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getNoPass() {
        return noPass;
    }

    public void setNoPass(String noPass) {
        this.noPass = noPass;
    }

    public String getRevocation() {
        return revocation;
    }

    public void setRevocation(String revocation) {
        this.revocation = revocation;
    }

    public String getDsh() {
        return dsh;
    }

    public void setDsh(String dsh) {
        this.dsh = dsh;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String[] getReceiptArry() {
        return receiptArry;
    }

    public void setReceiptArry(String[] receiptArry) {
        this.receiptArry = receiptArry;
    }

    public Long getInfoUrgency() {
        return infoUrgency;
    }

    public void setInfoUrgency(Long infoUrgency) {
        this.infoUrgency = infoUrgency;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
