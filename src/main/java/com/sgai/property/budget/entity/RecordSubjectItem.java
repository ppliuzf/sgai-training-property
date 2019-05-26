package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class RecordSubjectItem extends BoEntity<RecordSubjectItem>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "科目长名称")
    private String subLongName; //科目长名称
    @ApiModelProperty(value = "科目名称")
    private String subName; //科目名称
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "费项排序")
    private Long itemOrder; //费项排序
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
    @ApiModelProperty(value = "科目编号")
    private String subCode; //科目编号
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getSubLongName(){  
        return subLongName;  
    }
      
   public void setSubLongName(String subLongName){  
     this.subLongName = subLongName;  
    }  
    public String getSubName(){  
        return subName;  
    }
      
   public void setSubName(String subName){  
     this.subName = subName;  
    }  
    public String getRecordId(){  
        return recordId;  
    }
      
   public void setRecordId(String recordId){  
     this.recordId = recordId;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Long getItemOrder(){  
        return itemOrder;  
    }
      
   public void setItemOrder(Long itemOrder){  
     this.itemOrder = itemOrder;  
    }  
    public String getItemName(){  
        return itemName;  
    }
      
   public void setItemName(String itemName){  
     this.itemName = itemName;  
    }  
    public String getSubCode(){  
        return subCode;  
    }
      
   public void setSubCode(String subCode){  
     this.subCode = subCode;  
    }  
}