package com.sgai.property.customer.service;

import com.sgai.modules.login.consts.SysConstant;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.JwtUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 处理token的Service
 *
 * @author hou
 * @date 2017-12-22 10:07
 */
@Service
public class TokenServerImpl {
    private static final Logger logger = LogManager.getLogger(TokenServerImpl.class);

    /**
     * 生成首自信方需要的token
     *
     * @param loginUser 需要加密的对象
     * @return
     */
    public String getToken(LoginUser loginUser) {

        String result = null;
        try {
            result = JwtUtil.userToJwt(loginUser, SysConstant.JWT_TTL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 生成管理员token
     * @return
     */
    public String getAdminToken(){
        LoginUser loginUser = new LoginUser();
        loginUser.setUserType("I");
        loginUser.setUserName("admin");
        loginUser.setUserId("admin");
        loginUser.setComName("白鹭");
        loginUser.setComCode("jg001");
        String result = null;
        try {
            result = JwtUtil.userToJwt(loginUser, SysConstant.JWT_TTL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
