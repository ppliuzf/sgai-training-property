package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlEmp  
    * Description: (员工实体类)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlEmp extends BoEntity<CtlEmp> {
	
	 private static final long serialVersionUID = 1L;
	 @ApiModelProperty(value = "员工代码")
	 private String empCode;		// emp_id 员工代码
	 @ApiModelProperty(value = "部门代码")
	 private String deptCode;		// dept_id 部门代码
	 @ApiModelProperty(value = "部门名称")
	 private String deptName;		//deptName 部门名称
	 @ApiModelProperty(value = "员工类型")
	 private String empClass;		// emp_class 员工类型
	 @ApiModelProperty(value = "姓名")
	 private String lastname;		// lastname 姓名
	 @ApiModelProperty(value = "曾用名")
	 private String firstname;		// firstname 曾用名
	 @ApiModelProperty(value = " 地址1")
	 private String address1;		// address1 地址1
	 @ApiModelProperty(value = " 地址2")
	 private String address2;		// address2 地址2
	 @ApiModelProperty(value = "地址3")
	 private String address3;		// address3 地址3
	 @ApiModelProperty(value = "城市名称")
	 private String cityname;		// cityname 城市名称
	 @ApiModelProperty(value = "邮政编码")
	 private String zip;		// zip 邮政编码
	 @ApiModelProperty(value = "家庭电话号码")
	 private String telepno1;		// telepno1 家庭电话号码
	 @ApiModelProperty(value = "工作单位电话号")
	 private String telepno2;		// telepno2 工作单位电话号
	 @ApiModelProperty(value = " 移动电话")
	 private String telepno3;		// telepno3 移动电话
	 @ApiModelProperty(value = "出生日期")
	 private String birthdt;		// birthdt 出生日期
	 @ApiModelProperty(value = "性别")
	 private String sex;		// sex 性别
	 @ApiModelProperty(value = "岗位")
	 private String jobclassNo;		// jobclass_no 岗位
	 @ApiModelProperty(value = "主要工作任务")
	 private String defaproj;		// defaproj 主要工作任务
	 @ApiModelProperty(value = "婚姻状况")
	 private String marriage;		// marriage 婚姻状况
	 @ApiModelProperty(value = "身份证号")
	 private String idCard;		// id_card 身份证号
	 @ApiModelProperty(value = "职称或级别")
	 private String tectitleNo;		// tectitle_no 职称或级别
	 @ApiModelProperty(value = "职业状态")
	 private String empstatus;		// empstatus 职业状态
	 @ApiModelProperty(value = "入职日期")
	 private String employdt;		// employdt 入职日期
	 @ApiModelProperty(value = "离职日期")
	 private String termindt;		// termindt 离职日期
	 @ApiModelProperty(value = "电子邮件")
	 private String email;		// email 电子邮件
	 @ApiModelProperty(value = "是否可用")
	 private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public CtlEmp() {
		super();
	}

	public CtlEmp(String id){
		super(id);
	}

	@Length(min=1, max=20, message="emp_code长度必须介于 1 和 20 之间")
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	@Length(min=0, max=30, message="dept_Code长度必须介于 0 和 30 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Length(min=0, max=60, message="dept_Name长度必须介于 0 和 60 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Length(min=0, max=20, message="emp_class长度必须介于 0 和 20 之间")
	public String getEmpClass() {
		return empClass;
	}

	public void setEmpClass(String empClass) {
		this.empClass = empClass;
	}
	
	@Length(min=0, max=24, message="lastname长度必须介于 0 和 24 之间")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Length(min=0, max=24, message="firstname长度必须介于 0 和 24 之间")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Length(min=0, max=60, message="address1长度必须介于 0 和 60 之间")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@Length(min=0, max=60, message="address2长度必须介于 0 和 60 之间")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Length(min=0, max=60, message="address3长度必须介于 0 和 60 之间")
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	@Length(min=0, max=60, message="cityname长度必须介于 0 和 60 之间")
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	@Length(min=0, max=60, message="zip长度必须介于 0 和 60 之间")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Length(min=0, max=60, message="telepno1长度必须介于 0 和 60 之间")
	public String getTelepno1() {
		return telepno1;
	}

	public void setTelepno1(String telepno1) {
		this.telepno1 = telepno1;
	}
	
	@Length(min=0, max=60, message="telepno2长度必须介于 0 和 60 之间")
	public String getTelepno2() {
		return telepno2;
	}

	public void setTelepno2(String telepno2) {
		this.telepno2 = telepno2;
	}
	
	@Length(min=0, max=60, message="telepno3长度必须介于 0 和 60 之间")
	public String getTelepno3() {
		return telepno3;
	}

	public void setTelepno3(String telepno3) {
		this.telepno3 = telepno3;
	}
	
	@Length(min=0, max=19, message="birthdt长度必须介于 0 和 19 之间")
	public String getBirthdt() {
		return birthdt;
	}

	public void setBirthdt(String birthdt) {
		this.birthdt = birthdt;
	}
	
	@Length(min=0, max=1, message="sex长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=20, message="jobclass_no长度必须介于 0 和 20 之间")
	public String getJobclassNo() {
		return jobclassNo;
	}

	public void setJobclassNo(String jobclassNo) {
		this.jobclassNo = jobclassNo;
	}
	
	@Length(min=0, max=100, message="defaproj长度必须介于 0 和 100 之间")
	public String getDefaproj() {
		return defaproj;
	}

	public void setDefaproj(String defaproj) {
		this.defaproj = defaproj;
	}
	
	@Length(min=0, max=1, message="marriage长度必须介于 0 和 1 之间")
	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	@Length(min=0, max=30, message="id_card长度必须介于 0 和 30 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=60, message="tectitle_no长度必须介于 0 和 60 之间")
	public String getTectitleNo() {
		return tectitleNo;
	}

	public void setTectitleNo(String tectitleNo) {
		this.tectitleNo = tectitleNo;
	}
	
	@Length(min=0, max=2, message="empstatus长度必须介于 0 和 2 之间")
	public String getEmpstatus() {
		return empstatus;
	}

	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}
	
	@Length(min=0, max=19, message="employdt长度必须介于 0 和 19 之间")
	public String getEmploydt() {
		return employdt;
	}

	public void setEmploydt(String employdt) {
		this.employdt = employdt;
	}
	
	@Length(min=0, max=19, message="termindt长度必须介于 0 和 19 之间")
	public String getTermindt() {
		return termindt;
	}

	public void setTermindt(String termindt) {
		this.termindt = termindt;
	}
	
	@Length(min=0, max=60, message="email长度必须介于 0 和 60 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}