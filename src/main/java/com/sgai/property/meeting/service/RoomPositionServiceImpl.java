package com.sgai.property.meeting.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.IRoomPositionDao;
import com.sgai.property.meeting.entity.RoomPosition;
import com.sgai.property.meeting.vo.RoomPositionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议室位置service实现类
 *
 * @author hou
 * @date 2017年12月28日15:06:26
 */
@Service
public class RoomPositionServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomPositionDao,RoomPosition>{
	@Autowired
	private IRoomPositionDao roomPositionDao;


	/**
	 * 删除会议室位置
	 * @param rpId
	 * @return
	 */
	public Boolean deleteRoomPositionById( String rpId) {
		RoomPosition roomPosition = roomPositionDao.getById(rpId);
		if(null == roomPosition){
			throw new BusinessException(ReturnType.ParamIllegal,"id输入有误");
		}
		RoomPosition roomPosition1 = new RoomPosition();
		roomPosition1.setUpdateTime(System.currentTimeMillis());
		roomPosition1.setIsDelete(1);
		roomPosition1.setId(rpId);
		return roomPositionDao.updateById(roomPosition1) > 0;
	}

	/**
	 * 保存会议室位置
	 * @param roomPositionDto
	 * @return
	 */
	public Boolean saveRoomPosition(RoomPositionDto roomPositionDto) {
		RoomPosition roomPosition = new RoomPosition();

		if(roomPositionDto.getRpPositionName().length() > Constants.room.nameLenght){
			throw new BusinessException(ReturnType.ParamIllegal,"位置名称不得超过20字");
		}
		if(roomPositionDto.getRpPositionDesc().length() > Constants.room.descLenght){
			throw new BusinessException(ReturnType.ParamIllegal,"位置备注不得超过200字");
		}
		roomPosition.setIsDelete(0);
		roomPosition.setRpPositionName(roomPositionDto.getRpPositionName());
		roomPosition.setComId(UserServletContext.getUserInfo().getComCode());
		List<RoomPosition> list = this.findList(roomPosition);
		if(null != list && !list.isEmpty()){
			throw new BusinessException(ReturnType.ParamIllegal,"\""+roomPositionDto.getRpPositionName()+"\"位置名称已存在，请勿重复添加");
		}
		BeanUtils.copyProperties(roomPositionDto,roomPosition);
		roomPosition.setCreateTime(System.currentTimeMillis());
		roomPosition.setUpdateTime(System.currentTimeMillis());
		roomPosition.setId(null);
		this.save(roomPosition);
		return true;
	}
}