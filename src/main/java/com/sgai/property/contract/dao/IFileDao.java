package com.sgai.property.contract.dao;

import com.sgai.property.contract.entity.HtFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IFileDao {

    void save(HtFile htFile);

    /**
     *
     * @param file
     * @return
     */
    List<HtFile> getFileListByNo(HtFile file);

    void updateById(HtFile htFile);
}
