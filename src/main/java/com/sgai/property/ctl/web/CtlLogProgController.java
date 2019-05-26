package com.sgai.property.ctl.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.DateUtils;
import com.sgai.common.utils.excel.ExportExcel;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlLogProg;
import com.sgai.property.ctl.service.CtlLogProgService;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/** 
* ClassName: CtlLogProgController  
* Description: (这里用一句话描述这个类的作用)
* @author admin  
* Date 2017年11月18日  
* Company 首自信--智慧城市创新中心
*/  
@RestController
@RequestMapping(value = "${adminPath}/ctl/log/ctlLogProg")
@Api(description = "日志接口")
public class CtlLogProgController extends BaseController {

	@Autowired
	private CtlLogProgService ctlLogProgService;
	
	/*@ModelAttribute
	public CtlLogProg get(@RequestParam(required=false) String id) {
		CtlLogProg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlLogProgService.get(id);
		}
		if (entity == null){
			entity = new CtlLogProg();
		}
		return entity;
	}*/
	
	
	@ApiOperation(value = "获得日志信息", notes = "获得日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "progName", value = "功能名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "beginCreatedDt", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endCreatedDt", value = "结束时间", required = false, paramType = "query", dataType = "String")
    })
	@RequestMapping(value = "list",method=RequestMethod.POST)
	public CommonResponse list(
			 LoginUser user,
			 String userCode,
			 String beginCreatedDt,String endCreatedDt,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			 @RequestHeader("token") String token ,
			 HttpServletRequest request, HttpServletResponse response
			) throws IOException{

		CtlLogProg  ctlLogProg = new CtlLogProg();
		ctlLogProg.setUserCode(userCode);
		ctlLogProg.setBeginCreatedDt(beginCreatedDt);
		ctlLogProg.setEndCreatedDt(endCreatedDt);
		Page<CtlLogProg> page = ctlLogProgService.findPage(new Page<CtlLogProg>(pageNo, pageSize), ctlLogProg); 
		return ResponseUtil.successResponse(page);
	}

	@ApiOperation(value = "swagger测试2", notes = "swagger测试2")
	@RequestMapping(value = "listPost",method=RequestMethod.POST)
	public CommonResponse listPost(
			 @ApiParam(hidden=true) LoginUser user,
			 @RequestBody @ApiParam(name="用户对象",value="传入json格式") CtlLogProg ctlLogProg,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			 @RequestHeader("token") String token
			) throws IOException {
        
		Page<CtlLogProg> page = ctlLogProgService.findPage(new Page<CtlLogProg>(pageNo, pageSize), ctlLogProg); 
		return ResponseUtil.successResponse(page);
	}
	

	@PermessionLimit(limit = false)
	@RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HttpServletRequest request, 
    		HttpServletResponse response, RedirectAttributes redirectAttributes) {
		CtlLogProg  ctlLogProg  = new CtlLogProg();
		try {
            String fileName = "运行日志"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CtlLogProg> page = ctlLogProgService.findPage(new Page<CtlLogProg>(request, response), ctlLogProg); 
    		new ExportExcel("运行日志", CtlLogProg.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "";
    }
	

	@RequestMapping(value = "form")
	public String form(CtlLogProg ctlLogProg, Model model) {
		model.addAttribute("ctlLogProg", ctlLogProg);
		return "modules/ctl.user.log/ctlLogProgForm";
	}

	//@RequiresPermissions("ctl.user.log:ctlLogProg:edit")
	@RequestMapping(value = "save")
	public String save(CtlLogProg ctlLogProg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlLogProg)){
			return form(ctlLogProg, model);
		}
		ctlLogProgService.save(ctlLogProg);
		addMessage(redirectAttributes, "保存日志管理成功");
		return "redirect:"+Global.getAdminPath()+"/ctl.user.log/ctlLogProg/?repage";
	}
	
	//@RequiresPermissions("ctl.user.log:ctlLogProg:edit")
	@RequestMapping(value = "delete")
	public String delete(CtlLogProg ctlLogProg, RedirectAttributes redirectAttributes) {
		String idMerge=ctlLogProg.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ctlLogProg=new CtlLogProg();
				ctlLogProg.setId(id);
				ctlLogProgService.delete(ctlLogProg);
			}
		}
		addMessage(redirectAttributes, "删除日志管理成功");
		return "redirect:"+Global.getAdminPath()+"/ctl.user.log/ctlLogProg/?repage";
	}

	
	
	
}