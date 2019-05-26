package com.sgai.property.ctl.entity;
import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
    * ClassName: CtlRole  
    * Description: (角色实体类)
    * @author 王天尧  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlRole extends BoEntity<CtlRole> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "角色代码")
	private String roleCode;		// 角色代码
	@ApiModelProperty(value = "角色类型")
	private String roleType;		// 角色类型
	@ApiModelProperty(value = "系统描述")
	private String roleDesc;		// 角色描述
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String profession ; //专业  可以为空
	
	public CtlRole() {
		super();
	}

	public CtlRole(String id){
		super(id);
	}
	@Length(min=1, max=20, message="role_code长度必须介于 1 和 20 之间")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	@Length(min=0, max=10, message="role_type长度必须介于 0 和 10 之间")
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	@Length(min=0, max=60, message="角色描述长度必须介于 0 和 60 之间")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	
	
	
}