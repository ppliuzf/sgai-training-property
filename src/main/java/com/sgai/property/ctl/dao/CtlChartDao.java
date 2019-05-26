package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlChart;

/**
 * 图表DAO接口
 * @author admin
 * @version 2018-01-04
 */
@Mapper
public interface CtlChartDao extends CrudDao<CtlChart> {
	
	
	List<CtlChart>  findChartByParentCode(String arg0);
	
	  
    /**  
    * @Title: findListByUserCode  
    * @Description: 通过userCode获得图表
    * @param @param arg0
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart> findListByUserCode(String arg0, String arg1, String arg2, String arg3);
	List<CtlChart> findTabAndCharttByUserCode(String arg0, String arg1, String arg2, String arg3);
    /**  
    * @Title: findListByRoleCode  
    * @Description: 通过roleCode获得已经分配的图表
    * @param @param arg0
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart>  findListByRoleCode(String arg0, String arg1, String arg2);
	
	
    /**  
    * @Title: findListByRoleCodeList  
    * @Description: (这里用一句话描述这个方法的作用)
    * @param @param roleList
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart> findListByRoleCodeList(Map<String, Object> params);
		
	
    /**  
    * @Title: findAllChartWithoutSelected  
    * @Description: 分页
    * @param @param param   roleCode：角色    startNum：开始条数  endNum：结束条数
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart>  findAllChartWithoutSelected(Map<String, String> param);
	List<CtlChart>  findWithoutSelectedByUser(Map<String, String> param);
	/**  
    * @Title: findAllChartWithoutSelected  
    * @Description:  统计总数
    * @param @param param   
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    String getAllChartWithoutSelectedTotal(Map<String, String> param);
	String getTotalunUser(Map<String, String> param);
	  
    /**  
    * @Title: deleteById  
    * @Description: 按照id删除
    * @param @param id    参数  
    * @return void    返回类型  
    * @author admin
    * @throws  
    */
    void deleteById(String id);
	
	
	  
    /**  
    * @Title: findListSpecial  
    * @Description: chart_type =2的分页用到
    * @param @param ctlChart
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart>  findListSpecial(CtlChart ctlChart);
	
	  
    /**  
    * @Title: findById  
    * @Description: 修改页面使用 
    * @param @param id
    * @param @return    参数  
    * @return CtlChart    返回类型  
    * @author admin
    * @throws  
    */
    CtlChart findById(String id);
	
	
	  
    /**  
    * @Title: getDataForSelect  
    * @Description: 下拉框数据来源
    * @param @return    参数  
    * @return List<CtlChart>    返回类型  
    * @author admin
    * @throws  
    */
    List<CtlChart>  getDataForSelect(CtlChart ctlChart);
	
	
	/**
	 * findAlmByTime:区域报警事件数量   ---堆叠柱状图  按照时间段和报警类型名称查询
	 * @param param
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<Map<String,String>>  findAlmByTime(Map<String, String> param);
	
	/**
	 * findAllAlmType:所有的报警类型
	 * @return :List<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<String> findAllAlmType();
	
	/**
	 * findAllAlmSpace:所有的报警空间 building
	 * @return :List<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<String> findAllAlmSpace();
	
}