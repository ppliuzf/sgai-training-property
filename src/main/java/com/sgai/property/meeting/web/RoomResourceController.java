package com.sgai.property.meeting.web;

import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.IRoomResourceDao;
import com.sgai.property.meeting.entity.RoomDevice;
import com.sgai.property.meeting.entity.RoomResource;
import com.sgai.property.meeting.entity.RoomType;
import com.sgai.property.meeting.service.RoomDeviceServiceImpl;
import com.sgai.property.meeting.service.RoomPositionServiceImpl;
import com.sgai.property.meeting.service.RoomResourceServiceImpl;
import com.sgai.property.meeting.service.RoomTypeServiceImpl;
import com.sgai.property.meeting.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会议室管理
 * @author 146584
 * @date 2017年11月2日15:10:42
 */

@RestController
@RequestMapping("/roomResource")
@Api(description = "会议室管理")
public class RoomResourceController  extends BaseController {

    @Autowired
    private RoomResourceServiceImpl roomResourceService;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;
    @Autowired
    private RoomPositionServiceImpl roomPositionService;
    @Autowired
    private RoomDeviceServiceImpl roomDeviceService;
    @Autowired
    private IRoomResourceDao roomResourceDao;


    /**
     * 日志对象
     */
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    
//    @RequestMapping(value = "/roomResourcePageList", method = RequestMethod.POST)
//    @ApiOperation(value = "查询会议室列表", httpMethod = "POST", notes = "分页查询会议室列表")
//    public Response<Page<RoomResourceVo>> roomResourcePageList(
//                                                               int pageNo, int pageSize) {
//
//        if(null == toonCode){
//            throw new BusinessException(ReturnType.TokenError,"请检查token!");
//        }
//        Page<RoomResourceVo> page = roomResourceService.findAllPage(UserServletContext.getUserInfo().getCompanyId(),pageNo,pageSize);
//        Response<Page<RoomResourceVo>> result = new Response<>();
//        result.setData(page);
//        return result;
//    }
    
 /*   @ApiOperation(value = "编辑会议室", httpMethod = "POST", notes = "新建会议室")
    @RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
    public Response<Boolean> saveRoom( @RequestBody RoomResourceDto roomResourceDto) {
        Response<Boolean> result = new Response<>();

        if(null == toonCode){
            throw new BusinessException(ReturnType.TokenError,"请检查token!");
        }
        result.setData(roomResourceService.saveRoom(toonCode,roomResourceDto));
        return result;
    }*/

    @ApiOperation(value = "新建会议室/编辑会议室", httpMethod = "POST", notes = "新建会议室")
    @RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
    public Response<Boolean> saveRoomAdd( @RequestBody RoomResourceDto roomResourceDto) {

        Response<Boolean> result = new Response<>();


        if(!"".equals(roomResourceDto.getRrId()) && null != roomResourceDto.getRrId()) {
            result.setData(roomResourceService.saveRoom(roomResourceDto));

        }else {
            RoomResource bdNemes = roomResourceDao.getByName(roomResourceDto.getRrRoomName());
            if(bdNemes !=null){
                result.setData(false);
                result.setCode("0");
                result.setMessage("会议室名称已存在！请勿重复添加！");
                throw new BusinessException(ReturnType.ParamIllegal," \""+roomResourceDto.getRrRoomName()+"\"已存在，请勿重复添加");

            }else {
                result.setData(roomResourceService.saveRoomAdd(roomResourceDto));

            }


        }

        return result;

    }

    /*@ApiOperation(value = "根据Id获取会议室信息", httpMethod = "POST", notes = "根据Id获取会议室信息")
    @RequestMapping(value = "/getRoomListById", method = RequestMethod.POST)
    public Response<RoomResourceVo> getRoomListById(String id) {
        Response<RoomResourceVo> result = new Response<>();

        if(null == toonCode){
            throw new BusinessException(ReturnType.TokenError,"请检查token!");
        }
        RoomResourceVo roomResourceVo =roomResourceService.getRoomDetailById(id);
       // RoomResourceVo roomResourceVo = new RoomResourceVo();
      //  BeanUtils.copyProperties(roomResource,roomResourceVo);
      //  result.setData(roomResourceService.updateRoomResourceById(roomResourceModifyDto));
         result.setData(roomResourceVo);
        return result;
    }*/


    
    @ApiOperation(value = "根据Id更改会议室状态信息:删除(isDelete: 1 是 0 否),启禁用(rrRoomState:1 启用 0 禁用)", httpMethod = "POST", notes = "根据Id更改会议室状态信息:删除(isDelete: 1 是 0 否),启禁用(rrRoomState:1 启用 0 禁用)")
    @RequestMapping(value = "/modifyRoomStatusById", method = RequestMethod.POST)
    public Response<Boolean> updateById( @RequestBody RoomResourceModifyDto roomResourceModifyDto) {
        Response<Boolean> result = new Response<>();

        result.setData(roomResourceService.updateRoomResourceById(roomResourceModifyDto));
        return result;
    }


