package com.sgai.property.notice.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.NoticeInfoReceipt;
import com.sgai.property.notice.service.NoticeInfoReceiptServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticeInfoReceipt")
public class NoticeInfoReceiptController extends BaseController {
    @Autowired
	private NoticeInfoReceiptServiceImpl noticeInfoReceiptService;

  
	@PostMapping(value="/noticeInfoReceiptPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<NoticeInfoReceipt>> PageList(
                                                    @RequestBody NoticeInfoReceipt noticeInfoReceipt,int pageNum,int pageSize) {
        Page<NoticeInfoReceipt> page=noticeInfoReceiptService.findListPage(noticeInfoReceipt,pageNum,pageSize);
        Response<Page<NoticeInfoReceipt>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/noticeInfoReceiptList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<NoticeInfoReceipt>> list(@RequestBody NoticeInfoReceipt noticeInfoReceipt) {
		List<NoticeInfoReceipt> noticeInfoReceiptList=noticeInfoReceiptService.findList(noticeInfoReceipt);
		Response<List<NoticeInfoReceipt>> result = new Response<>();
		result.setData(noticeInfoReceiptList);
		return result;
	}

	@PostMapping(value="/getNoticeInfoReceipt")
	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
    public Response<NoticeInfoReceipt> getNoticeInfoReceipt(@RequestBody NoticeInfoReceipt noticeInfoReceipt){
		Response<NoticeInfoReceipt> result = new Response<>();
		result.setData(noticeInfoReceiptService.get(noticeInfoReceipt));
		return result;
    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<NoticeInfoReceipt> getById(String id) {
		Response<NoticeInfoReceipt> result = new Response<>();
		result.setData(noticeInfoReceiptService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<NoticeInfoReceipt> save(@RequestBody NoticeInfoReceipt noticeInfoReceipt){
        Response<NoticeInfoReceipt> result = new Response<>();
        noticeInfoReceipt.setCreateTime(System.currentTimeMillis());
        noticeInfoReceiptService.save(noticeInfoReceipt);
        result.setData(noticeInfoReceipt);
        return result;
    }

	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
	@PostMapping(value = "/updateById")
	public Response<NoticeInfoReceipt> updateById(@RequestBody NoticeInfoReceipt noticeInfoReceipt){
        Response<NoticeInfoReceipt> result = new Response<>();
        noticeInfoReceipt.setUpdateTime(System.currentTimeMillis());
		noticeInfoReceiptService.updateById(noticeInfoReceipt);
		result.setData(noticeInfoReceipt);
		return result;
	 }

	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
        result.setData(noticeInfoReceiptService.deleteById(id));
        return result;
	}

}