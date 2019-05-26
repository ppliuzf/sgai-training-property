package com.sgai.property.contract.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.contract.entity.HtContract;
import com.sgai.property.contract.entity.HtFile;
import com.sgai.property.contract.service.ContractHtContractServiceImpl;
import com.sgai.property.contract.service.HtFileServiceImpl;
import com.sgai.property.contract.service.inteface.IContractService;
import com.sgai.property.contract.service.inteface.SupplierService;
import com.sgai.property.contract.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
@Api(description = "合同")
public class HtContractController extends BaseController {
	private static final Logger logger = LogManager.getLogger(HtContractController.class);
	@Autowired
	private SupplierService supplierService;
    @Autowired
	private ContractHtContractServiceImpl htContractService;
    @Autowired
    private HtFileServiceImpl fileService;
    @Autowired
    private IContractService contractService;
	
	@PostMapping(value="/supplierPageList")
	@ApiOperation(value = "分页查询供应商列表", httpMethod = "POST", notes = "分页查询供应商列表")
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

	@PostMapping(value="/findContractPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<HtContractVO>> PageList(
												 @RequestBody ContractSearchParams contractSearchParams,
												 @RequestParam(value = "pageNum",required = true) int pageNum,
												 @RequestParam(value = "pageSize",required = true) int pageSize) {
        Response<Page<HtContractVO>> result = new Response<>();


		if (pageNum ==0 || pageSize==0) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		Page<HtContractVO> page=contractService.findListPage(contractSearchParams,pageNum,pageSize);
        result.setData(page);
		return result;
	}

	@PostMapping(value="/getById")
	@ApiOperation(value = "查询对象", httpMethod = "POST", notes = "查询对象")
	public Response<HtContractVO> getById(
										  @RequestParam(value = "id",required = true) String id) {
		Response<HtContractVO> result = new Response<>();

		if (null == id) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		result.setData(contractService.getById(id));
		return result;
	}


	@ApiOperation(value = "新增保存", httpMethod = "POST", notes = "新增保存")
	@PostMapping(value = "/save")
	public Response<ResultMessage> save(
									 @RequestBody HtContractVO htContractVO){
        Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

        if (null == htContractVO) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		HtContract contract = contractService.getContractByName(htContractVO);
        if (null != contract) {
			resultMessage.setCode(3);
			resultMessage.setMessage("合同名称重复,请重新输入!");
			result.setData(resultMessage);
			return result;
		}else {
			contractService.save(htContractVO);
			resultMessage.setCode(0);
			resultMessage.setMessage("保存成功!");
			result.setData(resultMessage);
			return result;
		}
    }

	@ApiOperation(value = "保存编辑", httpMethod = "POST", notes = "保存编辑")
	@PostMapping(value = "/updateById")
	public Response<ResultMessage> updateById(
										@RequestBody HtContractVO htContractVO){
        Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		if (null == htContractVO) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		if (StringUtils.isEmpty(htContractVO.getId())) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		HtContract contract = contractService.getContractByName(htContractVO);
		if (null != contract && !contract.getId().equals(htContractVO.getId())) {
			resultMessage.setCode(3);
			resultMessage.setMessage("合同名称重复,请重新输入!");
			result.setData(resultMessage);
			return result;
		}else {
			Boolean flag = contractService.updateById(htContractVO);
			if (flag==true) {
				resultMessage.setCode(0);
				resultMessage.setMessage("保存成功!");
				result.setData(resultMessage);
				return result;
			}else {
				resultMessage.setCode(-1);
				resultMessage.setMessage("保存失败!");
				result.setData(resultMessage);
				return result;
			}
		}

	 }

	@ApiOperation(value = "删除", httpMethod = "POST", notes = "删除")
	@PostMapping(value = "/deleteById")
    public Response<ResultMessage> deleteById(
											  @RequestParam(value = "id",required = true) String id){
        Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		if (null == id) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}else {
			Boolean flag =htContractService.deleteById(id);
			HtFile file = new HtFile();
			file.setContractId(id);
			fileService.delete(file);
			if (flag==true){
				resultMessage.setCode(0);
				resultMessage.setMessage("删除成功!");
				result.setData(resultMessage);
				return result;
			}else {
				resultMessage.setCode(-1);
				resultMessage.setMessage("删除失败!");
				result.setData(resultMessage);
				return result;
			}
		}
	}


	@ApiOperation(value = "批量删除", httpMethod = "POST", notes = "批量删除")
	@PostMapping(value = "/deleteByIds")
	public Response<ResultMessage> deleteByIds(
											  @RequestParam(value = "ids",required = true) String[] ids){
		Response<ResultMessage> result = new Response<>();
		ResultMessage resultMessage = new ResultMessage();

		if (ids.length==0) {
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		boolean flag = htContractService.deleteByIds(ids);
		for (int i=0;i<ids.length;i++) {
			HtFile file = new HtFile();
			file.setContractId(ids[i]);
			fileService.delete(file);
		}
//		boolean flag = htContractService.deleteByIds(ids);
		if (flag==true){
			resultMessage.setCode(0);
			resultMessage.setMessage("删除成功!");
			result.setData(resultMessage);
			return result;
		}else {
			resultMessage.setCode(1);
			resultMessage.setMessage("删除失败!");
			result.setData(resultMessage);
			return result;
		}

	}

}