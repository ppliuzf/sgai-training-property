package com.sgai.property.budget.vo;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
public class SubjectVo {
    
	private String id;
    @ApiModelProperty(value = "科目描述")
    private String description; //科目描述
    @ApiModelProperty(value = "科目长名称")
    private String longName; //科目长名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "父科目id")
    private String parentId; //父科目id
    @ApiModelProperty(value = "科目短名称")
    private String shortName; //科目短名称
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "科目级别(1:一级科目;2:二级科目;3:三级科目)")
    private Long levels; //科目级别(1:一级科目;2:二级科目;3:三级科目)
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "科目编号")
    private String codeNumber; //科目编号
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
    @ApiModelProperty(value = "一级科目名称")
    private String levelOneName;
    @ApiModelProperty(value = "二级科目名称")
    private String levelTwoName;
    
    private List<SubjectVo> childList;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getLongName(){  
        return longName;  
    }
      
   public void setLongName(String longName){  
     this.longName = longName;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getParentId(){  
        return parentId;  
    }
      
   public void setParentId(String parentId){  
     this.parentId = parentId;  
    }  
    public String getShortName(){  
        return shortName;  
    }
      
   public void setShortName(String shortName){  
     this.shortName = shortName;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getLevels(){  
        return levels;  
    }
      
   public void setLevels(Long levels){  
     this.levels = levels;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getCodeNumber(){  
        return codeNumber;  
    }
      
   public void setCodeNumber(String codeNumber){  
     this.codeNumber = codeNumber;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }

	public List<SubjectVo> getChildList() {
		return childList;
	}
	
	public void setChildList(List<SubjectVo> childList) {
		this.childList = childList;
	}

	public String getLevelOneName() {
		return levelOneName;
	}

	public void setLevelOneName(String levelOneName) {
		this.levelOneName = levelOneName;
	}

	public String getLevelTwoName() {
		return levelTwoName;
	}

	public void setLevelTwoName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}  
	
   
}