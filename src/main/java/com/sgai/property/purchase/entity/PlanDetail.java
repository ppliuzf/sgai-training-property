package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class PlanDetail extends BoEntity<PlanDetail>{

    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "物料类型ID")
    private String matTypeId; //物料类型ID
    @ApiModelProperty(value = "任务Id")
    private String taskId; //任务Id
    @ApiModelProperty(value = "需求数量")
    private Long applyCount; //需求数量
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号

    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
    }  
    public String getMatSpec(){  
        return matSpec;  
    }
      
   public void setMatSpec(String matSpec){  
     this.matSpec = matSpec;  
    }  
    public Long getBuyCount(){  
        return buyCount;  
    }
      
   public void setBuyCount(Long buyCount){  
     this.buyCount = buyCount;  
    }  
    public String getMatTypeId(){  
        return matTypeId;  
    }
      
   public void setMatTypeId(String matTypeId){  
     this.matTypeId = matTypeId;  
    }  
    public String getTaskId(){  
        return taskId;  
    }
      
   public void setTaskId(String taskId){  
     this.taskId = taskId;  
    }  
    public Long getApplyCount(){  
        return applyCount;  
    }
      
   public void setApplyCount(Long applyCount){  
     this.applyCount = applyCount;  
    }  
    public String getMatTypeCode(){  
        return matTypeCode;  
    }
      
   public void setMatTypeCode(String matTypeCode){  
     this.matTypeCode = matTypeCode;  
    }  
}