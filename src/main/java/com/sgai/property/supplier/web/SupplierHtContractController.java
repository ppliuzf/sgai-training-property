package com.sgai.property.supplier.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.supplier.service.impl.HtContractServiceImpl;
import com.sgai.property.supplier.vo.ContractDetailsVO;
import com.sgai.property.supplier.vo.ContractVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/htContract")
@Api(description = "合同管理")
public class SupplierHtContractController extends BaseController {
    @Autowired
	private HtContractServiceImpl htContractService;

  
	@PostMapping(value="/htContractPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<ContractVO>> PageList(
                                               @RequestParam(value = "id",required = true)String id,
                                               @RequestParam(value = "pageNum",required = true) int pageNum,
                                               @RequestParam(value = "pageSize",required = true) int pageSize) {

        if (pageNum ==0 || pageSize==0) {
            throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
        }
        Page<ContractVO> page=htContractService.findPageList(id,pageNum,pageSize);
        Response<Page<ContractVO>> result = new Response<>();
        result.setData(page);
		return result;
	}

    @PostMapping(value="/getById")
    @ApiOperation(value = "查询对象", httpMethod = "POST", notes = "查询对象")
    public Response<ContractDetailsVO> getById(
                                               @RequestParam(value = "id",required = true) String id) {
        Response<ContractDetailsVO> result = new Response<>();

        if (null == id) {
            throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
        }
        result.setData(htContractService.getById(id));
        return result;
    }

}