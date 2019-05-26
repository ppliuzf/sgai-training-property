package com.sgai.property.commonService.vo;

import java.io.Serializable;

/**
 * 连接平台解析出来的登录信息
 *
 * @author 146584
 * @date 2017-11-13 18:45
 */
public class LoginUserInfo implements Serializable {

    private Long createTime;
    private Long birthday;
    private Long sex;
    private String certType;
    private String userPwd;
    private String userType;
    private Long orgId;
    private String officeLocation;
    private String certCode;
    private Long userId;
    private Long externalSysCode;
    private Long status;
    private String userName;
    private String firstName;
    private String lastName;
    private String externalSysType;
    private String updateTime;
    private Long userCode;
    private String nativePlace;
    private String photo;
    private String nation;
    private Long mobilePhone;
    private String email;
    private String language;
    private Long userAccount;
    private Long salt;
    private Long adminId;
    private Long adminAccount;
    private String orgName;
    private String adminToken;

    public Long getAdminId() {
        return adminId;
    }

    public LoginUserInfo setAdminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public Long getAdminAccount() {
        return adminAccount;
    }

    public LoginUserInfo setAdminAccount(Long adminAccount) {
        this.adminAccount = adminAccount;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public LoginUserInfo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getBirthday() {
        return birthday;
    }

    public LoginUserInfo setBirthday(Long birthday) {
        this.birthday = birthday;
        return this;
    }

    public Long getSex() {
        return sex;
    }

    public LoginUserInfo setSex(Long sex) {
        this.sex = sex;
        return this;
    }

    public String getCertType() {
        return certType;
    }

    public LoginUserInfo setCertType(String certType) {
        this.certType = certType;
        return this;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public LoginUserInfo setUserPwd(String userPwd) {
        this.userPwd = userPwd;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public LoginUserInfo setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public LoginUserInfo setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public LoginUserInfo setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
        return this;
    }

    public String getCertCode() {
        return certCode;
    }

    public LoginUserInfo setCertCode(String certCode) {
        this.certCode = certCode;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public LoginUserInfo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getExternalSysCode() {
        return externalSysCode;
    }

    public LoginUserInfo setExternalSysCode(Long externalSysCode) {
        this.externalSysCode = externalSysCode;
        return this;
    }

    public Long getStatus() {
        return status;
    }

    public LoginUserInfo setStatus(Long status) {
        this.status = status;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public LoginUserInfo setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getUserCode() {
        return userCode;
    }

    public LoginUserInfo setUserCode(Long userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public LoginUserInfo setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public LoginUserInfo setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public LoginUserInfo setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public LoginUserInfo setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginUserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public LoginUserInfo setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Long getUserAccount() {
        return userAccount;
    }

    public LoginUserInfo setUserAccount(Long userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public Long getSalt() {
        return salt;
    }

    public LoginUserInfo setSalt(Long salt) {
        this.salt = salt;
        return this;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getExternalSysType() {
		return externalSysType;
	}

	public void setExternalSysType(String externalSysType) {
		this.externalSysType = externalSysType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}
    
}
