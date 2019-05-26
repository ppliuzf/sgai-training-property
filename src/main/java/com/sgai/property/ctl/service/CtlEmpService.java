package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.dao.CtlEmpDao;
import com.sgai.property.ctl.entity.CtlEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlEmpService
 * Description: (员工Service)
 *
 * @author yangyz
 * Date 2017年11月18日
 * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlEmpService extends CrudServiceExt<CtlEmpDao, CtlEmp> {

    @Autowired
    private DeleteRulesUtils deleteRulesUtils;

    public CtlEmp get(String id) {
        return super.get(id);
    }

    public List<CtlEmp> findList(CtlEmp ctlEmp) {
        return super.findList(ctlEmp);
    }

    public Page<CtlEmp> findPage(Page<CtlEmp> page, CtlEmp ctlEmp) {
        return super.findPage(page, ctlEmp);
    }

    @Transactional(readOnly = false)
    public void save(CtlEmp ctlEmp) {
        super.save(ctlEmp);
    }

    @Transactional(readOnly = false)
    public void delete(CtlEmp ctlEmp) {
        super.delete(ctlEmp);
    }

    /**
     * getEmpByComCodeAndEmpCode:(查询某机构下的一条员工信息).
     *
     * @param arg0
     * @param arg1
     * @return :CtlEmp
     * @author yangyz
     * @since JDK 1.8
     */
    public CtlEmp getEmpByComCodeAndEmpCode(String arg0, String arg1) {
        return dao.getEmpByComCodeAndEmpCode(arg0, arg1);
    }

    /**
     * saveEmp:(新增员工信息).
     *
     * @param ctlEmp
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, Object> saveEmp(CtlEmp ctlEmp, LoginUser user) {
        LoginUser loginUser = UserServletContext.getUserInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        if (ctlEmp.getId() != null && !"".equals(ctlEmp.getId())) {
            ctlEmp.setComCode(user.getComCode());
            ctlEmp.setEnabledFlag("Y");
            super.save(ctlEmp);
            map.put("msg", "success");
        } else {
            CtlEmp info = new CtlEmp();
            info.setEmpCode(ctlEmp.getEmpCode());
            info.setModuCode(loginUser.getModuCode());
            List<CtlEmp> list = dao.findEmpList(info);
            CtlEmp ctlEmps = new CtlEmp();
            ctlEmps.setIdCard(ctlEmp.getIdCard());
            List<CtlEmp> newList = dao.findEmpAllList(ctlEmps);
            if (list.size() > 0) {
                map.put("msg", "havaData");
            } else if (newList.size() > 0 && StringUtils.isBlank(newList.get(0).getIdCard())) {
                map.put("msg", "repeatCard");
            } else {
                ctlEmp.setEnabledFlag("Y");
                ctlEmp.setComCode(user.getComCode());
                super.save(ctlEmp);
                map.put("msg", "success");
            }
        }
        return map;
    }

    /**
     * deleteEmps:(删除员工信息).
     *
     * @param ids
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, Object> deleteEmps(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        String idss[] = ids.split(",");
        List<CtlEmp> list = new ArrayList<CtlEmp>();
        List<String> newList = new ArrayList<String>();
        for (String id : idss) {
            if (id != null && !id.equals("")) {
                newList.add(id);
                CtlEmp ctlEmp = super.get(id);
                list.add(ctlEmp);
            }
        }
        Map<String, String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlEmp.class, newList);
        if ("true".equals(resultMap.get("value"))) {
            List<CtlEmp> finalList = batchDelete(list);
            if (finalList.size() > 0) {
                map.put("msg", "删除成功!");
            } else {
                map.put("msg", "删除失败！");
            }
            map.put("result", "success");
        } else {
            map.put("msg", resultMap.get("description"));
            map.put("result", "fail");
        }
        return map;
    }

    public List<Map<String, Object>> getDeptList() {
        List<Map<String, Object>> result = Lists.newArrayList();
        LoginUser user = UserServletContext.getUserInfo();
        String comCode = user.getComCode();
        CtlEmp ctlEmp = new CtlEmp();
        new CtlEmp().setComCode(comCode);
        List<CtlEmp> list = findList(ctlEmp);
        CtlEmp info = new CtlEmp();
        if (list.size() == 0) {
            Map<String, Object> newMap = Maps.newHashMap();
            newMap.put("id", list.get(0).getComCode());
            newMap.put("name", list.get(0).getDeptName());
            result.add(newMap);
        } else {
            for (CtlEmp map : list) {
                Map<String, Object> newMap = Maps.newHashMap();
                newMap.put("id", map.getDeptCode());
                newMap.put("name", map.getDeptName());
                result.add(newMap);
            }
        }
        return result;
    }

    /**
     * @param @return 参数
     * @return List<CtlEmp>    返回类型
     * @throws
     * @Title: findLackList
     * @Description: (获取未被选择的员工)
     */
    public Page<CtlEmp> findLackList(String empName, Page<CtlEmp> page) {
        CtlEmp ctlEmp = new CtlEmp();
        ctlEmp.preGet();
        ctlEmp.setPage(page);
        ctlEmp.setLastname(empName);
        return page.setList(dao.findLackList(ctlEmp));

    }
}
