package com.sgai.property.notice.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class NoticeInfoTimePublish extends BoEntity<NoticeInfoTimePublish>{

    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否已发布（0：否，1：是）")
    private Long infoIsPublished; //是否已发布（0：否，1：是）
    @ApiModelProperty(value = "发布时间")
    private Long infoPublishTime; //发布时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "计划发布时间")
    private Long infoPublishPlanTime; //计划发布时间

    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getInfoIsPublished(){  
        return infoIsPublished;  
    }
      
   public void setInfoIsPublished(Long infoIsPublished){  
     this.infoIsPublished = infoIsPublished;  
    }  
    public Long getInfoPublishTime(){  
        return infoPublishTime;  
    }
      
   public void setInfoPublishTime(Long infoPublishTime){  
     this.infoPublishTime = infoPublishTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Long getInfoPublishPlanTime(){  
        return infoPublishPlanTime;  
    }
      
   public void setInfoPublishPlanTime(Long infoPublishPlanTime){  
     this.infoPublishPlanTime = infoPublishPlanTime;  
    }  
}