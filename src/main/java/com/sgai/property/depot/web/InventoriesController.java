package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.entity.Inventories;
import com.sgai.property.depot.param.ExcuteInventoisesParamNew;
import com.sgai.property.depot.param.InventoisesParamNew;
import com.sgai.property.depot.param.InventoriesParam;
import com.sgai.property.depot.param.WarehouseMatParam;
import com.sgai.property.depot.service.InventoriesServiceImpl;
import com.sgai.property.depot.vo.*;
import com.sgai.property.purchase.entity.WarehouseMat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by 145811 on 2018/1/25.
 */
@RestController
@RequestMapping(value = "/Inventories")
@Api(description = "盘点理相关接口")
public class InventoriesController extends BaseController {

    @Autowired
    InventoriesServiceImpl inventoriesService;

    @RequestMapping(value = "/saveInventoriesNew", method = RequestMethod.POST)
    @ApiOperation(value = "仓库盘点新增", httpMethod = "POST", notes = "仓库盘点新增")
    public Response<Boolean> saveInventoriesNew(
                                      @RequestBody InventoisesParamNew param){

        Response<Boolean> result = new Response<Boolean>();
        try{
            inventoriesService.saveInventoriesNew(param);
            result.setData(true);
        }catch (Exception e){
            result.setData(false);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/updateInventoriesNew", method = RequestMethod.POST)
    @ApiOperation(value = "新编辑仓库盘点", httpMethod = "POST", notes = "新编辑仓库盘点")
    public Response<Boolean> updateInventoriesNew(
                                                @RequestBody InventoisesParamNew param){

        Response<Boolean> result = new Response<Boolean>();
        try{
            inventoriesService.updateInventoriesNew(param);
            result.setData(true);
        }catch (Exception e){
            result.setData(false);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新", httpMethod = "POST", notes = "新增或更新")
    public Response<Boolean> addOrder(
                                      @RequestBody InventoriesParam param){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(inventoriesService.saveOrUpdate(param));
        return result;
    }

    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    @ApiOperation(value = "列表-选择列表", httpMethod = "POST", notes = "列表")
    public Response<Page<InventoriesVo>> searchPage(
                                                    @ApiParam(value = "日期", required = false) @RequestParam(required = false, name = "dt")Date dt,
                                                    @ApiParam(value = "单号", required = false) @RequestParam(required = false, name = "ivtNo")String ivtNo,
                                                    @ApiParam(value = "盘点标识(工单状态)？0：全部；1：未开始；2：盘点中；3已完成", required = false) @RequestParam(required = false, name = "matStat")Long matStat,
                                                    @ApiParam(value = "主题", required = false) @RequestParam(required = false, name = "ivtTitle")String ivtTitle,
                                                    @RequestParam(required = true, name = "pageNum") int pageNum,
                                                    @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<InventoriesVo>> result = new Response<Page<InventoriesVo>>();
        Page<InventoriesVo> page = inventoriesService.searchPage(dt, ivtNo, matStat, ivtTitle, pageNum, pageSize);
        result.setData(page);
        return result;
    }


    @RequestMapping(value = "/findMatListByivtNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据盘点单号获取物料列表", httpMethod = "POST", notes = "根据盘点单号获取物料列表")
    public Response<Page<InventoiresMatVoNew>> findMatListByivtNo(
                                                            @RequestParam(required = true, name = "ivtNo")String ivtNo,
                                                            @RequestParam(required = false, name = "whId")String whId,
                                                            @RequestParam(required = true, name = "pageNum") int pageNum,
                                                            @RequestParam(required = true, name = "pageSize") int pageSize){

        Response<Page<InventoiresMatVoNew>> result = new Response<Page<InventoiresMatVoNew>>();
        Page<InventoiresMatVoNew> page = inventoriesService.findMatListByivtNo(ivtNo,whId,pageNum,pageSize);
        result.setData(page);
        return result;
    }

    @RequestMapping(value = "/findMatListByonBlur", method = RequestMethod.POST)
    @ApiOperation(value = "失去焦点获取物料列表", httpMethod = "POST", notes = "失去焦点获取物料列表")
    public Response<Page<InventoiresMatVoNew>> findMatListByonBlur(
                                                                  @RequestParam(required = true, name = "ivtNo")String ivtNo,
                                                                  @RequestParam(required = false, name = "whId")String whId,
                                                                  @RequestParam(required = true, name = "pageNum") int pageNum,
                                                                  @RequestParam(required = true, name = "pageSize") int pageSize){

        Response<Page<InventoiresMatVoNew>> result = new Response<Page<InventoiresMatVoNew>>();
        Page<InventoiresMatVoNew> page = inventoriesService.findMatListByonBlur(ivtNo,whId,pageNum,pageSize);
        result.setData(page);
        return result;
    }


    @RequestMapping(value = "/updateWarehousRecord", method = RequestMethod.POST)
    @ApiOperation(value = "失去焦点编辑物料列表", httpMethod = "POST", notes = "失去焦点编辑物料列表")
    public Response<Boolean> updateWarehousRecord(
                                                                  @RequestBody(required = true) WarehouseMatParam warehouseMatParam){

        Response<Boolean> result = new Response<Boolean>();
        try {
            inventoriesService.updateWarehousRecord(warehouseMatParam);
            result.setData(true);
        }catch (Exception e){
            result.setData(false);
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/findAllBhByivtNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据盘点单号获取所有仓库", httpMethod = "POST", notes = "根据盘点单号获取物料列表")
    public Response<List<Inventories>> findAllBhByivtNo(
                                                           @RequestParam(required = true, name = "ivtNo")String ivtNo){

        Response<List<Inventories>> result = new Response<List<Inventories>>();
        List<Inventories> list = inventoriesService.findAllBhByivtNo(ivtNo);
        result.setData(list);
        return result;
    }


    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ApiOperation(value = "详情-不包括物料列表", httpMethod = "POST", notes = "详情-不包括物料列表")
    public Response<InventoriesVo> detail(
                                                    @ApiParam(value = "id", required = false) @RequestParam(required = false, name = "id")String id){

        Response<InventoriesVo> result = new Response<InventoriesVo>();
        InventoriesVo vo = inventoriesService.detail(id);
        result.setData(vo);
        return result;
    }

    @RequestMapping(value = "/excuteUpdateInventories", method = RequestMethod.POST)
    @ApiOperation(value = "执行盘点仓库新增", httpMethod = "POST", notes = "执行盘点仓库新增")
    public Response<Boolean> excuteUpdateInventories(
                                                @RequestBody ExcuteInventoisesParamNew param){

        Response<Boolean> result = new Response<Boolean>();
        try{
            inventoriesService.excuteUpdateInventories(param);
            result.setData(true);
        }catch (Exception e){
            result.setData(false);
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/excuteSynchroMatList", method = RequestMethod.POST)
    @ApiOperation(value = "执行盘点新增物料列表同步", httpMethod = "POST", notes = "执行盘点新增物料列表同步")
    public Response<Boolean> excuteSynchroMatList(
                                                  @RequestParam String ivtNo){

        Response<Boolean> result = new Response<Boolean>();
        try{
            inventoriesService.excuteSynchroMatList(ivtNo);
            result.setData(true);
        }catch (Exception e){
            result.setData(false);
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping(value = "/detailNew", method = RequestMethod.POST)
    @ApiOperation(value = "新盘点详情-不包括物料列表", httpMethod = "POST", notes = "新盘点详情-不包括物料列表")
    public Response<ExcuteInventoisesVoNew> detailNew(
                                           @RequestParam(required = true, name = "ivtNo")String ivtNo){

        Response<ExcuteInventoisesVoNew> result = new Response<ExcuteInventoisesVoNew>();
        ExcuteInventoisesVoNew vo = inventoriesService.detailNew(ivtNo);
        result.setData(vo);
        return result;
    }


    @RequestMapping(value = "/getInventByIvtNo", method = RequestMethod.POST)
    @ApiOperation(value = "编辑盘点前获取盘点仓库物料详情", httpMethod = "POST", notes = "编辑盘点前获取盘点仓库物料详情")
    public Response<InventoisesVoNew> getInventByIvtNo(
                                                      @RequestParam(required = true, name = "ivtNo")String ivtNo){

        Response<InventoisesVoNew> result = new Response<InventoisesVoNew>();
        InventoisesVoNew vo = inventoriesService.getInventByIvtNo(ivtNo);
        result.setData(vo);
        return result;
    }


    @RequestMapping(value = "/matPage", method = RequestMethod.POST)
    @ApiOperation(value = "指定盘点的物料分页数据", httpMethod = "POST", notes = "指定盘点的物料分页数据")
    public Response<Page<InventoriesMatVo>> matPage(
                                                    @ApiParam(value = "盘点id", required = true) @RequestParam(required = false, name = "id")String id,
                                                    @RequestParam(required = true, name = "pageNum") int pageNum,
                                                    @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<InventoriesMatVo>> result = new Response<Page<InventoriesMatVo>>();
        Page<InventoriesMatVo> page = inventoriesService.matPage(id, pageNum, pageSize);
        result.setData(page);
        return result;
    }



    @RequestMapping(value = "/excuteInventMatPage", method = RequestMethod.POST)
    @ApiOperation(value = "执行盘点后的物料分页数据", httpMethod = "POST", notes = "执行盘点后的物料分页数据")
    public Response<Page<InventoiresMatVoNew>> excuteInventMatPage(
                                                                @RequestParam(required = true, name = "ivtNo")String ivtNo,
                                                                @RequestParam(required = false, name = "whId")String whId,
                                                                @RequestParam(required = true, name = "pageNum") int pageNum,
                                                                @RequestParam(required = true, name = "pageSize") int pageSize){

        Response<Page<InventoiresMatVoNew>> result = new Response<Page<InventoiresMatVoNew>>();
        Page<InventoiresMatVoNew> page = inventoriesService.excuteInventMatPage(ivtNo,whId,pageNum,pageSize);
        result.setData(page);
        return result;
    }


    /**
     * 如果没有同步则同步

     * @param ivtNo
     * @return
     */
    @RequestMapping(value = "/begin", method = RequestMethod.POST)
    @ApiOperation(value = "执行盘点-将仓库物料数据同步到盘点物料数据", httpMethod = "POST", notes = "执行盘点-将仓库物料数据同步到盘点物料数据")
    public Response<Boolean> begin(
                                                    @ApiParam(value = "盘点单号", required = true) @RequestParam(required = true, name = "ivtNo")String ivtNo ){

        Response<Boolean> result = new Response<Boolean>();
        Boolean rtData = inventoriesService.begin(ivtNo);
        result.setData(rtData);
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "盘点删除", httpMethod = "POST", notes = "盘点删除")
    public Response<Boolean> del(
                                   @ApiParam(value = "盘点id", required = true) @RequestParam(required = true, name = "ivtNo")String ivtNo ){

        Response<Boolean> result = new Response<Boolean>();
        Boolean rtData = inventoriesService.del(ivtNo);
        result.setData(rtData);
        return result;
    }

    @RequestMapping(value = "/execSave", method = RequestMethod.POST)
    @ApiOperation(value = "执行盘点保存", httpMethod = "POST", notes = "执行盘点保存")
    public Response<Boolean> execSave(
                                      @RequestBody InventoriesParam param){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(inventoriesService.execSave(param));
        return result;
    }

    @RequestMapping(value = "/findMatListByWhId", method = RequestMethod.POST)
    @ApiOperation(value = "根据仓库id查询该仓库下所有的物料", httpMethod = "POST", notes = "根据仓库id查询该仓库下所有的物料")
    public Response<List<WarehouseMat>> findMatListByWhId(
                                                          @RequestParam String whId){

        Response<List<WarehouseMat>> result = new Response<List<WarehouseMat>>();
        result.setData(inventoriesService.findMatListByWhId(whId));
        return result;
    }
}
