package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CustomLevelInfo extends BoEntity<CustomLevelInfo>{

    @ApiModelProperty(value = "级别名称")
    private String levelName; //级别名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "是否删除")
    private Long clIsDelete; //是否删除
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "描述")
    private String levelDesc; //描述
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "级别状态(0:默认,1:自定义)")
    private Long levelType; //级别状态(0:默认,1:自定义)

    public String getLevelName(){  
        return levelName;  
    }
      
   public void setLevelName(String levelName){  
     this.levelName = levelName;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
    }
    public Long getClIsDelete(){  
        return clIsDelete;  
    }
      
   public void setClIsDelete(Long clIsDelete){  
     this.clIsDelete = clIsDelete;  
    }
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
    }
    public String getLevelDesc(){  
        return levelDesc;  
    }
      
   public void setLevelDesc(String levelDesc){  
     this.levelDesc = levelDesc;  
    }
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }
    public String getUpdateUserId(){  
        return updateUserId;  
    }
      
   public void setUpdateUserId(String updateUserId){  
     this.updateUserId = updateUserId;  
    }
    public Long getLevelType(){  
        return levelType;  
    }
      
   public void setLevelType(Long levelType){  
     this.levelType = levelType;  
    }
}