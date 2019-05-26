package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtTestItem;
import com.sgai.property.quality.vo.TestItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
@Mapper
public interface IQtTestItemDao extends MoreDataSourceDao<QtTestItem>{
    List<TestItemVo> listTestItem(TestItemVo testItemVo);

    QtTestItem getByObj(QtTestItem entity);

}