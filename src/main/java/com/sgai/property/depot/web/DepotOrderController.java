package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.service.DoptPurchaseOrderServiceImpl;
import com.sgai.property.depot.vo.WarehousRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 145811 on 2018/1/24.
 */

@RestController
@RequestMapping(value = "/order")
@Api(description = "采购订单")
public class DepotOrderController extends BaseController {


    @Autowired
    private DoptPurchaseOrderServiceImpl orderService;


    /**
     * 查询分页采购入库采购单用料明细
     * <p>
     * param orderNo 采购单号
     *
     * @param orderId  用料虚拟id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/searchWarehousMatList", method = RequestMethod.POST)
    @ApiOperation(value = "查询分页采购入库采购单用料明细", httpMethod = "POST", notes = "查询分页采购入库采购单用料明细")
    public Response<Page<WarehousRecordVo>> searchWarehousMatList(String orderNo,
                                                                  String orderId,
                                                                  String whId,
                                                                  String whName,
                                                                  String whType,
                                                                  Long isAllot,//1是挑拨2是采购
                                                                  @RequestParam(required = true, name = "pageNum") int pageNum,
                                                                  @RequestParam(required = true, name = "pageSize") int pageSize) {
        Response<Page<WarehousRecordVo>> result = new Response<Page<WarehousRecordVo>>();
        try {

            Page<WarehousRecordVo> page = orderService.searchWarehousMatList(orderNo, orderId, whId, whName, whType, pageNum, pageSize, isAllot);
            result.setData(page);
            result.setCode(ReturnType.Success.getCode());
            result.setMessage("操作成功！");
        } catch (Exception e) {
            result.setCode(ReturnType.Error.getCode());
            result.setMessage("操作失败！");
            e.printStackTrace();
        }

        return result;
    }
}
