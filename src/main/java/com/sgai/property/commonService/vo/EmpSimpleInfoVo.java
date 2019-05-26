package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

public class EmpSimpleInfoVo {
    @ApiModelProperty(value = "用户ID")
    private Long eiId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户工号")
    private String userCode;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "feedId")
    private String feedId;
    private Long getEiId;

    public Long getUserNo() {
        return eiId;
    }

    public void setEiId(Long eiId) {
        this.eiId = eiId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public void setGetEiId(Long getEiId) {
        this.getEiId = getEiId;
    }

    public Long getEiId() {

        return eiId;
    }
}
