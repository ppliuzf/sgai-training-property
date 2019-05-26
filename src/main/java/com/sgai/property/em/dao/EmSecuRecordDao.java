package com.sgai.property.em.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.EmSecuRecord;

/**
 * 安保事件维护DAO接口
 * @author guanze
 * @version 2017-12-05
 */
@Mapper
public interface EmSecuRecordDao extends CrudDao<EmSecuRecord> {
	EmSecuRecord findNextCodeEmSecuRecord();
	EmSecuRecordVo getEmSecuRecord(EmSecuRecordVo emSecuRecord);
	EmSecuRecord getEmSecuRecords(EmSecuRecord emSecuRecord);
	List<EmSecuRecord> findSecuRecordList(EmSecuRecord emSecuRecord);
}