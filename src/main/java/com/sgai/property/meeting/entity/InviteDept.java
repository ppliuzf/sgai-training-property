package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class InviteDept extends BoEntity<InviteDept> {
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会部门ID")
    private String inviterDeptId; //参会部门ID
    @ApiModelProperty(value = "参会部门名称")
    private String inviterDeptName; //参会部门名称
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除1 是 0  否")
    private Integer isDelete; //是否删除1 是 0  否
    @ApiModelProperty(value = "参会部门节点")
    private String nodeTree; //参会部门节点
    
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
    public String getInviterDeptId(){  
        return inviterDeptId;  
    }
      
   public void setInviterDeptId(String inviterDeptId){  
     this.inviterDeptId = inviterDeptId;  
    }  
    public String getInviterDeptName(){  
        return inviterDeptName;  
    }
      
   public void setInviterDeptName(String inviterDeptName){  
     this.inviterDeptName = inviterDeptName;  
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