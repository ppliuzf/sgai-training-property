package com.sgai.property.quality.web.plan;


import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.plan.Record;
import com.sgai.property.quality.service.plan.RecordPCServiceImpl;
import com.sgai.property.quality.vo.plan.RecordParam;
import com.sgai.property.quality.vo.plan.RecordParamAdd;
import com.sgai.property.quality.vo.plan.RecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 *@author 严浩淼
 *@date 2018年1月6日--上午9:57:03
 */
@RestController
@RequestMapping("/pc/plan")
@Api(description = "计划管理-PC端")
public class RecordPCController extends BaseController {

	@Autowired
	private RecordPCServiceImpl recordService;
	@Autowired
	private ITaskDao taskDao;

	@PostMapping(value="/planPageList")
    @ApiOperation(value = "根据条件获取计划分页列表", httpMethod = "POST", notes = "根据条件获取计划分页列表")
	public Response<Page<RecordVo>> PageList(
											 @RequestBody RecordParam param,int pageNum, int pageSize) {
        Response<Page<RecordVo>> result = new Response<>();

		try {
			Page<RecordVo> page = recordService.getListPageByParam(param, pageNum, pageSize);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("查询数据库异常！");
		}
		return result;
	}

	@PostMapping(value="/getById")
	@ApiOperation(value = "获取计划详情", httpMethod = "POST", notes = "获取计划详情")
	public Response<RecordVo> getById(String id) {
		Response<RecordVo> result = new Response<>();
		RecordVo recordVo =recordService.getRecordDetails(id);
		result.setData(recordVo);
		return result;
	}

	@ApiOperation(value = "新增计划", httpMethod = "POST", notes = "新增计划")
	@PostMapping(value = "/insert")
	public Response<Record> insert(
								   @RequestBody RecordParamAdd param){
        Response<Record> result = new Response<>();
		try {
			if (StringUtils.isEmpty(param.getRecordName())) {
				throw new Exception("计划名称不能为空");
			}

			Record record =recordService.saveRecord(param);
	        result.setData(record);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage(e.getMessage());
		}
        return result;
    }



	@ApiOperation(value = "删除计划关联模板", httpMethod = "POST", notes = "删除任务模板")
	@RequestMapping(value = "/deletePlanById", method = RequestMethod.POST)
	public Response<Integer> deletePlanById(
											String recordId, String templateId) {
		Response<Integer> response = new Response<>();
		//templateId 是 task表主键值
//		Task spring = new Task();
//		spring.setId(templateId);
		response.setData(taskDao.deleteById(templateId));
		return response;
	}




	/*@ApiOperation(value = "更新计划", httpMethod = "POST", notes = "更新计划")
	@PostMapping(value = "/updateById")
	public Response<Record> updateById(@RequestBody Record type){

        Response<Record> result = new Response<>();
        type.setUpdateTime(System.currentTimeMillis());
        recordService.updateById(type);
		result.setData(type);
		return result;
	 }

	@ApiOperation(value = "删除计划", httpMethod = "POST", notes = "删除计划")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
		try {

	        Record type =recordService.getById(id);
	        if (type!=null) {
	        	if (0==type.getTypeCode()) {
					throw new Exception("预算类为默认计划，不可删除!");
				}
		        type.setIsDelete(1L);
		        type.setUpdateTime(System.currentTimeMillis());
		        result.setData(recordService.updateById(type));
			}else {
				throw new Exception("数据库操作失败！");
			}
		} catch (Exception e) {
			result.setCode("-101");
			result.setMessage(e.getMessage());
		}
        return result;
	}*/
}
