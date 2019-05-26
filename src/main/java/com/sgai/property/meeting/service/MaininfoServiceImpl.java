package com.sgai.property.meeting.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.common.util.TimeUtil;
import com.sgai.property.common.util.ToonOwner;
import com.sgai.property.commonService.dao.IDictGeneralDao;
import com.sgai.property.commonService.entity.DictGeneral;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.service.EmpInfoServiceImpl;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlNoticeService;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.*;
import com.sgai.property.meeting.entity.*;
import com.sgai.property.meeting.vo.*;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;


/**
 * 会议应用业务逻辑实现类
 * ClassName: MaininfoServiceImpl
 *
 * @author zhangxiang3
 * @Description :
 * @date: 2017年11月9日 上午10:47:49
 */
@Service
public class MaininfoServiceImpl extends MoreDataSourceCrudServiceImpl<IMaininfoDao, Maininfo> {


    private static final Logger logger = LogManager.getLogger(MaininfoServiceImpl.class);
    @Autowired
    private IMaininfoDao maininfoDao;

    @Autowired
    private IPicDao picDao;

    @Autowired
    private PicServiceImpl picServiceImpl;

    @Autowired
    private IInviterDao inviterDao;

    @Autowired
    private InviterServiceImpl inviterServiceImpl;

    @Autowired
    private IMaininfoInviterDao maininfoInviterDao;

    @Autowired
    private MaininfoInviterServiceImpl maininfoInviterServiceImpl;

    @Autowired
    private IInviteDeptDao inviteDeptDao;

    @Autowired
    private InviterDeptServiceImpl inviterDeptServiceImpl;

    @Autowired
    private ISummaryDao summaryDao;

    @Autowired
    private ISummaryPicDao summaryPicDao;

    @Autowired
    private SummaryPicServiceImpl summaryPicServiceImpl;

    @Autowired
    private IRoomPositionDao roomPositionDao;

    @Autowired
    private IRoomResourceDao roomResourceDao;

    @Autowired
    private IDictGeneralDao dictGeneralDao;

    @Autowired
    private IMaterielDao materielDao;

    @Autowired
    private MaterielServiceImpl materielServiceImpl;

    @Autowired
    private MeetingsInfoAppAsyncTask meetingsInfoAppAsyncTask;

    @Autowired
    private EmpInfoServiceImpl empInfoService;

    @Autowired
    private SummaryServiceImpl summaryServiceImpl;

    @Autowired
    private RoomResourceServiceImpl roomResourceService;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;
    @Autowired
    private CtlNoticeService ctlNoticeService;
    @Autowired
    private CtlUserService ctlUserService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;


    @Value("${mt.defaultRoomUrl}")
    private String defaultRoomUrl;

    @Value("${mt.defaultEiPicUrl}")
    private String defaultEiPicUrl;

    @Value("${sendMessage.appId}")
    private String appId;

    @Value("${sendMessage.appKey}")
    private String appKey;

    @Value("${mt.appUrl}")
    private String appUrl;

    @Transactional
    public Boolean send(String miId, List<String> eiIds) {
        long currtTime = System.currentTimeMillis();

        List<Inviter> inviters2 = inviterDao.getByMiId(miId);
        Set<Inviter> set = new HashSet<>();
        Set<Inviter> senders = new HashSet<Inviter>();
        String deptId = "";
        for (String eiId : eiIds) {
            CtlEmp empInfoVo = baseSgaiOrgTreeService.getSgaiEmpById(eiId);
            deptId = empInfoVo.getDeptCode();
            Inviter inviter = new Inviter();
            inviter.setInviterEiId(eiId);
            inviter.setInviterEiName(empInfoVo.getLastname());
            inviter.setNodeTree(empInfoVo.getDeptCode());
            inviter.setMiId(miId);
            inviter.setUpdateTime(currtTime);
            inviter.setCreateTime(currtTime);
            set.add(inviter);
        }
        for (Inviter inviter : inviters2) {
            CtlEmp empInfoVo = baseSgaiOrgTreeService.getSgaiEmpById(inviter.getInviterEiId());
            if (deptId.equals(empInfoVo.getDeptCode())) {
                set.add(inviter);
            }
        }

        //派遣人员发送邮件通知
//		Maininfo maininfo = maininfoDao.getById(miId);
//		meetingsInfoAppAsyncTask.sendMessageEmail(senders, maininfo, toonCode);
        Page<CtlEmp> page = baseSgaiOrgTreeService.getSgaiEmp(1, Integer.MAX_VALUE, deptId + "", "", "");
        List<CtlEmp> empDtos = page.getList();
        for (CtlEmp sgaiEmpDto : empDtos) {
            Inviter inviter = new Inviter();
            inviter.setInviterEiId(sgaiEmpDto.getEmpCode());
            senders.add(inviter);
        }

        //保存最新参会人信息
        boolean flag;
        try {
            //更新参会人信息表
            if (miId != null && !"".equals(miId)) {
                //派遣完成之后，对比当前所有参会人，删除所选部门
                flag = isContainEachother(set, senders);
                if (flag) {
                    InviteDept inviteDept = new InviteDept();
                    inviteDept.setMiId(miId);
                    inviteDept.setInviterDeptId(deptId);
                    inviteDeptDao.delete(inviteDept);
                }

                inviterDao.deleteByMiId(miId);
                maininfoInviterDao.deleteByMiId(miId);
            }

            //更新所有参会人信息
            set.addAll(inviters2);

            for (Inviter inviter : set) {
                inviter.setId(null);
                inviterServiceImpl.saveEntity(inviter);
                MaininfoInviter maininfoInviter = BeanMapper.map(inviter, MaininfoInviter.class);
                maininfoInviter.setId(null);
                maininfoInviter.setComCode(UserServletContext.getUserInfo().getComCode());
                maininfoInviter.setModuCode(UserServletContext.getUserInfo().getModuCode());
                maininfoInviterServiceImpl.saveEntity(maininfoInviter);
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnType.Error, "派遣失败！" + e.getMessage());

        }
        return flag;
    }

    private boolean isContainEachother(Collection<Inviter> ca, Collection<Inviter> cb) {
        if (ca != null && cb != null) {
            if (ca.size() == cb.size()) {
                Set<Inviter> setA = (Set<Inviter>) ca, setB = (Set<Inviter>) cb; // 去重复元素
                setA.size();
                setB.size();
                return setA.containsAll(setB);
                // return setB.containsAll(setA); //setA与setB是包含关系
                // 证明两个set是相等的
            } else {
                return false;
            }
        } else if (ca == null && cb == null) {
            return true;
        }
        return false;
    }

    public List<DictGeneral> getRemindMins() {
        List<DictGeneral> dictGeneralVos = new ArrayList<>();
        List<DictGeneral> dictGenerals = dictGeneralDao.getByDgCode(Constants.BIZ_MT_REMIND_MIN);
        dictGeneralVos = BeanMapper.mapList(dictGenerals, DictGeneral.class);
        return dictGeneralVos;
    }

    public List<String> getMeetingDate(String meetingId) {
        Maininfo maininfo = maininfoDao.getById(meetingId);
        if (null == maininfo) {

        }
        return null;
    }

    public Page<RoomResourceVo> selectMeetingRoomPC(Long currtDate, String timeSeg, int pageNum, int pageSize) {
        List<RoomResourceVo> roomResourceVos = new ArrayList<>();
        //判断时间段是多个还是一个，一个按照单个数字处理
        if (currtDate != null && currtDate > 0 && !"".equals(timeSeg)) {
            if (StringUtils.isNotEmpty(timeSeg)) {
                String[] timeSegArr = timeSeg.split(",");
                if (timeSegArr != null && timeSegArr.length == 2) {
                    if (timeSegArr[0].equals(timeSegArr[1])) {
                        timeSeg = timeSegArr[0];
                    }
                }
            }
        }
        Page<Maininfo> page = new Page<>(pageNum, pageSize);
        Maininfo mf = new Maininfo();
        mf.setMiMtDate(currtDate);
        mf.setMiMtTimeSeg(timeSeg);
        mf.setPage(page);
        mf.setComCode(UserServletContext.getUserInfo().getComCode());
        List<RoomResource> roomResources = maininfoDao.selectMeetingRoomPC(mf);
        if (null != roomResources && !roomResources.isEmpty()) {
            roomResources.forEach(roomResource -> {
                RoomResourceVo vo = new RoomResourceVo();
                BeanUtils.copyProperties(roomResource, vo);
                vo.setDeviceDetailVoList(roomResourceService.getRoomDeviceDetailList(roomResource.getId()));
                vo.setRrRoomPicMain(vo.getRrRoomPicMain());
                roomResourceVos.add(vo);
            });
        }
        Long count = maininfoDao.getCount2(mf);
        Page<RoomResourceVo> page2 = new Page<>(pageNum, pageSize);
        page2.setList(roomResourceVos);
        page2.setCount(count);
        return page2;
    }

