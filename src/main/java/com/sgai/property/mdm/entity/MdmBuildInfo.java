package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 楼宇描述 ---空间Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmBuildInfo extends BoEntity<MdmBuildInfo> {
	
	private static final long serialVersionUID = 1L;
	private String buildingCode;		// 楼栋编号：系统自动生成
	private String buildingName;		// 楼栋名称
	private String buildArea;		// 建筑面积
	private String useDesc;		// 用途说明
	private Date startDate;		// 开工时间
	private Date endDate;		// 竣工时间
	private String floorCount;		// 楼层
	private Double longiTude;		// 经度
	private Double latiTude;		// 纬度
	private char enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String buildProperty ; // 空间属性
	
	public MdmBuildInfo() {
		super();
	}

	public MdmBuildInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="楼栋编号：系统自动生成长度必须介于 0 和 60 之间")
	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	
	@Length(min=0, max=60, message="楼栋名称长度必须介于 0 和 60 之间")
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	@Length(min=0, max=60, message="建筑面积长度必须介于 0 和 60 之间")
	public String getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	
	@Length(min=0, max=512, message="用途说明长度必须介于 0 和 512 之间")
	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
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
	
	@Length(min=0, max=60, message="楼层长度必须介于 0 和 60 之间")
	public String getFloorCount() {
		return floorCount;
	}

	public void setFloorCount(String floorCount) {
		this.floorCount = floorCount;
	}
	
	public Double getLongiTude() {
		return longiTude;
	}

	public void setLongiTude(Double longiTude) {
		this.longiTude = longiTude;
	}
	
	public Double getLatiTude() {
		return latiTude;
	}

	public void setLatiTude(Double latiTude) {
		this.latiTude = latiTude;
	}
	
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

	public String getBuildProperty() {
		return buildProperty;
	}

	public void setBuildProperty(String buildProperty) {
		this.buildProperty = buildProperty;
	}
	
}