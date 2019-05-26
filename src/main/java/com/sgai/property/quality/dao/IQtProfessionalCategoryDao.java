package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtProfessionalCategory;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IQtProfessionalCategoryDao extends MoreDataSourceDao<QtProfessionalCategory>{
    List<CategoryVo> listCategory(CategoryVo categoryVo);

    List<QtProfessionalCategory> findListByTypeName(QtProfessionalCategory entity);
}