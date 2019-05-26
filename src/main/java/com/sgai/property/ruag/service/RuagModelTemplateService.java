package com.sgai.property.ruag.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.sgai.common.utils.DateUtils;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ruag.dao.RuagModelCalendarDao;
import com.sgai.property.ruag.dao.RuagModelTemplateDao;
import com.sgai.property.ruag.dao.RuagWorkModelDatailDao;
import com.sgai.property.ruag.dto.RuagWorkModelDatailVo;
import com.sgai.property.ruag.entity.RuagModelCalendar;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;


/**
 *
    * @ClassName: RuagModelTemplateService
    * @com.sgai.property.commonService.vo;(运行模式定义Service)
    * @author 王天尧
    * @date 2018年1月2日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagModelTemplateService extends CrudServiceExt<RuagModelTemplateDao, RuagModelTemplate> {
	@Autowired
	private RuagWorkModelDatailService ruagWorkModelDatailService;
	@Autowired
	private RuagWorkModelDatailDao ruagWorkModelDatailDao;
	@Autowired
	private RuagModelCalendarService  ruagModelCalendarService;
	@Autowired
	private RuagModelCalendarDao  ruagModelCalendarDao;
	@Autowired
	private RuagDeviceCalendarInstctionService  ruagDeviceCalendarInstctionService;
	@Autowired
	private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
	public RuagModelTemplate get(String id) {
		return super.get(id);
	}

	public List<RuagModelTemplate> findList(RuagModelTemplate ruagModelTemplate) {
		return super.findList(ruagModelTemplate);
	}

	public Page<RuagModelTemplate> findPage(Page<RuagModelTemplate> page, RuagModelTemplate ruagModelTemplate) {
		return super.findPage(page, ruagModelTemplate);
	}

	@Transactional(readOnly = false)
	public void save(RuagModelTemplate ruagModelTemplate) {
			super.save(ruagModelTemplate);

	}

	@Transactional(readOnly = false)
	public void delete(RuagModelTemplate ruagModelTemplate) {
		super.delete(ruagModelTemplate);
	}
	/**
	 *
	    * @Title: getList
	    * @com.sgai.property.commonService.vo;(查询运行模式列表（带分页）)
	    * @param @param ruagModelTemplate
	    * @param @param page
	    * @param @return    参数
	    * @return Page<RuagModelTemplate>    返回类型
	    * @throws
	 */
	@Transactional
	public Page<RuagModelTemplate> getList(RuagModelTemplate ruagModelTemplate,Page<RuagModelTemplate> page) {
		Page<RuagModelTemplate> pageList = findPage(page, ruagModelTemplate);
		return pageList;
	}
	/**
	 *
	    * @Title: getAllList
	    * @com.sgai.property.commonService.vo;(查询所有运行模式)
	    * @param @return    参数
	    * @return List<RuagModelTemplate>    返回类型
	    * @throws
	 */
	@SuppressWarnings("deprecation")
	@Transactional
	public List<RuagModelTemplate> getAllList() {
		return dao.findAllList();
	}
	/**
	 *
	    * @Title: delete
	    * @com.sgai.property.commonService.vo;(删除运行模式)
	    * @param @param id 运行模式id
	    * @param @return    参数
	    * @return Map<String,String>    返回类型
	    * @throws
	 */
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public Map<String,String> deleteModel(String id) {
		LoginUser user = UserServletContext.getUserInfo();
		Map<String,String> result = new HashMap<String,String>();
		//首先判断当前模式的状态
		String status = get(id).getStatus();
		if(status.equals("1")) {
		    //表示模式已启动禁止删除操作
			result.put("msg", "start");
		}else {
			//表示未启动，可以删除，同时将该模式下的设备信息一同删除，包括参数设置信息，将日程表中的模式删除
			RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
			ruagWorkModelDatail.setModelTemplateId(id);
			List<RuagWorkModelDatail> List = ruagWorkModelDatailService.findList(ruagWorkModelDatail);
			if(List.size()==0) {
				delete(get(id));
				ruagModelCalendarService.deleteByModlId(id);
				result.put("msg", "success");
			}else {
				for (RuagWorkModelDatail workModelDatail : List) {
					RuagModelDeviceParamerSet ruagModelDeviceParamerSet = new RuagModelDeviceParamerSet();
					ruagModelDeviceParamerSet.setWorkModelId(workModelDatail.getId());
					ruagModelDeviceParamerSetService.deleteByModelId(ruagModelDeviceParamerSet);
					ruagWorkModelDatailService.delete(workModelDatail);
				}
				ruagModelCalendarService.deleteByModlId(id);
				delete(get(id));
				result.put("msg", "success");
			}

		}
		return result;
	}
	/**
	 * @throws ParseException
	 *
	    * @Title: saveMT
	    * @com.sgai.property.commonService.vo;(保存或修改运行策略)
	    * @param @param id 运行策略id
	    * @param @param controlCode 控制类型编码
	    * @param @param strategyCode 策略类型编码
	    * @param @param controlType 控制类型
	    * @param @param strategyType 策略类型
	    * @param @param modelDegree  运行策略等级
	    * @param @param workModeName 运行策略名称
	    * @param @param status       运行状态
	    * @param @param selectWorkTime 起止时间数组
	    * @param @param seDate 起止时间
	    * @param @return    参数
	    * @return Map<String,String>    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public Map<String,String> saveMT(
			String id,
			String controlCode,
			String strategyCode,
			String modelDegree,
			String workModeName,
			String seDate,
			String workTime
			) throws ParseException {
		LoginUser user = UserServletContext.getUserInfo();
		Map<String,String> result = new HashMap<String,String>();
		RuagModelTemplate ruagModelTemplate = new RuagModelTemplate();
		ruagModelTemplate.setWorkModeName(workModeName);
		ruagModelTemplate.setWorkTime(workTime);
		ruagModelTemplate.setComCode(user.getComCode());
		ruagModelTemplate.setModuCode(user.getModuCode());
		RuagModelTemplate modelTemplate = dao.findByName(ruagModelTemplate);
		//判断是日期控制还是时间控制
		if(controlCode.equals("time")) {
			    if(modelTemplate==null) {
			    	ruagModelTemplate.setControlCode(controlCode);
			        ruagModelTemplate.setControlType("时间控制");
					ruagModelTemplate.setStrategyCode(strategyCode);
					ruagModelTemplate.setStatus("0");
					ruagModelTemplate.setModelDegree("0");
					ruagModelTemplate.setEnabledFlag("Y");
					ruagModelTemplate.setWorkTime(workTime);
					save(ruagModelTemplate);
					result.put("msg", "success");
			    }else {
			    	result.put("msg", "repeat");
			    }

		}else {
			String startDate= seDate.substring(0, 10);
			String endDate = seDate.substring(12, 23);
			List<Date> selectDates = DateUtils.findDates(sdf.parse(startDate), sdf.parse(endDate));
			if(id.equals("")) {
				 if(modelTemplate==null) {
					ruagModelTemplate.setControlCode(controlCode);
			    	ruagModelTemplate.setControlType("日期控制");
					ruagModelTemplate.setStartDate(startDate);
					ruagModelTemplate.setEndDate(endDate);
					ruagModelTemplate.setControlCode(controlCode);
					ruagModelTemplate.setStrategyCode(strategyCode);
					ruagModelTemplate.setModelDegree(modelDegree);
					ruagModelTemplate.setStatus("0");
					ruagModelTemplate.setStrategyType(strategyCode);
					ruagModelTemplate.setEnabledFlag("Y");
					save(ruagModelTemplate);
					ruagModelCalendarService.saveMC(workTime, selectDates, ruagModelTemplate);
					result.put("msg", "success");
				}else {
					result.put("msg", "repeat");
				}
			}else {
				RuagModelTemplate ruagModelTemplate2 = get(id);
				ruagModelTemplate2.setStartDate(startDate);
				ruagModelTemplate2.setEndDate(endDate);
				ruagModelTemplate2.setWorkTime(workTime);
				ruagModelTemplate2.setModelDegree(modelDegree);
				ruagModelTemplate2.setStatus("0");
				save(ruagModelTemplate2);
				ruagModelCalendarService.saveMC(workTime, selectDates, ruagModelTemplate2);
				result.put("msg", "success");
			}
		}
		return result;

	}
	/**
	 * @throws ServletException
	 * @throws IOException
	 * @throws SchedulerException
	 * @throws ParseException
	 *
	    * @Title: startModel
	    * @com.sgai.property.commonService.vo;(开启日期控制类型运行策略)
	    * @param @param id 运行策略id
	    * @param @param startTime 开始时间
	    * @param @param endTime   结束时间
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void startModel(String id,String startTime,String endTime) throws ParseException, SchedulerException, IOException, ServletException {
    		//构造存放时间的数组，生成指令时使用
    		List<Date> dateList = new ArrayList<>();
    		List<Date> dates = new ArrayList<>();
    		//更新运行策略状态
    		 updateModelTemplate("1", id);
    		//更新运行策略设置状态
    		List<RuagWorkModelDatail> workModels = ruagWorkModelDatailService.updateWorkModel("1",id);
    		//更新策略日程状态
    		dateList=ruagModelCalendarService.update(id, startTime, endTime, "1",dateList);
    		//如果该模式下有设置了workmodels 且 查询到的模式日历记录不为空 则进行操作：生成指令，更新定时任务
    		List<RuagModelCalendar> calendars = ruagModelCalendarService.getCalendars(id, startTime, endTime);
    		Date today = sdf2.parse(sdf2.format(new Date()));
    		Date tomorrow = DateUtils.getDateAfter(today, 1);
    		if(workModels.size()!=0&&calendars.size()!=0) {
    			if(dateList.contains(tomorrow)) {
    				dates.add(tomorrow);
    				String generateInsStart = ruagDeviceCalendarInstctionService.generateInsStart(id, dates, workModels);
    			}
    		}
    		//若有与临时模式的指令有冲突，改为无效
    		for (Date date : dates) {
    			//若是开启计划模式，还要判断其指令是否影响了正在开启的临时模式
    			RuagModelCalendar ruagModelCalendarQ = new RuagModelCalendar();
    			ruagModelCalendarQ.setModelStatus("1");
    			ruagModelCalendarQ.setCurDate(date);
    			ruagModelCalendarQ.setControlCode("time");
    			List<RuagModelCalendar> findList = ruagModelCalendarService.findList(ruagModelCalendarQ);
    			if(findList.size()>0) {
    				for (RuagModelCalendar ruagModelCalendar2 : findList) {
    					RuagWorkModelDatail ruagWorkModelDatailQ = new RuagWorkModelDatail();
    		    		ruagWorkModelDatailQ.setModelTemplateId(ruagModelCalendar2.getModelId());
    		    		List<RuagWorkModelDatail> workModelsQ = ruagWorkModelDatailService.findList(ruagWorkModelDatailQ);
    					ruagModelCalendarService.selectEffective( ruagModelCalendar2, workModelsQ, today);
    				}
    			}
    		}

	}
	/**
	 *
	    * @Title: updateModelTemplate
	    * @com.sgai.property.commonService.vo;(更新运行策略状态)
	    * @param @param status 状态
	    * @param @param id    运行策略id
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void updateModelTemplate(String status,String id) {
		RuagModelTemplate ruagModelTemplate = get(id);
		ruagModelTemplate.setStatus(status);
		save(ruagModelTemplate);
	}

	/**
	    * @Title: stopModel
	    * @com.sgai.property.commonService.vo;(关闭运行策略)
	    * @param @param id 运行策略id
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	    */
	@Transactional(readOnly = false)
	public void stopModel(String id,String startTime,String endTime) {
		//构造存放时间的数组，删除指令时使用
		List<Date> dateList = new ArrayList<>();
		/*updateModelTemplate("0", id);*/
		//更新运行策略设置状态
		ruagWorkModelDatailService.updateWorkModel("0",id);
		//更新策略日程状态
		ruagModelCalendarService.update(id, startTime, endTime, "0",dateList);
		//删除 指令表中相关指令  不删除	当天的指令
		ruagDeviceCalendarInstctionService.deleteIns(id,startTime,endTime);
	}
	/**
	 * @return
	 *
	    * @Title: checkEffictive
	    * @com.sgai.property.commonService.vo;(检查运行策略是否在启用中)
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public boolean checkEffictive(){
			String nowDate = sdf2.format(new Date());
			String nowTime = sdf3.format(new Date());
			RuagModelTemplate ruagModelTemplateQ = new RuagModelTemplate();
			List<RuagModelTemplate> allList = findList(ruagModelTemplateQ);
		    for (RuagModelTemplate ruagModelTemplate : allList) {
			RuagModelCalendar ruagModelCalendar = new RuagModelCalendar();
			ruagModelCalendar.setModelId(ruagModelTemplate.getId());
			ruagModelCalendar.setModelStatus("1");
			Map<String,String> sqlMap = new HashMap<String,String>();
			if(ruagModelTemplate.getControlCode().equals("date")) {
				sqlMap.put("sql", "a.cur_date>='"+nowDate+"'");
			}else {
				sqlMap.put("sql","((A .CUR_DATE = '"+nowDate+"'AND A .TIME_END > '"+nowTime+"')OR A .CUR_DATE > '"+nowDate+"')");
			}
			ruagModelCalendar.setSqlMap(sqlMap);
			List<RuagModelCalendar> findList = ruagModelCalendarService.findList(ruagModelCalendar);
			if(findList.size()==0) {
				ruagModelTemplate.setStatus("0");
				save(ruagModelTemplate);
			}else {
				ruagModelTemplate.setStatus("1");
				save(ruagModelTemplate);
			}
		}
		return true;
	}
	/**
	 *
	    * @Title: checkConflict
	    * @com.sgai.property.commonService.vo;(判断是否存在与同等级的计划模式所挂设备冲突的情况)
	    * @param @param id
	    * @param @param startTime
	    * @param @param endTime
	    * @param @return    参数
	    * @return Map<String,String>    返回类型
	    * @throws
	 */
	public List<Map<String,List<String>>> checkConflict(String id,String startTime,String endTime,List<String> DeviceCodes){
		LoginUser user = UserServletContext.getUserInfo();
		List<Map<String,List<String>>> result = new ArrayList<Map<String,List<String>>>();
		RuagModelTemplate ruagModelTemplate1 = get(id);
		//首先判断是否存在优先等级相同的其他计划模式的存在
		RuagModelTemplate ruagModelTemplate2 = new RuagModelTemplate();
		ruagModelTemplate2.setControlCode("date");
		ruagModelTemplate2.setModelDegree(ruagModelTemplate1.getModelDegree());
		List<RuagModelTemplate> findList = findList(ruagModelTemplate2);
		//判断时间域上是否有冲突
		for (RuagModelTemplate ruagModelTemplate : findList) {
			if(!(ruagModelTemplate.getId().equals(id))) {
				Map<String,String> params = new HashMap<String,String>();
				params.put("startTime", startTime);
				params.put("endTime", endTime);
				params.put("modelId", ruagModelTemplate.getId());
				params.put("comCode", user.getComCode());
				params.put("moduCode", user.getModuCode());
				List<RuagModelCalendar> findListByDate = ruagModelCalendarDao.findListByDate(params);
				//如果该时间段内存在策略日程，那么代表时间有冲突，接下来判断是否设备上有冲突
				if(findListByDate.size()>0) {
					List<String> findDevices=DeviceCodes;
					Map<String,String> params3 = new HashMap<String,String>();
					params3.put("modelTemplateId", ruagModelTemplate.getId());
					params3.put("comCode", user.getComCode());
					params3.put("moduCode", user.getModuCode());
					List<String> findDevicesOther = ruagWorkModelDatailDao.findDevicesCodes(params3);
					findDevices.retainAll(findDevicesOther);
					if(findDevices.size()>0) {
						Map<String,List<String>> params4= new HashMap<String,List<String>>();
						params4.put(ruagModelTemplate.getWorkModeName(), findDevices);
						result.add(params4);
						break;
					}
				}
			}

		}
		return result;
	}
	/**
	 *
	    * @Title: checkConflict
	    * @com.sgai.property.commonService.vo;(判断是否存在与其他临时模式所挂设备冲突的情况)
	    * @param @param id
	    * @param @param startTime
	    * @param @param endTime
	    * @param @return    参数
	    * @return Map<String,String>    返回类型
	    * @throws
	 */
	public List<Map<String,List<String>>> checkTimeConflict(String date,String id,String startTime,String endTime,String flag){
		LoginUser user = UserServletContext.getUserInfo();
		List<Map<String,List<String>>> result = new ArrayList<Map<String,List<String>>>();
		//首先判断是否存在其他时间域有冲突的临时模式的存在
		Map<String,String> params = new HashMap<String,String>();
		params.put("timeStart", startTime);
		params.put("timeEnd", endTime);
		if(flag.equals("start")) {
			params.put("modelId", id);
		}else {
			params.put("modelId", "0");
		}
		params.put("comCode", user.getComCode());
		params.put("moduCode", user.getModuCode());
		params.put("date",date);
		List<RuagModelCalendar> findTimeBySE = ruagModelCalendarDao.findTimeBySE(params);
		//如果结果集有数据证明时间域上有冲突，接下来判断设备是否有冲突
		if(findTimeBySE.size()>0) {
			Map<String,String> params2 = new HashMap<String,String>();
			params2.put("modelTemplateId", id);
			params2.put("comCode", user.getComCode());
			params2.put("moduCode", user.getModuCode());
			for (RuagModelCalendar ruagModelCalendar : findTimeBySE) {
				List<String> findDevices = ruagWorkModelDatailDao.findDevicesCodes(params2);
				//如果该时间段内存在策略日程，那么代表时间有冲突，接下来判断是否设备上有冲突
					Map<String,String> params3 = new HashMap<String,String>();
					params3.put("modelTemplateId", ruagModelCalendar.getModelId());
					params3.put("comCode", user.getComCode());
					params3.put("moduCode", user.getModuCode());
					List<String> findDevicesOther = ruagWorkModelDatailDao.findDevicesCodes(params3);
					findDevices.retainAll(findDevicesOther);
					if(findDevices.size()>0) {
						Map<String,List<String>> params4= new HashMap<String,List<String>>();
						params4.put(ruagModelCalendar.getModelName(), findDevices);
						result.add(params4);
					}
			}
		}
		return result;
	}
	/**
	 * @throws ParseException
	 *
	    * @Title: checkOutOfTime
	    * @com.sgai.property.commonService.vo;(判断是否过期)
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public boolean checkOutOfTime() throws ParseException {
		//获取日期的毫秒数
		long nowDate = sdf.parse(sdf.format(new Date())).getTime();
		//获取当前时间点的毫秒数
		long nowTime = sdf3.parse(sdf3.format(new Date())).getTime();
		RuagModelTemplate ruagModelTemplateQ = new RuagModelTemplate();
		List<RuagModelTemplate> allList = dao.findList(ruagModelTemplateQ);
		for (RuagModelTemplate ruagModelTemplate : allList) {
			RuagModelCalendar ruagModelCalendar = new RuagModelCalendar();
			ruagModelCalendar.setModelId(ruagModelTemplate.getId());
			List<RuagModelCalendar> findList = ruagModelCalendarService.findList(ruagModelCalendar);
			if(findList.size()>0) {
				//获取策略日程日期的毫秒数
				long calendartime = findList.get(0).getCurDate().getTime();
				if(ruagModelTemplate.getControlCode().equals("time")) {
					Long timeEnd =sdf3.parse(findList.get(0).getTimeEnd()).getTime() ;
					if (timeEnd<nowTime){
						ruagModelTemplate.setStatus("0");
						save(ruagModelTemplate);
		    		}
				}else {
					if(calendartime<nowDate) {
						ruagModelTemplate.setStatus("3");
						save(ruagModelTemplate);
					}
				}

			}
		}
		return true;
	}
}
