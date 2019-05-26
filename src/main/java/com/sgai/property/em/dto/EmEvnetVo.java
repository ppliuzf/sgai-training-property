package com.sgai.property.em.dto;

import com.sgai.common.persistence.BoEntity;

/**
 * 
    * ClassName: EmEvnetVo  
    * com.sgai.property.commonService.vo;(事件临时实体类)
    * @author yangyz  
    * Date 2018年1月5日  
    * Company 首自信--智慧城市创新中心
 */
public class EmEvnetVo extends BoEntity<EmEvnetVo>{

	private static final long serialVersionUID = 1L;
	private String emId;    //事件主键
	private String emCode;  //事件编号
	private String emType;  //事件类型
	private String emTime;  //时间
	private String emPerson;  //提报人
	private String emAddress; //地址
	private String emContent; //内容
	private String emState;   //状态
	private String comCode;   //机构代码
	/**
	 * emId.
	 *
	 * @return  the emId
	 * @since   JDK 1.8
	 */
	public String getEmId() {
		return emId;
	}
	/**
	 * emId.
	 *
	 * @param   emId    the emId to set
	 * @since   JDK 1.8
	 */
	public void setEmId(String emId) {
		this.emId = emId;
	}
	/**
	 * emCode.
	 *
	 * @return  the emCode
	 * @since   JDK 1.8
	 */
	public String getEmCode() {
		return emCode;
	}
	/**
	 * emCode.
	 *
	 * @param   emCode    the emCode to set
	 * @since   JDK 1.8
	 */
	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	/**
	 * emType.
	 *
	 * @return  the emType
	 * @since   JDK 1.8
	 */
	public String getEmType() {
		return emType;
	}
	/**
	 * emType.
	 *
	 * @param   emType    the emType to set
	 * @since   JDK 1.8
	 */
	public void setEmType(String emType) {
		this.emType = emType;
	}
	/**
	 * emTime.
	 *
	 * @return  the emTime
	 * @since   JDK 1.8
	 */
	public String getEmTime() {
		return emTime;
	}
	/**
	 * emTime.
	 *
	 * @param   emTime    the emTime to set
	 * @since   JDK 1.8
	 */
	public void setEmTime(String emTime) {
		this.emTime = emTime;
	}
	/**
	 * emPerson.
	 *
	 * @return  the emPerson
	 * @since   JDK 1.8
	 */
	public String getEmPerson() {
		return emPerson;
	}
	/**
	 * emPerson.
	 *
	 * @param   emPerson    the emPerson to set
	 * @since   JDK 1.8
	 */
	public void setEmPerson(String emPerson) {
		this.emPerson = emPerson;
	}
	/**
	 * emAddress.
	 *
	 * @return  the emAddress
	 * @since   JDK 1.8
	 */
	public String getEmAddress() {
		return emAddress;
	}
	/**
	 * emAddress.
	 *
	 * @param   emAddress    the emAddress to set
	 * @since   JDK 1.8
	 */
	public void setEmAddress(String emAddress) {
		this.emAddress = emAddress;
	}
	/**
	 * emContent.
	 *
	 * @return  the emContent
	 * @since   JDK 1.8
	 */
	public String getEmContent() {
		return emContent;
	}
	/**
	 * emContent.
	 *
	 * @param   emContent    the emContent to set
	 * @since   JDK 1.8
	 */
	public void setEmContent(String emContent) {
		this.emContent = emContent;
	}
	/**
	 * emState.
	 *
	 * @return  the emState
	 * @since   JDK 1.8
	 */
	public String getEmState() {
		return emState;
	}
	/**
	 * emState.
	 *
	 * @param   emState    the emState to set
	 * @since   JDK 1.8
	 */
	public void setEmState(String emState) {
		this.emState = emState;
	}
	/**
	 * comCode.
	 *
	 * @return  the comCode
	 * @since   JDK 1.8
	 */
	public String getComCode() {
		return comCode;
	}
	/**
	 * comCode.
	 *
	 * @param   comCode    the comCode to set
	 * @since   JDK 1.8
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
}

