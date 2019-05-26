package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfoScopeEmp;
import com.sgai.property.notice.service.NoticeInfoScopeEmpServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfoScopeEmp")
public class NoticeInfoScopeEmpController extends BaseController {
    @Autowired
	private NoticeInfoScopeEmpServiceImpl noticeInfoScopeEmpService;

  
	@PostMapping(value="/noticeInfoScopeEmpPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfoScopeEmp>> PageList(
                                                    @RequestBody NoticeInfoScopeEmp noticeInfoScopeEmp,int pageNum,int pageSize) {
        Page<NoticeInfoScopeEmp> page=noticeInfoScopeEmpService.findListPage(noticeInfoScopeEmp,pageNum,pageSize);
        Response<Page<NoticeInfoScopeEmp>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoScopeEmpList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfoScopeEmp>> list(@RequestBody NoticeInfoScopeEmp noticeInfoScopeEmp) {
		List<NoticeInfoScopeEmp> noticeInfoScopeEmpList=noticeInfoScopeEmpService.findList(noticeInfoScopeEmp);
		Response<List<NoticeInfoScopeEmp>> result = new Response<>();
		result.setData(noticeInfoScopeEmpList);
		return result;
	}

	@PostMapping(value="/getNoticeInfoScopeEmp")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfoScopeEmp> getNoticeInfoScopeEmp(@RequestBody NoticeInfoScopeEmp noticeInfoScopeEmp){
		Response<NoticeInfoScopeEmp> result = new Response<>();
		result.setData(noticeInfoScopeEmpService.get(noticeInfoScopeEmp));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfoScopeEmp> getById(String id) {
		Response<NoticeInfoScopeEmp> result = new Response<>();
		result.setData(noticeInfoScopeEmpService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfoScopeEmp> save(@RequestBody NoticeInfoScopeEmp noticeInfoScopeEmp){
        Response<NoticeInfoScopeEmp> result = new Response<>();
        noticeInfoScopeEmp.setCreateTime(System.currentTimeMillis());
        noticeInfoScopeEmpService.save(noticeInfoScopeEmp);
        result.setData(noticeInfoScopeEmp);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfoScopeEmp> updateById(@RequestBody NoticeInfoScopeEmp noticeInfoScopeEmp){
        Response<NoticeInfoScopeEmp> result = new Response<>();
        noticeInfoScopeEmp.setUpdateTime(System.currentTimeMillis());
		noticeInfoScopeEmpService.updateById(noticeInfoScopeEmp);
		result.setData(noticeInfoScopeEmp);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoScopeEmpService.deleteById(id));
        return result;
	}

}