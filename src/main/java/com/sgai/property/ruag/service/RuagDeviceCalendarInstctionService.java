package com.sgai.property.ruag.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.DateUtils;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import com.sgai.property.mq.Sender;
import com.sgai.property.mq.entity.DeviceParamSender;
import com.sgai.property.quartz.job.SendInsTask;
import com.sgai.property.quartz.service.ScheduleJobService;
import com.sgai.property.ruag.dao.RuagDeviceCalendarInstctionDao;
import com.sgai.property.ruag.entity.*;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 王天尧
 * @ClassName: RuagDeviceCalendarInstctionService
 * @date 2018年1月4日
 * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagDeviceCalendarInstctionService extends CrudServiceExt<RuagDeviceCalendarInstctionDao, RuagDeviceCalendarInstction> {
    @Autowired
    private RuagModelTemplateService ruagModelTemplateService;
    @Autowired
    private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;
    @Autowired
    private RuagModelCalendarService ruagModelCalendarService;
    @Autowired
    private RuagWorkModelDatailService ruagWorkModelDatailService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private RuagLinkageRuleService ruagLinkageRuleService;
    @Autowired
    private RuagCalendarWorkModelService ruagCalendarWorkModelService;
    @Autowired
    private Sender sender;
    @Autowired
    private MdmDeviceParameterService mdmDeviceParameterService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

    public RuagDeviceCalendarInstction get(String id) {
        return super.get(id);
    }

    public List<RuagDeviceCalendarInstction> findList(RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        return super.findList(ruagDeviceCalendarInstction);
    }

    public Page<RuagDeviceCalendarInstction> findPage(Page<RuagDeviceCalendarInstction> page, RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        return super.findPage(page, ruagDeviceCalendarInstction);
    }

    @Transactional(readOnly = false)
    public void save(RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        super.save(ruagDeviceCalendarInstction);
    }

    @Transactional(readOnly = false)
    public void delete(RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        super.delete(ruagDeviceCalendarInstction);
    }

    /**
     * @param @param id 运行策略id
     * @param @param dates 起止时间
     * @param @param workModels   运行策略设置集合
     * @return void    返回类型
     * @throws ServletException
     * @throws IOException
     * @throws SchedulerException
     * @throws ParseException
     * @throws
     * @Title: generateInsStart
     */
    @Transactional
    public String generateInsStart(String id, List<Date> dates, List<RuagWorkModelDatail> workModels) throws ParseException, SchedulerException, IOException, ServletException {
        RuagModelTemplate ruagModelTemplate = ruagModelTemplateService.get(id);
        String modelDegree = ruagModelTemplate.getModelDegree();
        String workModeName = ruagModelTemplate.getWorkModeName();
        String controlType = ruagModelTemplate.getControlType();
        String controlCode = ruagModelTemplate.getControlCode();
        //遍历dates,因为一个日期运行策略除了时间不同，其它都是相同的
        for (Date date : dates) {
            RuagModelCalendar ruagModelCalendar = new RuagModelCalendar();
            ruagModelCalendar.setModelId(id);
            ruagModelCalendar.setCurDate(date);
            RuagModelCalendar ruagModelCalendar2 = ruagModelCalendarService.findAllOfList(ruagModelCalendar).get(0);
            //遍历运行策略设置获得参数以及参数值
            for (RuagWorkModelDatail ruagWorkModelDatail : workModels) {
                RuagModelDeviceParamerSet paramerSet = new RuagModelDeviceParamerSet();
                paramerSet.setWorkModelId(ruagWorkModelDatail.getId());
                List<RuagModelDeviceParamerSet> ParamerSets = ruagModelDeviceParamerSetService.findAllOfList(paramerSet);
                for (RuagModelDeviceParamerSet paramerSetNew : ParamerSets) {
                    // 思考  开时 温度，湿度等其他参数会生成指令,关时这些参数也会生成指令 需不需要去重？
                    //首先 要 数据去重  模式启动时生成指令 与 定时任务生成指令 会有重复  故需要 去重
                    //定时任务去重   如何才视为重复    同一设备 同一天  时间点相同 参数相同 未发送
                    RuagDeviceCalendarInstction instction = new RuagDeviceCalendarInstction();
                    instction.setDciDate(date);
                    instction.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                    instction.setInstructionStatus(0L);
                    instction.setParameterId(paramerSetNew.getId());
                    instction.setParameterValue(paramerSetNew.getParameterValue());
                    instction.setTimePoint(paramerSetNew.getTimePoint());
                    instction.setModelId(id);
                    instction.setComCode(ruagModelCalendar.getComCode());
                    instction.setModuCode(ruagModelCalendar.getModuCode());
                    instction.setEffectiveStatus1("1");
                    List<RuagDeviceCalendarInstction> insList = null;
                    insList = findList(instction);
                    if (insList.size() != 0) {
                        continue;
                    }
                    //生成指令
                    RuagDeviceCalendarInstction instctionNew = new RuagDeviceCalendarInstction();
                    instctionNew.setCalendarId(ruagModelCalendar2.getId());
                    instctionNew.setDciDate(date);
                    instctionNew.setModelId(id);
                    instctionNew.setModelName(workModeName);
                    instctionNew.setControlCode(controlCode);
                    instctionNew.setControlType(controlType);
                    instctionNew.setModelDegree(modelDegree);
                    instctionNew.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                    instctionNew.setDeviceName(ruagWorkModelDatail.getDeviceName());
                    instctionNew.setProfCode(ruagWorkModelDatail.getProfCode());
                    instctionNew.setProfName(ruagWorkModelDatail.getProfName());
                    instctionNew.setSpaceCode(ruagWorkModelDatail.getSpaceCode());
                    instctionNew.setSpaceName(ruagWorkModelDatail.getSpaceName());
                    instctionNew.setParameterId(paramerSetNew.getParameterId());
                    instctionNew.setParameterName(paramerSetNew.getParameterName());
                    instctionNew.setParameterValue(paramerSetNew.getParameterValue());
                    instctionNew.setTimePoint(paramerSetNew.getTimePoint());
                    instctionNew.setInstructionStatus(0L);
                    instctionNew.setEffectiveStatus1("1");
                    instctionNew.setEnabledFlag("Y");
                    instctionNew.setComCode(ruagModelCalendar.getComCode());
                    instctionNew.setModuCode(ruagModelCalendar.getModuCode());
                    //同一天同一个设备的同一个参数，计划模式比较，只有等级高的计划模式的指令起作用（等级数字越小等级越高）
                    //通过判断优先级设置有效状态
                    RuagDeviceCalendarInstction instctionThree = new RuagDeviceCalendarInstction();
                    instctionThree.setDciDate(date);
                    instctionThree.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                    instctionThree.setInstructionStatus(0L);
                    instctionThree.setParameterId(paramerSetNew.getParameterId());
                    instctionThree.setControlCode(controlCode);
                    instctionThree.setEffectiveStatus1("1");
                    //instctionThree.setModelId(id);
                    instctionThree.setProfCode(ruagWorkModelDatail.getProfCode());
                    instctionThree.setSpaceCode(ruagWorkModelDatail.getSpaceCode());
                    instctionThree.setComCode(ruagWorkModelDatail.getComCode());
                    instctionThree.setModuCode(ruagWorkModelDatail.getModuCode());
                    Map<String, String> sqlMap = new HashMap<String, String>();
                    sqlMap.put("sqlMap", "a.model_id!=" + "'" + id + "'");
                    instctionThree.setSqlMap(sqlMap);
                    List<RuagDeviceCalendarInstction> list = dao.findList(instctionThree);
                    //如果查询到的条目A 不为空 1.A 等级高于待插入条目的等级，则将有效状态设置为隐藏
                    // 2.A 等级低于待插入条目的等级，则将 有效状态设置为显示，并将A有效状态更新为为隐藏
                    if (list.size() != 0) {
                        for (RuagDeviceCalendarInstction ruagDeviceCalendarInstction : list) {
                            if (Long.parseLong(ruagDeviceCalendarInstction.getModelDegree()) < Long.parseLong(instctionNew.getModelDegree())) {
                                instctionNew.setEffectiveStatus1("0");
                                instctionNew.setCompareObj(ruagDeviceCalendarInstction.getCalendarId());
                            } else {
                                ruagDeviceCalendarInstction.setEffectiveStatus1("0");
                                ruagDeviceCalendarInstction.setCompareObj(instctionNew.getCalendarId());
                                save(ruagDeviceCalendarInstction);
                            }
                        }
                    }
                    save(instctionNew);
                }
            }
        }
        //查看是否与联动规则设备产生冲突，并做处理
        ruagLinkageRuleService.checkConflict();
        this.scanIns();
        return "success";
    }

    /**
     * @param @param id   运行策略id
     * @return void    返回类型
     * @throws
     * @Title: deleteIns
     */
    @Transactional
    public void deleteIns(String id, String startTime, String endTime) {
        LoginUser user = UserServletContext.getUserInfo();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("modelId", id);
        params.put("start", startTime);
        params.put("end", endTime);
        params.put("comCode", user.getComCode());
        params.put("moduCode", user.getModuCode());
        dao.deleteByDate(params);
    }

    /**
     * @param @param id 运行策略日程id
     * @param @param date 要操作的具体日期
     * @param @param workModels    运行策略设置
     * @return void    返回类型
     * @throws ServletException
     * @throws IOException
     * @throws SchedulerException
     * @throws ParseException
     * @throws
     * @Title: generateInsByDay
     */
    @Transactional
    public void generateInsByDay(
            Date date,
            List<RuagWorkModelDatail> workModels,
            RuagModelCalendar ruagModelCalendar) throws ParseException, SchedulerException, IOException, ServletException {
        String modelDegree = Long.toString(ruagModelCalendar.getModelDegree());
        String workModeName = ruagModelCalendar.getModelName();
        String controlType = ruagModelCalendar.getControlType();
        String controlCode = ruagModelCalendar.getControlCode();
        //获取当前时间点（"HH:mm:ss"）
        String format = sdf2.format(new Date());
        Date now = sdf2.parse(format);
        //遍历运行策略设置获得参数以及参数值
        for (RuagWorkModelDatail ruagWorkModelDatail : workModels) {
            RuagModelDeviceParamerSet paramerSet = new RuagModelDeviceParamerSet();
            paramerSet.setWorkModelId(ruagWorkModelDatail.getId());
            List<RuagModelDeviceParamerSet> ParamerSets = ruagModelDeviceParamerSetService.findAllOfList(paramerSet);
            for (RuagModelDeviceParamerSet paramerSetNew : ParamerSets) {
                // 先计算出时间点 计划模式：设置为对应模式的时间点；临时模式：设置为 timeStart/timeEnd+/-offsetTime
                String timePoint = "";
                if ("date".equals(controlCode)) {
                    timePoint = paramerSetNew.getTimePoint();
                } else if ("time".equals(controlCode)) {
                    long offTime = Long.parseLong(paramerSetNew.getTimePoint().substring(1));
                    if (paramerSetNew.getTimePoint().contains("+")) {
                        //延时
                        timePoint = DateUtils.longToString(DateUtils.stringToLong(ruagModelCalendar.getTimeEnd(), "HH:mm") + offTime * 60 * 1000, "HH:mm");
                    } else {
                        //提前
                        timePoint = DateUtils.longToString(DateUtils.stringToLong(ruagModelCalendar.getTimeStart(), "HH:mm") - offTime * 60 * 1000, "HH:mm");
                    }
                }
                Date pointDate = sdf2.parse(timePoint);
                if ((date.compareTo(getNowDate()) == 0) && (pointDate.compareTo(now) < 0)) {
                    //如果带解析参数记录 是当天 并且其计算得到的时间点小于服务器当前时间 则该条记录不生产指令
                    continue;
                }
                // 思考  开时 温度，湿度等其他参数会生成指令,关时这些参数也会生成指令 需不需要去重？
                //首先 要 数据去重  模式启动时生成指令 与 定时任务生成指令 会有重复  故需要 去重
                //定时任务去重   如何才视为重复    同一设备 同一天  时间点相同 参数相同 未发送
                RuagDeviceCalendarInstction instction = new RuagDeviceCalendarInstction();
                instction.setDciDate(date);
                instction.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                instction.setInstructionStatus(0L);
                instction.setParameterId(paramerSetNew.getParameterId());
                instction.setParameterValue(paramerSetNew.getParameterValue());
                instction.setTimePoint(timePoint);
                instction.setEffectiveStatus1("1");
                instction.setModelId(ruagModelCalendar.getModelId());
                instction.setComCode(ruagModelCalendar.getComCode());
                instction.setModuCode(ruagModelCalendar.getModuCode());
                List<RuagDeviceCalendarInstction> insList = dao.findList(instction);
                if (insList.size() > 0) {
                    continue;
                }
                //生成指令
                RuagDeviceCalendarInstction instctionNew = new RuagDeviceCalendarInstction();
                instctionNew.setCalendarId(ruagModelCalendar.getId());
                instctionNew.setDciDate(date);
                instctionNew.setModelId(ruagModelCalendar.getModelId());
                instctionNew.setModelName(workModeName);
                instctionNew.setControlCode(controlCode);
                instctionNew.setControlType(controlType);
                instctionNew.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                instctionNew.setDeviceName(ruagWorkModelDatail.getDeviceName());
                instctionNew.setProfCode(ruagWorkModelDatail.getProfCode());
                instctionNew.setProfName(ruagWorkModelDatail.getProfName());
                instctionNew.setSpaceCode(ruagWorkModelDatail.getSpaceCode());
                instctionNew.setSpaceName(ruagWorkModelDatail.getSpaceName());
                instctionNew.setParameterId(paramerSetNew.getParameterId());
                instctionNew.setParameterName(paramerSetNew.getParameterName());
                instctionNew.setParameterValue(paramerSetNew.getParameterValue());
                instctionNew.setTimePoint(timePoint);
                instctionNew.setInstructionStatus(0L);
                instctionNew.setEffectiveStatus1("1");
                instctionNew.setEnabledFlag("Y");
                instctionNew.setComCode(ruagModelCalendar.getComCode());
                instctionNew.setModuCode(ruagModelCalendar.getModuCode());
                //运行策略等级设置     日期控制：设置为对应策略的等级； 时间控制：设置为0，最高等级
                if ("date".equals(controlCode)) {
                    instctionNew.setModelDegree(modelDegree);
                }
                if ("time".equals(controlCode)) {
                    instctionNew.setModelDegree("0");
                }
                //同一天同一个设备的同一个参数，计划模式比较，只有等级高的计划模式的指令起作用（等级数字越小等级越高）
                if ("date".equals(controlCode)) {
                    //通过判断优先级设置有效状态
                    RuagDeviceCalendarInstction instctionThree = new RuagDeviceCalendarInstction();
                    instctionThree.setDciDate(date);
                    instctionThree.setDeviceCode(ruagWorkModelDatail.getDeviceCode());
                    instctionThree.setInstructionStatus(0L);
                    instctionThree.setParameterId(paramerSetNew.getParameterId());
                    instctionThree.setControlCode(controlCode);
                    instctionThree.setEffectiveStatus1("1");
                    instctionThree.setProfCode(ruagWorkModelDatail.getProfCode());
                    instctionThree.setSpaceCode(ruagWorkModelDatail.getSpaceCode());
                    instctionThree.setComCode(ruagWorkModelDatail.getComCode());
                    instctionThree.setModuCode(ruagWorkModelDatail.getModuCode());
                    Map<String, String> sqlMap = new HashMap<String, String>();
                    sqlMap.put("sqlMap", "a.model_id!=" + "'" + ruagModelCalendar.getModelId() + "'");
                    instctionThree.setSqlMap(sqlMap);
                    List<RuagDeviceCalendarInstction> list = findList(instctionThree);
                    //如果查询到的条目A 不为空 1.A 等级高于待插入条目的等级，则将有效状态设置为隐藏
                    // 2.A 等级低于待插入条目的等级，则将 有效状态设置为显示，并将A有效状态更新为为隐藏
                    if (list.size() != 0) {
                        for (RuagDeviceCalendarInstction ruagDeviceCalendarInstction : list) {
                            if (Long.parseLong(ruagDeviceCalendarInstction.getModelDegree()) < Long.parseLong(instctionNew.getModelDegree())) {
                                instctionNew.setEffectiveStatus1("0");
                                instctionNew.setCompareObj(ruagDeviceCalendarInstction.getCalendarId());
                            } else {
                                ruagDeviceCalendarInstction.setEffectiveStatus1("0");
                                ruagDeviceCalendarInstction.setCompareObj(instctionNew.getCalendarId());
                                save(ruagDeviceCalendarInstction);
                            }
                        }
                    }
                }
                save(instctionNew);
            }
        }
        //查看是否与联动规则设备产生冲突，并做处理
        ruagLinkageRuleService.checkConflict();
        this.scanIns();
    }

    /**
     * @param @return
     * @param @throws ParseException    参数
     * @return Date    返回类型
     * @throws
     * @Title: getNowDate
     */
    @Transactional
    public Date getNowDate() throws ParseException {
        Date date = new Date();
        return sdf1.parse(sdf1.format(date));
    }

    /**
     * @return void    返回类型
     * @throws ServletException
     * @throws IOException
     * @throws SchedulerException
     * @throws ParseException
     * @throws
     * @Title: generateInstructionsScheduleTask
     */
    @Transactional
    public void generateInstructionsScheduleTask() throws ParseException, SchedulerException, IOException, ServletException {
        LoginUser user = UserServletContext.getUserInfo();
        //获取今明两天的日期
        String today = sdf1.format(getNowDate());
        String tomorrow = addDay(today, 1);
        List<RuagModelCalendar> list = ruagModelCalendarService.findTodayAndTomorrow(today, tomorrow);
        if (list.size() > 0) {
            for (RuagModelCalendar modelCalendar : list) {
                List<RuagWorkModelDatail> workModels = new ArrayList<RuagWorkModelDatail>();
                if (modelCalendar.getChangeFlag().equals("N")) {
                    //没有被修改过，直接通过运行策略id去运行策略配置表查找运行策略配置
                    workModels = ruagWorkModelDatailService.getAllList(modelCalendar.getModelId());
                } else {
                    //已经被修改过，通过运行策略日程id去运行策略日程与运行策略配置关系表查找运行策略配置
                    RuagCalendarWorkModel ruagCalendarWorkModel = new RuagCalendarWorkModel();
                    ruagCalendarWorkModel.setModelCalendarId(modelCalendar.getId());
                    List<RuagCalendarWorkModel> findList = ruagCalendarWorkModelService.findAllOfList(ruagCalendarWorkModel);
                    for (RuagCalendarWorkModel ruagCalendarWorkModel2 : findList) {
                        workModels.add(ruagWorkModelDatailService.get(ruagCalendarWorkModel2.getWorkModelId()));
                    }
                }
                generateInsByDay(modelCalendar.getCurDate(), workModels, modelCalendar);
                //若是开启计划模式，还要判断其指令是否影响了正在开启的临时模式
                RuagModelCalendar ruagModelCalendarQ = new RuagModelCalendar();
                ruagModelCalendarQ.setModelStatus("1");
                ruagModelCalendarQ.setCurDate(sdf1.parse(today));
                ruagModelCalendarQ.setControlCode("time");
                ruagModelCalendarQ.setComCode(modelCalendar.getComCode());
                ruagModelCalendarQ.setModuCode(modelCalendar.getModuCode());
                List<RuagModelCalendar> findList = ruagModelCalendarService.findList(ruagModelCalendarQ);
                if (findList.size() > 0) {
                    for (RuagModelCalendar ruagModelCalendar2 : findList) {
                        RuagWorkModelDatail ruagWorkModelDatailQ = new RuagWorkModelDatail();
                        ruagWorkModelDatailQ.setModelTemplateId(ruagModelCalendar2.getModelId());
                        List<RuagWorkModelDatail> workModelsQ = ruagWorkModelDatailService.findList(ruagWorkModelDatailQ);
                        ruagModelCalendarService.selectEffective(ruagModelCalendar2, workModelsQ, sdf1.parse(today));
                    }
                }
            }
        }
    }

    /**
     * @param @param  date  某天
     * @param @param  i 第几天
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: addDay
     */
    @Transactional
    public String addDay(String date, int i) {
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)) - 1,
                Integer.parseInt(date.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return sdf1.format(gCal.getTime());
    }

    /**
     * @param @return 参数
     * @return List<RuagDeviceCalendarInstction>    返回类型
     * @throws
     * @Title: getInsLatestTimePoint
     */
    @Transactional
    public List<RuagDeviceCalendarInstction> getInsLatestTimePoint(String timePoint) {
        String today = sdf1.format(new Date());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startTime", timePoint);
        params.put("today", today);
        return dao.findInsLatest(params);
    }

    /**
     * @param @return
     * @param @throws ParseException    参数
     * @return Map<String, String>    返回类型
     * @throws SchedulerException
     * @throws
     * @Title: sendInstructions
     */
    @Transactional
    public Map<String, String> sendInstructions(String timePoint) throws ParseException, SchedulerException {
        Map<String, String> result = new HashMap<String, String>();
        List<RuagDeviceCalendarInstction> ins = getInsLatestTimePoint(timePoint);
        ins.forEach(ruagDeviceCalendarInstction -> {
            //todo 发送指令到物联网平台.
            sender.sendInstructionMessage(indetctionToMessage(ruagDeviceCalendarInstction));

            ruagDeviceCalendarInstction.setInstructionStatus(1L);
            save(ruagDeviceCalendarInstction);
        });
        scanIns();
        result.put("msg", "success");
        return result;
    }

    /**
     * @return void    返回类型
     * @throws SchedulerException
     * @throws ParseException
     * @throws
     * @Title: scanIns
     */
    @Transactional
    public void scanIns() throws SchedulerException {
        //获取指令表中今天最近的时间点
        String timePoint = dao.findRecentTime();
        if (!(StringUtils.isBlank(timePoint))) {
            LoggerFactory.getLogger("ruag").info("------------------触发定时任务-------------------------");
            //将小时和分钟拆解出来
            String hour = timePoint.split(":")[0];
            String minite = timePoint.split(":")[1];
            //构造cron表达式
            String cron = "0 " + minite + " " + hour + " * * ?";
            SendInsTask.setTimePoint(timePoint);
            // 验证cron表达式
            boolean f = CronExpression.isValidExpression(cron);
            if (f) {
                scheduleJobService.modifyTrigger("sendIns",
                        "ruag", cron);
            }
        } else {
            //已没有可发送的指令集
            LoggerFactory.getLogger("ruag").info("------------------暂停定时任务-------------------------");
            scheduleJobService.stopJob("sendIns", "ruag");
        }
    }

    public List<RuagDeviceCalendarInstction> findAllOfList(RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        return dao.findAllOfList(ruagDeviceCalendarInstction);
    }

    private String indetctionToMessage(RuagDeviceCalendarInstction ruagDeviceCalendarInstction) {
        DeviceParamSender sender = new DeviceParamSender();
        sender.setValue(ruagDeviceCalendarInstction.getParameterValue());
        sender.setPath(ruagDeviceCalendarInstction.getDeviceCode() + "." + ruagDeviceCalendarInstction.getParameterId());
        return sender.toString();
    }
}
