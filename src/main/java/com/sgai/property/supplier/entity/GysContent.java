package com.sgai.property.supplier.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class GysContent extends BoEntity<GysContent>{
     
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "内容描述")
    private String description; //内容描述
    @ApiModelProperty(value = "内容名称")
    private String name; //内容名称
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否

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
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getName(){  
        return name;  
    }
      
   public void setName(String name){  
     this.name = name;  
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
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }
}