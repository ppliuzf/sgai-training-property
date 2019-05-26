package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class PersonalRecordInfo extends BoEntity<PersonalRecordInfo>{

    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "手机号2")
    private Long prPhoneSecond; //手机号2
    @ApiModelProperty(value = "性别(0 男 1 女)")
    private Long prSex; //性别(0 男 1 女)
    @ApiModelProperty(value = "出生日期")
    private Long prBirth; //出生日期
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "手机号1")
    private Long prPhoneFirst; //手机号1
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "客户名称")
    private String prName; //客户名称
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "是否删除")
    private Long prIsDelete; //是否删除
    @ApiModelProperty(value = "邮箱")
    private String prEmail; //邮箱
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "部门ID")
    private String deptId; //部门ID
    @ApiModelProperty(value = "部门名称")
    private String deptName; //部门名称
    
    
    public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public Long getPrPhoneSecond(){  
        return prPhoneSecond;  
    }
      
   public void setPrPhoneSecond(Long prPhoneSecond){  
     this.prPhoneSecond = prPhoneSecond;  
    }
    public Long getPrSex(){  
        return prSex;  
    }
      
   public void setPrSex(Long prSex){  
     this.prSex = prSex;  
    }
    public Long getPrBirth(){  
        return prBirth;  
    }
      
   public void setPrBirth(Long prBirth){  
     this.prBirth = prBirth;  
    }
    public String getCtId(){  
        return ctId;  
    }
      
   public void setCtId(String ctId){  
     this.ctId = ctId;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
    }
    public Long getPrPhoneFirst(){  
        return prPhoneFirst;  
    }
      
   public void setPrPhoneFirst(Long prPhoneFirst){  
     this.prPhoneFirst = prPhoneFirst;  
    }
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
    }
    public String getPrName(){  
        return prName;  
    }
      
   public void setPrName(String prName){  
     this.prName = prName;  
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
    public Long getPrIsDelete(){  
        return prIsDelete;  
    }
      
   public void setPrIsDelete(Long prIsDelete){  
     this.prIsDelete = prIsDelete;  
    }
    public String getPrEmail(){  
        return prEmail;  
    }
      
   public void setPrEmail(String prEmail){  
     this.prEmail = prEmail;  
    }
    public String getClId(){  
        return clId;  
    }
      
   public void setClId(String clId){  
     this.clId = clId;  
    }
}