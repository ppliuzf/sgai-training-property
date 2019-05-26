
package com.sgai.property.wy.web;

import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.DataAuthority;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wy.dto.RepairConst;
import com.sgai.property.wy.entity.*;
import com.sgai.property.wy.service.RepairInformationService;
import com.sgai.property.wy.service.TodoEventService;
import com.sgai.property.wy.support.Book;
import com.sgai.property.wy.support.CompleteUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName: TodoEventController
 * (首页数据加载)
 * @author cui
 * @date 2018年2月7日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping("/todoEvent")
public class TodoEventController extends BaseController {

	@Autowired
	private TodoEventService eventService;
	@Autowired
	private RepairInformationService repairInformationService;

	@DataAuthority(tableAlias = "a")
	@RequestMapping(value = "queryEvents")
	public Map<String, Object> allEvents(EventCommon event, LoginUser user)
			throws ParseException {
		event.setUser(user.getUserId());
		return eventService.queryEvent(event);
	}

	/**
	 * @return :返回一个Map包含各个楼宇的各个事件,及各个事件的总数 该请求方法 已经废弃
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "echartsData")
	@Deprecated
	public Map<String, Object> echartsData(EventCommon event, LoginUser user)
			throws ParseException {
		RepairInformation infor = new RepairInformation();
		Map<String, Object> maplist = new HashMap<String, Object>();
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(infor);
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		// 查询所有父级类型获取list;
		// List<RepairInformationType> repairType =
		// repairInformationService.getTypeByCode("0");
		for (EventCommon s : space) {
			int qingdian = 0;
			int ruodian = 0;
			int kongtiao = 0;
			int paishui = 0;
			int dianti = 0;
			int zonghe = 0;
			/*
			 * //循环类型list map(存放父级类型<list[i].typeCode,0>) Map<String,Integer>
			 * sum = new HashMap<>(); for(RepairInformationType rt :
			 * repairType){ sum.put(rt.getTypeCode(),0); }
			 */
			Map<String, Object> sum = new HashMap<>();
			sum.put("qiangdian", qingdian);
			sum.put("ruodian", ruodian);
			sum.put("kongtiao", kongtiao);
			sum.put("paishui", paishui);
			sum.put("dianti", dianti);
			sum.put("zonghe", zonghe);
			for (RepairInformation p : rlist) {
				if (p.getRepairAddressCode().contains(s.getSpaceCode())) {// 判断是否属于该区域
					/*
					 * //循环类型list(判断p.getRepairType().contains(list[i].typeCode)
					 * ) for(RepairInformationType rt : repairType){ if
					 * (p.getRepairType().contains(rt.getTypeCode())) {
					 * //通过list[i].typeCode 获取map中对应的值 让其值++ Integer flag =
					 * sum.get(rt.getTypeCode()); flag++;
					 * sum.put(rt.getTypeCode(), flag); } }
					 */
					if (p.getRepairType().contains("强电维修")) {
						qingdian++;
					}
					if (p.getRepairType().contains("弱电维修")) {
						ruodian++;
					}
					if (p.getRepairType().contains("空调维修")) {
						kongtiao++;
					}
					if (p.getRepairType().contains("给排水维修")) {
						paishui++;
					}
					if (p.getRepairType().contains("电梯维修")) {
						dianti++;
					}
					if (p.getRepairType().contains("综合维修")) {
						zonghe++;
					}
					sum.put("qiangdian", qingdian);
					sum.put("ruodian", ruodian);
					sum.put("kongtiao", kongtiao);
					sum.put("paishui", paishui);
					sum.put("dianti", dianti);
					sum.put("zonghe", zonghe);
				}
			}
			maplist.put(s.getSpaceName(), sum);
		}
		return maplist;
	}

	/**
	 * @return :返回一个Map包含各个楼宇的事件总数
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "treemapData", method = RequestMethod.POST)
	public Map<String, Object> treemapData(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}

		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		Map<String, Object> sum;
		for (EventCommon s : space) {
			sum = new HashMap<>();
			Long count = rlist.stream().filter(
					p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
					.count();
			sum.put(s.getSpaceCode(), count);
			maplist.put(s.getSpaceName(), sum);
		}
		return maplist;
	}

	/**
	 * @return :返回一个Map包含各个楼宇的事件总数
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "treemapDataRepair", method = RequestMethod.POST)
	public Map<String, Object> treemapDataRepair(String buildName, String type,RepairInformation repair)
			throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		repair.setBuildName(buildName);
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有主楼的事件
		List<RepairInformation> rlist = eventService.getRepairList(repair);
		// 查询所有父级类型获取list;
		List<RepairInformationType> repairType = repairInformationService
				.getTypeByCode("0");
		Map<String, Object> sum;
		for (RepairInformationType s : repairType) {
			sum = new HashMap<>();
			// java8
			Long count = rlist.stream().filter(p -> {
				String[] t = p.getRepairTypeCode().split("-");
				return t[0].equals(s.getTypeId());
			}).count();
			sum.put(s.getTypeCode(), count);
			maplist.put(s.getTypeCode(), sum);
		}
		return maplist;
	}

	/**
	 * @return :返回一个Map包含各个楼宇的分析数据
	 * @since JDK 1.8
	 * @author cuiwenjian
	 */
	@RequestMapping(value = "statisticsData", method = RequestMethod.POST)
	public Map<String, Object> statistics(String analtype, String type,RepairInformation repair) {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		List<RepairInformation> ol = null;
		if ("bxcl".equals(analtype)) {
			long sum1 = rlist.stream()
					.filter(r -> RepairConst.echeckQualified.getIndex()
							.equals(r.getRepairStatus())
							|| RepairConst.appraise.getIndex()
									.equals(r.getRepairStatus()))
					.mapToInt(p -> {
						return (int) dif(p.getCreatedDt(), p.getUpdatedDt());
					}).reduce(0, Integer::sum);
			BigDecimal percent = calculate(rlist.size(), sum1);
			maplist.put("repairLevel", percent);
			for (EventCommon s : space) {
				// 当前楼宇的事件数
				List<RepairInformation> rl = rlist.stream()
						.filter(p -> p.getRepairAddressCode()
								.contains(s.getSpaceCode())) // java8
						.collect(Collectors.toList());
				if (rl.size() != 0) {
					// 当前楼宇环境验收合格的时间总数
					long sum2 = rl.stream()
							.filter(r -> RepairConst.echeckQualified.getIndex()
									.equals(r.getRepairStatus())
									|| RepairConst.appraise.getIndex()
											.equals(r.getRepairStatus()))
							.mapToInt(p -> {
								return (int) dif(p.getCreatedDt(), p.getUpdatedDt());
							}).reduce(0, Integer::sum);
					// 计算当前楼宇环境验收合格的合格率
					BigDecimal percent1 = calculate(rl.size(), sum2);

					if (percent1.compareTo(percent) < 0) {
						// 当前楼宇环境验收合格的合格率小于平均率,取反
						maplist.put(s.getSpaceName(), calculate(rl.size(), sum2)
								.multiply(new BigDecimal(-1)));
					} else {
						maplist.put(s.getSpaceName(),
								calculate(rl.size(), sum2));
					}
				} else {
					maplist.put(s.getSpaceName(), 0);
				}
			}
			return maplist;
		}
		if ("pjbx".equals(analtype)) { // 平均每户报修数
			int sumRoom = eventService.getSumRoom();
			BigDecimal percent = calculate(sumRoom, rlist.size());
			for (EventCommon s : space) {
				// 当前楼宇的事件数
				List<RepairInformation> rl = rlist.stream()
						.filter(p -> p.getRepairAddressCode()
								.contains(s.getSpaceCode())) // java8
						.collect(Collectors.toList());
				if (rl.size() != 0) {
					// 当前楼宇所有room
					int sumBuildRoom = eventService
							.getSumBuildRoom(s.getSpaceCode());
					// 计算当前楼宇环境验收合格的合格率
					BigDecimal percent1 = calculate(sumBuildRoom, rl.size());
					maplist.put("repairLevel", percent1);
					if (percent1.compareTo(percent) < 0) {
						// 当前楼宇环境验收合格的合格率小于平均率,取反
						maplist.put(s.getSpaceName(),
								calculate(sumBuildRoom, rl.size())
										.multiply(new BigDecimal(-1)));
					} else {
						maplist.put(s.getSpaceName(),
								calculate(sumBuildRoom, rl.size()));
					}
				} else {
					maplist.put(s.getSpaceName(), 0);
				}
			}
			return maplist;
		}
		if ("bxwc".equals(analtype) || StringUtils.isEmpty(analtype)) {// 报修完成率
			ol = rlist.stream()
					.filter(r -> RepairConst.echeckQualified.getIndex()
							.equals(r.getRepairStatus())
							|| RepairConst.appraise.getIndex()
									.equals(r.getRepairStatus()))
					.collect(Collectors.toList());
		}
		if ("bxts".equals(analtype)) {// 报修投诉率
			ol = rlist.stream()
					.filter(r -> StringUtils.isNotEmpty(r.getComplainId()))
					.collect(Collectors.toList());
		}
		BigDecimal percent = calculate(rlist.size(), ol.size());
		maplist.put("repairLevel", percent);
		for (EventCommon s : space) {
			// 当前楼宇的事件数
			List<RepairInformation> rl = rlist.stream().filter(
					p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
					.collect(Collectors.toList());
			if (rl.size() != 0) {
				// 当前楼宇环境验收合格的时间总数
				List<RepairInformation> rll = null;
				if ("bxwc".equals(analtype) || StringUtils.isEmpty(analtype)) {// 保修完成率
					rll = rl.stream()
							.filter(r -> RepairConst.echeckQualified.getIndex()
									.equals(r.getRepairStatus())
									|| RepairConst.appraise.getIndex()
											.equals(r.getRepairStatus()))
							.collect(Collectors.toList());
				}
				if ("bxts".equals(analtype)) {// 报修投诉率
					rll = rl.stream().filter(
							r -> StringUtils.isNotEmpty(r.getComplainId()))
							.collect(Collectors.toList());
				}
				// 计算当前楼宇环境验收合格的合格率
				BigDecimal percent1 = calculate(rl.size(), rll.size());

				if (percent1.compareTo(percent) < 0) {
					// 当前楼宇环境验收合格的合格率小于平均率,取反
					maplist.put(s.getSpaceName(),
							calculate(rl.size(), rll.size())
									.multiply(new BigDecimal(-1)));
				} else {
					maplist.put(s.getSpaceName(),
							calculate(rl.size(), rll.size()));
				}
			} else {
				maplist.put(s.getSpaceName(), 0);
			}
		}
		return maplist;
	}

	/**
	 * @return :返回一个Map包含时间跨度
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "getDates")
	public Map<String, Object> getDates() throws ParseException {
		String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Map<String, Object> maplist = new LinkedHashMap<>();
		// 年
		Calendar yc = Calendar.getInstance();
		yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
		Date day = yc.getTime();
		String ysStr = new SimpleDateFormat("yyyy/MM/dd").format(day);
		maplist.put("b", "年" + "(" + ysStr + "-" + today + ")");

		// 季度
		Calendar m3c = Calendar.getInstance();
		m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
		Date day1 = m3c.getTime();
		String mc3Str = new SimpleDateFormat("yyyy/MM/dd").format(day1);
		maplist.put("c", "季度" + "(" + mc3Str + "-" + today + ")");

		// 月
		Calendar m1c = Calendar.getInstance();
		m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
		Date month = m1c.getTime();
		String m1cStr = new SimpleDateFormat("yyyy/MM/dd").format(month);
		maplist.put("d", "月" + "(" + m1cStr + "-" + today + ")");

		// 当天时间
		maplist.put("a", "天" + "(" + today + "-" + today + ")");
		return maplist;
	}

	/**
	 * @return :返回一个Map 各个时间段报修的工单个数
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "analbxgd")
	public Map<String, Object> analbxgd(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new LinkedHashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0,
				sum7 = 0, sum8 = 0, sum9 = 0, sum10 = 0, sum11 = 0;
		String format = "HH:mm:ss";
		Date startTime = new SimpleDateFormat(format).parse("00:00:00");
		Date endTime = new SimpleDateFormat(format).parse("01:59:59");
		Date startTime1 = new SimpleDateFormat(format).parse("02:00:00");
		Date endTime1 = new SimpleDateFormat(format).parse("03:59:59");
		Date startTime2 = new SimpleDateFormat(format).parse("04:00:00");
		Date endTime2 = new SimpleDateFormat(format).parse("05:59:59");
		Date startTime3 = new SimpleDateFormat(format).parse("06:00:00");
		Date endTime3 = new SimpleDateFormat(format).parse("07:59:59");
		Date startTime4 = new SimpleDateFormat(format).parse("08:00:00");
		Date endTime4 = new SimpleDateFormat(format).parse("09:59:59");
		Date startTime5 = new SimpleDateFormat(format).parse("10:00:00");
		Date endTime5 = new SimpleDateFormat(format).parse("11:59:59");
		Date startTime6 = new SimpleDateFormat(format).parse("12:00:00");
		Date endTime6 = new SimpleDateFormat(format).parse("13:59:59");
		Date startTime7 = new SimpleDateFormat(format).parse("14:00:00");
		Date endTime7 = new SimpleDateFormat(format).parse("15:59:59");
		Date startTime8 = new SimpleDateFormat(format).parse("16:00:00");
		Date endTime8 = new SimpleDateFormat(format).parse("17:59:59");
		Date startTime9 = new SimpleDateFormat(format).parse("18:00:00");
		Date endTime9 = new SimpleDateFormat(format).parse("19:59:59");
		Date startTime10 = new SimpleDateFormat(format).parse("20:00:00");
		Date endTime10 = new SimpleDateFormat(format).parse("21:59:59");
		Date startTime11 = new SimpleDateFormat(format).parse("22:00:00");
		Date endTime11 = new SimpleDateFormat(format).parse("23:59:59");
		maplist.put("0", 0);
		maplist.put("1", 0);
		maplist.put("2", 0);
		maplist.put("3", 0);
		maplist.put("4", 0);
		maplist.put("5", 0);
		maplist.put("6", 0);
		maplist.put("7", 0);
		maplist.put("8", 0);
		maplist.put("9", 0);
		maplist.put("10", 0);
		maplist.put("11", 0);
		maplist.put("12", 0);
		maplist.put("13", 0);
		maplist.put("14", 0);
		maplist.put("15", 0);
		maplist.put("16", 0);
		maplist.put("17", 0);
		maplist.put("18", 0);
		maplist.put("19", 0);
		maplist.put("20", 0);
		maplist.put("21", 0);
		maplist.put("22", 0);
		maplist.put("23", 0);
		maplist.put("24", 0);
		for (RepairInformation r : rlist) {
			String nowTime1 = new SimpleDateFormat("HH:mm:ss")
					.format(r.getCreatedDt());
			Date nowTime = new SimpleDateFormat(format).parse(nowTime1);
			if (isEffectiveDate(nowTime, startTime, endTime)) {
				sum++;
				maplist.put("1", sum);
			}
			if (isEffectiveDate(nowTime, startTime1, endTime1)) {
				sum1++;
				maplist.put("3", sum1);
			}
			if (isEffectiveDate(nowTime, startTime2, endTime2)) {
				sum2++;
				maplist.put("5", sum2);
			}
			if (isEffectiveDate(nowTime, startTime3, endTime3)) {
				sum3++;
				maplist.put("7", sum3);
			}
			if (isEffectiveDate(nowTime, startTime4, endTime4)) {
				sum4++;
				maplist.put("9", sum4);
			}
			if (isEffectiveDate(nowTime, startTime5, endTime5)) {
				sum5++;
				maplist.put("11", sum5);
			}
			if (isEffectiveDate(nowTime, startTime6, endTime6)) {
				sum6++;
				maplist.put("13", sum6);
			}
			if (isEffectiveDate(nowTime, startTime7, endTime7)) {
				sum7++;
				maplist.put("15", sum7);
			}
			if (isEffectiveDate(nowTime, startTime8, endTime8)) {
				sum8++;
				maplist.put("17", sum8);
			}
			if (isEffectiveDate(nowTime, startTime9, endTime9)) {
				sum9++;
				maplist.put("19", sum9);
			}
			if (isEffectiveDate(nowTime, startTime10, endTime10)) {
				sum10++;
				maplist.put("21", sum10);
			}
			if (isEffectiveDate(nowTime, startTime11, endTime11)) {
				sum11++;
				maplist.put("23", sum11);
			}
		}
		return maplist;
	}

	/**
	 * @return :返回一个Map(事件时间状态的统计)
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "pieData")
	public Map<String, Object> pieData(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		// 已完成
		Long ywc = rlist.stream().filter(r -> RepairConst.echeckQualified
				.getIndex().equals(r.getRepairStatus())
				|| RepairConst.appraise.getIndex().equals(r.getRepairStatus()))
				.count();
		// 已关闭
		Long ygb = rlist.stream().filter(
				r -> RepairConst.close.getIndex().equals(r.getRepairStatus()))
				.count();
		// 待指派
		Long ytj = rlist.stream().filter(r -> RepairConst.repairAccept
				.getIndex().equals(r.getRepairStatus())).count();
		// 实施中
		Long ssz = rlist.stream()
				.filter(r -> RepairConst.maintainBegin.getIndex()
						.equals(r.getRepairStatus())
						|| RepairConst.maintainWait.getIndex()
								.equals(r.getRepairStatus())
						|| RepairConst.repairSubmit.getIndex()
								.equals(r.getRepairStatus()))
				.count();
		// 待回复
		Long dhf = rlist.stream()
				.filter(r -> RepairConst.echeckQualified.getIndex()
						.equals(r.getRepairStatus())
						|| RepairConst.echeckNoQualified.getIndex()
								.equals(r.getRepairStatus())
						|| RepairConst.fcheckQualified.getIndex()
								.equals(r.getRepairStatus())
						|| RepairConst.fcheckNoQualified.getIndex()
								.equals(r.getRepairStatus()))
				.count();
		maplist.put("已完成", ywc);
		maplist.put("已关闭", ygb);
		maplist.put("待指派", ytj);
		maplist.put("实施中", ssz);
		maplist.put("待回复", dhf);
		maplist.put("repairSum", rlist.size());
		return maplist;
	}
	
	
	/**
	 * @return :返回一个Map(事件时间状态的统计)
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "mapCData")
	public Map<String, Object> mapData(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new LinkedHashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		long ol =  rlist.stream()
				.filter(r -> StringUtils.isNotEmpty(r.getComplainId()))
				.count();
		long ywc =rlist.stream().filter(r -> RepairConst.echeckQualified
				.getIndex().equals(r.getRepairStatus())
				|| RepairConst.appraise.getIndex().equals(r.getRepairStatus()))
				.count();
		long sum1 = rlist.stream()
				.filter(r -> RepairConst.echeckQualified.getIndex()
						.equals(r.getRepairStatus())
						|| RepairConst.appraise.getIndex()
								.equals(r.getRepairStatus()))
				.mapToInt(p -> {
					return (int) dif(p.getCreatedDt(), p.getUpdatedDt());
				}).reduce(0, Integer::sum);
		RepairAnalysisDto rd=eventService.getRepairBxyzs(new RepairAnalysisDto());
		BigDecimal percent3 = calculate(rlist.size(), sum1/60);
		BigDecimal percent = calculate(rlist.size(), ol);
		BigDecimal percent2 = calculate(rlist.size(), ywc);
			maplist.put("报修建筑体数", space.size());
			maplist.put("报修处理时长", percent3);
			maplist.put("报修工单数", rlist.size());
			maplist.put("报修业主数", rd.getSumRepair());
			maplist.put("报修完成率", percent2);
			maplist.put("报修投诉率", percent);
		return maplist;
	}

	/**
	 * @return :返回一个Map(报修工单时间间隔)
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "repairNeedDate")
	public Map<String, Object> repairNeedDate(String type, String chosType,RepairInformation repair)
			 {
		Map<String, Object> maplist = new LinkedHashMap<>();
		maplist.put("avgreg",0);
		long avgreg=0;
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		if ("a".equals(type)) {
			int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0;
			maplist.put("0-4小时", 0);
			maplist.put("4-8小时", 0);
			maplist.put("8-12小时", 0);
			maplist.put("12-16小时", 0);
			maplist.put("16-20小时", 0);
			maplist.put("20-24小时", 0);
			for (RepairInformation r : rlist) {
				List<RepairRecord> rr = eventService.findRecordList(r.getId());
				List<RepairRecord> r1 = null;
				List<RepairRecord> r2 = null;
				// 已提交-已受理
				if ("ytjysl".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairSubmit.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已受理-已指派
				if ("yslyzp".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已指派-已完成
				if ("yzpywc".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已完成-验收完成
				if ("ywcyswc".equals(chosType) || "".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.echeckQualified.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				if (r1.size() > 0 && r2.size() > 0) {
					Date d1 = r1.get(0).getPresentDate();
					Date d2 = r2.get(0).getPresentDate();
					long c = dif2(d1, d2,type);
					avgreg+=c;
					BigDecimal big=calculate2(60*60,c);
					if (big.compareTo(new BigDecimal(0))>0 && big.compareTo(new BigDecimal(4))<= 0) {
						sum++;
						maplist.put("0-4小时", sum);
					} else if (big.compareTo(new BigDecimal(4))>0 && big.compareTo(new BigDecimal(8))<= 0) {
						sum1++;
						maplist.put("4-8小时", sum1);
					} else if (big.compareTo(new BigDecimal(8))>0 && big.compareTo(new BigDecimal(12))<= 0) {
						sum2++;
						maplist.put("8-12小时", sum2);
					} else if (big.compareTo(new BigDecimal(12))>0 && big.compareTo(new BigDecimal(16))<= 0) {
						sum3++;
						maplist.put("12-16小时", sum3);
					} else if (big.compareTo(new BigDecimal(16))>0 && big.compareTo(new BigDecimal(20))<= 0) {
						sum4++;
						maplist.put("16-20小时", sum4);
					} else if (big.compareTo(new BigDecimal(20))>0 && big.compareTo(new BigDecimal(24))<= 0) {
						sum5++;
						maplist.put("20-24小时", sum5);
					}
				}
			}
		}
		if ("c".equals(type) || "b".equals(type) || "".equals(type)) {
			int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0;
			maplist.put("0-7天", 0);
			maplist.put("7-15天", 0);
			maplist.put("15-30天", 0);
			maplist.put("30-60天", 0);
			maplist.put("60-90天", 0);
			maplist.put("90天以上", 0);
			for (RepairInformation r : rlist) {
				List<RepairRecord> rr = eventService.findRecordList(r.getId());
				List<RepairRecord> r1 = null;
				List<RepairRecord> r2 = null;
				// 已提交-已受理
				if ("ytjysl".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairSubmit.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已受理-已指派
				if ("yslyzp".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已指派-已完成
				if ("yzpywc".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已完成-验收完成
				if ("ywcyswc".equals(chosType) || "".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.echeckQualified.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				if (r1.size() > 0 && r2.size() > 0) {
					Date d1 = r1.get(0).getPresentDate();
					Date d2 = r2.get(0).getPresentDate();
					long c = dif2(d1, d2,type);
					avgreg+=c;
					BigDecimal big=calculate2(60*24*60,c);
					if (big.compareTo(new BigDecimal(0))>0 && big.compareTo(new BigDecimal(7))<= 0) {
						sum++;
						maplist.put("0-7天", sum);
					} else if (big.compareTo(new BigDecimal(7))>0 && big.compareTo(new BigDecimal(15))<= 0) {
						sum1++;
						maplist.put("7-15天", sum1);
					} else if (big.compareTo(new BigDecimal(15))>0 && big.compareTo(new BigDecimal(30))<= 0) {
						sum2++;
						maplist.put("15-30天", sum2);
					} else if (big.compareTo(new BigDecimal(30))>0 && big.compareTo(new BigDecimal(60))<= 0) {
						sum3++;
						maplist.put("30-60天", sum3);
					} else if (big.compareTo(new BigDecimal(60))>0 && big.compareTo(new BigDecimal(90))<= 0) {
						sum4++;
						maplist.put("60-90天", sum4);
					} else if (big.compareTo(new BigDecimal(90))>0){
						sum5++;
						maplist.put("90天以上", sum5);
					}
				}
			}
		}
		if ("d".equals(type)) {
			int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
			maplist.put("0-7天", 0);
			maplist.put("7-14天", 0);
			maplist.put("14-21天", 0);
			maplist.put("21-28天", 0);
			maplist.put("28天以上", 0);
			for (RepairInformation r : rlist) {
				List<RepairRecord> rr = eventService.findRecordList(r.getId());
				List<RepairRecord> r1 = null;
				List<RepairRecord> r2 = null;
				// 已提交-已受理
				if ("ytjysl".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairSubmit.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已受理-已指派
				if ("yslyzp".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAccept.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已指派-已完成
				if ("yzpywc".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.repairAppoint.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				// 已完成-验收完成
				if ("ywcyswc".equals(chosType) || "".equals(chosType)) {
					r1 = rr.stream()
							.filter(rp -> RepairConst.maintainFinish.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
					r2 = rr.stream()
							.filter(rp -> RepairConst.echeckQualified.getIndex()
							.equals(rp.getRepairStatus()))
							.collect(Collectors.toList());
				}
				if (r1.size() > 0 && r2.size() > 0) {
					Date d1 = r1.get(0).getPresentDate();
					Date d2 = r2.get(0).getPresentDate();
					long c = dif2(d1, d2,type);
					avgreg+=c;
					BigDecimal big=calculate2(60*24*60,c);
					if (big.compareTo(new BigDecimal(0))>0 && big.compareTo(new BigDecimal(7))<= 0) {
						sum++;
						maplist.put("0-7天", sum);
					} else if (big.compareTo(new BigDecimal(7))>0 && big.compareTo(new BigDecimal(14))<= 0) {
						sum1++;
						maplist.put("7-14天", sum1);
					} else if (big.compareTo(new BigDecimal(14))>0 && big.compareTo(new BigDecimal(21))<= 0) {
						sum2++;
						maplist.put("14-21天", sum2);
					} else if (big.compareTo(new BigDecimal(21))>0 && big.compareTo(new BigDecimal(28))<= 0) {
						sum3++;
						maplist.put("21-28天", sum3);
					} else if (big.compareTo(new BigDecimal(28))>0 && big.compareTo(new BigDecimal(31))<= 0) {
						sum4++;
						maplist.put("28天以上", sum4);
					}
				}
			}
		}
	      if("a".equals(type)){
	    	  maplist.put("avgreg",calculate(rlist.size(),avgreg/(60*24)));
	      }else{
	    	  maplist.put("avgreg",calculate(rlist.size(),avgreg/(60*24*60)));
	      }
		return maplist;
	}

	
	/**
	 * @return :返回一个Map
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "mapDataRepair", method = RequestMethod.POST)
	public Map<String, Object> mapDataRepair(RepairAnalysisDto dto,String type,String dynamicTime)
			throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(dynamicTime)) {
			changMoreDate(dynamicTime, dto);
		}
		DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
		Book book = CompleteUtil.complete(bf.format(dto.getStartDate()), bf.format(dto.getEndDate()), dto.getDateType());
		if("bxgd".equals(type)){ //报修工单数
			List<RepairAnalysisDto> rlist = eventService.getRepairBxgdList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxgd", book.getPage());
			}
			return maplist;
		}
		if("bxyz".equals(type)){ //报修业主数
			List<RepairAnalysisDto> rlist = eventService.getRepairBxyzList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxyz", book.getPage());
			}
			return maplist;
		}
		if("bxl".equals(type)){ //报修率
			List<RepairAnalysisDto> rlist = eventService.getRepairBxlList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxl", book.getPage());
			}
			return maplist;
		}
		if("bxwcl".equals(type)){ //报修完成率
			List<RepairAnalysisDto> rlist = eventService.getRepairBxwclList(dto);
			 Map<String,Object>  mList = new HashMap<>();
			    rlist.stream().collect(  
			               Collectors.groupingBy(RepairAnalysisDto::getxTime, 
			            		   Collectors.toList())).forEach((n,m)->{
			            				long sum  =  m.stream()
			            						.filter(p->RepairConst.echeckQualified.getIndex().equals(p.getRepairType())
			            								||RepairConst.appraise.getIndex().equals(p.getRepairType())
			            								).count();
			            				mList.put(n, calculate(m.size(),sum));
			            		   });
			    for(Entry<String, Object> entry : mList.entrySet()){
					Integer integer = book.getMenu().get(entry.getKey());
					Map<String, Object> map = book.getPage()
							.get(integer == null ? 0 : integer);
					map.put(entry.getKey(), entry.getValue());
					maplist.put("bxwcl", book.getPage());
				}
			return maplist;
		}
		if("bxtsl".equals(type)){ //报修投诉率
			List<RepairAnalysisDto> rlist = eventService.getRepairBxtslList(dto);
			 Map<String,Object>  mList = new HashMap<>();
		    rlist.stream().collect(  
		               Collectors.groupingBy(RepairAnalysisDto::getxTime, 
		            		   Collectors.toList())).forEach((n,m)->{
		            				long sum  =  m.stream().filter(p->StringUtils.isNotEmpty(p.getComplainID())).count();
		            				mList.put(n, calculate(m.size(),sum));
		            		   });
		    for(Entry<String, Object> entry : mList.entrySet()){
				Integer integer = book.getMenu().get(entry.getKey());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(entry.getKey(), entry.getValue());
				maplist.put("bxtsl", book.getPage());
			}
			return maplist;
		}
		if("yzAvg".equals(type)){ //报修业主区域平均报修条数
			List<RepairAnalysisDto> rlist = eventService.getRepairyzAvgList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("yzAvg", book.getPage());
			}
			return maplist;
		}
		if("allqyAvg".equals(type)){ //全部业主区域平均报修条数
			List<RepairAnalysisDto> rlist = eventService.getRepairAllqyAvgList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("allqyAvg", book.getPage());
			}
			return maplist;
		}
		
		if("bxAvgTimel".equals(type)){ //报修平均时长
			List<RepairAnalysisDto> rlist = eventService.getRepairAVGTimeList(dto);
		    Map<String,Object>  mList = new HashMap<>();
		    rlist.stream().collect(  
		               Collectors.groupingBy(RepairAnalysisDto::getxTime, 
		            		   Collectors.toList())).forEach((n,m)->{
		            				long sum  =  m.stream().mapToInt(p -> {
										return (int) dif(p.getEndDate(), p.getStartDate());
									}).reduce(0, Integer::sum);
		            				mList.put(n, calculate(m.size(),sum));
		            		   });
		    for(Entry<String, Object> entry : mList.entrySet()){
				Integer integer = book.getMenu().get(entry.getKey());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(entry.getKey(), entry.getValue());
				maplist.put("bxAvgTimel", book.getPage());
			}
			return maplist;
		}
		
		return maplist;
	}
	/**
	 * @return :返回一个Map
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "complainDetailAnalysis", method = RequestMethod.POST)
	public Map<String, Object> complainDetailAnalysis(RepairAnalysisDto dto,String type,String dynamicTime)
			throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(dynamicTime)) {
			changMoreDate(dynamicTime, dto);
		}
		DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
		Book book = CompleteUtil.complete(bf.format(dto.getStartDate()), bf.format(dto.getEndDate()), dto.getDateType());
		if("bxgd".equals(type)){ //投诉工单数
			dto.setRepairType("0");
			List<RepairAnalysisDto> rlist = eventService.getRepairBxgdList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxgd", book.getPage());
			}
			return maplist;
		}
		if("bxyz".equals(type)){ //投诉业主数
			dto.setRepairType("0");
			List<RepairAnalysisDto> rlist = eventService.getRepairBxyzList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxyz", book.getPage());
			}
			return maplist;
		}
		if("bxl".equals(type)){ //投诉建筑体数
			dto.setRepairType("0");
			List<RepairAnalysisDto> rlist = eventService.getBuildRoomAnalsis(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("bxl", book.getPage());
			}
			return maplist;
		}
		if("bxwcl".equals(type)){ //投诉完成率
			List<RepairAnalysisDto> rlist = eventService.getRepairAVGComplainTimeList(dto);
			Map<String,Object>  mList = new HashMap<>();
			rlist.stream().collect(  
					Collectors.groupingBy(RepairAnalysisDto::getxTime, 
							Collectors.toList())).forEach((n,m)->{
								long sum  =  m.stream().filter(pr ->StringUtils.isNotEmpty(pr.getComplainID())&&pr.getEndDate()!=null)
														.count();
								mList.put(n, calculate(m.size(),sum));
							});
			for(Entry<String, Object> entry : mList.entrySet()){
				Integer integer = book.getMenu().get(entry.getKey());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(entry.getKey(), entry.getValue());
				maplist.put("bxwcl", book.getPage());
			}
			return maplist;
		}
		if("bxtsl".equals(type)){ //报修投诉率
			List<RepairAnalysisDto> rlist = eventService.getRepairBxtslList(dto);
			 Map<String,Object>  mList = new HashMap<>();
		    rlist.stream().collect(  
		               Collectors.groupingBy(RepairAnalysisDto::getxTime, 
		            		   Collectors.toList())).forEach((n,m)->{
		            				long sum  =  m.stream().filter(p->StringUtils.isNotEmpty(p.getComplainID())).count();
		            				mList.put(n, calculate(m.size(),sum));
		            		   });
		    for(Entry<String, Object> entry : mList.entrySet()){
				Integer integer = book.getMenu().get(entry.getKey());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(entry.getKey(), entry.getValue());
				maplist.put("bxtsl", book.getPage());
			}
			return maplist;
		}
		if("yzAvg".equals(type)){ //报修业主区域平均投诉条数
			dto.setRepairType("0");
			List<RepairAnalysisDto> rlist = eventService.getRepairyzAvgList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("yzAvg", book.getPage());
			}
			return maplist;
		}
		if("allqyAvg".equals(type)){ //全部业主区域平均投诉条数
			dto.setRepairType("0");
			List<RepairAnalysisDto> rlist = eventService.getRepairAllqyAvgList(dto);
			for(RepairAnalysisDto d:rlist){
				Integer integer = book.getMenu().get(d.getxTime());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(d.getxTime(), d.getSumRepair());
				maplist.put("allqyAvg", book.getPage());
			}
			return maplist;
		}
		
		if("bxAvgTimel".equals(type)){ //投诉平均处理时长
			List<RepairAnalysisDto> rlist = eventService.getRepairAVGComplainTimeList(dto);
			Map<String,Object>  mList = new HashMap<>();
			rlist.stream().collect(  
					Collectors.groupingBy(RepairAnalysisDto::getxTime, 
							Collectors.toList())).forEach((n,m)->{
								long sum  =  m.stream().filter(pr ->StringUtils.isNotBlank(pr.getRepairType())&&pr.getEndDate()!=null)
										.mapToInt(p -> {
									return (int) dif(p.getEndDate(), p.getStartDate());
								}).reduce(0, Integer::sum);
								mList.put(n, calculate(m.size(),sum));
							});
			for(Entry<String, Object> entry : mList.entrySet()){
				Integer integer = book.getMenu().get(entry.getKey());
				Map<String, Object> map = book.getPage()
						.get(integer == null ? 0 : integer);
				map.put(entry.getKey(), entry.getValue());
				maplist.put("bxAvgTimel", book.getPage());
			}
			return maplist;
		}
		
		/*	// 查询所有主楼的事件
		RepairInformation repair = new RepairInformation();
		List<RepairInformation> rlist = eventService.getRepairList(new RepairInformation());
		// 查询所有父级类型获取list;
		List<RepairInformationType> repairType = repairInformationService
				.getTypeByCode("0");
		Map<String, Object> sum;
		for (RepairInformationType s : repairType) {
			sum = new HashMap<>();
			// java8
			Long count = rlist.stream().filter(p -> {
				String[] t = p.getRepairTypeCode().split("-");
				return t[0].equals(s.getTypeId());
			}).count();
			sum.put(s.getTypeCode(), count);
			maplist.put(s.getTypeCode(), sum);
		}*/
		return maplist;
	}
	
	/**
	 * @return :返回一个Map(自己理解)
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "repairAnalysisTGI", method = RequestMethod.POST)
	public Map<String, Object> repairTGI(String type,RepairInformation repair,String analtype) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		if("tssjjg".equals(analtype)){
			List<RepairInformation>  clist =eventService.getBtomtime(repair);
			long avg= clist.stream().mapToInt(p->{
				return (int)dif(p.getCreatedDt(), p.getEndTime());
			}).reduce(0, Integer::sum);
			if(clist.size()!=0){
				BigDecimal percent = calculate(clist.size(), avg);
				maplist.put("repairLevel", percent);
				for (EventCommon s : space) {
					List<RepairInformation> cclist=clist.stream().filter(
							p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
							.collect(Collectors.toList());
					long area=cclist.stream().mapToInt(p->{
						return (int)dif(p.getCreatedDt(), p.getEndTime());
					}).reduce(0, Integer::sum);
					if(cclist.size()!=0){
						BigDecimal percent1 = calculate(cclist.size(), area);
						if (percent1.compareTo(percent) < 0) {
							// 当前楼宇间隔时间小于平均率,取反
							maplist.put(s.getSpaceName(),
									percent1.multiply(new BigDecimal(-1)));
						} else {
							maplist.put(s.getSpaceName(),percent1);
						}
					}else{
						maplist.put(s.getSpaceName(), 0);
					}
				}
			}else{
				maplist.put("repairLevel", 0);
			}
			return maplist;
		}
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数
		if("bxgd".equals(analtype)){ //报修工单数
			List<RepairInformation> rlist = eventService.getRepairBxgdCount(repair);
			for (EventCommon s : space) {
				List<RepairInformation> cclist=rlist.stream().filter(
						p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
						.collect(Collectors.toList());
				//园区平均工单数
				String avg = df.format((float)rlist.size()/space.size());//返回的是String类型
				if(cclist.size()!=0){
					maplist.put("repairLevel", avg);
					maplist.put(s.getSpaceName(),cclist.size());
				}else{
					maplist.put(s.getSpaceName(), 0);
				}
			}
			return maplist;
		}
		if("bxyz".equals(analtype)){ //报修业主数
			List<RepairInformation> rlist = eventService.getRepairBxyzCount(repair);
			List<RepairInformation> newList = rlist.stream().collect(
					collectingAndThen(
							toCollection(() -> new TreeSet<>(comparing(RepairInformation::getRepairPeopleName))), ArrayList::new));
			for (EventCommon s : space) {
				List<RepairInformation> cclist=newList.stream().filter(
						p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
						.collect(Collectors.toList());
				//园区平均业主数
				String avg = df.format((float)newList.size()/space.size());//返回的是String类型
				if(cclist.size()!=0){
					maplist.put("repairLevel", avg);
					maplist.put(s.getSpaceName(),cclist.size());
				}else{
					maplist.put(s.getSpaceName(), 0);
				}
			}
			return maplist;
		}
		if("bxl".equals(analtype)){ //报修率
			List<RepairInformation> rlist = eventService.getRepairBxlCount(repair);
			List<RepairInformation> clist = eventService.getRepairBxgdCount(repair);
			int sumRoom = eventService.getSumRooms();
			double f1 = new BigDecimal((float)clist.size()/sumRoom*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			Map<String, String> smap = rlist.stream().collect(Collectors.toMap(RepairInformation::getCause, RepairInformation::getCount));
			for (EventCommon s : space) {
				if(smap.containsKey(s.getSpaceName())){
					maplist.put("repairLevel",f1);
					maplist.put(s.getSpaceName(),smap.get(s.getSpaceName()));
				}else{
					maplist.put(s.getSpaceName(),0);
				}
			}
			return maplist;
		}


		// 查询不包含状态为 已提交 的所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair)
				.stream().filter(p->!RepairConst.repairSubmit.getIndex().equals(p.getRepairStatus())).collect(Collectors.toList());
	    RepairInformationDto repairInformationDto=new RepairInformationDto();
	    repairInformationDto.setStartTime(repair.getCreatedDt());
	    repairInformationDto.setEndTime(new Date());
	    List<RepairInformationDto> listOnly=repairInformationService.selectOnlyList(repairInformationDto);
		int sal=0;
	    for(RepairInformationDto r:listOnly){
	    	int qy=0;
	    	for(RepairInformation re:rlist){
	    		if(re.getRepairAddressCode().equals(r.getRepairAddressCode()) && re.getRepairTypeCode().equals(r.getRepairTypeCode())){
	    				qy++;
	    			}
	    		}
	    	if(qy%2!=0){
	    		qy=qy-1;
	    	}
	    	sal+=qy/2;
	    }
	    if(rlist.size()!=0){
			// 计算当前园区TGI
	    	BigDecimal percent = calculate(rlist.size(),sal);
			maplist.put("repairLevel",percent);
			for (EventCommon s : space) {
				List<RepairInformation> rrlist = rlist.stream().filter(
						p -> p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
						.collect(Collectors.toList());
			    if(rrlist.size()!=0){
					int area=0;
				    for(RepairInformationDto r:listOnly){
				    	int qy=0;
				    	for(RepairInformation re:rrlist){
				    		if(re.getRepairAddressCode().equals(r.getRepairAddressCode()) && re.getRepairTypeCode().equals(r.getRepairTypeCode())){
				    				qy++;
				    			}
				    		}
				    	if(qy%2!=0){
				    		qy=qy-1;
				    	}
				    	area+=qy/2;
				    }
					// 计算当前楼体TGI
					BigDecimal areap = calculate(rrlist.size(),area);
					if (areap.compareTo(percent) < 0) {
						maplist.put(s.getSpaceName(),areap.multiply(new BigDecimal(-1)));
					} else {
						maplist.put(s.getSpaceName(),areap);
					}
			    }else{
			    	maplist.put(s.getSpaceName(),0);
			    }

			}
	    }else{
			maplist.put("repairLevel",0);
	    }
		return maplist;
	}



	/**
	 * @return :返回一个Map包含各个楼宇的投诉事件总数
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "treemapComplainData", method = RequestMethod.POST)
	public Map<String, Object> treemapComplainData(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}

		// 查询所有投诉事件
		List<RepairInformation> rlist = eventService
				.findComplainList(repair);
		// 查询所有楼宇
		List<EventCommon> space = eventService.getSpaceList();
		Map<String, Object> sum;
		for (EventCommon s : space) {
			sum = new HashMap<>();
			Long count = rlist.stream()
					.filter(p -> StringUtils.isNotEmpty(p.getComplainId()))
					.filter(p->p.getRepairAddressCode().contains(s.getSpaceCode())) // java8
					.count();
			sum.put(s.getSpaceCode(), count);
			maplist.put(s.getSpaceName(), sum);
		}
		maplist.put("naturalEvent", rlist.stream().filter(
				p ->StringUtils.isEmpty(p.getComplainId())) // java8
				.count());
		return maplist;
	}
	/**
	 * @return :返回一个Map包含各个楼宇的投诉事件类型分布
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "treemapCDataRepair", method = RequestMethod.POST)
	public Map<String, Object> treemapCDataRepair(String buildName, String type,RepairInformation repair)
			throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		repair.setBuildName(buildName);
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有主楼的事件
		List<RepairInformation> rlist = eventService.getRepairList(repair);
		// 查询所有父级类型获取list;
		List<RepairInformationType> repairType = repairInformationService
				.getTypeByCode("0");
		Map<String, Object> sum;
		for (RepairInformationType s : repairType) {
			sum = new HashMap<>();
			// java8
			Long count = rlist.stream().filter(p -> {
				String[] t = p.getRepairTypeCode().split("-");
				return t[0].equals(s.getTypeId())&&StringUtils.isNotEmpty(p.getComplainId());
			}).count();
			sum.put(s.getTypeCode(), count);
			maplist.put(s.getTypeCode(), sum);
		}
		return maplist;
	}
	
	/**
	 * @return :返回一个Map 各个时间段报修的工单个数
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "analtsgd")
	public Map<String, Object> analtsgd(String type,RepairInformation repair) throws ParseException {
		Map<String, Object> maplist = new LinkedHashMap<>();
		if (StringUtils.isNotEmpty(type)) {
			changDate(type, repair);
		}
		// 查询所有投诉事件
		List<RepairInformation> rlist = eventService
				.findComplainList(repair);
		int sum = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0,
				sum7 = 0, sum8 = 0, sum9 = 0, sum10 = 0, sum11 = 0;
		String format = "HH:mm:ss";
		Date startTime = new SimpleDateFormat(format).parse("00:00:00");
		Date endTime = new SimpleDateFormat(format).parse("01:59:59");
		Date startTime1 = new SimpleDateFormat(format).parse("02:00:00");
		Date endTime1 = new SimpleDateFormat(format).parse("03:59:59");
		Date startTime2 = new SimpleDateFormat(format).parse("04:00:00");
		Date endTime2 = new SimpleDateFormat(format).parse("05:59:59");
		Date startTime3 = new SimpleDateFormat(format).parse("06:00:00");
		Date endTime3 = new SimpleDateFormat(format).parse("07:59:59");
		Date startTime4 = new SimpleDateFormat(format).parse("08:00:00");
		Date endTime4 = new SimpleDateFormat(format).parse("09:59:59");
		Date startTime5 = new SimpleDateFormat(format).parse("10:00:00");
		Date endTime5 = new SimpleDateFormat(format).parse("11:59:59");
		Date startTime6 = new SimpleDateFormat(format).parse("12:00:00");
		Date endTime6 = new SimpleDateFormat(format).parse("13:59:59");
		Date startTime7 = new SimpleDateFormat(format).parse("14:00:00");
		Date endTime7 = new SimpleDateFormat(format).parse("15:59:59");
		Date startTime8 = new SimpleDateFormat(format).parse("16:00:00");
		Date endTime8 = new SimpleDateFormat(format).parse("17:59:59");
		Date startTime9 = new SimpleDateFormat(format).parse("18:00:00");
		Date endTime9 = new SimpleDateFormat(format).parse("19:59:59");
		Date startTime10 = new SimpleDateFormat(format).parse("20:00:00");
		Date endTime10 = new SimpleDateFormat(format).parse("21:59:59");
		Date startTime11 = new SimpleDateFormat(format).parse("22:00:00");
		Date endTime11 = new SimpleDateFormat(format).parse("23:59:59");
		maplist.put("0", 0);
		maplist.put("1", 0);
		maplist.put("2", 0);
		maplist.put("3", 0);
		maplist.put("4", 0);
		maplist.put("5", 0);
		maplist.put("6", 0);
		maplist.put("7", 0);
		maplist.put("8", 0);
		maplist.put("9", 0);
		maplist.put("10", 0);
		maplist.put("11", 0);
		maplist.put("12", 0);
		maplist.put("13", 0);
		maplist.put("14", 0);
		maplist.put("15", 0);
		maplist.put("16", 0);
		maplist.put("17", 0);
		maplist.put("18", 0);
		maplist.put("19", 0);
		maplist.put("20", 0);
		maplist.put("21", 0);
		maplist.put("22", 0);
		maplist.put("23", 0);
		maplist.put("24", 0);
		for (RepairInformation r : rlist) {
			String nowTime1 = new SimpleDateFormat("HH:mm:ss")
					.format(r.getCreatedDt());
			Date nowTime = new SimpleDateFormat(format).parse(nowTime1);
			if (isEffectiveDate(nowTime, startTime, endTime)) {
				sum++;
				maplist.put("1", sum);
			}
			if (isEffectiveDate(nowTime, startTime1, endTime1)) {
				sum1++;
				maplist.put("3", sum1);
			}
			if (isEffectiveDate(nowTime, startTime2, endTime2)) {
				sum2++;
				maplist.put("5", sum2);
			}
			if (isEffectiveDate(nowTime, startTime3, endTime3)) {
				sum3++;
				maplist.put("7", sum3);
			}
			if (isEffectiveDate(nowTime, startTime4, endTime4)) {
				sum4++;
				maplist.put("9", sum4);
			}
			if (isEffectiveDate(nowTime, startTime5, endTime5)) {
				sum5++;
				maplist.put("11", sum5);
			}
			if (isEffectiveDate(nowTime, startTime6, endTime6)) {
				sum6++;
				maplist.put("13", sum6);
			}
			if (isEffectiveDate(nowTime, startTime7, endTime7)) {
				sum7++;
				maplist.put("15", sum7);
			}
			if (isEffectiveDate(nowTime, startTime8, endTime8)) {
				sum8++;
				maplist.put("17", sum8);
			}
			if (isEffectiveDate(nowTime, startTime9, endTime9)) {
				sum9++;
				maplist.put("19", sum9);
			}
			if (isEffectiveDate(nowTime, startTime10, endTime10)) {
				sum10++;
				maplist.put("21", sum10);
			}
			if (isEffectiveDate(nowTime, startTime11, endTime11)) {
				sum11++;
				maplist.put("23", sum11);
			}
		}
		return maplist;
	}
	/**
	 * @return :返回一个Map包含时间跨度
	 * @since JDK 1.8
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "getMoreDates")
	public Map<String, Object> getMoreDates(String timeType) throws ParseException {
		String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Map<String, Object> maplist = new LinkedHashMap<>();
		if("0".equals(timeType)){ //时间维度为年时
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 3);
			Date day = yc.getTime();
			String ysStr = new SimpleDateFormat("yyyy/MM/dd").format(day);
			maplist.put("years", "近三年" + "(" + ysStr + "-" + today + ")");
			return maplist;
		}
		if("1".equals(timeType)){  //时间维度为月时
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = new SimpleDateFormat("yyyy/MM/dd").format(day);
			maplist.put("year", "近一年" + "(" + ysStr + "-" + today + ")");

			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = new SimpleDateFormat("yyyy/MM/dd").format(day1);
			maplist.put("months", "近一季度" + "(" + mc3Str + "-" + today + ")");
			return maplist;
		}
		if("2".equals(timeType)){ //时间维度为日时
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.DAY_OF_MONTH, m1c.get(Calendar.DAY_OF_MONTH) - 30);
			Date month = m1c.getTime();
			String m1cStr = new SimpleDateFormat("yyyy/MM/dd").format(month);
			maplist.put("daym", "近一月" + "(" + m1cStr + "-" + today + ")");
			// 周
			Calendar mdc = Calendar.getInstance();
			mdc.set(Calendar.DATE, m1c.get(Calendar.DATE) - 7);
			Date days = mdc.getTime();
			String mdcStr = new SimpleDateFormat("yyyy/MM/dd").format(days);
			maplist.put("days", "近一周" + "(" + mdcStr + "-" + today + ")");
		}
		return maplist;
	}
	
	private void changMoreDate(String type, RepairAnalysisDto repair) {
		Calendar y = Calendar.getInstance();
		Date day = null;
		if(repair.getStartDate()==null){
			if ("years".equals(type)) {
				day = new Date();
				y.set(Calendar.YEAR, y.get(Calendar.YEAR) - 3);
				day = y.getTime();
			}
			if ("year".equals(type)) {
				y.set(Calendar.YEAR, y.get(Calendar.YEAR) - 1);
				day = y.getTime();
			}
			if ("months".equals(type)) {
				y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 3);
				day = y.getTime();
			}
			if ("daym".equals(type)) {
				y.set(Calendar.DAY_OF_MONTH, y.get(Calendar.DAY_OF_MONTH) - 30);
				day = y.getTime();
			}
			if ("days".equals(type)) {
				y.set(Calendar.DATE, y.get(Calendar.DATE) - 7);
				day = y.getTime();
			}
			repair.setStartDate(day);
			repair.setEndDate(new Date());
		}
	}
	private void changDate(String type, RepairInformation repair) {
		Calendar y = Calendar.getInstance();
		Date day = null;
		if(repair.getCreatedDt()==null){
		if ("a".equals(type)) {
			day = new Date();
		}
		if ("b".equals(type)) {
			y.set(Calendar.YEAR, y.get(Calendar.YEAR) - 1);
			day = y.getTime();
		}
		if ("c".equals(type)) {
			y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 3);
			day = y.getTime();
		}
		if ("d".equals(type)) {
			y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 1);
			day = y.getTime();
		}
		repair.setCreatedDt(day);
		repair.setUpdatedDt(new Date());
		}

	}

	/**
	 * @return :int (计算两个时间的分钟差)
	 * @since JDK 1.8
	 * @author cuiwenjian
	 */
	public long dif(Date createdDt, Date updateDt) {
		Instant instantc = createdDt.toInstant();
		Instant instantu = updateDt.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime c = instantc.atZone(zoneId).toLocalDateTime();
		LocalDateTime u = instantu.atZone(zoneId).toLocalDateTime();
		long daysDiff = ChronoUnit.MINUTES.between(c, u);
		return  daysDiff;
	}
	/**
	 * @return :long (计算两个时间的差)
	 * @since JDK 1.8
	 * @author cuiwenjian
	 */
	public long dif2(Date createdDt, Date updateDt, String type) {
		Instant instantc = createdDt.toInstant();
		Instant instantu = updateDt.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime c = instantc.atZone(zoneId).toLocalDateTime();
		LocalDateTime u = instantu.atZone(zoneId).toLocalDateTime();
		long daysDiff = ChronoUnit.SECONDS.between(c, u);
		return  daysDiff;
	}

	/**
	 * @Title: calculate (平均率,保留小数点两位) @param @param
	 *         size @param @param size2 @param @return 参数 @return BigDecimal
	 *         返回类型 @throws
	 */

	private BigDecimal calculate(int size, long size2) {
		if(size==0){
			return new BigDecimal("0");
		}
		BigDecimal percent;
		BigDecimal all = new BigDecimal(size);
		BigDecimal total = new BigDecimal(size2);

		// 保留两位小数点
		percent = total.divide(all, 2, BigDecimal.ROUND_HALF_UP);
		return percent;
	}
	
	/**
	 * @Title: calculate (平均率,保留小数点六位) @param @param
	 *         size @param @param size2 @param @return 参数 @return BigDecimal
	 *         返回类型 @throws
	 */
	private BigDecimal calculate2(int size, long size2) {
		if(size==0){
			return new BigDecimal("0");
		}
		BigDecimal percent;
		BigDecimal all = new BigDecimal(size);
		BigDecimal total = new BigDecimal(size2);

		// 保留六位小数点
		percent = total.divide(all, 6, BigDecimal.ROUND_HALF_UP);
		return percent;
	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * 
	 * @param nowTime
	 *            当前时间
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @author jqlin
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime,
			Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

}
