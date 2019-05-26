/**
 * Project Name:smart-framework
 * File Name:CtlCompVo.java
 * Package Name:com.sgai.modules.ctl.dto
 * Date:2017年11月22日下午1:13:25
 * Copyright (c) 2017, 首自信--智慧城市创新中心.
 *
*/
/**    
    * @Title: CtlCompVo.java  
    * @Package com.sgai.modules.ctl.dto  
    * @Description: (用一句话描述该文件做什么)
    * @author lenovo  
    * @date 2017年11月22日  
    * Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  

package com.sgai.property.ctl.dto;


/** 
 * ClassName: CtlCompVo  
 * Description: (机构临时实体类)
 * @author yangyz  
 * Date 2017年11月22日  
 * Company 首自信--智慧城市创新中心
 */
public class CtlCompVo {

	private String comCode;		// com_code 机构代码
	private String comName;		// com_name 机构名称
	private String comNameAbbr;		// com_name_abbr 机构简称
	private String dummyFlag;		// 1选项为:'Y':是'N':否默认为'N'2.表示该结点企业是否是真实的企业
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'2.备用,先不做处理
	private String comAddr;		// com_addr 机构地址
	private String id;          //ID
	private String busiCode;  //子系统编码
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
	/**
	 * comName.
	 *
	 * @return  the comName
	 * @since   JDK 1.8
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * comName.
	 *
	 * @param   comName    the comName to set
	 * @since   JDK 1.8
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}
	/**
	 * comNameAbbr.
	 *
	 * @return  the comNameAbbr
	 * @since   JDK 1.8
	 */
	public String getComNameAbbr() {
		return comNameAbbr;
	}
	/**
	 * comNameAbbr.
	 *
	 * @param   comNameAbbr    the comNameAbbr to set
	 * @since   JDK 1.8
	 */
	public void setComNameAbbr(String comNameAbbr) {
		this.comNameAbbr = comNameAbbr;
	}
	/**
	 * dummyFlag.
	 *
	 * @return  the dummyFlag
	 * @since   JDK 1.8
	 */
	public String getDummyFlag() {
		return dummyFlag;
	}
	/**
	 * dummyFlag.
	 *
	 * @param   dummyFlag    the dummyFlag to set
	 * @since   JDK 1.8
	 */
	public void setDummyFlag(String dummyFlag) {
		this.dummyFlag = dummyFlag;
	}
	/**
	 * enabledFlag.
	 *
	 * @return  the enabledFlag
	 * @since   JDK 1.8
	 */
	public String getEnabledFlag() {
		return enabledFlag;
	}
	/**
	 * enabledFlag.
	 *
	 * @param   enabledFlag    the enabledFlag to set
	 * @since   JDK 1.8
	 */
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * comAddr.
	 *
	 * @return  the comAddr
	 * @since   JDK 1.8
	 */
	public String getComAddr() {
		return comAddr;
	}
	/**
	 * comAddr.
	 *
	 * @param   comAddr    the comAddr to set
	 * @since   JDK 1.8
	 */
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
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
	
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	
}

