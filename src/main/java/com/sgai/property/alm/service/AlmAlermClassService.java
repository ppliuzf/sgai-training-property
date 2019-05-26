package com.sgai.property.alm.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.alm.dao.AlmAlermClassDao;
import com.sgai.property.alm.entity.AlmAlermClass;
import com.sgai.property.ctl.service.CtlComRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 报警分类Service
 *
 * @author 王天尧
 * @version 2017-11-24
 */
@Service
@Transactional
public class AlmAlermClassService extends CrudServiceExt<AlmAlermClassDao, AlmAlermClass> {
    @Autowired
    private CtlComRuleService ctlComRuleService;
    @Autowired
    AlmAlermClassService almAlermClassService;

    public AlmAlermClass get(String id) {
        return super.get(id);
    }

    public List<AlmAlermClass> findList(AlmAlermClass almAlermClass) {
        return super.findList(almAlermClass);
    }

    public Page<AlmAlermClass> findPage(Page<AlmAlermClass> page, AlmAlermClass almAlermClass) {
        return super.findPage(page, almAlermClass);
    }

    @Transactional(readOnly = false)
    public void save(AlmAlermClass almAlermClass) {
        super.save(almAlermClass);
    }

    @Transactional(readOnly = false)
    public void delete(AlmAlermClass almAlermClass) {
        super.delete(almAlermClass);
    }

    /**
     * saveAlmAlerm:(保存报警分类).
     *
     * @param almAlermClass
     * @param result        存放验证结果的map集合
     * @return :Map<String,String>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, String> saveAlmAlerm(AlmAlermClass almAlermClass, Map<String, String> result) {

        //根据id是否为空判断是增加还是修改
        if (almAlermClass.getId().equals("")) {
            AlmAlermClass almAlermClass1 = new AlmAlermClass();
            List<AlmAlermClass> almAlermClassList = almAlermClassService.findList(almAlermClass1);
            for (AlmAlermClass list : almAlermClassList) {
                if (almAlermClass.getAlermTypeName().equals(list.getAlermTypeName())) {
                    result.put("msg", "havName");
                    return result;
                }
                if (almAlermClass.getAlermTypeDesc().equals(list.getAlermTypeDesc())) {
                    result.put("msg", "havDesc");
                    return result;
                }
            }
            //新增
            LoginUser user = UserServletContext.getUserInfo();
            String code = ctlComRuleService.getNext(user.getComCode(), "ALMCODE-TYPE");
            almAlermClass.setAlermTypeCode(code);
            almAlermClass.setEnabledFlag("Y");
            save(almAlermClass);
            result.put("msg", "success");
        } else {
            //修改
            AlmAlermClass almAlermClass2 = get(almAlermClass.getId());
            almAlermClass.setAlermTypeCode(almAlermClass2.getAlermTypeCode());
            almAlermClass.setEnabledFlag("Y");
            List<AlmAlermClass> almAlermClassList = almAlermClassService.findList(almAlermClass);
            for (AlmAlermClass list : almAlermClassList) {
                if (almAlermClass.getAlermTypeName().equals(list.getAlermTypeName())) {
                    result.put("msg", "havName");
                    return result;
                } else if (almAlermClass.getAlermTypeDesc().equals(list.getAlermTypeDesc())) {
                    result.put("msg", "havDesc");
                    return result;
                }

            }
            save(almAlermClass);
            result.put("msg", "success");
        }
        return result;
    }
}
