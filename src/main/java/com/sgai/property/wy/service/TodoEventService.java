
package com.sgai.property.wy.service;

import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.TodoEventDao;
import com.sgai.property.wy.dto.RepairConst;
import com.sgai.property.wy.entity.EventCommon;
import com.sgai.property.wy.entity.RepairAnalysisDto;
import com.sgai.property.wy.entity.RepairInformation;
import com.sgai.property.wy.entity.RepairRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * @ClassName: TodoEventService
 * @Description: 查询所有的事件
 * @author cui
 * @date 2018年2月6日
 * @Company 首自信--智慧城市创新中心
 */
@Service
public class TodoEventService
		extends
        CrudServiceExt<TodoEventDao, EventCommon> {

	@Autowired
	private CallService callService;
	/**
	 * @throws ParseException
	 * 
	 * @Title: queryEvent (查询事件并分类) @param @param
	 *         EventCommon @param @return 参数 @return Map<String,Object>
	 *         返回类型 @throws
	 */
	public Map<String, Object> queryEvent(EventCommon event)
			throws ParseException {
		Map<String, Object> map = new HashMap<>();

		List<EventCommon> all = dao.findList(event);
		int events1 = 0;
		int events2 = 0;
		int events3 = 0;
		int events4 = 0;
		int events5 = 0;
		int events11 = 0;
		for (EventCommon e : all) {
			if (RepairConst.close.getIndex().equals(e.getRepairStatus())) { // 关闭事件总数
				events1++;
			}
			if (RepairConst.chargeback.getIndex().equals(e.getRepairStatus())) { // 退单事件总数
				events2++;
			}
			if (RepairConst.repairSubmit.getIndex().equals(e.getRepairStatus())) { // 未受理事件总数
				events3++;
			}
			if (RepairConst.repairAccept.getIndex().equals(e.getRepairStatus())) { // 未指派事件总数
				events4++;
			}
			if (RepairConst.maintainFinish.getIndex().equals(e.getRepairStatus())) { // 未验收事件总数
				events5++;
			}
			if (RepairConst.repairAppoint.getIndex().equals(e.getRepairStatus())) { // 未处理事件总数
				events11++;
			}
		} ;
		String userId = event.getUser();
		event.setUser("");
		event.setRepairStatus("1");
		// 来电总数
		int call = dao.findCallList(event);
		// 日志总数
		int log = dao.findLogList(event);
		event.setRepairStatus("");
		List<String> role = callService.queryRole(userId);
		role.forEach(e -> {
			if (!"wy_jl".equals(e)) {
				if (StringUtils.isNotBlank(event.getUser())) {
					event.setUser(userId);
				}
			} else {
				event.setUser("");
				event.setType("0"); // 经理
			}
		});
		event.setStatus("1");
		// 我的未处理来电事件
		int mycall = dao.findCallList(event);
		int mylog = dao.findLogList(event);
		map.put("mycall", mycall);
		map.put("mylog", mylog);
		// 发布公告总数
		List<EventCommon> puNotice = dao.findPuNoticeList(event);
		Long pubAll = puNotice.stream()
				.filter(e -> "已发布".equals(e.getPublishNotice())) // 发布总数
				.count();
		map.put("pn", pubAll);

		// 待审核总数
		Long pnwait = puNotice.stream()
				.filter(e -> "待审核".equals(e.getNotcieWait())) // 待审核总数
				.count();
		map.put("pnwait", pnwait);

		List<EventCommon> notice = dao.findNoticeList(event); // 我收到的总数
		map.put("pnown", notice.size());

		// 填充今日时间
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date strD = lsdStrFormat.parse(date);
		event.setDate(strD);
		event.setUser("");
		event.setStatus("");
		event.setRepairStatus("1");
		// 今日新增来电
		int todayCall = dao.findCallList(event);
		// 今日新增日志
		int todayLog = dao.findLogList(event);
		event.setStatus("1");
		event.setRepairStatus("");
		role.forEach(e -> {
			if (!"wy_jl".equals(e)) {
				if (StringUtils.isNotBlank(event.getUser())) {
					event.setUser(userId);
				}
			} else {
				event.setUser("");
				event.setType("0"); // 经理
			}
		});

		// 今日新增的我的未处理事件
		int tomyCall = dao.findCallList(event);
		// 今日新增我的未处理日志
		int tmyLog = dao.findLogList(event);
		map.put("tomyCall", tomyCall);
		map.put("tmyLog", tmyLog);
		// 今日新增发布公告
		List<EventCommon> todaypn = dao.findPuNoticeList(event);

		Long jpun = todaypn.stream()
				.filter(e -> "已发布".equals(e.getPublishNotice())) // 今日新增发布
				.count();
		map.put("jpn", jpun);
		// 待审核总数
		Long jwaitNotice = todaypn.stream()
				.filter(e -> "待审核".equals(e.getNotcieWait())) // 今日新增待审核
				.count();
		map.put("jpnwait", jwaitNotice);

		List<EventCommon> tnotice = dao.findNoticeList(event); // 今日新增我收到的公告
		map.put("jpnown", tnotice.size());

		List<EventCommon> tList = dao.findTodayList(event); // 今日所有事件
		int events6 = 0;
		int events7 = 0;
		int events8 = 0;
		int events9 = 0;
		int events10 = 0;
		int events12 = 0;
		for (EventCommon e : tList) {
			if (RepairConst.close.getIndex().equals(e.getRepairStatus())) { // 今日关闭事件总数
				events6++;
			}
			if (RepairConst.chargeback.getIndex().equals(e.getRepairStatus())) { // 今日退单事件总数
				events7++;
			}
			if (RepairConst.repairSubmit.getIndex().equals(e.getRepairStatus())) { // 今日未受理事件总数
				events8++;
			}
			if (RepairConst.repairAccept.getIndex().equals(e.getRepairStatus())) { // 今日未指派事件总数
				events9++;
			}
			if (RepairConst.maintainFinish.getIndex().equals(e.getRepairStatus())) { // 今日未验收事件总数
				events10++;
			}
			if (RepairConst.repairAppoint.getIndex().equals(e.getRepairStatus())) { // 今日未处理事件总数
				events12++;
			}
		} ;

		map.put("close", events1);
		map.put("back", events2);
		map.put("wsl", events3);
		map.put("wzp", events4);
		map.put("wys", events5);
		map.put("jclose", events6);
		map.put("jback", events7);
		map.put("jwsl", events8);
		map.put("jwzp", events9);
		map.put("jwys", events10);
		map.put("jwcl", events11);
		map.put("jxwcl", events12);
		map.put("eventsAll", all.size());
		map.put("call", call);
		map.put("todayCall", todayCall);
		map.put("log", log);
		map.put("todayLog", todayLog);
		map.put("callAndlog", log + call);
		return map;
	}

	/**
	 * @Title: getSpaceList (获取所有楼宇的名字) @param @return
	 *         参数 @return List<String> 返回类型 @throws
	 */

	public List<EventCommon> getSpaceList() {
		return dao.getSpaceList();
	}

	/**
	 * @Title: getRepairList () @param @return 参数 @return
	 *         List<RepairInformation> 返回类型 @throws
	 */

	public List<RepairInformation> getRepairList(RepairInformation repair) {
		return dao.getRepairList(repair);
	}

	/**
	 * @Title: getSumRoom (统计所有的报修区域room) @param @return
	 *         参数 @return int 返回类型 @throws
	 */

	public int getSumRoom() {
		return dao.getSumRoom();
	}

	/**
	 * @param nodeCode 
	 * @Title: getSumBuildRoom (获取当前楼宇所有room) @param @return
	 *         参数 @return int 返回类型 @throws
	 */

	public int getSumBuildRoom(String nodeCode) {
		return dao.getSumBuildRoom(nodeCode);
	}

	  
	    /**  
	    * @Title: findRecordList  
	    * (查询报修事件记录)
	    * @param @param id
	    * @param @return    参数  
	    * @return List<RepairRecord>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairRecord> findRecordList(String id) {
		return dao.findRecordList(id);
	}

		  
		    /**  
		    * @Title: getRepairBxgdList  
		    * (获取报修工单数)
		    * @param @param repair
		    * @param @return    参数  
		    * @return List<RepairAnalysisDto>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairAnalysisDto> getRepairBxgdList(
				RepairAnalysisDto repair) {
			return dao.getRepairBxgdList(repair);
		}

			  
	    /**  
	    * @Title: getRepairBxyzList  
	    * (获取报修业主数)
	    * @param @param dto
	    * @param @return    参数  
	    * @return List<RepairAnalysisDto>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairAnalysisDto> getRepairBxyzList(
			RepairAnalysisDto dto) {
		return dao.getRepairBxyzList(dto);
	}

		  
		    /**  
		    * @Title: getRepairBxlList  
		    * (报修率)
		    * @param @param dto
		    * @param @return    参数  
		    * @return List<RepairAnalysisDto>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairAnalysisDto> getRepairBxlList(RepairAnalysisDto dto) {
			return dao.getRepairBxlList(dto);
		}

	  
	    /**  
	    * @Title: getRepairBxwclList  
	    * (报修完成率)
	    * @param @param dto
	    * @param @return    参数  
	    * @return List<RepairAnalysisDto>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairAnalysisDto> getRepairBxwclList(
			RepairAnalysisDto dto) {
		return dao.getRepairBxwclList(dto);
	}
				  
	    /**  
	    * @Title: getRepairBxtslList  
	    * (报修投诉率)
	    * @param @param dto
	    * @param @return    参数  
	    * @return List<RepairAnalysisDto>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairAnalysisDto> getRepairBxtslList(
			RepairAnalysisDto dto) {
		return dao.getRepairBxtslList(dto);
	}

		  
		    /**  
		    * @Title: getRepairAVGTimeList  
		    * (报修平均时长)
		    * @param @param dto
		    * @param @return    参数  
		    * @return List<RepairAnalysisDto>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairAnalysisDto> getRepairAVGTimeList(
				RepairAnalysisDto dto) {
			return dao.getRepairAVGTimeList(dto);
		}

			  
			    /**  
			    * @Title: getRepairyzAvgList  
			    * (报修业主区域平均报修条数)
			    * @param @param dto
			    * @param @return    参数  
			    * @return List<RepairAnalysisDto>    返回类型  
			    * @throws  
			    */  
			    
			public List<RepairAnalysisDto> getRepairyzAvgList(
					RepairAnalysisDto dto) {
				return dao.getRepairyzAvgList(dto);
			}
			/**  
			 * @Title: getRepairyzAvgList  
			 * (全部业主区域平均报修条数)
			 * @param @param dto
			 * @param @return    参数  
			 * @return List<RepairAnalysisDto>    返回类型  
			 * @throws  
			 */  
			public List<RepairAnalysisDto> getRepairAllqyAvgList(
					RepairAnalysisDto dto) {
				return dao.getRepairAllqyAvgList(dto);
			}

			  
			    /**  
			    * @Title: getBtomtime  
			    * （投诉-报修时间间隔）
			    * @param @param repair
			    * @param @return    参数  
			    * @return List<RepairInformation>    返回类型  
			    * @throws  
			    */  
			    
			public List<RepairInformation> getBtomtime(
					RepairInformation repair) {
				return dao.getBtomtime(repair);
			}

				  
		    /**  
		    * @Title: getBuildRoomAnalsis  
		    * (投诉建筑体数，根据叶子节点)
		    * @param @param dto
		    * @param @return    参数  
		    * @return List<RepairAnalysisDto>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairAnalysisDto> getBuildRoomAnalsis(
				RepairAnalysisDto dto) {
			return dao.getBuildRoomAnalsis(dto);
		}

			  
	    /**  
	    * @Title: getRepairAVGComplainTimeList  
	    * (投诉处理平均时长)
	    * @param @param dto
	    * @param @return    参数  
	    * @return List<RepairAnalysisDto>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairAnalysisDto> getRepairAVGComplainTimeList(
			RepairAnalysisDto dto) {
		return dao.getRepairAVGComplainTimeList(dto);
	}

		  
		    /**  
		    * @Title: findComplainList  
		    * (查询投诉事件)
		    * @param @param repair
		    * @param @return    参数  
		    * @return List<RepairInformation>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairInformation> findComplainList(
				RepairInformation repair) {
			return dao.findComplainList(repair);
		}

			public RepairAnalysisDto getRepairBxyzs(
					RepairAnalysisDto repairAnalysisDto) {
				return dao.getRepairBxyzs(repairAnalysisDto);
			}

	public List<RepairInformation> getRepairBxgdCount(RepairInformation repair) {
			return dao.getRepairBxgdCount(repair);
	}

	public List<RepairInformation> getRepairBxyzCount(RepairInformation repair) {
			return dao.getRepairBxyzCount(repair);
	}

	public List<RepairInformation> getRepairBxlCount(RepairInformation repair) {
		return dao.getRepairBxlCount(repair);
	}

	public int getSumRooms() {
			return dao.getSumRooms();
	}
}
