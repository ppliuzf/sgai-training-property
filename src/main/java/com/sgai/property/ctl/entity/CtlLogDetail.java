package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.sgai.common.persistence.BoEntity;
import com.sgai.common.utils.excel.annotation.ExcelField;

/**
 * &quot;在线用户管理关联查询实体类&quot;Entity
 * @author guanze
 * @version 2017-11-09
 */
public class CtlLogDetail extends BoEntity<CtlLogDetail> {
	
	private static final long serialVersionUID = 1L;
	private String sessionId;		// session_id
	private String comCode;		// com_id
	private String comName;		// com_name
	private String userCode;		// user_id
	private String userName;		// user_name
	private String userType;		// user_type
	private String corrCode;		// corr_id
	private String corrName;		// corr_name
	private String runTime;		// run_time
	private String sbsCode;		// sbs_id
	private String sbsName;		// sbs_name
	private String menuCode;		// menu_id
	private String menuName;		// menu_name
	private String progCode;		// prog_id
	private String progName;		// prog_name
	private String beginCreatedDt;		// 开始 创建日期
	private String endCreatedDt;		// 结束 创建日期
	private Date loginTime;		// 登陆时间login_time
	
	public CtlLogDetail() {
		super();
	}

	public CtlLogDetail(String id){
		super(id);
	}

	@ExcelField(title="SeesionId", type=1, align=2, sort=1)
	@Length(min=1, max=60, message="session_id长度必须介于 1 和 60 之间")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@ExcelField(title="公司ID", type=1, align=2, sort=2)
	@Length(min=1, max=10, message="com_id长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComId(String comCode) {
		this.comCode = comCode;
	}
	
	@ExcelField(title="公司名称", type=1, align=2, sort=3)
	@Length(min=0, max=60, message="com_name长度必须介于 0 和 60 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@Length(min=0, max=30, message="user_id长度必须介于 0 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@ExcelField(title="用户名称", type=1, align=2, sort=4)
	@Length(min=0, max=60, message="user_name长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@ExcelField(title="用户类型", type=1, align=2, sort=5)
	@Length(min=1, max=1, message="user_type长度必须介于 1 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=60, message="corr_id长度必须介于 0 和 60 之间")
	public String getCorrCode() {
		return corrCode;
	}

	public void setCorrCode(String corrCode) {
		this.corrCode = corrCode;
	}
	
	@ExcelField(title="操作", type=1, align=2, sort=6)
	@Length(min=0, max=60, message="corr_name长度必须介于 0 和 60 之间")
	public String getCorrName() {
		return corrName;
	}

	public void setCorrName(String corrName) {
		this.corrName = corrName;
	}
	
	@ExcelField(title="运行时间", type=1, align=2, sort=7)
	@Length(min=1, max=19, message="run_time长度必须介于 1 和 19 之间")
	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	@Length(min=1, max=10, message="sbs_id长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	
	@ExcelField(title="子系统名称", type=1, align=2, sort=8)
	@Length(min=0, max=60, message="sbs_name长度必须介于 0 和 60 之间")
	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}
	
	@Length(min=1, max=30, message="menu_id长度必须介于 1 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@ExcelField(title="菜单名称", type=1, align=2, sort=9)
	@Length(min=0, max=60, message="menu_name长度必须介于 0 和 60 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Length(min=1, max=60, message="prog_id长度必须介于 1 和 60 之间")
	public String getProgCode() {
		return progCode;
	}

	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	
	@ExcelField(title="模块名称", type=1, align=2, sort=10)
	@Length(min=0, max=60, message="prog_name长度必须介于 0 和 60 之间")
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}
	
	
	public String getBeginCreatedDt() {
		return beginCreatedDt;
	}

	public void setBeginCreatedDt(String beginCreatedDt) {
		this.beginCreatedDt = beginCreatedDt;
	}
	
	public String getEndCreatedDt() {
		return endCreatedDt;
	}

	public void setEndCreatedDt(String endCreatedDt) {
		this.endCreatedDt = endCreatedDt;
	}
		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="登陆时间login_time不能为空")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
