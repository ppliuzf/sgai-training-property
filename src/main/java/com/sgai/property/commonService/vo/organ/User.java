package com.sgai.property.commonService.vo.organ;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 * @author 147693
 *
 */
public class User {

	private String id;		//id
	private String userAccount;	//用户账号
	private String userCode;	//用户编号
	private String userName;	//用户中文名
	private String mobilePhone;	//手机号
	private String email;	//邮箱
	private String photo;	//照片地址
	private List<String> feedIds = new ArrayList<String>();
	private List<UserOrganUnit> depts = new ArrayList<UserOrganUnit>(); 
	private List<UserRole> roles = new ArrayList<UserRole>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public List<UserOrganUnit> getDepts() {
		return depts;
	}
	public void setDepts(List<UserOrganUnit> depts) {
		this.depts = depts;
	}
	public List<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

}
