package com.sgai.property.supplier.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class GysLevel extends BoEntity<GysLevel>{
     
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "等级名称")
    private String name; //等级名称
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "是否删除；1:是;0:否;3:默认值,可编辑不可删除")
    private Long isDelete; //是否删除；1:是;0:否;3:默认值,可编辑不可删除
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "等级描述")
    private String description; //等级描述
	
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
    public String getName(){  
        return name;  
    }
      
   public void setName(String name){  
     this.name = name;  
    }  
    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(Long toonUserId){  
     this.toonUserId = toonUserId;  
    }  
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }  
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }
}