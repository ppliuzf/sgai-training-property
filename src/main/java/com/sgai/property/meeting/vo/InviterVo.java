package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class InviterVo implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1818375216962275857L;
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会人ID")
    private String inviterEiId; //参会人ID
    @ApiModelProperty(value = "参会人名称")
    private String inviterEiName; //参会人名称
    @ApiModelProperty(value = "是否主持人1 是 0 否")
    private Integer isCompere; //是否主持人1 是 0 否
    @ApiModelProperty(value = "参会人节点")
    private String nodeTree; //参会人节点
    
    public String getNodeTree() {
		return nodeTree;
	}

	public void setNodeTree(String nodeTree) {
		this.nodeTree = nodeTree;
	}

	public String getMiId(){  
        return miId;  
    }
      
   public void setMiId(String miId){  
     this.miId = miId;  
    }  
    public String getInviterEiId(){  
        return inviterEiId;  
    }
      
   public void setInviterEiId(String inviterEiId){  
     this.inviterEiId = inviterEiId;  
    }  
    public String getInviterEiName(){  
        return inviterEiName;  
    }
      
   public void setInviterEiName(String inviterEiName){  
     this.inviterEiName = inviterEiName;  
    }  
    public Integer getIsCompere(){  
        return isCompere;  
    }
      
   public void setIsCompere(Integer isCompere){  
     this.isCompere = isCompere;  
    }  
    
}