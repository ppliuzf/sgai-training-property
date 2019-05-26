package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class RecordActor extends BoEntity<RecordActor>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "参与者员工id")
    private String eiId; //参与者员工id
    @ApiModelProperty(value = "员工姓名")
    private String eiEmpName; //员工姓名
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "toon_user_id")
    private String toonUserId; //toon_user_id
    @ApiModelProperty(value = "参与者北京通号")
    private String bjtoonCode; //参与者北京通号
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    @ApiModelProperty(value = "名片id")
    private String feedId; //名片id
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getRecordId(){  
        return recordId;  
    }
      
   public void setRecordId(String recordId){  
     this.recordId = recordId;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getUserNo(){
        return eiId;  
    }
      
   public void setEiId(String eiId){  
     this.eiId = eiId;  
    }  
    public String getEiEmpName(){  
        return eiEmpName;  
    }
      
   public void setEiEmpName(String eiEmpName){  
     this.eiEmpName = eiEmpName;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(String toonUserId){  
     this.toonUserId = toonUserId;  
    }  
    public String getBjtoonCode(){  
        return bjtoonCode;  
    }
      
   public void setBjtoonCode(String bjtoonCode){  
     this.bjtoonCode = bjtoonCode;  
    }  
    public String getRecordName(){  
        return recordName;  
    }
      
   public void setRecordName(String recordName){  
     this.recordName = recordName;  
    }  
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
}