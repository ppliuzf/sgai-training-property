package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.InfoDetail;
import com.sgai.property.notice.entity.NoticeInfoParam;
import com.sgai.property.notice.entity.NoticeResponseInfo;
import com.sgai.property.notice.service.NoticeInfoIApproveServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/11/10.
 */
@RestController
@RequestMapping("/noticeInfoIApprove")
@Api(description = "我审核的相关接口")
public class NoticeInfoIApproveController extends BaseController {

    @Autowired
    NoticeInfoIApproveServiceImpl noticeInfoIApproveService;

    @RequestMapping(value="/cminfoPageList", method = RequestMethod.POST)
    @ApiOperation(value = "我审核的列表(搜索)", httpMethod = "POST", notes = "我审核的列表(搜索)")
    public Response<Page<NoticeResponseInfo>> cminfoPageList(
             @RequestBody NoticeInfoParam noticeInfoParam
            , int pageNum
            , int pageSize
    ) {

        Response<Page<NoticeResponseInfo>> page= noticeInfoIApproveService.cminfoPageList(noticeInfoParam, pageNum, pageSize);
        return page;
    }

    @RequestMapping(value="/cminfoDetail", method = RequestMethod.POST)
    @ApiOperation(value = "web资讯详情", httpMethod = "POST", notes = "web资讯详情")
    public Response<InfoDetail> cminfoDetail(

            @RequestParam String id
    ) {

        Response<InfoDetail> page= noticeInfoIApproveService.cminfoDetail(id);
        return page;
    }
}
