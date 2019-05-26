package com.sgai.property.meeting.service;
import com.sgai.property.commonService.dao.IDictGeneralDao;
import com.sgai.property.commonService.entity.DictGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MeetingDictGeneralServiceImpl extends MoreDataSourceCrudServiceImpl<IDictGeneralDao, DictGeneral> {
	@Autowired
	private IDictGeneralDao dictGeneralDao;

}