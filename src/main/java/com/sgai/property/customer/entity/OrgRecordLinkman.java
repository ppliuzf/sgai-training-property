package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class OrgRecordLinkman extends BoEntity<OrgRecordLinkman>{

    @ApiModelProperty(value = "联系人电话")
    private String orlPhone; //联系人电话
    @ApiModelProperty(value = "联系人名称")
    private String orlLinkman; //联系人名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "机构信息id")
    private String orId; //机构信息id
    @ApiModelProperty(value = "是否删除")
    private Long orlIsDelete; //是否删除

    public String getOrlPhone(){  
        return orlPhone;  
    }
      
   public void setOrlPhone(String orlPhone){  
     this.orlPhone = orlPhone;  
    }
    public String getOrlLinkman(){  
        return orlLinkman;  
    }
      
   public void setOrlLinkman(String orlLinkman){  
     this.orlLinkman = orlLinkman;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
    }
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }
    public String getUpdateUserId(){  
        return updateUserId;  
    }
      
   public void setUpdateUserId(String updateUserId){  
     this.updateUserId = updateUserId;  
    }
    public String getOrId(){  
        return orId;  
    }
      
   public void setOrId(String orId){  
     this.orId = orId;  
    }
    public Long getOrlIsDelete(){  
        return orlIsDelete;  
    }
      
   public void setOrlIsDelete(Long orlIsDelete){  
     this.orlIsDelete = orlIsDelete;  
    }
}