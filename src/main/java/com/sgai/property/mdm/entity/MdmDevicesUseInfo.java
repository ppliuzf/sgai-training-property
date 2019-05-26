package com.sgai.property.mdm.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmDevicesUseInfo  
    * com.sgai.property.commonService.vo;(设备主数据实体类)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmDevicesUseInfo extends BoEntity<MdmDevicesUseInfo> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "设备编号")
	private String devicesCode;		// 业主编号
	@ApiModelProperty(value = "设备名称")
	private String devicesName;		// 设备名称
	@ApiModelProperty(value = "型号编码")
	private String modelCode;		// 型号编码
	@ApiModelProperty(value = "设备型号")
	private String devicesModel;		// 设备型号
	@ApiModelProperty(value = "类型编码")
	private String classCode;		// 类型编码
	@ApiModelProperty(value = "类型名称")
	private String className;		// 类型名称
	@ApiModelProperty(value = "生产日期")
	private Date outDate;		// 生产日期
	@ApiModelProperty(value = "供应商编号")
	private String supplierNo;		// 供应商编号
	@ApiModelProperty(value = "供应商名称")
	private String supplierName;		// 供应商名称
	@ApiModelProperty(value = "联系电话")
	private String telPhone;		// 联系电话，可以从供应商获取，允许修改！
	@ApiModelProperty(value = "空间代码")
	private String spaceCode;		// 空间编码
	@ApiModelProperty(value = "空间名称")
	private String spaceName;		// 空间名称引用节点类型的数据的名称
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String ip;
	private String x;
	private String y;
	private String path;
    private String parentCode;//父类设备编码
    private String spaceDesc;//位置描述
	
	
	
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public MdmDevicesUseInfo() {
		super();
	}

	public MdmDevicesUseInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="业主编号长度必须介于 0 和 60 之间")
	public String getDevicesCode() {
		return devicesCode;
	}

	public void setDevicesCode(String devicesCode) {
		this.devicesCode = devicesCode;
	}
	
	@Length(min=0, max=60, message="设备名称长度必须介于 0 和 60 之间")
	public String getDevicesName() {
		return devicesName;
	}

	public void setDevicesName(String devicesName) {
		this.devicesName = devicesName;
	}
	
	@Length(min=0, max=60, message="型号编码长度必须介于 0 和 60 之间")
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	@Length(min=0, max=60, message="设备型号长度必须介于 0 和 60 之间")
	public String getDevicesModel() {
		return devicesModel;
	}

	public void setDevicesModel(String devicesModel) {
		this.devicesModel = devicesModel;
	}
	
	@Length(min=0, max=60, message="类型编码长度必须介于 0 和 60 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=60, message="类型名称长度必须介于 0 和 60 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@Length(min=0, max=64, message="供应商编号长度必须介于 0 和 64 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Length(min=0, max=128, message="供应商名称长度必须介于 0 和 128 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=60, message="联系电话，可以从供应商获取，允许修改！长度必须介于 0 和 60 之间")
	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	
	@Length(min=0, max=60, message="空间编码长度必须介于 0 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
	@Length(min=0, max=60, message="空间名称引用节点类型的数据的名称长度必须介于 0 和 60 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
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
	
	
	
}