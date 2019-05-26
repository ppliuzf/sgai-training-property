package com.sgai.property.ctl.entity;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlProg  
    * Description: (功能实体类)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlProg extends BoEntity<CtlProg> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "功能代码")
	private String progCode;		// 1.系统内唯一的功能代码,结合表和处理的动作来起名字2.比如:  供应商维护 pur_supplier；  采购方式定义 pur_activeStyleDefine；  供货项目参数设定 pur_itemParDefine
	@ApiModelProperty(value = "子系统代码")
	private String sbsCode;		// sbs_code 子系统代码
	@ApiModelProperty(value = "子系统名称")
	private String sbsName;		//sbs_name 子系统名称
	@ApiModelProperty(value = "功能名称")
	private String progName;		// 1.如:sfc_parameter 功能名称
	@ApiModelProperty(value = "功能路径")
	private String progPath;		// prog_path 功能路径
	@ApiModelProperty(value = "功能级别")
	private String progLevel;		// 1.选项为:'S':系统级'M':机构管理级'C':企业级'B':按钮级&ldquo;增加、删除等操作&rdquo;2.系统级功能只能由用户root来维护，并且也不能参与公司功能分配；  企业管理级别：默认公司信息主管可操作  企业普通级别：默认一般用户可操作。比如，子系统定义、菜单定义、功能定义等
	@ApiModelProperty(value = "功能类型")
	private String progType;		// 1.选项为:'A':所有用户'I':只授权给内部用户'O':只授权给外部用户默认为'A'2.在授权时可以更方便地过滤功能
	@ApiModelProperty(value = "日志级别")
	private Long logLevel;		// 1.只记录&lt;=系统参数表中设置的   日志级别
	@ApiModelProperty(value = "父功能代码")
	private String parentProgCode;		// 父功能代码
	@ApiModelProperty(value = "系统初始化标识")
	private String sysFlag;		// sys_flag   is_sys
	@ApiModelProperty(value = "可用标识")
	private String initCheckFlag;		// 1.选项为:'Y':是'N':否默认为 系统初始化标志
	@ApiModelProperty(value = "每页显示行数")
	private Long linesPerpage;		// lines_perpage 每页显示行数
	@ApiModelProperty(value = "备注")
	private String remark;		// remark 备注
	public CtlProg() {
		super();
	}

	public CtlProg(String id){
		super(id);
	}

	//@比如:  供应商维护 pur_supplier；  采购方式定义 pur_activeStyleDefine；  供货项目参数设定 pur_itemParDefine长度必须介于 1 和 60 之间")
	public String getProgCode() {
		return progCode;
	}

	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	
	@Length(min=1, max=10, message="sbs_code长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=1, max=10, message="sbs_name长度必须介于 1 和 10 之间")
	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}

	//@如:sfc_parameter长度必须介于 0 和 60 之间")
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}
	
	@Length(min=1, max=255, message="prog_path长度必须介于 1 和 255 之间")
	public String getProgPath() {
		return progPath;
	}

	public void setProgPath(String progPath) {
		this.progPath = progPath;
	}
	
	//@系统级功能只能由用户root来维护，并且也不能参与公司功能分配；  企业管理级别：默认公司信息主管可操作  企业普通级别：默认一般用户可操作。比如，子系统定义、菜单定义、功能定义等长度必须介于 1 和 1 之间")
	public String getProgLevel() {
		return progLevel;
	}

	public void setProgLevel(String progLevel) {
		this.progLevel = progLevel;
	}
	
	//@在授权时可以更方便地过滤功能长度必须介于 1 和 1 之间")
	public String getProgType() {
		return progType;
	}

	public void setProgType(String progType) {
		this.progType = progType;
	}
	
	//@只记录&lt;=系统参数表中设置的不能为空")
	public Long getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Long logLevel) {
		this.logLevel = logLevel;
	}
	
	@Length(min=0, max=60, message="父功能代码长度必须介于 0 和 60 之间")
	public String getParentProgCode() {
		return parentProgCode;
	}

	public void setParentProgCode(String parentProgCode) {
		this.parentProgCode = parentProgCode;
	}
	
	@Length(min=0, max=1, message="sys_flag长度必须介于 0 和 1 之间")
	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}
	
	//@选项为:'Y':是'N':否默认为长度必须介于 1 和 1 之间")
	public String getInitCheckFlag() {
		return initCheckFlag;
	}

	public void setInitCheckFlag(String initCheckFlag) {
		this.initCheckFlag = initCheckFlag;
	}
	
	@NotNull(message="lines_perpage不能为空")
	public Long getLinesPerpage() {
		return linesPerpage;
	}

	public void setLinesPerpage(Long linesPerpage) {
		this.linesPerpage = linesPerpage;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}