package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class RoomPosition extends BoEntity<RoomPosition>{
    @ApiModelProperty(value = "位置名称")
    private String rpPositionName; //位置名称
    @ApiModelProperty(value = "描述")
    private String rpPositionDesc; //描述
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0 否")
    private Integer isDelete; //是否删除1 是 0 否
    public String getRpPositionName(){  
        return rpPositionName;  
    }
      
   public void setRpPositionName(String rpPositionName){  
     this.rpPositionName = rpPositionName;  
    }  
    public String getRpPositionDesc(){  
        return rpPositionDesc;  
    }
      
   public void setRpPositionDesc(String rpPositionDesc){  
     this.rpPositionDesc = rpPositionDesc;  
    }  
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Integer getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Integer isDelete){  
     this.isDelete = isDelete;  
    }  
}