    @ApiOperation(value = "会议室列表,搜索会议室", httpMethod = "POST", notes = "搜索会议室,rrRoomState 会议室状态 2 全部 1 启用 0 禁用")
    @RequestMapping(value = "/searchRoom", method = RequestMethod.POST)
    public Response<Page<RoomResourceVo>> searchRoom(
                                                     @RequestParam(required=false,name="keyWord") String keyWord,
                                                     @RequestParam(required=false,name="rrRoomState") Integer rrRoomState, int pageNo, int pageSize) {
        Response<Page<RoomResourceVo>> result = new Response<>();

        result.setData(roomResourceService.searchRoomResourcePage(UserServletContext.getUserInfo().getComCode(), keyWord, rrRoomState, pageNo, pageSize));
        return result;
    }

    @RequestMapping(value="/getRoomTypeDetail", method = RequestMethod.POST)
    @ApiOperation(value = "查询会议室类型详细信息", httpMethod = "POST", notes = "查询会议室类型详细信息")
    public Response<RoomType> getRoomTypeDetail(String id) {
        RoomType roomType = new RoomType();
        roomType = roomTypeService.getById(id);
        Response<RoomType> result = new Response<>();
        result.setData(roomType);
        return result;
    }
    
    @RequestMapping(value="/getRoomTypeList", method = RequestMethod.POST)
    @ApiOperation(value = "查询会议室类型列表", httpMethod = "POST", notes = "查询会议室类型列表")
    public Response<Page<RoomTypeVo>> getRoomTypeList(
    		@RequestParam(required=true,name="pageNo",defaultValue="1") int pageNo,
    		@RequestParam(required=true,name="pageSize",defaultValue="10") int pageSize) {

        RoomType roomType = new RoomType();
        roomType.setIsDelete(0);
        roomType.setComCode(UserServletContext.getUserInfo().getComCode());
        roomType.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Response<Page<RoomTypeVo>> result = new Response<>();
        result.setData(roomTypeService.getRoomTypeList(roomType, pageNo, pageSize));
        return result;
    }

