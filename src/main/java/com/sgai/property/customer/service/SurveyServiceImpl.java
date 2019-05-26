package com.sgai.property.customer.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.customer.dao.*;
import com.sgai.property.customer.entity.SurveyMain;
import com.sgai.property.customer.entity.SurveyQuestion;
import com.sgai.property.customer.entity.SurveyUserAnswer;
import com.sgai.property.customer.entity.SurveyUserInfo;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.vo.SurveyOptionStatisticsVo;
import com.sgai.property.customer.vo.SurveyStatisticsVo;
import com.sgai.property.customer.vo.SurveyTextAnswerVo;
import com.sgai.property.customer.vo.survey.*;
import com.sgai.modules.login.consts.SysConstant;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.support.UserServletContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 问卷调查住逻辑处理Service
 *
 * @author Hou
 * @create 2018-03-01 15:07
 */
@Service
public class SurveyServiceImpl {
    private static final Logger logger = LogManager.getLogger(SurveyServiceImpl.class);

    @Value("${SurveyNo.ComCode}")
    private String comCode;
    @Value("${SurveyNo.SequCode}")
    private String sequCode;
    @Autowired
    private BaseCodeService baseCodeService;

    @Autowired
    private ISurveyMainDao surveyMainDao;
    @Autowired
    private ISurveyQuestionDao surveyQuestionDao;
    @Autowired
    private ISurveyUserInfoDao surveyUserInfoDao;
    @Autowired
    private ISurveyUserAnswerDao surveyUserAnswerDao;
    @Autowired
    private IUserAnswerStatisticsDao userAnswerStatisticsDao;
    
    @Autowired
    private TokenServerImpl tokenServer;
    @Autowired
    private SurveyMainServiceImpl surveyMainService;
    @Autowired
    private SurveyQuestionServiceImpl surveyQuestionService;

    /**
     * 获取问卷信息通过SmId - app端专用
     * @param smId
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public SurveyInfo getDetailById(String smId) {
        SurveyInfo surveyInfo = new SurveyInfo();
        SurveyMainVo mainVo = new SurveyMainVo();
        List<SurveyQuestionDetailVo> sqVoList = new ArrayList<>();

        //查看用户是否完成
        mainVo.setSuIsFinish(surveyIsFinish(smId,UserServletContext.getUserInfo().getFeeId()));

        //获取问卷主信息
        SurveyMain surveyMain = surveyMainDao.getById(smId);
        if (null == surveyMain) {
            throw new BusinessException(ReturnType.ParamIllegal, "Id有误请检查!");
        }
        if("1".equals(surveyMain.getIsDelete().toString())){
            throw new BusinessException(ReturnType.ParamIllegal, "问卷已经被删除!");
        }
        BeanUtils.copyProperties(surveyMain, mainVo);
        mainVo.setSmId(surveyMain.getId());
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
        mainVo.setSmStartTime(surveyMain.getSmStartTime()==null?"":sdf.format(surveyMain.getSmStartTime()));
        mainVo.setSmEndTime(surveyMain.getSmEndTime()==null?"":sdf.format(surveyMain.getSmEndTime()));

        //获取问题list
        SurveyQuestion sq = new SurveyQuestion();
        sq.setSmId(smId);
        sq.setIsDelete(Constants.IsDelete.No);
        sq.setComCode(UserServletContext.getUserInfo().getComCode());
        sq.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<SurveyQuestion> surveyQuestionList = surveyQuestionDao.findList(sq);
        if (null != surveyQuestionList && !surveyQuestionList.isEmpty()) {
            List<SurveyUserAnswer> uaList = new ArrayList<>(null != surveyMain.getSmCount()?Integer.parseInt(surveyMain.getSmCount().toString()):30);
            if(mainVo.getSuIsFinish().equals(1L)){
                //如果已经答过题 需要拿到之前的所有答案
                SurveyUserAnswer surveyUserAnswer = new SurveyUserAnswer();
                surveyUserAnswer.setSmId(smId);
                surveyUserAnswer.setUserId(UserServletContext.getUserInfo().getUserNo()+"");
                surveyUserAnswer.setFeedId(UserServletContext.getUserInfo().getFeeId());
                surveyUserAnswer.setComCode(UserServletContext.getUserInfo().getComCode());
                surveyUserAnswer.setModuCode(UserServletContext.getUserInfo().getModuCode());
                uaList = surveyUserAnswerDao.findList(surveyUserAnswer);
            }
            List<SurveyUserAnswer> finalUaList = uaList;
            surveyQuestionList.forEach(surveyQuestion -> {
                SurveyQuestionDetailVo sqVo = new SurveyQuestionDetailVo();
                BeanUtils.copyProperties(surveyQuestion, sqVo);
                sqVo.setSqId(surveyQuestion.getId());
                if(null != finalUaList && !finalUaList.isEmpty()){
                    //答过题也要将原来的答案返回
                    List<String> saContentList = new LinkedList<>();
                    finalUaList.stream().filter(surveyUserAnswer -> null != surveyUserAnswer.getSqId()).forEach(surveyUserAnswer -> {
                        if(surveyQuestion.getId().equals(surveyUserAnswer.getSqId())){
                            saContentList.add(surveyUserAnswer.getSaContent());
                        }
                    });
                    sqVo.setSaContentList(saContentList);
                }
                sqVoList.add(sqVo);
            });
            Collections.sort(sqVoList);

        }

        surveyInfo.setSurveyMainVo(mainVo);
        surveyInfo.setSurveyQuestionDetailVoList(sqVoList);

        return surveyInfo;
    }

    /**
     * 判断问卷是否完成 0未完成 1完成
     * @param smId
     * @param feedId
     * @return
     */
    public Long surveyIsFinish(String smId,String feedId){
        SurveyUserInfo surveyUserInfo = new SurveyUserInfo();
        surveyUserInfo.setSmId(smId);
        surveyUserInfo.setFeedId(feedId);
        surveyUserInfo = surveyUserInfoDao.get(surveyUserInfo);
        if(null != surveyUserInfo && null != surveyUserInfo.getId()){
            return surveyUserInfo.getSuIsFinish();
        }else {
           return 0L;
        }
    }

