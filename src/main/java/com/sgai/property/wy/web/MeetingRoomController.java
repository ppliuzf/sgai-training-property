package com.sgai.property.wy.web;

import com.alibaba.fastjson.JSONObject;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmSpaceTreeService;
import com.sgai.property.wy.entity.MeetingRoom;
import com.sgai.property.wy.service.MeetingRoomService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/meetingRoom")

public class MeetingRoomController extends BaseController {
    private static final String PARENT_CODE = "201801241622310335";
    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    private MdmSpaceTreeService mdmSpaceTreeService;

    /**
     * 通过腹肌编码查询出所有的楼体
     *
     * @return
     */
    @RequestMapping("/getByParentCode")
    public List<MdmSpaceTree> getByParentCode() {
        MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
        mdmSpaceTree.setParentCode(PARENT_CODE);
        return mdmSpaceTreeService.findList(mdmSpaceTree);
    }

    /**
     * 按条件查询
     *
     * @param meetingRoom
     * @return
     */
    @RequestMapping("/queryByCondition")
    public List<MeetingRoom> queryByCondition(MeetingRoom meetingRoom) {
        return meetingRoomService.findList(meetingRoom);
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
            MeetingRoom meetingRoom = new MeetingRoom();
            meetingRoom.setId(idsArray[i]);
            meetingRoomService.delete(meetingRoom);
        }
        map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MeetingRoom save(MeetingRoom meetingRoom, @RequestHeader String token) throws Exception {
        Claims claims = JwtUtil.parseJWT(token);
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
        UserServletContext.setUserInfo(user);
        meetingRoomService.save(meetingRoom);
        return meetingRoom;
    }

    /**
     * @param id
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public CommonResponse findById(String id) throws IOException, ServletException {
        MeetingRoom MeetingRoom = meetingRoomService.get(id);
        return ResponseUtil.successResponse(MeetingRoom);
    }
}
