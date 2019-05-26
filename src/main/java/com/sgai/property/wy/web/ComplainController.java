package com.sgai.property.wy.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.wy.entity.Complain;
import com.sgai.property.wy.entity.RepairInformation;
import com.sgai.property.wy.service.ComplainService;
import com.sgai.property.wy.service.RepairInformationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin/complain")
public class ComplainController extends BaseController {

	@Autowired
	private ComplainService complainService;
	@Autowired
	private RepairInformationService repairInformationService;

	/**
	 * 按条件查询
	 *
	 * @param complain
	 * @return
	 */
	@RequestMapping("/queryByCondition")
	public CommonResponse queryByCondition(
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, Complain complain)
			throws JsonProcessingException {
		Page<Complain> page = complainService.findPage(new Page<Complain>(pageNo, pageSize), complain);

		return ResponseUtil.successResponse(page);
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
			Complain complain = new Complain();
			complain.setId(idsArray[i]);
			complainService.delete(complain);
		}
		map.put("msg", "success");
		return ResponseUtil.successResponse(map);
	}

	@RequestMapping(value = "/save")
	public CommonResponse save(Complain complain, LoginUser user) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		UserServletContext.setUserInfo(user);
		// 保存记录人的信息
		boolean flag = false;
		if (complain.getId() == null) {
			complain.setComplainStatus("0");
			complain.setComplainTime(new Date());
			complain.setIncidentSource("1");
			flag = true;
		} else {
			complain.setReplyTime(new Date());
		}
		complainService.save(complain);
		if (flag) {
			repairInformationService.updateComplainId(complain.getSourceKey(), complain.getId());
		}
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
		Complain complain = complainService.get(id);
		return ResponseUtil.successResponse(complain);
	}

	/**
	 * @return :返回一个Map(事件时间状态的统计)
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "pieData")
	public Map<String, Object> pieData(String id, String startTime, String endTime, String type) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		RepairInformation repair = new RepairInformation();
		if (StringUtils.isNotEmpty(id)) {
			changDate(id, repair, startTime, endTime);
		}
		if ("1".equals(type)) {
			Complain complain = new Complain();
			complain.setStartTime(repair.getStartTime());
			complain.setEndTime(repair.getEndTime());
			// 查询所有事件
			List<Complain> rlist = complainService.findList(complain);
			// 待处理投诉事件(未回复)
			complain.setComplainStatus("0");
			List<Complain> appList = complainService.findList(complain);
			// 已回复投诉
			complain.setComplainStatus("1");
			List<Complain> phoneList = complainService.findList(complain);
			maplist.put("待处理投诉事件", appList.size());
			maplist.put("已回复投诉", phoneList.size());
			maplist.put("repairSum", rlist.size());
		} else {
			Complain complain = new Complain();
			complain.setStartTime(repair.getStartTime());
			complain.setEndTime(repair.getEndTime());
			// 查询所有事件
			List<Complain> rlist = complainService.findList(complain);
			// 微信投诉
			complain.setIncidentSource("2");
			List<Complain> appList = complainService.findList(complain);
			// 电话投诉
			complain.setIncidentSource("1");
			List<Complain> phoneList = complainService.findList(complain);

			maplist.put("微信投诉", appList.size());
			maplist.put("电话投诉", phoneList.size());
			maplist.put("repairSum", rlist.size());

		}

		return maplist;
	}

	private void changDate(String id, RepairInformation repair, String startTime, String endTime)
			throws ParseException {
		Calendar y = Calendar.getInstance();
		Date day = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("e".equals(id)) {
			if (startTime != null && !startTime.equals("")) {
				startTime += " 00:00:00";
				repair.setStartTime(formatter.parse(startTime));
			}
			if (endTime != null && !endTime.equals("")) {
				endTime += " 23:59:59";
				repair.setEndTime(formatter1.parse(endTime));

			}
		} else {
			if ("a".equals(id)) {
				day = new Date();
			}
			if ("b".equals(id)) {
				y.set(Calendar.YEAR, y.get(Calendar.YEAR) - 1);
				day = y.getTime();
			}
			if ("c".equals(id)) {
				y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 3);
				day = y.getTime();
			}
			if ("d".equals(id)) {
				y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 1);
				day = y.getTime();
			}
			if ("days".equals(id)) {
				y.set(Calendar.DATE, y.get(Calendar.DATE) - 7);
				day = y.getTime();
			}
			repair.setStartTime(day);
			repair.setEndTime(new Date());
		}
	}

	@RequestMapping(value = "getDates")
	public Map<String, Object> getDates() throws ParseException {
		String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Map<String, Object> maplist = new HashMap<>();
		// 年
		Calendar yc = Calendar.getInstance();
		yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
		Date day = yc.getTime();
		String ysStr = new SimpleDateFormat("yyyy/MM/dd").format(day);
		maplist.put("b", "近一年" + "(" + ysStr + "-" + today + ")");

		// 季度
		Calendar m3c = Calendar.getInstance();
		m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
		Date day1 = m3c.getTime();
		String mc3Str = new SimpleDateFormat("yyyy/MM/dd").format(day1);
		maplist.put("c", "近一个季度" + "(" + mc3Str + "-" + today + ")");

		// 月
		Calendar m1c = Calendar.getInstance();
		m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
		Date month = m1c.getTime();
		String m1cStr = new SimpleDateFormat("yyyy/MM/dd").format(month);
		maplist.put("d", "近一个月" + "(" + m1cStr + "-" + today + ")");
		// 周
		Calendar mdc = Calendar.getInstance();
		mdc.set(Calendar.DATE, m1c.get(Calendar.DATE) - 7);
		Date days = mdc.getTime();
		String mdcStr = new SimpleDateFormat("yyyy/MM/dd").format(days);
		maplist.put("days", "近一周" + "(" + mdcStr + "-" + today + ")");
		return maplist;
	}
}
