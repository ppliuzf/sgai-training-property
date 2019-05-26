package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class Pic extends BoEntity<Pic>{
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "会议文件url")
    private String picUrl; //会议文件url
    @ApiModelProperty(value = "会议文件名称")
    private String picName; //会议文件名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0 否")
    private Integer isDelete; //是否删除1 是 0 否
    @ApiModelProperty(value = "会议图片原始高度")
    private Long high; //会议图片原始高度
    @ApiModelProperty(value = "会议图片原始宽度")
    private Long width; //会议图片原始宽度
    
    
    public Long getHigh() {
		return high;
	}

	public void setHigh(Long high) {
		this.high = high;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}
    public String getMiId(){
        return miId;  
    }
      
   public void setMiId(String miId){
     this.miId = miId;  
    }  
    public String getPicUrl(){  
        return picUrl;  
    }
      
   public void setPicUrl(String picUrl){  
     this.picUrl = picUrl;  
    }  
    public String getPicName(){  
        return picName;  
    }
      
   public void setPicName(String picName){  
     this.picName = picName;  
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