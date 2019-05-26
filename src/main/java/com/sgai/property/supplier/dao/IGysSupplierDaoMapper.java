package com.sgai.property.supplier.dao;
import com.sgai.property.supplier.entity.GysSupplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGysSupplierDaoMapper {

    List<GysSupplier> findList(GysSupplier supplier);

    GysSupplier getSupperlierByName(GysSupplier supplier);

    int getCount(GysSupplier supplier);
 
}