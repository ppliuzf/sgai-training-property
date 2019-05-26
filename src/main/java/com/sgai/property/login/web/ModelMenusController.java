package com.sgai.property.login.web;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.entity.IndexMenus;
import com.sgai.property.ctl.service.ModelMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/2 9:05
 */
@RestController
@RequestMapping("/admin")
public class ModelMenusController {
    @Autowired
    private ModelMenuService modelMenuService;

    @RequestMapping("/getModelMenus")
    public ResponseEntity<String> getTechnologyMenus(HttpServletRequest request, String modelType) throws Exception {
        String token = request.getHeader("token");
        try {
            if (token == null || "".equals(token) || "null".equals(token))
                return ResponseUtil.noLogin();
            LoginUser user = UserServletContext.getUserInfo();
            List<IndexMenus> listMenus = modelMenuService.getIndexMenusByModelName(user.getUserType(), user.getUserId(), modelType);
            return ResponseUtil.success(listMenus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.unKonwException();
        }

    }
}