    @RequestMapping(value="/saveRoomType", method = RequestMethod.POST)
    @ApiOperation(value = "保存会议室类型", httpMethod = "POST", notes = "保存会议室类型")
    public Response<Boolean> saveRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        Response<Boolean> result = new Response<>();
        //根据类型名称查询是否有重复 实现去重功能
        List<RoomType>  roomName = roomTypeService.getByName(roomTypeVo.getRtName());
        if(roomName.size()>0){
            result.setCode("0");
            result.setMessage("该类型已存在，不可重复创建！");
            result.setData(false);
            return result;
        }
        RoomType roomType = new RoomType();
        roomType = BeanMapper.map(roomTypeVo, RoomType.class);
        roomType.setCreateTime(System.currentTimeMillis());//创建时间
        roomType.setUpdateTime(System.currentTimeMillis());//更新时间
        roomType.setCreatedBy(UserServletContext.getUserInfo().getUserNo());//创建人
        roomType.setUpdatedBy(UserServletContext.getUserInfo().getUserNo());//更新人
        roomType.setComId(UserServletContext.getUserInfo().getComCode());//公司id
        roomType.setComCode(UserServletContext.getUserInfo().getComCode()); //机构代码
        roomType.setModuCode(UserServletContext.getUserInfo().getModuCode());//模块代码
        roomTypeService.save(roomType);
        result.setData(true);
        return result;
    }
    
    @RequestMapping(value="/updateRoomType", method = RequestMethod.POST)
    @ApiOperation(value = "更新会议室类型", httpMethod = "POST", notes = "更新会议室类型")
    public Response<Boolean> updateRoomType(@RequestBody RoomTypeDto roomTypeDto) {
        Response<Boolean> result = new Response<>();

        //根据类型名称查询是否有重复 实现去重功能
        List<RoomType>  roomName = roomTypeService.getByNameAndId(roomTypeDto.getRtName(),roomTypeDto.getId());
        if(roomName.size()>0){
        RoomType roomType = new RoomType();
        roomType = BeanMapper.map(roomTypeDto, RoomType.class);
        roomType.setUpdateTime(System.currentTimeMillis());
        roomType.setUpdatedBy(UserServletContext.getUserInfo().getUserNo());
        result.setData(roomTypeService.updateById(roomType));

        return result;
        }else{
         List<RoomType>  roomNames = roomTypeService.getByName(roomTypeDto.getRtName());
            if(roomNames.size()==0){
                RoomType roomType = new RoomType();
                roomType = BeanMapper.map(roomTypeDto, RoomType.class);
                roomType.setUpdateTime(System.currentTimeMillis());
                roomType.setUpdatedBy(UserServletContext.getUserInfo().getUserNo());
                result.setData(roomTypeService.updateById(roomType));
                return result;
            }else {
                result.setCode("0");
                result.setMessage("该类型已存在，不可重复创建！");
                result.setData(false);
            }
        }
        return result;
    }
    
    @RequestMapping(value="/deleteRoomType", method = RequestMethod.POST)
    @ApiOperation(value = "删除会议室类型", httpMethod = "POST", notes = "删除会议室类型")
    public Response<Boolean> deleteRoomType(String rtId) {
        Response<Boolean> result = new Response<>();

        RoomType roomType = new RoomType();
        roomType.setId(rtId);
        roomType.setUpdateTime(System.currentTimeMillis());
        roomType.setUpdatedBy(UserServletContext.getUserInfo().getUserNo());
        roomType.setIsDelete(Constants.TRUE);
        result.setData(roomTypeService.updateById(roomType));
        return result;
    }

    
    
    @RequestMapping(value = "/roomPositionPageList", method = RequestMethod.POST)
    @ApiOperation(value = "分页-查询会议室位置列表", httpMethod = "POST", notes = "分页查询-会议室位置列表")
    public Response<Page<RoomPositionVo>> roomPositionPageList( int pageNo, int pageSize) {
        Response<Page<RoomPositionVo>> result = new Response<>();

        result.setData(roomResourceService.findRoomPositionPage(UserServletContext.getUserInfo().getComCode(),pageNo, pageSize));
        return result;
    }


    @RequestMapping(value = "/getRoomPositionList", method = RequestMethod.POST)
    @ApiOperation(value = "获取会议室位置List", httpMethod = "POST", notes = "获取会议室位置list")
    public Response<List<RoomPositionVo>> getRoomPositionList() {
        Response<List<RoomPositionVo>> result = new Response<>();

        result.setData(roomResourceService.getRoomPositionList(UserServletContext.getUserInfo().getComCode()));
        return result;
    }

    
    
    @ApiOperation(value = "保存会议室位置对象", httpMethod = "POST", notes = "保存会议室位置对象")
    @RequestMapping(value = "/saveRoomPosition", method = RequestMethod.POST)
    public Response<Boolean> saveRoomPosition( @RequestBody RoomPositionDto roomPositionDto) {
        Response<Boolean> result = new Response<>();

        result.setData(roomPositionService.saveRoomPosition(roomPositionDto));
        return result;
    }

    
    
    @ApiOperation(value = "通过id删除会议室位置", httpMethod = "POST", notes = "通过rpId,删除会议室位置")
    @RequestMapping(value = "/deleteRoomPositionById", method = RequestMethod.POST)
    public Response<Boolean> deleteRoomPositionById(@RequestParam("rpId") String rpId) {
        Response<Boolean> result = new Response<>();
        result.setData(roomPositionService.deleteRoomPositionById(rpId));
        return result;
    }


    
    
    @RequestMapping(value = "/roomDeviceList", method = RequestMethod.POST)
    @ApiOperation(value = "查询会议室设备列表", httpMethod = "POST", notes = "查询会议室设备列表")
    public Response<List<RoomDevice>> roomDeviceList() {
        RoomDevice roomDevice = new RoomDevice();

        roomDevice.setIsDelete(0);
        roomDevice.setComCode(UserServletContext.getUserInfo().getComCode());
        List<RoomDevice> roomDeviceList = roomDeviceService.findList(roomDevice);
        Response<List<RoomDevice>> result = new Response<>();
        result.setData(roomDeviceList);
        return result;
    }

    
    
    @ApiOperation(value = "保存会议室设备", httpMethod = "POST", notes = "保存会议室设备")
    @RequestMapping(value = "/saveRoomDevice", method = RequestMethod.POST)
    public Response<List<RoomDeviceDto>> saveRoomDevice(@RequestParam("roomDeviceDetails")String roomDeviceDetails) {
        Response<List<RoomDeviceDto>> result = new Response<>();

        List<RoomDeviceDto> list = roomDeviceService.saveRoomDevice(roomDeviceDetails);
        result.setData(list);
        return result;
    }

    
    
    @ApiOperation(value = "通过Id删除会议室设备", httpMethod = "POST", notes = "通过Id删除会议室设备")
    @RequestMapping(value = "/deleteRoomDeviceById", method = RequestMethod.POST)
    public Response<Boolean> deleteRoomDeviceById(@RequestParam("rdId")String rdId) {
        Response<Boolean> result = new Response<>();

        result.setData(roomDeviceService.deleteRoomDeviceById(rdId));
        return result;
    }

    @ApiOperation(value = "获取我预约的会议室列表页/会议预约列表", httpMethod = "POST", notes = "获取我预约的会议室列表页/会议预约列表")
    @RequestMapping(value = "/getMyReserve", method = RequestMethod.POST)
    public Response<Page<MyReserveRoomVo>> getMyReserve(
                                                        @RequestBody MyReserveRoomDto myReserveRoomDto, int pageNo, int pageSize,String falg) {
        Response<Page<MyReserveRoomVo>> result = new Response<>();

        if(!"".equals(falg) && null != falg){

            result.setData(roomResourceService.getReserve(myReserveRoomDto, pageNo,  pageSize,falg));

        }else {
            result.setData(roomResourceService.getMyReserve(myReserveRoomDto, pageNo,  pageSize,falg));

        }

        return result;
    }


    @ApiOperation(value = "获取预定会议信息列表", httpMethod = "POST", notes = "获取预定会议信息列表")
    @RequestMapping(value = "/getReserveDetail", method = RequestMethod.POST)
    public Response<SchMessageVo> getReserveDetail(
                                                   @RequestBody MyReserveRoomDto myReserveRoomDto, String falg) {
        Response<SchMessageVo> result = new Response<>();

        myReserveRoomDto.setEndTime(myReserveRoomDto.getStartTime()+86400000);
        if(!"".equals(falg) && null != falg){
            result.setData(roomResourceService.getReserveDetail(myReserveRoomDto,falg));

        }else {
            result.setData(roomResourceService.getReserveDetail(myReserveRoomDto, falg));

        }

        return result;
    }

