package com.sgai.property.wy.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.wy.entity.MeetingOrder;
import com.sgai.property.wy.service.MeetingOrderService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin/meetingOrder")
public class MeetingOrderController extends BaseController {
    @Autowired
    private MeetingOrderService meetingOrderService;

    /**
     * 按条件查询
     *
     * @param meetingOrder
     * @return
     */
    @RequestMapping("/queryByCondition")
    public CommonResponse queryByCondition(@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, MeetingOrder meetingOrder) throws JsonProcessingException {
        if (meetingOrder.getEndTime() != null) {
            Date time = meetingOrder.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
            meetingOrder.setEndTime(c.getTime());
        }
        Page<MeetingOrder> page = meetingOrderService.findPage(new Page<MeetingOrder>(pageNo, pageSize), meetingOrder);

        return ResponseUtil.successResponse(page);
    }

    @RequestMapping("/queryByConditionOrder")
    public CommonResponse queryByConditionOrder(@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                                @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, MeetingOrder meetingOrder) throws JsonProcessingException {
        if (meetingOrder.getEndTime() != null) {
            Date time = meetingOrder.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
            meetingOrder.setEndTime(c.getTime());
        }
        Page<MeetingOrder> page = meetingOrderService.findPage(new Page<MeetingOrder>(pageNo, pageSize), meetingOrder);
        List<MeetingOrder> list = page.getList();
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        for (MeetingOrder order : list) {
            if ("预约".equals(order.getServiceType())) {
                List<Integer> dateList = new ArrayList<Integer>();
                int startNum = 0;
                int endNum = 0;
                String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getBeginTime());
                startDate = startDate.substring(startDate.indexOf(" ") + 1);
                String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getEndTime());
                endDate = endDate.substring(endDate.indexOf(" ") + 1);
                int startHH = Integer.valueOf(startDate.substring(0, startDate.indexOf(":")));
                int startSS = Integer.valueOf(startDate.substring(startDate.indexOf(":") + 1));
                int endHH = Integer.valueOf(endDate.substring(0, endDate.indexOf(":")));
                int endSS = Integer.valueOf(endDate.substring(endDate.indexOf(":") + 1));
                int start = startHH * 60 + startSS;
                int end = endHH * 60 + endSS;
                //判断开始时间
                for (int i = 0; i < 48; i++) {
                    if (start < 15) {
                        start = 0;
                        break;
                    }
                    if (start > 46 * 30 + 15) {
                        startNum = 47;
                        break;
                    }
                    if (start >= i * 30 + 15 && start <= (i + 1) * 30) {
                        startNum = i + 1;
                        break;
                    }
                    if (start >= i * 30 && start <= i * 30 + 15) {
                        startNum = i;
                        break;
                    }
                }
                for (int i = 0; i < 48; i++) {
                    if (end < 15) {
                        endNum = 0;
                        break;
                    }
                    if (end > 46 * 30 + 15) {
                        endNum = 47;
                        break;
                    }
                    if (end >= i * 30 + 15 && end <= (i + 1) * 30) {
                        endNum = i + 1;
                        break;
                    }
                    if (end >= i * 30 && end <= i * 30 + 15) {
                        endNum = i;
                        break;
                    }
                }
                int length = endNum - startNum;
                dateList.add(startNum);
                dateList.add(length);
                finalList.add(dateList);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("listData", page);
        map.put("finalList", finalList);
        return ResponseUtil.successResponse(map);
    }


