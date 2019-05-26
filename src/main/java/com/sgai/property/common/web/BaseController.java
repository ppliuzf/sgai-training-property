package com.sgai.property.common.web;

import com.sgai.common.utils.StringUtils;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2016/4/18
 * Time: 17:44
 * Version:1.0
 *
 * @author llh
 */
public abstract class BaseController {
    public int pageNo = 1;
    public final static int pageSize = 10;

    @Autowired
    private RedisClient redisClient;
    /**
     * 日志对象
     */

    public final static String SUCCESS = "success";
    public final static String FAILER = "failer";
    // 返回错误信息的标志
    public final static String RESULT_ERROR_MSG = "errorMessage";

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected RedirectAttributes attr;

    /**
     * 功能描述:只要是继承了BaseController，调用任何方法都会首先执行该方法。
     *
     * @param request
     * @param response
     * @param attr     void
     * @author llh
     */
    @ModelAttribute
    public void serReqAndRes(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr,
                             @RequestParam(value = "pageNo", required = false) String pageNo) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.attr = attr;
        if (pageNo != null) {
            this.pageNo = Integer.parseInt(pageNo);
        } else {
            this.pageNo = 1;
        }
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Time.class, new CustomDateEditor(new SimpleDateFormat("HH:mm:ss"), true));
    }

    @ExceptionHandler
    public Response expHandler(Exception ex) {
        Response responce ;
        if (ex instanceof BusinessException) {
            responce=new Response();
            responce.setCode(((BusinessException) ex).getCode());
            if(StringUtils.isNotEmpty(ex.getMessage())){
                responce.setMessage(ex.getMessage());
            }else{
                ((BusinessException) ex).setType(responce.getMessage());
            }
        } else{//第三方的异常直接放行
            responce=new Response();
            responce.setCode(ReturnType.Unknown.getCode());
            responce.setMessage(ReturnType.Unknown.getType());
        }
        ex.printStackTrace();
        return responce;
    }
}
