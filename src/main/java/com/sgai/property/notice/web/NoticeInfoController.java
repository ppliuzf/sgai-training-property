package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfo;
import com.sgai.property.notice.service.NoticeInfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfo")
public class NoticeInfoController extends BaseController {
    @Autowired
	private NoticeInfoServiceImpl noticeInfoService;

  
	@PostMapping(value="/noticeInfoPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfo>> PageList(
                                                    @RequestBody NoticeInfo noticeInfo,int pageNum,int pageSize) {
        Page<NoticeInfo> page=noticeInfoService.findListPage(noticeInfo,pageNum,pageSize);
        Response<Page<NoticeInfo>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfo>> list(@RequestBody NoticeInfo noticeInfo) {
		List<NoticeInfo> noticeInfoList=noticeInfoService.findList(noticeInfo);
		Response<List<NoticeInfo>> result = new Response<>();
		result.setData(noticeInfoList);
		return result;
	}

	@PostMapping(value="/getNoticeInfo")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfo> getNoticeInfo(@RequestBody NoticeInfo noticeInfo){
		Response<NoticeInfo> result = new Response<>();
		result.setData(noticeInfoService.get(noticeInfo));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfo> getById(String id) {
		Response<NoticeInfo> result = new Response<>();
		result.setData(noticeInfoService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfo> save(@RequestBody NoticeInfo noticeInfo){
        Response<NoticeInfo> result = new Response<>();
        noticeInfo.setCreateTime(System.currentTimeMillis());
        noticeInfoService.save(noticeInfo);
        result.setData(noticeInfo);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfo> updateById(@RequestBody NoticeInfo noticeInfo){
        Response<NoticeInfo> result = new Response<>();
        noticeInfo.setUpdateTime(System.currentTimeMillis());
		noticeInfoService.updateById(noticeInfo);
		result.setData(noticeInfo);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoService.deleteById(id));
        return result;
	}

}