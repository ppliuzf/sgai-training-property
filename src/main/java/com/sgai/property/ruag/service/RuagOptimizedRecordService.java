package com.sgai.property.ruag.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.ruag.dao.RuagOptimizedRecordDao;
import com.sgai.property.ruag.entity.RuagModelCalendar;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagOptimizedRecord;

/**
 * 优化记录Service
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagOptimizedRecordService extends CrudServiceExt<RuagOptimizedRecordDao, RuagOptimizedRecord> {
	@Autowired
	private RuagModelTemplateService ruagModelTemplateService;
	@Autowired
	private RuagEnergyCompareService ruagEnergyCompareService;
	@Autowired
	private RuagModelCalendarService ruagModelCalendarService;

	public RuagOptimizedRecord get(String id) {
		return super.get(id);
	}

	public List<RuagOptimizedRecord> findList(RuagOptimizedRecord ruagOptimizedRecord) {
		return super.findList(ruagOptimizedRecord);
	}

	public Page<RuagOptimizedRecord> findPage(Page<RuagOptimizedRecord> page, RuagOptimizedRecord ruagOptimizedRecord) {
		return super.findPage(page, ruagOptimizedRecord);
	}

	@Transactional(readOnly = false)
	public void save(RuagOptimizedRecord ruagOptimizedRecord) {
		super.save(ruagOptimizedRecord);
	}

	@Transactional(readOnly = false)
	public void delete(RuagOptimizedRecord ruagOptimizedRecord) {
		super.delete(ruagOptimizedRecord);
	}


	@Transactional(readOnly = false)
	public void saveRecord(String ids,LoginUser user) {
		String idList[]=ids.split(",");
		for(String id:idList) {
			RuagModelTemplate ruagModelTemplate =ruagModelTemplateService.get(id);
			RuagOptimizedRecord record=new RuagOptimizedRecord();
			record.setMdoelTemplateId(ruagModelTemplate.getId());
			record.setModelTemplateName(ruagModelTemplate.getWorkModeName());
			record.setOptimizationTime(new Date());
			record.setUserCode(user.getUserId());
			record.setUserName(user.getUserName());
			record.setComCode(user.getComCode());
			super.save(record);
			//处理数据，将数据插入到能源表
			try {
				if("date".equals(ruagModelTemplate.getControlCode())) {
					//按照日期天数  向能源表添加数据
					ruagEnergyCompareService.saveDateEnergyRecord(ruagModelTemplate,record.getId());
				}else {
					//按照时间   向能源表添加数据
					RuagModelCalendar ruagModelCalendar =new RuagModelCalendar();
					ruagModelCalendar.setModelId(ruagModelTemplate.getId());
					List<RuagModelCalendar> list =ruagModelCalendarService.findList(ruagModelCalendar);
					String timeStart=null;
					String timeEnd=null;
					for(int i=0;i<list.size();i++) {
						 timeStart=list.get(i).getTimeStart();
						 timeEnd=list.get(i).getTimeEnd();
					}

					ruagEnergyCompareService.saveTimeEnergyRecord(ruagModelTemplate,record.getId(),timeStart,timeEnd);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(record.getId());

		}

			}

}
