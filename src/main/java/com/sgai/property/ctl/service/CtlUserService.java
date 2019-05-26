package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.CryptUtil;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.dao.CtlAllocMenuDao;
import com.sgai.property.ctl.dao.CtlUserDao;
import com.sgai.property.ctl.dao.IndexMenusDao;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.IndexMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * ClassName: CtlUserService
 * Description: (定义用户的service)
 *
 * @author 王天尧
 * Date 2017年11月20日
 * Company 首自信--智慧城市创新中心
 */

@Service
@Transactional
public class CtlUserService extends CrudServiceExt<CtlUserDao, CtlUser> {

    @Autowired
    private IndexMenusDao indexMenusDao;
    @Autowired
    private CtlEmpService ctlEmpService;
    @Autowired
    private DeleteRulesUtils deleteRulesUtils;
    @Autowired
    private CtlAllocMenuService ctlAllocMenuService;
    @Autowired
    private CtlAllocMenuDao ctlAllocMenuDao;
    @Autowired
    private CtlComRuleService ctlComRuleService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public CtlUser get(String id) {
        return super.get(id);
    }

    public List<CtlUser> findList(CtlUser ctlUser) {
        return super.findList(ctlUser);
    }

    public List<CtlUser> findAllList(CtlUser ctlUser) {
        return dao.findAllList(ctlUser);
    }

    public List<CtlUser> findCompUserList(CtlUser ctlUser) {
        return dao.findMList(ctlUser);
    }

    public List<CtlUser> findCompGLUserList(CtlUser ctlUser) {
        return dao.findGTList(ctlUser);
    }

    public Page<CtlUser> findPage(Page<CtlUser> page, CtlUser ctlUser) {
        return super.findPage(page, ctlUser);
    }

    @Transactional
    public void save(CtlUser ctlUser) {
        super.save(ctlUser);
    }

    @Transactional
    public void delete(CtlUser ctlUser) {
        super.delete(ctlUser);
    }

    @Transactional
    public List<CtlUser> findUserForEventByDeptCode(CtlUser user) {
        return dao.findUserForEventByDeptCode(user);
    }

    /**
     * @param @param  loginName
     * @param @param  rawPassword
     * @param @return 参数
     * @return Boolean    返回类型
     * @throws
     * @Title: getUserByLoginNameAndpassword
     * @Description: (这里用一句话描述这个方法的作用)
     */
    public CtlUser getUserByLoginNameAndpassword(String loginName, String rawPassword) {
        CtlUser user = dao.getUserByLoginName(loginName);
        if (user == null) {
            return null;//没有此登录用户 ;验证失败
        } else {
            String encodePass = user.getUserPass();
            if (CryptUtil.isValid(encodePass, rawPassword, loginName)) {
                return user;//用户和密码验证成功
            } else {
                return null;//密码验证失败
            }
        }
    }


    /**
     * @param @param  userCode
     * @param @return 参数
     * @return List<IndexMenus>    返回类型
     * @throws
     * @Title: getIndexMenus
     * @Description: (这里用一句话描述这个方法的作用)
     */
    public List<IndexMenus> getIndexMenus(String userType, String userCode) {
        //缓存中 没有 从数据库中获得
        List<IndexMenus> userMenuList = indexMenusDao.getUserMenuList(userType, userCode);
        List<IndexMenus> roleMenuList = indexMenusDao.getRoleMenuList(userType, userCode);
        userMenuList.addAll(roleMenuList);
        List<IndexMenus> allMenuList = userMenuList.stream().distinct().sorted(Comparator.comparing(IndexMenus::getDisplayOrder)).collect(Collectors.toList());
        List<IndexMenus> rootMenus = new ArrayList<>();  //顶级
        for (IndexMenus menuF : allMenuList) {
            menuF.setChildrenMenus(new ArrayList<>());
            if ("ROOT".equals(menuF.getParnetMenuCode())) {
                rootMenus.add(menuF);
            }
            for (IndexMenus menuS : allMenuList) {
                if (menuF.getMenuCode().equals(menuS.getParnetMenuCode())) {
                    menuF.getChildrenMenus().add(menuS);
                }
            }
        }
        return rootMenus;
    }

