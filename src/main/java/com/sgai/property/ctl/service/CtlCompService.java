package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlCompDao;
import com.sgai.property.ctl.dto.CtlCompVo;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlUserComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlCompService
 * Description: (机构Service)
 *
 * @author yangyz
 * Date 2017年11月18日
 * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlCompService extends CrudServiceExt<CtlCompDao, CtlComp> {

    @Autowired
    private CtlUserCompService ctlUserCompService;

    public CtlComp get(String id) {
        return super.get(id);
    }

    public List<CtlComp> findList(CtlComp ctlComp) {
        return super.findList(ctlComp);
    }

    public Page<CtlComp> findPage(Page<CtlComp> page, CtlComp ctlComp) {
        return super.findPage(page, ctlComp);
    }

    @Transactional(readOnly = false)
    public void save(CtlComp ctlComp) {
        super.save(ctlComp);
    }

    @Transactional(readOnly = false)
    public void delete(CtlComp ctlComp) {
        super.delete(ctlComp);
    }

    /**
     * saveComp:(新增保存功能项).
     *
     * @param ctlComp
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> saveComp(CtlComp ctlComp) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (ctlComp.getId() != null && !"".equals(ctlComp.getId())) {
            super.save(ctlComp);
            map.put("msg", "success");
        } else {
            CtlComp info = new CtlComp();
            info.setComCode(ctlComp.getComCode());
            List<CtlComp> list = super.findList(info);
            if (list.size() > 0) {
                map.put("msg", "havaData");
            } else {
                ctlComp.setEnabledFlag("Y");
                super.save(ctlComp);
                map.put("msg", "success");
            }
        }

        return map;
    }

    /**
     * deleteComp:(删除机构项).
     *
     * @param ids
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> deleteComp(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        String idss[] = ids.split(",");

        List<CtlComp> list = new ArrayList<CtlComp>();
        List<String> newList = new ArrayList<String>();
        for (String id : idss) {
            if (id != null && !id.equals("")) {
                newList.add(id);
                CtlComp ctlComp = super.get(id);
                list.add(ctlComp);
            }
        }
        boolean flag = false;
        for (CtlComp comp : list) {
            CtlUserComp ctlUserComp = new CtlUserComp();
            ctlUserComp.setComCode(comp.getComCode());
            List<CtlUserComp> comps = ctlUserCompService.findList(ctlUserComp);
            if (comps != null && comps.size() > 0) {
                flag = true;
                break;
            }
        }
        if (flag) {
            map.put("msg", "请先去除机构与管理员的绑定再删除");
            map.put("result", "fail");
        } else {
            List<CtlComp> finalList = batchDelete(list);
            if (finalList.size() > 0) {
                map.put("msg", "删除成功!");
            } else {
                map.put("msg", "删除失败！");
            }
            map.put("result", "success");
        }
        return map;
    }

    /**
     * getAllListComp:(公共下拉列表使用).
     *
     * @param ctlComp
     * @return :Map<Object,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<Object, Object> getAllListComp(CtlComp ctlComp) {
        List<CtlComp> list = super.findList(ctlComp);
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (CtlComp comp : list) {
            map.put(comp.getId(), comp.getComName());
        }
        return map;
    }

    /**
     * getYListComp:(获取可用的机构).
     *
     * @param ctlComp
     * @param request
     * @param response
     * @return :Page<CtlComp>
     * @author yangyz
     * @since JDK 1.8
     */
    public Page<CtlComp> getYListComp(CtlComp ctlComp, HttpServletRequest request, HttpServletResponse response) {
        ctlComp.setEnabledFlag("Y");
        ctlComp.getPage().setOrderBy("createdDt");
        Page<CtlComp> page = super.findPage(new Page<CtlComp>(request, response), ctlComp);
        return page;
    }

    /**
     * findPageForVo:(查询机构列表，重新组装参数).
     *
     * @param page
     * @param ctlComp
     * @return :Page<CtlCompVo>
     * @author yangyz
     * @since JDK 1.8
     */
    public Page<CtlCompVo> findPageForVo(Page<CtlComp> page, CtlComp ctlComp) {
        Page<CtlComp> pages = super.findPage(page, ctlComp);
        Page<CtlCompVo> pageVo = new Page<CtlCompVo>();
        pageVo.setCount(pages.getCount());
        pageVo.setFuncName(pages.getFuncName());
        pageVo.setFuncParam(pages.getFuncParam());
        pageVo.setOrderBy(pages.getOrderBy());
        pageVo.setPageNo(pages.getPageNo());
        pageVo.setPageSize(pages.getPageSize());
        List<CtlCompVo> list = new ArrayList<CtlCompVo>();
        for (CtlComp info : pages.getList()) {
            CtlCompVo vo = new CtlCompVo();
            vo.setId(info.getId());
            vo.setComCode(info.getComCode());
            vo.setComName(info.getComName());
            vo.setComNameAbbr(info.getComNameAbbr());
            String dummyFlag = "";
            if ("Y".equals(info.getDummyFlag())) {
                dummyFlag = "是";
            } else if ("N".equals(info.getDummyFlag())) {
                dummyFlag = "否";
            }
            vo.setDummyFlag(dummyFlag);
            vo.setComAddr(info.getComAddr());
            vo.setBusiCode(info.getBusiCode());
            String enabledFlag = "";
            if ("Y".equals(info.getEnabledFlag())) {
                enabledFlag = "是";
            } else if ("N".equals(info.getEnabledFlag())) {
                enabledFlag = "否";
            }
            vo.setEnabledFlag(enabledFlag);
            list.add(vo);
        }
        pageVo.setList(list);
        return pageVo;
    }

    /**
     * getComTree:(构造机构树).
     *
     * @return :List<Map<String,String>>
     * @author 王天尧
     * @since JDK 1.8
     */
    public List<Map<String, String>> getComTree() {
        List<Map<String, String>> result = Lists.newArrayList();
        @SuppressWarnings("deprecation")
        List<CtlComp> findAllList = dao.findAllList();
        Map<String, String> newMap = Maps.newHashMap();
        newMap.put("id", "U");
        newMap.put("pId", "0");
        newMap.put("name", "机构");
        result.add(newMap);
        for (CtlComp ctlComp : findAllList) {
            Map<String, String> newMap2 = Maps.newHashMap();
            newMap2.put("id", ctlComp.getComCode());
            newMap2.put("pId", "U");
            newMap2.put("name", ctlComp.getComName());
            result.add(newMap2);
        }
        return result;
    }

    public CtlComp getComp(CtlComp ctlComp) {
        return dao.getComp(ctlComp);
    }

    public CtlComp findByCompCode(String compCode) {
        CtlComp info = new CtlComp();
        info.setComCode(compCode);
        return getComp(info);
    }
}