    /**
     * 批量删除.
     *
     * @param ids
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delete")
    @Transactional
    public CommonResponse delete(String ids) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsArray = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            MeetingOrder meetingOrder = new MeetingOrder();
            meetingOrder.setId(idsArray[i]);
            meetingOrderService.delete(meetingOrder);
        }
        map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }

    @RequestMapping(value = "/save")
    public CommonResponse save(MeetingOrder meetingOrder, @RequestHeader String token) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<String, Object>();
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
        UserServletContext.setUserInfo(user);
        //保存记录人的信息
        if (meetingOrder.getId() == null || "".endsWith(meetingOrder.getId())) {
            meetingOrder.setRecordPeople(user.getUserName());
        }
        meetingOrderService.save(meetingOrder);
        map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }

    /**
     * @param id
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public CommonResponse findById(String id) throws IOException, ServletException {
        MeetingOrder meetingOrder = meetingOrderService.get(id);
        return ResponseUtil.successResponse(meetingOrder);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @PermessionLimit(limit = false)
    public void export(HttpServletResponse response, MeetingOrder meetingOrder) throws IOException {
        meetingOrderService.export(response, meetingOrder);
    }

    @RequestMapping("/changeServiceType")
    public CommonResponse changeServiceType(String id) throws JsonProcessingException {
        MeetingOrder meetingOrder = meetingOrderService.get(id);
        meetingOrder.setServiceType("取消");
        meetingOrderService.save(meetingOrder);
        return ResponseUtil.successResponse(meetingOrder);
    }

    @RequestMapping("/checkMeetingTime")
    public CommonResponse checkMeetingTime(MeetingOrder meetingOrder) throws JsonProcessingException, ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = meetingOrder.getBeginTime();
        Date endTime = meetingOrder.getEndTime();
        String meetingScale = meetingOrder.getMeetingScale();
        MeetingOrder m = new MeetingOrder();
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        m.setBeginTime(sdf.parse(sdf.format(c.getTime())));
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        m.setEndTime(sdf.parse(sdf.format(c.getTime())));
        m.setServiceType("预约");
        m.setMeetingRoomId(meetingOrder.getMeetingRoomId());
        m.setSourceType("meetingRoom");
        List<MeetingOrder> meetingOrders = meetingOrderService.findList(m);
        //判断开始时间和结束时间是否已被占用
        CommonResponse commonResponse = ResponseUtil.successResponse();
        if (ifExsit(meetingOrders, beginTime, endTime, meetingScale)) {
            commonResponse.setMsg("ok");
        }
        return commonResponse;
    }

    private boolean ifExsit(List<MeetingOrder> meetingOrders, Date beginTime, Date endTime, String meetingScale) {


        if (meetingOrders == null || meetingOrders.isEmpty()) {
            return true;
        }
        //如果会议排到开头
        if (meetingScale.equals("小型会议") && dateToNumber(endTime) <= dateToNumber(meetingOrders.get(0).getBeginTime())) {
            return true;
        }
        if (meetingScale.equals("中型会议") && dateToNumber(endTime) + 30 <= dateToNumber(meetingOrders.get(0).getBeginTime())) {
            return true;
        }
        if (meetingScale.equals("大型会议") && dateToNumber(endTime) + 60 <= dateToNumber(meetingOrders.get(0).getBeginTime())) {
            return true;
        }
        //如果会议排到结尾
        if (dateToNumber(meetingOrders.get(meetingOrders.size() - 1).getEndTime()) <= dateToNumber(beginTime) && meetingOrders.get(meetingOrders.size() - 1).getMeetingScale().equals("小型会议")) {
            return true;
        }
        if (dateToNumber(meetingOrders.get(meetingOrders.size() - 1).getEndTime()) + 30 <= dateToNumber(beginTime) && meetingOrders.get(meetingOrders.size() - 1).getMeetingScale().equals("中型会议")) {
            return true;
        }
        if (dateToNumber(meetingOrders.get(meetingOrders.size() - 1).getEndTime()) + 60 <= dateToNumber(beginTime) && meetingOrders.get(meetingOrders.size() - 1).getMeetingScale().equals("大型会议")) {
            return true;
        }

        if (meetingOrders.size() > 2) {
            for (int i = 0; i < meetingOrders.size() - 1; i++) {
                if (dateToNumber(meetingOrders.get(i).getEndTime()) <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("小型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) && meetingScale.equals("小型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("小型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 30 && meetingScale.equals("中型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("小型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 60 && meetingScale.equals("大型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 30 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("中型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) && meetingScale.equals("小型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 30 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("中型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 30 && meetingScale.equals("中型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 30 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("中型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 60 && meetingScale.equals("大型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 60 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("大型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) && meetingScale.equals("小型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 60 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("大型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 30 && meetingScale.equals("中型会议")) {
                    return true;
                }
                if (dateToNumber(meetingOrders.get(i).getEndTime()) + 60 <= dateToNumber(beginTime) && meetingOrders.get(i).getMeetingScale().equals("大型会议") && dateToNumber(meetingOrders.get(i + 1).getBeginTime()) >= dateToNumber(endTime) + 60 && meetingScale.equals("大型会议")) {
                    return true;
                }
            }
        }
        return false;
    }

    private int dateToNumber(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(date);
        String str1 = str.substring(str.indexOf(" ") + 1, str.indexOf(":"));
        String str2 = str.substring(str.indexOf(":") + 1);
        return Integer.parseInt(str1) * 60 + Integer.parseInt(str2);
    }
}
