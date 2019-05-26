package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class OrgRecordCard extends BoEntity<OrgRecordCard>{

    @ApiModelProperty(value = "是否删除")
    private Long orcIsDelete; //是否删除
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "机构证件类型名称")
    private String orcCertificateName; //机构证件类型名称
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "机构信息id")
    private String orId; //机构信息id
    @ApiModelProperty(value = "机构证件号")
    private String orcCertificateNo; //机构证件号

    public Long getOrcIsDelete(){  
        return orcIsDelete;  
    }
      
   public void setOrcIsDelete(Long orcIsDelete){  
     this.orcIsDelete = orcIsDelete;  
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
    public String getOrcCertificateName(){  
        return orcCertificateName;  
    }
      
   public void setOrcCertificateName(String orcCertificateName){  
     this.orcCertificateName = orcCertificateName;  
    }
    public String getUpdateUserId(){  
        return updateUserId;  
    }
      
   public void setUpdateUserId(String updateUserId){  
     this.updateUserId = updateUserId;  
    }
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }
    public String getOrId(){  
        return orId;  
    }
      
   public void setOrId(String orId){  
     this.orId = orId;  
    }
    public String getOrcCertificateNo(){  
        return orcCertificateNo;  
    }
      
   public void setOrcCertificateNo(String orcCertificateNo){  
     this.orcCertificateNo = orcCertificateNo;  
    }
}