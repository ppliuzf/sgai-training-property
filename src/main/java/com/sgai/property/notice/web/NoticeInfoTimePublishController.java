package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfoTimePublish;
import com.sgai.property.notice.service.NoticeInfoTimePublishServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfoTimePublish")
public class NoticeInfoTimePublishController extends BaseController {
    @Autowired
	private NoticeInfoTimePublishServiceImpl noticeInfoTimePublishService;

  
	@PostMapping(value="/noticeInfoTimePublishPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfoTimePublish>> PageList(
                                                    @RequestBody NoticeInfoTimePublish noticeInfoTimePublish,int pageNum,int pageSize) {
        Page<NoticeInfoTimePublish> page=noticeInfoTimePublishService.findListPage(noticeInfoTimePublish,pageNum,pageSize);
        Response<Page<NoticeInfoTimePublish>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoTimePublishList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfoTimePublish>> list(@RequestBody NoticeInfoTimePublish noticeInfoTimePublish) {
		List<NoticeInfoTimePublish> noticeInfoTimePublishList=noticeInfoTimePublishService.findList(noticeInfoTimePublish);
		Response<List<NoticeInfoTimePublish>> result = new Response<>();
		result.setData(noticeInfoTimePublishList);
		return result;
	}

	@PostMapping(value="/getNoticeInfoTimePublish")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfoTimePublish> getNoticeInfoTimePublish(@RequestBody NoticeInfoTimePublish noticeInfoTimePublish){
		Response<NoticeInfoTimePublish> result = new Response<>();
		result.setData(noticeInfoTimePublishService.get(noticeInfoTimePublish));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfoTimePublish> getById(String id) {
		Response<NoticeInfoTimePublish> result = new Response<>();
		result.setData(noticeInfoTimePublishService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfoTimePublish> save(@RequestBody NoticeInfoTimePublish noticeInfoTimePublish){
        Response<NoticeInfoTimePublish> result = new Response<>();
        noticeInfoTimePublish.setCreateTime(System.currentTimeMillis());
        noticeInfoTimePublishService.save(noticeInfoTimePublish);
        result.setData(noticeInfoTimePublish);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfoTimePublish> updateById(@RequestBody NoticeInfoTimePublish noticeInfoTimePublish){
        Response<NoticeInfoTimePublish> result = new Response<>();
        noticeInfoTimePublish.setUpdateTime(System.currentTimeMillis());
		noticeInfoTimePublishService.updateById(noticeInfoTimePublish);
		result.setData(noticeInfoTimePublish);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoTimePublishService.deleteById(id));
        return result;
	}

}