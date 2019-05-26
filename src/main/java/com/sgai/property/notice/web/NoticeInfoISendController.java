package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.InfoDetail;
import com.sgai.property.notice.entity.NoticeInfoParam;
import com.sgai.property.notice.entity.NoticeResponseInfo;
import com.sgai.property.notice.service.NoticeInfoISendServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/11/10.
 */
@RestController
@RequestMapping("/noticeInfoISend")
@Api(description = "我发起的相关接口")
public class NoticeInfoISendController extends BaseController {
    @Autowired
    NoticeInfoISendServiceImpl noticeInfoISendService;

    @RequestMapping(value="/cminfoPageList", method = RequestMethod.POST)
    @ApiOperation(value = "我发起的列表(搜索)", httpMethod = "POST", notes = "我发起的列表(搜索)")
    public Response<Page<NoticeResponseInfo>> cminfoPageList(

             @RequestBody NoticeInfoParam noticeInfoParam
            , int pageNum
            , int pageSize
    ) {

        Response<Page<NoticeResponseInfo>> page=noticeInfoISendService.cmInfoPageList(noticeInfoParam, pageNum,pageSize);
        return page;
    }


    @RequestMapping(value="/cminfoDetail", method = RequestMethod.POST)
    @ApiOperation(value = "web资讯详情", httpMethod = "POST", notes = "web资讯详情")
    public Response<InfoDetail> cminfoDetail(

            @RequestParam String id
    ) {
        Response<InfoDetail> page=noticeInfoISendService.cminfoDetail(id);
        return page;
    }
}
