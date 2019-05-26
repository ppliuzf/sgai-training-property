/**
 * Project Name:smart-framework
 * File Name:MdmSpaceInfoDao.java
 * Package Name:com.sgai.modules.mdm.dao
 * Date:2017年11月27日上午9:07:26
 * Copyright (c) 2017, 首自信--智慧城市创新中心.
 *
*/
/**    
    * @Title: MdmSpaceInfoDao.java  
    * @Package com.sgai.modules.mdm.dao  
    * @com.sgai.property.commonService.vo;(用一句话描述该文件做什么)
    * @author admin  
    * @date 2017年11月27日  
    * Company 首自信--智慧城市创新中心
    * version V1.0    
    */  

package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmRoomInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;

/**
 * ClassName:MdmSpaceInfoDao <br/>
 *  ADD FUNCTION. <br/>
 * Reason:	  REASON. <br/>
 * Date:     2017年11月27日 上午9:07:26 <br/>
 * @author   admin
 * @version  
 * @since    JDK 1.8	 
 */
@Mapper
public interface MdmSpaceInfoDao  extends CrudDao<MdmSpaceInfo> {

	MdmSpaceInfo getAreaInfo(String arg0, String arg1);
	MdmSpaceInfo getParkInfo(String arg0, String arg1);
	MdmSpaceInfo getBuildInfo(String arg0, String arg1);
	MdmSpaceInfo getFloorInfo(String arg0, String arg1);
	MdmSpaceInfo getRoomInfo(String arg0, String arg1);
	
}

