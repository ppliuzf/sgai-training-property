package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.IndexMenus;

/**
 * @author admin
 * @ClassName: IndexMenusDao
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2017年12月25日
 * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface IndexMenusDao extends CrudDao<IndexMenus> {


    /**
     * getIndexMenus:(这里用一句话描述这个方法的作用).
     *
     * @param arg1 userCode
     * @param arg0 comCode
     * @return :List<IndexMenus>
     * @author admin
     * @since JDK 1.8
     */
    List<IndexMenus> getUserMenuList(@Param("userType") String userType, @Param("userCode") String userCode);

    List<IndexMenus> getRoleMenuList(@Param("userType") String userType, @Param("userCode") String userCode);


    /**
     * getIndexMenusForIUSER:(这里用一句话描述这个方法的作用).
     *
     * @param arg0 userCode
     * @param arg1 comCode
     * @return :List<IndexMenus>
     * @author admin
     * @since JDK 1.8
     */
    List<IndexMenus> getIndexMenusForIUSER(String arg0, String arg1, String arg2);

    List<IndexMenus> getRootMenus();

    /**
     * @param @param  arg0 userCode
     * @param @param  arg1 url
     * @param @return 参数
     * @return List<String>    返回类型
     * @throws
     * @Title: getBtnsForMUser
     * @Description: (这里用一句话描述这个方法的作用)
     */
    List<String> getBtnsForMUser(String arg0, String arg1);


    /**
     * @param @param  arg0  comCode
     * @param @param  arg1  userCode
     * @param @param  arg2  url
     * @param @return 参数
     * @return List<String>    返回类型
     * @throws
     * @Title: getBtnsForIUser
     * @Description: (这里用一句话描述这个方法的作用)
     */
    List<String> getBtnsForIUser(String arg0, String arg1, String arg2);
}
