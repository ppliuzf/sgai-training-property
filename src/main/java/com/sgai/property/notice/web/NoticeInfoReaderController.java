package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfoReader;
import com.sgai.property.notice.service.NoticeInfoReaderServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfoReader")
public class NoticeInfoReaderController extends BaseController {
    @Autowired
	private NoticeInfoReaderServiceImpl noticeInfoReaderService;

  
	@PostMapping(value="/noticeInfoReaderPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfoReader>> PageList(
                                                    @RequestBody NoticeInfoReader noticeInfoReader,int pageNum,int pageSize) {
        Page<NoticeInfoReader> page=noticeInfoReaderService.findListPage(noticeInfoReader,pageNum,pageSize);
        Response<Page<NoticeInfoReader>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoReaderList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfoReader>> list(@RequestBody NoticeInfoReader noticeInfoReader) {
		List<NoticeInfoReader> noticeInfoReaderList=noticeInfoReaderService.findList(noticeInfoReader);
		Response<List<NoticeInfoReader>> result = new Response<>();
		result.setData(noticeInfoReaderList);
		return result;
	}

	@PostMapping(value="/getNoticeInfoReader")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfoReader> getNoticeInfoReader(@RequestBody NoticeInfoReader noticeInfoReader){
		Response<NoticeInfoReader> result = new Response<>();
		result.setData(noticeInfoReaderService.get(noticeInfoReader));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfoReader> getById(String id) {
		Response<NoticeInfoReader> result = new Response<>();
		result.setData(noticeInfoReaderService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfoReader> save(@RequestBody NoticeInfoReader noticeInfoReader){
        Response<NoticeInfoReader> result = new Response<>();
        noticeInfoReader.setCreateTime(System.currentTimeMillis());
        noticeInfoReaderService.save(noticeInfoReader);
        result.setData(noticeInfoReader);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfoReader> updateById(@RequestBody NoticeInfoReader noticeInfoReader){
        Response<NoticeInfoReader> result = new Response<>();
        noticeInfoReader.setUpdateTime(System.currentTimeMillis());
		noticeInfoReaderService.updateById(noticeInfoReader);
		result.setData(noticeInfoReader);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoReaderService.deleteById(id));
        return result;
	}

}