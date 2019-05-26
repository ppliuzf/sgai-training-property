package com.sgai.property.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.notice.entity.NoticeInfoReceipt;

@Mapper
public interface InfoReceiptVoDao {

	void batchInsert(List<NoticeInfoReceipt> infoReceiptList);

}
