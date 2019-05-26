package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class Operation extends BoEntity<Operation>{

    @ApiModelProperty(value = "")
    private String moduCode; //
    @ApiModelProperty(value = "订单号")
    private String orderNumber; //订单号
    @ApiModelProperty(value = "操作时间")
    private Date operationTime; //操作时间
    @ApiModelProperty(value = "")
    private String comCode; //
    @ApiModelProperty(value = "物料操作人员表id")
    private String opeationMatId; //物料操作人员表id
    @ApiModelProperty(value = "操作人")
    private String operationName; //操作人
    @ApiModelProperty(value = "操作类型")
    private String operationType; //操作类型

    public String getModuCode(){  
        return moduCode;  
    }
      
   public void setModuCode(String moduCode){  
     this.moduCode = moduCode;  
    }  
    public String getOrderNumber(){  
        return orderNumber;  
    }
      
   public void setOrderNumber(String orderNumber){  
     this.orderNumber = orderNumber;  
    }  
    public Date getOperationTime(){  
        return operationTime;  
    }
      
   public void setOperationTime(Date operationTime){  
     this.operationTime = operationTime;  
    }  
    public String getComCode(){  
        return comCode;  
    }
      
   public void setComCode(String comCode){  
     this.comCode = comCode;  
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
}