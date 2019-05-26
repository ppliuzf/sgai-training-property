package com.sgai.property.car.entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class GearBoxType extends BoEntity<GearBoxType>{
     
    @ApiModelProperty(value = "是否默认0:默认1:自定义")
    private Long btStatus; //是否默认0:默认1:自定义
    @ApiModelProperty(value = "类型名称")
    private String btName; //类型名称
    @ApiModelProperty(value = "类型描述")
    private String btDesc; //类型描述
    @ApiModelProperty(value = "是否删除 0:可用 1:删除")
    @JsonIgnore
    private Long btIsDelete; //是否删除 0:可用 1:删除
	
    public Long getBtStatus(){  
        return btStatus;  
    }
      
   public void setBtStatus(Long btStatus){  
     this.btStatus = btStatus;  
    }  
    public String getBtName(){  
        return btName;  
    }
      
   public void setBtName(String btName){  
     this.btName = btName;  
    }  
    public String getBtDesc(){  
        return btDesc;  
    }
      
   public void setBtDesc(String btDesc){  
     this.btDesc = btDesc;  
    }  
    public Long getBtIsDelete(){  
        return btIsDelete;  
    }
      
   public void setBtIsDelete(Long btIsDelete){  
     this.btIsDelete = btIsDelete;  
    }  
}