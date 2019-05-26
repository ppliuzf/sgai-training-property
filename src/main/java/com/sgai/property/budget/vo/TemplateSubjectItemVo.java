package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class TemplateSubjectItemVo extends BaseVo{
     
    @ApiModelProperty(value = "模板名称")
    private String templateName; //模板名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "科目长名称")
    private String subLongName; //科目长名称
    @ApiModelProperty(value = "科目名称")
    private String subName; //科目名称
    @ApiModelProperty(value = "费项id")
    private String itemId; //费项id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "费项排序")
    private Long itemOrder; //费项排序
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
    @ApiModelProperty(value = "科目编号")
    private String subCode; //科目编号
    @ApiModelProperty(value = "科目id")
    private String subId; //科目id
    @ApiModelProperty(value = "模板id")
    private String templateId; //模板id
	
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
    public String getSubLongName(){  
        return subLongName;  
    }
      
   public void setSubLongName(String subLongName){  
     this.subLongName = subLongName;  
    }  
    public String getSubName(){  
        return subName;  
    }
      
   public void setSubName(String subName){  
     this.subName = subName;  
    }  
    public String getItemId(){  
        return itemId;  
    }
      
   public void setItemId(String itemId){  
     this.itemId = itemId;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public Long getItemOrder(){  
        return itemOrder;  
    }
      
   public void setItemOrder(Long itemOrder){  
     this.itemOrder = itemOrder;  
    }  
    public String getItemName(){  
        return itemName;  
    }
      
   public void setItemName(String itemName){  
     this.itemName = itemName;  
    }  
    public String getSubCode(){  
        return subCode;  
    }
      
   public void setSubCode(String subCode){  
     this.subCode = subCode;  
    }  
    public String getSubId(){  
        return subId;  
    }
      
   public void setSubId(String subId){  
     this.subId = subId;  
    }  
    public String getTemplateId(){  
        return templateId;  
    }
      
   public void setTemplateId(String templateId){  
     this.templateId = templateId;  
    }  
}