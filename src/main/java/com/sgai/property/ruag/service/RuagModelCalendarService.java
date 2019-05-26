package com.sgai.property.ruag.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.DateUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ruag.dao.RuagModelCalendarDao;
import com.sgai.property.ruag.dto.RuagModelCalendarVo;
import com.sgai.property.ruag.entity.*;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
    * @ClassName: RuagModelCalendarService
    * @com.sgai.property.commonService.vo;(策略运行日程Service)
    * @author 王天尧
    * @date 2018年1月3日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagModelCalendarService extends CrudServiceExt<RuagModelCalendarDao, RuagModelCalendar> {
	@Autowired
	private RuagModelTemplateService ruagModelTemplateService;
	@Autowired
	private RuagWorkModelDatailService ruagWorkModelDatailService;
	@Autowired
	private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
	@Autowired
	private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;
	@Autowired
	private RuagCalendarWorkModelService RuagCalendarWorkModelService;
	private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
	public RuagModelCalendar get(String id) {
		return super.get(id);
	}

	public List<RuagModelCalendar> findList(RuagModelCalendar ruagModelCalendar) {
		return super.findList(ruagModelCalendar);
	}

	public Page<RuagModelCalendar> findPage(Page<RuagModelCalendar> page, RuagModelCalendar ruagModelCalendar) {
		return super.findPage(page, ruagModelCalendar);
	}

	@Transactional(readOnly = false)
	public void save(RuagModelCalendar ruagModelCalendar) {
		super.save(ruagModelCalendar);
	}

	@Transactional(readOnly = false)
	public void delete(RuagModelCalendar ruagModelCalendar) {
		super.delete(ruagModelCalendar);
	}
	/**
	 * @Title: saveMC
	    * @com.sgai.property.commonService.vo;(保存运行策略日程)
	    * @param @param workTime 周一到周日字符串
	    * @param @param selectDates 起止时间数组
	    * @param @param ruagModelTemplate    参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void saveMC(String workTime,List<Date>selectDates,RuagModelTemplate ruagModelTemplate) {
		//创建数组盛放根据星期过滤掉的日期
		ArrayList<Date> dates = new ArrayList<>();
		for (Date date :selectDates) {
			String dateWeek = getWeekOfDate(date);
    		if (workTime.contains(dateWeek)) {
				dates.add(date);
			}
		}
		RuagModelCalendar calendarNew = new RuagModelCalendar();
		calendarNew.setModelId(ruagModelTemplate.getId());
		dao.deleteByModelId(calendarNew);
		//新建一个运行策略时即在 rugModelCalendar中添加对应记录  --记录状态  0(未开启)
    	for (Date date : dates) {
    		RuagModelCalendar calendar = new RuagModelCalendar();
    		calendar.setModelId(ruagModelTemplate.getId());
    		calendar.setModelName(ruagModelTemplate.getWorkModeName());
    		calendar.setControlCode(ruagModelTemplate.getControlCode());
    		calendar.setControlType(ruagModelTemplate.getControlType());
    		calendar.setModelDegree(Long.parseLong(ruagModelTemplate.getModelDegree()));
    		calendar.setModelStatus("0");
    		calendar.setCurDate(date);
    		calendar.setChangeFlag("N");
    		save(calendar);
    	}
	}
	/**
	 *
	    * @Title: getWeekOfDate
	    * @com.sgai.property.commonService.vo;(获取当前日期是星期几)
	    * @param @param dt 日期
	    * @param @return    当前日期是星期几
	    * @return String    返回类型
	    * @throws
	 */
    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     *
        * @Title: update
        * @com.sgai.property.commonService.vo;(更新运行策略日程状态)
        * @param @param id 运行策略id
        * @param @param startTime 开始时间
        * @param @param endTime   结束时间
        * @return void    返回类型
        * @throws
     */
    public List<Date> update(String id,String startTime,String endTime,String status,List<Date> dateList) {
    	//只影响第二天到模式设置的结束时间的数据   当天的不影响
    	List<RuagModelCalendar> calendars = getCalendars(id,startTime,endTime);
    	if(calendars.size()>0) {
    		//更新策略日程状态
        	for (RuagModelCalendar modelCalendar : calendars) {
        		dateList.add(modelCalendar.getCurDate());
        		modelCalendar.setModelStatus(status);
        		modelCalendar.setChangeFlag("N");
        		save(modelCalendar);
    		}
    	}
    	return dateList;
    }
    /**
     *
        * @Title: getCalendars
        * @com.sgai.property.commonService.vo;(获取属于某个模式的明天到结束日期的运行策略日程记录)
        * @param @param id 运行策略id
        * @param @param startTime
        * @param @param endTime
        * @param @return    参数
        * @return List<RuagModelCalendar>    返回类型
        * @throws
     */
    public List<RuagModelCalendar> getCalendars(String id,String startTime,String endTime) {
    	LoginUser user = UserServletContext.getUserInfo();
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("modelId", id);
    	params.put("startTime", startTime);
    	params.put("endTime", endTime);
    	params.put("comCode", user.getComCode());
    	params.put("moduCode", user.getModuCode());
    	return dao.findListByDate(params);
	}
    /**
     *
        * @Title: getList
        * @com.sgai.property.commonService.vo;(获取策略日程记录)
        * @param @return
        * @param @throws ParseException    参数
        * @return List<RuagModelCalendarVo>    返回类型
        * @throws
     */
    @SuppressWarnings("deprecation")
    @Transactional(readOnly = false)
	public List<RuagModelCalendarVo> getList() throws ParseException{
    	RuagModelCalendar ruagModelCalendar = new RuagModelCalendar();
		List<RuagModelCalendar> allList = findList(ruagModelCalendar);
		//创建list集合存放vo类
		List<RuagModelCalendarVo> list=new ArrayList<RuagModelCalendarVo>();
		//将所需字段放入vo类中
    	for (RuagModelCalendar modelCalendar : allList) {
    		RuagModelCalendarVo ruagModelCalendarVo = new RuagModelCalendarVo();
    		//获取日期的毫秒数
    		long nowDate = sdf.parse(sdf.format(new Date())).getTime();
    		//获取当前时间点的毫秒数
    		long nowTime = sdf1.parse(sdf1.format(new Date())).getTime();
    		//获取策略日程日期的毫秒数
    		long calendartime = modelCalendar.getCurDate().getTime();
    		String title="";
    		//获取开始时间
    		String start = Long.toString(calendartime);
    		//获取结束时间
    		String end   = Long.toString(calendartime+86400000);
    		ruagModelCalendarVo.setId(modelCalendar.getModelId());
    		ruagModelCalendarVo.setCalendarId(modelCalendar.getId());
    		ruagModelCalendarVo.setStart(start);
    		ruagModelCalendarVo.setEnd(end);
    		//判断是临时模式还是计划模式
			if(modelCalendar.getControlCode().equals("time")) {
				//如果是时间控制，策略名称后面还要加时间点
				title = modelCalendar.getModelName()+":"+ modelCalendar.getTimeStart()+"-"+modelCalendar.getTimeEnd();
			}else {
				title = modelCalendar.getModelName();
			}
			ruagModelCalendarVo.setTitle(title);
			//比较策略日程日期,设置状态，0是关闭，1是开启，2是删除，3是过去时
    		//如果策略日程日期小于当前日期，设置状态3，不可操作
			if(modelCalendar.getControlCode().equals("time")){
					Long timeEnd =sdf1.parse(modelCalendar.getTimeEnd()).getTime() ;
					if(calendartime<nowDate) {
		    			ruagModelCalendarVo.setStatus("3");
		    			list.add(ruagModelCalendarVo);
		    		}else if (calendartime==nowDate&&timeEnd<nowTime){
		    			ruagModelCalendarVo.setStatus("3");
		    			list.add(ruagModelCalendarVo);
		    		}else if(modelCalendar.getModelStatus().equals("0")) {
		    			ruagModelCalendarVo.setStatus("0");
		    			list.add(ruagModelCalendarVo);
		    		}else if (modelCalendar.getModelStatus().equals("1")) {
		    			ruagModelCalendarVo.setStatus("1");
		    			list.add(ruagModelCalendarVo);
		    		}else if(modelCalendar.getModelStatus().equals("2")) {
		    			continue;
		    		}
    		}else{
    			if(calendartime<nowDate) {
        			ruagModelCalendarVo.setStatus("3");
        			list.add(ruagModelCalendarVo);
        		}else if(modelCalendar.getModelStatus().equals("0")) {
        			ruagModelCalendarVo.setStatus("0");
        			list.add(ruagModelCalendarVo);
        		}else if (modelCalendar.getModelStatus().equals("1")) {
        			ruagModelCalendarVo.setStatus("1");
        			list.add(ruagModelCalendarVo);
        		}else if(modelCalendar.getModelStatus().equals("2")) {
        			continue;
        		}
    		}
		}
    	return list;

    }
    /**
     * @return
     * @throws ParseException
     *
        * @Title: saveTimeModel
        * @com.sgai.property.commonService.vo;(保存时间控制模式到策略日程表里)
        * @param @param modelTemplateId 运行策略id
        * @param @param timeStart  开始时间
        * @param @param timeEnd    结束时间
        * @return void    返回类型
        * @throws
     */
    @Transactional(readOnly = false)
    public Map<String, String> saveTimeModel(String date,String modelTemplateId,String timeStart,String timeEnd) throws ParseException {
    	//首先判断是否与已存在的临时模式有冲突
    	Map<String,String> result = new HashMap<String,String>();
    	List<Map<String, List<String>>> checkTimeConflict = ruagModelTemplateService.checkTimeConflict(date, modelTemplateId, timeStart, timeEnd,"creat");
    	//如果返回结果不为空证明有冲突
    	String message="";
    	if(checkTimeConflict.size()>0) {
    		String modeName="";
    		String conflictDeviceCodes="";
    		for (Map<String, List<String>> map : checkTimeConflict) {
    			 for (String key : map.keySet())
   			  {
   		    	  modeName=key;
   			  }
   			  //遍历map中的值
   			  for (List<String> value : map.values())
   			  {
   				  conflictDeviceCodes+=value;
   			  }
   			message+= "策略名称-"+modeName+":"+"设备编码-"+conflictDeviceCodes+";";
			}

			  result.put("msg", "conflict");
			  result.put("message",message);
    	}else {
        	Map<String,String> sqlMap = new HashMap<String,String>();
        	//获得运行策略对象
        	RuagModelTemplate ruagModelTemplate = ruagModelTemplateService.get(modelTemplateId);
        	RuagModelCalendar modelCalendar = new RuagModelCalendar();
        	//提前
        	modelCalendar.setTimeStart(timeStart);
        	modelCalendar.setTimeEnd(timeEnd);
        	modelCalendar.setCurDate(sdf.parse(date));
        	/*sqlMap.put("sqlMap", "a.model_status !=2");
        	modelCalendar.setSqlMap(sqlMap);
        	List<RuagModelCalendar> findList = findList(modelCalendar);
        	if(findList.size()==0) {*/
        		//保存到策略日程表里
            	modelCalendar.setControlCode(ruagModelTemplate.getControlCode());
            	modelCalendar.setControlType(ruagModelTemplate.getControlType());
            	modelCalendar.setModelDegree(Long.parseLong(ruagModelTemplate.getModelDegree()));
            	modelCalendar.setModelId(modelTemplateId);
            	modelCalendar.setModelName(ruagModelTemplate.getWorkModeName());
            	modelCalendar.setModelStatus("0");
            	modelCalendar.setChangeFlag("N");
            	save(modelCalendar);
            	result.put("msg", "success");
        	/*}else {
        		result.put("msg", "repeat");
        	} */
    	}
    	return result;
    }
    /**
     * @throws ServletException
     * @throws IOException
     * @throws SchedulerException
     * @return
     * @throws ParseException
     *
        * @Title: startModelByCalendar
        * @com.sgai.property.commonService.vo;(开启某一天的某个运行策略)
        * @param @param id    策略日程id
        * @return void    返回类型
        * @throws
     */
    @Transactional(readOnly = false)
    public Map<String, String> startModelByCalendar(String id) throws ParseException, SchedulerException, IOException, ServletException {
    	Map<String,String> result = new HashMap<String,String>();
    	//更新策略日程的状态
    	RuagModelCalendar ruagModelCalendar = get(id);
    	List<Map<String, List<String>>> checkTimeConflict = new ArrayList<Map<String, List<String>>>();
    	if(ruagModelCalendar.getControlCode().equals("time")) {
    		 checkTimeConflict = ruagModelTemplateService.checkTimeConflict(sdf.format(ruagModelCalendar.getCurDate()), ruagModelCalendar.getModelId(), ruagModelCalendar.getTimeStart(), ruagModelCalendar.getTimeEnd(),"start");
    	}
    	//如果返回结果不为空证明有冲突
		String message="";
		if(checkTimeConflict.size()>0) {
			for (Map<String, List<String>> map : checkTimeConflict) {
				String modeName="";
				String conflictDeviceCodes="";
			      for (String key : map.keySet())
				  {
			    	  modeName=key;
				  }
				  //遍历map中的值
				  for (List<String> value : map.values())
				  {
					  conflictDeviceCodes+=value;
				  }
				message+= "策略名称-"+modeName+":"+"设备编码-"+conflictDeviceCodes+";";
			}

		  result.put("msg", "conflict");
		  result.put("message", message);
		}else {
			ruagModelCalendar.setModelStatus("1");
    	    save(ruagModelCalendar);
    	//更新运行策略状态
    	RuagModelTemplate ruagModelTemplate = ruagModelTemplateService.get(ruagModelCalendar.getModelId());
    	ruagModelTemplate.setStatus("1");
    	ruagModelTemplateService.save(ruagModelTemplate);
    	//判断该天是否为当天,如果是当天就生成指令
    	Date today = sdf.parse(sdf.format(new Date()));
    	if(ruagModelCalendar.getCurDate().equals(today)) {
    		RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
    		ruagWorkModelDatail.setModelTemplateId(get(id).getModelId());
    		List<RuagWorkModelDatail> workModels = ruagWorkModelDatailService.findList(ruagWorkModelDatail);
    		ruagDeviceCalendarInstctionService.generateInsByDay(today, workModels, ruagModelCalendar);
    		if("time".equals(ruagModelCalendar.getControlCode())) {
    			selectEffective( ruagModelCalendar, workModels, today);
    		}else {
    			//若是开启计划模式，还要判断其指令是否影响了正在开启的临时模式
    			RuagModelCalendar ruagModelCalendarQ = new RuagModelCalendar();
    			ruagModelCalendarQ.setModelStatus("1");
    			ruagModelCalendarQ.setCurDate(today);
    			ruagModelCalendarQ.setControlCode("time");
    			List<RuagModelCalendar> findList = findList(ruagModelCalendarQ);
    			if(findList.size()>0) {
    				for (RuagModelCalendar ruagModelCalendar2 : findList) {
    					RuagWorkModelDatail ruagWorkModelDatailQ = new RuagWorkModelDatail();
    		    		ruagWorkModelDatailQ.setModelTemplateId(ruagModelCalendar2.getModelId());
    		    		List<RuagWorkModelDatail> workModelsQ = ruagWorkModelDatailService.findList(ruagWorkModelDatailQ);
        				selectEffective( ruagModelCalendar2, workModelsQ, today);
    				}
    			}
    		}
    	}
    	result.put("msg", "success");
    }
    	return result;
    }
    /**
     * @return
     * @Title: stopMdoelByCalendar
        * @com.sgai.property.commonService.vo;(关闭某一天某个模式)
        * @param @param id    策略运行日程id
        * @return void    返回类型
        * @throws
     */
    @Transactional(readOnly = false)
    public Map<String, String> stopModelByCalendar(String id) {
    	    Map<String,String> result = new HashMap<String,String>();
    		List<RuagDeviceCalendarInstction> insList = new ArrayList<RuagDeviceCalendarInstction>();
        	//更新策略日程的状态
        	RuagModelCalendar ruagModelCalendar = get(id);
        	ruagModelCalendar.setModelStatus("0");
        	save(ruagModelCalendar);
        	//删除对应的指令：未发送的  ，当前模式的,指定日期的
        	RuagDeviceCalendarInstction ins = new RuagDeviceCalendarInstction();
        		ins.setDciDate(ruagModelCalendar.getCurDate());
            	ins.setInstructionStatus(0L);
        		ins.setEffectiveStatus1("1");
            	ins.setModelId(ruagModelCalendar.getModelId());
            	ins.setCalendarId(id);
            	//查找其他开启的计划模式，判断是否又与它冲突而被设置为无效的指令，如果有将其恢复为有效
            	RuagDeviceCalendarInstction insQ = new RuagDeviceCalendarInstction();
            	insQ.setDciDate(ruagModelCalendar.getCurDate());
            	insQ.setControlCode("Date");
            	insQ.setCompareObj(id);
            	insQ.setEffectiveStatus1("0");
            	List<RuagDeviceCalendarInstction> findList = ruagDeviceCalendarInstctionService.findList(insQ);
            	if(findList.size()>0) {
            		for (RuagDeviceCalendarInstction ruagDeviceCalendarInstction : findList) {
            			ruagDeviceCalendarInstction.setEffectiveStatus1("1");
            			ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction);
    				}
            	}
        	insList = ruagDeviceCalendarInstctionService.findList(ins);
        	for (RuagDeviceCalendarInstction instction : insList) {
        		ruagDeviceCalendarInstctionService.delete(instction);
    		}
        	result.put("msg", "success");
    	return result;
    }
    /**
     *
        * @Title: deleteMdoelByCalendar
        * @com.sgai.property.commonService.vo;(删除某一天的某个运行策略)
        * @param @param id   策略运行日程id
        * @return void    返回类型
        * @throws
     */
    @Transactional(readOnly = false)
    public void deleteMdoelByCalendar(String id) {
    	//更新策略日程的状态,2代表已删除
    	RuagModelCalendar ruagModelCalendar = get(id);
    	ruagModelCalendar.setModelStatus("2");
    	save(ruagModelCalendar);
    	//删除对应的指令：未发送的  ，当前模式的,指定日期的
    	RuagDeviceCalendarInstction ins = new RuagDeviceCalendarInstction();
    	if(ruagModelCalendar.getControlCode().equals("time")) {
    		ins.setDciDate(ruagModelCalendar.getCurDate());
        	ins.setInstructionStatus(0L);
        	ins.setModelId(ruagModelCalendar.getModelId());
        	ins.setCalendarId(id);
    	}else {
    		ins.setDciDate(ruagModelCalendar.getCurDate());
        	ins.setInstructionStatus(0L);
        	ins.setModelId(ruagModelCalendar.getModelId());
    	}
    	List<RuagDeviceCalendarInstction> insList = ruagDeviceCalendarInstctionService.findList(ins);
    	for (RuagDeviceCalendarInstction instction : insList) {
    		ruagDeviceCalendarInstctionService.delete(instction);
		}
    }
    /**
     * @throws ServletException
     * @throws IOException
     * @throws SchedulerException
     * @return
     *
        * @Title: startChecked
        * @com.sgai.property.commonService.vo;(保存某天某个运行策略的修改)
        * @param @param ids 运行策略配置id集合，字符串形式，以逗号隔开
        * @param @param id 策略日程id
        * @param @throws ParseException    参数
        * @return void    返回类型
        * @throws
     */
    @Transactional(readOnly = false)
    public Map<String, String> startChecked(String ids,String id) throws ParseException, SchedulerException, IOException, ServletException {
    	Map<String,String> result = new HashMap<String,String>();
    		RuagModelCalendar ruagModelCalendar = get(id);
        	String modelId = ruagModelCalendar.getModelId();
        	RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
    		ruagWorkModelDatail.setModelTemplateId(modelId);
    		List<RuagWorkModelDatail> workModels = ruagWorkModelDatailService.findList(ruagWorkModelDatail);
    		String[] modelDatilIds = ids.split(",");
    		List<RuagWorkModelDatail> workModelChecked = new ArrayList<RuagWorkModelDatail>();
    		//存放要关闭的运行策略配置id
    		List<String> workmodelidsToDel = new ArrayList<>();
    		//获取某个 modelTemplate 下 所有运行策略配置id
    		for (RuagWorkModelDatail workModel : workModels) {
    			workmodelidsToDel.add(workModel.getId());
    		}
		    //先将策略日程的关系清空
			RuagCalendarWorkModelService.deleteByCalendarId(id);
    		//获取待关闭的指令的 运行策略配置id集合
    		if(!("".equals(ids))&&!(ids.equals(null))) {
    			for(int i=0;i<modelDatilIds.length;i++){
    				//将策略日程id和策略配置id保存到关系表里
    				RuagCalendarWorkModel ruagCalendarWorkModel = new RuagCalendarWorkModel();
    				ruagCalendarWorkModel.setModelCalendarId(id);
    				ruagCalendarWorkModel.setWorkModelId(modelDatilIds[i]);
    				RuagCalendarWorkModelService.save(ruagCalendarWorkModel);
    				workmodelidsToDel.remove(modelDatilIds[i]);
    				workModelChecked.add(ruagWorkModelDatailService.get(modelDatilIds[i]));
    			}
    			//更新状态
    			ruagModelCalendar.setModelStatus("1");
    		}else {
    			//更新状态
    			ruagModelCalendar.setModelStatus("0");
    		}

    		//该策略日程下的策略配置已经发生改变，修改状态
    		ruagModelCalendar.setChangeFlag("Y");
    		save(ruagModelCalendar);
    		//删除已去掉设备的指令指令,同时删除关系表
    		if(workmodelidsToDel.size()>0) {
    			for (String workmodelId : workmodelidsToDel) {
    	    		RuagWorkModelDatail ruagWorkModelDatailNew = ruagWorkModelDatailService.get(workmodelId);
    	    		RuagDeviceCalendarInstction ins = new RuagDeviceCalendarInstction();
    	        	ins.setDciDate(ruagModelCalendar.getCurDate());
    	        	ins.setInstructionStatus(0L);
    	        	ins.setModelId(ruagWorkModelDatailNew.getModelTemplateId());
    	        	ins.setDeviceCode(ruagWorkModelDatailNew.getDeviceCode());
    	        	List<RuagDeviceCalendarInstction> insList = ruagDeviceCalendarInstctionService.findList(ins);
    	        	for (RuagDeviceCalendarInstction instction : insList) {
    	        		ruagDeviceCalendarInstctionService.delete(instction);
    	    		}
    			}
    		}
    		//判断该天是否为当天,如果是当天就生成指令
    		if(workModelChecked.size()>0) {
    			Date today = sdf.parse(sdf.format(new Date()));
    	    	if(ruagModelCalendar.getCurDate().equals(today)) {
    	    		//生成指令
    	    		ruagDeviceCalendarInstctionService.generateInsByDay(today, workModelChecked,ruagModelCalendar);
    	    	}
    		}
    		result.put("msg", "success");
    	return result;
    }
    /**
     *
        * @Title: findTodayAndTomorrow
        * @com.sgai.property.commonService.vo;(查询今天或明天的策略日程记录)
        * @param @param today 今天
        * @param @param tomorrow 明天
        * @param @return    参数
        * @return List<RuagModelCalendar>    返回类型
        * @throws
     */
    public List<RuagModelCalendar> findTodayAndTomorrow(String today,String tomorrow){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("today", today);
    	params.put("tomorrow", tomorrow);
    	return dao.findTodayAndTomorrow(params);
    }
    /**
     *
        * @Title: deleteByModlId
        * @com.sgai.property.commonService.vo;(根据模式id删除日程记录)
        * @param @param id    参数
        * @return void    返回类型
        * @throws
     */
    public void deleteByModlId(String id) {
    	RuagModelCalendar ruagModelCalendar = new RuagModelCalendar();
    	ruagModelCalendar.setModelId(id);
    	Map<String,String> sqlMap = new HashMap<String,String>();
    	String sql = "a.CUR_DATE>=str_to_date("+sdf.format(new Date())+",'%Y-%m-%d')";
    	List<RuagModelCalendar> findList = findList(ruagModelCalendar);
    	for (RuagModelCalendar ruagModelCalendar2 : findList) {
			delete(ruagModelCalendar2);
		}
    }
  /**
   *
      * @Title: selectEffective
      * @com.sgai.property.commonService.vo;(临时模式生成指令筛选有效指令)
      * @param @param ruagModelCalendar
      * @param @param workModels
      * @param @param today
      * @param @throws ParseException    参数
      * @return void    返回类型
      * @throws
   */
    @Transactional(readOnly = false)
    public void selectEffective(RuagModelCalendar ruagModelCalendar,List<RuagWorkModelDatail> workModels,Date today) throws ParseException {
    	//如果临时模式的指令与计划模式的指令冲突，以临时模式的指令为主，临时模式的起止时间内，其他指令无效
			for (RuagWorkModelDatail ruagWorkModelDatail2 : workModels) {
				RuagModelDeviceParamerSet paramerSet= new RuagModelDeviceParamerSet();
				paramerSet.setWorkModelId(ruagWorkModelDatail2.getId());
				List<RuagModelDeviceParamerSet> ParamerSets = ruagModelDeviceParamerSetService.findAllOfList(paramerSet);
				for (RuagModelDeviceParamerSet paramerSetNew : ParamerSets) {
				long offTime= Long.parseLong(paramerSetNew.getTimePoint().substring(1));
				String timeStart = DateUtils.longToString(DateUtils.stringToLong(ruagModelCalendar.getTimeStart(), "HH:mm")-offTime*60*1000, "HH:mm");
				String timeEnd =DateUtils.longToString(DateUtils.stringToLong(ruagModelCalendar.getTimeEnd(), "HH:mm")+offTime*60*1000, "HH:mm");
				RuagDeviceCalendarInstction instctionThree = new RuagDeviceCalendarInstction ();
				instctionThree.setDciDate(today);
				instctionThree.setDeviceCode(ruagWorkModelDatail2.getDeviceCode());
				instctionThree.setInstructionStatus(0L);
				instctionThree.setParameterId(paramerSetNew.getParameterId());
				instctionThree.setControlCode("date");
				instctionThree.setEffectiveStatus1("1");
				instctionThree.setProfCode(ruagWorkModelDatail2.getProfCode());
				instctionThree.setSpaceCode(ruagWorkModelDatail2.getSpaceCode());
				instctionThree.setComCode(ruagWorkModelDatail2.getComCode());
				instctionThree.setModuCode(ruagWorkModelDatail2.getModuCode());
				Map<String,String> sqlMap = new HashMap<String,String>();
				sqlMap.put("sql", "a.model_id!="+"'"+ruagModelCalendar.getModelId()+"'AND a.time_point>='"+timeStart+"'AND a.time_point<='"+timeEnd+"'");
				instctionThree.setSqlMap(sqlMap);
				List<RuagDeviceCalendarInstction> list = ruagDeviceCalendarInstctionService.findList(instctionThree);
				if(list.size()>0) {
					for (RuagDeviceCalendarInstction ruagDeviceCalendarInstction : list) {
						ruagDeviceCalendarInstction.setCompareObj(ruagModelCalendar.getId());
						ruagDeviceCalendarInstction.setEffectiveStatus1("0");
						ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction);
					}
				  }
				}
			}
    }
    /**
     *
        * @Title: findAllOfList
        * @com.sgai.property.commonService.vo;(查询所有数据不根据机构和模块去分)
        * @param @param ruagModelCalendar
        * @param @return    参数
        * @return List<RuagModelCalendar>    返回类型
        * @throws
     */
    public List<RuagModelCalendar> findAllOfList(RuagModelCalendar ruagModelCalendar) {
		return dao.findAllOfList(ruagModelCalendar);
	}
}
