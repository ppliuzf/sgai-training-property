package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class ExpensesItem extends BoEntity<ExpensesItem>{
     
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "费项编号")
    private String itemCode; //费项编号
    @ApiModelProperty(value = "是否删除(0：否，1：是)")
    private Long isDelete; //是否删除(0：否，1：是)
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
    @ApiModelProperty(value = "状态(0：启用，1：禁用)")
    private Long state; //状态(0：启用，1：禁用)
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
	
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getItemCode(){  
        return itemCode;  
    }
      
   public void setItemCode(String itemCode){  
     this.itemCode = itemCode;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getItemName(){  
        return itemName;  
    }
      
   public void setItemName(String itemName){  
     this.itemName = itemName;  
    }  
    public Long getState(){  
        return state;  
    }
      
   public void setState(Long state){  
     this.state = state;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }  
}