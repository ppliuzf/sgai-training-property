package com.sgai.property.ctl.service;

import com.github.abel533.echarts.json.GsonOption;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.DateUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.ctl.dao.CtlChartDao;
import com.sgai.property.ctl.entity.CtlChart;
import com.sgai.property.ctl.entity.CtlRoleChart;
import com.sgai.property.ctl.entity.CtlUserChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @author admin
 * @ClassName: CtlChartService
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2018年1月4日
 * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlChartService extends CrudServiceExt<CtlChartDao, CtlChart> {

    @Autowired
    private CtlRoleChartService ctlRoleChartService;
    @Autowired
    private CtlUserChartService ctlUserChartService;
    @Autowired
    private CtlUserRoleService ctlUserRoleService;

    @Autowired
    ApplicationContext context;

    public CtlChart get(String id) {
        return super.get(id);
    }

    public CtlChart findById(String id) {
        return dao.findById(id);
    }

    public List<CtlChart> findList(CtlChart ctlChart) {
        return super.findList(ctlChart);
    }


    /**
     * @param @param  ctlChart
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findListSpecial
     * @Description: 查询chart_type=2的
     * @author admin
     */
    public List<CtlChart> findListSpecial(CtlChart ctlChart) {
        return dao.findListSpecial(ctlChart);
    }

    public Page<CtlChart> findPage(Page<CtlChart> page, CtlChart ctlChart) {
        //return super.findPage(page, ctlChart);
        ctlChart.setPage(page);
        page.setList(dao.findListSpecial(ctlChart));
        return page;
    }

    public List<CtlChart> findAllList() {
        CtlChart chart = new CtlChart();
        return dao.findAllList(chart);
    }

    @Transactional(readOnly = false)
    public void save(CtlChart ctlChart) {
        super.save(ctlChart);
    }

    @Transactional(readOnly = false)
    public void delete(CtlChart ctlChart) {
        super.delete(ctlChart);
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    public List<CtlChart> getDataForSelect(CtlChart ctlChart) {
        return dao.getDataForSelect(ctlChart);
    }

	/*public  List<CtlChart> findChartByRoleCode(Map<String,String> param){
		List<CtlChart>  list = findListByRoleCode(param);
		return list;
	}*/

    public List<CtlChart> findChartByUserCode(String userCode, String comCode, String moduCode, String homeShow) {
        List<CtlChart> list = findListByUserCode(userCode, comCode, moduCode, homeShow);
        return list;
    }

    /**
     * @param @param  userCode
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findListByUserCode
     * @Description: (这里用一句话描述这个方法的作用)
     * @author admin
     */
    public List<CtlChart> findListByUserCode(String userCode, String comCode, String moduCode, String homeShow) {
        return dao.findListByUserCode(userCode, comCode, moduCode, homeShow);
    }

    /**
     * @param @param  roleCode
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findListByRoleCode
     * @Description: 通过roleCode获得已经分配的图表
     * @author admin
     */
    public List<CtlChart> findChartByRoleCode(String arg0, String comCode, String moduCode) {
        return dao.findListByRoleCode(arg0, comCode, moduCode);
    }


    /**
     * @param @param  parentChartCode
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findChartByParentCode
     * @Description: 获得所有的图表 根据图表的关系 组织成递归关系
     * @author admin
     */
    public List<CtlChart> findChartByParentCode() {

        List<CtlChart> indexMenus = dao.findAllList();
        List<CtlChart> rootMenus = new ArrayList<CtlChart>();  //顶级
        for (CtlChart menuF : indexMenus) {
            if ("ROOT".equals(menuF.getParentChartCode())) {
                rootMenus.add(menuF);
            }
            for (CtlChart menuS : indexMenus) {
                if (menuS.getParentChartCode() == menuF.getChartCode()
                        || menuS.getParentChartCode().equals(menuF.getChartCode())) {
                    if (menuF.getChildrenCharts() == null) {
                        List<CtlChart> childrenMenus = new ArrayList<CtlChart>();
                        childrenMenus.add(menuS);
                        menuF.setChildrenCharts(childrenMenus);
                    } else {
                        menuF.getChildrenCharts().add(menuS);
                    }
                }
            }
        }
        return rootMenus;
    }


    /**
     * @param @param  indexMenus  输入的list<CtlChart>
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: changTypeNow
     * @Description: 获得所有的图表 根据图表的关系 组织成递归关系
     * @author admin
     */
    public List<CtlChart> changTypeNow(List<CtlChart> indexMenus) {

        //List<CtlChart> indexMenus = dao.findAllList();
        List<CtlChart> rootMenus = new ArrayList<CtlChart>();  //顶级
        for (CtlChart menuF : indexMenus) {
            if ("ROOT".equals(menuF.getParentChartCode())) {
                rootMenus.add(menuF);
            }
            for (CtlChart menuS : indexMenus) {
                if (menuS.getParentChartCode() == menuF.getChartCode()
                        || menuS.getParentChartCode().equals(menuF.getChartCode())) {
                    if (menuF.getChildrenCharts() == null) {
                        List<CtlChart> childrenMenus = new ArrayList<CtlChart>();
                        childrenMenus.add(menuS);
                        menuF.setChildrenCharts(childrenMenus);
                    } else {
                        menuF.getChildrenCharts().add(menuS);
                    }
                }
            }
        }
        return rootMenus;
    }


    /**
     * @param @param  roleList
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findListByRoleCodeList
     * @Description: 通过角色列表 来获得图表们
     * @author admin
     */
    public List<CtlChart> findListByRoleCodeList(Map<String, Object> params) {
        return dao.findListByRoleCodeList(params);
    }


    /**
     * findAllChartWithoutSelected:(没有被选择的图表们).
     *
     * @param param
     * @return :List<CtlChart>
     * @author admin
     * @since JDK 1.8
     */
    public List<CtlChart> findAllChartWithoutSelected(Map<String, String> param) {
        return dao.findAllChartWithoutSelected(param);
    }

    public String getAllChartWithoutSelectedTotal(Map<String, String> param) {
        return dao.getAllChartWithoutSelectedTotal(param);
    }

    public List<CtlChart> findWithoutSelectedByUser(Map<String, String> param) {
        return dao.findWithoutSelectedByUser(param);
    }

    public String getTotalunUser(Map<String, String> param) {
        return dao.getTotalunUser(param);
    }

    /**
     * @param @param params
     * @param @param roleCode
     * @param @param user    参数
     * @return void    返回类型
     * @throws
     * @Title: saveChartForRole
     * @Description: 保存角色对应的图标关系
     * @author admin
     */
    @Transactional(readOnly = false)
    public void saveChartForRole(String[] params, String roleCode, LoginUser user) {
        //将全部的图表数据 存到set中
        //先删除 roleCode 对应的所有的图标关系
        CtlRoleChart ctlRoleChart = new CtlRoleChart();
        ctlRoleChart.setRoleCode(roleCode);
        ctlRoleChart.setComCode(user.getComCode());
        ctlRoleChart.setModuCode(user.getModuCode());
        List<CtlRoleChart> findList = ctlRoleChartService.findList(ctlRoleChart);
        for (CtlRoleChart ctlRoleChart2 : findList) {
            ctlRoleChartService.delete(ctlRoleChart2);
        }
        if (params.length > 0) {
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < params.length; i++) {
                String[] a;
                if (params.length == 1) {
                    a = params[i].split("\\*");
                } else {
                    a = params[i].split(",");
                }

                set.add(a[0]);
                set.add(a[1]);
            }
            if (set.size() > 0) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    CtlRoleChart ctlRoleChartAdd = new CtlRoleChart();
                    ctlRoleChartAdd.setChartCode(str);
                    ctlRoleChartAdd.setRoleCode(roleCode);
                    ctlRoleChartService.save(ctlRoleChartAdd);
                }
            }
        }
    }


    /**
     * @param @param params
     * @param @param userCode
     * @param @param user    参数
     * @return void    返回类型
     * @throws
     * @Title: saveChartForUser
     * @Description: 保存用户对应的图标关系    同时保存父级关系
     * @author admin
     */
    @Transactional(readOnly = false)
    public void saveChartForUser(String[] params, String userCode, LoginUser user) {
        //先删除 roleCode 对应的所有的图标关系
        CtlUserChart ctlUserChart = new CtlUserChart();
        ctlUserChart.setUserCode(userCode);
        ctlUserChart.setComCode(user.getComCode());
        ctlUserChart.setModuCode(user.getModuCode());
        ctlUserChart.setHomeShow("N");
        List<CtlUserChart> findList = ctlUserChartService.findList(ctlUserChart);
        for (CtlUserChart ctlUserChart2 : findList) {
            ctlUserChartService.delete(ctlUserChart2);
        }
        //将全部的图表数据 存到set中
        if (params.length > 0) {
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < params.length; i++) {
                String[] a;
                if (params.length == 1) {
                    a = params[i].split("\\*");
                } else {
                    a = params[i].split(",");
                }
                set.add(a[0]);
                set.add(a[1]);
            }
            if (set.size() > 0) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    CtlUserChart ctlUserChartAdd = new CtlUserChart();
                    ctlUserChartAdd.setChartCode(str);
                    ctlUserChartAdd.setUserCode(userCode);
                    ctlUserChartAdd.setHomeShow("N");
                    ctlUserChartService.save(ctlUserChartAdd);
                }
            }
        }
    }

    /**
     * @param @param params
     * @param @param user    参数
     * @return void    返回类型
     * @throws
     * @Title: saveChartForHome
     * @Description: (保存首页要展示的图表)
     */
    @Transactional(readOnly = false)
    public void saveChartForHome(String[] params, LoginUser user) {
        //先删除 roleCode 对应的所有的图标关系
        CtlUserChart ctlUserChart = new CtlUserChart();
        ctlUserChart.setUserCode(user.getUserId());
        ctlUserChart.setComCode(user.getComCode());
        ctlUserChart.setModuCode(user.getModuCode());
        ctlUserChart.setHomeShow("Y");
        List<CtlUserChart> findList = ctlUserChartService.findList(ctlUserChart);
        for (CtlUserChart ctlUserChart2 : findList) {
            ctlUserChartService.delete(ctlUserChart2);
        }
        //将全部的图表数据 存到set中
        if (params.length > 0) {
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < params.length; i++) {
                String[] a = params[i].split("&");
                set.add(a[0]);
                set.add(a[1]);
            }
            if (set.size() > 0) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    CtlUserChart ctlUserChartAdd = new CtlUserChart();
                    ctlUserChartAdd.setChartCode(str);
                    ctlUserChartAdd.setUserCode(user.getUserId());
                    ctlUserChartAdd.setHomeShow("Y");
                    ctlUserChartService.save(ctlUserChartAdd);
                }
            }
        }
    }

    /**
     * @param @param  list
     * @param @throws NoSuchMethodException
     * @param @throws SecurityException
     * @param @throws IllegalAccessException
     * @param @throws IllegalArgumentException
     * @param @throws InvocationTargetException    参数
     * @return void    返回类型
     * @throws
     * @Title: getChartOption
     * @Description: 获得图表的option
     * @author admin
     */
    public void getChartOption(List<CtlChart> list) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {  //tab标签
                List<CtlChart> listChildren = list.get(i).getChildrenCharts();
                if (listChildren.size() > 0) {
                    for (int j = 0; j < listChildren.size(); j++) {  //图表
                        String chartUrl = listChildren.get(j).getChartUrl();
                        String[] classAndmethod = chartUrl.split("/");
                        if (classAndmethod.length != 0) {
                            Object bean = context.getBean(classAndmethod[0]);
                            //Object bean = ApplicationContextProvider.getBean(classAndmethod[0]);
                            Class clazz = bean.getClass();
                            Method method = clazz.getDeclaredMethod(classAndmethod[1]);
                            GsonOption option = (GsonOption) method.invoke(bean);
                            listChildren.get(j).setOption(option.toString());
                        }
                    }
                }

            }
        }
    }

    /**
     * @param @param userChart  用户被分配到的图表
     * @param @param roleChart  用户当前角色所拥有的图表
     * @return void    返回类型
     * @throws
     * @Title: tickUserChartFromRoleChart
     * @Description: (这里用一句话描述这个方法的作用)
     * @author admin
     */
    public List<CtlChart> tickUserChartFromRoleChart(String userCode, String comCode, String moduCode) {
        //获得当前用户首页展示的图表
        List<CtlChart> homeList = findListByUserCode(userCode, comCode, moduCode, "Y");
        List<CtlChart> resultCharts = new ArrayList<CtlChart>();
        List<CtlChart> userList = findOwnAllChart(userCode, comCode, moduCode);
        homeList.retainAll(userList);
        for (int i = 0; i < homeList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (homeList.get(i).getChartCode().equals(userList.get(j).getChartCode())) {
                    userList.get(j).setTick("1");//roleList中有当前用户已经分配的
                    break;
                }
            }
        }
        resultCharts = changTypeNow(userList);//变成树形格式
        try {
            getChartOption(resultCharts);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
        }
        return resultCharts;
    }


    @Transactional(readOnly = false)
    public void deleteByIds(String ids) {
        String[] idArray = ids.split(",");
        for (int i = 0; i < idArray.length; i++) {
            deleteById(idArray[i]);
        }
    }

    /**
     * findAlmByTime:区域报警事件数量   ---堆叠柱状图  按照时间段和报警类型名称查询
     *
     * @param param
     * @return :List<Map<String,String>>
     * @author admin
     * @since JDK 1.8
     */
    public List<Map<String, String>> findAlmByTime(Map<String, String> param) {
        return dao.findAlmByTime(param);
    }


    /**
     * paramChangOptional:根据参数 整合查询条件
     *
     * @param timeType
     * @param beginCreatedDt
     * @param endCreatedDt
     * @return :Map<String,String>
     * @author admin
     * @since JDK 1.8
     */
    public Map<String, String> paramChangOptional(String timeType,
                                                  String beginCreatedDt, String endCreatedDt) {
        Map<String, String> map = new HashMap<String, String>();
        //查询时间条件进行处理
        if ("c".equals(timeType)) {
            //查询条件时间
            map.put("startTime", beginCreatedDt);
            map.put("endTime", endCreatedDt);
        } else if ("t".equals(timeType)) {
            //今天
            map = DateUtils.getTodayTime();
        } else if ("l".equals(timeType)) {
            //上周
            map = DateUtils.getLastWeekTime();
        } else if ("lm".equals(timeType)) {
            //上月
            map = DateUtils.getLastMonthTime();
        } else {
            //昨天  前端默认是昨天
            map = DateUtils.getYesterdayTime();
        }
        return map;
    }

    /**
     * findAllAlmType:查找所有的报警类型
     *
     * @return :List<String>
     * @author admin
     * @since JDK 1.8
     */
    public List<String> findAllAlmType() {
        return dao.findAllAlmType();
    }

    /**
     * findAllAlmSpace:所有building 为统计所有楼宇的报警
     *
     * @return :List<String>
     * @author admin
     * @since JDK 1.8
     */
    public List<String> findAllAlmSpace() {
        return dao.findAllAlmSpace();
    }

    /**
     * @param @param  userCode
     * @param @param  comCode
     * @param @param  moduCode
     * @param @return 参数
     * @return List<CtlChart>    返回类型
     * @throws
     * @Title: findOwnAllChart
     * @Description: (查找到当前登录用户所有的图表)
     */
    public List<CtlChart> findOwnAllChart(String userCode, String comCode, String moduCode) {
        // 获得 当前用户所分配到的图表
        List<CtlChart> userList = dao.findTabAndCharttByUserCode(userCode, comCode, moduCode, "N");
        //获得  角色列表
        List<String> roleList = ctlUserRoleService.getRoleCodeByUserCode(userCode, comCode, moduCode);
        // 获得当前用户对应的角色所分配到的图表
        if (roleList.size() > 0) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("comCode", comCode);
            params.put("moduCode", moduCode);
            params.put("list", roleList);
            List<CtlChart> roleChart = findListByRoleCodeList(params);
            userList.removeAll(roleChart);
            userList.addAll(roleChart);
        }
        return userList;
    }
}
