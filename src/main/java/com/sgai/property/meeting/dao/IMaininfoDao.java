package com.sgai.property.meeting.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.Maininfo;
import com.sgai.property.meeting.entity.RoomResource;
import com.sgai.property.meeting.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IMaininfoDao extends MoreDataSourceDao<Maininfo> {

	List<MeetingsListDto> getAllMeetingsList(@Param("currtDate") Long currtDate, @Param("createEiId") String createEiId);
	
	List<MeetingsListDto> getAllMeetingsListByRrId(@Param("currtDate") Long currtDate, @Param("rrId") String rrId);

	List<CurrtDaysStatusDto> getCurrtWeekStatus(@Param("currtDays") List<Long> currtDays, @Param("createEiId") String createEiId);

	List<TimeSegStatusDto> getTimeSegStatus(@Param("currtDate") Long currtDate, @Param("rrId") String rrId, @Param("createEiId") String createEiId);

	List<Maininfo> confirmTimeSeg(@Param("currtDate") Long currtDate, @Param("timeSeg") String timeSeg, @Param("rrId") String rrId);

	List<Maininfo> findMeetingsByCurrtDate(@Param("currtDate") Long currtDate);

	List<Maininfo> findMeetingsByCurrtDateNoStart(@Param("currtDate") Long currtDate);

	List<RoomResource> isExistsRoom(@Param("currtDate") Long currtDate, @Param("timeSeg") String timeSeg);

	List<MeetingRoomListDto> selectMeetingRoom(@Param("rpId") String rpId, @Param("currtDate") Long currtDate, @Param("timeSeg") String timeSeg);
	
	List<RoomResource> selectMeetingRoomPC(Maininfo mf);

	Long getCount2(Maininfo mf);

	/**
	 * 获取我发起的列表
	 * @param myReserveRoomDto
	 * @return
	 */
	List<MyReserveRoomVo> getMyReserve(MyReserveRoomDto myReserveRoomDto);

	/**
	 * 获取预定会议信息列表
	 * @param map
	 * @return
	 */
	List<MyReserveRoomVo> getReserveDetail(Map<String, Object> map);

	/**
	 * 获取全部列表
	 * @param myReserveRoomDto
	 * @return
	 */
	List<MyReserveRoomVo> getReserve(MyReserveRoomDto myReserveRoomDto);
}