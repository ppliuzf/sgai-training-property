package com.sgai.property.ctl.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlComBusiRelation;

/**
 * 
    * ClassName: CtlComBusiRelationDao  
    * Description: (机构分配Dao)
    * @author ASUS  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlComBusiRelationDao extends CrudDao<CtlComBusiRelation> {
	/**
	 * 
	 * getBusiListByCom:(根据机构代码获取子系统).
	 * @param param 所需要的参数
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    List<CtlBusinessDefine> getBusiListByCom(Map<String, String> param);
	/**
	 * 
	 * getBusiListLack:(获取该机构未拥有的子系统).
	 * @param param
	 * @return :List<CtlBusinessDefine> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	List<CtlBusinessDefine> getBusiListLack(Map<String, String> param);
	/**
	 * 
	 * getAllBusiList:(获取所有的子系统).
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    List<CtlBusinessDefine> getAllBusiList();
	/**
	 * 
	 * deleteBusiTree:(移除该机构的子系统).
	 * @param param :void 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    void deleteBusiTree(Map<String, String> param);
	/**
	 * 
	 * getMenuByBusi:(根据子系统代码获取菜单).
	 * @param param value为子系统代码的集合
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    List<Map<String,String>>getMenuByBusi(Map<String, String> param);
}