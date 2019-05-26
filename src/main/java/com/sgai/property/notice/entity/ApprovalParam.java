package com.sgai.property.notice.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/12/27.
 */
public class ApprovalParam {
    @ApiModelProperty(value = "公告ID")
    private String id;
    @ApiModelProperty(value = "审批状态（Y:通过/置顶，N:拒绝/取消置顶）")
    private String approvalStatus;
    @ApiModelProperty(value = "审批意见")
    private String approvalOpinition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public String getApprovalOpinition() {
        return approvalOpinition;
    }
    public void setApprovalOpinition(String approvalOpinition) {
        this.approvalOpinition = approvalOpinition;
    }

}
