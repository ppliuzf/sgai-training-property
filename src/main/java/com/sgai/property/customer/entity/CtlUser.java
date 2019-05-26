package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
 * 
    * ClassName: CtlUser  
    * Description: (用户实体类)
    * @author 王天尧  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlUser extends BoEntity<CtlUser> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户代码")
	private String userCode;		// 用户代码
	@ApiModelProperty(value = "用户名称")
	private String userName;		// 用户名称
	@ApiModelProperty(value = "机构id")
	private String comId;		// 保存创建机构id
	@ApiModelProperty(value = "用户密码")
	private String userPass;		// 用户密码
	@ApiModelProperty(value = "用户类型")
	private String userType;		// 1.选项为:
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Long status;		// Y：可用N：不可用
	@ApiModelProperty(value = "有效开始时间")
	private Date startDate;		// 有效开始日期
	@ApiModelProperty(value = "有效结束时间")
	private Date endDate;		// 有效结束日期
	@ApiModelProperty(value = "角色代码")
	private String roleCode;		// 角色代码
	@ApiModelProperty(value = "ip限制标识")
	private String ipLimitedFlag;		// ip限制标识
	@ApiModelProperty(value = "多线程显示标识")
	private String pnLimitedFlag;		// 多线程显示标识
	@ApiModelProperty(value = "允许多线程数")
	private Long permitNum;		// 允许多线程数
	@ApiModelProperty(value = "员工代码")
	private String corrCode;		// 员工代码
	@ApiModelProperty(value = "员工名称")
	private String corrName;		// 角色名称：如超级用户、管理员、普通用户
	@ApiModelProperty(value = "认证模式")
	private String authMode;		// A:CA认证B:URL认证
	@ApiModelProperty(value = "菜单类型")
	private String menuType;		// default：outlookmenu：menun
	@ApiModelProperty(value = "颜色类型")
	private String colorType;		// red:红色系blue：蓝色系
	@ApiModelProperty(value = "登录错误次数")
	private Long errCount;		// 登录错误次数
	private String lang;		// cn:汉语en:英语
	private String remarks2;		// 备注
	private String comName;     //机构名称
	private String deptCode; //部门地址
	
	public CtlUser() {
		super();
	}

	public CtlUser(String id){
		super(id);
	}

	@Length(min=1, max=30, message="user_code长度必须介于 1 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=60, message="user_name长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=60, message="保存创建机构id长度必须介于 0 和 60 之间")
	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}
	@Length(min=0, max=128, message="user_pass长度必须介于 0 和 128 之间")
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=20, message="role_code长度必须介于 0 和 20 之间")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getIpLimitedFlag() {
		return ipLimitedFlag;
	}

	public void setIpLimitedFlag(String ipLimitedFlag) {
		this.ipLimitedFlag = ipLimitedFlag;
	}
	
	@Length(min=0, max=1, message="pn_limited_flag长度必须介于 0 和 1 之间")
	public String getPnLimitedFlag() {
		return pnLimitedFlag;
	}

	public void setPnLimitedFlag(String pnLimitedFlag) {
		this.pnLimitedFlag = pnLimitedFlag;
	}
	
	public Long getPermitNum() {
		return permitNum;
	}

	public void setPermitNum(Long permitNum) {
		this.permitNum = permitNum;
	}
	
	@Length(min=0, max=60, message="=登录代码长度必须介于 0 和 60 之间")
	public String getCorrCode() {
		return corrCode;
	}

	public void setCorrCode(String corrCode) {
		this.corrCode = corrCode;
	}
	
	@Length(min=0, max=60, message="角色名称：如超级用户、管理员、普通用户长度必须介于 0 和 60 之间")
	public String getCorrName() {
		return corrName;
	}

	public void setCorrName(String corrName) {
		this.corrName = corrName;
	}
	
	@Length(min=0, max=2, message="A:CA认证B:URL认证长度必须介于 0 和 2 之间")
	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}
	
	@Length(min=0, max=60, message="default：outlookmenu：menun长度必须介于 0 和 60 之间")
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	@Length(min=0, max=30, message="red:红色系blue：蓝色系长度必须介于 0 和 30 之间")
	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}
	
	public Long getErrCount() {
		return errCount;
	}

	public void setErrCount(Long errCount) {
		this.errCount = errCount;
	}
	
	@Length(min=0, max=30, message="cn:汉语en:英语长度必须介于 0 和 30 之间")
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	@Length(min=0, max=512, message="备注长度必须介于 0 和 512 之间")
	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	/**
	 * deptCode.
	 *
	 * @return  the deptCode
	 * @since   JDK 1.8
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * deptCode.
	 *
	 * @param   deptCode    the deptCode to set
	 * @since   JDK 1.8
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}