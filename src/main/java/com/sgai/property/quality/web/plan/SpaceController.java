package com.sgai.property.quality.web.plan;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseSpaceService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.vo.plan.SpaceTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/space")
@Api(description = "空间主数据(房产数据)接口")
public class SpaceController extends BaseController {
    private Logger logger = LogManager.getLogger(SpaceController.class);
    @Autowired
    private BaseSpaceService baseSpaceService;

    @PostMapping("/getSpaceTree")
    @ApiOperation(value = "获取所有空间主数据", notes = "获取所有空间主数据接口")
    public Response<List<SpaceTreeVo>> registAndCallBack() {
        Response<List<SpaceTreeVo>> response=new Response<>();
        List<SpaceDto> spaceDtos = baseSpaceService.registAndCallBack();
        List<SpaceTreeVo> spaceTreeVos = new ArrayList<>();
        if (spaceDtos.size()>0) {
            for(SpaceDto spaceVo : spaceDtos){
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


}
