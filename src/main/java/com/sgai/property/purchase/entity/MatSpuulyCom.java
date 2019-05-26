package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MatSpuulyCom extends BoEntity<MatSpuulyCom>{

    @ApiModelProperty(value = "供应商名称")
    private String comName; //供应商名称

    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
    }  
}