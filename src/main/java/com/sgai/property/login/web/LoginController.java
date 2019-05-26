package com.sgai.property.login.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.FastDSFile;
import com.sgai.common.utils.CryptUtil;
import com.sgai.common.utils.DateUtils;
import com.sgai.common.utils.Encodes;
import com.sgai.common.utils.FastDFSClientUtils;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.consts.SysConstant;
import com.sgai.modules.login.consts.SysConsts;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.entity.*;
import com.sgai.property.ctl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LoginController Description: 登录用Controller
 *
 * @author admin Date 2017年11月18日 Company 首自信--智慧城市创新中心
 */
@RestController
public class LoginController {

	@Autowired
	private CtlUserService ctlUserService;
	@Autowired
	private CtlLogService ctlLogService;
	@Autowired
	private CtlEmpService ctlEmpService;
	@Autowired
	private CtlCompService ctlCompService;
	@Autowired
	private CtlCompBusiService ctlCompBusiService;

	/**
	 * doLogin:(这里用一句话描述这个方法的作用).
	 *
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String>
	 * @author admin
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public ResponseEntity<String> doLogin(String userName, String password, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		try {
			String ip = request.getRemoteAddr();
			CtlUser ctlUser = ctlUserService.getUserByLoginNameAndpassword(userName, password);
			if (ctlUser != null && ctlUser.getComCode() != null && !"".equals(ctlUser.getComCode())) {
				CtlComp comp = ctlCompService.findByCompCode(ctlUser.getComCode());
				ctlUser.setComName(comp.getComName());
			}
			if (ctlUser != null) {
				// 密码验证通过
				if (!"Y".equals(ctlUser.getEnabledFlag())) {
					ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "用户被禁用");
				}
				if ("I".equals(ctlUser.getUserType())) { // 普通用户
					// 普通用户 I 机构用户 V是机构用户的外部用户 暂不考虑
					String corrCode = ctlUser.getCorrCode();
					if (corrCode == null || "".equals(corrCode)) {
						ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "用户未绑定员工！");
					}
					CtlEmp ctlEmp = ctlEmpService.getEmpByComCodeAndEmpCode(corrCode, ctlUser.getComCode());
					if (ctlEmp != null) {
						ctlUser.setCorrCode(corrCode);
						ctlUser.setCorrName(ctlEmp.getLastname());
						ctlUser.setDeptCode(ctlEmp.getDeptCode());
					} else {
						ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "绑定员工不存在！");
					}
				}
				// 模块管理员判断
				if ("MO".equals(ctlUser.getUserType())) {
					if (ctlUser.getModuCode() == null || "".equals(ctlUser.getMenuType())) {
						ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "模块管理员请先关联模块！");
					}
				}
				LoginUser loginUser = new LoginUser();
				loginUser.setUserId(ctlUser.getUserCode());
				loginUser.setComCode(ctlUser.getComCode());
				loginUser.setComName(ctlUser.getComName());
				loginUser.setUserName(ctlUser.getUserName());
				loginUser.setUserType(ctlUser.getUserType());
				loginUser.setDeptCode(ctlUser.getDeptCode());
				loginUser.setModuCode(ctlUser.getModuCode());
				loginUser.setEmCode(ctlUser.getCorrCode());
				UserServletContext.setUserInfo(loginUser);

				String token = JwtUtil.userToJwt(loginUser, SysConstant.JWT_TTL);
				String refreshToken = JwtUtil.userToJwt(loginUser, SysConstant.JWT_REFRESH_TTL);
				JSONObject jo = new JSONObject();
				jo.put("token", token);
				jo.put("refreshToken", refreshToken);

				// 记录在线日志
				doLoginOn(ctlUser, ip, request.getSession().getId());
				return ResponseUtil.success(jo);
			} else {
				return ResponseUtil.custom(SysConsts.RESCODE_WRONGUSERORPASSWORD, "用户名或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.unKonwException();
		}
	}

	/**
	 * 修改用户密码
	 *
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String>
	 * @author admin
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "${adminPath}/editPwd", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public ResponseEntity<String> editPwd(String userName, String oldPwd, String newPwd, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		try {
			String ip = request.getRemoteAddr();
			CtlUser ctlUser = ctlUserService.getUserByLoginNameAndpassword(userName, oldPwd);
			if (ctlUser != null) {
				// 密码验证通过
				if (!"Y".equals(ctlUser.getEnabledFlag())) {
					ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "用户被禁用,无法修改");
				}
				if ("I".equals(ctlUser.getUserType())) { // 普通用户
					// 普通用户 I机构用户 V是机构用户的外部用户 暂不考虑
					String corrId = ctlUser.getCorrCode();
					if (corrId == null || "".equals(corrId)) {
						ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "用户不存在！");
					}
					CtlEmp ctlEmp = ctlEmpService.getEmpByComCodeAndEmpCode(corrId, ctlUser.getComId());
					if (ctlEmp != null) {
						ctlUser.setCorrCode(corrId);
						ctlUser.setCorrName(ctlEmp.getLastname());
					} else {
						ResponseUtil.custom(SysConsts.RESCODE_FORBIDDEN, "用户不存在！");
					}
				}
				String enCodePwd = CryptUtil.encode(newPwd, ctlUser.getUserCode());
				ctlUser.setUserPass(enCodePwd);
				ctlUserService.editPwdByUserName(ctlUser);
				return ResponseUtil.custom(SysConsts.RESCODE_SUCCESS, "修改密码成功");
			} else {
				return ResponseUtil.custom(SysConsts.RESCODE_WRONGUSERORPASSWORD, "原密码输入有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.unKonwException();
		}
	}

	private void doLoginOn(CtlUser ctlUser, String ip, String sessionId) {
		CtlLog ctlLog = new CtlLog();
		ctlLog.setComCode(ctlUser.getComCode());
		ctlLog.setUserCode(ctlUser.getUserCode());
		ctlLog.setUserName(ctlUser.getUserName());
		ctlLog.setUserType(ctlUser.getUserType());
		ctlLog.setLoginTime(new Date());
		ctlLog.setSessionId(sessionId);
		ctlLog.setCorrName(ctlUser.getCorrName());
		ctlLog.setCorrCode(ctlUser.getCorrCode());
		ctlLog.setLoginIp(ip);
		ctlLog.setOnlineFlag("Y");
		ctlLogService.save(ctlLog);
	}

	/**
	 * getMenus:(这里用一句话描述这个方法的作用).
	 *
	 * @throws Exception :ResponseEntity<String>
	 * @author admin
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "${adminPath}/getMenus", method = RequestMethod.POST)
	public ResponseEntity<String> getMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String token = request.getHeader("token");
		try {
			if (token == null || "".equals(token) || "null".equals(token))
				return ResponseUtil.noLogin();
			LoginUser user = UserServletContext.getUserInfo();
			List<IndexMenus> listMenus = ctlUserService.getIndexMenus(user.getUserType(), user.getUserId());
			return ResponseUtil.success(listMenus);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.unKonwException();
		}

	}

	/**
	 * refreshToken:(这里用一句话描述这个方法的作用).
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception :ResponseEntity<String>
	 * @author admin
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "${adminPath}/refreshToken", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String refreshToken = request.getHeader("refreshToken");
		try {
			if (refreshToken == null || "".equals(refreshToken) || "null".equals(refreshToken))
				return ResponseUtil.noLogin();
			LoginUser user = JwtUtil.jwtToUser(refreshToken);
			if (user != null) {
				String token = JwtUtil.userToJwt(user, SysConstant.JWT_TTL);
				System.out.println("新生成的token:" + token);
				return ResponseUtil.success(token);
			} else {
				return ResponseUtil.noLogin();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.unKonwException();
		}
	}

	/**
	 * @param @param url
	 * @param @param token
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception 参数
	 * @return ResponseEntity<String> 返回类型
	 * @throws @Title: btnPermit
	 * @Description: 当前用户获得页面的按钮权限
	 */
	@RequestMapping(value = "${adminPath}/btnPermit", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse btnPermit(@RequestParam(value = "url", required = true) String url,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser userInfo = UserServletContext.getUserInfo();
		//List<String> listBtn = ctlUserService.getBtnsForAllUser(url, userInfo);
		return ResponseUtil.successResponse(true);

	}

	/**
	 * noPermit:(针对没有token的情况 直接返回).
	 *
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String>
	 * @author admin
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/admin/noPermit")
	@PermessionLimit(limit = false)
	public CommonResponse noPermit(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {
		String status_ = request.getParameter("status");
		if (status_ != null) {
			if (status_.equals("no_token"))
				return ResponseUtil.noLoginResponse();
			else
				return ResponseUtil.unKonwExceptionResponse();
		}
		return ResponseUtil.noLoginResponse();
	}

	/**
	 * @param @param file
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception 参数
	 * @return ResponseEntity<String> 返回类型
	 * @throws @Title: uploadNow
	 * @Description: 上传实例
	 */
	@RequestMapping(value = "${adminPath}/uploadNow", method = RequestMethod.POST)
	public ResponseEntity<String> uploadNow(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FastDSFile fastDSFile = new FastDSFile();
		fastDSFile.setContent(file.getBytes());
		fastDSFile.setExt("jpg");
		JSONArray rs = FastDFSClientUtils.upload(fastDSFile);
		String filePath = "http://114.115.140.115/" + rs.getString(0) + "/" + rs.getString(1);
		System.out.println("上传路径:" + filePath);
		return ResponseUtil.success(filePath);
	}

	/**
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型
	 * @throws @Title: downloadNow
	 * @Description: 下载实例
	 */
	@RequestMapping(value = "${adminPath}/downloadNow", method = RequestMethod.POST)
	public String downloadNow(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String groupName = "group1";
		String remoteFileName = "M00/00/00/wKgABFovMieAEYMaAACrzWKUo_o170.jpg";
		String fileName = "下载文件" + DateUtils.getDate("yyyyMMddHHmmss") + ".jpg";

		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
		OutputStream out = response.getOutputStream();

		byte[] content = FastDFSClientUtils.download(groupName, remoteFileName);
		try {
			out.write(content);
			// 关闭输出流
			if (out != null) {
				out.flush();
				out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param @param file
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception 参数
	 * @return ResponseEntity<String> 返回类型
	 * @throws @Title: deleteNow
	 * @Description: 删除实例
	 */
	@RequestMapping(value = "${adminPath}/deleteNow", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNow(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String groupName = "group1";
		String remoteFileName = "M00/00/00/wKgABFovMieAEYMaAACrzWKUo_o170.jpg";
		int i = FastDFSClientUtils.delete(groupName, remoteFileName);
		if (i == 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("失败哦");
		}
		return ResponseUtil.success();
	}

	@RequestMapping(value = "${adminPath}/importExcel")
	public CommonResponse importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws JsonProcessingException {
		// filePath = "D:\\事件流程定义.xls";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// map = wfFlowDefineService.importFlowDefines(filePath);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}
