package com.sgai.property.meeting.service;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.IRoomDeviceDao;
import com.sgai.property.meeting.entity.RoomDevice;
import com.sgai.property.meeting.vo.RoomDeviceDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会议室设备service
 * @author hou
 * @date 2018年1月8日13:41:07
 */

@Service
public class RoomDeviceServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomDeviceDao,RoomDevice>{
	@Autowired
	private IRoomDeviceDao roomDeviceDao;

	@Transactional(rollbackFor = BusinessException.class)
	public List<RoomDeviceDto> saveRoomDevice(String roomDeviceDetails){
		List<RoomDeviceDto> result = new ArrayList<>();
		String[] list = roomDeviceDetails.split("@@");
		StringBuilder sb = new StringBuilder();
		for(String str:list){
			//判断是否重复
			if(str.length() > Constants.room.nameLenght){
				throw new BusinessException(ReturnType.ParamIllegal,"设备名称不能超过20个字");
			}
			RoomDevice roomDevice1 = new RoomDevice();
			roomDevice1.setRdRoomDevice(str);
			roomDevice1.setIsDelete(0);
			roomDevice1 = roomDeviceDao.get(roomDevice1);
			if(null != roomDevice1 && null != roomDevice1.getId()){
				sb.append("\""+str+"\",");
				continue;
			}
			RoomDevice roomDevice = new RoomDevice();
			roomDevice.setRdRoomDevice(str);
			roomDevice.setRrState(1);
			roomDevice.setIsDelete(Constants.IsDelete.NO);
			roomDevice.setCreateTime(System.currentTimeMillis());
			roomDevice.setUpdateTime(System.currentTimeMillis());
			roomDevice.preGet();
			this.save(roomDevice);

			RoomDeviceDto rdd = new RoomDeviceDto();
			rdd.setRdId(roomDevice.getId());
			rdd.setRdRoomDevice(roomDevice.getRdRoomDevice());
			result.add(rdd);
		}
		if(StringUtils.isNotEmpty(sb)){
			String str = sb.substring(0,sb.length()-1);
			throw new BusinessException(ReturnType.DB,str+"设备已存在，请勿重复添加");
		}
		return result;
	}



	public Boolean deleteRoomDeviceById(String rdId) {
		RoomDevice roomDeviceInfo = roomDeviceDao.getById(rdId);
		if(null == roomDeviceInfo){
			throw new BusinessException(ReturnType.ParamIllegal,"Id传入有误");
		}else if(0 == roomDeviceInfo.getRrState()){
			throw new BusinessException(ReturnType.Error,"无法删除初始设备");
		}
		RoomDevice roomDevice = new RoomDevice();
		roomDevice.setUpdateTime(System.currentTimeMillis());
		roomDevice.setIsDelete(1);
		roomDevice.setId(rdId);
		return roomDeviceDao.updateById(roomDevice) > 0;
	}
}