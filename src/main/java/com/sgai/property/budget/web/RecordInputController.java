package com.sgai.property.budget.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.budget.entity.Input;
import com.sgai.property.budget.service.InputServiceNewImpl;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author 严浩淼
 *@date 2018年1月19日--上午11:23:08
 */
@Api(description="录入记录")
@RestController
@RequestMapping(value="/recordInput")
public class RecordInputController extends BaseController {

	@Autowired
	private InputServiceNewImpl inputService;
	
	@ApiOperation(value="分页获取录入记录",httpMethod="POST",notes="分页获取录入记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name="recordId",value="计划id",required=true,paramType="query",dataType="String"),
		@ApiImplicitParam(name="pageNum",value="页码",required=true,paramType="query",dataType="Integer"),
		@ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="query",dataType="Integer")
	})
	@PostMapping(value="getPageList")
	public Response<Page<Input>> getPageList(
			String recordId,Integer pageNum,Integer pageSize) {
		Response<Page<Input>> result = new Response<Page<Input>>();
		try {
			Input input =new Input();
			input.setRecordId(recordId);

			Page<Input> page =inputService.getListPage(input, pageNum, pageSize);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
}
