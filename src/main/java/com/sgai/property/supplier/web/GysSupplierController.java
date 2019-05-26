package com.sgai.property.supplier.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.supplier.dao.IGysSupplierDaoMapper;
import com.sgai.property.supplier.entity.GysFile;
import com.sgai.property.supplier.entity.GysLevel;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.service.GysFileServiceImpl;
import com.sgai.property.supplier.service.GysLevelServiceImpl;
import com.sgai.property.supplier.service.GysSupplierServiceImpl;
import com.sgai.property.supplier.service.inteface.SupplierService;
import com.sgai.property.supplier.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@Api(description = "供应商")
public class GysSupplierController extends BaseController {
    @Autowired
	private GysSupplierServiceImpl gysSupplierService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private GysFileServiceImpl fileService;
    @Autowired
	private GysLevelServiceImpl levelService;
    @Autowired
    private IGysSupplierDaoMapper supplierDaoMapper;
	@Autowired
	private CtlComRuleService ctlComRuleService;

	@Value("${mat.comCode}")
	private String comCode;
	@Value("${mat.sequCode}")
	private String sequCode;


  
	@PostMapping(value="/supplierPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<SupplierVO>> PageList(
											   @RequestBody SupplierParams supplierParams,
											   @RequestParam(value = "pageNum",required = true) int pageNum,
											   @RequestParam(value = "pageSize",required = true) int pageSize) {


		if (pageNum ==0 || pageSize==0) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
        Response<Page<SupplierVO>> result = new Response<>();
		Page<SupplierVO> page=supplierService.findList(supplierParams,pageNum,pageSize);
        result.setData(page);
		return result;
	}

	@ApiOperation(value = "删除单个对象", httpMethod = "POST", notes = "删除单个对象")
	@PostMapping(value = "/deleteById")
	public Response<ResultMessage> deleteById(
										      @RequestParam(value = "id",required = true)String id){
		Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		boolean flag = gysSupplierService.deleteById(id);
		GysFile file = new GysFile();
		file.setSupplierId(id);
		fileService.delete(file);
		if (flag==true) {
			resultMessage.setCode("0");
			resultMessage.setMessage("删除成功!");
			result.setData(resultMessage);
		}else {
			resultMessage.setCode("-1");
			resultMessage.setMessage("删除失败!");
			result.setData(resultMessage);
		}
		return result;
	}

	@ApiOperation(value = "批量删除", httpMethod = "POST", notes = "批量删除")
	@PostMapping(value = "/deleteByIds")
	public Response<ResultMessage> deleteByIds(
										       @RequestParam(value = "ids",required = true)String[] ids){
		Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		if (null == ids || ids.length==0) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		boolean flag = gysSupplierService.deleteByIds(ids);
		for (int i=0;i<ids.length;i++){
			GysFile file = new GysFile();
			file.setSupplierId(ids[i]);
			fileService.delete(file);
		}
		if (flag==true) {
			resultMessage.setCode("0");
			resultMessage.setMessage("删除成功!");
			result.setData(resultMessage);
		}else {
			resultMessage.setCode("-1");
			resultMessage.setMessage("删除失败!");
			result.setData(resultMessage);
		}
		return result;
	}

	@PostMapping(value="/getById")
	@ApiOperation(value = "查看详情", httpMethod = "POST", notes = "查看详情")
	public Response<SupplierDetailsVO> getById(
											   @RequestParam(value = "id",required = true) String id) {
		Response<SupplierDetailsVO> result = new Response<>();

		SupplierDetailsVO supplierDetailsVO = supplierService.getSupplierDetails(id);
		GysLevel gysLevel = levelService.getById(supplierDetailsVO.getLevelId());
		if (gysLevel !=null) {
			supplierDetailsVO.setLevelId(gysLevel.getId());
			supplierDetailsVO.setLevelName(gysLevel.getName());
		}
		result.setData(supplierDetailsVO);
		return result;
	}

	@ApiOperation(value = "新增供应商", httpMethod = "POST", notes = "新增供应商")
	@PostMapping(value = "/saveSupplier")
	public Response<ResultMessage> saveSupplier(
											    @RequestBody GysSupplierParams gysSupplierParams){
		Response<ResultMessage> result = new Response<>();

		ResultMessage resultMessage = new ResultMessage();
		//获取供应商编码
		String num = ctlComRuleService.getNext(comCode, sequCode);
		if (num == null || "".equals(num)) {
			resultMessage.setCode("-1");
			resultMessage.setMessage("获取供应商编码失败!");
			result.setData(resultMessage);
			return result;
		}

		GysSupplier supplier = new GysSupplier();
		supplier.setName(gysSupplierParams.getName());
		supplier.setComCode(UserServletContext.getUserInfo().getComCode());
		GysSupplier gysSupplier = supplierDaoMapper.getSupperlierByName(supplier);
		if (gysSupplier !=null){
			resultMessage.setCode("3");
			resultMessage.setMessage("供应商名称重复,请重新输入!");
			result.setData(resultMessage);
			return result;
		}
		
		gysSupplierParams.setNo(num);
		gysSupplierService.save(gysSupplierParams);
		resultMessage.setCode("0");
		resultMessage.setMessage("保存成功!");
		result.setData(resultMessage);
		return result;
	}

	@ApiOperation(value = "更新保存", httpMethod = "POST", notes = "更新保存")
	@PostMapping(value = "/updateById")
	public Response<ResultMessage> updateById(
										      @RequestBody SupplierDetailsVO supplierDetailsVO){
		Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		GysSupplier supplier = new GysSupplier();
		supplier.setName(supplierDetailsVO.getName());
		GysSupplier gysSupplier = supplierDaoMapper.getSupperlierByName(supplier);
		if (gysSupplier !=null && !gysSupplier.getId().equals(supplierDetailsVO.getId())) {
			resultMessage.setCode("3");
			resultMessage.setMessage("供应商名称重复,请重新输入!");
			result.setData(resultMessage);
			return result;
		}
		boolean flag = supplierService.updateById(supplierDetailsVO);
		if (flag==true) {
			resultMessage.setCode("0");
			resultMessage.setMessage("更新成功!");
			result.setData(resultMessage);
			return result;
		}else {
			resultMessage.setCode("-1");
			resultMessage.setMessage("更新失败!");
			result.setData(resultMessage);
			return result;
		}

	}


	@ApiOperation(value = "是否启用:是否启用: 1启用; 0禁用", httpMethod = "POST", notes = "是否启用:是否启用: 1启用; 0禁用")
	@PostMapping(value = "/isEnabled")
	public Response<ResultMessage> isEnabled(
											 @RequestParam(value = "id",required = true) String id,
											 @RequestParam(value = "statusId",required = true) Long statusId){
		Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		GysSupplier supplier = new GysSupplier();
		supplier.setId(id);
		supplier.setIsEnabled(statusId);
		supplier.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
		boolean flag = gysSupplierService.updateById(supplier);
		if (flag==true) {
			resultMessage.setCode("0");
			resultMessage.setMessage("成功!");
			result.setData(resultMessage);
		}else {
			resultMessage.setCode("-1");
			resultMessage.setMessage("失败!");
			result.setData(resultMessage);
		}
		return result;
	}




}