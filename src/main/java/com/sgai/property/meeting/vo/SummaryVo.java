package com.sgai.property.meeting.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SummaryVo implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 2422001269235142312L;
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "会议纪要内容")
    private String mtContent; //会议纪要内容
    @ApiModelProperty(value = "可见范围0 自己可见 1 全部可见 ")
    private Integer msShow; //可见范围0 自己可见 1 全部可见 
    @ApiModelProperty(value = "会议纪要图片")
    private List<SummaryPicVo> summaryPicUrls; //会议纪要图片
	
    public String getMiId(){  
        return miId;  
    }
      
   public void setMiId(String miId){  
     this.miId = miId;  
    }  
      
    public String getMtContent(){  
        return mtContent;  
    }
      
   public void setMtContent(String mtContent){  
     this.mtContent = mtContent;  
    }  
    public Integer getMsShow(){  
        return msShow;  
    }
      
   public void setMsShow(Integer msShow){  
     this.msShow = msShow;  
    }

	public List<SummaryPicVo> getSummaryPicUrls() {
		return summaryPicUrls;
	}
	
	public void setSummaryPicUrls(List<SummaryPicVo> summaryPicUrls) {
		this.summaryPicUrls = summaryPicUrls;
	}  
	
	 
    
}