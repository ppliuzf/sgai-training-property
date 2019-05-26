
package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.EventCommon;
import com.sgai.property.wy.entity.RepairAnalysisDto;
import com.sgai.property.wy.entity.RepairInformation;
import com.sgai.property.wy.entity.RepairRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: TodoEventDao
 * (这里用一句话描述这个类的作用)
 * @author cui
 * @date 2018年2月6日
 * @Company 首自信--智慧城市创新中心
 */

@Mapper
public interface TodoEventDao extends CrudDao<EventCommon> {

	List<EventCommon> findTodayList(EventCommon event);

	int findCallList(EventCommon event);

	int findLogList(EventCommon event);

	List<EventCommon> findNoticeList(EventCommon event);

	List<EventCommon> findPuNoticeList(EventCommon event);

	List<EventCommon> getSpaceList();

	List<RepairInformation> getRepairList(RepairInformation repair);

	int getSumRoom();

	int getSumBuildRoom(@Param("nodeCode") String nodeCode);
	    
	List<RepairRecord> findRecordList(@Param("id") String id);
	    
	List<RepairAnalysisDto> getRepairBxgdList(RepairAnalysisDto repair);

	List<RepairAnalysisDto> getRepairBxyzList(RepairAnalysisDto dto);

	List<RepairAnalysisDto> getRepairBxlList(RepairAnalysisDto dto);

	List<RepairAnalysisDto> getRepairBxwclList(RepairAnalysisDto dto);

	List<RepairAnalysisDto> getRepairBxtslList(RepairAnalysisDto dto);
	    
	List<RepairAnalysisDto> getRepairAVGTimeList(RepairAnalysisDto dto);
	    
	List<RepairAnalysisDto> getRepairyzAvgList(RepairAnalysisDto dto);
	
	List<RepairAnalysisDto> getRepairAllqyAvgList(RepairAnalysisDto dto);

	List<RepairInformation> getBtomtime(RepairInformation repair);
	    
	List<RepairAnalysisDto> getBuildRoomAnalsis(RepairAnalysisDto dto);
	    
	List<RepairAnalysisDto> getRepairAVGComplainTimeList(RepairAnalysisDto dto);
	    
	List<RepairInformation> findComplainList(RepairInformation repair);
	    
	RepairAnalysisDto getRepairBxyzs(RepairAnalysisDto repairAnalysisDto);

	List<RepairInformation> getRepairBxyzCount(RepairInformation repair);

	List<RepairInformation> getRepairBxgdCount(RepairInformation repair);

	List<RepairInformation> getRepairBxlCount(RepairInformation repair);

	int getSumRooms();
}
