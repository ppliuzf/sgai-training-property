package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class RoomPic extends BoEntity<RoomPic>{
    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
    @ApiModelProperty(value = "图片url")
    private String rpRoomPicUrl; //图片url
    @ApiModelProperty(value = "会议原图片名称")
    private String rpRoomPicName; //会议原图片名称
    @ApiModelProperty(value = "是否主图 1 是 0否")
    private Integer isRoomPicMain; //是否主图 1 是 0否
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除 1 是 0 否")
    private Integer isDelete; //是否删除 1 是 0 否
    public String getRrId(){
        return rrId;  
    }
      
   public void setRrId(String rrId){
     this.rrId = rrId;  
    }  
    public String getRpRoomPicUrl(){  
        return rpRoomPicUrl;  
    }
      
   public void setRpRoomPicUrl(String rpRoomPicUrl){  
     this.rpRoomPicUrl = rpRoomPicUrl;  
    }  
    public String getRpRoomPicName(){  
        return rpRoomPicName;  
    }
      
   public void setRpRoomPicName(String rpRoomPicName){  
     this.rpRoomPicName = rpRoomPicName;  
    }  
    public Integer getIsRoomPicMain(){  
        return isRoomPicMain;  
    }
      
   public void setIsRoomPicMain(Integer isRoomPicMain){  
     this.isRoomPicMain = isRoomPicMain;  
    }  
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
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