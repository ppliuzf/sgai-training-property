package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 楼层描述 ---空间Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmFloorInfo extends BoEntity<MdmFloorInfo> {
	
	private static final long serialVersionUID = 1L;
	private String areaCode;		// 自动生成
	private String buildingCode;		// 楼栋编号：系统自动生成
	private String floorCode;		// 楼层编码：系统自动生成
	private String floorName;		// 楼层名称
	private String floorDesc;		// 楼层定位
	private char enabledFlag;		// 是否能用的标志
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String floorProperty; // 空间属性
	
	public MdmFloorInfo() {
		super();
	}

	public MdmFloorInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="自动生成长度必须介于 0 和 60 之间")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Length(min=0, max=60, message="楼栋编号：系统自动生成长度必须介于 0 和 60 之间")
	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	
	@Length(min=0, max=60, message="楼层编码：系统自动生成长度必须介于 0 和 60 之间")
	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}
	
	@Length(min=0, max=60, message="楼层名称长度必须介于 0 和 60 之间")
	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	
	@Length(min=0, max=60, message="楼层定位长度必须介于 0 和 60 之间")
	public String getFloorDesc() {
		return floorDesc;
	}

	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
	
	@Length(min=1, max=1, message="是否能用的标志长度必须介于 1 和 1 之间")
	public char getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(char enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFloorProperty() {
		return floorProperty;
	}

	public void setFloorProperty(String floorProperty) {
		this.floorProperty = floorProperty;
	}
	
}