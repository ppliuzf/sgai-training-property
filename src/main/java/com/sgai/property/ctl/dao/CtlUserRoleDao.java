package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CtlUserRoleDao
 * Description: ( 用户和角色关联DAO接口)
 *
 * @author 王天尧
 * Date 2017年12月11日
 * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlUserRoleDao extends CrudDao<CtlUserRole> {
    List<CtlUser> getOwnUsersByRole(Map<String, String> param);

    List<CtlUser> getUnUsersByRole(Map<String, Object> param);

    List<CtlUser> getAllUnUsersByRole(Map<String, String> param);

    List<String> getRoleCodeByUserCode(String arg0, String arg1, String arg2);

}