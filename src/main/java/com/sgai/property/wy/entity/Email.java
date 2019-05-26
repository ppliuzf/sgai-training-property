package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ppliu on 2018/1/19.
 */
public class Email extends BoEntity<Email> {
    /**发送时间.*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    /**部门id.*/
    private Long departmentId;
    /**部门名称.*/
    private String departmentName;
    /**接收人id.*/
    private Long resiverId;
    /**接收人名称.*/
    private String resiverName;
    /**电话.*/
    private String phone;
    /**快递公司id.*/
    private Long expressCompanyId;
    /**快递公司名称.*/
    private String expressCompanyName;
    /**快递单号.*/
    private String expressNumber;
    /**快递分类.*/
    private String expressType;
    /**签字时间.*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date receiptTime;
    /**交接人名称.*/
    private String successorName;
    /**交接人id.*/
    private Long successorId;
    /**签收人id.*/
    private Long  signerId;
    /**签收人名称.*/
    private String signerName;
    /**备注.*/
    private String remark;
    private Date beginTime;
    private Date endTime;
    private String deptCode;
    private String comCode;

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getResiverId() {
        return resiverId;
    }

    public void setResiverId(Long resiverId) {
        this.resiverId = resiverId;
    }

    public String getResiverName() {
        return resiverName;
    }

    public void setResiverName(String resiverName) {
        this.resiverName = resiverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Long expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getSuccessorName() {
        return successorName;
    }

    public void setSuccessorName(String successorName) {
        this.successorName = successorName;
    }

    public Long getSuccessorId() {
        return successorId;
    }

    public void setSuccessorId(Long successorId) {
        this.successorId = successorId;
    }

    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(Long signerId) {
        this.signerId = signerId;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }
}