    /**
     * @param arg1    userCode
     * @param arg0    comCode
     * @param @return 参数
     * @return List<IndexMenus>    返回类型
     * @throws
     * @Title: getIndexMenusForIUSER
     * @Description: //I级用户显示菜单
     */
    public List<IndexMenus> getIndexMenusForIUSER(String arg0, String arg1, String arg2) {
        // 缓存没有 从数据库获得
        List<IndexMenus> indexMenus = indexMenusDao.getIndexMenusForIUSER(arg0, arg1, arg2);
        List<IndexMenus> rootMenus = new ArrayList<IndexMenus>();  //顶级
        for (IndexMenus menuF : indexMenus) {
            if ("ROOT".equals(menuF.getParnetMenuCode())) {
                if (menuF.getDefineName() != null) {
                    String defineName = menuF.getDefineName();
                    menuF.setMenuName(defineName);
                }
                rootMenus.add(menuF);
            }
            for (IndexMenus menuS : indexMenus) {
                if (menuS.getParnetMenuCode() == menuF.getMenuCode() || menuS.getParnetMenuCode().equals(menuF.getMenuCode())) {
                    if (menuF.getChildrenMenus() == null) {
                        List<IndexMenus> childrenMenus = new ArrayList<>();
                        if (menuS.getDefineName() != null) {
                            String defineName = menuS.getDefineName();
                            menuS.setMenuName(defineName);
                        }
                        childrenMenus.add(menuS);
                        menuF.setChildrenMenus(childrenMenus);
                    } else {
                        if (menuS.getDefineName() != null) {
                            String defineName = menuS.getDefineName();
                            menuS.setMenuName(defineName);
                        }
                        menuF.getChildrenMenus().add(menuS);
                    }
                }
            }
        }
        if (rootMenus.size() > 0) {

        }


        return rootMenus;
    }

    /**
     * saveMUser:(保存机构管理员/模块管理员).
     *
     * @param ctlUser
     * @param userPass 用户密码
     * @param userCode 用户代码
     * @param map      存储状态的集合
     * @return :Map<String,String>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, String> saveMUser(CtlUser ctlUser, String userCode, String userPass, Map<String, String> map) {
        //获取当前登录用户
        LoginUser userInfo = UserServletContext.getUserInfo();
        ctlUser.getPage().setOrderBy("cteratdDt");
        ctlUser.setEnabledFlag("Y");
        //通过判断对象id是否为空判断是执行插入方法还是更新方法
        if (StringUtils.isBlank(ctlUser.getId())) {
            //用户代码判断唯一性
            CtlUser userByLoginName = dao.getUserByLoginName(userCode);
            if (userByLoginName == null) {
                //对密码进行加密
                String encode = CryptUtil.encode(userPass, userCode);
                ctlUser.setUserPass(encode);
                ctlUser.setComCode(userInfo.getComCode());
                save(ctlUser);
                map.put("msg", "success");
            } else {
                map.put("msg", "repeat");
            }
        } else {
            //更新用户
            CtlUser ctlUser2 = get(ctlUser.getId());
            ctlUser.setComCode(ctlUser2.getComCode());
            ctlUser.setModuCode(ctlUser2.getModuCode());
            save(ctlUser);
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * verificationAndSave:校验.
     *
     * @param id
     * @param passOriginal
     * @param passNew1
     * @param passNew2
     * @return :boolean
     * @author admin
     * @since JDK 1.8
     */
    public boolean verificationAndSave(String id, String passOriginal, String passNew1, String passNew2) {

        CtlUser ctlUser = get(id);
        boolean fg = CryptUtil.isValid(ctlUser.getUserPass(), passOriginal,
                ctlUser.getUserCode());
        if (fg) {
            //密码校验通过  保存新的密码
            String encodePassNew = CryptUtil.encode(passNew1, ctlUser.getUserCode());
            ctlUser.setUserPass(encodePassNew);
            this.save(ctlUser);
            return true;
        } else {
            //密码验证不通过
            return false;
        }
    }

