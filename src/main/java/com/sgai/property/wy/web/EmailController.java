package com.sgai.property.wy.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.DataAuthority;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.wy.entity.Email;
import com.sgai.property.wy.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ppliu on 2018/1/19.
 */
@RestController
@RequestMapping("/admin/email")

public class EmailController extends BaseController {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    /**
     * @param pageNo
     * @param pageSize
     * @param email
     * @return
     * @throws JsonProcessingException
     */
    @DataAuthority(tableAlias = "w")
    @RequestMapping("/queryByCondition")
    public CommonResponse queryByCondition(@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize, Email email) throws JsonProcessingException {
        if(email.getEndTime()!=null){
            Date time=email.getEndTime();
            Calendar c= Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            email.setEndTime(c.getTime());
        }
        Page<Email> page = emailServiceImpl.findPage(new Page<Email>(pageNo, pageSize), email);
        return ResponseUtil.successResponse(page);
    }

    /**
     * @param ids
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delete")
    @Transactional
    public CommonResponse delete(String ids) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idsArray = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            Email email = new Email();
            email.setId(idsArray[i]);
            emailServiceImpl.delete(email);
        }
        map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }

    /**
     * @param email
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResponse save(Email email, LoginUser user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
            UserServletContext.setUserInfo(user);
            email.setCreatedBy(user.getUserId());
            email.setComCode(user.getComCode());
            email.setDeptCode(user.getDeptCode());
            emailServiceImpl.save(email);
            map.put("msg", "success");
        return ResponseUtil.successResponse(map);
    }

    /**
     * @param id
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public CommonResponse findById(
            String id
    ) throws IOException, ServletException {
        Email email = emailServiceImpl.get(id);
        return ResponseUtil.successResponse(email);
    }

    @DataAuthority(tableAlias = "w")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @PermessionLimit(limit = false)
    public void export(HttpServletResponse response, Email email) throws IOException {
        emailServiceImpl.export(response, email);
    }

}
