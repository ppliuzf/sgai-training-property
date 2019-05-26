package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.depot.param.WarehouseAllotParam;
import com.sgai.property.depot.service.AllotServiceImpl;
import com.sgai.property.depot.vo.WarehouseAllotMatVo;
import com.sgai.property.depot.vo.WarehouseAllotVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 145811 on 2018/1/24.
 */
@RestController
@RequestMapping(value = "/allot")
@Api(description = "调拨")
public class AllotController extends BaseController {

    @Autowired
    AllotServiceImpl allotService;
    @Autowired
    BaseDepartmentService baseDepartmentService;

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新", httpMethod = "POST", notes = "新增或更新")
    public Response<Boolean> addOrder(
                                      @RequestBody WarehouseAllotParam allotParam){

        Response<Boolean> result = new Response<Boolean>();

        String deptCode = UserServletContext.getUserInfo().getDeptCode();
        List<CtlDept> data = baseDepartmentService.findDeptsByCodes(deptCode);
        result.setData(allotService.saveOrUpdate(allotParam));
        return result;
    }

    @RequestMapping(value = "/searchList", method = RequestMethod.POST)
    @ApiOperation(value = "列表-选择列表", httpMethod = "POST", notes = "列表")
    public Response<Page<WarehouseAllotVo>> searchList(
                                                       @ApiParam(value = "调拨单号", required = false) @RequestParam(required = false, name = "allotNo")String allotNo,
                                                       @RequestParam(required = true, name = "pageNum") int pageNum,
                                                       @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<WarehouseAllotVo>> result = new Response<Page<WarehouseAllotVo>>();
        Page<WarehouseAllotVo> page = allotService.searchList(allotNo, pageNum, pageSize);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/searchList4InOut", method = RequestMethod.POST)
    @ApiOperation(value = "列表-选择列表", httpMethod = "POST", notes = "列表")
    public Response<List<WarehouseAllotVo>> searchList4InOut(
                                                             @ApiParam(value = "调拨单号", required = false) @RequestParam(required = false, name = "allotNo")String allotNo,
                                                             @ApiParam(value = "启用状态？0:出库；1:入库启用", required = false) @RequestParam(required = false, name = "hasOrder")Long hasOrder){

        Response<List<WarehouseAllotVo>> result = new Response<List<WarehouseAllotVo>>();
        List<WarehouseAllotVo> voList = allotService.searchList4InOut(allotNo , hasOrder);
        result.setData(voList);
        return result;
    }
    @RequestMapping(value = "/searchList4InOutMat", method = RequestMethod.POST)
    @ApiOperation(value = "查询分页挑拨入库采购单用料明细", httpMethod = "POST", notes = "查询分页挑拨入库采购单用料明细")
    public Response<Page<WarehouseAllotMatVo>> searchList4InOutMat(
                                                                   @ApiParam(value = "调拨单号", required = false) @RequestParam(required = false, name = "allotNo")String allotNo,
                                                                   @RequestParam(required = true, name = "pageNum") int pageNum,
                                                                   @RequestParam(required = true, name = "pageSize") int pageSize){

        Response<Page<WarehouseAllotMatVo>> result = new Response<Page<WarehouseAllotMatVo>>();
        Page<WarehouseAllotMatVo> voList = allotService.searchList4InOutMat(allotNo,pageNum,pageSize);
        result.setData(voList);
        return result;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "详情", httpMethod = "POST", notes = "详情")
    public Response<WarehouseAllotVo> searchList(
                                                       @RequestParam(required = true, name = "id") String id ){

        Response<WarehouseAllotVo> result = new Response<WarehouseAllotVo>();
        WarehouseAllotVo vo = allotService.getById(id);
        result.setData(vo);
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", httpMethod = "POST", notes = "删除")
    public Response<Boolean> del(
                                                 @RequestParam(required = true, name = "id") String id ){

        Response<Boolean> result = new Response<Boolean>();
        Boolean rtVal = allotService.del(id);
        result.setData(rtVal);
        return result;
    }

}
