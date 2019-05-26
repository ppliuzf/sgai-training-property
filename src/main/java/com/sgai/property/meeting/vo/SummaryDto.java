package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SummaryDto implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 2422001269235142312L;
	@ApiModelProperty(value = "主键")
    private String id; //主键
	@ApiModelProperty(value = "主键")
    private Long msId; //主键
	@ApiModelProperty(value = "参会人ID")
    private Long inviterEiId; //参会人ID
    @ApiModelProperty(value = "参会人名称")
    private String inviterEiName; //参会人名称
    @ApiModelProperty(value = "参会人公司/职位")
    private String compereComDept; //参会人公司/职位
    @ApiModelProperty(value = "会议纪要内容")
    private String mtContent; //会议纪要内容
    @ApiModelProperty(value = "参会人头像")
    private String inviterUrl; //参会人头像
    @ApiModelProperty(value = "可见范围0 自己可见 1 全部可见 ")
    private Integer msShow; //可见范围0 自己可见 1 全部可见 
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "会议纪要图片")
    private List<SummaryPicDto> summaryPicDtos; //会议纪要图片
	
    
    
    /**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.8
	 */
	public String getId() {
		return id;
	}

	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Long getInviterEiId() {
		return inviterEiId;
	}

	public void setInviterEiId(Long inviterEiId) {
		this.inviterEiId = inviterEiId;
	}

	public String getInviterUrl() {
		return inviterUrl;
	}

	public void setInviterUrl(String inviterUrl) {
		this.inviterUrl = inviterUrl;
	}

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getInviterEiName(){  
        return inviterEiName;  
    }
      
   public void setInviterEiName(String inviterEiName){  
     this.inviterEiName = inviterEiName;  
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
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }

	public String getCompereComDept() {
		return compereComDept;
	}
	
	public void setCompereComDept(String compereComDept) {
		this.compereComDept = compereComDept;
	}
	
	public List<SummaryPicDto> getSummaryPicDtos() {
		return summaryPicDtos;
	}
	
	public void setSummaryPicDtos(List<SummaryPicDto> summaryPicDtos) {
		this.summaryPicDtos = summaryPicDtos;
	}

	  
    
}