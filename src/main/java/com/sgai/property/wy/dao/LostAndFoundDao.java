package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.LostAndFound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**  
	* @ClassName: LostAndFound  
	* (数据库SQL映射)
	* @author LiuXiaobing  
	* @date 2018年1月28日  
	* @Company 首自信--智慧城市创新中心  
*/
@Mapper
public interface LostAndFoundDao extends CrudDao<LostAndFound> {
	public void batchDelete(List<String> idList);
}
