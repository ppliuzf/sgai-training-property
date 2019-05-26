package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtDefectAttachment;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQtDefectAttachmentDao extends MoreDataSourceDao<QtDefectAttachment>{
    int batchInsert(List<QtDefectAttachment> attachments);
}