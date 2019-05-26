package com.sgai.property.common.configuration;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.consts.SysConstant;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.support.UserServletContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ClassName: PermissionInterceptor
 * Description: (这里用一句话描述这个类的作用)
 *
 * @author admin
 * Date 2017年11月18日
 * Company 首自信--智慧城市创新中心
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {


    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(SysConstant.LOGIN_IDENTITY_KEY);
    }

    public boolean ifLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        String token = request.getHeader("token");
        // 没有携带token 返回false 转发到没有权限处理
        if (token == null || "null".equals(token) || "".equals(token)) {
            token = request.getHeader("accessToken");
            if (token == null || "".equals(token)) {
                return false;
            } else {
                token = token.substring(3);
            }
        }
        LoginUser user = null;
        try {

            Claims claims = null;
            try {
                /*claims = JwtUtil.parseJWT(token);*/
                user = JwtUtil.jwtToUser(token);
            } catch (ExpiredJwtException e) {
            }
            /*String json = claims.getSubject();
            AdminUserSubject userSubject = JSONObject.parseObject(json, AdminUserSubject.class);
            user = new LoginUser();
            user.setUserId(userSubject.getUserId() + "");
            user.setEmCode(userSubject.getEmployeeId() + "");
            user.setUserName(userSubject.getUserName());*/
        } catch (MalformedJwtException e) {
            //解析异常的话  认为是token异常需要重新登录
            return false;
        }
        // 将用户信息放到上下文中
        UserServletContext.setUserInfo(user);
        return user != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        HandlerMethod method = (HandlerMethod) handler;
        System.out.println("拦截前台应用调用的方法名：=" + method.getMethod().getName());
        PermessionLimit permission = method.getMethodAnnotation(PermessionLimit.class);
        if (permission != null && permission.limit() == false) {
            return super.preHandle(request, response, handler);
        }
        if (!ifLogin(request, response)) {
            request.getRequestDispatcher("/admin/noPermit").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
