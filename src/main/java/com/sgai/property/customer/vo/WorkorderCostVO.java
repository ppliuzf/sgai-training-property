package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class WorkorderCostVO implements Serializable{

     
      
	    /**  
	    * @Fields field:field:(用一句话描述这个变量表示什么)
	    */  
	    
	private static final long serialVersionUID = -5243289253001564362L;
	@ApiModelProperty(value = "单价(元)")
    private Long wdPrice;
    @ApiModelProperty(value = "费用名称")
    private String wdCostName;
    @ApiModelProperty(value = "描述")
    private String wdDesc;
    @ApiModelProperty(value = "单位")
    private String wdUnit;
    @ApiModelProperty(value = "主键ID")
    private String id;
    
     
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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