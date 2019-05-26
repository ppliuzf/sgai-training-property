package com.sgai.property.ruag.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 设备运行策略配置Entity
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagWorkModelDatail extends BoEntity<RuagWorkModelDatail> {
	
	private static final long serialVersionUID = 1L;
	private String modelTemplateId;		// 模式ID
	private String workModeName;		// 模式名称
	private String profCode;		// 专业代码
	private String profName;		// 专业名称
	private String deviceCode;		// 设备代码
	private String deviceName;		// 设备名称
	private String spaceCode;		// 空间编码
	private String spaceName;		// 空间名称引用节点类型的数据的名称
	private String oper;		// 操作
	private Date openTime;		// 设备开启时间
	private Date closeTime;		// 设备关闭时间
	private String offsetTimeKind;		// 0:延时1:提前
	private String offsetTime;		// 偏移量(分钟)
	private Long comparePoint;		// 比对时间点  确定该设备的该操作对于  临时模式设置的 开始时间  还是结束时间  ； 0---开始时间，1---结束时间
	private String status;		// 模式状态  未启动--0已启动--1
	private String timeStart;		// 开始时间（临时模式）
	private String timeEnd;		// 结束时间（临时模式）
	private String classCode;    //类型编码
	private String className;    //类型名称
	private List<RuagModelDeviceParamerSet> paramerSets;
	public RuagWorkModelDatail() {
		super();
	}

	public RuagWorkModelDatail(String id){
		super(id);
	}

	@Length(min=0, max=60, message="模式ID长度必须介于 0 和 60 之间")
	public String getModelTemplateId() {
		return modelTemplateId;
	}

	public void setModelTemplateId(String modelTemplateId) {
		this.modelTemplateId = modelTemplateId;
	}
	
	@Length(min=0, max=60, message="模式名称长度必须介于 0 和 60 之间")
	public String getWorkModeName() {
		return workModeName;
	}

	public void setWorkModeName(String workModeName) {
		this.workModeName = workModeName;
	}
	
	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=60, message="专业名称长度必须介于 0 和 60 之间")
	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@Length(min=0, max=60, message="设备代码长度必须介于 0 和 60 之间")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	@Length(min=0, max=60, message="设备名称长度必须介于 0 和 60 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	
	@Length(min=0, max=60, message="操作长度必须介于 0 和 60 之间")
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	
	@Length(min=0, max=60, message="0:延时1:提前长度必须介于 0 和 60 之间")
	public String getOffsetTimeKind() {
		return offsetTimeKind;
	}

	public void setOffsetTimeKind(String offsetTimeKind) {
		this.offsetTimeKind = offsetTimeKind;
	}
	
	public String getOffsetTime() {
		return offsetTime;
	}

	public void setOffsetTime(String offsetTime) {
		this.offsetTime = offsetTime;
	}
	
	public Long getComparePoint() {
		return comparePoint;
	}

	public void setComparePoint(Long comparePoint) {
		this.comparePoint = comparePoint;
	}
	
	@Length(min=0, max=60, message="模式状态  未启动--0已启动--1长度必须介于 0 和 60 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=60, message="开始时间（临时模式）长度必须介于 0 和 60 之间")
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	
	@Length(min=0, max=60, message="结束时间（临时模式）长度必须介于 0 和 60 之间")
	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * paramerSets.
	 *
	 * @return  the paramerSets
	 * @since   JDK 1.8
	 */
	public List<RuagModelDeviceParamerSet> getParamerSets() {
		return paramerSets;
	}

	/**
	 * paramerSets.
	 *
	 * @param   paramerSets    the paramerSets to set
	 * @since   JDK 1.8
	 */
	public void setParamerSets(List<RuagModelDeviceParamerSet> paramerSets) {
		this.paramerSets = paramerSets;
	}
	
}