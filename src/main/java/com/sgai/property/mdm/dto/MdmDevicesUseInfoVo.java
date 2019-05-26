package com.sgai.property.mdm.dto;

import com.sgai.common.persistence.BoEntity;
import com.sgai.property.util.ExcelField;

/**
 * ClassName:MdmDevicesUseInfoVo <br/>
 *  ADD FUNCTION. <br/>
 * Reason:	  REASON. <br/>
 * Date:     2017年11月30日 下午3:36:01 <br/>
 * @author   yangyz
 * @version
 * @since    JDK 1.8
 */
public class MdmDevicesUseInfoVo extends BoEntity<MdmDevicesUseInfoVo>{

	private static final long serialVersionUID = 1L;
	private String id;				//主键
	@ExcelField(title = "设备编码",type = 1,sort = 1,align = 2)
	private String devicesCode;		// 业主编号
	@ExcelField(title = "设备名称",type = 1,sort = 1,align = 2)
	private String devicesName;		// 设备名称
	@ExcelField(title = "专业名称",type = 1,sort = 1,align = 2)
	private String profName;		// 专业名称
	@ExcelField(title = "设备类型",type = 1,sort = 1,align = 2)
	private String className;		// 类型名称
	private String spaceCode;		// 类型名称
	@ExcelField(title = "设备品牌",type = 1,sort = 1,align = 2)
	private String brandName;		// 品牌名称
	@ExcelField(title = "设备型号",type = 1,sort = 1,align = 2)
	private String devicesModel;	// 设备型号
	@ExcelField(title = "供应商",type = 1,sort = 1,align = 2)
	private String supplierName;		// 供应商名称
	@ExcelField(title = "联系电话",type = 1,sort = 1,align = 2)
	private String telPhone;		// 联系电话
	@ExcelField(title = "空间名称",type = 1,sort = 1,align = 2)
	private String spaceName;		// 空间名称引用节点类型的数据的名称
	@ExcelField(title = "生产日期",type = 1,sort = 1,align = 2)
	private String outDate;		// 生产日期
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String remarks;	// 备注
	private String profCode;	// 备注
	private String modelCode;//设备型号

	private String classCode;	// 备注
	private String ip;          //ip
	private String x;          //横坐标
	private String y;          //纵坐标
	private String path;
	private String parentCode;
	private String spaceDesc;

	private String states ;    //报警状态

	private String attrName ;// 属性名称
	private String attrValue ;// 属性值
	private String paramStr ; //查询参数 字符串
	private String selectStr ;//查询字段 字符串



	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}
	public String getSelectStr() {
		return selectStr;
	}
	public void setSelectStr(String selectStr) {
		this.selectStr = selectStr;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getSpaceDesc() {
		return spaceDesc;
	}
	public void setSpaceDesc(String spaceDesc) {
		this.spaceDesc = spaceDesc;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.8
	 */
	public String getId() {
		return id;
	}
	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * devicesCode.
	 *
	 * @return  the devicesCode
	 * @since   JDK 1.8
	 */
	public String getDevicesCode() {
		return devicesCode;
	}
	/**
	 * devicesCode.
	 *
	 * @param   devicesCode    the devicesCode to set
	 * @since   JDK 1.8
	 */
	public void setDevicesCode(String devicesCode) {
		this.devicesCode = devicesCode;
	}
	/**
	 * devicesName.
	 *
	 * @return  the devicesName
	 * @since   JDK 1.8
	 */
	public String getDevicesName() {
		return devicesName;
	}
	/**
	 * devicesName.
	 *
	 * @param   devicesName    the devicesName to set
	 * @since   JDK 1.8
	 */
	public void setDevicesName(String devicesName) {
		this.devicesName = devicesName;
	}
	/**
	 * profName.
	 *
	 * @return  the profName
	 * @since   JDK 1.8
	 */
	public String getProfName() {
		return profName;
	}
	/**
	 * profName.
	 *
	 * @param   profName    the profName to set
	 * @since   JDK 1.8
	 */
	public void setProfName(String profName) {
		this.profName = profName;
	}
	/**
	 * className.
	 *
	 * @return  the className
	 * @since   JDK 1.8
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * className.
	 *
	 * @param   className    the className to set
	 * @since   JDK 1.8
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * brandName.
	 *
	 * @return  the brandName
	 * @since   JDK 1.8
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * brandName.
	 *
	 * @param   brandName    the brandName to set
	 * @since   JDK 1.8
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * devicesModel.
	 *
	 * @return  the devicesModel
	 * @since   JDK 1.8
	 */
	public String getDevicesModel() {
		return devicesModel;
	}
	/**
	 * devicesModel.
	 *
	 * @param   devicesModel    the devicesModel to set
	 * @since   JDK 1.8
	 */
	public void setDevicesModel(String devicesModel) {
		this.devicesModel = devicesModel;
	}
	/**
	 * supplierName.
	 *
	 * @return  the supplierName
	 * @since   JDK 1.8
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * supplierName.
	 *
	 * @param   supplierName    the supplierName to set
	 * @since   JDK 1.8
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * telPhone.
	 *
	 * @return  the telPhone
	 * @since   JDK 1.8
	 */
	public String getTelPhone() {
		return telPhone;
	}
	/**
	 * telPhone.
	 *
	 * @param   telPhone    the telPhone to set
	 * @since   JDK 1.8
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
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
	 * outDate.
	 *
	 * @return  the outDate
	 * @since   JDK 1.8
	 */
	public String getOutDate() {
		return outDate;
	}
	/**
	 * outDate.
	 *
	 * @param   outDate    the outDate to set
	 * @since   JDK 1.8
	 */
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	/**
	 * enabledFlag.
	 *
	 * @return  the enabledFlag
	 * @since   JDK 1.8
	 */
	public String getEnabledFlag() {
		return enabledFlag;
	}
	/**
	 * enabledFlag.
	 *
	 * @param   enabledFlag    the enabledFlag to set
	 * @since   JDK 1.8
	 */
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * remarks.
	 *
	 * @return  the remarks
	 * @since   JDK 1.8
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * remarks.
	 *
	 * @param   remarks    the remarks to set
	 * @since   JDK 1.8
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProfCode() {
		return profCode;
	}
	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getSpaceCode() {
		return spaceCode;
	}
	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}


}