/*    @ApiOperation(value = "/会议室预约列表", httpMethod = "POST", notes = "获取我预约的会议室列表页/会议预约列表")
    @RequestMapping(value = "/getMyReserve", method = RequestMethod.POST)
    public Response<Page<MyReserveRoomVo>> getReserve(
                                                        @RequestBody MyReserveRoomDto myReserveRoomDto, int pageNo, int pageSize,String falg) {
        Response<Page<MyReserveRoomVo>> result = new Response<>();


        result.setData(roomResourceService.getReserve(toonCode,myReserveRoomDto, pageNo,  pageSize,falg));
        return result;
    }*/

    @RequestMapping(value="/getRoomDetailById", method = RequestMethod.POST)
    @ApiOperation(value = "获取会议室详情", httpMethod = "POST", notes = "获取会议室详情")
    public Response<RoomResourceVo> getRoomDetailById(@RequestParam("id") String id) {
        Response<RoomResourceVo> result = new Response<>();

        RoomResourceVo roomResourceVo = roomResourceService.getRoomDetailById(id);
        roomResourceVo.setLoginUserName(UserServletContext.getUserInfo().getUserName());
        result.setData(roomResourceVo);
        return result;
    }


    @ApiOperation(value = "获取物料列表", httpMethod = "POST", notes = "获取物料列表")
    @RequestMapping(value = "/getMaterielList", method = RequestMethod.POST)
    public Response<List<MaterielVo>> getMaterielList() {
        Response<List<MaterielVo>> result = new Response<>();

        result.setData(roomResourceService.getMaterielList());
        return result;
    }

    @ApiOperation(value = "根据物料品类,获取物料列表 - app端专用", httpMethod = "POST", notes = "根据物料品类,获取物料列表")
    @RequestMapping(value = "/getMaterielInfoByType", method = RequestMethod.POST)
    public Response<MaterielInfoVo> getMaterielInfoByType(
                                                        @RequestBody  MaterielInfoDto materielInfoDto) {
        Response<MaterielInfoVo> result = new Response<>();

        result.setData(roomResourceService.getMaterielInfoByType(materielInfoDto));
        return result;
    }

    @ApiOperation(value = "修改会议室设备状态 - 0启用,1禁用", httpMethod = "POST", notes = "修改会议室设备状态 - 0启用,1禁用")
    @RequestMapping(value = "/updateDeviceStatus", method = RequestMethod.POST)
    public Response<Boolean> updateDeviceStatus(
                                                          @RequestParam("rdsId")String rdsId,@RequestParam("status")Long status) {
        Response<Boolean> result = new Response<>();

        result.setData(roomResourceService.updateRoomDeviceStatus(rdsId,status));
        return result;
    }
}