package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

public class CarAuditRelationInfoVo {

    @ApiModelProperty(value = "申请者姓名或审核人姓名(status为1是申请者 status为2和3是审核者)")
    private String riAuditName; //申请者姓名或审核人姓名(status为1是申请者 status为2和3是审核者)
    @ApiModelProperty(value = "申请者id或审核人id(status为1是申请者 status为2和3是审核者)")
    private String riAuditName1; //申请者id或审核人id(status为1是申请者 status为2和3是审核者)
    @ApiModelProperty(value = "申请者部门或审核人部门")
    private String deptName; //申请者部门或审核人部门
    @ApiModelProperty(value = "操作时间")
    private Long time;//操作时间
    @ApiModelProperty(value = "预约状态(区分是申请者还是审核者的状态(1:提交预约 2:审核通过 3:审核不通过))")
    private Long status;//预约状态(区分是申请者还是审核者的状态(1:提交预约 2:审核通过 3:审核不通过))
    @ApiModelProperty(value = "结束行程公里数")
    private Long end_journey;//结束行程公里数


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRiAuditName() {
        return riAuditName;
    }

    public void setRiAuditName(String riAuditName) {
        this.riAuditName = riAuditName;
    }

    public String getRiAuditName1() {
        return riAuditName1;
    }

    public void setRiAuditName1(String riAuditName1) {
        this.riAuditName1 = riAuditName1;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getEnd_journey() {
        return end_journey;
    }

    public void setEnd_journey(Long end_journey) {
        this.end_journey = end_journey;

    }
}
