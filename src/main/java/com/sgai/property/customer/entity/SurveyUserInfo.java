package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SurveyUserInfo extends BoEntity<SurveyUserInfo> {

    @ApiModelProperty(value = "参与问卷关联的ID")
    private String smId;
    @ApiModelProperty(value = "答题者性别(0,男;1女)")
    private Long userSex;
    @ApiModelProperty(value = "答题者年龄")
    private Long userAge;
    @ApiModelProperty(value = "用户FeedId")
    private String feedId;
    @ApiModelProperty(value = "用户所属公司ID")
    private String comId;
    @ApiModelProperty(value = "用户所属公司名称")
    private String comName;
    @ApiModelProperty(value = "参与时间")
    private Date suTime;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户所属部门名称")
    private String deptName;
    @ApiModelProperty(value = "用户所属部门ID")
    private String deptId;
    @ApiModelProperty(value = "答题者备注(冗余)")
    private String userDesc;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "答题数量")
    private Long suAnswerCount;
    @ApiModelProperty(value = "是否完成(0,未完成,1,已完成)")
    private Long suIsFinish;
    @ApiModelProperty(value = "是否删除(0,否:1.是)")
    private Long isDelete;

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

    public Long getUserSex() {
        return userSex;
    }

    public void setUserSex(Long userSex) {
        this.userSex = userSex;
    }

    public Long getUserAge() {
        return userAge;
    }

    public void setUserAge(Long userAge) {
        this.userAge = userAge;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Date getSuTime() {
        return suTime;
    }

    public void setSuTime(Date suTime) {
        this.suTime = suTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSuAnswerCount() {
        return suAnswerCount;
    }

    public void setSuAnswerCount(Long suAnswerCount) {
        this.suAnswerCount = suAnswerCount;
    }

    public Long getSuIsFinish() {
        return suIsFinish;
    }

    public void setSuIsFinish(Long suIsFinish) {
        this.suIsFinish = suIsFinish;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }
}