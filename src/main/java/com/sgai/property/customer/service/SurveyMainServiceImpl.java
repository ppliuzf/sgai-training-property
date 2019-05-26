package com.sgai.property.customer.service;

import com.alibaba.fastjson.JSON;
import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.GetIp;
import com.sgai.property.common.util.NetUtils;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.dao.ISurveyMainDao;
import com.sgai.property.customer.dao.ISurveyQuestionDao;
import com.sgai.property.customer.dao.ISurveyUserAnswerDao;
import com.sgai.property.customer.dao.ISurveyUserInfoDao;
import com.sgai.property.customer.entity.SurveyMain;
import com.sgai.property.customer.entity.SurveyQuestion;
import com.sgai.property.customer.entity.SurveyUserAnswer;
import com.sgai.property.customer.entity.SurveyUserInfo;
import com.sgai.property.customer.vo.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SurveyMainServiceImpl extends MoreDataSourceCrudServiceImpl<ISurveyMainDao, SurveyMain> {

    private static final Logger logger = LogManager.getLogger(SurveyMainServiceImpl.class);

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private TokenServerImpl tokenServer;
    @Autowired
    private CommonServiceImpl commonServiceImpl;
    @Autowired
    private SurveyUserInfoServiceImpl surveyUserInfoServiceImpl;
    @Autowired
    private SurveyServiceImpl surveyService;
    @Autowired
    private SurveyUserAnswerServiceImpl surveyUserAnswerService;

    @Autowired
    private ISurveyQuestionDao surveyQuestionDao;
    @Autowired
    private ISurveyUserAnswerDao surveyUserAnswerDao;
    @Autowired
    private ISurveyMainDao surveyMainDao;
    @Autowired
    private ISurveyUserInfoDao surveyUserInfoDao;


    /**
     * 开始调查
     *
     * @param @param  accessToken
     * @param @param  id
     * @param @return 参数
     * @return Boolean    返回类型
     */
    @Transactional(rollbackFor = BusinessException.class)
    public List<MessageEntity> startSurvey(String id) {
        List<MessageEntity> messageEntityList = new ArrayList<>();

        Boolean flag = false;
        
        SurveyMain survey = surveyMainDao.getById(id);
        Date endDateTime = survey.getSmEndTime();
        Long currTime = System.currentTimeMillis();
        if(currTime > endDateTime.getTime() ){
        	throw new BusinessException(ReturnType.Error, "问卷已结束，不允许修改!");
        }else {

            SurveyMain sm = new SurveyMain();
            sm.setId(id);
            sm.setSmStatus(Constants.SurveyStatus.Ing);
            this.updateById(sm);
        }
        
        /*Response<List<EmpInfo>> response = new Response<>();
        try {
            response = commonsRomeotService.getOrgEveryOne(UserServletContext.getUserInfo().getCompanyId()+"",
                    null,orgAccessId,orgAccessSecret);
            if(null == response.getData()){
                logger.error("--三方返回数据为空-->response:"+JSON.toJSONString(response));
            }
        } catch (Exception e) {
            logger.error("-- 三方接口获取信息失败! findEmpInfoByDeptId()--" + e.getMessage());
            e.printStackTrace();
            throw new BusinessException(ReturnType.ThirdParty, "三方接口获取人员信息失败!");
        }
        List<MessageEntity> messageEntityList = new ArrayList<>();
        List<SurveyUserInfo> surveyUserInfoList = new LinkedList<>();
        
        Date startTime = new Date();
        if (response != null && response.getData() != null) {
            List<EmpInfo> empInfoList = response.getData();
            SurveyMain sm1 = new SurveyMain();
            sm1.setId(id);
            sm1.setSmStartTime(new Date());
            surveyMainDao.updateById(sm1);
            SurveyMain surveyMain = surveyMainDao.getById(id);
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
            List<String> empNameList = Arrays.asList("候林涛","6候林涛","申晓唯","叶子梅","谭婉玲","雷黎","张秋月","赵凡","刘雷");
            empInfoList.stream().filter(empInfo -> empNameList.contains(empInfo.getEiEmpName())).forEach(empInfo -> {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.setToFeedId(empInfo.getFeedId());
                String startDate = surveyMain.getSmStartTime()==null?sdf.format(startTime):sdf.format(surveyMain.getSmStartTime());
                String endDate = surveyMain.getSmEndTime()==null?sdf.format(startTime):sdf.format(surveyMain.getSmEndTime());
                messageEntity.setSubCatalog("问卷调查-测试");
                messageEntity.setMessage(MessageFormat.format(startSurvey, surveyMain.getSmName(), startDate, endDate, surveyMain.getSmCreatorName()));
                messageEntity.setShowHeadFlag(Constants.NO);
                messageEntity.setFinishFlag(0);
                String url = MessageFormat.format(appUrl, id, this.getCode(empInfo.getOrgId(),empInfo.getFeedId()));
                logger.info("-- url -->"+url);
                messageEntity.setToonUrl(url);
                messageEntity.setBizNo(surveyMain.getId() + "-" + empInfo.getFeedId());
                messageEntity.setAppId(appId);
                messageEntity.setAppSecret(appKey);
                messageEntity.setToonUserId(empInfo.getToonUserId() + "");
                messageEntityList.add(messageEntity);

                //保存参与问卷调查用户信息
                SurveyUserInfo surveyUserInfo = new SurveyUserInfo();
                surveyUserInfo.setSmId(id);
                surveyUserInfo.setUserSex(Constants.TRUE);
                surveyUserInfo.setFeedId(empInfo.getFeedId());
                surveyUserInfo.setComId(empInfo.getOrgId() + "");
                surveyUserInfo.setComName(empInfo.getOrgName());
                surveyUserInfo.setUserId(empInfo.getToonUserId() + "");
                surveyUserInfo.setDeptName(empInfo.getDeptName());
                surveyUserInfo.setDeptId(empInfo.getDeptId() + "");
                surveyUserInfo.setUserName(empInfo.getEiEmpName());
                surveyUserInfo.setSuIsFinish(Constants.FALSE);
                surveyUserInfo.setIsDelete(Constants.IsDelete.No);
                surveyUserInfo.setUserAge(0L);
                surveyUserInfo.setSuTime(null);
                surveyUserInfo.setUserDesc("");
                surveyUserInfo.setSuAnswerCount(0L);
                surveyUserInfo.setId(UUID.randomUUID().toString());
                surveyUserInfo.setRemarks("");
                surveyUserInfo.setVersion(1);
                surveyUserInfo.setDelFlag("");
                surveyUserInfo.setUpdatedBy(UserServletContext.getUserInfo().getUserNo() + "");
                surveyUserInfo.setUpdatedDt(startTime);
                surveyUserInfo.setCreatedBy(UserServletContext.getUserInfo().getUserNo() + "");
                surveyUserInfo.setCreatedDt(startTime);
                surveyUserInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                surveyUserInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                surveyUserInfoList.add(surveyUserInfo);
            });
            surveyUserInfoDao.batchInsert(surveyUserInfoList);


            SurveyMain sm = new SurveyMain();
            sm.setId(id);
            sm.setSmStatus(Constants.SurveyStatus.Ing);
            this.updateById(sm);

        } else {
            return null;
        }*/
        return messageEntityList;
    }


    /**
     * 问卷问题详情
     *
     * @param @param  accessToken
     * @param @param  id
     * @param @return 参数
     * @return SurveyMainDetailVO    返回类型
     * @throws
     */
    public SurveyMainDetailVO getSurveyDetail(String id) {

        SurveyMainDetailVO surveyMainDetailVO = new SurveyMainDetailVO();
        SurveyMain surveyMain = surveyMainDao.getById(id);
        BeanUtils.copyProperties(surveyMain,surveyMainDetailVO);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
        surveyMainDetailVO.setSmStartTime(null != surveyMain.getSmStartTime()?sdf.format(surveyMain.getSmStartTime()):"");
        surveyMainDetailVO.setSmEndTime(null != surveyMain.getSmEndTime()?sdf.format(surveyMain.getSmEndTime()):"");
        //设置问题详情
        List<SurveyQuestionVO> surveyQuestionVOs = new ArrayList<>();
        List<SurveyQuestion> surveyQuestions = surveyQuestionDao.getSurveyQuestionsBySmId(id);
        surveyQuestionVOs = BeanMapper.mapList(surveyQuestions, SurveyQuestionVO.class);
        Collections.sort(surveyQuestionVOs);
        surveyMainDetailVO.setSurveyQuestionVOs(surveyQuestionVOs);
        return surveyMainDetailVO;
    }


    /**
     * 答题结束提交
     *
     * @param @param  accessToken
     * @param @param  surveyUserAnswerVOs
     * @param @return 参数
     * @return Boolean    返回类型
     */
    @Transactional(rollbackFor = BusinessException.class)
    public Boolean submitSurvey(HttpServletRequest request, String smId, SurveyUserAnswerPcVO surveyUserAnswerPcVO) {
        //判断问卷的状态
       /* SurveyMain sm = surveyMainDao.getById(smId);
        if(!sm.getSmStatus().toString().equals("1")){
            throw new BusinessException(ReturnType.ParamIllegal,"问卷已经结束");
        }*/

        //判断是否是已经提交过 再编辑的 (0未完成 1完成)
//        Long surveyStatus = surveyService.surveyIsFinish(smId,UserServletContext.getUserInfo().getFeeId());
        Date currentDate = new Date();
        /*if(surveyStatus.equals(1L)){
            //已开始 将原用户答案删除
            SurveyUserAnswer ua = new SurveyUserAnswer();
            ua.setSmId(smId);
            ua.setFeedId(UserServletContext.getUserInfo().getFeeId());
            ua.setUserId(UserServletContext.getUserInfo().getUserNo()+"");
            surveyUserAnswerDao.delete(ua);
        }*/
        //将用户回答表更新
        SurveyUserInfo userInfo = new SurveyUserInfo();
//        userInfo.setFeedId(UserServletContext.getUserInfo().getFeeId());
        userInfo.setSmId(smId);
        userInfo = surveyUserInfoDao.get(userInfo);
        if (userInfo != null) {
            SurveyUserInfo surveyUserInfo = new SurveyUserInfo();
            surveyUserInfo.setSuTime(currentDate);
            surveyUserInfo.setSuIsFinish(1L);
            surveyUserInfo.setId(userInfo.getId());
            surveyUserInfoDao.updateById(surveyUserInfo);
        }


        //插入到用户回答表
        surveyUserAnswerPcVO.getSurveyUserAnswerVOs().stream().filter(surveyUserAnswerVO -> null != surveyUserAnswerVO).forEach(surveyUserAnswerVO -> {
            SurveyUserAnswer surveyUserAnswer = new SurveyUserAnswer();
            BeanUtils.copyProperties(surveyUserAnswerVO,surveyUserAnswer);
            surveyUserAnswer.setUserId(UserServletContext.getUserInfo().getUserNo() + "");
            surveyUserAnswer.setSaAnswerTime(currentDate);
//            surveyUserAnswer.setFeedId(UserServletContext.getUserInfo().getFeeId());
            surveyUserAnswer.setUserPhone(surveyUserAnswerPcVO.getUserPhone());
            surveyUserAnswer.setUserName(StringUtils.isEmpty(surveyUserAnswerPcVO.getUserName())?UserServletContext.getUserInfo().getUserName():surveyUserAnswerPcVO.getUserName());
            surveyUserAnswer.setSource(NetUtils.getOsAndBrowserInfo(request));
            try {
                surveyUserAnswer.setIp(GetIp.getIpAddrs(request));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(surveyUserAnswerVO.getSaType().toString().equals("1")){
                //多选分批插入
                List<String> saContentList = Arrays.asList(surveyUserAnswerVO.getSaContent().split("@@"));
                saContentList.forEach(saContent -> {
                    SurveyUserAnswer sua = new SurveyUserAnswer();
                    BeanUtils.copyProperties(surveyUserAnswer,sua);
                    sua.setSaContent(saContent);
                    surveyUserAnswerService.saveEntity(sua);
                });
            }else {
                surveyUserAnswerService.saveEntity(surveyUserAnswer);
            }

        });

        SurveyMain surveyMain = surveyMainDao.getById(smId);

        //发送消息(覆盖之前的消息)
//        List<MessageEntity> messageEntityList = new ArrayList<>();
//        MessageEntity messageEntity = new MessageEntity();
//        messageEntity.setToFeedId(UserServletContext.getUserInfo().getFeeId());
//        String startDate = DateFormatUtils.format(surveyMain.getSmStartTime(), Constants.DATEFORMAT_YYYYMMDDHHMM);
//        String endDate = DateFormatUtils.format(surveyMain.getSmEndTime(), Constants.DATEFORMAT_YYYYMMDDHHMM);
//        messageEntity.setSubCatalog("问卷调查-测试");
//        messageEntity.setMessage(MessageFormat.format(startSurvey, surveyMain.getSmName(), startDate, endDate, surveyMain.getSmCreatorName()));
//        messageEntity.setShowHeadFlag(Constants.NO);
//        messageEntity.setFinishFlag(1);
////        String url = MessageFormat.format(appUrl,smId,this.getCode(UserServletContext.getUserInfo().getCompanyId(),UserServletContext.getUserInfo().getFeeId()));
//        String url ="";
//        logger.info("-- message_url -->"+url);
//        messageEntity.setToonUrl(url);
//        messageEntity.setBizNo(surveyMain.getId() + "-" + UserServletContext.getUserInfo().getFeeId());
//        messageEntity.setAppId(appId);
//        messageEntity.setAppSecret(appKey);
////        Response<EmpInfo> responseEmpInfo = commonsRomeotService.getEmpInfoByfeedId(UserServletContext.getUserInfo().getCompanyId(), UserServletContext.getUserInfo().getFeeId());
//        logger.info("==== return empInfoDate ===>");
//
////        messageEntity.setToonUserId(responseEmpInfo.getData().getToonUserId() + "");
//        messageEntityList.add(messageEntity);

//        return commonServiceImpl.sendMessage(messageEntityList);
        return true;
    }

    /**
     * 获取code
     * @param feedId
     * @param comId
     * @return
     */
    private String getCode(Long comId,String feedId){
        return commonServiceImpl.findCodeByToken();
    }
    /**
     * 问卷问题详情
     *
     * @param @param  accessToken
     * @param @param  id
     * @param @return 参数
     * @return SurveyMainDetailVO    返回类型
     */
    public SurveyAnswerDetailVO getSurveyAnswerDetail(String id) {
        SurveyAnswerDetailVO surveyAnswerDetailVO = new SurveyAnswerDetailVO();
        SurveyMain surveyMain = surveyMainDao.getById(id);
        surveyAnswerDetailVO = BeanMapper.map(surveyMain, SurveyAnswerDetailVO.class);
        //设置问题答案详情
        List<SurveyQuestionAnswerDetailVO> questionAnswerDetailVOs = new ArrayList<>();
        List<SurveyQuestion> surveyQuestions = surveyQuestionDao.getSurveyQuestionsBySmId(id);
        for (SurveyQuestion surveyQuestion : surveyQuestions) {
            SurveyQuestionAnswerDetailVO surveyQuestionAnswerDetailVO = new SurveyQuestionAnswerDetailVO();
            surveyQuestionAnswerDetailVO = BeanMapper.map(surveyQuestion, SurveyQuestionAnswerDetailVO.class);
            List<SurveyUserAnswer> surveyUserAnswers = surveyUserAnswerDao.getSurveyUserAnswersBySmIdAndSqId(id, surveyQuestion.getId());
            List<String> saContents = new ArrayList<>();
            for (SurveyUserAnswer surveyUserAnswer : surveyUserAnswers) {
                String saContent = surveyUserAnswer.getSaContent();
                saContents.add(saContent);
            }
            //设置问题答案
            surveyQuestionAnswerDetailVO.setSaContent(JSON.toJSONString(saContents));
            questionAnswerDetailVOs.add(surveyQuestionAnswerDetailVO);
        }
//        List<SurveyUserAnswer> surveyUserAnswers = surveyUserAnswerDao.getSurveyUserAnswersBySmId(id); 
        surveyAnswerDetailVO.setSurveyQuestionAnswerDetailVOs(questionAnswerDetailVOs);
        return surveyAnswerDetailVO;
    }


}