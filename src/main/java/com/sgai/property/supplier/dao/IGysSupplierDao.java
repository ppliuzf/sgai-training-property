package com.sgai.property.supplier.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.supplier.entity.GysSupplier;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IGysSupplierDao extends MoreDataSourceDao<GysSupplier> {
    //根据供应商类型Id查询关联供应商数量
    int getTypeCount(GysSupplier gysSupplier);

    //根据供应商内容Id查询关联供应商数量
    int getContentCount(GysSupplier gysSupplier);

    //根据供应商等级Id查询关联供应商数量
    int getLevelCount(GysSupplier gysSupplier);
}