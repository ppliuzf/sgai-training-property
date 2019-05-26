  
    /**    
    * @Title: Member.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月26日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;


    /**  
 * @ClassName: Member  
 * @Description: 会员用户  
 * @author XJ9001  
 * @date 2018年1月26日  
 * @Company 首自信--智慧城市创新中心  
 */

public class Member extends BoEntity<Member> {

	  
		    /**  
		    * @Fields field:field:(用一句话描述这个变量表示什么)
		    */  
		    
		private static final long serialVersionUID = 1L;

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date enrollTime; //注册时间
	
	private String phoneNumber;  //登录手机号
	
	private String chineseName;  //中文姓名
	
	private String contactWay;  //联系方式
	 
	private String type;   //类型
	
	private String status;  //状态
	
	private String deptName;  //部门
	
	private String deptCode;  //部门
	
	private String email;  //电子邮件
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;  //出生年月
	
	private String finishSchool;  //毕业院校
	
	private String position;  //职位
	
	private String remark;  //备注
	
	private String repairAddress;  //地址
	
	private String repairAddressCode;  //地址Code
	
	private String memberRankId;  //等级ID
	
	private String memberRankName;  //等级Name
	
	private String memberCode; //用户Code(唯一)

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFinishSchool() {
		return finishSchool;
	}

	public void setFinishSchool(String finishSchool) {
		this.finishSchool = finishSchool;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRepairAddress() {
		return repairAddress;
	}

	public void setRepairAddress(String repairAddress) {
		this.repairAddress = repairAddress;
	}

	public String getRepairAddressCode() {
		return repairAddressCode;
	}

	public void setRepairAddressCode(String repairAddressCode) {
		this.repairAddressCode = repairAddressCode;
	}

	public String getMemberRankId() {
		return memberRankId;
	}

	public void setMemberRankId(String memberRankId) {
		this.memberRankId = memberRankId;
	}

	public String getMemberRankName() {
		return memberRankName;
	}

	public void setMemberRankName(String memberRankName) {
		this.memberRankName = memberRankName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	
	
	
	
	
	
}
