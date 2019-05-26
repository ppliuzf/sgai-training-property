package com.sgai.property.commonService.vo.organ;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * description:
 * Created by llh on 2017/4/18.
 */
public class OrganUserVo {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户账户")
    private String userAccount;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户工号")
    private String userCode;

    @ApiModelProperty(value = "用户手机号")
    private String mobilePhone;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "feedIds")
    private List<String> feedIds;


    @ApiModelProperty(value = "roles",hidden = true)
    private List<OrganUnitVo> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public List<String> getFeedIds() {
        return feedIds;
    }

    public void setFeedIds(List<String> feedIds) {
        this.feedIds = feedIds;
    }

    public List<OrganUnitVo> getRoles() {
        return roles;
    }

    public void setRoles(List<OrganUnitVo> roles) {
        this.roles = roles;
    }
}
