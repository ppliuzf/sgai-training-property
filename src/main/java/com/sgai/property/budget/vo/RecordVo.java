package com.sgai.property.budget.vo;
import io.swagger.annotations.ApiModelProperty;
public class RecordVo {
	
	@ApiModelProperty(value = "计划id")
	private String id;
    @ApiModelProperty(value = "模板名称")
    private String templateName; //模板名称
    @ApiModelProperty(value = "预算年份")
    private String year; //预算年份
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)")
    private Long state; //计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)
    @ApiModelProperty(value = "计划部门名称")
    private String deptName; //计划部门名称
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "审核人id")
    private String approverId; //审核人id
    @ApiModelProperty(value = "申报人id")
    private String creatorEiId; //申报人id
    @ApiModelProperty(value = "模板ID")
    private String templateId; //模板ID
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "预算类型名称")
    private String typeName; //预算类型名称
    @ApiModelProperty(value = "审核人名称")
    private String approverName; //审核人名称
    @ApiModelProperty(value = "预算周期（1:全年2:半年3季度4:月）")
    private Long cycle; //预算周期（1:全年2:半年3季度4:月）
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    @ApiModelProperty(value = "预算类型id")
    private String typeId; //预算类型id
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
    @ApiModelProperty(value = "审核时间")
    private Long approvalTime; //审核时间
    @ApiModelProperty(value = "申报人名称")
    private String creatorEiEmpName; //申报人名称
    @ApiModelProperty(value = "计划部门id")
    private String deptId; //计划部门id
    @ApiModelProperty(value = "审核原因")
    private String approvalReason; //审核原因
	
    public String getApprovalReason() {
		return approvalReason;
	}

	public void setApprovalReason(String approvalReason) {
		this.approvalReason = approvalReason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplateName(){  
        return templateName;  
    }
      
   public void setTemplateName(String templateName){  
     this.templateName = templateName;  
    }  
    public String getYear(){  
        return year;  
    }
      
   public void setYear(String year){  
     this.year = year;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getState(){  
        return state;  
    }
      
   public void setState(Long state){  
     this.state = state;  
    }  
    public String getDeptName(){  
        return deptName;  
    }
      
   public void setDeptName(String deptName){  
     this.deptName = deptName;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getApproverId(){  
        return approverId;  
    }
      
   public void setApproverId(String approverId){  
     this.approverId = approverId;  
    }  
    public String getCreatorEiId(){  
        return creatorEiId;  
    }
      
   public void setCreatorEiId(String creatorEiId){  
     this.creatorEiId = creatorEiId;  
    }  
    public String getTemplateId(){  
        return templateId;  
    }
      
   public void setTemplateId(String templateId){  
     this.templateId = templateId;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }  
    public String getApproverName(){  
        return approverName;  
    }
      
   public void setApproverName(String approverName){  
     this.approverName = approverName;  
    }  
    public Long getCycle(){  
        return cycle;  
    }
      
   public void setCycle(Long cycle){  
     this.cycle = cycle;  
    }  
    public String getRecordName(){  
        return recordName;  
    }
      
   public void setRecordName(String recordName){  
     this.recordName = recordName;  
    }  
    public String getTypeId(){  
        return typeId;  
    }
      
   public void setTypeId(String typeId){  
     this.typeId = typeId;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }  
    public Long getApprovalTime(){  
        return approvalTime;  
    }
      
   public void setApprovalTime(Long approvalTime){  
     this.approvalTime = approvalTime;  
    }  
    public String getCreatorEiEmpName(){  
        return creatorEiEmpName;  
    }
      
   public void setCreatorEiEmpName(String creatorEiEmpName){  
     this.creatorEiEmpName = creatorEiEmpName;  
    }  
    public String getDeptId(){  
        return deptId;  
    }
      
   public void setDeptId(String deptId){  
     this.deptId = deptId;  
    }  
}