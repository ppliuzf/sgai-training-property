package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CustomCardInfo extends BoEntity<CustomCardInfo>{

    @ApiModelProperty(value = "证件id")
    private String ccnId; //证件id
    @ApiModelProperty(value = "客户证件号")
    private String ccCertificateNo; //客户证件号
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "客户证件类型名称")
    private String ccCertificateName; //客户证件类型名称
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "证件名称")
    private String ccnName; //证件名称
    @ApiModelProperty(value = "是否删除")
    private Long ccIsDelete; //是否删除
    @ApiModelProperty(value = "客户信息主键ID")
    private String prId; //客户信息主键ID

    public String getCcnId(){  
        return ccnId;  
    }
      
   public void setCcnId(String ccnId){  
     this.ccnId = ccnId;  
    }
    public String getCcCertificateNo(){  
        return ccCertificateNo;  
    }
      
   public void setCcCertificateNo(String ccCertificateNo){  
     this.ccCertificateNo = ccCertificateNo;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public String getCcCertificateName(){  
        return ccCertificateName;  
    }
      
   public void setCcCertificateName(String ccCertificateName){  
     this.ccCertificateName = ccCertificateName;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
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
    public String getCcnName(){  
        return ccnName;  
    }
      
   public void setCcnName(String ccnName){  
     this.ccnName = ccnName;  
    }
    public Long getCcIsDelete(){  
        return ccIsDelete;  
    }
      
   public void setCcIsDelete(Long ccIsDelete){  
     this.ccIsDelete = ccIsDelete;  
    }
    public String getPrId(){  
        return prId;  
    }
      
   public void setPrId(String prId){  
     this.prId = prId;  
    }
}