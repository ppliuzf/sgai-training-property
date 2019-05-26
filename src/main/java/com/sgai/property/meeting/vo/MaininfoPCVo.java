package com.sgai.property.meeting.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class MaininfoPCVo implements Serializable{
     
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2254022466382495635L;
	@ApiModelProperty(value = "主键")
    private String id; //主键
	@ApiModelProperty(value = "主键")
    private String miId; //主键
	@ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题
    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期
    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段
    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要
    @ApiModelProperty(value = "参与人")
    private List<InviterVo> inviters; //参与人
    @ApiModelProperty(value = "参会部门")
    private List<InviteDeptVo> inviteDeptVos; //参会部门
    @ApiModelProperty(value = "所需物料")
    private List<MaterielVo> materielVos; //所需物料
    @ApiModelProperty(value = "是否开启会议提醒")
    private Integer miIsRemind; //是否开启会议提醒
    @ApiModelProperty(value = "提前分钟数")
    private Integer miRemindMin;
    
   

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

	/**
	 * materielVos.
	 *
	 * @return  the materielVos
	 * @since   JDK 1.8
	 */
	public List<MaterielVo> getMaterielVos() {
		return materielVos;
	}

	/**
	 * materielVos.
	 *
	 * @param   materielVos    the materielVos to set
	 * @since   JDK 1.8
	 */
	public void setMaterielVos(List<MaterielVo> materielVos) {
		this.materielVos = materielVos;
	}

	/**
	 * miIsRemind.
	 *
	 * @return  the miIsRemind
	 * @since   JDK 1.8
	 */
	public Integer getMiIsRemind() {
		return miIsRemind;
	}

	/**
	 * miIsRemind.
	 *
	 * @param   miIsRemind    the miIsRemind to set
	 * @since   JDK 1.8
	 */
	public void setMiIsRemind(Integer miIsRemind) {
		this.miIsRemind = miIsRemind;
	}

	/**
	 * miRemindMin.
	 *
	 * @return  the miRemindMin
	 * @since   JDK 1.8
	 */
	public Integer getMiRemindMin() {
		return miRemindMin;
	}

	/**
	 * miRemindMin.
	 *
	 * @param   miRemindMin    the miRemindMin to set
	 * @since   JDK 1.8
	 */
	public void setMiRemindMin(Integer miRemindMin) {
		this.miRemindMin = miRemindMin;
	}

	public String getMiId() {
		return miId;
	}

	public void setMiId(String miId) {
		this.miId = miId;
	}

	public List<InviterVo> getInviters() {
		return inviters;
	}

	public void setInviters(List<InviterVo> inviters) {
		this.inviters = inviters;
	}

	public List<InviteDeptVo> getInviteDeptVos() {
		return inviteDeptVos;
	}

	public void setInviteDeptVos(List<InviteDeptVo> inviteDeptVos) {
		this.inviteDeptVos = inviteDeptVos;
	}

	public String getMiMtSubject(){  
        return miMtSubject;  
    }
      
   public void setMiMtSubject(String miMtSubject){  
     this.miMtSubject = miMtSubject;  
    }  
    public String getRrId(){  
        return rrId;  
    }
      
   public void setRrId(String rrId){  
     this.rrId = rrId;  
    }  
    public Long getMiMtDate(){  
        return miMtDate;  
    }
      
   public void setMiMtDate(Long miMtDate){  
     this.miMtDate = miMtDate;  
    }  
    public String getMiMtTimeSeg(){  
        return miMtTimeSeg;  
    }
      
   public void setMiMtTimeSeg(String miMtTimeSeg){  
     this.miMtTimeSeg = miMtTimeSeg;  
    }  
    public String getMiMtContent(){  
        return miMtContent;  
    }
      
   public void setMiMtContent(String miMtContent){  
     this.miMtContent = miMtContent;  
    }  
    
}