    /**
     * 创建问卷
     *
     * @param surveyParam
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean createSurvey(SurveyParam surveyParam) {
        Boolean result;
        SurveyMain surveyMain = new SurveyMain();
        BeanUtils.copyProperties(surveyParam.getSurveyMainParam(), surveyMain);
        surveyMain.setIsDelete(Constants.IsDelete.No);
        surveyMain.setSmCreatorId(UserServletContext.getUserInfo().getUserNo() + "");
        surveyMain.setSmCreatorFeedId(UserServletContext.getUserInfo().getFeeId());
        surveyMain.setSmCreatorName(UserServletContext.getUserInfo().getUserName());
        surveyMain.setSmStatus(surveyParam.getSurveyMainParam().getSmStatus()==null?
                Constants.SurveyStatus.Wait:surveyParam.getSurveyMainParam().getSmStatus());
        surveyMain.setSmStartTime(StringUtils.isNotBlank(surveyParam.getSurveyMainParam().getSmStartTime())?
                new Date(Long.parseLong(surveyParam.getSurveyMainParam().getSmStartTime())):null);
        surveyMain.setSmEndTime(StringUtils.isNotBlank(surveyParam.getSurveyMainParam().getSmEndTime())?
                new Date(Long.parseLong(surveyParam.getSurveyMainParam().getSmEndTime())):null);

        //获取问卷编号
        String surveyNo;
        try{
        	String token = JwtUtil.userToJwt(UserServletContext.getUserInfo(), SysConstant.JWT_TTL);
            surveyNo = baseCodeService.getNumber(comCode, sequCode);
        }catch (Exception e){
            e.printStackTrace();
//            throw new BusinessException(ReturnType.ThirdParty, "问卷编号创建失败!");
            surveyNo="WJ_"+DateFormatUtils.format(new Date(), "yyyyMMdd")+"_0001";
        }
        surveyMain.setSurveyNo(surveyNo);
        surveyMain.setComCode(UserServletContext.getUserInfo().getComCode());
        surveyMain.setModuCode(UserServletContext.getUserInfo().getModuCode());
        result = surveyMainService.saveEntity(surveyMain);

        //批量插入问题
        batchInsertSurveyQuestions(surveyMain.getId(), surveyParam.getSurveyQuestionParamList());

        return result;
    }


    /**
     * 批量插入问卷问题
     *  @param smId                    问卷id
     * @param surveyQuestionParamList
     */
    private void batchInsertSurveyQuestions(String smId, List<SurveyQuestionParam> surveyQuestionParamList) {
        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        if (null != surveyQuestionParamList && !surveyQuestionParamList.isEmpty()) {
            surveyQuestionParamList.forEach(surveyQuestionParam -> {
                SurveyQuestion surveyQuestion = new SurveyQuestion();
                BeanUtils.copyProperties(surveyQuestionParam, surveyQuestion);
                surveyQuestion.setId(UUID.randomUUID().toString());
                surveyQuestion.setSmId(smId);
                surveyQuestion.setIsDelete(Constants.IsDelete.No);
                surveyQuestion.setSqDetail("1");
                surveyQuestion.setSqIsRequired(0L);
                surveyQuestion.setCreatedBy(UserServletContext.getUserInfo().getUserNo());
                surveyQuestion.setCreatedDt(new Date());
                surveyQuestion.setUpdatedDt(new Date());
                surveyQuestion.setUpdatedBy(UserServletContext.getUserInfo().getUserNo());
                surveyQuestion.setVersion(1);
                surveyQuestion.setRemarks("");
                surveyQuestion.setComCode(UserServletContext.getUserInfo().getComCode());
                surveyQuestion.setModuCode(UserServletContext.getUserInfo().getModuCode());
                surveyQuestionList.add(surveyQuestion);
            });
            surveyQuestionDao.insertSurveyQuestionList(surveyQuestionList);
        }
    }


