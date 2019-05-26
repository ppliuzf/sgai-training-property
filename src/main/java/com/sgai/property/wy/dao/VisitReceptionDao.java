package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.VisitReception;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**  
* @ClassName: VisitReceptionDao  
* (数据库SQL映射)
* @author LiuXiaobing  
* @date 2018年2月1日  
* @Company 首自信--智慧城市创新中心  
*/
@Mapper
public interface VisitReceptionDao extends CrudDao<VisitReception> {
	public void batchDelete(List<String> idList);
}
