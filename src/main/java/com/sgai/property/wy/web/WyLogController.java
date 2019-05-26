
/**
 * @Title: LogController.java
 * @Package com.sgai.property.wy.web
 * (用一句话描述该文件做什么)
 * @author Lenovo
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.DataAuthority;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.property.wy.entity.WyLog;
import com.sgai.property.wy.service.CallService;
import com.sgai.property.wy.service.WyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heibin
 * @ClassName: LogController
 * (事件管理-日志管理)
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping("/wyLog")
public class WyLogController extends BaseController {
    @Autowired
    private WyLogService wyLogService;
    @Autowired
    private CtlUserService userService;

    @Autowired
    private CallService callService;

    // 查询所有数据并分页
    @DataAuthority(tableAlias = "v")
    @RequestMapping(value = "/wyLogList", method = RequestMethod.POST)
    @PermessionLimit(limit = false)
    public CommonResponse getListWyLog(
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, WyLog wyLog, LoginUser user
    ) throws IOException, ServletException {

        if (wyLog.getType() == 2) {
            //处理页面跳转
            // wyLog.setUserId(user.getUserId());
            wyLog.setProcessingPersonId(user.getUserId());
        }
        Page<WyLog> page = wyLogService.findPage(new Page<WyLog>(pageNo, pageSize), wyLog);
        return ResponseUtil.successResponse(page);
    }

    /**
     * save
     *
     * @return :Map<String,Object>
     * @throws IOException
     * @throws ServletException
     * @author HEIBIN
     * @since JDK 1.8
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResponse save(WyLog wyLog, LoginUser user) throws ServletException, IOException {
        //区域代码处理
        String areaCode = "";
        if (StringUtils.isNotEmpty(wyLog.getRepairAddress1())) {
            areaCode += wyLog.getRepairAddress1();
        }
        if (StringUtils.isNotEmpty(wyLog.getRepairAddress2())) {
            areaCode += "-" + wyLog.getRepairAddress2();
        }
        if (StringUtils.isNotEmpty(wyLog.getRepairAddress3())) {
            areaCode += "-" + wyLog.getRepairAddress3();
        }
        wyLog.setAreaCode(areaCode);
        Map<String, Object> map = new HashMap<String, Object>();
        wyLog.setCreatedBy(user.getUserId());
        wyLog.setComCode(user.getComCode());
        wyLog.setDeptCode(user.getDeptCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String userCode = wyLog.getProcessingPersonId();
        if(userCode != null){
            CtlUser cc = new CtlUser();
            cc.setUserCode(userCode);
            List<CtlUser> lists = userService.findList(cc);
            wyLog.setProcessingPersonName(lists.get(0).getUserName());
        }
        if (wyLog.getId() != null && !"".equals(wyLog.getId())) {
            //修改
            String a = wyLog.getId().replace(",", "");
            wyLog.setId(a);
        } else {
            //新增
            wyLog.setUserId(user.getUserId());//记录人
        }
        if (wyLog.getDescribes() != null && !"".equals(wyLog.getDescribes())) {
            String b[] = wyLog.getDescribes().split(",");
            wyLog.setDescribes(b[0]);
            wyLog.setProcessingPersonId(user.getUserId());//处理人
        }
        int a = -1;
        int b = -1;
        if (wyLog.getHour() != null && !"".equals(wyLog.getHour())) {
            a = Integer.parseInt(wyLog.getHour());
        }
        if (wyLog.getMinute() != null && !"".equals(wyLog.getMinute())) {
            b = Integer.parseInt(wyLog.getMinute());
        }
        String c = "";
        if (-1 < a && a < 10) {
            c = "0" + wyLog.getHour();
        } else if (a >= 10) {
            c = wyLog.getHour();
        }
        if (-1 < b && b < 10) {
            c = c + ":" + "0" + wyLog.getMinute();
        } else if (b >= 10) {
            c = c + ":" + wyLog.getMinute();
        }
        if (!"".equals(c)) {
            wyLog.setHour(c);
        }
        if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
            wyLog.setArea(wyLog.getRepairAddress());
        }
        try {

            if (wyLog.getCreateTime() != null) {
                String d = sdf.format(wyLog.getCreateTime());
                d = d + " " + c + ":00";
                wyLog.setCreateTime(sdf1.parse(d));

            }
            wyLogService.save(wyLog);
            //新增

            if (wyLog.getContent1() != null && !"".equals(wyLog.getContent1())) {
                WyLog wyLog1 = new WyLog();
                wyLog1.setComCode(user.getComCode());
                wyLog1.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog1.setArea(wyLog.getRepairAddress());
                }
                wyLog1.setUserId(user.getUserId());
                //wyLog1.setCreateTime(wyLog.getCreateTime());
                wyLog1.setContent(wyLog.getContent1());
                int a1 = -1;
                int b1 = -1;
                if (wyLog.getHour1() != null && !"".equals(wyLog.getHour1())) {
                    a1 = Integer.parseInt(wyLog.getHour1());
                }
                if (wyLog.getMinute1() != null && !"".equals(wyLog.getMinute1())) {
                    b1 = Integer.parseInt(wyLog.getMinute1());
                }
                String c1 = "";
                if (-1 < a1 && a1 < 10) {
                    c1 = "0" + wyLog.getHour1();
                } else if (a1 >= 10) {
                    c1 = wyLog.getHour1();
                }
                if (-1 < b1 && b1 < 10) {
                    c1 = c1 + ":" + "0" + wyLog.getMinute1();
                } else if (b1 >= 10) {
                    c1 = c1 + ":" + wyLog.getMinute1();
                }
                if (!"".equals(c1)) {
                    wyLog1.setHour(c1);
                }
                String d1 = sdf.format(wyLog.getCreateTime());
                d1 = d1 + " " + c1 + ":00";
                wyLog1.setCreateTime(sdf1.parse(d1));
                wyLogService.save(wyLog1);//修改
            }
            if (wyLog.getContent2() != null && !"".equals(wyLog.getContent2())) {
                WyLog wyLog2 = new WyLog();
                wyLog2.setComCode(user.getComCode());
                wyLog2.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog2.setArea(wyLog.getRepairAddress());
                }
                wyLog2.setUserId(user.getUserId());
                //wyLog2.setCreateTime(wyLog.getCreateTime());
                wyLog2.setContent(wyLog.getContent2());
                int a2 = -1;
                int b2 = -1;
                if (wyLog.getHour2() != null && !"".equals(wyLog.getHour2())) {
                    a2 = Integer.parseInt(wyLog.getHour2());
                }
                if (wyLog.getMinute2() != null && !"".equals(wyLog.getMinute2())) {
                    b2 = Integer.parseInt(wyLog.getMinute2());
                }
                String c2 = "";
                if (-1 < a2 && a2 < 10) {
                    c2 = "0" + wyLog.getHour2();
                } else if (a2 >= 10) {
                    c2 = wyLog.getHour2();
                }
                if (-1 < b2 && b2 < 10) {
                    c2 = c2 + ":" + "0" + wyLog.getMinute2();
                } else if (b2 >= 10) {
                    c2 = c2 + ":" + wyLog.getMinute2();
                }
                if (!"".equals(c2)) {
                    wyLog2.setHour(c2);
                }
                String d2 = sdf.format(wyLog.getCreateTime());
                d2 = d2 + " " + c2 + ":00";
                wyLog2.setCreateTime(sdf1.parse(d2));
                wyLogService.save(wyLog2);//修改
            }
            if (wyLog.getContent3() != null && !"".equals(wyLog.getContent3())) {
                WyLog wyLog3 = new WyLog();
                wyLog3.setComCode(user.getComCode());
                wyLog3.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog3.setArea(wyLog.getRepairAddress());
                }
                wyLog3.setUserId(user.getUserId());
                //wyLog3.setCreateTime(wyLog.getCreateTime());
                wyLog3.setContent(wyLog.getContent3());
                int a3 = -1;
                int b3 = -1;
                if (wyLog.getHour3() != null && !"".equals(wyLog.getHour3())) {
                    a3 = Integer.parseInt(wyLog.getHour3());
                }
                if (wyLog.getMinute3() != null && !"".equals(wyLog.getMinute3())) {
                    b3 = Integer.parseInt(wyLog.getMinute3());
                }
                String c3 = "";
                if (-1 < a3 && a3 < 10) {
                    c3 = "0" + wyLog.getHour3();
                } else if (a3 >= 10) {
                    c3 = wyLog.getHour3();
                }
                if (-1 < b3 && b3 < 10) {
                    c3 = c3 + ":" + "0" + wyLog.getMinute3();
                } else if (b3 >= 10) {
                    c3 = c3 + ":" + wyLog.getMinute3();
                }
                if (!"".equals(c3)) {
                    wyLog3.setHour(c3);
                }
                String d3 = sdf.format(wyLog.getCreateTime());
                d3 = d3 + " " + c3 + ":00";
                wyLog3.setCreateTime(sdf1.parse(d3));
                wyLogService.save(wyLog3);//修改
            }
            if (wyLog.getContent4() != null && !"".equals(wyLog.getContent4())) {
                WyLog wyLog4 = new WyLog();
                wyLog4.setComCode(user.getComCode());
                wyLog4.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog4.setArea(wyLog.getRepairAddress());
                }
                wyLog4.setUserId(user.getUserId());
                //wyLog4.setCreateTime(wyLog.getCreateTime());
                wyLog4.setContent(wyLog.getContent4());
                int a4 = -1;
                int b4 = -1;
                if (wyLog.getHour4() != null && !"".equals(wyLog.getHour4())) {
                    a4 = Integer.parseInt(wyLog.getHour4());
                }
                if (wyLog.getMinute4() != null && !"".equals(wyLog.getMinute4())) {
                    b4 = Integer.parseInt(wyLog.getMinute4());
                }
                String c4 = "";
                if (-1 < a4 && a4 < 10) {
                    c4 = "0" + wyLog.getHour4();
                } else if (a4 >= 10) {
                    c4 = wyLog.getHour4();
                }
                if (-1 < b4 && b4 < 10) {
                    c4 = c4 + ":" + "0" + wyLog.getMinute4();
                } else if (b4 >= 10) {
                    c4 = c4 + ":" + wyLog.getMinute4();
                }
                if (!"".equals(c4)) {
                    wyLog4.setHour(c4);
                }
                String d4 = sdf.format(wyLog.getCreateTime());
                d4 = d4 + " " + c4 + ":00";
                wyLog4.setCreateTime(sdf1.parse(d4));
                wyLogService.save(wyLog4);//修改
            }
            if (wyLog.getContent5() != null && !"".equals(wyLog.getContent5())) {
                WyLog wyLog5 = new WyLog();
                wyLog5.setComCode(user.getComCode());
                wyLog5.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog5.setArea(wyLog.getRepairAddress());
                }
                wyLog5.setUserId(user.getUserId());
                //wyLog5.setCreateTime(wyLog.getCreateTime());
                wyLog5.setContent(wyLog.getContent5());
                int a5 = -1;
                int b5 = -1;
                if (wyLog.getHour5() != null && !"".equals(wyLog.getHour5())) {
                    a5 = Integer.parseInt(wyLog.getHour5());
                }
                if (wyLog.getMinute5() != null && !"".equals(wyLog.getMinute5())) {
                    b5 = Integer.parseInt(wyLog.getMinute5());
                }
                String c5 = "";
                if (-1 < a5 && a5 < 10) {
                    c5 = "0" + wyLog.getHour5();
                } else if (a5 >= 10) {
                    c5 = wyLog.getHour5();
                }
                if (-1 < b5 && b5 < 10) {
                    c5 = c5 + ":" + "0" + wyLog.getMinute5();
                } else if (b5 >= 10) {
                    c5 = c5 + ":" + wyLog.getMinute5();
                }
                if (!"".equals(c5)) {
                    wyLog5.setHour(c5);
                }
                String d5 = sdf.format(wyLog.getCreateTime());
                d5 = d5 + " " + c5 + ":00";
                wyLog5.setCreateTime(sdf1.parse(d5));
                wyLogService.save(wyLog5);//修改
            }
            if (wyLog.getContent6() != null && !"".equals(wyLog.getContent6())) {
                WyLog wyLog6 = new WyLog();
                wyLog6.setComCode(user.getComCode());
                wyLog6.setDeptCode(user.getDeptCode());
                if (wyLog.getRepairAddress() != null && !"".equals(wyLog.getRepairAddress())) {
                    wyLog6.setArea(wyLog.getRepairAddress());
                }
                wyLog6.setUserId(user.getUserId());
                //wyLog6.setCreateTime(wyLog.getCreateTime());
                wyLog6.setContent(wyLog.getContent6());
                int a6 = -1;
                int b6 = -1;
                if (wyLog.getHour6() != null && !"".equals(wyLog.getHour6())) {
                    a6 = Integer.parseInt(wyLog.getHour6());
                }
                if (wyLog.getMinute6() != null && !"".equals(wyLog.getMinute6())) {
                    b6 = Integer.parseInt(wyLog.getMinute6());
                }
                String c6 = "";
                if (-1 < a6 && a6 < 10) {
                    c6 = "0" + wyLog.getHour6();
                } else if (a6 >= 10) {
                    c6 = wyLog.getHour6();
                }
                if (-1 < b6 && b6 < 10) {
                    c6 = c6 + ":" + "0" + wyLog.getMinute6();
                } else if (b6 >= 10) {
                    c6 = c6 + ":" + wyLog.getMinute6();
                }
                if (!"".equals(c6)) {
                    wyLog6.setHour(c6);
                }
                String d6 = sdf.format(wyLog.getCreateTime());
                d6 = d6 + " " + c6 + ":00";
                wyLog6.setCreateTime(sdf1.parse(d6));
                wyLogService.save(wyLog6);//修改
            }
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return ResponseUtil.successResponse(map);
    }

    /**
     * @param ids
     * @return :Map<String,Object>
     * @throws IOException
     * @throws ServletException
     * @author heibin
     * @since JDK 1.8
     */
    @RequestMapping(value = "/deleteWyLog", method = RequestMethod.POST)
    public CommonResponse delete(
            String ids
    ) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = wyLogService.deleteWyLog(ids);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return ResponseUtil.successResponse(map);
    }

    /**
     * export
     *
     * @throws IOException
     * @throws Exception
     * @author zxj
     * @since JDK 1.8
     */
    @DataAuthority(tableAlias = "v")
    @RequestMapping(value = "/exportWyLog", method = RequestMethod.GET)
    @PermessionLimit(limit = false)
    public void export(WyLog wyLog, String token, LoginUser user, HttpServletResponse response) throws Exception {
        //处理页面跳转
        List<String> role = callService.queryRole(user.getUserId());
        if (wyLog.getType() == 2) {
            role.forEach(e -> {
                if (!"wy_jl".equals(e)) {
                    if (wyLog.getType() == 2) {
                        wyLog.setProcessingPersonId(user.getUserId());
                    }
                } else {
                    wyLog.setProcessingPersonId("");
                    wyLog.setType(3);
                }
            });
        }
        wyLogService.export(wyLog, response);
    }
}
