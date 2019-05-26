package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

public class WarehouseOpterationMatVo{

    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "实际数量")
    private Long matCount; //实际数量

    public String getMatName(){
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
    }

    public Long getMatCount() {
        return matCount;
    }

    public void setMatCount(Long matCount) {
        this.matCount = matCount;
    }
}