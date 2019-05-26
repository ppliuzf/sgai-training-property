package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PicDto implements Serializable{
     
    /**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 6070774631986172479L;
	@ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "会议文件url")
    private String picUrl; //会议文件url
    @ApiModelProperty(value = "会议文件名称")
    private String picName; //会议文件名称
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
    
}