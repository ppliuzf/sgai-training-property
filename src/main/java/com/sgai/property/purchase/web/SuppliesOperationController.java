package com.sgai.property.purchase.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.purchase.entity.MatApplyDetail;
import com.sgai.property.purchase.entity.SuppliesParam;
import com.sgai.property.purchase.service.SuppliesOperationServiceImpl;
import com.sgai.property.purchase.vo.ApprovalParam;
import com.sgai.property.purchase.vo.SuppliesApproveParam;
import com.sgai.property.purchase.vo.SuppliesApproveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用料相关操作接口 on 2018/1/11.
 */
@RestController
@RequestMapping(value = "/suppliesOperation")
@Api(description = "用料相关接口")
public class SuppliesOperationController extends BaseController {
    @Autowired
    SuppliesOperationServiceImpl suppliesOperationService;

    @RequestMapping(value = "/addSuppliesInvoices", method = RequestMethod.POST)
    @ApiOperation(value = "新增用料申请单", httpMethod = "POST", notes = "新增用料申请单")
    public Response<Boolean> infoApproval(
            @RequestBody SuppliesParam suppliesParam
    ) {return suppliesOperationService.infoApproval(suppliesParam);
    }

    @RequestMapping(value = "/suppliesList", method = RequestMethod.POST)
    @ApiOperation(value = "用料申请列表", httpMethod = "POST", notes = "用料申请列表")
    public Response<Page<MatApplyDetail>> suppliesList(

            @RequestParam(required = false, name = "applyEmpName") String applyEmpName,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ) {


        Response<Page<MatApplyDetail>> result = suppliesOperationService.suppliesList(pageNum, pageSize, applyEmpName);

        return result;
    }

    @RequestMapping(value = "/suppliesDetil", method = RequestMethod.POST)
    @ApiOperation(value = "用料详情", httpMethod = "POST", notes = "用料详情")
    public Response<SuppliesParam> suppliesDetil(

            @RequestParam(required = true, name = "id") String id
    ) {


        return suppliesOperationService.suppliesDetil(id);
    }

    @RequestMapping(value = "/updateSuppliesInvoices", method = RequestMethod.POST)
    @ApiOperation(value = "二次编辑用料申请单", httpMethod = "POST", notes = "二次编辑用料申请单")
    public Response<Boolean> updateSupplies(

            @RequestBody SuppliesParam suppliesParam
    ) {


        Response<Boolean> result = suppliesOperationService.updateSupplies(suppliesParam);

        return result;
    }

    @RequestMapping(value = "/suppliesApproves", method = RequestMethod.POST)
    @ApiOperation(value = "用料审批列表", httpMethod = "POST", notes = "用料审批列表")
    public Response<Page<SuppliesApproveVo>> suppliesApproves(

            @RequestBody SuppliesApproveParam suppliesApproveParam,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ) {


        Response<Page<SuppliesApproveVo>> result = suppliesOperationService.suppliesApproves(pageNum, pageSize, suppliesApproveParam);

        return result;
    }

    @RequestMapping(value = "/suppliesRetract", method = RequestMethod.POST)
    @ApiOperation(value = "撤回用料订单", httpMethod = "POST", notes = "撤回用料订单")
    public Response<Boolean> suppliesRetract(

            @RequestParam String id) {

        Boolean bool = suppliesOperationService.suppliesRetract(id);
        Response<Boolean> result = new Response<>();
        result.setData(bool);
        return result;
    }

    @RequestMapping(value = "/suppliesApproval", method = RequestMethod.POST)
    @ApiOperation(value = "审批用料订单", httpMethod = "POST", notes = "审批用料订单")
    public Response<Boolean> suppliesApproval(

            @RequestBody ApprovalParam approvalParam
    ) {

        Response<Boolean> result = new Response<>();
        Boolean bool = suppliesOperationService.suppliesApproval(approvalParam);
        result.setData(bool);
        return result;
    }

    @RequestMapping(value = "/getSupplies", method = RequestMethod.POST)
    @ApiOperation(value = "领取用料", httpMethod = "POST", notes = "领取用料")
    public Response<Boolean> getSupplies(

            @RequestParam String id) {

        Boolean bool = suppliesOperationService.getSupplies(id);
        Response<Boolean> result = new Response<>();
        result.setData(bool);
        return result;
    }
}
