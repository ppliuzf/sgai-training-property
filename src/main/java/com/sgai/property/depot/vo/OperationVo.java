package com.sgai.property.depot.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OperationVo{
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "操作时间")
    private Date operationTime; //操作时间
    @ApiModelProperty(value = "物料操作人员表id")
    private String opeationMatId; //物料操作人员表id
    @ApiModelProperty(value = "操作人")
    private String operationName; //操作人
    @ApiModelProperty(value = "操作类型")
    private String operationType; //操作类型

    public Date getOperationTime(){
        return operationTime;  
    }
      
   public void setOperationTime(Date operationTime){  
     this.operationTime = operationTime;  
    }  
    public String getOpeationMatId(){
        return opeationMatId;  
    }
      
   public void setOpeationMatId(String opeationMatId){  
     this.opeationMatId = opeationMatId;  
    }  
    public String getOperationName(){  
        return operationName;  
    }
      
   public void setOperationName(String operationName){  
     this.operationName = operationName;  
    }  
    public String getOperationType(){  
        return operationType;  
    }
      
   public void setOperationType(String operationType){  
     this.operationType = operationType;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}