package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class TaskExtend extends BoEntity<TaskExtend>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "审核意见")
    private String taskApprTarget; //审核意见
    @ApiModelProperty(value = "任务计划详情(分类详情，json对象)")
    private String taskPlanDetail; //任务计划详情(分类详情，json对象)
    @ApiModelProperty(value = "任务id")
    private String taskId; //任务id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "审核情况详情(json)")
    private String taskApprDetail; //审核情况详情(json)
    @ApiModelProperty(value = "审核要求")
    private String taskApprRequire; //审核要求
    @ApiModelProperty(value = "任务完成详情（json）")
    private String taskResult; //任务完成详情（json）
    @ApiModelProperty(value = "关联对象选中的值(JSON)")
    private String associatedObject; //关联对象选中的值(JSON)
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getTaskApprTarget(){  
        return taskApprTarget;  
    }
      
   public void setTaskApprTarget(String taskApprTarget){  
     this.taskApprTarget = taskApprTarget;  
    }  
    public String getTaskPlanDetail(){  
        return taskPlanDetail;  
    }
      
   public void setTaskPlanDetail(String taskPlanDetail){  
     this.taskPlanDetail = taskPlanDetail;  
    }  
    public String getTaskId(){  
        return taskId;  
    }
      
   public void setTaskId(String taskId){  
     this.taskId = taskId;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getTaskApprDetail(){  
        return taskApprDetail;  
    }
      
   public void setTaskApprDetail(String taskApprDetail){  
     this.taskApprDetail = taskApprDetail;  
    }  
    public String getTaskApprRequire(){  
        return taskApprRequire;  
    }
      
   public void setTaskApprRequire(String taskApprRequire){  
     this.taskApprRequire = taskApprRequire;  
    }  
    public String getTaskResult(){  
        return taskResult;  
    }
      
   public void setTaskResult(String taskResult){  
     this.taskResult = taskResult;  
    }  
    public String getAssociatedObject(){  
        return associatedObject;  
    }
      
   public void setAssociatedObject(String associatedObject){  
     this.associatedObject = associatedObject;  
    }  
}