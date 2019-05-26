package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.entity.WarehousRecord;
import com.sgai.property.depot.entity.WarehouseIn;
import com.sgai.property.depot.entity.WarehouseInMat;
import com.sgai.property.depot.param.UpdateWhInParam;
import com.sgai.property.depot.param.WarehousRecordParam;
import com.sgai.property.depot.service.DepotInManageServiceImpl;
import com.sgai.property.depot.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 入库 on 2018/1/25.
 */
@RestController
@RequestMapping(value = "/depotInManage")
@Api(description = "入库管理相关接口")
public class DepotInManageController extends BaseController {
    @Autowired
    DepotInManageServiceImpl depotInManageService;

    @RequestMapping(value="/depotInLists", method = RequestMethod.POST)
    @ApiOperation(value = "入库管理列表", httpMethod = "POST", notes = "入库管理列表")
    public Response<Page<WarehouseIn>> depotInLists(

            @RequestBody WhInParam whInParam,
            @RequestParam(value = "pageNo") int pageNo ,
            @RequestParam(value = "pageSize") int pageSize
    ) {

        Response<Page<WarehouseIn>> depotLists = depotInManageService.depotInLists(whInParam,pageNo, pageSize);
        return depotLists;
    }

    @RequestMapping(value="/addDepotMatInfo", method = RequestMethod.POST)
    @ApiOperation(value = "新增入库单用料明细", httpMethod = "POST", notes = "新增入库单用料明细")
    public Response<WarehousRecordParam> addDepotMatInfo(
            @RequestBody WarehousRecordParam warehousRecordParam){

        Response<WarehousRecordParam> s = new Response<WarehousRecordParam>();
        try {
            warehousRecordParam = depotInManageService.addDepotMatInfo(warehousRecordParam);
            s.setData(warehousRecordParam);
            s.setMessage("操作成功！");
            s.setCode(ReturnType.Success.getCode());
        }catch (Exception e){
            s.setCode(ReturnType.Error.getCode());
            s.setMessage("操作失败！");
        }
        return s;
    }

    @RequestMapping(value="/addDepot", method = RequestMethod.POST)
    @ApiOperation(value = "新增入库单", httpMethod = "POST", notes = "新增入库单")
    public Response<Boolean> addDepot(

            @RequestBody AddWhInParam addWhInParam
    ){

        Response<Boolean> boo = depotInManageService.addDepot(addWhInParam);
        return boo ;
    }

    @RequestMapping(value="/addDepotBatch", method = RequestMethod.POST)
    @ApiOperation(value = "新增批量入库单", httpMethod = "POST", notes = "新增批量入库单")
    public Response<Boolean> addDepotBatch(

            @RequestBody List<AddWhInParam> addWhInBatchParam
    ){
        Response<Boolean> boo = null;
        try {

            //List<AddWhInParam> whInParamList = addWhInBatchParam.getWhInParamList();
            for (AddWhInParam addWhInParam: addWhInBatchParam) {
                boo = depotInManageService.addDepot(addWhInParam);
            }
            boo.setMessage("操作成功！");
            boo.setCode(ReturnType.Success.getCode());
        }catch (Exception e){
            boo.setCode(ReturnType.Error.getCode());
            boo.setMessage("操作失败！");
        }

        return boo ;
    }

    @RequestMapping(value="/updateWhInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改入库信息页面仓库数据接口", httpMethod = "POST", notes = "修改入库信息页面仓库数据接口")
    public Response<String> updateWhInfo(

            String orderId,
            String whType,String whId,String whName
    ){

        Response<String> boo = null;
        WarehousRecord record = new WarehousRecord();

        if(whType != null){
            record.setWhType(whType);
        }
        if(whId != null){
            record.setWhId(whId);
            record.setWhName(whName);
        }
        record.setOrderId(orderId);
        boo = depotInManageService.updateWhInfo(record);

        return boo ;
    }






