package com.sgai.property.supplier.dao;
import com.sgai.property.supplier.entity.HtFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IHtFileDao {

    List<HtFile> getFileListById(HtFile file);
 
}