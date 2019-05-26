package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.commonService.dto.SgaiDeptDto;
import com.sgai.property.ctl.dao.CtlDeptDao;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.ctl.entity.CtlModu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlDeptService
 * Description: (部门维护Service)
 *
 * @author yangyz
 * Date 2017年11月18日
 * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlDeptService extends CrudServiceExt<CtlDeptDao, CtlDept> {
    @Autowired
    private CtlModuService ctlModuService;
    @Autowired
    private CtlCompService ctlCompService;

    public CtlDept get(String id) {
        return super.get(id);
    }

    public List<CtlDept> findList(CtlDept ctlDept) {
        return super.findList(ctlDept);
    }

    public Page<CtlDept> findPage(Page<CtlDept> page, CtlDept ctlDept) {
        return super.findPage(page, ctlDept);
    }

    @Transactional(readOnly = false)
    public void save(CtlDept ctlDept) {
        super.save(ctlDept);
    }

    @Transactional(readOnly = false)
    public void delete(CtlDept ctlDept) {
        super.delete(ctlDept);
    }

    /**
     * getDeptList:(查询部门树结构).
     *
     * @return :List<Map<String,Object>>
     * @author yangyz
     * @since JDK 1.8
     */
    public List<SgaiDeptDto> getDeptList() {
        List<SgaiDeptDto> result = Lists.newArrayList();
        LoginUser user = UserServletContext.getUserInfo();
        CtlDept ctlDept = new CtlDept();
        ctlDept.preGet();
        List<Map<String, Object>> list = dao.getDeptList(ctlDept);
        if (list.size() == 0) {
            CtlDept info = new CtlDept();
            if (user.getModuCode() != null && !"".equals(user.getModuCode())) {
                CtlModu ctlModu = new CtlModu();
                ctlModu.setSbsCode(user.getModuCode());
                List<CtlModu> modus = ctlModuService.findList(ctlModu);
                info.setDeptCode(user.getModuCode());
                info.setDeptName(modus.get(0).getSbsName());
                info.setParentDeptName(modus.get(0).getSbsName());
                info.setDeptAbbr(modus.get(0).getSbsName());
            } else {
                CtlComp comp = new CtlComp();
                comp.setComCode(user.getComCode());
                List<CtlComp> comps = ctlCompService.findList(comp);
                info.setDeptCode(user.getComCode());
                info.setDeptName(comps.get(0).getComName());
                info.setParentDeptName(comps.get(0).getComName());
                info.setDeptAbbr(comps.get(0).getComName());
            }
            info.setParentDeptCode("0");
            info.setEnabledFlag("Y");
            info.setDisplayOrder(1L);
            super.save(info);
            SgaiDeptDto newMap = new SgaiDeptDto();
            newMap.setId(info.getDeptCode());
            newMap.setpId(info.getParentDeptCode());
            newMap.setName(info.getDeptName());
            result.add(newMap);
        } else {
            for (Map<String, Object> map : list) {
                SgaiDeptDto newMap = new SgaiDeptDto();
                newMap.setId((String) map.get("dept_code"));
                newMap.setpId((String) map.get("parent_dept_code"));
                newMap.setName((String) map.get("dept_name"));
                result.add(newMap);
            }
        }

        return result;
    }

    /**
     * getCtlDept:(查询一条部门信息).
     *
     * @param ctlDept
     * @return :CtlDept
     * @author yangyz
     * @since JDK 1.8
     */
    public CtlDept getCtlDept(CtlDept ctlDept) {
        ctlDept.preGet();
        return dao.getCtlDept(ctlDept);
    }

    /**
     * countDeptCodeExceptSelf:(查询部门代码重复记录).
     *
     * @param entity
     * @return :Integer
     * @author yangyz
     * @since JDK 1.8
     */
    public Integer countDeptCodeExceptSelf(CtlDept entity) {
        entity.preGet();
        return dao.countDeptCodeExceptSelf(entity);
    }

    /**
     * saveDept:(保存部门信息).
     *
     * @param ctlDept
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> saveDept(CtlDept ctlDept, LoginUser user) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (ctlDept.getId() != null && !"".equals(ctlDept.getId())) {
            ctlDept.setComCode(user.getComCode());
            super.save(ctlDept);
            result.put("state", true);
            result.put("msg", "保存成功!");
        } else {
            CtlDept info = new CtlDept();
            info.setDeptCode(ctlDept.getDeptCode());
            List<CtlDept> list = super.findList(info);
            if (list.size() > 0) {
                result.put("state", false);
                result.put("msg", "部门代码已存在!");
            } else {
                ctlDept.setComCode(user.getComCode());
                String deptSeq = getParentCode(ctlDept.getParentDeptCode(), ctlDept.getDeptSeq());
                ctlDept.setDeptSeq(deptSeq);
                super.save(ctlDept);
                result.put("state", true);
                result.put("msg", "保存成功!");
            }
        }
        return result;
    }

    /**
     * getParentCode:(查询父节点Code).
     *
     * @param parentCode
     * @return :String
     * @author yangyz
     * @since JDK 1.8
     */
    public String getParentCode(String parentCode, String deptSeq) {
		/*String[] parents = parentCode.split(",");
		int length = parents.length;*/
        if (deptSeq == null) {
			
			/*CtlDept ctlDept = new CtlDept();
			ctlDept.setDeptCode(parents[length-1]);
			CtlDept info = getDeppt(ctlDept);
			parentCode +=  "," + info.getParentDeptCode();
			return getParentCode(parentCode);*/
            return parentCode;
        } else {
            return deptSeq + parentCode;
        }
    }

    /**
     * getDeppt:(查询部门信息).
     *
     * @param ctlDept
     * @return :CtlDept
     * @author yangyz
     * @since JDK 1.8
     */
    public CtlDept getDeppt(CtlDept ctlDept) {
        ctlDept.preGet();
        return dao.getCtlDept(ctlDept);
    }

    /**
     * deleteDept:(删除部门信息).
     *
     * @param ctlDept
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> deleteDept(CtlDept ctlDept) {
        Map<String, Object> result = Maps.newHashMap();
        CtlDept info = new CtlDept();
        info.setParentDeptCode(ctlDept.getDeptCode());
        List<CtlDept> list = super.findList(info);
        if (list.size() == 0) {
            super.delete(ctlDept);
            result.put("state", true);
            result.put("msg", "删除成功!");
        } else {
            result.put("state", false);
            result.put("msg", "请删除子节点！");
        }
        return result;
    }

    public List<CtlDept> findDeptsByCodes(String deptCodes) {
        List<CtlDept> result = Lists.newArrayList();
        String deptcodes[] = deptCodes.split(",");
        for (String code : deptcodes) {
            if (code != null && !code.equals("")) {
                CtlDept ctlDept = new CtlDept();
                ctlDept.setDeptCode(code);
                CtlDept info = this.getCtlDept(ctlDept);
                result.add(info);
            }
        }
        return result;
    }
}