    @RequestMapping(value="/updateDepot", method = RequestMethod.POST)
    @ApiOperation(value = "修改入库单", httpMethod = "POST", notes = "修改入库单")
    public Response<Boolean> updateDepot(

            @RequestBody UpdateWhInParam addWhInParam
    ){

        Response<Boolean> boo = depotInManageService.updateDepot(addWhInParam);
        return boo ;
    }
    
    @RequestMapping(value="/depotDetil", method = RequestMethod.POST)
    @ApiOperation(value = "入库单详情", httpMethod = "POST", notes = "入库单详情")
    public Response<WarehouseInMatVo> depotDetil(

            @RequestParam(value = "id") String id
    ){

        Response<WarehouseInMatVo> boo = depotInManageService.depotDetil(id);
        return boo ;
    }

    @RequestMapping(value="/depotDetilMat", method = RequestMethod.POST)
    @ApiOperation(value = "入库单详情--用料明细分页", httpMethod = "POST", notes = "入库单详情--用料明细分页")
    public Response<Page<WarehouseInMat>> depotDetilMat(

            @RequestParam(value = "id") String id,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ){
        Response<Page<WarehouseInMat>> result = new Response<Page<WarehouseInMat>>();

        Page<WarehouseInMat> boo = depotInManageService.depotDetilMat(id,pageNum,pageSize);
        result.setData(boo);
        return result ;
    }

    @RequestMapping(value="/depotDetilOperation", method = RequestMethod.POST)
    @ApiOperation(value = "入库单详情--入库操作员记录", httpMethod = "POST", notes = "入库单详情--入库操作员记录")
    public Response<Page<OperationVo>> depotDetilOperation(

            @RequestParam(value = "id") String id,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ){
        Response<Page<OperationVo>> result = new Response<Page<OperationVo>>();

        Page<OperationVo> boo = depotInManageService.depotDetilOperation(id,pageNum,pageSize);
        result.setData(boo);
        return result ;
    }

    @RequestMapping(value="/depotDetilOperationMatPag", method = RequestMethod.POST)
    @ApiOperation(value = "入库单详情--入库操作员物料分页记录", httpMethod = "POST", notes = "入库单详情--入库操作员物料分页记录")
    public Response<Page<WarehouseOpterationMatVo>> depotDetilOperationMatPag(

            @RequestParam(value = "id") String id,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ){
        Response<Page<WarehouseOpterationMatVo>> result = new Response<Page<WarehouseOpterationMatVo>>();

        Page<WarehouseOpterationMatVo> boo = depotInManageService.depotDetilOperationMatPag(id,pageNum,pageSize);
        result.setData(boo);
        return result ;
    }








    @RequestMapping(value="/deleteDepot", method = RequestMethod.POST)
    @ApiOperation(value = "撤销入库单", httpMethod = "POST", notes = "撤销入库单")
    public Response<Boolean> deleteDepot(

            @RequestParam(value = "id") String id
    ){

        Response<Boolean> boo = depotInManageService.deleteDepot(id);
        return boo ;
    }
    
    
    @RequestMapping(value = "/searchInOutList", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询出入库列表", httpMethod = "POST", notes = "分页查询出入库列表")
    public Response<Page<WarehouseInOutVo>> searchInOutList(
                                                 @ApiParam(value = "开始时间", required = false) @RequestParam(required = false, name = "dtBegin")Date dtBegin,
                                                 @ApiParam(value = "结束时间", required = false) @RequestParam(required = false, name = "dtEnd")Date dtEnd,
                                                 @RequestParam(required = true, name = "pageNum") int pageNum,
                                                 @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<WarehouseInOutVo>> result = new Response<Page<WarehouseInOutVo>>();
        Page<WarehouseInOutVo> page = depotInManageService.findInOutPage(dtBegin, dtEnd, pageNum, pageSize);
        result.setData(page);
        return result;
    }
}
