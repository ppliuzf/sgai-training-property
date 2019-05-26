
package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.MagazineInfo;
import com.sgai.property.wy.entity.MagazineSub;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: MagazineSubDao
 * (杂志订阅)
 * @author cui
 * @date 2018年2月1日
 * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MagazineSubDao extends CrudDao<MagazineSub> {

	void batchDelete(List<String> idList);

	List<MagazineInfo> findAllM();

	List<MagazineSub> queryMCount(MagazineSub magazine);

}