    @Transactional
    public boolean saveMeetingPC(MaininfoPCVo maininfoPCVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //保存会议信息
        //请求数据放缓存
        String key = "bjfzx:meetings:saveMeetingPC:" + maininfoPCVo.getMiMtDate() + maininfoPCVo.getMiMtTimeSeg() + maininfoPCVo.getRrId();

        boolean flag = false;
        long currtTime = System.currentTimeMillis();
        Maininfo maininfo = new Maininfo();
        BeanUtils.copyProperties(maininfoPCVo, maininfo);
        maininfo.setUpdateTime(currtTime);
        int rs = 0;
        maininfo.setCreateEiId(UserServletContext.getUserInfo().getUserId());
        maininfo.setCreateEiName(UserServletContext.getUserInfo().getUserName());
        maininfo.setComId(UserServletContext.getUserInfo().getComCode());
        try {
//       boolean isExistKey =redisClient.setNx(key);
//       if(!isExistKey){
//          throw new BusinessException(ReturnType.Error,"选择的会议室时间段已经被占用");
//       }
            boolean isUpdate = false;
            Maininfo maininfoOld = new Maininfo();
            List<Inviter> invitersOld = new ArrayList<>();
            //验证提交数据是否合法，会议室有效性，时间段有效性
            checkMeetingInfo(maininfo);

            //验证提交时间是否已经到会议开始时间
            Long starTime = getMeetingStarttime(maininfo.getMiMtDate(), maininfo.getMiMtTimeSeg());

            if (currtTime > starTime) {
                maininfo.setMiStatus(Constants.MT_STATUS_1);//执行中
            } else {
                maininfo.setMiStatus(Constants.MT_STATUS_2);
            }
            maininfo.setMiStartTime(DateUtils.parseDate(DateFormatUtils.format(starTime, Constants.DATEFORMAT_YYYYMMDDHHMMSS), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            //编辑

            if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
                RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
                if (roomResource.getIsDelete().equals(Constants.TRUE)) {
                    throw new BusinessException(ReturnType.ParamIllegal, "该会议室已删除");
                }
                isUpdate = true;
                //原始会议信息
                maininfoOld = maininfoDao.getById(maininfo.getId());
                if (currtTime > starTime) {
                    throw new BusinessException(ReturnType.Error, "会议已经开始不能修改");
                }

                //判断是否有新增人员，发送邀请通知
                invitersOld = inviterDao.getByMiId(maininfo.getId());
                rs = maininfoDao.updateById(maininfo);
            } else {
                maininfo.setComCode(UserServletContext.getUserInfo().getComCode());
                maininfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                maininfo.setCreateTime(currtTime);
                flag = saveEntity(maininfo);
                Maininfo maininfoNew = new Maininfo();
				/*List<Maininfo> maininfoId =maininfoDao.findList(maininfo);
				for(Maininfo maininfoEndTime:maininfoId){*/
                //插入会议结束时间
                Date date = new Date();
                date = getMeetingEndtime(maininfo.getId());
                maininfoNew.setMiEndTime(DateUtils.parseDate(DateFormatUtils.format(date, Constants.DATEFORMAT_YYYYMMDDHHMMSS), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
                maininfoNew.setId(maininfo.getId());
                maininfoDao.updateById(maininfoNew);


                String noticeTime = com.sgai.common.utils.DateUtils.formatDate(new Date(), Constants.DATEFORMAT_YYYYMMDDHHMMSS);//发送会议通知的时间
                String noticeInfo = maininfoPCVo.getMiMtSubject();//会议的主题
                String userCode = UserServletContext.getUserInfo().getUserId();//用户登录的帐号 当前登录系统用户
                //开始调用第三方接口 参数分别是 userCode 用户的id （登录帐号名）， noticeTime 通知时间 （会议的预定时间） noticeInfo 会议的主题 token 验证用户身份token
                ctlNoticeService.saveNotice(userCode, DateUtils.parseDate(noticeTime, "yyyy-MM-dd HH:mm:ss"), noticeInfo); // 返回值 map 成功和失败的状态信息
                if (flag) {
                    rs = 1;
                }
            }

            if (rs > 0) {
                //处理参会人 参会部门信息
                List<Inviter> inviters = processInviterAndDeptPC(maininfoPCVo, maininfo);
                //循环参会人信息 拿到参会人的id 再根据id调用获取参会人登录帐号的接口 因为发送通知到参会人必须传入登陆人帐号userCode

                //
                if (isUpdate) {
                    List<Inviter> invitersAdd = new ArrayList<Inviter>();
                    for (Inviter inviter : inviters) {
                        if (!invitersOld.contains(inviter)) {
                            invitersAdd.add(inviter);
                        }
                    }
                    inviters = Lists.newArrayList();
                    if (invitersAdd != null && invitersAdd.size() > 0) {
                        inviters.addAll(invitersAdd);
                    }
                }

                for (Inviter inv : inviters) {
                    String userCode = null;//用户登陆账号
                    String userName = inv.getInviterEiName();//用户名称
                    String empCode = inv.getInviterEiId();//用户代码
                    //开始调用第三方接口 必传参数: userName userCode empCode token request response    成功返回对应用户的信息
                    CtlUser ctlUser = new CtlUser();
                    Page<CtlUser> ctlUserf = ctlUserService.getListIUser(ctlUser, userCode, userName, empCode, new Page<CtlUser>(request, response));
                    if (ctlUserf != null) {
                        //取出分页对象中的 实例集合
                        List<CtlUser> ctlUserfList = ctlUserf.getList();
                        //遍历集合 取出用户的登录帐号字段  userCode

                        for (CtlUser ctlUsers : ctlUserfList) {
                            //只取有系统帐号的用户或员工 并和其他参数一起传入第三方接口 实现通知参会人到平台功能
                            if (ctlUsers.getUserCode() != null && !ctlUsers.getUserCode().equals(UserServletContext.getUserInfo().getUserId())) {//判断如果某一个参会人有登陆账号就发送会议通知
                                userCode = ctlUsers.getUserCode();//userCode登录帐号  关键
                                //发送通知的时间
                                String noticeTime = com.sgai.common.utils.DateUtils.formatDate(new Date(), Constants.DATEFORMAT_YYYYMMDDHHMMSS);
                                //会议主题 通知内容
                                String noticeInfo = maininfoPCVo.getMiMtSubject();
                                //重定向  首自信必传参数 默认为空  相当于return:"redirect：/path/list？param1=value1&param2=value2 "
                                RedirectAttributes redirectAttributes = null;
                                //发送通知接口 循环遍历每一个参会人 一个一个发送
                                ctlNoticeService.saveNotice(userCode, DateUtils.parseDate(noticeTime, "yyyy-MM-dd HH:mm:ss"), noticeInfo); // 返回值 map 成功和失败的状态信息

                            }

                        }
                    }
                }

                //处理物料信息
                processMateriels(maininfoPCVo, maininfo);

                //发送发起会议通知
                if (!isUpdate) {
//             meetingsInfoAppAsyncTask.sendMessageEmail(inviters, maininfo,toonCode);
                } else {
                    //发送更新通知
//             meetingsInfoAppAsyncTask.editSendMessage(maininfo, maininfoOld, toonCode, inviters);
                    List<Inviter> invitersAdd = new ArrayList<Inviter>();
                    for (Inviter inviter : inviters) {
                        if (!invitersOld.contains(inviter)) {
                            invitersAdd.add(inviter);
                        }
                    }
                    if (invitersAdd != null && invitersAdd.size() > 0) {
//                meetingsInfoAppAsyncTask.sendMessageEmail(invitersAdd, maininfo,toonCode);
                    }

                }
            } else {
                throw new BusinessException(ReturnType.Error, "保存会议信息失败！");
            }
        } catch (DataAccessException e) {
            logger.error("", e);
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                throw new BusinessException(ReturnType.Error, "选择的会议室时间段已经被占用");
            }
        } catch (Exception e) {
            e.printStackTrace();
//    }finally {
//       redisClient.remove(key);
        }
        return flag;
    }

    /**
     * @throws Exception
     */
    public List<MeetingsListDto> getAllMeetingsList(Long currtDate) throws Exception {
        List<MeetingsListDto> meetingsListDtos = maininfoDao.getAllMeetingsList(currtDate, UserServletContext.getUserInfo().getUserId());
        //设置当前登录人的会议是否逾期
        for (MeetingsListDto meetingsListDto : meetingsListDtos) {
            Inviter inviter = new Inviter();
            inviter.setInviterEiId(UserServletContext.getUserInfo().getUserId());
            inviter.setMiId(meetingsListDto.getMiId());
            inviter = inviterDao.get(inviter);
            if (meetingsListDto.getMiStatus().equals(Constants.MT_INVITE_3)
                    && inviter != null && inviter.getIsInvite().equals(Constants.MT_INVITE_0)) {
                meetingsListDto.setMiStatus(Constants.MT_STATUS_4);//已逾期
                meetingsListDto.setMiStatusCn("已逾期");
            }

            //判断当前会议是否是创建人
            if (meetingsListDto.getCreateEiId().equals(UserServletContext.getUserInfo().getUserId())) {
                meetingsListDto.setIsCreateUser(Constants.TRUE);
            } else {
                meetingsListDto.setIsCreateUser(Constants.FALSE);
            }

        }
        Set<MeetingsListDto> set = new HashSet<>();
        //增加管理员看到的会议列表
        RoomResource roomResource = new RoomResource();
        roomResource.setComId(UserServletContext.getUserInfo().getComCode());
        roomResource.setRrAdminId(UserServletContext.getUserInfo().getUserId());
        List<RoomResource> roomResources = roomResourceDao.findList(roomResource);
        for (RoomResource resource : roomResources) {
            List<MeetingsListDto> meetingsListDtos2 = new ArrayList<>();
            meetingsListDtos2 = maininfoDao.getAllMeetingsListByRrId(currtDate, resource.getId());
            meetingsListDtos.addAll(meetingsListDtos2);
        }
        set.addAll(meetingsListDtos);
        meetingsListDtos = BeanMapper.mapList(set, MeetingsListDto.class);
        return meetingsListDtos;
    }

    /**
     *
     */
    public List<CurrtDaysStatusDto> getCurrtWeekStatus(List<Long> currtDays) throws Exception {
        //返回当前周状态信息
        List<CurrtDaysStatusDto> currtDaysStatusDtos = new ArrayList<CurrtDaysStatusDto>();
        for (Long day : currtDays) {
            CurrtDaysStatusDto currtDaysStatusDto = new CurrtDaysStatusDto();
            currtDaysStatusDto.setCurrtDate(day.toString());
            currtDaysStatusDto.setStatus(Constants.FALSE.toString());
            currtDaysStatusDtos.add(currtDaysStatusDto);
        }

        if (currtDays == null || currtDays.size() == 0) {
            throw new BusinessException(ReturnType.Error, "当前日期时间为空！");
        }
        //整理返回值当前周日期及状态
        List<CurrtDaysStatusDto> daysStatusDtos = maininfoDao.getCurrtWeekStatus(currtDays, UserServletContext.getUserInfo().getUserId());
        for (CurrtDaysStatusDto currtDaysStatusDto : currtDaysStatusDtos) {
            for (CurrtDaysStatusDto daysStatusDto : daysStatusDtos) {
                if (currtDaysStatusDto.getCurrtDate().equals(daysStatusDto.getCurrtDate())) {
                    if (Constants.TRUE.toString().equals(currtDaysStatusDto.getStatus())) {
                        break;
                    }
                    currtDaysStatusDto.setStatus(daysStatusDto.getStatus());
                }
            }
        }
        return currtDaysStatusDtos;
    }

    /**
     * MethodName : checkMeetingInfo
     *
     * @param accessToken
     * @param maininfo
     * @throws Exception
     * @Description : 验证提交数据是否合法，会议室有效性，时间段有效性
     * @author zhangxiang
     * @date: 2017年11月24日 下午1:46:08
     */
    private void checkMeetingInfo(Maininfo maininfo) throws Exception {
        if (maininfo.getMiMtDate() == null || "".equals(maininfo.getMiMtDate())
                || "null".equalsIgnoreCase(maininfo.getMiMtDate().toString())) {
            throw new BusinessException(ReturnType.Error, "会议时间为空");
        }
        //验证会议时间是否是过去时间
//		Long currtDay = DateUtils.parseDate(DateFormatUtils.format(new Date(),  Constants.DATEFORMAT_YYYYMMDD),Constants.DATEFORMAT_YYYYMMDD).getTime();
        Long currtTime = System.currentTimeMillis();
        Long startTime = getMeetingStarttime(maininfo.getMiMtDate(), maininfo.getMiMtTimeSeg());

        if (startTime < currtTime) {
            throw new BusinessException(ReturnType.Error, "会议时间无效");
        }

        if (maininfo.getMiMtTimeSeg() == null || "".equals(maininfo.getMiMtTimeSeg())
                || "null".equalsIgnoreCase(maininfo.getMiMtTimeSeg())) {
            throw new BusinessException(ReturnType.Error, "会议时间段为空");
        }

        if (maininfo.getRrId() == null || "".equals(maininfo.getRrId())
                || "null".equalsIgnoreCase(maininfo.getRrId().toString())) {
            throw new BusinessException(ReturnType.Error, "会议室为空");
        }

        //会议室删除时，给出提示
        RoomResource roomResource = new RoomResource();
        roomResource = roomResourceDao.getById(maininfo.getRrId());
        if (roomResource == null || "".equals(roomResource.getId())) {
            throw new BusinessException(ReturnType.Error, "该会议室已删除！");
        }
        boolean isCommit = confirmTimeSeg(maininfo);
        if (!isCommit) {
            throw new BusinessException(ReturnType.Error, "选择的会议室时间段已经被占用");
        }

    }

    @Transactional
    public boolean saveMeeting(MaininfoVo maininfoVo) throws Exception {
        //保存会议信息
        //请求数据放缓存
        String key = "bjfzx:meetings:saveMeetings:" + maininfoVo.getMiMtDate() + maininfoVo.getMiMtTimeSeg() + maininfoVo.getRrId();
//		RedisLock redisLock=new RedisLock(redisTemplate,key);
//		redisLock.lock();

        ;

        boolean flag = false;
        long currtTime = System.currentTimeMillis();
        Maininfo maininfo = new Maininfo();
        BeanUtils.copyProperties(maininfoVo, maininfo);
        maininfo.setUpdateTime(currtTime);
        int rs = 0;
        maininfo.setCreateEiId(UserServletContext.getUserInfo().getUserId());
        maininfo.setCreateEiName(UserServletContext.getUserInfo().getUserName());
        maininfo.setComId(UserServletContext.getUserInfo().getComCode());
        try {
//			boolean isExistKey =redisClient.exists(key);
//			if(!isExistKey){
//				throw new BusinessException(ReturnType.Error,"选择的会议室时间段已经被占用");
//			}
            boolean isUpdate = false;
            Maininfo maininfoOld = new Maininfo();
            List<Inviter> invitersOld = new ArrayList<>();
//			if (redisLock.lock()) {
            //验证提交数据是否合法，会议室有效性，时间段有效性
            checkMeetingInfo(maininfo);

            //验证提交时间是否已经到会议开始时间
            Long starTime = getMeetingStarttime(maininfo.getMiMtDate(), maininfo.getMiMtTimeSeg());

            if (currtTime > starTime) {
                maininfo.setMiStatus(Constants.MT_STATUS_2);
            }
            maininfo.setMiStartTime(DateUtils.parseDate(DateFormatUtils.format(starTime, Constants.DATEFORMAT_YYYYMMDDHHMMSS), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
                isUpdate = true;
                //原始会议信息
                maininfoOld = maininfoDao.getById(maininfo.getId());
                if (currtTime > starTime) {
                    throw new BusinessException(ReturnType.Error, "会议已经开始不能修改");
                }

                //判断是否有新增人员，发送邀请通知
                invitersOld = inviterDao.getByMiId(maininfo.getId());
                rs = maininfoDao.updateById(maininfo);
            } else {
                maininfo.setComCode(UserServletContext.getUserInfo().getComCode());
                maininfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                maininfo.setCreateTime(currtTime);
                rs = saveEntity(maininfo) ? 1 : 0;
//						rs = maininfoDao.insert(maininfo);
            }

            if (rs > 0) {
                //处理物料信息
                saveMateriels(maininfoVo, maininfo.getId());
                //保存图片信息
                processMeetingPic(currtTime, maininfo, maininfoVo);
                MaininfoPCVo maininfoPCVo = BeanMapper.map(maininfoVo, MaininfoPCVo.class);
                //处理参会人 参会部门信息
                List<Inviter> inviters = processInviterAndDeptPC(maininfoPCVo, maininfo);

                //发送发起会议通知
                if (!isUpdate) {
//						meetingsInfoAppAsyncTask.sendMessageEmail(inviters, maininfo,toonCode);
                    //处理循环例会信息
//						meetingsInfoAppAsyncTask.loopWeekMeeting(flag, maininfoVo, accessToken);
                } else {
                    //发送更新通知
//						meetingsInfoAppAsyncTask.editSendMessage(maininfo, maininfoOld, toonCode, inviters);
                    List<Inviter> invitersAdd = new ArrayList<Inviter>();
                    for (Inviter inviter : inviters) {
                        if (!invitersOld.contains(inviter)) {
                            invitersAdd.add(inviter);
                        }
                    }
                    if (invitersAdd != null && invitersAdd.size() > 0) {
//							meetingsInfoAppAsyncTask.sendMessageEmail(invitersAdd, maininfo,toonCode);
                    }

                }
            } else {
                throw new BusinessException(ReturnType.Error, "保存会议信息失败！");
            }
//			}
        } catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof MySQLIntegrityConstraintViolationException) {
                throw new BusinessException(ReturnType.Error, "选择的会议室时间段已经被占用");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BusinessException(ReturnType.Error, e.getMessage());
        } finally {
//			redisLock.unlock();
//			redisClient.remove(key);
        }
        return flag;
    }

    /**
     * MethodName : processMeetingPic
     *
     * @param currtTime
     * @param maininfo
     * @param maininfoVo
     * @return
     * @Description : 处理会议图片信息
     * @author zhangxiang
     * @date: 2017年11月14日 下午1:39:54
     */
    @Transactional(rollbackFor = Exception.class)
    public void processMeetingPic(long currtTime, Maininfo maininfo, MaininfoVo maininfoVo) {
        List<Pic> pics = new ArrayList<Pic>();
        List<PicVo> picVos = maininfoVo.getPicVos();
        int rs = 0;
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            rs = picDao.deleteByMiId(maininfo.getId());
        }
        if (picVos != null && picVos.size() > 0) {
            pics = BeanMapper.mapList(picVos, Pic.class);
            for (Pic pic : pics) {
                pic.setMiId(maininfo.getId());
                pic.setCreateTime(currtTime);
                pic.setUpdateTime(currtTime);
                pic.setComCode(maininfo.getComCode());
                pic.setModuCode(maininfo.getModuCode());
                rs = picServiceImpl.saveEntity(pic) ? 1 : 0;
//				rs = picDao.insert(pic);
                if (rs < 1) {
                    throw new BusinessException(ReturnType.Error, "保存会议图片信息失败！");
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveMateriels(MaininfoVo maininfoVo, String mtId) {
        long currtTime = System.currentTimeMillis();
        if (mtId != null && StringUtils.isNotEmpty(mtId)) {
            materielDao.deleteByMiId(mtId);
        }
        List<Materiel> materiels;
        List<MaterielVo> materielVos = maininfoVo.getMaterielVos();
        if (materielVos != null && materielVos.size() > 0) {
            materiels = BeanMapper.mapList(materielVos, Materiel.class);
            for (Materiel materiel : materiels) {
                materiel.setMtId(mtId);
                materiel.setCreateTime(currtTime);
                materiel.setUpdateTime(currtTime);
                materielServiceImpl.saveEntity(materiel);
//				materielDao.insert(materiel);
            }
        }
    }

    @Transactional
    public void processMateriels(MaininfoPCVo maininfoPCVo, Maininfo maininfo) {
        long currtTime = System.currentTimeMillis();
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            materielDao.deleteByMiId(maininfo.getId());
        }
        List<Materiel> materiels = new ArrayList<>();
        List<MaterielVo> materielVos = maininfoPCVo.getMaterielVos();
        if (materielVos != null && materielVos.size() > 0) {
            materiels = BeanMapper.mapList(materielVos, Materiel.class);
            for (Materiel materiel : materiels) {
                Materiel m = new Materiel();
                m.setMaId(materiel.getMaId());
                m.setMaName(materiel.getMaName());
                m.setMaTypeName(materiel.getMaTypeName());
                m.setDelFlag(materiel.getDelFlag());
                m.setMtId(maininfo.getId());
                m.setCreateTime(currtTime);
                m.setUpdateTime(currtTime);
                m.setComCode(maininfo.getComCode());
                m.setModuCode(maininfo.getModuCode());
                materielServiceImpl.saveEntity(m);
            }
        }
    }

    /**
     * MethodName : checkeInivterInfo
     *
     * @param miId
     * @param toonCode
     * @return
     * @throws Exception
     * @Description : 验证参会人是否被移除
     * @author zhangxiang
     * @date: 2017年11月29日 下午6:42:09
     */
    public boolean checkeInivterInfo(String miId) throws Exception {
        Inviter inviter = new Inviter();
        inviter.setMiId(miId);
        inviter.setInviterEiId(UserServletContext.getUserInfo().getUserId());
        inviter = inviterDao.get(inviter);


        return true;
//		Maininfo maininfo = maininfoDao.getById(miId);
//		if(maininfo.getCreateEiId().equals(UserServletContext.getUserInfo().getUserId())) {
//			return true;
//		}
//		if(inviter != null && !"".equals(inviter.getId()) && StringUtils.isNotEmpty(inviter.getId())) {
//			return true;
//		}else {
//			throw new BusinessException(ReturnType.Error,"您已从参会人中被移除");
//		}
    }


    /**
     * @param maininfoVo
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Inviter> processInviterAndDeptPC(MaininfoPCVo maininfoVo, Maininfo maininfo) throws Exception {
        long currtTime = System.currentTimeMillis();
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            inviteDeptDao.deleteByMiId(maininfo.getId());
        }
        List<InviteDept> inviteDepts = new ArrayList<InviteDept>();
        List<InviteDeptVo> inviteDeptVos = maininfoVo.getInviteDeptVos();
        if (inviteDeptVos != null && inviteDeptVos.size() > 0) {
            inviteDepts = BeanMapper.mapList(inviteDeptVos, InviteDept.class);
            for (InviteDept inviteDept : inviteDepts) {
                inviteDept.setMiId(maininfo.getId());
                inviteDept.setCreateTime(currtTime);
                inviteDept.setUpdateTime(currtTime);
                inviteDept.setComCode(maininfo.getComCode());
                inviteDept.setModuCode(maininfo.getModuCode());
                inviterDeptServiceImpl.saveEntity(inviteDept);
            }
        }

        List<Inviter> inviters = new ArrayList<Inviter>();
        List<InviterVo> inviterVos = maininfoVo.getInviters();
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            inviterDao.deleteByMiId(maininfo.getId());
            maininfoInviterDao.deleteByMiId(maininfo.getId());
        }
        //添加所选部门下所有人
        //去重
        HashSet<Inviter> set = new HashSet<Inviter>();
        if (inviterVos != null && inviterVos.size() > 0) {
            inviters = BeanMapper.mapList(inviterVos, Inviter.class);
            //只保存参会人信息
            MaininfoInviter maininfoInviter = null;
            for (Inviter inviter : inviters) {
                maininfoInviter = new MaininfoInviter();
                maininfoInviter.setMiId(maininfo.getId());
                maininfoInviter.setUpdateTime(currtTime);
                maininfoInviter.setCreateTime(currtTime);
                maininfoInviter.setInviterEiId(inviter.getInviterEiId());
                maininfoInviter.setInviterEiName(inviter.getInviterEiName());
                maininfoInviter.setNodeTree(inviter.getNodeTree());
                maininfoInviter.setComCode(maininfo.getComCode());
                maininfoInviter.setModuCode(maininfo.getModuCode());
                maininfoInviterServiceImpl.saveEntity(maininfoInviter);

                inviter.setMiId(maininfo.getId());
                inviter.setUpdateTime(currtTime);
                inviter.setCreateTime(currtTime);
                inviter.setComCode(maininfo.getComCode());
                inviter.setModuCode(maininfo.getModuCode());
                //创建人也是参会人 默认是参加状态
                if (inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
                    inviter.setIsInvite(Constants.MT_INVITE_1);
                }
            }
        }
        set.addAll(inviters);
        Set<Inviter> invitersDept = new HashSet<>();
        //部门
        if (inviteDepts != null && inviteDepts.size() > 0) {
            for (InviteDept inviteDept : inviteDepts) {
                Inviter inviter = new Inviter();
                inviter.setMiId(maininfo.getId());
                inviter.setInviterEiId(inviteDept.getInviterDeptId() + "");
                inviter.setInviterEiName(inviteDept.getInviterDeptName());
                invitersDept.add(inviter);

                //查询部门下所有人 发送邮件使用
                List<Long> deptIds = new ArrayList<>();
//				deptIds.add(inviteDept.getInviterDeptId());
//				Page<SgaiEmpDto> empSimpleInfoVos = empInfoService.getSgaiEmp( inviteDept.getInviterDeptId(),"","");
//				List<SgaiEmpDto> empDtos = empSimpleInfoVos.getList();
//				if(empDtos != null && empDtos.size() > 0) {
//					for(SgaiEmpDto empDto : empDtos) {
//						Inviter inviterDept = new Inviter();
//						inviterDept.setInviterEiId(empDto.getEmpCode().toString());
//						inviterDept.setInviterEiName(empDto.getLastname());
//						inviterDept.setMiId(maininfo.getId());
//
//						set.add(inviterDept);
//
//						inviterDept.setMiId(maininfo.getId());
//						inviterDept.setUpdateTime(currtTime);
//						inviterDept.setCreateTime(currtTime);
//						inviterDept.setComCode(maininfo.getComCode());
//						inviterDept.setModuCode(maininfo.getModuCode());
//						//创建人也是参会人 默认是参加状态
//						if(inviterDept.getInviterEiId().equals(maininfo.getCreateEiId())) {
//							inviterDept.setIsInvite(Constants.MT_INVITE_1);
//						}
//					}
//				}
            }
        }
        inviters = BeanMapper.mapList(set, Inviter.class);
        for (Inviter inviter : inviters) {
            inviterServiceImpl.saveEntity(inviter);
        }
        inviters.addAll(invitersDept);
        return inviters;
    }


    /**
     * @param maininfoVo
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Inviter> processInviterAndDept(MaininfoVo maininfoVo, Maininfo maininfo) throws Exception {
        long currtTime = System.currentTimeMillis();
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            inviteDeptDao.deleteByMiId(maininfo.getId());
        }
        List<InviteDept> inviteDepts = new ArrayList<InviteDept>();
        List<InviteDeptVo> inviteDeptVos = maininfoVo.getInviteDeptVos();
        if (inviteDeptVos != null && inviteDeptVos.size() > 0) {
            inviteDepts = BeanMapper.mapList(inviteDeptVos, InviteDept.class);
            for (InviteDept inviteDept : inviteDepts) {
                inviteDept.setMiId(maininfo.getId());
                inviteDept.setCreateTime(currtTime);
                inviteDept.setUpdateTime(currtTime);
                inviteDept.setComCode(maininfo.getComCode());
                inviteDept.setModuCode(maininfo.getModuCode());
                inviterDeptServiceImpl.saveEntity(inviteDept);
//				inviteDeptDao.insert(inviteDept);
            }
        }

        List<Inviter> inviters = new ArrayList<Inviter>();
        List<InviterVo> inviterVos = maininfoVo.getInviters();
        if (maininfo.getId() != null && StringUtils.isNotEmpty(maininfo.getId())) {
            inviterDao.deleteByMiId(maininfo.getId());
            maininfoInviterDao.deleteByMiId(maininfo.getId());
        }
        //添加所选部门下所有人
        //去重
        HashSet<Inviter> set = new HashSet<Inviter>();
        List<Inviter> invitersDept = new ArrayList<Inviter>();
        if (inviteDepts != null && inviteDepts.size() > 0) {
            List<String> deptIds = new ArrayList<>();
            for (InviteDept inviteDept : inviteDepts) {
                deptIds.add(inviteDept.getInviterDeptId());
            }

//			List<EmpSimpleInfoVo> empSimpleInfoVos = empInfoService.getSgaiEmp( deptId);
            List<EmpSimpleInfoVo> empSimpleInfoVos = new ArrayList<>();
            if (empSimpleInfoVos != null && empSimpleInfoVos.size() > 0) {
                for (EmpSimpleInfoVo empSimpleInfoVo : empSimpleInfoVos) {
                    Inviter inviter = new Inviter();
                    inviter.setInviterEiId(empSimpleInfoVo.getEiId().toString());
                    inviter.setInviterEiName(empSimpleInfoVo.getUserName());
                    inviter.setMiId(maininfo.getId());
                    inviter.setUpdateTime(currtTime);
                    inviter.setCreateTime(currtTime);
                    invitersDept.add(inviter);
                }
            }

            for (int i = 0; i < invitersDept.size(); i++) {
                set.add(invitersDept.get(i));
            }
        }
        if (inviterVos != null && inviterVos.size() > 0) {
            inviters = BeanMapper.mapList(inviterVos, Inviter.class);
            //只保存参会人信息
            MaininfoInviter maininfoInviter = null;
            for (Inviter inviter : inviters) {
                maininfoInviter = new MaininfoInviter();
                maininfoInviter.setMiId(maininfo.getId());
                maininfoInviter.setUpdateTime(currtTime);
                maininfoInviter.setCreateTime(currtTime);
                maininfoInviter.setInviterEiId(inviter.getInviterEiId());
                maininfoInviter.setInviterEiName(inviter.getInviterEiName());
                maininfoInviter.setNodeTree(inviter.getNodeTree());
                maininfoInviter.setModuCode(maininfo.getModuCode());
                maininfoInviter.setComCode(maininfo.getComCode());
                maininfoInviterServiceImpl.saveEntity(maininfoInviter);
            }

            if (set != null && set.size() > 0) {
                set.addAll(inviters);
                for (Inviter inviter : set) {
                    inviter.setMiId(maininfo.getId());
                    inviter.setUpdateTime(currtTime);
                    inviter.setCreateTime(currtTime);
                    inviter.setComCode(maininfo.getComCode());
                    inviter.setModuCode(maininfo.getModuCode());
                    //创建人也是参会人 默认是参加状态
                    if (inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
                        inviter.setIsInvite(Constants.MT_INVITE_1);
                    }
                    inviterServiceImpl.saveEntity(inviter);
//					inviterDao.insert(inviter);
                }
                inviters = BeanMapper.mapList(set, Inviter.class);
            } else {
                for (Inviter inviter : inviters) {
                    inviter.setMiId(maininfo.getId());
                    inviter.setUpdateTime(currtTime);
                    inviter.setCreateTime(currtTime);
                    inviter.setComCode(maininfo.getComCode());
                    inviter.setModuCode(maininfo.getModuCode());
                    //创建人也是参会人 默认是参加状态
                    if (inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
                        inviter.setIsInvite(Constants.MT_INVITE_1);
                    }
                    inviterServiceImpl.saveEntity(inviter);
//					inviterDao.insert(inviter);
                }
            }
            //没有选人只选部门
        } else {
            if (inviteDepts != null && inviteDepts.size() > 0) {
                for (Inviter inviter : set) {
                    inviter.setMiId(maininfo.getId());
                    inviter.setUpdateTime(currtTime);
                    inviter.setCreateTime(currtTime);
                    inviter.setComCode(maininfo.getComCode());
                    inviter.setModuCode(maininfo.getModuCode());
                    //创建人也是参会人 默认是参加状态
                    if (inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
                        inviter.setIsInvite(Constants.MT_INVITE_1);
                    }
                    inviterServiceImpl.saveEntity(inviter);
//					inviterDao.insert(inviter);
                }
                inviters = BeanMapper.mapList(set, Inviter.class);
            }
        }
        return inviters;
    }

    public List<TimeSegStatusDto> getTimeSegStatus(Long currtDate, String rrId, String miId) throws Exception {
        //新增或修改
        if (StringUtils.isNotEmpty(miId)) {
            return maininfoDao.getTimeSegStatus(currtDate, rrId, UserServletContext.getUserInfo().getUserId());
        } else {
            return maininfoDao.getTimeSegStatus(currtDate, rrId, null);
        }
    }

    @Deprecated
    public boolean confirmTimeSeg(Long currtDate, String timeSeg, String rrId) throws Exception {
        //判断时间段是多个还是一个，一个按照单个数字处理
        boolean flag = true;
        if (StringUtils.isNotEmpty(timeSeg)) {
            String[] timeSegArr = timeSeg.split(",");
            if (timeSegArr != null && timeSegArr.length == 2) {
                if (timeSegArr[0].equals(timeSegArr[1])) {
                    timeSeg = timeSegArr[0];
                }
            }
        }
        if (rrId != null && StringUtils.isNotEmpty(rrId)) {
            List<Maininfo> maininfos = maininfoDao.confirmTimeSeg(currtDate, timeSeg, rrId);

            //过滤当前创建人的会议信息
            String eiId = UserServletContext.getUserInfo().getUserId();
            Iterator<Maininfo> iterator = maininfos.iterator();
            while (iterator.hasNext()) {
                Maininfo maininfo = iterator.next();
                if (maininfo.getCreateEiId().equals(eiId)) {
                    iterator.remove();
                }
            }

            flag = (maininfos == null || maininfos.size() == 0);
        } else {
            List<RoomResource> roomResources = maininfoDao.isExistsRoom(currtDate, timeSeg);
            flag = (roomResources != null && roomResources.size() > 0);
        }

        return flag;
    }

    public boolean confirmTimeSeg(Maininfo maininfo) throws Exception {
        //判断时间段是多个还是一个，一个按照单个数字处理
        boolean flag = true;
        String timeSeg = maininfo.getMiMtTimeSeg();
        if (StringUtils.isNotEmpty(timeSeg)) {
            String[] timeSegArr = timeSeg.split(",");
            if (timeSegArr != null && timeSegArr.length == 2) {
                if (timeSegArr[0].equals(timeSegArr[1])) {
                    timeSeg = timeSegArr[0];
                }
            }
        }
        String rrId = maininfo.getRrId();
        Long currtDate = maininfo.getMiMtDate();
        if (rrId != null && StringUtils.isNotEmpty(rrId)) {
            List<Maininfo> maininfos = maininfoDao.confirmTimeSeg(currtDate, timeSeg, rrId);

            //过滤当前创建人的会议信息（修改，新增判断逻辑不一样，修改会议需要排除当前操作人发起的会议）
            if (!"".equals(maininfo.getId())) {
                String eiId = UserServletContext.getUserInfo().getUserId();
                Iterator<Maininfo> iterator = maininfos.iterator();
                while (iterator.hasNext()) {
                    Maininfo maininfo2 = iterator.next();
                    if (maininfo2.getCreateEiId().equals(eiId) && maininfo2.getId().equals(maininfo.getId())) {
                        iterator.remove();
                    }
                }
            }

            flag = (maininfos == null || maininfos.size() == 0);
        } else {
            List<RoomResource> roomResources = maininfoDao.isExistsRoom(currtDate, timeSeg);
            flag = (roomResources != null && roomResources.size() > 0);
        }

        return flag;
    }

    public MeetingDetailDto getMeetingDetail(String miId) throws Exception {

        MeetingDetailDto meetingDetailDto = new MeetingDetailDto();
        Maininfo maininfo = maininfoDao.getById(miId);
        BeanUtils.copyProperties(maininfo, meetingDetailDto);
        EmpInfo empInfo = new EmpInfo();
        empInfo.setComCode(maininfo.getComId());
        empInfo.setEiId(maininfo.getCompereEiId());
        //这个查询 查的什么玩意儿 先置空了 搞清楚再放
        //empInfo = empInfoService.get(empInfo);
        empInfo = null;
        if (null != empInfo) {
            if (!"".equals(empInfo.getOrgName()) && !"null".equals(empInfo.getOrgName()) && !"".equals(empInfo.getDeptName())
                    && !"null".equals(empInfo.getDeptName())) {
                meetingDetailDto.setCompereComDept(empInfo.getOrgName() + " / " + empInfo.getDeptName());
            }
            if (!"".equals(empInfo.getEiHeadPicture()) && !"null".equals(empInfo.getEiHeadPicture())) {
                meetingDetailDto.setCompereUrl(empInfo.getEiHeadPicture());
            } else {
                meetingDetailDto.setCompereUrl(defaultEiPicUrl);
            }
        }

        List<PicDto> picDtos;
        List<Pic> pics = picDao.getByMiId(miId);
        picDtos = BeanMapper.mapList(pics, PicDto.class);

        meetingDetailDto.setPicDtos(picDtos);

        List<InviterDto> inviterDtos = new ArrayList<InviterDto>();
        List<Inviter> inviters = inviterDao.getByMiId(miId);
        inviterDtos = BeanMapper.mapList(inviters, InviterDto.class);
        for (InviterDto inviterDto : inviterDtos) {
            inviterDto.setIsDept(Constants.FALSE);
        }
        //部门转参会人
        List<Long> inviteDeptIds = new ArrayList<>();
        List<InviteDept> inviteDepts = inviteDeptDao.getByMiId(miId);
        if (inviteDepts != null && inviteDepts.size() > 0) {
            for (InviteDept inviteDept : inviteDepts) {
//				inviteDeptIds.add(inviteDept.getInviterDeptId());
                Inviter inviter = new Inviter();
                inviter.setMiId(miId);
                inviter.setInviterEiId(inviteDept.getInviterDeptId() + "");
                inviter.setInviterEiName(inviteDept.getInviterDeptName());
//				inviters.add(inviter);
                InviterDto inviterDto = BeanMapper.map(inviter, InviterDto.class);
                inviterDto.setIsDept(Constants.TRUE);
                inviterDtos.add(inviterDto);
            }
        }

        //获取参会人头像
        Iterator<InviterDto> iterator = inviterDtos.iterator();
//		while(iterator.hasNext()){
//			InviterDto inviterDto = iterator.next();
//			EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviterDto.getInviterEiId());
//			if(empInfoVo != null){
//				String inviterUrl = empInfoVo.getEiHeadPicture();
//				inviterDto.setInviterUrl(inviterUrl);
//
//				for(Long deptId : inviteDeptIds){
//					if(deptId.equals(empInfoVo.getDeptId())){
//						iterator.remove();
//					}
//				}
//			}
//
//		}
        meetingDetailDto.setInviters(inviterDtos);
        //设置当前操作人的状态（参加，请假）
        Inviter inviter = new Inviter();
        inviter.setInviterEiId(UserServletContext.getUserInfo().getUserId());
        inviter.setMiId(miId);
        inviter = inviterDao.get(inviter);
        if (inviter != null) {
            meetingDetailDto.setIsInvite(inviter.getIsInvite());
            meetingDetailDto.setIsInviter(Constants.TRUE);
        } else {
            meetingDetailDto.setIsInvite(Constants.FALSE);
            meetingDetailDto.setIsInviter(Constants.FALSE);
        }

        //是否是创建人
        if (maininfo.getCreateEiId().equals(UserServletContext.getUserInfo().getUserId())) {
            meetingDetailDto.setIsCreateUser(Constants.TRUE);
        } else {
            meetingDetailDto.setIsCreateUser(Constants.FALSE);
        }

        Long invitersSum = inviterDao.getCountByMiId(miId);
        meetingDetailDto.setInvitersSum(invitersSum);

        //判断是否逾期的状态
        if (Constants.MT_INVITE_3.equals(meetingDetailDto.getMiStatus())
                && inviter != null && Constants.MT_INVITE_0.equals(inviter.getIsInvite())) {
            meetingDetailDto.setMiStatus(Constants.MT_STATUS_4);//已逾期
        }

        List<SummaryDto> summaryDtos = new ArrayList<SummaryDto>();

        //获取当前人可见的会议纪要
        List<Summary> summaries = summaryDao.getByMiIdAndEiId(miId, UserServletContext.getUserInfo().getUserId());
        summaryDtos = BeanMapper.mapList(summaries, SummaryDto.class);
        for (SummaryDto summaryDto : summaryDtos) {
            List<SummaryPic> summaryPics = summaryPicDao.getByMsId(summaryDto.getId());
            List<SummaryPicDto> summaryPicDtos;
            summaryPicDtos = BeanMapper.mapList(summaryPics, SummaryPicDto.class);
            //获取参会人部门，头像
            EmpInfo empInfo1 = new EmpInfo();
            empInfo1.setComCode(maininfo.getComId());
            empInfo1.setEiId(summaryDto.getInviterEiId());
            empInfo1 = empInfoService.get(empInfo1);
            summaryDto.setInviterUrl(empInfo1.getEiHeadPicture());
            summaryDto.setCompereComDept(empInfo1.getOrgName() + " / " + empInfo1.getDeptName());
            summaryDto.setSummaryPicDtos(summaryPicDtos);
        }

        meetingDetailDto.setSummaryDtos(summaryDtos);

        RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
        RoomPosition roomPosition = new RoomPosition();
        roomPosition.setId(roomResource.getRpId());
//		String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
        meetingDetailDto.setRpPositionName(roomResource.getRrRoomPosition());
        meetingDetailDto.setRoomName(roomResource.getRrRoomName());
        meetingDetailDto.setRrRoomPeoples(roomResource.getRrRoomPeoples());
        meetingDetailDto.setRdRoomDevice(roomResource.getRdRoomDevice());
        meetingDetailDto.setDeviceDetailVoList(roomResourceService.getRoomDeviceDetailList(roomResource.getId()));
        //判断是否是管理员 1 部门 0 人员
        meetingDetailDto.setIsAdmin(Constants.FALSE);
        if (Constants.TRUE.equals(roomResource.getRrAdminType())) {
            List<Long> deptIds = new ArrayList<>();
            deptIds.add(Long.valueOf(roomResource.getRrAdminId()));
            List<EmpSimpleInfoVo> empInfoVos = baseEmployeeService.findEmpInfoByDeptId(Long.valueOf(maininfo.getComId()), deptIds, 0);
            for (EmpSimpleInfoVo empSimpleInfoVo : empInfoVos) {
                if (UserServletContext.getUserInfo().getUserId().equals(empSimpleInfoVo.getEiId())) {
                    meetingDetailDto.setIsAdmin(Constants.TRUE);
                }
            }
        } else {
            if (UserServletContext.getUserInfo().getUserId().toString().equals(roomResource.getRrAdminId())) {
                meetingDetailDto.setIsAdmin(Constants.TRUE);
            }
        }
        //获取物料信息
        List<MaterielVo> materielDtoList = new ArrayList<>();
        Materiel materiel = new Materiel();
        materiel.setMtId(maininfo.getId());
        materiel.setIsDelete(0L);
        List<Materiel> materielList = materielDao.findList(materiel);
        if (null != materielList && !materielList.isEmpty()) {
            for (Materiel materiel1 : materielList) {
                MaterielVo materielVo = new MaterielVo();
                BeanUtils.copyProperties(materiel1, materielVo);
                materielDtoList.add(materielVo);
            }
        }
        meetingDetailDto.setMaterielDtoList(materielDtoList);

        return meetingDetailDto;
    }

    public List<MeetingRoomListDto> selectMeetingRoomBL(Long currtDate, String timeSeg, String miId) throws Exception {
        List<MeetingRoomListDto> meetingRoomListDtos = new ArrayList<MeetingRoomListDto>();
        //判断时间段是多个还是一个，一个按照单个数字处理
        if (currtDate != null && currtDate > 0 && !"".equals(timeSeg)) {
            if (StringUtils.isNotEmpty(timeSeg)) {
                String[] timeSegArr = timeSeg.split(",");
                if (timeSegArr != null && timeSegArr.length == 2) {
                    if (timeSegArr[0].equals(timeSegArr[1])) {
                        timeSeg = timeSegArr[0];
                    }
                }
            }
        }

        List<RoomPosition> roomPositions = new ArrayList<RoomPosition>();
        List<RoomResource> roomResources = new ArrayList<RoomResource>();

        //默认显示所有的位置，如果有时间段，需要过滤没有会议室的位置
        if (currtDate != null && !"".equals(timeSeg)) {
            if (miId != null && StringUtils.isNotEmpty(miId)) {
                roomPositions = roomPositionDao.findRoomPositionListExistsRoom(currtDate, timeSeg, UserServletContext.getUserInfo().getUserId());
            } else {
                roomPositions = roomPositionDao.findRoomPositionListExistsRoom(currtDate, timeSeg, null);
            }
        } else {
            roomPositions = roomPositionDao.findAllList();
        }
        meetingRoomListDtos = BeanMapper.mapList(roomPositions, MeetingRoomListDto.class);
        //默认取第一个位置的会议室
        if (meetingRoomListDtos != null && meetingRoomListDtos.size() > 0) {
            for (MeetingRoomListDto meetingRoomListDto : meetingRoomListDtos) {
                String rpId = meetingRoomListDto.getId();

                //判断新增会议还是修改会议
                if (miId != null && StringUtils.isNotEmpty(miId)) {
                    roomResources = roomResourceDao.getByRpIdOrCurrtDateAndTimeSeg(rpId, currtDate, timeSeg, UserServletContext.getUserInfo().getUserId());
                } else {
                    roomResources = roomResourceDao.getByRpIdOrCurrtDateAndTimeSeg(rpId, currtDate, timeSeg, null);
                }
                List<RoomResourceVo> roomResourceVos = new ArrayList<>();
                if (roomResources != null && roomResources.size() > 0) {
                    roomResources.forEach(roomResource -> {
                        RoomResourceVo vo = new RoomResourceVo();
                        BeanUtils.copyProperties(roomResource, vo);
                        if (vo.getRrRoomPicMain() == null || "".equals(vo.getRrRoomPicMain())) {
                            vo.setRrRoomPicMain(defaultRoomUrl);
                        } else {
                            vo.setRrRoomPicMain(vo.getRrRoomPicMain());
                        }
                        vo.setDeviceDetailVoList(roomResourceService.getRoomDeviceDetailList(roomResource.getId()));
                        roomResourceVos.add(vo);
                    });
                    meetingRoomListDto.setRoomResourceVos(roomResourceVos);
                }
            }

        }

//		meetingRoomListDtos = setMeetingRoomListDtos(toonCode,meetingRoomListDtos, rpId, currtDate, timeSeg, miId);

        return meetingRoomListDtos;
    }


    public List<MeetingRoomListDto> selectMeetingRoom(String rpId, Long currtDate, String timeSeg, String miId) throws Exception {
        List<MeetingRoomListDto> meetingRoomListDtos = new ArrayList<MeetingRoomListDto>();
        //判断时间段是多个还是一个，一个按照单个数字处理
        if (currtDate != null && currtDate > 0 && !"".equals(timeSeg)) {
            if (StringUtils.isNotEmpty(timeSeg)) {
                String[] timeSegArr = timeSeg.split(",");
                if (timeSegArr != null && timeSegArr.length == 2) {
                    if (timeSegArr[0].equals(timeSegArr[1])) {
                        timeSeg = timeSegArr[0];
                    }
                }
            }
        }
        meetingRoomListDtos = setMeetingRoomListDtos(meetingRoomListDtos, rpId, currtDate, timeSeg, miId);

        return meetingRoomListDtos;
    }


    /**
     * MethodName : setMeetingRoomListDtos
     *
     * @param toonCode
     * @param meetingRoomListDtos
     * @param rpId
     * @param currtDate
     * @param timeSeg
     * @param miId
     * @Description : 处理meetingRoomListDtos
     * @author zhangxiang
     * @date: 2017年11月24日 下午2:51:56
     */
    private List<MeetingRoomListDto> setMeetingRoomListDtos(List<MeetingRoomListDto> meetingRoomListDtos,
                                                            String rpId, Long currtDate, String timeSeg, String miId) {
        List<RoomPosition> roomPositions = new ArrayList<RoomPosition>();
        List<RoomResource> roomResources = new ArrayList<RoomResource>();
        if (rpId != null && StringUtils.isNotEmpty(rpId)) {
            //会议室删除时，给出提示
            RoomPosition roomPosition = roomPositionDao.getById(rpId);
            if (roomPosition == null || "".equals(roomPosition.getId())) {
                throw new BusinessException(ReturnType.Error, "该位置不可用！");
            }
            roomPositions.add(roomPosition);
            if (roomPositions != null && roomPositions.size() > 0) {
                meetingRoomListDtos = BeanMapper.mapList(roomPositions, MeetingRoomListDto.class);
            }
        } else {
            //默认显示所有的位置，如果有时间段，需要过滤没有会议室的位置
            if (currtDate != null && !"".equals(timeSeg)) {
                if (miId != null && StringUtils.isNotEmpty(miId)) {
                    roomPositions = roomPositionDao.findRoomPositionListExistsRoom(currtDate, timeSeg, UserServletContext.getUserInfo().getUserId());
                } else {
                    roomPositions = roomPositionDao.findRoomPositionListExistsRoom(currtDate, timeSeg, null);
                }
            } else {
                roomPositions = roomPositionDao.findAllList();
            }
            meetingRoomListDtos = BeanMapper.mapList(roomPositions, MeetingRoomListDto.class);
            //默认取第一个位置的会议室
            if (meetingRoomListDtos != null && meetingRoomListDtos.size() > 0) {
                rpId = meetingRoomListDtos.get(0).getId();
            }
        }
        //判断新增会议还是修改会议
        if (miId != null && StringUtils.isNotEmpty(miId)) {
            roomResources = roomResourceDao.getByRpIdOrCurrtDateAndTimeSeg(rpId, currtDate, timeSeg, UserServletContext.getUserInfo().getUserId());
        } else {
            roomResources = roomResourceDao.getByRpIdOrCurrtDateAndTimeSeg(rpId, currtDate, timeSeg, null);
        }
        return getRoomResourceByRp(meetingRoomListDtos, roomResources);
    }

    /**
     * MethodName : getRoomResourceByRp
     *
     * @param meetingRoomListDtos
     * @param roomResources
     * @Description :
     * @author zhangxiang
     * @date: 2017年11月24日 下午2:52:26
     */
    private List<MeetingRoomListDto> getRoomResourceByRp(List<MeetingRoomListDto> meetingRoomListDtos, List<RoomResource> roomResources) {
        List<RoomResourceVo> roomResourceVos = new ArrayList<RoomResourceVo>();
        if (roomResources != null && roomResources.size() > 0) {
            roomResourceVos = BeanMapper.mapList(roomResources, RoomResourceVo.class);
            for (RoomResourceVo roomResourceVo : roomResourceVos) {
                if (roomResourceVo.getRrRoomPicMain() == null || "".equals(roomResourceVo.getRrRoomPicMain())) {
                    roomResourceVo.setRrRoomPicMain(defaultRoomUrl);
                } else {
                    roomResourceVo.setRrRoomPicMain(roomResourceVo.getRrRoomPicMain());
                }
                //设置会议室设备信息
                roomResourceVo.setDeviceDetailVoList(roomResourceService.getRoomDeviceDetailList(roomResourceVo.getId()));
            }
            meetingRoomListDtos.get(0).setRoomResourceVos(roomResourceVos);
        }

        return meetingRoomListDtos;
    }


    public MeetingInfoDto editMeeting(String miId) throws Exception {
        MeetingInfoDto meetingInfoDto = new MeetingInfoDto();
        Maininfo maininfo = maininfoDao.getById(miId);
        BeanUtils.copyProperties(maininfo, meetingInfoDto);
        List<PicDto> picDtos = new ArrayList<PicDto>();
        List<Pic> picUrls = picDao.getByMiId(miId);
        picDtos = BeanMapper.mapList(picUrls, PicDto.class);

        for (PicDto picDto : picDtos) {
            picDto.setPicUrl(picDto.getPicUrl());
        }

        meetingInfoDto.setPicDtos(picDtos);
        List<InviterDto> inviterDtos = new ArrayList<InviterDto>();
//		List<Inviter> inviters = inviterDao.getByMiId(miId);
        List<MaininfoInviter> inviters = maininfoInviterDao.getByMiId(miId);
        if (inviters != null && inviters.size() > 0) {
            inviterDtos = BeanMapper.mapList(inviters, InviterDto.class);

            //参会人头像
            for (InviterDto inviterDto : inviterDtos) {
                EmpInfo empInfoVo = new EmpInfo();
                empInfoVo.setComCode(maininfo.getComId());
                empInfoVo.setEiId(Long.valueOf(inviterDto.getInviterEiId()));
                empInfoVo = empInfoService.get(empInfoVo);
                String eiHeadPicture = empInfoVo.getEiHeadPicture();
//				if (maininfo.getCompereEiId().longValue() > 0 && inviterDto.getInviterEiId().longValue() == maininfo.getCompereEiId().longValue()) {
//					inviterDto.setIsCompere(Constants.TRUE);
//				}
                inviterDto.setIsCompere(Constants.FALSE);
                if (eiHeadPicture == null || "".equals(eiHeadPicture)) {
                    inviterDto.setInviterUrl(defaultEiPicUrl);
                } else {
                    inviterDto.setInviterUrl(eiHeadPicture);
                }

            }
            meetingInfoDto.setInviterDtos(inviterDtos);
        }
        List<InviteDeptVo> inviteDeptVos = new ArrayList<InviteDeptVo>();
        List<InviteDept> inviteDepts = inviteDeptDao.getByMiId(miId);
        inviteDeptVos = BeanMapper.mapList(inviteDepts, InviteDeptVo.class);

        meetingInfoDto.setInviteDeptVos(inviteDeptVos);
        RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
        RoomPosition roomPosition = new RoomPosition();
        roomPosition.setId(roomResource.getRpId());
//		String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
        meetingInfoDto.setRpId(roomResource.getRpId().toString());
        meetingInfoDto.setRpPositionName(roomResource.getRrRoomPosition());
        meetingInfoDto.setRoomName(roomResource.getRrRoomName());
        meetingInfoDto.setRrId(roomResource.getId());

        List<MaterielVo> materielDtoList = new ArrayList<>();
        Materiel materiel = new Materiel();
        materiel.setMtId(maininfo.getId());
        materiel.setIsDelete(0L);
        List<Materiel> materielList = materielDao.findList(materiel);
        if (null != materielList && !materielList.isEmpty()) {
            for (Materiel materiel1 : materielList) {
                MaterielVo materielVo = new MaterielVo();
                BeanUtils.copyProperties(materiel1, materielVo);
                materielVo.setMaId(materiel1.getMaId());
                materielDtoList.add(materielVo);
            }
        }

        meetingInfoDto.setMaterielDtoList(materielDtoList);

        return meetingInfoDto;
    }

    /* (non-Javadoc)
     * @see com.qitoon.app.meetings.api.service.inteface.IMaininfoService#leaveMeeting(java.lang.String, java.lang.Long, java.lang.String)
     */
    @Transactional
    public boolean leaveMeeting(String miId, String leaveReason) throws Exception {

        checkeInivterInfo(miId);

        Inviter inviter = new Inviter();
        inviter.setMiId(miId);
        inviter.setInviterEiId(UserServletContext.getUserInfo().getUserId());
        inviter.setIsInvite(Constants.MT_INVITE_2);
        inviter.setUpdateTime(System.currentTimeMillis());
        inviter.setLeaveReason(leaveReason);
        int rs = inviterDao.updateByMiIdAndInviterEiId(inviter);
        if (rs < 1) {
            return false;
        } else {
            //发送通知
            Maininfo maininfo = maininfoDao.getById(miId);
            //EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), UserServletContext.getUserInfo().getUserId());
//			meetingsInfoAppAsyncTask.leaveMeetingMessage(inviter, inToonCode, miId, leaveReason);
            return true;
        }

    }

    /* (non-Javadoc)
     * @see com.qitoon.app.meetings.api.service.inteface.IMaininfoService#inviteMeeting(java.lang.String, java.lang.Long)
     */
    public boolean inviteMeeting(String miId) throws Exception {
        return inviteMeetingFromEmail(miId, UserServletContext.getUserInfo().getUserId());
    }

    /* (non-Javadoc)
     * @see com.qitoon.app.meetings.api.service.inteface.IMaininfoService#saveSummary(java.lang.String, com.qitoon.app.meetings.api.vo1.SummaryVo)
     */
    @Transactional
    public boolean saveSummary(SummaryVo summaryVo) throws Exception {
        long currtTime = System.currentTimeMillis();
        Summary summary = new Summary();
        BeanUtils.copyProperties(summaryVo, summary);
        summary.setCreateTime(currtTime);
        summary.setUpdateTime(currtTime);
        summary.setInviterEiId(UserServletContext.getUserInfo().getUserId());
        summary.setInviterEiName(UserServletContext.getUserInfo().getUserName());
        summary.setComCode(UserServletContext.getUserInfo().getComCode());
        summary.setModuCode(UserServletContext.getUserInfo().getModuCode());
        boolean rs = summaryServiceImpl.saveEntity(summary);
        if (!rs) {
            return rs;
        } else {
            //保存会议纪要图片信息
            List<SummaryPicVo> summaryPicUrls = summaryVo.getSummaryPicUrls();
            List<SummaryPic> summaryPics = BeanMapper.mapList(summaryPicUrls, SummaryPic.class);
            for (SummaryPic summaryPic : summaryPics) {
                summaryPic.setMsId(summary.getId());
                summaryPic.setCreateTime(currtTime);
                summaryPic.setUpdateTime(currtTime);
                summaryPic.setComCode(UserServletContext.getUserInfo().getComCode());
                summaryPic.setModuCode(UserServletContext.getUserInfo().getModuCode());
                rs = summaryPicServiceImpl.saveEntity(summaryPic);
//				rs = summaryPicDao.insert(summaryPic);
                if (!rs) {
                    return rs;
                }
            }
            //发送通知
//			meetingsInfoAppAsyncTask.saveSummaryMessage(toonCode, summary);
            return true;
        }
    }

    @Transactional(noRollbackFor = BusinessException.class)
    public boolean finishMeeting(String miId) throws Exception {
        long currtTime = System.currentTimeMillis();
        //判断会议是否已经开始
        Maininfo maininfoC = maininfoDao.getById(miId);
        Maininfo maininfo = new Maininfo();
        if (maininfoC != null) {
            if (!maininfoC.getMiStatus().equals(Constants.MT_STATUS_1) && !maininfoC.getMiStatus().equals(Constants.MT_STATUS_2)) {
                throw new BusinessException("90", "", "会议已结束");
            }
        }

        maininfo.setId(miId);
        maininfo.setUpdateTime(currtTime);
        maininfo.setMiStatus(Constants.MT_STATUS_4);
        int rs = maininfoDao.updateById(maininfo);
        if (rs < 1) {
            return false;
        } else {
            //发送通知
            //meetingsInfoAppAsyncTask.cancelMeetingMessage(toonCode, miId);
            return true;
        }

    }


    @Transactional(noRollbackFor = BusinessException.class)
    public boolean cancelMeeting(String miId) throws Exception {
        long currtTime = System.currentTimeMillis();
        //判断会议是否已经开始
        Date miDate = getMeetingStarttime(miId);
        //进行中
        Maininfo maininfoC = maininfoDao.getById(miId);
        Maininfo maininfo = new Maininfo();
        if (currtTime > miDate.getTime()) {
            //开始后，更新数据
            maininfo.setId(miId);
            maininfo.setUpdateTime(currtTime);
            maininfo.setMiStatus(Constants.MT_STATUS_1);
            maininfoDao.updateById(maininfo);
            throw new BusinessException("80", "", "会议已开始，无法取消");
        }

        if (!maininfoC.getMiStatus().equals(Constants.MT_STATUS_2)) {
            throw new BusinessException("80", "", "会议已开始，无法取消");
        }
        maininfo.setId(miId);
        maininfo.setUpdateTime(currtTime);
        maininfo.setMiStatus(Constants.MT_STATUS_3);
        int rs = maininfoDao.updateById(maininfo);
        if (rs < 1) {
            return false;
        } else {
            //发送通知
//			meetingsInfoAppAsyncTask.cancelMeetingMessage(toonCode, miId);
            return true;
        }

    }


    /**
     */
    public List<EmpSimpleInfoVo> findEmpInfoByDeptId(List<Long> deptIds) {
        List<EmpSimpleInfoVo> empSimpleInfoVos = baseEmployeeService.findEmpInfoByDeptId(UserServletContext.getUserInfo().getCompanyId(), deptIds, 0);
        if (empSimpleInfoVos != null && empSimpleInfoVos.size() > 7) {
            return empSimpleInfoVos.subList(0, 7);
        }
        return empSimpleInfoVos;
    }


    /**
     * MethodName : getMeetingStartEndTime
     *
     * @param miMtTimeSeg
     * @return
     * @Description : 通过会议时间段 返回对应的时间段(如：1,2-->7:00-8:00)
     * @author zhangxiang
     * @date: 2017年11月13日 下午3:51:21
     */
    public String getMeetingStartEndTime(String miMtTimeSeg) {
        String[] meetingTimeSeg = new String[2];
        if (miMtTimeSeg != null && !"".equals(miMtTimeSeg)) {
            String[] miMtTimeSegArr = miMtTimeSeg.split(",");
            DictGeneral dictGeneralStart = new DictGeneral();
            dictGeneralStart.setDgCode("BIZ_MT_TIME_SEG");
            dictGeneralStart.setDgKey(miMtTimeSegArr[0]);
            dictGeneralStart = dictGeneralDao.get(dictGeneralStart);
            meetingTimeSeg[0] = dictGeneralStart.getDgValue();

            DictGeneral dictGeneralEnd = new DictGeneral();
            dictGeneralEnd.setDgCode("BIZ_MT_TIME_SEG");
            dictGeneralEnd.setDgKey(miMtTimeSegArr[miMtTimeSegArr.length - 1]);
            dictGeneralEnd = dictGeneralDao.get(dictGeneralEnd);
            TimeUtil timeEnd = new TimeUtil(dictGeneralEnd.getDgValue() + ":00").addTime(new TimeUtil(0, 30));
            meetingTimeSeg[1] = timeEnd.toString();
        }
        return meetingTimeSeg[0] + "-" + meetingTimeSeg[1];
    }

    /**
     */
    public Date getMeetingEndtime(String miId) {
        Maininfo maininfo = maininfoDao.getById(miId);
        String[] miMtTimeSegArr = maininfo.getMiMtTimeSeg().split(",");
        DictGeneral dictGeneralEnd = new DictGeneral();
        dictGeneralEnd.setDgCode("BIZ_MT_TIME_SEG");
        dictGeneralEnd.setDgKey(miMtTimeSegArr[miMtTimeSegArr.length - 1]);
        dictGeneralEnd = dictGeneralDao.get(dictGeneralEnd);
        TimeUtil timeEnd = new TimeUtil(dictGeneralEnd.getDgValue() + ":00").addTime(new TimeUtil(0, 30));
        Date date = null;
        try {
            date = DateUtils.parseDate(DateFormatUtils.format(maininfo.getMiMtDate(), Constants.DATEFORMAT_YYYYMMDD) + " " + timeEnd + ":00", Constants.DATEFORMAT_YYYYMMDDHHMMSS);
        } catch (ParseException e) {
            logger.error("日期转换错误！");
        }
        return date;
    }

    private Long getMeetingStarttime(Long miMiMtDate, String miMtTimeSeg) {
        Date date = null;
        String[] miMtTimeSegArr = miMtTimeSeg.split(",");
        DictGeneral dictGeneralStart = new DictGeneral();
        dictGeneralStart.setDgCode("BIZ_MT_TIME_SEG");
        dictGeneralStart.setDgKey(miMtTimeSegArr[0]);
        dictGeneralStart = dictGeneralDao.get(dictGeneralStart);
        TimeUtil timeStart = new TimeUtil(dictGeneralStart.getDgValue() + ":00");
        try {
            date = DateUtils.parseDate(DateFormatUtils.format(miMiMtDate, Constants.DATEFORMAT_YYYYMMDD) + " " + timeStart + ":00", Constants.DATEFORMAT_YYYYMMDDHHMMSS);
        } catch (ParseException e) {
            logger.error("日期转换错误！");
        }
        return date.getTime();
    }

    /**
     * @see
     */
    public Date getMeetingStarttime(String miId) {
        Date date = null;
        Maininfo maininfo = maininfoDao.getById(miId);
        String[] miMtTimeSegArr = maininfo.getMiMtTimeSeg().split(",");
        DictGeneral dictGeneralStart = new DictGeneral();
        dictGeneralStart.setDgCode("BIZ_MT_TIME_SEG");
        dictGeneralStart.setDgKey(miMtTimeSegArr[0]);
        dictGeneralStart = dictGeneralDao.get(dictGeneralStart);
        TimeUtil timeStart = new TimeUtil(dictGeneralStart.getDgValue() + ":00");
        try {
            date = DateUtils.parseDate(DateFormatUtils.format(maininfo.getMiMtDate(), Constants.DATEFORMAT_YYYYMMDD) + " " + timeStart + ":00", Constants.DATEFORMAT_YYYYMMDDHHMMSS);
        } catch (ParseException e) {
            logger.error("日期转换错误！");
        }
        return date;
    }

    /**
     * @throws ParseException
     */
    public boolean schedulerUpdateMeetingState() throws ParseException {
        //获取当天的时间段内 状态需要更新的会议数据
        long currtDate = System.currentTimeMillis();
        long date = DateUtils.parseDate(DateFormatUtils.format(new Date(), Constants.DATEFORMAT_YYYYMMDD), Constants.DATEFORMAT_YYYYMMDD).getTime();
        List<Maininfo> maininfos = maininfoDao.findMeetingsByCurrtDate(date);
        for (Maininfo maininfo : maininfos) {
            String[] miMtTimeSegArr = maininfo.getMiMtTimeSeg().split(",");
            if (miMtTimeSegArr != null && miMtTimeSegArr.length > 0) {
                //已经取消的不处理,已结束的不处理
                if (Constants.MT_STATUS_3.equals(maininfo.getMiStatus())
                        || Constants.MT_STATUS_4.equals(maininfo.getMiStatus())) {
                    continue;
                }
                Date startTime = getMeetingStarttime(maininfo.getId());
                //进行中
                if (currtDate > startTime.getTime()) {
                    Maininfo arg0 = new Maininfo();
                    arg0.setId(maininfo.getId());
                    arg0.setUpdateTime(currtDate);
                    arg0.setMiStatus(Constants.MT_STATUS_1);
                    maininfoDao.updateById(arg0);
                }
                Date endTime = getMeetingEndtime(maininfo.getId());
                //已结束
                if (currtDate > endTime.getTime()) {
                    Maininfo arg0 = new Maininfo();
                    arg0.setId(maininfo.getId());
                    arg0.setUpdateTime(currtDate);
                    arg0.setMiStatus(Constants.MT_STATUS_4);
                    maininfoDao.updateById(arg0);
                }
            }
        }
        return true;
    }

    /**
     */
    @Transactional
    public boolean inviteMeetingFromEmail(String miId, String meetingInviter) throws Exception {
        long currtTime = System.currentTimeMillis();
        Inviter inviter = new Inviter();
        inviter.setMiId(miId);
        inviter.setInviterEiId(meetingInviter);
        //判断请假或参加状态
        Inviter inviterSource = inviterDao.get(inviter);
        if (inviterSource.getIsInvite().equals(Constants.MT_INVITE_2)) {
            inviter.setIsInvite(Constants.MT_INVITE_3);
        } else {
            inviter.setIsInvite(Constants.MT_INVITE_1);
        }
        inviter.setSignTime(currtTime);
        inviter.setUpdateTime(currtTime);
        int rs = inviterDao.updateByMiIdAndInviterEiId(inviter);
        if (rs < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     */
    @Transactional
    public boolean leaveMeetingFromEmail(String miId, String meetingInviter) throws Exception {
        Inviter inviter = new Inviter();
        inviter.setMiId(miId);
        inviter.setInviterEiId(meetingInviter);
        inviter.setIsInvite(Constants.MT_INVITE_2);
        inviter.setUpdateTime(System.currentTimeMillis());
        inviter.setLeaveReason("");
        int rs = inviterDao.updateByMiIdAndInviterEiId(inviter);
        if (rs < 1) {
            return false;
        } else {
            //发送通知
            //Maininfo maininfo = maininfoDao.getById(miId);
            //EmpInfoVo empInfoVo = baseEmployeeService.getEmpInfoByEiIds(UserServletContext.getUserInfo().getCompanyId(), meetingInviter);
            return true;
        }
    }


    public boolean sendMeetingNotice() throws Exception {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.add(Calendar.MINUTE, 15);
        calendarEnd.add(Calendar.MINUTE, 17);
        String yyyyMMDD = Constants.DATEFORMAT_YYYYMMDD;
        String yyyyMMDDHHMM = Constants.DATEFORMAT_YYYYMMDDHHMM;
        long date = DateUtils.parseDate(DateFormatUtils.format(new Date(), yyyyMMDD), yyyyMMDD).getTime();
        List<Maininfo> maininfos = maininfoDao.findMeetingsByCurrtDateNoStart(date);
        for (Maininfo maininfo : maininfos) {
            //计算开始时间
            Date startTime = getMeetingStarttime(maininfo.getId());
            Date minTime = DateUtils.parseDate(DateFormatUtils.format(calendarStart.getTimeInMillis(), yyyyMMDDHHMM), yyyyMMDDHHMM);
            Date maxTime = DateUtils.parseDate(DateFormatUtils.format(calendarEnd.getTimeInMillis(), yyyyMMDDHHMM), yyyyMMDDHHMM);
            if (startTime.getTime() >= minTime.getTime() && startTime.getTime() < maxTime.getTime()) {
                logger.info("meetingsInfoAppAsyncTask.sendMeetingNotice:{" + maininfo.getId() + "}");
                meetingsInfoAppAsyncTask.sendMeetingNotice(maininfo);
            }
        }
        return true;
    }

    public boolean sendMeetingNoticeForSet() throws Exception {
        Calendar calendarStart = Calendar.getInstance();
        String yyyyMMDD = Constants.DATEFORMAT_YYYYMMDD;
        String yyyyMMDDHHMM = Constants.DATEFORMAT_YYYYMMDDHHMM;
        long date = DateUtils.parseDate(DateFormatUtils.format(new Date(), yyyyMMDD), yyyyMMDD).getTime();
        List<Maininfo> maininfos = maininfoDao.findMeetingsByCurrtDateNoStart(date);
        for (Maininfo maininfo : maininfos) {
            //计算开始时间
            Date startTime = getMeetingStarttime(maininfo.getId());
            //是否开启提醒
            if (Constants.TRUE.equals(maininfo.getMiIsRemind())) {
                Date remindTime = DateUtils.addMinutes(startTime, -maininfo.getMiRemindMin());
                logger.info("sendMeetingNotice:会议开始时间:" + DateFormatUtils.format(startTime.getTime(), yyyyMMDDHHMM));
                logger.info("sendMeetingNotice:会议提醒时间:" + DateFormatUtils.format(remindTime.getTime(), yyyyMMDDHHMM));
                if (calendarStart.getTimeInMillis() == remindTime.getTime()) {
                    logger.info("MaininfoServiceImpl.sendMeetingNoticeForSet:{" + maininfo.getId() + "}");
                    meetingsInfoAppAsyncTask.sendMeetingNotice(maininfo);
                }
            }

        }
        return true;
    }

}