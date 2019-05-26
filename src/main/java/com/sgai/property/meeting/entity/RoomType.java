package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class RoomType extends BoEntity<RoomType> {
    @ApiModelProperty(value = "名称")
    private String rtName; //名称
    @ApiModelProperty(value = "类型名称")
    private String rtTypeName; //类型名称
    @ApiModelProperty(value = "描述")
    private String rtTypeDesc; //描述
    @ApiModelProperty(value = "类型：1礼堂2办公")
    private Integer rtType; //类型：1礼堂2办公
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0否")
    private Integer isDelete; //是否删除1 是 0否
    @ApiModelProperty(value = "是否关联删除 1是 0否")
    private Integer mayDelete; //是否关联删除1 是 0否

    
	public Integer getMayDelete() {
		return mayDelete;
	}

	public void setMayDelete(Integer mayDelete) {
		this.mayDelete = mayDelete;
	}

	public String getRtName(){  
        return rtName;  
    }
      
   public void setRtName(String rtName){  
     this.rtName = rtName;  
    }  
    public String getRtTypeName(){  
        return rtTypeName;  
    }
      
   public void setRtTypeName(String rtTypeName){  
     this.rtTypeName = rtTypeName;  
    }  
    public String getRtTypeDesc(){  
        return rtTypeDesc;  
    }
      
   public void setRtTypeDesc(String rtTypeDesc){  
     this.rtTypeDesc = rtTypeDesc;  
    }  
    public Integer getRtType(){  
        return rtType;  
    }
      
   public void setRtType(Integer rtType){  
     this.rtType = rtType;  
    }  
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
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