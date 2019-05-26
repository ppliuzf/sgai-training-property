package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.entity.WarehouseOut;
import com.sgai.property.depot.param.RecordParam;
import com.sgai.property.depot.param.UpdateWhOtParam;
import com.sgai.property.depot.service.DepotOutManageServiceImpl;
import com.sgai.property.depot.vo.AddWhOutParam;
import com.sgai.property.depot.vo.WarehouseOutMatVo;
import com.sgai.property.depot.vo.WhOutParam;
import com.sgai.property.depot.vo.WhRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库管理 on 2018/1/24.
 */
@RestController
@RequestMapping(value = "/depotOutManage")
@Api(description = "出库管理相关接口")
public class DepotOutManageController extends BaseController {
    @Autowired
    DepotOutManageServiceImpl depotOutManageService;

    @RequestMapping(value = "/depotOutLists", method = RequestMethod.POST)
    @ApiOperation(value = "出库管理列表", httpMethod = "POST", notes = "出库管理列表")
    public Response<Page<WarehouseOut>> depotOutLists(

            @RequestBody WhOutParam whOutParam,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {

        Response<Page<WarehouseOut>> depotLists = depotOutManageService.depotOutLists(whOutParam, pageNo, pageSize);
        return depotLists;
    }

    @RequestMapping(value = "/addDepot", method = RequestMethod.POST)
    @ApiOperation(value = "新增出库单", httpMethod = "POST", notes = "新增出库单")
    public Response<StringBuffer> addDepot(
            @RequestBody AddWhOutParam addWhOutParam
    ) {

        Response<StringBuffer> log = depotOutManageService.addDepot(addWhOutParam);
        return log;
    }

    @RequestMapping(value = "/updateDepot", method = RequestMethod.POST)
    @ApiOperation(value = "修改出库单", httpMethod = "POST", notes = "修改出库单")
    public Response<StringBuffer> updateDepot(

            @RequestBody UpdateWhOtParam updateWhOtParam
    ) {

        Response<StringBuffer> boo = depotOutManageService.updateDepot(updateWhOtParam);
        return boo;
    }

    @RequestMapping(value = "/depotDetil", method = RequestMethod.POST)
    @ApiOperation(value = "出库单详情", httpMethod = "POST", notes = "出库单详情")
    public Response<WarehouseOutMatVo> depotDetil(

            @RequestParam(value = "id") String id,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {

        Response<WarehouseOutMatVo> boo = depotOutManageService.depotDetil(id, pageNo, pageSize);
        return boo;
    }

    @RequestMapping(value = "/deleteDepot", method = RequestMethod.POST)
    @ApiOperation(value = "撤销出库单", httpMethod = "POST", notes = "撤销出库单")
    public Response<Boolean> deleteDepot(

            @RequestParam(value = "id") String id
    ) {

        Response<Boolean> boo = depotOutManageService.deleteDepot(id);
        return boo;
    }

    @RequestMapping(value = "/detilList", method = RequestMethod.POST)
    @ApiOperation(value = "出库物料分页", httpMethod = "POST", notes = "出库物料分页")
    public Response<Page<WhRecordVo>> detilList(
            @RequestBody RecordParam recordParam,
            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        return depotOutManageService.detilList(recordParam, pageNo, pageSize);
    }


    @RequestMapping(value = "/addListDepot", method = RequestMethod.POST)
    @ApiOperation(value = "批量新增出库单", httpMethod = "POST", notes = "批量新增出库单")
    public Response<StringBuffer> addListDepot(

            @RequestBody List<AddWhOutParam> whOutParamList
    ) {

        Response<StringBuffer> log = depotOutManageService.addListDepot(whOutParamList);
        return log;
    }
}
