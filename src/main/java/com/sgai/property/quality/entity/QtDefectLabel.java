package com.sgai.property.quality.entity;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtDefectLabel extends BoEntity<QtDefectLabel> {
    @ApiModelProperty(value = "标签名称")
    private String dlName; //标签名称
    @ApiModelProperty(value = "创建人ID")
    private String dlCreateId; //创建人ID
    @ApiModelProperty(value = "创建时间")
    private Long dlCreateTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否有效(0有效,1无效)")
    private Integer dlValid; //是否有效(0有效,1无效)
    public String getDlName(){  
        return dlName;  
    }
      
   public void setDlName(String dlName){  
     this.dlName = dlName;  
    }  
    public String getDlCreateId(){  
        return dlCreateId;  
    }
      
   public void setDlCreateId(String dlCreateId){  
     this.dlCreateId = dlCreateId;  
    }  
    public Long getDlCreateTime(){  
        return dlCreateTime;  
    }
      
   public void setDlCreateTime(Long dlCreateTime){  
     this.dlCreateTime = dlCreateTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Integer getDlValid(){  
        return dlValid;  
    }
      
   public void setDlValid(Integer dlValid){  
     this.dlValid = dlValid;  
    }  
}