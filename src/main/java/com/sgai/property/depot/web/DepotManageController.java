package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.entity.Warehouse;
import com.sgai.property.depot.param.InventoryManageParam;
import com.sgai.property.depot.service.DepotManageServiceImpl;
import com.sgai.property.depot.vo.InventoryManageVo;
import com.sgai.property.depot.vo.WarehouseParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 仓库管理 on 2018/1/23.
 */
@RestController
@RequestMapping(value = "/depotManage")
@Api(description = "仓库管理相关接口")
public class DepotManageController extends BaseController {
    @Autowired
    DepotManageServiceImpl depotManageService;

    @RequestMapping(value="/depotLists", method = RequestMethod.POST)
    @ApiOperation(value = "仓库档案", httpMethod = "POST", notes = "仓库档案")
    public Response<Page<Warehouse>> depotLists(

            @RequestParam(required = false, name = "whName") String whName,
            @RequestParam(required = false, name = "whAddress") String whAddress,
            @RequestParam(required = false, name = "whType") Long whType,
            @RequestParam(value = "pageNo") int pageNo ,
            @RequestParam(value = "pageSize") int pageSize
    ) {

        Response<Page<Warehouse>> depotLists = depotManageService.depotLists(whName,whAddress,whType,pageNo, pageSize);
        return depotLists;
    }

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增仓库", httpMethod = "POST", notes = "新增仓库")
    public Response<Boolean> addOrUpdate(

            @RequestBody WarehouseParam warehouseParam
    ){

        Response<Boolean> boo = depotManageService.addOrUpdate(warehouseParam);
        return boo ;
    }

    @RequestMapping(value="/depotDetil", method = RequestMethod.POST)
    @ApiOperation(value = "仓库详情", httpMethod = "POST", notes = "仓库详情")
    public Response<Warehouse> depotDetil(

            @RequestParam(value = "id") String id
    ){

        Response<Warehouse> boo = depotManageService.depotDetil(id);
        return boo ;
    }

    @RequestMapping(value="/deleteDepot", method = RequestMethod.POST)
    @ApiOperation(value = "删除仓库", httpMethod = "POST", notes = "删除仓库")
    public Response<Boolean> deleteDepot(

            @RequestParam(value = "id") String id
    ){

        Response<Boolean> boo = depotManageService.deleteDepot(id);
        return boo ;
    }

    @RequestMapping(value="/inventoryManages", method = RequestMethod.POST)
    @ApiOperation(value = "库存管理", httpMethod = "POST", notes = "库存管理")
    public Response<Page<InventoryManageVo>> inventoryManages(

            @RequestParam(value = "pageNo") int pageNo ,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestBody InventoryManageParam inventoryManageParam
    ) {

        Response<Page<InventoryManageVo>> depotLists = depotManageService.inventoryManages(inventoryManageParam ,pageNo, pageSize);
        return depotLists;
    }
}
