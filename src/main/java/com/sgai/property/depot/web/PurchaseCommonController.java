package com.sgai.property.depot.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseMaterielService;
import com.sgai.property.common.service.BaseSpaceService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.entity.Warehouse;
import com.sgai.property.depot.param.InstantSavParam;
import com.sgai.property.depot.service.DepotManageServiceImpl;
import com.sgai.property.depot.service.DepotOutManageServiceImpl;
import com.sgai.property.depot.vo.MatApplyDetailVo;
import com.sgai.property.depot.vo.SpaceTreeVo;
import com.sgai.property.mdm.entity.MdmMatInfo;
import com.sgai.property.purchase.entity.MatSpuulyCom;
import com.sgai.property.purchase.service.PurchaseCommonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 145811 on 2018/1/12.
 */
@RestController
@RequestMapping(value = "/common")
@Api(description = "公用接口")
public class PurchaseCommonController extends BaseController {

    @Autowired
    DepotManageServiceImpl depotManageService;
    @Autowired
    DepotOutManageServiceImpl depotOutManageService;
    @Autowired
    PurchaseCommonServiceImpl commonService;
    @Autowired
    private BaseSpaceService baseSpaceService;
    @Autowired
    private BaseMaterielService baseMaterielService;

    @RequestMapping(value = "/getComList", method = RequestMethod.POST)
    @ApiOperation(value = "供应商列表", httpMethod = "POST", notes = "列表")
    public Response<List<MatSpuulyCom>> searchList() {

        Response<List<MatSpuulyCom>> result = new Response<List<MatSpuulyCom>>();
        List<MatSpuulyCom> comList = commonService.getComList();
        result.setData(comList);
        return result;
    }

    @RequestMapping(value = "/listMdmMatInfo", method = RequestMethod.POST)
    @ApiOperation(value = "物料类型", httpMethod = "POST", notes = "物料类型")
    public Response<List<MdmMatInfo>> listMdmMatInfo(

            @RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize
    ) {

        return new Response<>(baseMaterielService.getListMdmMatInfo("", "", pageNo, pageSize).getList());
    }

    @RequestMapping(value = "/spaceTree", method = RequestMethod.POST)
    @ApiOperation(value = "空间数据", httpMethod = "POST", notes = "空间数据")
    public Response<List<SpaceTreeVo>> spaceTree(

            @RequestParam(value = "sgaiToken") String sgaiToken
    ) {
        Response<List<SpaceTreeVo>> response = new Response<>();

        List<SpaceDto> spaceDtos = baseSpaceService.registAndCallBack();
        List<SpaceTreeVo> spaceTreeVos = new ArrayList<>();
        if (spaceDtos.size() > 0) {
            for (SpaceDto spaceVo : spaceDtos) {
                SpaceTreeVo spaceTreeVo = new SpaceTreeVo();
                spaceTreeVo.setName(spaceVo.getName());
                spaceTreeVo.setParent(spaceVo.getpId());
                spaceTreeVo.setValue(spaceVo.getId());
                spaceTreeVos.add(spaceTreeVo);
            }
            response.setData(spaceTreeVos);
        } else {
            throw new BusinessException(ReturnType.ParamIllegal, "获取所有空间主数据接口异常!");
        }
        return response;
    }

    @RequestMapping(value = "/byWhType", method = RequestMethod.POST)
    @ApiOperation(value = "根据仓库类型选择仓库", httpMethod = "POST", notes = "仓库类型")
    public Response<List<Warehouse>> byWhType(

            @RequestParam(value = "whType") String whType
    ) {

        return depotManageService.byWhType(whType);
    }

    @RequestMapping(value = "/suppliesList", method = RequestMethod.POST)
    @ApiOperation(value = "选择用料申请单", httpMethod = "POST", notes = "选择用料申请单")
    public Response<Page<MatApplyDetailVo>> suppliesList(

            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ) {


        return depotOutManageService.suppliesList(pageNum, pageSize);
    }

    @RequestMapping(value = "/instantSaving", method = RequestMethod.POST)
    @ApiOperation(value = "即时保存", httpMethod = "POST", notes = "即时保存")
    public Response<Boolean> instantSaving(

            @RequestBody InstantSavParam instantSavParam
    ) {


        return depotOutManageService.instantSaving(instantSavParam);
    }
}
