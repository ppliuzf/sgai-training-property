package com.sgai.property.em.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.dto.EmEvnetVo;
import com.sgai.property.em.entity.EmComplaints;

/**
 * 投诉事件维护DAO接口
 * @author guanze
 * @version 2017-12-05
 */
@Mapper
public interface EmComplaintsDao extends CrudDao<EmComplaints> {
	EmComplaints findNextCodeEmComplaints();
	
	EmComplaintsVo getEmComplaints(EmComplaintsVo emComplaintsVo);
	
	EmComplaints getEmComplaint(EmComplaints emComplaints);
	
	List<EmComplaints> findComplaintsList(EmComplaints emComplaints);
	
	/**
	 * 
	 * findSkanList:(查询已完成和已终止事件).
	 * @param emComplaints
	 * @return :List<EmComplaints> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<EmComplaints> findSkanList(EmComplaints emComplaints);
	
	/**
	 * 
	 * findAppList:(查询事件列表).
	 * @param emEvnetVo
	 * @return :List<EmEvnetVo> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<EmEvnetVo> findAppList(EmEvnetVo emEvnetVo);
	
	
	  
    /**  
    * @Title: findUnhandledEvent  
    * @Description:  获取当前用户的权限下未处理的事件 home页面  homeMsg页面
    * @param @param emEvnetVo
    * @param @return    参数  
    * @return List<EmEvnetVo>    返回类型  
    * @throws  
    */
    List<EmEvnetVo> findUnhandledEvent(EmEvnetVo emEvnetVo);
	

}