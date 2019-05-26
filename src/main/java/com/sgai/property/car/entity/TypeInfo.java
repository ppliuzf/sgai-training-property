package com.sgai.property.car.entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class TypeInfo extends BoEntity<TypeInfo>{
     
    @ApiModelProperty(value = "类型名称")
    private String ctName; //类型名称
    @ApiModelProperty(value = "类型描述")
    private String ctDesc; //类型描述
    @ApiModelProperty(value = "是否删除 0:可用 1:删除")
    @JsonIgnore
    private Long ctIsDelete; //是否删除 0:可用 1:删除
    @ApiModelProperty(value = "是否默认0:默认1:自定义")
    private Long ctStatus; //是否默认0:默认1:自定义
	
    public String getCtName(){  
        return ctName;  
    }
      
   public void setCtName(String ctName){  
     this.ctName = ctName;  
    }  
    public String getCtDesc(){  
        return ctDesc;  
    }
      
   public void setCtDesc(String ctDesc){  
     this.ctDesc = ctDesc;  
    }  
    public Long getCtIsDelete(){  
        return ctIsDelete;  
    }
      
   public void setCtIsDelete(Long ctIsDelete){  
     this.ctIsDelete = ctIsDelete;  
    }  
    public Long getCtStatus(){  
        return ctStatus;  
    }
      
   public void setCtStatus(Long ctStatus){  
     this.ctStatus = ctStatus;  
    }  
}