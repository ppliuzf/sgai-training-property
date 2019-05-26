  
    /**    
    * @Title: PaperConfirm.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.bean.LoginUser;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.property.wy.entity.PaperConfirm;
    import com.sgai.property.wy.service.PaperConfirmService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    /**
        * @ClassName: PaperConfirm
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年2月1日
        * @Company 首自信--智慧城市创新中心
        */
    @RestController
    @RequestMapping("/paperConfirm")
    public class PaperConfirmController  extends BaseController {
        @Autowired
        private PaperConfirmService paperConfirmService;
        @RequestMapping(value = "/confirmList", method=RequestMethod.POST)
        @PermessionLimit(limit=false)
        public CommonResponse getConfirmList(
                @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
                @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
                HttpServletRequest request, HttpServletResponse response, PaperConfirm paperConfirm, LoginUser user
                )throws IOException, ServletException {
                  Page<PaperConfirm> page = paperConfirmService.findPage(new Page<PaperConfirm>(pageNo, pageSize), paperConfirm);
            return ResponseUtil.successResponse(page);
        }
        /**
         *
         * save
         * @param GoodsBorrow
         * @return :Map<String,Object>
         * @since JDK 1.8
         * @author HEIBIN
         * @throws IOException
         * @throws ServletException
         */
        @RequestMapping(value = "/save", method=RequestMethod.POST)
        public CommonResponse save(PaperConfirm paperConfirm, LoginUser user ) throws ServletException, IOException {
            Map<String, Object> map = new HashMap<String, Object>();
            String paperConfirmArray[] = null;
            String recNumberArray[] = null;
            if(paperConfirm.getReceptUserId()!=null&&!"".equals(paperConfirm.getReceptUserId())){
                paperConfirmArray=paperConfirm.getReceptUserId().split(",");
            }
            if(paperConfirm.getRecNumber()!=null&&!"".equals(paperConfirm.getRecNumber())){
                recNumberArray=paperConfirm.getRecNumber().split(",");
            }
            for(int i=0;i<paperConfirmArray.length;i++){
                PaperConfirm paperConfirm1=new PaperConfirm();
                paperConfirm1.setSubId(paperConfirm.getSubId());
                paperConfirm1.setReceptUserId(paperConfirmArray[i]);
                paperConfirm1.setRecNumber(recNumberArray[i]);
                paperConfirm1.setCreateTime(new Date());
                try {
                    paperConfirmService.save(paperConfirm1);
                    map.put("msg", "success");
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("msg", "fail");
                }
            }
            return ResponseUtil.successResponse(map);
        }
        @RequestMapping(value = "/getPaperConfirmById", method=RequestMethod.POST)
        @PermessionLimit(limit=false)
        public CommonResponse getPaperConfirmById(
                HttpServletRequest request, HttpServletResponse response, PaperConfirm paperConfirm, LoginUser user
                )throws IOException, ServletException {
            List<PaperConfirm> list=paperConfirmService.getPaperConfirmById(paperConfirm);
            return ResponseUtil.successResponse(list);
        }
    }
