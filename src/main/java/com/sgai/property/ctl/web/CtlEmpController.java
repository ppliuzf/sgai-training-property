package com.sgai.property.ctl.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.ctl.service.CtlEmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlEmpController Description: (员工维护)
 *
 * @author yangyz Date 2017年11月18日 Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/emp/ctlEmp")
@Api(description = "员工管理")
public class CtlEmpController extends BaseController {

    @Autowired
    private CtlEmpService ctlEmpService;

    /**
     * getListEmp:(为List页面返回数据列表).
     *
     * @param ctlEmp
     * @param request
     * @param response
     * @return
     * @throws IOException :Page<CtlEmp>
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "获得员工分页列表", httpMethod = "POST", notes = "获得员工分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "部门代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "empCode", value = "员工代码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lastname", value = "员工名称", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getListEmp", method = RequestMethod.POST)
    public CommonResponse getListEmp(
            LoginUser user,
            String deptCode,
            String empCode,
            String lastname,

            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        CtlEmp ctlEmp = new CtlEmp();
        ctlEmp.setDeptCode(deptCode);
        ctlEmp.setEmpCode(empCode);
        ctlEmp.setLastname(lastname);
        ctlEmp.setComCode(user.getComCode());
        Page<CtlEmp> page = ctlEmpService.findPage(new Page<CtlEmp>(pageNo, pageSize), ctlEmp);
        return ResponseUtil.successResponse(page);
    }

    @ApiOperation(value = "获得未被选择的员工分页列表", httpMethod = "POST", notes = "获得未被选择的员工分页列表")
    @RequestMapping(value = "/findLackList", method = RequestMethod.POST)
    public CommonResponse findLackList(
            @RequestParam(value = "pageNo", required = true, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            String empName
    ) throws IOException {
        Page<CtlEmp> page = ctlEmpService.findLackList(empName,new Page<>(pageNo, pageSize));
        return ResponseUtil.successResponse(page);
    }

    /**
     * getAllEmp:(获取一个实体对象).
     *
     * @param request
     * @param id
     * @param response
     * @return
     * @throws IOException :CtlEmp
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "获取员工", httpMethod = "POST", notes = "获取员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getEmp", method = RequestMethod.POST)
    public CommonResponse getAllEmp(
            String id,

            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        CtlEmp ctlEmp = ctlEmpService.get(id);
        return ResponseUtil.successResponse(ctlEmp);
    }

    /**
     * getEmp:(公共下拉，得到所有员工).
     *
     * @param ctlEmp
     * @param request
     * @param response
     * @return
     * @throws IOException :List<CtlEmp>
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "获得员工信息", httpMethod = "POST", notes = "获得所有员工")
    @RequestMapping(value = "/getAllEmp", method = RequestMethod.POST)
    public CommonResponse getEmp(
            CtlEmp ctlEmp,

            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        List<CtlEmp> ctlEmpList = ctlEmpService.findList(ctlEmp);
        return ResponseUtil.successResponse(ctlEmpList);
    }

    /**
     * save:(新增员工信息).
     *
     * @param ctlEmp
     * @param model
     * @param redirectAttributes
     * @return :Map<String,Object>
     * @throws IOException
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "保存员工信息", httpMethod = "POST", notes = "保存员工信息")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public CommonResponse save(
            LoginUser user,
            CtlEmp ctlEmp,

            RedirectAttributes redirectAttributes
    ) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = ctlEmpService.saveEmp(ctlEmp, user);
        } catch (Exception e) {
            // : handle exception
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return ResponseUtil.successResponse(map);
    }

    /**
     * delete:(删除员工信息).
     *
     * @param ids
     * @param redirectAttributes
     * @return :Map<String,Object>
     * @throws IOException
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "删除员工信息", httpMethod = "POST", notes = "删除员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public CommonResponse delete(
            String ids,

            RedirectAttributes redirectAttributes
    ) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = ctlEmpService.deleteEmps(ids);
        } catch (Exception e) {
            // : handle exception
            e.printStackTrace();
            map.put("msg", "出错了！");
            map.put("result", "fail");
        }
        return ResponseUtil.successResponse(map);
    }

    /**
     * getDeptList:(无token请求部门树结构).
     *
     * @return :List<Map<String,Object>>
     * @author yangyz
     * @since JDK 1.8
     */
    @ApiOperation(value = "无token请求部门树结构", httpMethod = "GET", notes = "无token请求部门树结构")
    @RequestMapping(value = "getDeptList", method = RequestMethod.GET)
    public List<Map<String, Object>> getDeptList(
            LoginUser user
    ) {
        return ctlEmpService.getDeptList();
    }
}
