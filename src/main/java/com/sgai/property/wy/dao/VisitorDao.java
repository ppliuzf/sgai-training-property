package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**  
* @ClassName: VisitorDao  
* (数据库SQL映射)
* @author LiuXiaobing  
* @date 2018年1月18日  
* @Company 首自信--智慧城市创新中心  
*/
@Mapper
public interface VisitorDao extends CrudDao<Visitor> {
	// 批量删除
	public void batchDelete(List<String> idList);
}
