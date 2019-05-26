package com.sgai.property.wy.web;

import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.DataAuthority;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.Visitor;
import com.sgai.property.wy.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LiuXiaobing
 * @ClassName: VisitorController
 * (访客记录    -- 管理服务入口)
 * @date 2018年1月18日
 * @Company 首自信--智慧城市创新中心
 */

@RestController
@RequestMapping("/visitor")
public class VisitorController extends BaseController {

    @Autowired
    private VisitorService visitorService;

    // 查询所有数据并分页
    @DataAuthority(tableAlias = "v")
    @RequestMapping(value = "/visitorList", method = RequestMethod.POST)
    @PermessionLimit(limit = false)
    public CommonResponse getListVisitor(@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo, @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, Visitor visitor) throws IOException, ServletException {
        if (visitor.getEndTime() != null) {
            Date time = visitor.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
            visitor.setEndTime(c.getTime());
        }
        Page<Visitor> page = visitorService.findPage(new Page<Visitor>(pageNo, pageSize), visitor);
        return ResponseUtil.successResponse(page);
    }

    // 批量删除数据
    @RequestMapping("/deleteVisitor")
    @Transactional
    public Map<String, Object> deleteVisitor(Visitor visitor) throws ServletException, IOException {
        Map<String, Object> result = Maps.newHashMap();
        try {
            String ids = visitor.getId();
            String[] idArray = ids.split(",");
            // 逻辑删除
            for (String id : idArray) {
                if (id != null && !id.equals("")) {
                    visitor = visitorService.get(id);
                    visitor.setDisplay("N");
                    visitorService.save(visitor);
                }
            }
            result.put("state", true);
            result.put("msg", "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", false);
            result.put("msg", "删除失败!");
        }
        return result;
    }

    // 修改/添加数据
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResponse save(Visitor visitor, LoginUser loginUser) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            visitor.setDisplay("Y");
            visitor.setComCode(loginUser.getComCode());
            visitor.setDeptCode(loginUser.getDeptCode());
            visitorService.save(visitor);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return ResponseUtil.successResponse(map);
    }

    // 查询单条数据
    @RequestMapping(value = "/findVisitorById", method = RequestMethod.POST)
    public CommonResponse getVisitor(String id) throws IOException, ServletException {
        Visitor visitor = visitorService.get(id);
        return ResponseUtil.successResponse(visitor);
    }

    // 导出数据到EXCEL
    @DataAuthority(tableAlias = "v")
    @RequestMapping(value = "/visitorExport", method = RequestMethod.GET)
    @PermessionLimit(limit = false)
    public void export(Visitor visitor, HttpServletResponse response) throws IOException {
        visitorService.export(visitor, response);
    }

}
