package com.sgai.property.contract.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.contract.entity.HtContract;
import com.sgai.property.contract.entity.HtType;
import com.sgai.property.contract.service.ContractHtContractServiceImpl;
import com.sgai.property.contract.service.HtTypeServiceImpl;
import com.sgai.property.contract.service.impl.TypeServiceImpl;
import com.sgai.property.contract.vo.HtTypeVO;
import com.sgai.property.contract.vo.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/type")
@Api(description = "合同类型")
public class HtTypeController extends BaseController {
    private static final Logger logger = LogManager.getLogger(HtTypeController.class);
    @Autowired
    private HtTypeServiceImpl htTypeService;
    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private ContractHtContractServiceImpl htContractService;


    @PostMapping(value = "/findTypePageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
    public Response<Page<HtTypeVO>> PageList(
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestParam(value = "pageSize", required = true) int pageSize) {
        Response<Page<HtTypeVO>> result = new Response<>();

        if (pageNum == 0 || pageSize == 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        Page<HtTypeVO> page = typeService.findListPage(pageNum, pageSize);
        result.setData(page);
        return result;
    }


    @PostMapping(value = "/findList")
    @ApiOperation(value = "不分页查询所有类型", httpMethod = "POST", notes = "不分页查询所有类型")
    public Response<List<HtType>> getById() {
        Response<List<HtType>> result = new Response<>();

        HtType type = new HtType();
        Page<HtType> page = new Page<>();
        page.setOrderBy("created_dt ASC");
        type.setPage(page);
        type.setIsDelete(1L);
        List<HtType> pageList = typeService.findList(type);
        result.setData(pageList);
        return result;
    }

    @PostMapping(value = "/getById")
    @ApiOperation(value = "查询对象", httpMethod = "POST", notes = "查询对象")
    public Response<HtTypeVO> getById(
            @RequestParam(value = "id", required = true) String id) {
        Response<HtTypeVO> result = new Response<>();

        if (null == id) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        result.setData(typeService.getById(id));
        return result;
    }


    @ApiOperation(value = "新增保存", httpMethod = "POST", notes = "新增保存")
    @PostMapping(value = "/save")
    public Response<ResultMessage> save(
            @RequestBody HtTypeVO htType) {
        Response<ResultMessage> result = new Response<>();
        ResultMessage resultMessage = new ResultMessage();

        if (null == htType) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        HtType type = new HtType();
        type.setTypeName(htType.getTypeName());
        HtType htType1 = typeService.get(type);
        if (null != htType1) {
            resultMessage.setCode(3);
            resultMessage.setMessage("类型名称重复,请重新输入!");
            result.setData(resultMessage);
            return result;
        } else {
            typeService.save(htType);
            resultMessage.setCode(0);
            resultMessage.setMessage("保存成功!");
            result.setData(resultMessage);
            return result;
        }
    }

    @ApiOperation(value = "保存编辑", httpMethod = "POST", notes = "保存编辑")
    @PostMapping(value = "/updateById")
    public Response<ResultMessage> updateById(
            @RequestBody HtTypeVO htType) {
        Response<ResultMessage> result = new Response<>();
        ResultMessage resultMessage = new ResultMessage();

        if (null == htType) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        }
        HtType type = new HtType();
        type.setTypeName(htType.getTypeName());
        HtType htType1 = typeService.get(type);
        if (null != htType1 && !htType1.getId().equals(htType.getId())) {
            resultMessage.setCode(3);
            resultMessage.setMessage("类型名称重复,请重新输入!");
            result.setData(resultMessage);
            return result;
        } else {
            Boolean flag = typeService.updateById(htType);
            if (flag == true) {
                resultMessage.setCode(0);
                resultMessage.setMessage("保存成功!");
                result.setData(resultMessage);
                return result;
            } else {
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
            @RequestParam(value = "id", required = true) String id) {
        Response<ResultMessage> result = new Response<>();
        ResultMessage resultMessage = new ResultMessage();

        //更新关联该类型的合同类型
        HtContract htContract = new HtContract();
        htContract.setTypeId(id);
        htContract.setComCode(UserServletContext.getUserInfo().getComCode());
        htContract.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<HtContract> htContracts = htContractService.findList(htContract);
        if (htContracts.size() > 0) {
            resultMessage.setCode(3);
            resultMessage.setMessage("类型已关联合同,不能删除!");
            result.setData(resultMessage);
            return result;
        }
        if (id.equals("")) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数错误!");
        } else {
            Boolean flag = htTypeService.deleteById(id);
            if (flag == true) {
                resultMessage.setCode(0);
                resultMessage.setMessage("删除成功!");
                result.setData(resultMessage);
                return result;
            } else {
                resultMessage.setCode(-1);
                resultMessage.setMessage("删除失败!");
                result.setData(resultMessage);
                return result;
            }
        }


    }

}