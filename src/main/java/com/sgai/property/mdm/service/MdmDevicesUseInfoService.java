package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmDevicesUseInfoDao;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceProf;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;

/**
 *
 * ClassName: MdmDevicesUseInfoService com.sgai.property.commonService.vo;(设备主数据业务层)
 *
 * @author yangyz Date 2017年11月24日 Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmDevicesUseInfoService extends CrudServiceExt<MdmDevicesUseInfoDao, MdmDevicesUseInfo> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	@Autowired
	private MdmSpaceTreeService mdmSpaceTreeService;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");

	public MdmDevicesUseInfo get(String id) {

		return super.get(id);
	}

	public MdmDevicesUseInfoVo getpro(String id) {

		return dao.getMdmDevicesUseInfoVo(id);
	}

	public List<MdmDevicesUseInfo> findList(MdmDevicesUseInfo mdmDevicesUseInfo) {
		return super.findList(mdmDevicesUseInfo);
	}

	public Page<MdmDevicesUseInfo> findPage(Page<MdmDevicesUseInfo> page, MdmDevicesUseInfo mdmDevicesUseInfo) {
		return super.findPage(page, mdmDevicesUseInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmDevicesUseInfo mdmDevicesUseInfo) {
		super.save(mdmDevicesUseInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmDevicesUseInfo mdmDevicesUseInfo) {
		super.delete(mdmDevicesUseInfo);
	}

	/**
	 *
	 * findPageVoByModel:(查询设备主数据列表重新组装Vo).
	 *
	 * @param page
	 * @param mdmDevicesUseInfoVo
	 * @return :Page<MdmDevicesUseInfoVo>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Page<MdmDevicesUseInfoVo> findPageVoByModel(Page<MdmDevicesUseInfoVo> page,
			MdmDevicesUseInfoVo mdmDevicesUseInfoVo) {
		mdmDevicesUseInfoVo.setPage(page);
		mdmDevicesUseInfoVo.preGet();
		page.setList(dao.findListVo(mdmDevicesUseInfoVo));
		return page;
	}

	public Page<MdmDevicesUseInfoVo> findAttrPageVoByDevices(Page<MdmDevicesUseInfoVo> page,
			MdmDevicesUseInfoVo mdmDevicesUseInfoVo) {
		List<MdmDevicesUseInfoVo> attrNameList = dao.getALLAttrName(mdmDevicesUseInfoVo);

		String paramStr = "";

		String selectStr = "";

		if (attrNameList != null && !attrNameList.isEmpty()) {
			for (MdmDevicesUseInfoVo mdmDevicesUseInfoVo2 : attrNameList) {
				paramStr += ",'" + mdmDevicesUseInfoVo2.getAttrName() + "' " + mdmDevicesUseInfoVo2.getAttrName();

				selectStr += ",b." + mdmDevicesUseInfoVo2.getAttrName() + " " + mdmDevicesUseInfoVo2.getAttrName();
			}
			// 第一个去掉逗号
			paramStr = paramStr.substring(1);

			selectStr = selectStr.substring(1);
		}

		if (paramStr != "") {
			paramStr = "select * from (select DEVICES_CODE,ATTR_NAME,ATTR_VALUE from MDM_ATTRIBUTE) pivot (max(ATTR_VALUE) for ATTR_NAME in"
					+ "(" + paramStr + "))";
		} else {
			paramStr = "select * from (select DEVICES_CODE,ATTR_NAME,ATTR_VALUE from MDM_ATTRIBUTE) pivot (max(ATTR_VALUE) for ATTR_NAME in"
					+ "())";
		}

		mdmDevicesUseInfoVo.setParamStr(paramStr);

		mdmDevicesUseInfoVo.setSelectStr(selectStr);

		mdmDevicesUseInfoVo.setPage(page);
		mdmDevicesUseInfoVo.preGet();
		page.setList(dao.findAttrListVoByDevices(mdmDevicesUseInfoVo));

		return page;
	}

	/**
	 *
	 * saveDeviceClass:(新增或修改保存设备主数据).
	 *
	 * @param mdmDevicesUseInfo
	 * @param oldDevicesCode
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveDevicesUseInfo(MdmDevicesUseInfo mdmDevicesUseInfo) {

		Map<String, Object> map = new HashMap<String, Object>();
		mdmDevicesUseInfo.setEnabledFlag("Y");
		if (mdmDevicesUseInfo.getId() != null && !"".equals(mdmDevicesUseInfo.getId())) {
			super.save(mdmDevicesUseInfo);
			map.put("msg", "success");
		} else {
			MdmDevicesUseInfo info = new MdmDevicesUseInfo();
			// String code = ctlComRuleService.getNext("DEVICE-USE-CODE");
			String code = mdmDevicesUseInfo.getDevicesCode();
			if (StringUtils.isNotBlank(code)) {
				info.setDevicesCode(code);
			} else {
				code = ctlComRuleService.getNext("DEVICE-CODE");
				info.setDevicesCode(code);
			}
			List<MdmDevicesUseInfo> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			} else {
				mdmDevicesUseInfo.setDevicesCode(code);
				super.save(mdmDevicesUseInfo);
				map.put("msg", "success");
			}
		}

		return map;
	}

	/**
	 *
	 * deleteDevicesUseInfo:(批量删除设备主数据).
	 *
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteDevicesUseInfo(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[] = ids.split(",");
		List<MdmDevicesUseInfo> list = new ArrayList<MdmDevicesUseInfo>();
		List<String> newList = new ArrayList<String>();
		for (String id : idss) {
			if (id != null && !id.equals("")) {
				newList.add(id);
				MdmDevicesUseInfo info = super.get(id);
				list.add(info);
			}
		}
		Map<String, String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmDevicesUseInfo.class, newList);
		if ("true".equals(resultMap.get("value"))) {
			List<MdmDevicesUseInfo> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			} else {
				map.put("msg", "删除失败！");
			}
			map.put("result", "success");
		} else {
			map.put("msg", resultMap.get("description"));
			map.put("result", "fail");
		}
		return map;
	}

	/**
	 *
	 * @Title: findNotByIdList @Description:
	 *         mdmDevicesUseInfoVo @param @return 参数 @return
	 *         Page<MdmDevicesUseInfoVo> 返回类型 @throws
	 */
	public Page<MdmDevicesUseInfoVo> findNotByIdList(Page<MdmDevicesUseInfoVo> page,
			MdmDevicesUseInfoVo mdmDevicesUseInfoVo) {
		mdmDevicesUseInfoVo.setPage(page);
		mdmDevicesUseInfoVo.preGet();
		page.setList(dao.findNotByIdList(mdmDevicesUseInfoVo));
		return page;
	}

	/**
	 *
	 * @Title: findProBySpace @com.sgai.property.commonService.vo;(根据联动规则区域查询专业) @param @param
	 *         param @param @return 参数 @return List<MdmDeviceProf> 返回类型 @throws
	 */
	public List<MdmDeviceProf> findProBySpace(Map<String, String> param) {
		return dao.findProBySpace(param);
	}

	/**
	 *
	 * @Title: findListByProf @com.sgai.property.commonService.vo;(根据专业查询设备) @param @param
	 *         param @param @return 参数 @return List<MdmDevicesUseInfoVo>
	 *         返回类型 @throws
	 */
	public List<MdmDevicesUseInfoVo> findListByProf(MdmDevicesUseInfoVo mdmDevicesUseInfoVo) {
		return dao.findListByProf(mdmDevicesUseInfoVo);
	}

	public List<MdmDevicesUseInfoVo> findMdmDevicesUseInfoVoList(MdmDevicesUseInfoVo mdmDevicesUseInfoVo) {
		return dao.findListVo(mdmDevicesUseInfoVo);
	}

	/**
	 *
	 * @Title: findDeviceCodes @com.sgai.property.commonService.vo;(根据专业和位置查询设备编码) @param @param
	 *         param @param @return 参数 @return List<String> 返回类型 @throws
	 */
	public List<String> findDeviceCodes(Map<String, String> param) {
		LoginUser user = UserServletContext.getUserInfo();
		param.put("comCode", "bailu");
		param.put("moduCode", "");
		return dao.findDeviceCodes(param);
	}
	/**
	 *
	    * @Title: getVideoTree
	    * @com.sgai.property.commonService.vo;(获取视频位置树)
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
	public List<Map<String, Object>> getVideoTree() {
		LoginUser user = UserServletContext.getUserInfo();
		MdmSpaceTree mdmSpaceTree  = new MdmSpaceTree();
		mdmSpaceTree.setComCode(user.getComCode());
		if (StringUtils.isNotBlank(user.getModuCode())) {
			mdmSpaceTree.setModuCode(user.getModuCode());
		}
		List<Map<String, Object>> result=mdmSpaceTreeService.getSpaceFloorBuild(mdmSpaceTree);
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
		mdmDevicesUseInfoVo.setProfCode("DEVICE-PROF-2018-00010");
		mdmDevicesUseInfoVo.setComCode(user.getComCode());
		mdmDevicesUseInfoVo.setModuCode(user.getModuCode());
		 List<MdmDevicesUseInfoVo> findListByProf = dao.findListByProf(mdmDevicesUseInfoVo);
		 for (MdmDevicesUseInfoVo mdmDevicesUseInfoVo2 : findListByProf) {
			 Map<String, Object> videoMap = new HashMap<String, Object>();
				videoMap.put("id",mdmDevicesUseInfoVo2.getDevicesCode() );
				videoMap.put("pId",mdmDevicesUseInfoVo2.getSpaceCode());
				videoMap.put("name", mdmDevicesUseInfoVo2.getDevicesName());
				result.add(videoMap);
		}
		return result;
	}
	/**
	 *
	    * @Title: findAttrList
	    * @Description: 获取视频监控设备信息（设备位置以及设备属性）
	    * @param @return    参数
	    * @return List<MdmAttribute>    返回类型
	    * @throws
	 */
	public String findAttrList() {
		MdmDevicesUseInfo mdmDevicesUseInfo = new MdmDevicesUseInfo();
		mdmDevicesUseInfo.setComCode("bailu");
		mdmDevicesUseInfo.setDevicesCode("DEVICE-PROF-2018-00010");//实际传入的是专业编码
		List<MdmDevicesUseInfo> list = dao.findAttrList(mdmDevicesUseInfo);
		List<Map<String,String>> result = new ArrayList<>();
		for (MdmDevicesUseInfo mdmDevicesUseInfo2 : list) {
			if(StringUtils.isEmpty(mdmDevicesUseInfo2.getSpaceCode())) {
				continue;
			}
			Map<String,String> temp = new HashMap<>();
			temp.put("IP", mdmDevicesUseInfo2.getIp());
			temp.put("REMARKS", mdmDevicesUseInfo2.getRemarks()==null?"":mdmDevicesUseInfo2.getRemarks());
			Map<String,String> params = new HashMap<>();
			params.put("spaceCode", mdmDevicesUseInfo2.getSpaceCode());
			params.put("comCode", "bailu");
			List<MdmSpaceTree> spaceList = dao.findSpaceInfo(params);
			for (MdmSpaceTree mdmSpaceTree : spaceList) {
				temp.put(mdmSpaceTree.getNodeType(), mdmSpaceTree.getSpaceName());
				if("BUILD".equals(mdmSpaceTree.getNodeType())) {
					temp.put("AREA", mdmSpaceTree.getSpaceName().substring(0, 1));
				}
			}
			result.add(temp);
		}
		return JSON.toJSONString(result);
	}

	public MdmDevicesUseInfo getMdmDevicesUseInfo(MdmDevicesUseInfo mdmDevicesUseInfo){
		return dao.getDevicesByCode(mdmDevicesUseInfo);
	}

}
