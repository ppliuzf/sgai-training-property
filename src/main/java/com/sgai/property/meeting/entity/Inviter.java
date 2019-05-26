package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class Inviter extends BoEntity<Inviter>{
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会人ID")
    private String inviterEiId; //参会人ID
    @ApiModelProperty(value = "参会人名称")
    private String inviterEiName; //参会人名称
    @ApiModelProperty(value = "参会人节点")
    private String nodeTree; //参会人节点
    @ApiModelProperty(value = "是否主持人1 是 0 否")
    private Integer isCompere; //是否主持人1 是 0 否
    @ApiModelProperty(value = "签到时间")
    private Long signTime; //签到时间
    @ApiModelProperty(value = "是否参加1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加")
    private Integer isInvite; //是否参加1 参加未请假 0 未参加未请假 2请假未参加 3请假且参加
    @ApiModelProperty(value = "请假理由")
    private String leaveReason; //请假理由
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0  否")
    private Integer isDelete; //是否删除1 是 0  否
	
    
    
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
    public Long getSignTime(){  
        return signTime;  
    }
      
   public void setSignTime(Long signTime){  
     this.signTime = signTime;  
    }  
    public Integer getIsInvite(){  
        return isInvite;  
    }
      
   public void setIsInvite(Integer isInvite){  
     this.isInvite = isInvite;  
    }  
    public String getLeaveReason(){  
        return leaveReason;  
    }
      
   public void setLeaveReason(String leaveReason){  
     this.leaveReason = leaveReason;  
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inviterEiId == null) ? 0 : inviterEiId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inviter other = (Inviter) obj;
		if (inviterEiId == null) {
            return other.inviterEiId == null;
		} else return inviterEiId.equals(other.inviterEiId);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inviter [miId=" + miId + ", inviterEiId=" + inviterEiId + ", inviterEiName=" + inviterEiName
				+ ", nodeTree=" + nodeTree + ", isCompere=" + isCompere + ", signTime=" + signTime + ", isInvite="
				+ isInvite + ", leaveReason=" + leaveReason + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", isDelete=" + isDelete + "]";
	}  
   
   
}