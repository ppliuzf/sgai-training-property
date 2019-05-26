package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class RoomDevice extends BoEntity<RoomDevice>{
    @ApiModelProperty(value = "会议室设备名称")
    private String rdRoomDevice; //会议室设备名称
    @ApiModelProperty(value = "会议室设备状态(初始,自定义)")
    private Integer rrState; //会议室设备状态(初始,自定义)
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0否")
    private Integer isDelete; //是否删除1 是 0否

    public String getRdRoomDevice(){  
        return rdRoomDevice;  
    }
      
   public void setRdRoomDevice(String rdRoomDevice){  
     this.rdRoomDevice = rdRoomDevice;  
    }  
    public Integer getRrState(){  
        return rrState;  
    }
      
   public void setRrState(Integer rrState){  
     this.rrState = rrState;  
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