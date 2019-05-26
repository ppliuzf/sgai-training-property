package com.sgai.property.quality.entity;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtApprovalRecords extends BoEntity<QtApprovalRecords>{
    @ApiModelProperty(value = "所属任务id")
    private String ttId; //所属任务id
    @ApiModelProperty(value = "发起时间")
    private Long createTime; //发起时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否有效(0:有效,1:无效)")
    private Integer valid; //是否有效(0:有效,1:无效)
    public String getTtId(){
        return ttId;  
    }
      
   public void setTtId(String ttId){
     this.ttId = ttId;  
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
    public Integer getValid(){  
        return valid;  
    }
      
   public void setValid(Integer valid){  
     this.valid = valid;  
    }  
}