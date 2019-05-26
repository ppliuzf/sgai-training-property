package com.sgai.property.wy.web;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.SysFile;
import com.sgai.property.wy.service.SysFileService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/sysFile")
public class SysFileController extends BaseController {

    @Autowired
    private SysFileService sysFileService;

    @RequestMapping("/queryByCondition")
    public CommonResponse queryByCondition(@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, SysFile sysFile) throws JsonProcessingException {
        if (sysFile.getEndTime() != null) {
            Date time = sysFile.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
            sysFile.setEndTime(c.getTime());
        }
        Page<SysFile> page = sysFileService.findPage(new Page<SysFile>(pageNo, pageSize), sysFile);
        return ResponseUtil.successResponse(page);
    }

    @RequestMapping(value = "/uploadFile", produces = "text/html;charset=utf-8")
    public String uploadFile(HttpServletRequest request, @RequestHeader String token) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
        String uploadPeople = user.getUserName();
        return sysFileService.uploadFile(request, multipartFile, uploadPeople);
    }
    @RequestMapping("/delete")
    @Transactional
    public CommonResponse delete(String ids) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsArray = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            SysFile sysFile = sysFileService.get(idsArray[i]);
            File file=new File(sysFile.getRealPath());
            if(file.exists()){
                file.delete();
            }
            sysFileService.delete(sysFile);
        }
        map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }
    @RequestMapping("/downLoads")
    @PermessionLimit(limit = false)
    public void downLoad(HttpServletResponse response, String id){
        sysFileService.downLoad(response,id);
    }
}
