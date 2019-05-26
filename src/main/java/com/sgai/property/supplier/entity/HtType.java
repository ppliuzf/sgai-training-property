package com.sgai.property.supplier.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class HtType extends BoEntity<HtType>{
     
    @ApiModelProperty(value = "类型名称")
    private String typeName; //类型名称
    @ApiModelProperty(value = "合同规约值限制")
    private Long limitValue; //合同规约值限制
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "类型描述")
    private String typeDescription; //类型描述
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
	
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }  
    public Long getLimitValue(){  
        return limitValue;  
    }
      
   public void setLimitValue(Long limitValue){  
     this.limitValue = limitValue;  
    }  
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
    }  
    public Long getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(Long toonUserId){  
     this.toonUserId = toonUserId;  
    }  
    public String getTypeDescription(){  
        return typeDescription;  
    }
      
   public void setTypeDescription(String typeDescription){  
     this.typeDescription = typeDescription;  
    }  
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
}