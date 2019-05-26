package com.sgai.property.em.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
    * @ClassName: EmResourceDispatch  
    * @com.sgai.property.commonService.vo;(应急资源调度Entity)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
public class EmResourceDispatch extends BoEntity<EmResourceDispatch> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 保修事件投诉事件报警事件
	@ApiModelProperty(value = "group leader")
	private String groupLeader;		// group leader
	@ApiModelProperty(value = "group leader2")
	private String groupLeader2;		// group leader2
	@ApiModelProperty(value = "成员")
	private String members;		// members
	@ApiModelProperty(value = "group leader 名称")
	private String groupLeaderName;		// group leader 名称
	@ApiModelProperty(value = "group leader2 名称")
	private String groupLeader2Name;		// group leader2 名称
	@ApiModelProperty(value = "成员名称")
	private String membersName;		// 成员名称
	@ApiModelProperty(value = "应急专家")
	private String specialist;		// 应急专家
	@ApiModelProperty(value = "应急专家名称")
	private String specialistName;		// 应急专家名称
	@ApiModelProperty(value = "是否完成")
	private String endFlag;		// 未完成：N完成：Y
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号
	@ApiModelProperty(value = "修改时间")
	private Date updatedDt;		// 修改时间
	@ApiModelProperty(value = "修改人")
	private String updatedBy;		// 修改人
	@ApiModelProperty(value = "创建日期")
	private Date createdDt;		// 创建日期
	@ApiModelProperty(value = "创建者")
	private String createdBy;		// 创建者
	
	
	@ApiModelProperty(value = "处理情况")
	private String repairContent;		// 处理情况
	@ApiModelProperty(value = "处理时间")
	private Date repairDatetime;		// 处理时间
	public EmResourceDispatch() {
		super();
	}

	public EmResourceDispatch(String id){
		super(id);
	}

	@Length(min=0, max=64, message="instance_id长度必须介于 0 和 64 之间")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	@Length(min=0, max=32, message="对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等长度必须介于 0 和 32 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=32, message="保修事件投诉事件报警事件长度必须介于 0 和 32 之间")
	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}
	
	@Length(min=0, max=60, message="group leader长度必须介于 0 和 60 之间")
	public String getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
	
	@Length(min=0, max=60, message="group leader2长度必须介于 0 和 60 之间")
	public String getGroupLeader2() {
		return groupLeader2;
	}

	public void setGroupLeader2(String groupLeader2) {
		this.groupLeader2 = groupLeader2;
	}
	
	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}
	
	@Length(min=0, max=1, message="未完成：N完成：Y长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
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

	@Length(min=0, max=60, message="维修内容长度必须介于 0 和 60 之间")
	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairDatetime() {
		return repairDatetime;
	}

	public void setRepairDatetime(Date repairDatetime) {
		this.repairDatetime = repairDatetime;
	}

	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getGroupLeaderName() {
		return groupLeaderName;
	}

	public void setGroupLeaderName(String groupLeaderName) {
		this.groupLeaderName = groupLeaderName;
	}

	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getGroupLeader2Name() {
		return groupLeader2Name;
	}

	public void setGroupLeader2Name(String groupLeader2Name) {
		this.groupLeader2Name = groupLeader2Name;
	}

	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getMembersName() {
		return membersName;
	}

	public void setMembersName(String membersName) {
		this.membersName = membersName;
	}

	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	
	@Length(min=0, max=60, message="members长度必须介于 0 和 60 之间")
	public String getSpecialistName() {
		return specialistName;
	}

	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}
	
}