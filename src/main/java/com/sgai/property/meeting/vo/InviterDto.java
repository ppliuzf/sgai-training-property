package com.sgai.property.meeting.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class InviterDto implements Serializable{
     
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
    @ApiModelProperty(value = "是否参加1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加")
    private Integer isInvite; //是否参加1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加
    @ApiModelProperty(value = "参会人头像")
    private String inviterUrl; //参会人头像
    @ApiModelProperty(value = "参会人节点")
    private String nodeTree; //参会人节点
    
    @ApiModelProperty(value = "是否是部门 1 是 0 否")
    private Integer isDept; //是否是部门
    
    
    
    /**
	 * isDept.
	 *
	 * @return  the isDept
	 * @since   JDK 1.8
	 */
	public Integer getIsDept() {
		return isDept;
	}

	/**
	 * isDept.
	 *
	 * @param   isDept    the isDept to set
	 * @since   JDK 1.8
	 */
	public void setIsDept(Integer isDept) {
		this.isDept = isDept;
	}

	public String getNodeTree() {
		return nodeTree;
	}

	public void setNodeTree(String nodeTree) {
		this.nodeTree = nodeTree;
	}

	public String getInviterUrl() {
		return inviterUrl;
	}

	public void setInviterUrl(String inviterUrl) {
		this.inviterUrl = inviterUrl;
	}

	public Integer getIsInvite() {
		return isInvite;
	}

	public void setIsInvite(Integer isInvite) {
		this.isInvite = isInvite;
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

	/**
	 *  简单描述该方法的实现功能（可选）.
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inviterEiId == null) ? 0 : inviterEiId.hashCode());
		result = prime * result + ((miId == null) ? 0 : miId.hashCode());
		return result;
	}
	
	/**
	 *  简单描述该方法的实现功能（可选）.
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InviterDto other = (InviterDto) obj;
		if (inviterEiId == null) {
			if (other.inviterEiId != null)
				return false;
		} else if (!inviterEiId.equals(other.inviterEiId))
			return false;
		if (miId == null) {
            return other.miId == null;
		} else return miId.equals(other.miId);
    }
      
   	
   
}