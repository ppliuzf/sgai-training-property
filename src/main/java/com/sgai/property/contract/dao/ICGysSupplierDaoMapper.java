package com.sgai.property.contract.dao;
import com.sgai.property.contract.entity.GysSupplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICGysSupplierDaoMapper {

    List<GysSupplier> findList(GysSupplier supplier);

    GysSupplier getById(GysSupplier supplier);

    int getCount(GysSupplier supplier);
 
}