package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

public class TypeVo extends BaseVo{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "计划类型描述")
    private String typeDesc; //计划类型描述
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "计划类型名称")
    private String typeName; //计划类型名称
    @ApiModelProperty(value = "计划类型编码(0:默认;1:自定义)")
    private Long typeCode; //计划类型编码(0:默认;1:自定义)
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id

	public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getTypeDesc(){  
        return typeDesc;  
    }
      
   public void setTypeDesc(String typeDesc){  
     this.typeDesc = typeDesc;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }  
    public Long getTypeCode(){  
        return typeCode;  
    }
      
   public void setTypeCode(Long typeCode){  
     this.typeCode = typeCode;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }  
}