package com.sgai.property.meeting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgai.common.persistence.Page;
import com.sgai.property.meeting.dao.IRoomResourceDao;
import com.sgai.property.meeting.dao.IRoomTypeDao;
import com.sgai.property.meeting.entity.RoomResource;
import com.sgai.property.meeting.entity.RoomType;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.vo.RoomTypeVo;

@Service
public class RoomTypeServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomTypeDao, RoomType> {
	@Autowired
	private IRoomResourceDao iRoomResourceDao;
	
	@Autowired
	private IRoomTypeDao roomTypeDao;

	public Page<RoomTypeVo> getRoomTypeList(RoomType roomType, int pageNo, int pageSize) {
		Page<RoomType> roomTyPepage = new Page<>();
		roomTyPepage.setOrderBy(Constants.SqlString.CreateTimeDesc);
		roomTyPepage.setPageNo(pageNo);
		roomTyPepage.setPageSize(pageSize);
		roomType.setIsDelete(Constants.IsDelete.NO);
		roomTyPepage = this.findPage(roomTyPepage, roomType);
		List<RoomTypeVo> voList = new ArrayList<>();
		if (roomTyPepage != null && roomTyPepage.getList() != null && !roomTyPepage.getList().isEmpty()) {
			//voList = BeanMapper.mapList(roomTyPepage.getList(), RoomTypeVo.class);
			for (RoomType roomTypes : roomTyPepage.getList()) {
				RoomTypeVo roomTypeVo = new RoomTypeVo();
				roomTypeVo.setId(roomTypes.getId());
				roomTypeVo.setRtName(roomTypes.getRtName());
				roomTypeVo.setRtType(roomTypes.getRtType());
				roomTypeVo.setRtTypeDesc(roomTypes.getRtTypeDesc());
				roomTypeVo.setRtTypeName(roomTypes.getRtTypeName());
				// 验证是否可删除
				List<RoomResource> roomResources = iRoomResourceDao.findRtAll(roomTypes.getId());
				if (null != roomResources && roomResources.size() > 0 && !roomResources.isEmpty()) {
					roomTypeVo.setMayDelete(Constants.IsDelete.YES);
				}else {
					roomTypeVo.setMayDelete(Constants.IsDelete.NO);
				}
				voList.add(roomTypeVo);
			}
		}
		Page<RoomTypeVo> page = new Page<>(pageNo, pageSize,roomTyPepage.getCount());
		BeanUtils.copyProperties(roomTyPepage,page);
		page.setList(voList);
		return page;
	}
	
	@Override
	public boolean updateById(RoomType entity) {
		//  Auto-generated method stub
		return roomTypeDao.updateById(entity) > 0;
	}

	/**
	 * 根据类型名称查询是否有重复 实现去重功能
	 * @param rtName
	 * @return
	 */
    public List<RoomType> getByName(String rtName) {
		return roomTypeDao.getByName(rtName);
    }

	public List<RoomType> getByNameAndId(String rtName, String id) {
		return roomTypeDao.getByNameAndId(rtName,id);
	}
}