package com.sgai.property.commonService;

import java.io.Serializable;

/**
 * commons返回参数
 *
 * @author 146584
 * @date 2017-11-10 16:46
 */
public class UserInfo implements Serializable {

    private Long id;
    private String photo;
    private String externalSysCode;
    private String userName;
    private Long userId;
    private String userCode;
    private Long orgId;
    private Long mobilePhone;
    private String email;

    public String getPhoto() {
        return photo;
    }

    public UserInfo setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getExternalSysCode() {
        return externalSysCode;
    }

    public UserInfo setExternalSysCode(String externalSysCode) {
        this.externalSysCode = externalSysCode;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserInfo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public UserInfo setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public UserInfo setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public UserInfo setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