    /**
     * saveCUser:(这里用一句话描述这个方法的作用).
     *
     * @param ctlUser
     * @param userPass 用户密码
     * @param userCode 用户代码
     * @param userType 用户类型
     * @param empCode  员工代码
     * @param map
     * @return :Map<String,String>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, String> saveCUser(CtlUser ctlUser, String userPass, String userCode,
                                         String userType, String empCode, Map<String, String> map) {
        //获取当前登录用户
        LoginUser userInfo = UserServletContext.getUserInfo();
        ctlUser.setComCode(userInfo.getComCode());
        ctlUser.setEnabledFlag("Y");
        //判断用户类型决定是否保存员工属性
        if (!(empCode.equals("")) && userType.equals("I")) {
            CtlEmp ctlEmp = new CtlEmp();
            ctlEmp.setEmpCode(empCode);
            List<CtlEmp> ctlEmpList = ctlEmpService.findList(ctlEmp);
            ctlUser.setCorrCode(ctlEmpList.get(0).getEmpCode());
            ctlUser.setCorrName(ctlEmpList.get(0).getLastname());
            ctlUser.setDeptCode(ctlEmpList.get(0).getDeptCode());
        }
        //通过判断对象id是否为空判断是执行插入方法还是更新方法
        if (ctlUser.getId().equals("")) {
            //用户代码判断唯一性
            CtlUser userByLoginName = dao.getUserByLoginName(userCode);
            if (userByLoginName == null) {
                String encode = CryptUtil.encode(userPass, userCode);
                ctlUser.setUserPass(encode);
                save(ctlUser);
                map.put("msg", "success");
            } else {
                map.put("msg", "repeat");
            }
        } else {
            save(ctlUser);
            map.put("msg", "success");
        }
        return map;
    }

    /**
     * delete:(删除用户).
     *
     * @param userIds :void  用户id集合
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public Map<String, String> delete(String userIds) {
        //将字符串解析为数组
        String ids[] = userIds.split(",");
        Map<String, String> result = new HashMap<String, String>();
        for (String id : ids) {
            if (id != null && !id.equals("")) {
                CtlUser ctlUser = get(id);
                Map<String, String> checkBeforeDelete = deleteRulesUtils.checkBeforeDelete(CtlUser.class, id);
                String value = checkBeforeDelete.get("value");
                String desc = checkBeforeDelete.get("description");
                if (value == "true") {
                    //如果用户分配了菜单，那么将分配的菜单也同时删除
                    CtlAllocMenu ctlAllocMenu = new CtlAllocMenu();
                    ctlAllocMenu.setCorrCode(ctlUser.getUserCode());
                    ctlAllocMenu.setComCode(ctlUser.getComCode());
                    ctlAllocMenuDao.deleteByCode(ctlAllocMenu);
                    delete(ctlUser);
                    result.put("value", value);
                } else {
                    result.put("value", value);
                    result.put("desc", desc);
                }
            }
        }
        return result;
    }

    /**
     * getListUserM:(查询机构管理员列表).
     *
     * @param ctlUser
     * @param page    分页对象
     * @return :Page<CtlUser>
     * @author 王天尧
     * @since JDK 1.8
     */
    @Transactional
    public Page<CtlUser> getListUserM(CtlUser ctlUser, String userName, Page<CtlUser> page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", "1=1");
        ctlUser.setSqlMap(map);
        ctlUser.setUserType("M");
        ctlUser.setUserName(userName);
        ctlUser.setEnabledFlag("Y");
        page.setOrderBy("user_code");
        ctlUser.setPage(page);
        Page<CtlUser> pageList = findMPage(page, ctlUser);
        return pageList;
    }

    public Page<CtlUser> findMPage(Page<CtlUser> page, CtlUser ctlUser) {
        ctlUser.setPage(page);
        page.setList(dao.findMList(ctlUser));
        return page;
    }

    /**
     * @param @param  ctlUser
     * @param @param  userName
     * @param @param  page
     * @param @return 参数
     * @return Page<CtlUser>    返回类型
     * @throws
     * @Title: getListModuUser
     * @Description: (查询模块管理员)
     */
    @Transactional
    public Page<CtlUser> getListModuUser(CtlUser ctlUser, String userCode, String userName, String relevance, String sbsCode, Page<CtlUser> page) {
        Map<String, String> map = new HashMap<String, String>();
        LoginUser user = UserServletContext.getUserInfo();
        if (!"".equals(relevance) && relevance != null) {
            relevance = " AND A.user_code in (select user_code from ctl_user_modu)";
        } else {
            relevance = "";
        }
        if (!"".equals(sbsCode) && sbsCode != null) {
            user.setModuCode(sbsCode);
        }
        map.put("sql", "1=1");
        map.put("sql", map.get("sql") + relevance);
        ctlUser.setSqlMap(map);
        ctlUser.setUserType("MO");
        ctlUser.setUserCode(userCode);
        ctlUser.setUserName(userName);
        ctlUser.setEnabledFlag("Y");
        ctlUser.setModuCode(user.getModuCode());
        page.setOrderBy("user_code");
        ctlUser.setPage(page);
        Page<CtlUser> pageList = findPage(page, ctlUser);
        return pageList;
    }

    @Transactional
    public Page<CtlUser> getUserM(CtlUser ctlUser, Page<CtlUser> page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("sql", "1=1");
        ctlUser.setSqlMap(map);
        ctlUser.getPage().setOrderBy("createdDt");
        Page<CtlUser> pageList = findPage(page, ctlUser);
        return pageList;
    }


