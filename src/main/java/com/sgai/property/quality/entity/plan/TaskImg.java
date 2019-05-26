package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class TaskImg extends BoEntity<TaskImg>{
     
    @ApiModelProperty(value = "排序")
    private Long imgSort; //排序
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否是默认图片(1:是;0:否)")
    private Long isDefault; //是否是默认图片(1:是;0:否)
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "任务id")
    private String taskId; //任务id
    @ApiModelProperty(value = "图片地址")
    private String imgUrl; //图片地址
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
	
    public Long getImgSort(){  
        return imgSort;  
    }
      
   public void setImgSort(Long imgSort){  
     this.imgSort = imgSort;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getIsDefault(){  
        return isDefault;  
    }
      
   public void setIsDefault(Long isDefault){  
     this.isDefault = isDefault;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getTaskId(){  
        return taskId;  
    }
      
   public void setTaskId(String taskId){  
     this.taskId = taskId;  
    }  
    public String getImgUrl(){  
        return imgUrl;  
    }
      
   public void setImgUrl(String imgUrl){  
     this.imgUrl = imgUrl;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
}