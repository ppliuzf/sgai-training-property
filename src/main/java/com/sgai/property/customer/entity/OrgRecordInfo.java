package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class OrgRecordInfo extends BoEntity<OrgRecordInfo>{

    @ApiModelProperty(value = "注册地址")
    private String orRegistAddress; //注册地址
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "企业法人")
    private String orCompanyLegal; //企业法人
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "注册资金")
    private String orRegistFund; //注册资金
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "经营范围")
    private String orBisScope; //经营范围
    @ApiModelProperty(value = "经营期限开始日期")
    private Long bisStartDate; //经营期限开始日期
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID
    @ApiModelProperty(value = "公司名称")
    private String orCompanyName; //公司名称
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "是否删除")
    private Long orIsDelete; //是否删除
    @ApiModelProperty(value = "经营期限结束日期")
    private Long bisEndDate; //经营期限结束日期
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "注册日期")
    private Long registDate; //注册日期

    public String getOrRegistAddress(){  
        return orRegistAddress;  
    }
      
   public void setOrRegistAddress(String orRegistAddress){  
     this.orRegistAddress = orRegistAddress;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public String getOrCompanyLegal(){  
        return orCompanyLegal;  
    }
      
   public void setOrCompanyLegal(String orCompanyLegal){  
     this.orCompanyLegal = orCompanyLegal;  
    }
    public String getCtId(){  
        return ctId;  
    }
      
   public void setCtId(String ctId){  
     this.ctId = ctId;  
    }
    public String getOrRegistFund(){  
        return orRegistFund;  
    }
      
   public void setOrRegistFund(String orRegistFund){  
     this.orRegistFund = orRegistFund;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
    }
    public String getOrBisScope(){  
        return orBisScope;  
    }
      
   public void setOrBisScope(String orBisScope){  
     this.orBisScope = orBisScope;  
    }
    public Long getBisStartDate(){  
        return bisStartDate;  
    }
      
   public void setBisStartDate(Long bisStartDate){  
     this.bisStartDate = bisStartDate;  
    }
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }
    public String getOrCompanyName(){  
        return orCompanyName;  
    }
      
   public void setOrCompanyName(String orCompanyName){  
     this.orCompanyName = orCompanyName;  
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
    public Long getOrIsDelete(){  
        return orIsDelete;  
    }
      
   public void setOrIsDelete(Long orIsDelete){  
     this.orIsDelete = orIsDelete;  
    }
    public Long getBisEndDate(){  
        return bisEndDate;  
    }
      
   public void setBisEndDate(Long bisEndDate){  
     this.bisEndDate = bisEndDate;  
    }
    public String getClId(){  
        return clId;  
    }
      
   public void setClId(String clId){  
     this.clId = clId;  
    }
    public Long getRegistDate(){  
        return registDate;  
    }
      
   public void setRegistDate(Long registDate){  
     this.registDate = registDate;  
    }
}