package com.sgai.property.notice.web;

import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.InfoDetail;
import com.sgai.property.notice.entity.NoticeInfoParam;
import com.sgai.property.notice.entity.NoticeResponseInfo;
import com.sgai.property.notice.service.NoticeInfoAppIReceivedServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/11/10.
 */
@RestController
@RequestMapping("/noticeInfoAppIReceive")
@Api(description = "App我收到的相关接口")
public class NoticeInfoAppIReceivedController extends BaseController {
    @Autowired
    NoticeInfoAppIReceivedServiceImpl noticeInfoAppIReceivedService;

    @RequestMapping(value="/appInfoPageList", method = RequestMethod.POST)
    @ApiOperation(value = "App我收到的列表", httpMethod = "POST", notes = "App我收到的列表")
    public Response<List<NoticeResponseInfo>> appInfoPageList(

             @RequestBody NoticeInfoParam noticeInfoParam
            , int pageNum
            , int pageSize
    ) {

        Response<List<NoticeResponseInfo>> page=noticeInfoAppIReceivedService.appInfoPageList(noticeInfoParam, pageNum,pageSize);
        return page;
    }


    @RequestMapping(value="/appInfoDetail", method = RequestMethod.POST)
    @ApiOperation(value = "App资讯详情", httpMethod = "POST", notes = "App资讯详情")
    public Response<InfoDetail> appInfoDetail(

            @RequestParam String id
    ) {
        Response<InfoDetail> page=noticeInfoAppIReceivedService.appInfoDetail(id);
        return page;
    }
}
