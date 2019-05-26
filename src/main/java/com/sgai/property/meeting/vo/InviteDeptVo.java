package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class InviteDeptVo implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 2385800849511134900L;
    @ApiModelProperty(value = "会议ID")
    private String miId; //会议ID
    @ApiModelProperty(value = "参会部门ID")
    private String inviterDeptId; //参会部门ID
    @ApiModelProperty(value = "参会部门名称")
    private String inviterDeptName; //参会部门名称
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
    
}