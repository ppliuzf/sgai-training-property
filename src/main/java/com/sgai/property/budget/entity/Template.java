package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class Template extends BoEntity<Template>{
     
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "模板名称")
    private String templateName; //模板名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "创建者姓名")
    private String creatorEiEmpName; //创建者姓名
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "模板内容（json串）")
    private String content; //模板内容（json串）
    @ApiModelProperty(value = "创建者员工Id")
    private String creatorEiId; //创建者员工Id
    @ApiModelProperty(value = "状态(0:启用1:禁用)")
    private Long state; //状态(0:启用1:禁用)
    @ApiModelProperty(value = "周期类型（1:全年2:半年3季度4:月）")
    private Long cycle; //周期类型（1:全年2:半年3季度4:月）
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
	
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getTemplateName(){  
        return templateName;  
    }
      
   public void setTemplateName(String templateName){  
     this.templateName = templateName;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getCreatorEiEmpName(){  
        return creatorEiEmpName;  
    }
      
   public void setCreatorEiEmpName(String creatorEiEmpName){  
     this.creatorEiEmpName = creatorEiEmpName;  
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
    public String getContent(){  
        return content;  
    }
      
   public void setContent(String content){  
     this.content = content;  
    }  
    public String getCreatorEiId(){  
        return creatorEiId;  
    }
      
   public void setCreatorEiId(String creatorEiId){  
     this.creatorEiId = creatorEiId;  
    }  
    public Long getState(){  
        return state;  
    }
      
   public void setState(Long state){  
     this.state = state;  
    }  
    public Long getCycle(){  
        return cycle;  
    }
      
   public void setCycle(Long cycle){  
     this.cycle = cycle;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }  
}