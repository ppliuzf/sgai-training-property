package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class MailConfigure extends BoEntity<MailConfigure> {
    @ApiModelProperty(value = "邮件账户")
    private String mcAccount; //邮件账户
    @ApiModelProperty(value = "邮箱密码")
    private String mcPassword; //邮箱密码
    @ApiModelProperty(value = "服务器地址")
    private String mcIp; //服务器地址
    @ApiModelProperty(value = "服务器端口")
    private Integer mcPort; //服务器端口
    @ApiModelProperty(value = "收件看到名称")
    private String mcEmailName; //收件看到名称
    @ApiModelProperty(value = "是否需要发送状态 1 是 0否")
    private Integer isSend; //是否需要发送状态 1 是 0否
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0否")
    private Integer isDelete; //是否删除1 是 0否
    public String getMcAccount(){  
        return mcAccount;  
    }
      
   public void setMcAccount(String mcAccount){  
     this.mcAccount = mcAccount;  
    }  
    public String getMcPassword(){  
        return mcPassword;  
    }
      
   public void setMcPassword(String mcPassword){  
     this.mcPassword = mcPassword;  
    }  
    public String getMcIp(){  
        return mcIp;  
    }
      
   public void setMcIp(String mcIp){  
     this.mcIp = mcIp;  
    }  
    public Integer getMcPort(){  
        return mcPort;  
    }
      
   public void setMcPort(Integer mcPort){  
     this.mcPort = mcPort;  
    }  
    public String getMcEmailName(){  
        return mcEmailName;  
    }
      
   public void setMcEmailName(String mcEmailName){  
     this.mcEmailName = mcEmailName;  
    }  
    public Integer getIsSend(){  
        return isSend;  
    }
      
   public void setIsSend(Integer isSend){  
     this.isSend = isSend;  
    }  
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
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
    public Integer getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Integer isDelete){  
     this.isDelete = isDelete;  
    }  
}