package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.MagazineInfo;
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
public interface MagazineInfoDao extends CrudDao<MagazineInfo> {
	public void batchDelete(List<String> idList);
}
