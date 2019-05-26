package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MaininfoVo implements Serializable {


    private static final long serialVersionUID = -2254022466382495635L;
    @ApiModelProperty(value = "主键")
    private String id; //主键
    @ApiModelProperty(value = "主键")
    private String miId; //主键
    @ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题
    @ApiModelProperty(value = "会议图片")
    private List<PicVo> picVos; //会议图片
    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期
    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段
    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要
    @ApiModelProperty(value = "是否周例会1 是 0否")
    private Integer miIsWeekMeeting; //是否周例会1 是 0否
    @ApiModelProperty(value = "重复次数")
    private Integer miRepeatNum; //重复次数
    @ApiModelProperty(value = "主持人ID")
    private Long compereEiId; //主持人ID
    @ApiModelProperty(value = "主持人")
    private String compereEiName; //主持人
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
	 * miId.
	 *
	 * @return  the miId
	 * @since   JDK 1.8
	 */
	public String getMiId() {
		return miId;
	}

	/**
	 * miId.
	 *
	 * @param   miId    the miId to set
	 * @since   JDK 1.8
	 */
	public void setMiId(String miId) {
		this.miId = miId;
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

	public List<MaterielVo> getMaterielVos() {
        return materielVos;
    }

    public MaininfoVo setMaterielVos(List<MaterielVo> materielVos) {
        this.materielVos = materielVos;
        return this;
    }

    public String getRrId() {
        return rrId;
    }

    public MaininfoVo setRrId(String rrId) {
        this.rrId = rrId;
        return this;
    }

    public Long getMiMtDate() {
        return miMtDate;
    }

    public List<PicVo> getPicVos() {
        return picVos;
    }

    public void setPicVos(List<PicVo> picVos) {
        this.picVos = picVos;
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

    public String getMiMtSubject() {
        return miMtSubject;
    }

    public void setMiMtSubject(String miMtSubject) {
        this.miMtSubject = miMtSubject;
    }


    public void setMiMtDate(Long miMtDate) {
        this.miMtDate = miMtDate;
    }

    public String getMiMtTimeSeg() {
        return miMtTimeSeg;
    }

    public void setMiMtTimeSeg(String miMtTimeSeg) {
        this.miMtTimeSeg = miMtTimeSeg;
    }

    public String getMiMtContent() {
        return miMtContent;
    }

    public void setMiMtContent(String miMtContent) {
        this.miMtContent = miMtContent;
    }

    public Integer getMiIsWeekMeeting() {
        return miIsWeekMeeting;
    }

    public void setMiIsWeekMeeting(Integer miIsWeekMeeting) {
        this.miIsWeekMeeting = miIsWeekMeeting;
    }

    public Integer getMiRepeatNum() {
        return miRepeatNum;
    }

    public void setMiRepeatNum(Integer miRepeatNum) {
        this.miRepeatNum = miRepeatNum;
    }

    public Long getCompereEiId() {
        return compereEiId;
    }

    public void setCompereEiId(Long compereEiId) {
        this.compereEiId = compereEiId;
    }

    public String getCompereEiName() {
        return compereEiName;
    }

    public void setCompereEiName(String compereEiName) {
        this.compereEiName = compereEiName;
    }

}