package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RecordVo extends BaseVo{
     
    @ApiModelProperty(value = "应用范围名称")
    private String applicationScopeName; //应用范围名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "创建者姓名")
    private String creatorEiEmpName; //创建者姓名
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "任务管理者(1:责任人;2:参与者)")
    private Long recordManager; //任务管理者(1:责任人;2:参与者)
    @ApiModelProperty(value = "计划类型id")
    private String typeId; //计划类型id
    @ApiModelProperty(value = "计划描述")
    private String recordDesc; //计划描述
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "责任岗位名称")
    private String postName; //责任岗位名称
    @ApiModelProperty(value = "应用范围ID")
    private String applicationScopeId; //应用范围ID
    @ApiModelProperty(value = "计划类型名称")
    private String typeName; //计划类型名称
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    @ApiModelProperty(value = "创建者员工id")
    private String creatorEiId; //创建者员工id
    @ApiModelProperty(value = "责任岗位id")
    private String postId; //责任岗位id
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
    
    @ApiModelProperty(value = "参与者姓名")
    private List<String> actorNames;
    
    @ApiModelProperty(value = "阶段任务列表")
    private List<PeriodTaskVo> periodTaskList;

    @ApiModelProperty(value = "任务计划详情")
    private List<TaskTemplateVo> taskTemplateVos;//任务计划详情


    @ApiModelProperty(value = "是否有重复执行 0 没有  1 有")
    private Long taskFlag;//是否有重复执行 0 没有  1 有
    @ApiModelProperty(value = "选择重复标示：1 选择天  2 选择周  3 选择月")
    private Long optionFlag;

    @ApiModelProperty(value = "0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日")
    private String taskDay; //0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日

    
    public String getApplicationScopeName(){  
        return applicationScopeName;  
    }
      
   public void setApplicationScopeName(String applicationScopeName){  
     this.applicationScopeName = applicationScopeName;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getCreatorEiEmpName(){  
        return creatorEiEmpName;  
    }
      
   public void setCreatorEiEmpName(String creatorEiEmpName){  
     this.creatorEiEmpName = creatorEiEmpName;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getRecordManager(){  
        return recordManager;  
    }
      
   public void setRecordManager(Long recordManager){  
     this.recordManager = recordManager;  
    }  
    public String getTypeId(){  
        return typeId;  
    }
      
   public void setTypeId(String typeId){  
     this.typeId = typeId;  
    }  
    public String getRecordDesc(){  
        return recordDesc;  
    }
      
   public void setRecordDesc(String recordDesc){  
     this.recordDesc = recordDesc;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getPostName(){  
        return postName;  
    }
      
   public void setPostName(String postName){  
     this.postName = postName;  
    }  
    public String getApplicationScopeId(){  
        return applicationScopeId;  
    }
      
   public void setApplicationScopeId(String applicationScopeId){  
     this.applicationScopeId = applicationScopeId;  
    }  
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }  
    public String getRecordName(){  
        return recordName;  
    }
      
   public void setRecordName(String recordName){  
     this.recordName = recordName;  
    }  
    public String getCreatorEiId(){  
        return creatorEiId;  
    }
      
   public void setCreatorEiId(String creatorEiId){  
     this.creatorEiId = creatorEiId;  
    }  
    public String getPostId(){  
        return postId;  
    }
      
   public void setPostId(String postId){  
     this.postId = postId;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }

	public List<String> getActorNames() {
		return actorNames;
	}
	
	public void setActorNames(List<String> actorNames) {
		this.actorNames = actorNames;
	}

	public List<PeriodTaskVo> getPeriodTaskList() {
		return periodTaskList;
	}

	public void setPeriodTaskList(List<PeriodTaskVo> periodTaskList) {
		this.periodTaskList = periodTaskList;
	}

    public List<TaskTemplateVo> getTaskTemplateVos() {
        return taskTemplateVos;
    }

    public void setTaskTemplateVos(List<TaskTemplateVo> taskTemplateVos) {
        this.taskTemplateVos = taskTemplateVos;
    }

    public Long getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Long taskFlag) {
        this.taskFlag = taskFlag;
    }

    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    public String getTaskDay() {
        return taskDay;
    }

    public void setTaskDay(String taskDay) {
        this.taskDay = taskDay;
    }
}