package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class MaininfoInviter extends BoEntity<MaininfoInviter> {
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会人ID")
    private String inviterEiId; //参会人ID
    @ApiModelProperty(value = "参会人名称")
    private String inviterEiName; //参会人名称
    @ApiModelProperty(value = "参会人节点")
    private String nodeTree; //参会人节点
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0  否")
    private Integer isDelete; //是否删除1 是 0  否

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
    public String getNodeTree(){  
        return nodeTree;  
    }
      
   public void setNodeTree(String nodeTree){  
     this.nodeTree = nodeTree;  
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