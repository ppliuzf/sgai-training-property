package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehouseAllotImg extends BoEntity<WarehouseAllotImg>{

    @ApiModelProperty(value = "图片地址")
    private String allotImgUrl; //图片地址
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id

    public String getAllotImgUrl(){  
        return allotImgUrl;  
    }
      
   public void setAllotImgUrl(String allotImgUrl){  
     this.allotImgUrl = allotImgUrl;  
    }  
    public String getAllotId(){  
        return allotId;  
    }
      
   public void setAllotId(String allotId){  
     this.allotId = allotId;  
    }  
}