    /**
     * getListCUser:(这里用一句话描述这个方法的作用).
     *
     * @param ctlUser
     * @param page
     * @return :Page<CtlUser>
     * @author ASUS
     * @since JDK 1.8
     */
    @Transactional
    public Page<CtlUser> getListCUser(CtlUser ctlUser, String userName, String corrCode, Page<CtlUser> page) {
        //获取当前登录用户
        LoginUser user = UserServletContext.getUserInfo();
        Map<String, String> map = new HashMap<String, String>();
        String sql = "a.user_type in ('I','V') ";
//		AND b.com_code = '"+user.getComCode()+"'
        map.put("sql", sql);
        ctlUser.setUserName(userName);
        ctlUser.setCorrCode(corrCode);
        ctlUser.setEnabledFlag("Y");
        ctlUser.setSqlMap(map);
        ctlUser.getPage().setOrderBy("createdDt");
        return findPage(page, ctlUser);
    }

    /**
     * @param @param  ctlUser
     * @param @param  userCode
     * @param @param  userName
     * @param @param  page
     * @param @return 参数
     * @return Page<CtlUser>    返回类型
     * @throws
     * @Title: getListIUser
     * @Description: (获取内部用户)
     */
    @Transactional
    public Page<CtlUser> getListIUser(CtlUser ctlUser, String userCode, String userName, String empCode, Page<CtlUser> page) {
        //获取当前登录用户
        LoginUser user = UserServletContext.getUserInfo();
        Map<String, String> map = new HashMap<String, String>();
        String sql = "a.user_type = 'I' ";
        map.put("sql", sql);
        ctlUser.setUserCode(userCode);
        ctlUser.setUserName(userName);
        ctlUser.setCorrCode(empCode);
        ctlUser.setEnabledFlag("Y");
        ctlUser.setSqlMap(map);
        ctlUser.getPage().setOrderBy("createdDt");
        return findPage(page, ctlUser);
    }

    /**
     * findById:(通过id查询用户).
     *
     * @param userIds 用户id
     * @return :Map<String,Object>
     * @author ASUS
     * @since JDK 1.8
     */
    @SuppressWarnings("unlikely-arg-type")
    @Transactional
    public Map<String, Object> findById(String userIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] ids = userIds.split(",");
        CtlUser ctlUser = get(ids[0]);
        String startDate = "";
        String endDate = "";
        if (!("").equals(ctlUser.getStartDate()) && ctlUser.getStartDate() != null) {
            startDate = sdf.format(ctlUser.getStartDate());
        }
        if (!("").equals(ctlUser.getEndDate()) && ctlUser.getEndDate() != null) {
            endDate = sdf.format(ctlUser.getEndDate());
        }
        map.put("ctlUser", ctlUser);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }

    /**
     * findNextNodeUserList:(根据事件类型、节点级别查询User).
     *
     * @return :List<CtlUser>
     * @author yangyz
     * @since JDK 1.8
     */
    public List<CtlUser> findNextNodeUserList(Map<String, String> map) {
        List<CtlUser> list = dao.findNextNodeUserList(map);
        return list;
    }


    /**
     * @param @return 参数
     * @return List<CtlUser>    返回类型
     * @throws
     * @Title: getUserBytype
     * @Description: (查询C级用户)
     */
    public List<CtlUser> getUserBytype() {
        return dao.getUserBytype();
    }


    /**
     * 后面 保存用户下的所有的btn权限
     *
     * @param @param  url   页面名称 prog_level为B的 对应的prog_path是页面名称 例如 ctlProgList.html
     * @param @param  userType 用户类型
     * @param @param  arg0  机构编码
     * @param @param  arg1  用户编码
     * @param @return 参数
     * @return List<String>    返回类型
     * @throws
     * @Title: getBtnsForIUser
     * @Description: 获得url页面中的按钮权限
     */
    public List<String> getBtnsForAllUser(String url, LoginUser user) {

        List<String> btnAll = null;
        if ("M".equals(user.getUserType()) || "S".equals(user.getUserType()) || "MO".equals(user.getUserType())) {
            btnAll = indexMenusDao.getBtnsForMUser(user.getUserId(), url);
        } else if ("I".equals(user.getUserType())) {
            btnAll = indexMenusDao.getBtnsForIUser(user.getComCode(), user.getUserId(), url);
        }
        List<String> listBtn = null;
        if (btnAll != null) {
            return btnAll;
        }
        return listBtn;
    }

    /**
     * editPwdByUserName:(通过userName修改用户密码).
     *
     * @param user 用户
     * @return :int
     * @author WKR
     * @since JDK 1.8
     */
    public int editPwdByUserName(CtlUser user) {
        return dao.update(user);
    }


}
