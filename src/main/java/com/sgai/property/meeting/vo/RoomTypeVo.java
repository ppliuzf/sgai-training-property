package com.sgai.property.meeting.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class RoomTypeVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5635584697413568829L;
	@ApiModelProperty(value = "主键ID,更新传值")
    private String id;
	@ApiModelProperty(value = "名称")
    private String rtName; //名称
    @ApiModelProperty(value = "类型名称")
    private String rtTypeName; //类型名称
    @ApiModelProperty(value = "描述")
    private String rtTypeDesc; //描述
    @ApiModelProperty(value = "类型：1礼堂2办公")
    private Integer rtType; //类型：1礼堂2办公
    @ApiModelProperty(value = "是否关联删除  0:可删除 1:不可删除",required = false)
    private Integer mayDelete; //是否关联删除1 是 0否
    
    public Integer getMayDelete() {
		return mayDelete;
	}

	public void setMayDelete(Integer mayDelete) {
		this.mayDelete = mayDelete;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
}