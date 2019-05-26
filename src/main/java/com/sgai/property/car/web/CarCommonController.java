package com.sgai.property.car.web;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import com.sgai.property.commonService.vo.OrgTreeNode;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
@Api(description = "公共接口;含组织树、用户等相关接口")
public class CarCommonController extends BaseController {


    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseOrgTreeService baseOrgTreeService;
    @ApiOperation(value = "获取部门Id获取子部门集合信息", httpMethod = "POST", notes = "获取部门Id获取子部门集合信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "-1:根部门,0:获取一级部门", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "isIncludeEmp", value = "是否查询员工0:不是1是", required = true, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "/getOrgTreeById", method = RequestMethod.POST)
    public Response<List<OrgTreeNode>> getOrgTreeById(
                                                      @RequestParam("deptId")Long deptId,
                                                      @RequestParam("isIncludeEmp")Integer isIncludeEmp) {

        Long comId = UserServletContext.getUserInfo().getCompanyId();
        if(comId == null){
            throw new BusinessException(ReturnType.Error, "token解析失败，请重新输入");
        }
        return new Response<>(baseOrgTreeService.getDeptListById(comId, deptId, isIncludeEmp));
    }

    @ApiOperation(value = "根据部门id搜索用户信息", httpMethod = "POST", notes = "根据部门id搜索用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptIds", value = "部门ID集合", required = true, paramType = "body", dataType = "List"),
            @ApiImplicitParam(name = "cascade", value = "是否查询员工0:不是1是", required = true, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "/findEmpInfoByDeptId", method = RequestMethod.POST)
    public Response<List<EmpSimpleInfoVo>> findEmpInfoByDeptId(
                                                               @RequestBody List<Long> deptIds,
                                                               @RequestParam("cascade")Integer cascade) {

        Long comId = UserServletContext.getUserInfo().getCompanyId();
        if(comId == null){
            throw new BusinessException(ReturnType.Error, "token解析失败，请重新输入");
        }
        return new Response<>(baseEmployeeService.findEmpInfoByDeptId(comId, deptIds, cascade));
    }

    @ApiOperation(value = "根据用户名称关键字搜索组织树", httpMethod = "POST", notes = "根据用户名称关键字搜索组织树")
    @RequestMapping(value = "/searchOrgTree", method = RequestMethod.POST)
    public Response<List<OrgTreeNode>> searchOrgTree( @RequestParam("keyword")String keyword) {

        Long comId = UserServletContext.getUserInfo().getCompanyId();
        if(comId == null){
            throw new BusinessException(ReturnType.Error, "token解析失败，请重新输入");
        }
        return new Response<>(baseOrgTreeService.searchOrgTree(comId, keyword));
    }
}
