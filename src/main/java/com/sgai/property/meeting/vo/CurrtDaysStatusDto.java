package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 当前周状态信息
 *
 * @author 146584
 * @create 2017-11-02 15:43
 */
public class CurrtDaysStatusDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 388555360764119366L;
	 
    @ApiModelProperty(value = "日期")
    private String currtDate; //日期
    @ApiModelProperty(value = "是否有会议1 有 0 无")
    private String status; //是否有会议
     
	
	/**
	 * currtDate.
	 *
	 * @return  the currtDate
	 * @since   JDK 1.8
	 */
	public String getCurrtDate() {
		return currtDate;
	}
	/**
	 * currtDate.
	 *
	 * @param   currtDate    the currtDate to set
	 * @since   JDK 1.8
	 */
	public void setCurrtDate(String currtDate) {
		this.currtDate = currtDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
