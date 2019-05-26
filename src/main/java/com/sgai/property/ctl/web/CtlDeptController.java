package com.sgai.property.ctl.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.commonService.dto.SgaiDeptDto;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.ctl.service.CtlDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlDeptController
 * Description: (部门维护)
 *
 * @author yangyz
 * Date 2017年11月18日
 * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/dept/ctlDept")
@Api(description = "部门管理")
public class CtlDeptController extends BaseController {

    @Autowired
    private CtlDeptService ctlDeptService;

    /**
     * getDeptList:(无token请求部门树结构).
     *
     * @return :List<Map<String,Object>>
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "无token请求部门树结构", httpMethod = "GET", notes = "无token请求部门树结构")
    @RequestMapping(value = "getDeptList", method = RequestMethod.GET)
    public List<SgaiDeptDto> getDeptList() {
        List<SgaiDeptDto> list;
        list = ctlDeptService.getDeptList();
        return list;
    }

    /**
     * getDeptTree:(带token请求部门树结构).
     *
     * @param user
     * @param token
     * @return
     * @throws JsonProcessingException :CommonResponse
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "带token请求部门树结构", httpMethod = "GET", notes = "带token请求部门树结构")
    @RequestMapping(value = "getDeptTree", method = RequestMethod.GET)
    public CommonResponse getDeptTree(
            LoginUser user,
            @RequestHeader("token") String token
    ) throws JsonProcessingException {
        List<SgaiDeptDto> list = ctlDeptService.getDeptList();
        return ResponseUtil.successResponse(list);
    }

    /**
     * form:(获取部门信息).
     *
     * @param ctlDept
     * @return :CtlDept
     * @throws JsonProcessingException
     * @author yangyz
     * @since JDK 1.8
     */

    @ApiOperation(value = "获取部门接口", httpMethod = "POST", notes = "获取部门接口")
    @RequestMapping(value = "form", method = RequestMethod.POST)
    public CommonResponse form(
            CtlDept ctlDept,
            @RequestHeader("token") String token
    ) throws JsonProcessingException {
        CtlDept dept = ctlDeptService.getCtlDept(ctlDept);
        return ResponseUtil.successResponse(dept);
    }

    /**
     * save:(保存部门信息).
     *
     * @param ctlDept
     * @param redirectAttributes
     * @return :Map<String,Object>
     * @throws JsonProcessingException
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "保存部门信息", httpMethod = "POST", notes = "保存部门信息")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public CommonResponse save(
            LoginUser user,
            CtlDept ctlDept,

            RedirectAttributes redirectAttributes
    ) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = ctlDeptService.saveDept(ctlDept, user);
        } catch (Exception e) {
            // : handle exception
            e.printStackTrace();
            result.put("state", false);
            result.put("msg", "保存失败!");
        }
        return ResponseUtil.successResponse(result);
    }

    /**
     * delete:(删除部门信息).
     *
     * @param ctlDept
     * @param redirectAttributes
     * @return :Map<String,Object>
     * @throws JsonProcessingException
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "删除部门信息", httpMethod = "POST", notes = "删除部门信息")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public CommonResponse delete(
            CtlDept ctlDept,

            RedirectAttributes redirectAttributes
    ) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = ctlDeptService.deleteDept(ctlDept);
        } catch (Exception e) {
            // : handle exception
            e.printStackTrace();
            result.put("state", false);
            result.put("msg", "删除失败！");
        }
        return ResponseUtil.successResponse(result);
    }

    /**
     * findChildDepts:(根据部门代码查询下级部门).
     *
     * @param user
     * @param deptCode
     * @param token
     * @return
     * @throws JsonProcessingException :CommonResponse
     * @author lenovo
     * @since JDK 1.8
     */
    @ApiOperation(value = "查询部门子节点", httpMethod = "POST", notes = "查询部门子节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "部门代码", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "findChildDepts", method = RequestMethod.POST)
    public CommonResponse findChildDepts(
            LoginUser user,
            String deptCode,
            @RequestHeader("token") String token
    ) throws JsonProcessingException {
        List<Map<String, Object>> result = Lists.newArrayList();

        CtlDept dept = new CtlDept();
        dept.setParentDeptCode(deptCode);
        List<CtlDept> list = ctlDeptService.findList(dept);
        for (CtlDept info : list) {
            Map<String, Object> newMap = new HashMap<String, Object>();
            newMap.put("id", info.getDeptCode());
            newMap.put("pId", info.getParentDeptCode());
            newMap.put("name", info.getDeptName());
            result.add(newMap);
        }
        return ResponseUtil.successResponse(result);
    }

    @ApiOperation(value = "多部门代码批量查询部门信息", httpMethod = "POST", notes = "多部门代码批量查询部门信息，逗号分隔")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCodes", value = "多部门代码", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "findDeptsByCodes", method = RequestMethod.POST)
    public CommonResponse findDeptsByCodes(
            LoginUser user,
            String deptCodes,
            @RequestHeader("token") String token
    ) throws JsonProcessingException {
        List<CtlDept> result = Lists.newArrayList();
        String deptcodes[] = deptCodes.split(",");
        for (String code : deptcodes) {
            if (code != null && !code.equals("")) {
                CtlDept ctlDept = new CtlDept();
                ctlDept.setDeptCode(code);
                CtlDept info = ctlDeptService.getCtlDept(ctlDept);
                result.add(info);
            }
        }
        return ResponseUtil.successResponse(result);
    }

}