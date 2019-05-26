package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class Input extends BoEntity<Input>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "录入类型（2:支出；1预算）")
    private Long creatorType; //录入类型（2:支出；1预算）
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "录入人id")
    private String creatorEiId; //录入人id
    @ApiModelProperty(value = "录入人名称")
    private String creatorEiEmpName; //录入人名称
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getRecordId(){  
        return recordId;  
    }
      
   public void setRecordId(String recordId){  
     this.recordId = recordId;  
    }  
    public Long getCreatorType(){  
        return creatorType;  
    }
      
   public void setCreatorType(Long creatorType){  
     this.creatorType = creatorType;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getCreatorEiId(){  
        return creatorEiId;  
    }
      
   public void setCreatorEiId(String creatorEiId){  
     this.creatorEiId = creatorEiId;  
    }  
    public String getCreatorEiEmpName(){  
        return creatorEiEmpName;  
    }
      
   public void setCreatorEiEmpName(String creatorEiEmpName){  
     this.creatorEiEmpName = creatorEiEmpName;  
    }  
}