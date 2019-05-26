package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class Summary extends BoEntity<Summary> {
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会人ID")
    private String inviterEiId; //参会人ID
    @ApiModelProperty(value = "参会人名称")
    private String inviterEiName; //参会人名称
    @ApiModelProperty(value = "会议纪要内容")
    private String mtContent; //会议纪要内容
    @ApiModelProperty(value = "可见范围0 自己可见 1 全部可见 ")
    private Integer msShow; //可见范围0 自己可见 1 全部可见 
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0 否")
    private Integer isDelete; //是否删除1 是 0 否
    public String getMiId(){
        return miId;  
    }
      
   public void setMiId(String miId){
     this.miId = miId;  
    }  
    public String getInviterEiId(){  
        return inviterEiId;  
    }
      
   public void setInviterEiId(String inviterEiId){  
     this.inviterEiId = inviterEiId;  
    }  
    public String getInviterEiName(){  
        return inviterEiName;  
    }
      
   public void setInviterEiName(String inviterEiName){  
     this.inviterEiName = inviterEiName;  
    }  
    public String getMtContent(){  
        return mtContent;  
    }
      
   public void setMtContent(String mtContent){  
     this.mtContent = mtContent;  
    }  
    public Integer getMsShow(){  
        return msShow;  
    }
      
   public void setMsShow(Integer msShow){  
     this.msShow = msShow;  
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