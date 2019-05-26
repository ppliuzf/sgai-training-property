package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 房间描述 ---空间Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmRoomInfo extends BoEntity<MdmRoomInfo> {
	
	private static final long serialVersionUID = 1L;
	private String roomCode;		// 房间编号:系统自动生成
	private String roomName;		// 房间名称
	private String buildingCode;		// 楼栋编号
	private String bulidingName;		// 楼栋名称
	private Double roomArea;		// 建筑面积
	private String spatProperty;		// 空间属性：办公区办公室接待区T字路口十字路口
	private char enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String roomProperty; //空间属性
	
	public MdmRoomInfo() {
		super();
	}

	public MdmRoomInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="房间编号:系统自动生成长度必须介于 0 和 60 之间")
	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	
	@Length(min=0, max=60, message="房间名称长度必须介于 0 和 60 之间")
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Length(min=0, max=60, message="楼栋编号长度必须介于 0 和 60 之间")
	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	
	@Length(min=0, max=60, message="楼栋名称长度必须介于 0 和 60 之间")
	public String getBulidingName() {
		return bulidingName;
	}

	public void setBulidingName(String bulidingName) {
		this.bulidingName = bulidingName;
	}
	
	public Double getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(Double roomArea) {
		this.roomArea = roomArea;
	}
	
	@Length(min=0, max=60, message="空间属性：办公区办公室接待区T字路口十字路口长度必须介于 0 和 60 之间")
	public String getSpatProperty() {
		return spatProperty;
	}

	public void setSpatProperty(String spatProperty) {
		this.spatProperty = spatProperty;
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

	public String getRoomProperty() {
		return roomProperty;
	}

	public void setRoomProperty(String roomProperty) {
		this.roomProperty = roomProperty;
	}
	
}