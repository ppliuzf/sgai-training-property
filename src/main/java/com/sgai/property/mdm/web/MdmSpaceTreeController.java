package com.sgai.property.mdm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sgai.property.commonService.dto.SpaceDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.consts.SysConsts;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmSpaceInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmSpaceTreeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/** 
* ClassName: MdmSpaceTreeController  
* Description: 空间主数据 
* @author admin  
* Date 2017年11月29日  
* Company 首自信--智慧城市创新中心
*/  
@Api(description = "空间结构")
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmSpaceTree")
public class MdmSpaceTreeController extends BaseController {

	@Autowired
	private MdmSpaceTreeService mdmSpaceTreeService;

	
	/*@ModelAttribute
	public MdmSpaceTree get(@RequestParam(required=false) String id) {
		MdmSpaceTree entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmSpaceTreeService.get(id);
		}
		if (entity == null){
			entity = new MdmSpaceTree();
		}
		return entity;
	}*/
	
	/**
	 * getSpaceList:(加载树形结构).
	 * @param request
	 * @param response
	 * @return :List<Map<String,Object>>
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getSpaceList",method=RequestMethod.POST)
	@ApiOperation(value = "获得所有的空间树结构", notes = "获得所有的空间树结构")
	public List<SpaceDto> getSpaceList(@RequestHeader("token") String token , LoginUser user,
									   HttpServletRequest request, HttpServletResponse response) {
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		if(StringUtils.isNotBlank(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		return mdmSpaceTreeService.getSpaceList(mdmSpaceTree);
	}
	
	/**
	 * getSpaceList:(加载树形结构).
	 * @param request
	 * @param response
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getUserSpace",method=RequestMethod.POST)
	@ApiOperation(value = "根据用户获得空间树结构", notes = "获得所有的空间树结构")
	public Map<String,List<Map<String,Object>>> getUserSpace(@RequestHeader("token") String token , 
			@RequestParam(required=true) String corrCode,
			@RequestParam(required=false) String comCode,
			HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = new LoginUser();
		user.setUserId(corrCode);
		user.setComCode(comCode);
		Map<String,List<Map<String,Object>>> result = new HashMap<>();
		result.put("lackList", mdmSpaceTreeService.getUserSpaceLack(user));
		result.put("ownList", mdmSpaceTreeService.getUserSpaceOwn(user));
		return result;
	}
	
	@RequestMapping(value="/getUserProf",method=RequestMethod.POST)
	@ApiOperation(value="根据用户获取专业",notes="根据用户获取专业")
	public Map<String,List<Map<String,Object>>> getUserProf(
			@RequestParam(required=true) String corrCode,
			@RequestParam(required=false) String comCode,
			HttpServletRequest request,HttpServletResponse respinse){
		LoginUser user = new LoginUser();
		user.setUserId(corrCode);
		user.setComCode(comCode);
		Map<String,List<Map<String,Object>>> result = new HashMap<>();
		result.put("lackList", mdmSpaceTreeService.getUserProfLack(user));
		result.put("ownList", mdmSpaceTreeService.getUserProfOwn(user));
		return result;
	}
	
	/**
	 * getSpaceList:(加载树形结构，只是加载楼层和层).
	 * @param request
	 * @param response
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getSpaceFloorBuild",method=RequestMethod.POST)
	@ApiOperation(value = "获得空间树结构楼层和楼",notes = "获得空间树结构楼层和楼")
	public List<Map<String,Object>> getSpaceFloorBuild(LoginUser user,
		HttpServletRequest request, HttpServletResponse response){
		MdmSpaceTree mdmSpaceTree  = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		if (StringUtils.isNotBlank(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		/*Map<String,String> sqlMap = new HashMap<String,String>();
		String sql = "AND IN ('FLOOR','BUILD')";
		sqlMap.put("sql", sql);
		mdmSpaceTree.setSqlMap(sqlMap);*/
		return mdmSpaceTreeService.getSpaceFloorBuild(mdmSpaceTree);
	}
	/**
	 * 
	    * @Title: getSelectSpace  
	    * @com.sgai.property.commonService.vo;(根据区域获取空间)
	    * @param @param token
	    * @param @param user
	    * @param @param linkCode
	    * @param @param request
	    * @param @param response
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
	@RequestMapping(value = "getSelectSpace",method=RequestMethod.POST)
	@ApiOperation(value = "获得所有的空间树结构", notes = "获得所有的空间树结构")
	public List<SpaceDto> getSelectSpace(@RequestHeader("token") String token ,
										 LoginUser user,
										 String linkCode,
										 HttpServletRequest request, HttpServletResponse response) {
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		Map<String,String> sqlMap = new HashMap<String,String>();
		sqlMap.put("sqlMap", " AND NODE_CODE IN "
				+ "( SELECT A .SPACE_CODE FROM RUAG_LINKAGE_RULE_SPACE A WHERE A.COM_CODE='"+user.getComCode()+"' and A .LINKAGE_CODE = '"+linkCode+"')");
		if(StringUtils.isNotBlank(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		mdmSpaceTree.setSqlMap(sqlMap);
		return mdmSpaceTreeService.getSpaceList(mdmSpaceTree);
	
	}
	@RequestMapping(value = "getSpaceListByLinkCode",method=RequestMethod.POST)
	@ApiOperation(value = "获得所有的空间树结构", notes = "获得所有的空间树结构")
	public List<SpaceDto> getSpaceListBylingkCode(@RequestHeader("token") String token ,
												  LoginUser user,
												  String spaceCodes,
												  HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> sqlMap = new HashMap<String,String>();
		sqlMap.put("sql", "AND node_code in("+spaceCodes.substring(0, spaceCodes.length()-1)+")");
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		if(StringUtils.isNotBlank(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		mdmSpaceTree.setSqlMap(sqlMap);
		return mdmSpaceTreeService.getSpaceList(mdmSpaceTree);
	
	}
	/**
	 * 
	 * getSpaceLists:(获得所有的空间树结构).
	 * @param token
	 * @param request
	 * @param response
	 * @return :List<Map<String,Object>>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "getSpaceLists",method=RequestMethod.GET)
	@ApiOperation(value = "获得所有的空间树结构", notes = "获得所有的空间树结构")
	public List<SpaceDto> getSpaceLists(@RequestHeader("token") String token , LoginUser user,
										HttpServletRequest request, HttpServletResponse response) {
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		if(user.getModuCode() != null && !"".equals(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		return mdmSpaceTreeService.getSpaceList(mdmSpaceTree);
	}

	/**
	 * getNodeInfo:结点的信息. 在单机结点的时候返回的本结点信息  
	 * @param request
	 * @param response
	 * @return :返回一个dto 包装类  包含所有的结点信息
	 * @since JDK 1.8
	 * @author admin
	 * @throws JsonProcessingException 
	 */
     @RequestMapping(value = "getNodeInfo",method=RequestMethod.POST)
     @ApiOperation(value = "通过Id获得空间结点", notes = "通过Id获得空间结点")
     @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "空间结点id", required = true,paramType = "query", dataType = "String")
     })
     public CommonResponse getNodeInfo(String id,
		   @RequestHeader("token") String token ,
		   LoginUser user,
		   HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
    	 MdmSpaceTree spaceTree = new MdmSpaceTree();
    	 spaceTree.setSpaceCode(id);
    	 spaceTree.setComCode(user.getComCode());
    	 spaceTree.setModuCode(user.getModuCode());
    	 MdmSpaceTree mdmSpaceTree =  mdmSpaceTreeService.getByCode(spaceTree);
    	 String nodeType = mdmSpaceTree.getNodeType();
    	 String arg0 = mdmSpaceTree.getSpaceCode();
    	 MdmSpaceInfo spaceInfo =  mdmSpaceTreeService.getSpaceInfo(nodeType, arg0);
		 return ResponseUtil.successResponse(spaceInfo);
	
	}
	
     @RequestMapping(value = "saveSpaceTree")
 	public Map<String,Object> saveSpaceTree(
 			                               @RequestParam(required=true) String corrCode,
 			                               @RequestParam(required=true) String menuCodes,
 			                               @RequestParam(required=true) String comCode) {
 		Map<String,Object> result = Maps.newHashMap();
 		try {
 			Map<String,String> param = new HashMap<String,String>();
 			LoginUser user = new LoginUser();
 			user.setUserId(corrCode);
// 			param.put("corrCode", corrCode);
// 			param.put("category", category);
 			param.put("userCode", user.getUserId());
 			mdmSpaceTreeService.deleteSpaceTree(param);
 			List<String> menuCodeList = Lists.newArrayList();
 			String[] menuCodeArray = menuCodes.split(",");
 			for(String s : menuCodeArray) {
 				if(s!=null && !s.equals("")) {
 					menuCodeList.add(s);
 				}
 			}
 			mdmSpaceTreeService.saveSpaceTree(menuCodeList,user.getUserId());
 			//CacheUtils.remove("IndexMenus", corrCode);
 			result.put("state", true);
 			result.put("msg", "保存成功!");
 		}catch(Exception e) {
 			e.printStackTrace();
 			result.put("state", false);
 			result.put("msg", "保存失败!");
 		}
 		return result;
 	}
     
     @RequestMapping(value = "saveProfTree")
     public Map<String,Object> saveProfTree(
    		 @RequestParam(required=true) String corrCode,
    		 @RequestParam(required=true) String menuCodes,
    		 @RequestParam(required=true) String comCode) {
    	 Map<String,Object> result = Maps.newHashMap();
    	 try {
    		 LoginUser user = new LoginUser();
  			 user.setUserId(corrCode);
    		 Map<String,String> param = new HashMap<String,String>();
// 			param.put("corrCode", corrCode);
// 			param.put("category", category);
    		 param.put("userCode", user.getUserId());
    		 mdmSpaceTreeService.deleteProfTree(param);
    		 List<String> menuCodeList = Lists.newArrayList();
    		 String[] menuCodeArray = menuCodes.split(",");
    		 for(String s : menuCodeArray) {
    			 if(s!=null && !s.equals("")) {
    				 menuCodeList.add(s);
    			 }
    		 }
    		 mdmSpaceTreeService.saveProfTree(menuCodeList,user.getUserId());
    		 //CacheUtils.remove("IndexMenus", corrCode);
    		 result.put("state", true);
    		 result.put("msg", "保存成功!");
    	 }catch(Exception e) {
    		 e.printStackTrace();
    		 result.put("state", false);
    		 result.put("msg", "保存失败!");
    	 }
    	 return result;
     }
     
	/**
	 * saveSpace:保存结点信息  新增保存和修改保存
	 * @param mdmSpaceInfo
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "saveSpace")
	public ResponseEntity<String> saveSpace(MdmSpaceInfo mdmSpaceInfo, LoginUser user) throws JsonProcessingException {
		Map<String,String> map = new HashMap<>();
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		mdmSpaceTree.setModuCode(user.getModuCode());
		mdmSpaceTree.setSpaceCode(mdmSpaceInfo.getSpaceNodeCode());
		MdmSpaceTree stree = mdmSpaceTreeService.getByCode(mdmSpaceTree);
		if(stree!=null) {
			//修改保存
			mdmSpaceInfo.setSpaceNodeType(stree.getNodeType());
		}
		MdmSpaceTree str = mdmSpaceTreeService.getByCode(mdmSpaceTree);
		String nodeType  = mdmSpaceInfo.getSpaceNodeType();
        if(!"".equals(nodeType)) {
           mdmSpaceInfo.setComCode(user.getComCode());
    	  Map<String,String> map1 = mdmSpaceTreeService.saveSpaceInfo(mdmSpaceInfo,map);
    	  if(map1.get("msg").equals("repeat")) {
       	   return ResponseUtil.custom(SysConsts.RESCODE_CUSTOM_MSG, "空间编码已经存在，请重新编写");
    	  } else {
       	   return ResponseUtil.custom(SysConsts.RESCODE_SUCCESS_MSG, "保存成功");
    	  }
        }else if("".equals(nodeType) && "ROOM".equals(str.getNodeType())) {
        	//父级是room类型 并且新增类型为空  判断是room级别的新增下级
        	return ResponseUtil.custom(SysConsts.RESCODE_EXCEPTION, "不能新增下级结点啦！"); 
        }else {
        	return ResponseUtil.custom(SysConsts.RESCODE_CUSTOM_MSG, "保存失败");
        }
	}
		
	/**
	 * listSubSpace:(列出右下边的直接子树).
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "listSubSpace",method=RequestMethod.POST)
	@ApiImplicitParams({
	        @ApiImplicitParam(name = "total_code", value = "父结点id", required = true,paramType = "query", dataType = "String")
	     })
	@ApiOperation(value = "通过父结点id获得所有的子结点", notes = "通过父结点id获得所有的子结点")
	public CommonResponse listSubSpace(String total_code,
			@RequestHeader("token") String token ,HttpServletRequest request,
			HttpServletResponse response ) throws JsonProcessingException {
		MdmSpaceTree  mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setParentCode(total_code);
		mdmSpaceTree.setEnabledFlag('Y');
		Page<MdmSpaceTree> page = mdmSpaceTreeService.findPage(new Page<MdmSpaceTree>(request, response), mdmSpaceTree); 
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * deleteNew:(删除结点方法).
	 * @param nodeCode
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "deleteNew")
	public ResponseEntity<String> deleteNew(String nodeCode, LoginUser user,HttpServletRequest request, 
			HttpServletResponse response ) throws JsonProcessingException {
	    mdmSpaceTreeService.deleteNew(nodeCode,user.getComCode(),user.getModuCode());
	    return ResponseUtil.success("ok");
	}
	
}