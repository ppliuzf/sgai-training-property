package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.dao.CtlUserRoleDao;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlUserRoleService
 * Description: (用户和角色关联Service)
 *
 * @author 王天尧
 * Date 2017年12月11日
 * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlUserRoleService extends CrudServiceExt<CtlUserRoleDao, CtlUserRole> {
    @Autowired
    private CtlRoleService ctlRoleService;

    public CtlUserRole get(String id) {
        return super.get(id);
    }

    public List<CtlUserRole> findList(CtlUserRole ctlUserRole) {
        return super.findList(ctlUserRole);
    }

    public Page<CtlUserRole> findPage(Page<CtlUserRole> page, CtlUserRole ctlUserRole) {
        return super.findPage(page, ctlUserRole);
    }

    @Transactional(readOnly = false)
    public void save(CtlUserRole ctlUserRole) {
        super.save(ctlUserRole);
    }

    @Transactional(readOnly = false)
    public void delete(CtlUserRole ctlUserRole) {
        super.delete(ctlUserRole);
    }

    /**
     * saveRoleUsers:(给角色添加用户).
     *
     * @param roleCode    :void  角色代码
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public void saveRoleUsers(List<String> userCodeList, String roleCode) {
        //获取当前登录用户
        LoginUser userInfo = UserServletContext.getUserInfo();
        for (String userCode : userCodeList) {
            CtlUserRole ctlUserRoleNew = new CtlUserRole();
            ctlUserRoleNew.setComCode(userInfo.getComCode());
            ctlUserRoleNew.setRoleCode(roleCode);
            ctlUserRoleNew.setUserCode(userCode);
            save(ctlUserRoleNew);
        }
    }

    /**
     * delete:(移除某一角色的用户).
     *
     * @param ctlUserRole
     * @param userCodes   用户代码集合
     * @param roleCode    :void  角色代码
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public void delete(CtlUserRole ctlUserRole, String userCodes, String roleCode) {
        String[] userCodeArr = userCodes.split(",");
        for (String userCode : userCodeArr) {
            CtlUserRole ctlUserRoleNew = new CtlUserRole();
            ctlUserRoleNew.setRoleCode(roleCode);
            ctlUserRoleNew.setUserCode(userCode);
            List<CtlUserRole> ctlUserRoleList = findList(ctlUserRoleNew);
            delete(ctlUserRoleList.get(0));
        }
    }

    /**
     * getOwnUsers:(获取某角色已经拥有的用户).
     *
     * @param param
     * @return :List<CtlUser>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional
    public List<CtlUser> getOwnUsers(Map<String, String> param) {
        return dao.getOwnUsersByRole(param);
    }

    /**
     * getAllUnUsers:(获得该角色未拥有的用户不分页).
     *
     * @param param
     * @return :List<CtlUser>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional
    public List<CtlUser> getAllUnUsers(Map<String, String> param) {
        return dao.getAllUnUsersByRole(param);
    }

    /**
     * getUnUsers:(获得该角色未拥有的用户带分页).
     *
     * @param param
     * @return :List<CtlUser>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional
    public Page<CtlUser> getUnUsers(Map<String, Object> param, Page<CtlUser> page) {
        param.put("page", page);
        List<CtlUser> ctlUserList = dao.getUnUsersByRole(param);
        page.setList(ctlUserList);
        return page;
    }

    /**
     * getRoleTree:(得到角色树).
     *
     * @return :List<Map<String,String>>
     * @author 王天尧
     * @since JDK 1.8
     */
    @SuppressWarnings("deprecation")
    public List<Map<String, String>> getRoleTree() {
        List<Map<String, String>> result = Lists.newArrayList();
        CtlRole ctlRole = new CtlRole();
        List<CtlRole> roleList = ctlRoleService.findList(ctlRole);
        Map<String, String> newMap = Maps.newHashMap();
        newMap.put("id", "R");
        newMap.put("pId", "0");
        newMap.put("name", "角色");
        result.add(newMap);
        for (CtlRole role : roleList) {
            Map<String, String> newMap2 = Maps.newHashMap();
            newMap2.put("id", role.getRoleCode());
            newMap2.put("pId", "R");
            newMap2.put("name", role.getRoleDesc());
            result.add(newMap2);
        }
        return result;
    }


    /**
     * @param @param  userCode
     * @param @return 参数
     * @return List<String>    返回类型
     * @throws
     * @Title: getRoleCodeByUserCode
     * @Description: 通过userCode获得 用户所在的全部角色
     * @author admin
     */
    @Transactional
    public List<String> getRoleCodeByUserCode(String userCode, String comCode, String moduCode) {
        return dao.getRoleCodeByUserCode(userCode, comCode, moduCode);
    }
}
