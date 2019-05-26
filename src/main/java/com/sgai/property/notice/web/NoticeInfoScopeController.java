package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfoScope;
import com.sgai.property.notice.service.NoticeInfoScopeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfoScope")
public class NoticeInfoScopeController extends BaseController {
    @Autowired
	private NoticeInfoScopeServiceImpl noticeInfoScopeService;

  
	@PostMapping(value="/noticeInfoScopePageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfoScope>> PageList(
                                                    @RequestBody NoticeInfoScope noticeInfoScope,int pageNum,int pageSize) {
        Page<NoticeInfoScope> page=noticeInfoScopeService.findListPage(noticeInfoScope,pageNum,pageSize);
        Response<Page<NoticeInfoScope>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoScopeList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfoScope>> list(@RequestBody NoticeInfoScope noticeInfoScope) {
		List<NoticeInfoScope> noticeInfoScopeList=noticeInfoScopeService.findList(noticeInfoScope);
		Response<List<NoticeInfoScope>> result = new Response<>();
		result.setData(noticeInfoScopeList);
		return result;
	}

	@PostMapping(value="/getNoticeInfoScope")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfoScope> getNoticeInfoScope(@RequestBody NoticeInfoScope noticeInfoScope){
		Response<NoticeInfoScope> result = new Response<>();
		result.setData(noticeInfoScopeService.get(noticeInfoScope));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfoScope> getById(String id) {
		Response<NoticeInfoScope> result = new Response<>();
		result.setData(noticeInfoScopeService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfoScope> save(@RequestBody NoticeInfoScope noticeInfoScope){
        Response<NoticeInfoScope> result = new Response<>();
        noticeInfoScope.setCreateTime(System.currentTimeMillis());
        noticeInfoScopeService.save(noticeInfoScope);
        result.setData(noticeInfoScope);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfoScope> updateById(@RequestBody NoticeInfoScope noticeInfoScope){
        Response<NoticeInfoScope> result = new Response<>();
        noticeInfoScope.setUpdateTime(System.currentTimeMillis());
		noticeInfoScopeService.updateById(noticeInfoScope);
		result.setData(noticeInfoScope);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoScopeService.deleteById(id));
        return result;
	}

}