    /**
     * 更新问卷调查
     *
     * @param surveyParam
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean updateSurvey(SurveyParam surveyParam) {
        Boolean result;
        if (null == surveyParam.getSurveyMainParam().getSmId()) {
            throw new BusinessException(ReturnType.ParamIllegal, "smId为空!");
        }

        //验证问卷结束时间
      //获取问卷主信息
        SurveyMain survey = surveyMainDao.getById(surveyParam.getSurveyMainParam().getSmId());
        Long currTime = System.currentTimeMillis();
        Date endDateTime = survey.getSmEndTime();
        if (currTime > endDateTime.getTime()) {
            throw new BusinessException(ReturnType.Error, "问卷已结束，不允许修改");
        }
        
        //更新主表
        SurveyMain surveyMain = new SurveyMain();
        BeanUtils.copyProperties(surveyParam.getSurveyMainParam(), surveyMain);
        surveyMain.setId(surveyParam.getSurveyMainParam().getSmId());
        if(StringUtils.isNotBlank(surveyParam.getSurveyMainParam().getSmEndTime())){
            Long endTime = Long.valueOf(surveyParam.getSurveyMainParam().getSmEndTime());
            surveyMain.setSmEndTime(new Date(endTime));
        }
        result = surveyMainService.updateById(surveyMain);

        //问卷问题删除再添加
        SurveyQuestion sq = new SurveyQuestion();
        sq.setSmId(surveyParam.getSurveyMainParam().getSmId());
        surveyQuestionDao.delete(sq);
        this.batchInsertSurveyQuestions(surveyParam.getSurveyMainParam().getSmId(), surveyParam.getSurveyQuestionParamList());

        return result;
    }

    /**
     * 结束问卷调查
     *
     * @param smId
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean stopSurvey(String smId) {
        Boolean result;
        if (StringUtils.isBlank(smId)) {
            throw new BusinessException(ReturnType.ParamIllegal, "id不能为空");
        }
        SurveyMain sm = new SurveyMain();
        sm.setId(smId);
        sm.setSmStatus(Constants.SurveyStatus.Stop);
        sm.setSmEndTime(new Date());
        result = surveyMainService.updateById(sm);
        return result;
    }

    /**
     * 删除问卷调查
     *
     * @param smId
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean deleteSurvey(String smId) {
        Boolean result;
        if (StringUtils.isBlank(smId)) {
            throw new BusinessException(ReturnType.ParamIllegal, "id不能为空");
        }
        SurveyMain surveyMain = surveyMainDao.getById(smId);
        if(null != surveyMain && Constants.SurveyStatus.Ing.equals(surveyMain.getSmType())){
            throw new BusinessException(ReturnType.ServiceError,"进行中的问卷无法删除");
        }

        SurveyMain sm = new SurveyMain();
        sm.setId(smId);
        sm.setIsDelete(Constants.IsDelete.Yes);
        result = surveyMainService.updateById(sm);

        SurveyQuestion sq = new SurveyQuestion();
        sq.setSmId(smId);
        List<SurveyQuestion> sqList = surveyQuestionDao.findList(sq);
        if (null != sqList && !sqList.isEmpty()) {
            List<String> sqIdList = new LinkedList();
            sq.setIsDelete(Constants.IsDelete.Yes);
            sqList.forEach(surveyQuestion -> {
                sq.setId(surveyQuestion.getId());
                surveyQuestionService.updateById(sq);
            });
        }

        return result;
    }


    /**
     * 定时任务 - 更新问卷调查状态
     * @param currentDate 当前时间
     * @return
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean updateSurveyStatusByTask(Long currentDate){
        Boolean result = new Boolean(true);
        List<SurveyMain> smList = surveyMainDao.getNotFinishSurvey();
        if(null != smList && !smList.isEmpty()){
            smList.stream().filter(sm -> null != sm.getSmEndTime() && (sm.getSmEndTime().getTime() <= currentDate)).forEach(surveyMain -> surveyMainDao.updateSurveyStatusToEnd(surveyMain.getId()));
        }
        return result;
    }
    
    public SurveyStatisticsVo getSurveyResult(String smId, Long sqNo, int PageInfoNum, int PageInfoSize) {
        SurveyStatisticsVo statisticsVo = new SurveyStatisticsVo();

        //获取问卷主体信息
        SurveyMain sm = surveyMainDao.getById(smId);
        if(null == sm || !Constants.SurveyStatus.Stop.toString().equals(sm.getSmStatus().toString())){
            throw new BusinessException(ReturnType.ParamIllegal,"问卷调查不存在,或还未结束!");
        }
        BeanUtils.copyProperties(sm,statisticsVo);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
        statisticsVo.setSmEndTime(sdf.format(sm.getSmEndTime()));

        //获取问题信息 sqNo
        SurveyQuestion sq = new SurveyQuestion();
        sq.setSmId(sm.getId());
        sq.setSqNo(sqNo);
        sq.setIsDelete(Constants.FALSE);
        sq.setComCode(UserServletContext.getUserInfo().getComCode());
        sq.setModuCode(UserServletContext.getUserInfo().getModuCode());
        sq = surveyQuestionDao.get(sq);
        if(null == sq){
            throw new BusinessException(ReturnType.DB,"获取问题失败!");
        }
        BeanUtils.copyProperties(sq,statisticsVo);

        //单选,多选 问题统计信息整理
        List<SurveyOptionStatisticsVo> optionStatisticsVoList = new ArrayList<>();
        //文本
        List<SurveyTextAnswerVo> textAnswerVoList = new ArrayList<>();

        //文本分页信息  List<SurveyUserAnswer>
        Page<SurveyUserAnswer> uaPage = new Page<>();

        if(!"2".equals(sq.getSqType().toString())){
            //非文本 //问题选项信息
            List<String> optionList = Arrays.asList(sq.getSoContent().split("@@"));
            if("0".equals(sq.getSqType().toString())){
                //单选 //问题出现的总次数
                Double questionCount = userAnswerStatisticsDao.getCount(sm.getId(),sq.getId(),null);
                SurveyQuestion finalSq = sq;
                optionList.forEach(option -> {
                    SurveyOptionStatisticsVo optionStatistics = new SurveyOptionStatisticsVo();
                    optionStatistics.setSaContent(option);
                    optionStatistics.setSqId(finalSq.getId());
                    if(questionCount == 0.0){
                        optionStatistics.setCount(0D);
                        optionStatistics.setScale(new BigDecimal(0));
                    }else {
                        //获取该选项的被选率
                        Double optionCount = userAnswerStatisticsDao.getCount(sm.getId(), finalSq.getId(),option);
                        optionStatistics.setCount(optionCount);
                        System.out.println(optionCount/questionCount);
                        optionStatistics.setScale(new BigDecimal(optionCount/questionCount));
                    }
                    optionStatisticsVoList.add(optionStatistics);
                });
            }else {
                //多选 //获取这道题有多少人答了
                Double joinCount = userAnswerStatisticsDao.getCountForMulti(sm.getId(),sq.getId());
                SurveyQuestion finalSq = sq;
               // Double optionCounts = userAnswerStatisticsDao.getCount(sm.getId(), finalSq.getId(),option);

                optionList.forEach(option -> {
                    SurveyOptionStatisticsVo optionStatistics = new SurveyOptionStatisticsVo();
                    optionStatistics.setSqId(finalSq.getId());
                    optionStatistics.setSaContent(option);
                    if(0.0 == joinCount){
                        optionStatistics.setScale(new BigDecimal(0));
                        optionStatistics.setCount(0D);
                    }else {
                        //获取该选项的被选率
                        Double optionCount = userAnswerStatisticsDao.getCount(sm.getId(), finalSq.getId(),option);
                        optionStatistics.setCount(optionCount);
                        //Double dd = Double.valueOf(optionStatistics.getSaContent());
                        optionStatistics.setScale(new BigDecimal(optionCount/joinCount));
                    }
                    optionStatisticsVoList.add(optionStatistics);
                });
            }
        }else {
            //文本整理
            SurveyUserAnswer ua = new SurveyUserAnswer();
            ua.setSmId(smId);
            ua.setSqId(sq.getId());
            ua.setIsDelete(Constants.FALSE);
            ua.setComCode(UserServletContext.getUserInfo().getComCode());
            ua.setModuCode(UserServletContext.getUserInfo().getModuCode());
//            PageHelper.startPage(PageInfoNum, PageInfoSize);
            uaPage = new Page<>(PageInfoNum, PageInfoSize);
            uaPage.setList(surveyUserAnswerDao.findList(ua));
            List<SurveyUserAnswer> uaList = uaPage.getList();
            if(null != uaList && !uaList.isEmpty()){
                uaList.forEach(newUa ->{
                    SurveyTextAnswerVo textAnswer = new SurveyTextAnswerVo();
                    BeanUtils.copyProperties(newUa,textAnswer);
                    textAnswer.setSaAnswerTime(sdf.format(newUa.getSaAnswerTime()));
                    textAnswer.setUserAnswerId(newUa.getId());
                    textAnswerVoList.add(textAnswer);
                });
            }
        }
        Page<SurveyTextAnswerVo> textAnswerVoPage = new Page();
        BeanUtils.copyProperties(uaPage,textAnswerVoPage);
        textAnswerVoPage.setList(textAnswerVoList);

        Page<SurveyOptionStatisticsVo> optionStatisticsPage = new Page().setList(optionStatisticsVoList);
        statisticsVo.setSqTopic(sq.getSqTopic());
        statisticsVo.setSqId(sq.getId());
        statisticsVo.setOptionInfoPageInfo(optionStatisticsPage);
        statisticsVo.setTextAnswerVoPageInfo(textAnswerVoPage);
        return statisticsVo;
    }
    
    public Page<SurveyTextAnswerVo> getResultDetail(SurveyStatisticsParam surveyStatisticsParam,
                                                    int PageInfoNum, int PageInfoSize) {
    	Page<SurveyTextAnswerVo> result = new Page<>(PageInfoNum, PageInfoSize);
		
		SurveyUserAnswer sua = new SurveyUserAnswer();
		BeanUtils.copyProperties(surveyStatisticsParam,sua);
		sua.setIsDelete(0L);
		sua.setComCode(UserServletContext.getUserInfo().getComCode());
		sua.setModuCode(UserServletContext.getUserInfo().getModuCode());
		Page<SurveyUserAnswer> page = new Page<>(PageInfoNum, PageInfoSize);
		page.setList(surveyUserAnswerDao.findList(sua));
		BeanUtils.copyProperties(page,result);
		List<SurveyTextAnswerVo> staList = new LinkedList<>();
		if(null != page.getList() && !page.getList().isEmpty()){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
		page.getList().forEach(surveyUserAnswer -> {
		SurveyTextAnswerVo staVo = new SurveyTextAnswerVo();
		BeanUtils.copyProperties(surveyUserAnswer,staVo);
		staVo.setSaAnswerTime(sdf.format(surveyUserAnswer.getSaAnswerTime()));
		staList.add(staVo);
		});
		}
		result.setList(staList);
		return result;
	}

	//根据答题者手机号查出答案表 验重
    public List<SurveyUserAnswer> getPhone(String userPhone,String smId) {
        return userAnswerStatisticsDao.getPhone(userPhone,smId);
    }
}
