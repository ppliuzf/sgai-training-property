package com.sgai.property.ctl.entity;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 
* ClassName: CtlDept  
* Description: (部门实体类)
* @author yangyz  
* Date 2017年11月18日  
* Company 首自信--智慧城市创新中心
 */
public class CtlDept extends BoEntity<CtlDept> {
	
	private static final long serialVersionUID = 1L;
	private String deptCode;		// dept_code 部门代码
	private String deptName;		// dept_name 部门名称
	private String parentDeptCode;// parent_dept_code 部门父节点代码
	private String parentDeptName;// parent_dept_name 部门父节点名称
	private String parentsCode;   //  部门父节点代码们
	private String enabledFlag;		// 1.选项为:'Y':是'N':否 默认为'Y'
	private String endFlag;		// end_flag 末级标志
	private Long displayOrder;		// display_order 同层序号
	private String deptSeq;		// dept_seq 部门序列
	private Long deptLevel;		// dept_level 部门级别
	private String deptClass;		// dept_class 部门类型
	private String deptAttr;		// dept_attr 部门属性
	private String deptAbbr;		// dept_abbr 部门简称
	private String deptZjm;		// dept_zjm 部门助记码
	private String deptAddr;		// dept_addr 部门地址
	private String deptTel;		// dept_tel 部门电话
	private String deptFax;		// dept_fax 部门传真
	private String deptResp;		// dept_resp 部门负责人
	private String spaceCode;		//空间编码
	private String spaceName;		//空间名称
	private String profession;  //专业  可以为空
	
	public CtlDept() {
		super();
	}

	public CtlDept(String id){
		super(id);
	}

	@Length(min=1, max=30, message="dept_code长度必须介于 1 和 30 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Length(min=1, max=60, message="dept_name长度必须介于 1 和 60 之间")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Length(min=1, max=30, message="parent_dept_code长度必须介于 1 和 30 之间")
	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}
	
	@Length(min=1, max=60, message="dept_name长度必须介于 1 和 60 之间")
	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
	
	//@选项为:'Y':是'N':否 默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=1, message="end_flag长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	@NotNull(message="display_order不能为空")
	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@Length(min=0, max=120, message="dept_seq长度必须介于 0 和 120 之间")
	public String getDeptSeq() {
		return deptSeq;
	}

	public void setDeptSeq(String deptSeq) {
		this.deptSeq = deptSeq;
	}
	
	public Long getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Long deptLevel) {
		this.deptLevel = deptLevel;
	}
	
	@Length(min=0, max=60, message="dept_class长度必须介于 0 和 60 之间")
	public String getDeptClass() {
		return deptClass;
	}

	public void setDeptClass(String deptClass) {
		this.deptClass = deptClass;
	}
	
	@Length(min=0, max=60, message="dept_attr长度必须介于 0 和 60 之间")
	public String getDeptAttr() {
		return deptAttr;
	}

	public void setDeptAttr(String deptAttr) {
		this.deptAttr = deptAttr;
	}
	
	@Length(min=0, max=20, message="dept_abbr长度必须介于 0 和 20 之间")
	public String getDeptAbbr() {
		return deptAbbr;
	}

	public void setDeptAbbr(String deptAbbr) {
		this.deptAbbr = deptAbbr;
	}
	
	@Length(min=0, max=30, message="dept_zjm长度必须介于 0 和 30 之间")
	public String getDeptZjm() {
		return deptZjm;
	}

	public void setDeptZjm(String deptZjm) {
		this.deptZjm = deptZjm;
	}
	
	@Length(min=0, max=100, message="dept_addr长度必须介于 0 和 100 之间")
	public String getDeptAddr() {
		return deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}
	
	@Length(min=0, max=60, message="dept_tel长度必须介于 0 和 60 之间")
	public String getDeptTel() {
		return deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}
	
	@Length(min=0, max=20, message="dept_fax长度必须介于 0 和 20 之间")
	public String getDeptFax() {
		return deptFax;
	}

	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}
	
	@Length(min=0, max=20, message="dept_resp长度必须介于 0 和 20 之间")
	public String getDeptResp() {
		return deptResp;
	}

	public void setDeptResp(String deptResp) {
		this.deptResp = deptResp;
	}
	

	/**
	 * spaceCode.
	 *
	 * @return  the spaceCode
	 * @since   JDK 1.8
	 */
	public String getSpaceCode() {
		return spaceCode;
	}

	/**
	 * spaceCode.
	 *
	 * @param   spaceCode    the spaceCode to set
	 * @since   JDK 1.8
	 */
	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}

	/**
	 * spaceName.
	 *
	 * @return  the spaceName
	 * @since   JDK 1.8
	 */
	public String getSpaceName() {
		return spaceName;
	}

	/**
	 * spaceName.
	 *
	 * @param   spaceName    the spaceName to set
	 * @since   JDK 1.8
	 */
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	/**
	 * parentsCode.
	 *
	 * @return  the parentsCode
	 * @since   JDK 1.8
	 */
	public String getParentsCode() {
		return parentsCode;
	}

	public void setParentsCode(String parentsCode) {
		this.parentsCode = parentsCode;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
	
}