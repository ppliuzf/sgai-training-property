package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MatOrderTake extends BoEntity<MatOrderTake>{

    @ApiModelProperty(value = "订单Id")
    private String orderId; //订单Id
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "收货人id")
    private String takeCargoId; //收货人id
    @ApiModelProperty(value = "收货人姓名")
    private String takeCargoName; //收货人姓名
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    private String createdTime;

    public String getOrderId(){  
        return orderId;  
    }
      
   public void setOrderId(String orderId){  
     this.orderId = orderId;  
    }  
    public String getWhId(){  
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public String getTakeCargoId(){  
        return takeCargoId;  
    }
      
   public void setTakeCargoId(String takeCargoId){  
     this.takeCargoId = takeCargoId;  
    }  
    public String getTakeCargoName(){  
        return takeCargoName;  
    }
      
   public void setTakeCargoName(String takeCargoName){  
     this.takeCargoName = takeCargoName;  
    }  
    public String getWhName(){  
        return whName;  
    }
      
   public void setWhName(String whName){  
     this.whName = whName;  
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}