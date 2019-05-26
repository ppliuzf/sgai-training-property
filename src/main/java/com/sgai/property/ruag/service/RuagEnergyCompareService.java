package com.sgai.property.ruag.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagEnergyCompareDao;
import com.sgai.property.ruag.entity.RuagEnergyCompare;
import com.sgai.property.ruag.entity.RuagModelTemplate;


/**
 * 能耗对比Service
 *
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagEnergyCompareService extends CrudServiceExt<RuagEnergyCompareDao, RuagEnergyCompare> {
	@Autowired
	private RuagEnergyCompareService ruagEnergyCompareService;



	public List<RuagEnergyCompare> findEnergyListById(RuagEnergyCompare ruagEnergyCompare) {
		return dao.findEnergyListById(ruagEnergyCompare);
	}

	public RuagEnergyCompare get(String id) {
		return super.get(id);
	}

	public List<RuagEnergyCompare> findList(RuagEnergyCompare ruagEnergyCompare) {
		return super.findList(ruagEnergyCompare);
	}

	public Page<RuagEnergyCompare> findPage(Page<RuagEnergyCompare> page, RuagEnergyCompare ruagEnergyCompare) {
		return super.findPage(page, ruagEnergyCompare);
	}

	@Transactional(readOnly = false)
	public void save(RuagEnergyCompare ruagEnergyCompare) {
		super.save(ruagEnergyCompare);
	}

	@Transactional(readOnly = false)
	public void delete(RuagEnergyCompare ruagEnergyCompare) {
		super.delete(ruagEnergyCompare);
	}

	public int computeDate(String startDate, String endDate, int weekDay, RuagModelTemplate ruagModelTemplate,
			String recordId) throws ParseException {
		// 判断当前时间是否大于起始时间   startDate
		DateFormat datef = new SimpleDateFormat("yyyy/MM/dd");
		Date ldate1 = datef.parse(startDate);
		if (ldate1.getTime() + 1 * 24 * 60 * 60 * 1000 < new Date().getTime()) {
			startDate = datef.format(new Date());
		} else {
			// 当前时间+1天
			startDate = datef.format(new Date(ldate1.getTime() + 1 * 24 * 60 * 60 * 1000));

		}
		DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
		Date bdate;
		int count = 0; // 星期一的次数
		try {
			while (true) {
				bdate = format1.parse(startDate);
				if (bdate.getTime() <= format1.parse(endDate).getTime()) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(bdate);
					if (cal.get(Calendar.DAY_OF_WEEK) == weekDay) {
						Random random = new Random();
						int before = random.nextInt(10) + 60;
						int after = random.nextInt(10) + 70;

						RuagEnergyCompare ruagEnergyCompare = new RuagEnergyCompare();
						String modelTemplateId = ruagModelTemplate.getId();// 策略id
						ruagEnergyCompare.setModelTemplateId(modelTemplateId);
						ruagEnergyCompare.setRecordId(recordId);
						ruagEnergyCompare.setDateTime(bdate);
						ruagEnergyCompare.setEnergyBefore(before + "");
						ruagEnergyCompare.setEnergyAftter(after + "");
						ruagEnergyCompare.setEnabledFlag("Y");
						ruagEnergyCompare.setComCode(ruagModelTemplate.getComCode());
						ruagEnergyCompareService.save(ruagEnergyCompare);
					}
					// 后一天
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					Date ldate = df.parse(startDate);
					startDate = df.format(new Date(ldate.getTime() + 1 * 24 * 60 * 60 * 1000));
				} else {
					break;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("星期一次数：" + count);
		return count;
	}

	// weekDay=2; 星期一 weekDay=3; 星期二 weekDay=4;星期三 weekDay=5; 星期四 weekDay=6; 星期五
	// weekDay=7;星期六 weekDay=1;星期日
	@Transactional(readOnly = false)
	public void saveDateEnergyRecord(RuagModelTemplate ruagModelTemplate, String recordId) throws ParseException {
		String startDate = ruagModelTemplate.getStartDate(); // 起始时间
		String endDate = ruagModelTemplate.getEndDate(); // 结束时间
		int weekDay;
		String workTime = ruagModelTemplate.getWorkTime();
		String workTimes[] = workTime.split(",");
		for (int i = 0; i < workTimes.length; i++) {
			if ("星期一".equals(workTimes[i])) {
				weekDay = 2;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);
			}
			if ("星期二".equals(workTimes[i])) {
				weekDay = 3;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
			if ("星期三".equals(workTimes[i])) {
				weekDay = 4;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
			if ("星期四".equals(workTimes[i])) {
				weekDay = 5;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
			if ("星期五".equals(workTimes[i])) {
				weekDay = 6;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
			if ("星期六".equals(workTimes[i])) {
				weekDay = 7;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
			if ("星期日".equals(workTimes[i])) {
				weekDay = 1;
				ruagEnergyCompareService.computeDate(startDate, endDate, weekDay, ruagModelTemplate, recordId);

			}
		}

	}



	@Transactional(readOnly = false)
	public void saveTimeEnergyRecord(RuagModelTemplate ruagModelTemplate, String recordId,String timeStart,String timeEnd) throws ParseException {
		int start=Integer.valueOf(timeStart.substring(0,2));
		int end=Integer.valueOf(timeEnd.substring(0,2));
		DateFormat format = new SimpleDateFormat("HH:mm");
		Date bdate;
		for(int i=start;i<end;i++) {

			bdate = format.parse(i+":00");

			Random random = new Random();
			int before = random.nextInt(10) + 60;
			int after = random.nextInt(10) + 70;

			RuagEnergyCompare ruagEnergyCompare = new RuagEnergyCompare();
			String modelTemplateId = ruagModelTemplate.getId();// 策略id
			ruagEnergyCompare.setModelTemplateId(modelTemplateId);
			ruagEnergyCompare.setRecordId(recordId);
			ruagEnergyCompare.setDateTime(bdate);
			ruagEnergyCompare.setEnergyBefore(before + "");
			ruagEnergyCompare.setEnergyAftter(after + "");
			ruagEnergyCompare.setEnabledFlag("Y");
			ruagEnergyCompare.setComCode(ruagModelTemplate.getComCode());
			ruagEnergyCompareService.save(ruagEnergyCompare);

		}

	}

}
