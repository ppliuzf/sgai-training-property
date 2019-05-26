package com.sgai.property.quality.web.plan;


import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.plan.PeriodPCServiceImpl;
import com.sgai.property.quality.vo.plan.PeriodParam;
import com.sgai.property.quality.vo.plan.PeriodVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *@author 严浩淼
 *@date 2018年1月6日--上午9:57:03
 */
@RestController
@RequestMapping("/pc/period")
@Api(description = "阶段管理-PC端")
public class PeriodPCController extends BaseController {

	@Autowired
	private PeriodPCServiceImpl periodService;
	
	@PostMapping(value="/periodList")
    @ApiOperation(value = "获取计划的阶段列表", httpMethod = "POST", notes = "获取计划的阶段列表")
	public Response<List<PeriodVo>> PageList( @RequestParam String recordId) {
        Response<List<PeriodVo>> result = new Response<>();
		try {
			PeriodVo periodVo =new PeriodVo();
			periodVo.setRecordId(recordId);
			List<PeriodVo> periodVos = periodService.getPeriodList(periodVo);
			result.setData(periodVos);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("查询数据库异常！");
		}
		return result;
	}

	@ApiOperation(value = "阶段保存", httpMethod = "POST", notes = "阶段保存")
	@PostMapping(value = "/save")
	public Response<Boolean> insert( @RequestBody List<PeriodParam> periodList){
        Response<Boolean> result = new Response<>();
		try {
			Set<String> nameSet =new HashSet<String>();
			//阶段信息判断
			for (PeriodParam param : periodList) {
				nameSet.add(param.getPeriodName());
			}
			if (periodList.size()==0) {
				throw new Exception("阶段信息为空！");
			}else if (periodList.size()!=nameSet.size()) {
				throw new Exception("阶段名称不能重复！");
			}

			Boolean blBoolean=periodService.saveOrUpdate(periodList);
	        result.setData(blBoolean);
		} catch (Exception e) {
			result.setCode("-100");
			result.setMessage(e.getMessage());
		}
        return result;
    }

	@ApiOperation(value = "删除阶段", httpMethod = "POST", notes = "删除阶段")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
		try {

			result.setData(periodService.deletePeriodById(id));
		} catch (Exception e) {
			result.setCode("-101");
			result.setMessage(e.getMessage());
		}
        return result;
	}
}
