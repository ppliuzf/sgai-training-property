package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CustomTypeInfo extends BoEntity<CustomTypeInfo>{

    @ApiModelProperty(value = "类型分类(1 个人 2 机构)")
    private Long typeClass; //类型分类(1 个人 2 机构)
    @ApiModelProperty(value = "类型名称")
    private String typeName; //类型名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "状态(0:默认,1:自定义)")
    private Long typeStatus; //状态(0:默认,1:自定义)
    @ApiModelProperty(value = "更新人名称")
    private String updateUserName; //更新人名称
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "更新人id")
    private String updateUserId; //更新人id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "类型描述")
    private String typeDesc; //类型描述
    @ApiModelProperty(value = "是否删除")
    private Long ctIsDelete; //是否删除

    public Long getTypeClass(){  
        return typeClass;  
    }
      
   public void setTypeClass(Long typeClass){  
     this.typeClass = typeClass;  
    }
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public Long getTypeStatus(){  
        return typeStatus;  
    }
      
   public void setTypeStatus(Long typeStatus){  
     this.typeStatus = typeStatus;  
    }
    public String getUpdateUserName(){  
        return updateUserName;  
    }
      
   public void setUpdateUserName(String updateUserName){  
     this.updateUserName = updateUserName;  
    }
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
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
    public String getTypeDesc(){  
        return typeDesc;  
    }
      
   public void setTypeDesc(String typeDesc){  
     this.typeDesc = typeDesc;  
    }
    public Long getCtIsDelete(){  
        return ctIsDelete;  
    }
      
   public void setCtIsDelete(Long ctIsDelete){  
     this.ctIsDelete = ctIsDelete;  
    }
}