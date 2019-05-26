package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class Record extends BoEntity<Record>{
     
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
	
    @ApiModelProperty(value = "开始时间")
    private Long startTime; //创建时间
    @ApiModelProperty(value = "结束时间")
    private Long endTime; //创建时间

    @ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
    private Integer typeFlag;



    
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

public Long getStartTime() {
	return startTime;
}

public void setStartTime(Long startTime) {
	this.startTime = startTime;
}

public Long getEndTime() {
	return endTime;
}

public void setEndTime(Long endTime) {
	this.endTime = endTime;
}

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }
}