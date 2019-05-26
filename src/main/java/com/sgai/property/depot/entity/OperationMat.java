package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class OperationMat extends BoEntity<OperationMat>{

    @ApiModelProperty(value = "操作人员表的id")
    private String operationId; //操作人员表的id
    @ApiModelProperty(value = "")
    private String moduCode; //
    @ApiModelProperty(value = "操作时间")
    private Date operationTime; //
    @ApiModelProperty(value = "")
    private String comCode; //
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "订单号")
    private String oderNumber; //订单号
    @ApiModelProperty(value = "")
    private Long matCount; //

    public String getOperationId(){  
        return operationId;  
    }
      
   public void setOperationId(String operationId){  
     this.operationId = operationId;  
    }  
    public String getModuCode(){  
        return moduCode;  
    }
      
   public void setModuCode(String moduCode){  
     this.moduCode = moduCode;  
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
    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
    }  
    public String getOderNumber(){  
        return oderNumber;  
    }
      
   public void setOderNumber(String oderNumber){  
     this.oderNumber = oderNumber;  
    }  
    public Long getMatCount(){
        return matCount;  
    }
      
   public void setMatCount(Long matCount){
     this.matCount = matCount;  
    }  
}