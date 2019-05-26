package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class WorkorderCost extends BoEntity<WorkorderCost>{

    @ApiModelProperty(value = "是否删除(0,否:1.是)")
    private Long isDelete;
    @ApiModelProperty(value = "单价(元)")
    private Long wdPrice;
    @ApiModelProperty(value = "费用名称")
    private String wdCostName;
    @ApiModelProperty(value = "描述")
    private String wdDesc;
    @ApiModelProperty(value = "单位")
    private String wdUnit;

    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }
    public Long getWdPrice(){  
        return wdPrice;  
    }
      
   public void setWdPrice(Long wdPrice){  
     this.wdPrice = wdPrice;  
    }
    public String getWdCostName(){  
        return wdCostName;  
    }
      
   public void setWdCostName(String wdCostName){  
     this.wdCostName = wdCostName;  
    }
    public String getWdDesc(){  
        return wdDesc;  
    }
      
   public void setWdDesc(String wdDesc){  
     this.wdDesc = wdDesc;  
    }
    public String getWdUnit(){  
        return wdUnit;  
    }
      
   public void setWdUnit(String wdUnit){  
     this.wdUnit = wdUnit;  
    }
}