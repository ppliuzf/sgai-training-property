package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtTaskItemGroup  extends BoEntity<QtTaskItemGroup> {
    @ApiModelProperty(value = "分组名称")
    private String tigName; //分组名称
    @ApiModelProperty(value = "所属任务id")
    private String ttId; //所属任务id
    @ApiModelProperty(value = "分组在方案中排序")
    private Integer tigSort; //分组在方案中排序
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除(1:已删除,0:未删除)")
    private Integer valid; //是否删除(1:已删除,0:未删除)
    public String getTigName(){  
        return tigName;  
    }
      
   public void setTigName(String tigName){  
     this.tigName = tigName;  
    }  
    public String getTtId(){
        return ttId;  
    }
      
   public void setTtId(String ttId){
     this.ttId = ttId;  
    }  
    public Integer getTigSort(){  
        return tigSort;  
    }
      
   public void setTigSort(Integer tigSort){  
     this.tigSort = tigSort;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Integer getValid(){  
        return valid;  
    }
      
   public void setValid(Integer valid){  
     this.valid = valid;  
    }  
}