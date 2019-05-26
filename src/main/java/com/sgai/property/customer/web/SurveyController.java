package com.sgai.property.customer.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.entity.SurveyMain;
import com.sgai.property.customer.entity.SurveyUserAnswer;
import com.sgai.property.customer.service.CommonServiceImpl;
import com.sgai.property.customer.service.SurveyMainServiceImpl;
import com.sgai.property.customer.service.SurveyServiceImpl;
import com.sgai.property.customer.vo.*;
import com.sgai.property.customer.vo.survey.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 问卷调查相关接口
 *
 * @author hou
 * @create 2018年3月1日11:33:10
 */
@RestController
@RequestMapping("/survey")
@Api(description = "问卷调查相关接口")
public class SurveyController extends BaseController {
    @Autowired
    private SurveyMainServiceImpl surveyMainService;
    @Autowired
    private SurveyServiceImpl surveyService;
    @Autowired
    private CommonServiceImpl commonServiceImpl;


    @ApiOperation(value = "查询问卷详情列表-分页", httpMethod = "POST", notes = "查询问卷详情列表-分页")
    @RequestMapping(value = "/getSurveyPage",method = RequestMethod.POST)
    public Response<Page<SurveyMainVo>> PageList(
                                                 @RequestBody SurveyMainDto dto, int pageNum, int pageSize) {
        SurveyMain surveyMain = new SurveyMain();
        surveyMain.setSmName(StringUtils.isNotBlank(dto.getSmName())?dto.getSmName().trim():null);
        surveyMain.setSmStatus(dto.getSmStatus().equals(-1L)?null:dto.getSmStatus());
        surveyMain.setIsDelete(Constants.IsDelete.No);
        List<SurveyMainVo> voList = new ArrayList<>();
        Page<SurveyMain> surveyMainPage = surveyMainService.findListPage(surveyMain, pageNum, pageSize);
        Page<SurveyMainVo> page = new Page<>();
        if(null != surveyMainPage.getList() && surveyMainPage.getList().size()>0){
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMM);
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDD);
            surveyMainPage.getList().forEach(sm -> {
                SurveyMainVo smVo = new SurveyMainVo();
                BeanUtils.copyProperties(sm,smVo);
                smVo.setSmId(sm.getId());
                smVo.setSmStartTime(null == sm.getSmStartTime()?"":sdf.format(sm.getSmStartTime()));
                smVo.setSmEndTime(null == sm.getSmEndTime()?"":sdf.format(sm.getSmEndTime()));
                smVo.setCreatedDt(sdf1.format(sm.getCreatedDt()));
                voList.add(smVo);
            });
        }
        BeanUtils.copyProperties(surveyMainPage,page);
        page.setList(voList);
        Response<Page<SurveyMainVo>> result = new Response<>();
        result.setData(page);
        return result;
    }

    @ApiOperation(value = "查询问卷详情 - app端专用", httpMethod = "POST", notes = "查询问卷详情 - app端专用")
    @RequestMapping(value = "/getDetailById",method = RequestMethod.POST)
    public Response<SurveyInfo> getDetailById( String smId) {

        Response<SurveyInfo> result = new Response<>();
        result.setData(surveyService.getDetailById(smId));
        return result;
    }

    @ApiOperation(value = "新建问卷调查", httpMethod = "POST", notes = "新建问卷调查")
    @RequestMapping(value = "/createSurvey",method = RequestMethod.POST)
    public Response<Boolean> createSurvey( @RequestBody SurveyParam surveyParam) {
        Response<Boolean> result = new Response<>();

        result.setData(surveyService.createSurvey(surveyParam));
        return result;
    }

    @ApiOperation(value = "开始调查", httpMethod = "POST", notes = "开始调查")
    @RequestMapping(value = "/startSurvey",method = RequestMethod.POST)
    public Response<Boolean> startSurvey( String id) {
        Response<Boolean> result = new Response<>();
        List<MessageEntity> messageEntityList = surveyMainService.startSurvey(id);
        if(null != messageEntityList && !messageEntityList.isEmpty()){
            result.setData(commonServiceImpl.sendMessage(surveyMainService.startSurvey(id)));
        }else{
            result.setData(false);
        }
        return result;
    }

    @ApiOperation(value = "手动结束问卷调查", httpMethod = "POST", notes = "手动结束问卷调查")
    @RequestMapping(value = "/stopSurvey",method = RequestMethod.POST)
    public Response<Boolean> stopSurvey( @RequestParam("smId")String smId) {
        Response<Boolean> result = new Response<>();
        result.setData(surveyService.stopSurvey(smId));
        return result;
    }
    
    @ApiOperation(value = "问卷问题详情-pc端专用", httpMethod = "POST", notes = "问题详情-pc端专用")
    @RequestMapping(value = "/getSurveyDetail",method = RequestMethod.POST)
    public Response<SurveyMainDetailVO> getSurveyQuestion( String id) {
        Response<SurveyMainDetailVO> result = new Response<>();
        SurveyMainDetailVO surveyMainDetailVO = surveyMainService.getSurveyDetail(id);
        result.setData(surveyMainDetailVO);
        return result;
    }
    
    @ApiOperation(value = "调查问卷答题提交 -app端", httpMethod = "POST", notes = "调查问卷答题提交 -app端")
    @RequestMapping(value = "/submitSurvey",method = RequestMethod.POST)
    public Response<Boolean> submitSurvey(
                                          @RequestBody SurveyUserAnswerPcVO surveyUserAnswerPcVO){


        Response<Boolean> result = new Response<>();


        List<SurveyUserAnswer> getphone =  surveyService.getPhone(surveyUserAnswerPcVO.getUserPhone(),surveyUserAnswerPcVO.getSurveyUserAnswerVOs().get(0).getSmId());
        if(getphone.size() >0){
            result.setCode("0");
            result.setMessage("该手机号以参与过答题");
            result.setData(false);
            return result;
        }
        Boolean flag = surveyMainService.submitSurvey(request, surveyUserAnswerPcVO.getSurveyUserAnswerVOs().get(0).getSmId(),surveyUserAnswerPcVO);
        result.setData(flag);
        return result;
    }
    
    @ApiOperation(value = "调查问卷答题详情", httpMethod = "POST", notes = "调查问卷答题详情")
    @RequestMapping(value = "/getSurveyAnswerDetail",method = RequestMethod.POST)
    public Response<SurveyAnswerDetailVO> getSurveyAnswerDetail( String id) {
        Response<SurveyAnswerDetailVO> result = new Response<>();
        SurveyAnswerDetailVO surveyMainDetailVO = surveyMainService.getSurveyAnswerDetail(id);
        result.setData(surveyMainDetailVO);
        return result;
    }

    @ApiOperation(value = "编辑问卷调查", httpMethod = "POST", notes = "通过ID编辑问卷调查")
    @RequestMapping(value = "/updateSurvey",method = RequestMethod.POST)
    public Response<Boolean> updateSurvey( @RequestBody SurveyParam surveyParam) {
        Response<Boolean> result = new Response<>();

        result.setData(surveyService.updateSurvey(surveyParam));
        return result;
    }

    @ApiOperation(value = "删除问卷调查", httpMethod = "POST", notes = "删除问卷调查")
    @RequestMapping(value = "/deleteSurvey",method = RequestMethod.POST)
    public Response<Boolean> deleteSurvey( @RequestParam("smId")String smId) {
        Response<Boolean> result = new Response<>();
        result.setData(surveyService.deleteSurvey(smId));
        return result;
    }

    @ApiOperation(value = "问卷调查task任务-手动触发", httpMethod = "POST", notes = "问卷调查task任务-手动触发")
    @RequestMapping(value = "/surveyTask",method = RequestMethod.POST)
    public Response<Boolean> surveyTask() {
        Response<Boolean> result = new Response<>();
        result.setData(surveyService.updateSurveyStatusByTask(System.currentTimeMillis()));
        return result;
    }

    @ApiOperation(value = "获取问卷调查结果 - pc端", httpMethod = "POST", notes = "获取问卷调查结果 - pc端")
    @RequestMapping(value = "/getSurveyResult",method = RequestMethod.POST)
    public Response<SurveyStatisticsVo> getSurveyResult(
                                                        @RequestParam("smId")String smId, @RequestParam("sqNo")Long sqNo,
                                                        int PageInfoNum, int PageInfoSize) {

        Response<SurveyStatisticsVo> result = new Response<>();
        result.setData(surveyService.getSurveyResult(smId,sqNo,PageInfoNum,PageInfoSize));
        return result;
    }
    
    @ApiOperation(value = "获取调查结果详情(选择) - pc端", httpMethod = "POST", notes = "获取调查结果详情 - pc端")
    @RequestMapping(value = "/getResultDetail",method = RequestMethod.POST)
    public Response<Page<SurveyTextAnswerVo>> getResultDetail(
                                                              @RequestBody SurveyStatisticsParam surveyStatisticsParam,
                                                              @RequestParam int PageInfoNum, @RequestParam int PageInfoSize) {

        Response<Page<SurveyTextAnswerVo>> result = new Response<>();
        result.setData(surveyService.getResultDetail(surveyStatisticsParam,PageInfoNum,PageInfoSize));
        return result;
    }

}