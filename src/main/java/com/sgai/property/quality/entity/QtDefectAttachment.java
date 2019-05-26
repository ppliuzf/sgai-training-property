package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtDefectAttachment extends BoEntity<QtDefectAttachment> {
    @ApiModelProperty(value = "跟工单操作相对应")
    private Integer daSource; //跟工单操作相对应
    @ApiModelProperty(value = "业务ID")
    private String businessId; //业务ID
    @ApiModelProperty(value = "附件地址")
    private String daUrl; //附件地址
    @ApiModelProperty(value = "创建时间")
    private Long daCreateTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "附件类型")
    private String daType; //附件类型

    public Integer getDaSource(){  
        return daSource;  
    }
      
   public void setDaSource(Integer daSource){ 
     this.daSource = daSource;  
    }  
    public String getBusinessId(){
        return businessId;  
    }
      
   public void setBusinessId(String businessId){
     this.businessId = businessId;  
    }  
    public String getDaUrl(){  
        return daUrl;  
    }
      
   public void setDaUrl(String daUrl){  
     this.daUrl = daUrl;  
    }  
    public Long getDaCreateTime(){  
        return daCreateTime;  
    }
      
   public void setDaCreateTime(Long daCreateTime){  
     this.daCreateTime = daCreateTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getDaType(){  
        return daType;  
    }
      
   public void setDaType(String daType){  
     this.daType = daType;  
    }  
}