package com.sgai.property.ctl.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
	
/**
 * 
    * ClassName: CtlBusinessDefineDao  
    * Description: (定义子系统的Dao)
    * @author 王天尧  
    * Date 2017年11月21日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlBusinessDefineDao extends CrudDao<CtlBusinessDefine> {
	/**
	 * 
	 * findByParam:(通过某一字段查询子系统).
	 * @param map  封装查询条件的集合
	 * @return :CtlBusinessDefine 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	 CtlBusinessDefine findByParam(Map<String, Object> map);
	 
	 /**
	  * 机构指定子系统时 选择没有被指定过的子系统
	 * findBusiList:(这里用一句话描述这个方法的作用).
	 * @param ctlBusinessDefine
	 * @return :List<CtlBusinessDefine> 
	 * @since JDK 1.8
	 * @author admin
	 */
	List<CtlBusinessDefine> findBusiList(CtlBusinessDefine ctlBusinessDefine);
}