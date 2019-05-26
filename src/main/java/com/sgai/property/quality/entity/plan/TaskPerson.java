package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class TaskPerson extends BoEntity<TaskPerson>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "人员头像")
    private String personIcon; //人员头像
    @ApiModelProperty(value = "人员类型（1,责任人；2审核人）")
    private Long personType; //人员类型（1,责任人；2审核人）
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "人员姓名")
    private String eiEmpName; //人员姓名
    @ApiModelProperty(value = "任务id")
    private String taskId; //任务id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "toon_user_id")
    private String toonUserId; //toon_user_id
    @ApiModelProperty(value = "人员id")
    private String eiId; //人员id
    @ApiModelProperty(value = "人员相关北京通code")
    private String bjtoonCode; //人员相关北京通code
    @ApiModelProperty(value = "名片id")
    private String feedId; //名片id
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getPersonIcon(){  
        return personIcon;  
    }
      
   public void setPersonIcon(String personIcon){  
     this.personIcon = personIcon;  
    }  
    public Long getPersonType(){  
        return personType;  
    }
      
   public void setPersonType(Long personType){  
     this.personType = personType;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getEiEmpName(){  
        return eiEmpName;  
    }
      
   public void setEiEmpName(String eiEmpName){  
     this.eiEmpName = eiEmpName;  
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
    public String getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(String toonUserId){  
     this.toonUserId = toonUserId;  
    }  
    public String getUserNo(){
        return eiId;  
    }
      
   public void setEiId(String eiId){  
     this.eiId = eiId;  
    }  
    public String getBjtoonCode(){  
        return bjtoonCode;  
    }
      
   public void setBjtoonCode(String bjtoonCode){  
     this.bjtoonCode = bjtoonCode;  
    }  
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
}