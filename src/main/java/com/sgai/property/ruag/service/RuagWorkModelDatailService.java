package com.sgai.property.ruag.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmDeviceClassService;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import com.sgai.property.mdm.service.MdmDeviceProfService;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.mdm.service.MdmSpaceTreeService;
import com.sgai.property.ruag.dao.RuagWorkModelDatailDao;
import com.sgai.property.ruag.dto.RuagWorkModelDatailVo;
import com.sgai.property.ruag.entity.RuagModelCalendar;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;

/**
 *
    * @ClassName: RuagWorkModelDatailService
    * @com.sgai.property.commonService.vo;(设备运行策略配置Service)
    * @author 王天尧
    * @date 2018年1月3日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagWorkModelDatailService extends CrudServiceExt<RuagWorkModelDatailDao, RuagWorkModelDatail> {
	@Autowired
	private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;
	@Autowired
	private MdmSpaceTreeService mdmSpaceTreeService;
	@Autowired
	private MdmDeviceParameterService mdmDeviceParameterService;
	@Autowired
	private MdmDevicesUseInfoService  mdmDevicesUseInfoService;
	@Autowired
	private MdmDeviceClassService mdmDeviceClassService;
	@Autowired
	private RuagModelCalendarService ruagModelCalendarService;
	@Autowired
	private RuagModelTemplateService ruagModelTemplateService ;


	public List<RuagWorkModelDatail> findDevicesById(String id) {
		return dao.findDevicesById(id);
	}

	public List<RuagWorkModelDatail> getListByIds(RuagWorkModelDatail ruagWorkModelDatail) {
		return dao.getListByIds(ruagWorkModelDatail);
	}


	public List<RuagWorkModelDatail> getListGroupByProfCode(RuagWorkModelDatail ruagWorkModelDatail) {
		return dao.getListGroupByProfCode(ruagWorkModelDatail);
	}


	public RuagWorkModelDatail get(String id) {
		return super.get(id);
	}

	public List<RuagWorkModelDatail> findList(RuagWorkModelDatail ruagWorkModelDatail) {
		return super.findList(ruagWorkModelDatail);
	}

	public Page<RuagWorkModelDatail> findPage(Page<RuagWorkModelDatail> page, RuagWorkModelDatail ruagWorkModelDatail) {
		return super.findPage(page, ruagWorkModelDatail);
	}

	@Transactional(readOnly = false)
	public void save(RuagWorkModelDatail ruagWorkModelDatail) {
		super.save(ruagWorkModelDatail);
	}

	@Transactional(readOnly = false)
	public void delete(RuagWorkModelDatail ruagWorkModelDatail) {
		super.delete(ruagWorkModelDatail);
	}


	    /**
	    * @Title: getList
	    * @com.sgai.property.commonService.vo;(获取模式设置列表)
	    * @param @param workModel
	    * @param @param page
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	    */

	public Page<RuagWorkModelDatail> getList(RuagWorkModelDatail workModel, Page<RuagWorkModelDatail> page) {
		return findPage(page,workModel);
	}
	/**
	 *
	    * @Title: getAllList
	    * @com.sgai.property.commonService.vo;(根据运行策略id查找运行策略配置)
	    * @param @param modelTemplateId 运行策略id
	    * @param @return    参数
	    * @return List<RuagWorkModelDatail>    返回类型
	    * @throws
	 */
	public List<RuagWorkModelDatail> getAllList(String modelTemplateId) {
		RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
		ruagWorkModelDatail.setModelTemplateId(modelTemplateId);
		return dao.findAllOfList(ruagWorkModelDatail);
	}
	/**
	 *
	    * @Title: delete
	    * @com.sgai.property.commonService.vo;(删除运行策略设置)
	    * @param @param ids    运行策略id集合（表现为字符串，以逗号隔开）
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void delete(String ids){
		//将得到的ids拆分成数组
		String[]WorkModelids = ids.split(",");
		//遍历数组逐个删除
		for(String id:WorkModelids){
			if(id!=null&&!id.equals("")){
				RuagModelDeviceParamerSet ruagModelDeviceParamerSet = new RuagModelDeviceParamerSet();
				ruagModelDeviceParamerSet.setWorkModelId(id);
				ruagModelDeviceParamerSetService.deleteByModelId(ruagModelDeviceParamerSet);
				delete(get(id));

			}
		}
	}
	/**
	 *
	    * @Title: updateWorkModel
	    * @com.sgai.property.commonService.vo;(更新运行策略设置状态)
	    * @param @param status 运行策略设置状态
	    * @param @param id    运行策略id
	    * @return void    返回类型
	    * @throws
	 */
	public List<RuagWorkModelDatail> updateWorkModel(String status,String id) {
		RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
		ruagWorkModelDatail.setModelTemplateId(id);
		List<RuagWorkModelDatail> list = findList(ruagWorkModelDatail);
		if(list.size()!=0) {
			for (RuagWorkModelDatail workModelDatail : list) {
				workModelDatail.setStatus(status);
				save(workModelDatail);
			}
		}
		return list;
	}
	/**
	 *
	    * @Title: getWorkModelTree
	    * @com.sgai.property.commonService.vo;(构造空间设备树)
	    * @param @param id 运行策略id
	    * @param @return
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	 */
    public List<Map<String,Object>> getWorkModelTree(String id){
    	List<Map<String,Object>> result = Lists.newArrayList();
    	//获得空间树
    	//result=mdmSpaceTreeService.getSpaceList();
    	//构造设备树并放入空间树
    	RuagWorkModelDatail workModelDatail = new RuagWorkModelDatail();
    	workModelDatail.setModelTemplateId(ruagModelCalendarService.get(id).getModelId());
    	List<RuagWorkModelDatail> workModelDatils = findList(workModelDatail);

    	for (RuagWorkModelDatail workModel : workModelDatils) {
    		Map<String,Object> deviceMap = new HashMap<String,Object>();
    		deviceMap.put("id", workModel.getId());
    		deviceMap.put("pId", workModel.getClassCode()+workModel.getProfCode()+workModel.getSpaceCode());
    		deviceMap.put("name", workModel.getDeviceName());
    		result.add(deviceMap);
    		Map<String,Object> classMap = new HashMap<String,Object>();
    		classMap.put("id", workModel.getClassCode()+workModel.getProfCode()+workModel.getSpaceCode());
    		classMap.put("pId", workModel.getProfCode()+workModel.getSpaceCode());
    		classMap.put("name", workModel.getClassName());
    		result.add(classMap);
    		Map<String,Object> proMap = new HashMap<String,Object>();
    		proMap.put("id", workModel.getProfCode()+workModel.getSpaceCode());
    		proMap.put("pId", workModel.getSpaceCode());
    		proMap.put("name", workModel.getProfName());
    		result.add(proMap);
    		List<Map<String, Object>> spaceTreeList = spaceTree(workModel.getSpaceCode());
    		result.addAll(spaceTreeList);
		}
    	return quChong(result);
    }
    /**
     *
        * @Title: quChong
        * @com.sgai.property.commonService.vo;(list去重)
        * @param @param result list集合
        * @param @return    参数
        * @return List<Map<String,Object>>    返回类型
        * @throws
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String,Object>> quChong(List<Map<String,Object>> result){

         Set set = new  HashSet();
         List<Map<String,Object>> newList = new  ArrayList<Map<String,Object>>();
         set.addAll(result);
         newList.addAll(set);
         return newList;
     }
    /**
     *
        * @Title: findDatilByDay
        * @com.sgai.property.commonService.vo;(查询某个模式某一天已发指令的设备)
        * @param @param id 运行策略id
        * @param @param date 日期
        * @param @return    参数
        * @return List<RuagWorkModelDatail>    返回类型
        * @throws
     */
    public List<RuagWorkModelDatail> findDatilByDay(String id,String date){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("modelTemplateId", id);
    	params.put("date", date);
    	return dao.findDatilByDay(params);
    }
    /**
     *
        * @Title: getWorkmodelParameters
        * @com.sgai.property.commonService.vo;(查找某个运行策略配置下的参数设置)
        * @param @param id 运行策略配置id
        * @param @return    参数
        * @return List<RuagModelDeviceParamerSet>    返回类型
        * @throws
     */
    public List<RuagModelDeviceParamerSet> getWorkmodelParameters(String id){
    	RuagModelDeviceParamerSet ruagModelDeviceParamerSet = new RuagModelDeviceParamerSet();
    	ruagModelDeviceParamerSet.setWorkModelId(id);
    	return ruagModelDeviceParamerSetService.findList(ruagModelDeviceParamerSet);
    }
    /**
     *
        * @Title: getParametersOfEqmBelongOneType
        * @com.sgai.property.commonService.vo;(查找某个设备类型的参数)
        * @param @param classCode 设备类型代码
        * @param @return    参数
        * @return List<MdmDeviceParameter>    返回类型
        * @throws
     */
    public List<MdmDeviceParameter> getParametersOfEqmBelongOneType(String  deviceCodes) {
    	String[] split = deviceCodes.split(",");
    	MdmDevicesUseInfo mdmDevicesUseInfo = new MdmDevicesUseInfo();
    	mdmDevicesUseInfo.setDevicesCode(split[0]);
    	List<MdmDevicesUseInfo> findList = mdmDevicesUseInfoService.findList(mdmDevicesUseInfo);
    	MdmDeviceParameter mdmDeviceParameter = new  MdmDeviceParameter();
    	mdmDeviceParameter.setDeviceClassCode(findList.get(0).getClassCode());
    	return mdmDeviceParameterService.findList(mdmDeviceParameter);
    }
    /**
     *
        * @Title: getParametersByClassCode
        * @com.sgai.property.commonService.vo;(查找同一设备类型的参数)
        * @param @param classCode
        * @param @return    参数
        * @return List<MdmDeviceParameter>    返回类型
        * @throws
     */
    public List<MdmDeviceParameter> getParametersByClassCode(String  classCode) {
    	MdmDeviceParameter mdmDeviceParameter = new  MdmDeviceParameter();
    	mdmDeviceParameter.setDeviceClassCode(classCode);
    	return mdmDeviceParameterService.findList(mdmDeviceParameter);
    }
    /**
     * @return
     *
        * @Title: setAndSaveModels
        * @com.sgai.property.commonService.vo;(保存运行策略配置参数)
        * @param @param deviceCodes 设备代码（字符串形式，以逗号隔开）
        * @param @param onOffParameterCode （开关状态代码）
        * @param @param openTime 开启时间
        * @param @param closeTime 关闭时间
        * @param @param paramerSets
        * @param @param ruagWorkModelDatail    参数
        * @return void    返回类型
        * @throws
     */
    @SuppressWarnings("null")
	@Transactional
    public Map<String, String> setAndSaveModels(
    		String deviceCodes,String onOffParameterCode,String onOffParameterName,
    		String openTime,String closeTime,
    		String parameterNames,
			String parameterIds,
			String parameterValues,
			String timePoints,
    		RuagWorkModelDatail ruagWorkModelDatail
    		) {
    	    Map<String,String> result = new HashMap<String,String>();
    		RuagModelTemplate ruagModelTemplate = ruagModelTemplateService.get(ruagWorkModelDatail.getModelTemplateId());
    		String[] deviceArr = deviceCodes.split(",");
    		List<String> deviceList = new ArrayList<String>(Arrays.asList(deviceArr));
    		//判断是否有冲突
    		List<Map<String, List<String>>> checkConflict = new ArrayList<Map<String, List<String>>>();
    		if(ruagModelTemplate.getControlCode().equals("date")) {
    			checkConflict = ruagModelTemplateService.checkConflict(ruagModelTemplate.getId(), ruagModelTemplate.getStartDate(), ruagModelTemplate.getEndDate(), deviceList);
    		}
    		//如果返回结果不为空证明有冲突
    		String message="";
    		if(checkConflict.size()>0) {
    			for (Map<String, List<String>> map : checkConflict) {
    				String modeName="";
    				String conflictDeviceCodes="";
  			      for (String key : map.keySet())
  				  {
  			    	  modeName=key;
  				  }
  				  //遍历map中的值
  				  for (List<String> value : map.values())
  				  {
  					  conflictDeviceCodes+=value;
  				  }
  				message+= "策略名称-"+modeName+":"+"设备编码-"+conflictDeviceCodes+";";
				}

    		  result.put("msg", "conflict");
    		  result.put("message", message);
    		}else {
    			List<RuagModelDeviceParamerSet> list = new ArrayList<RuagModelDeviceParamerSet>();
            	//取得设置不为空的参数，添加该参数
            	if((!parameterNames.equals(""))&&(!parameterIds.equals(""))&&(!parameterValues.equals(""))&&(!timePoints.equals(""))) {
            		String[] names = parameterNames.split(",");
                	String[] codes = parameterIds.split(",");
                	String[] values = parameterValues.split(",");
                	String[] points = timePoints.split(",");
                	if(ruagModelTemplate.getControlCode().equals("time")) {
                	for(int i=0;i<names.length;i++) {
                		RuagModelDeviceParamerSet set = new RuagModelDeviceParamerSet();
                		set.setParameterId(codes[i]);
                		set.setParameterName(names[i]);
                		set.setParameterValue(values[i]);
                		set.setTimePoint("-"+points[i]);
                		list.add(set);

                	}
                	}else {
                		for(int i=0;i<names.length;i++) {
                    		RuagModelDeviceParamerSet set = new RuagModelDeviceParamerSet();
                    		set.setParameterId(codes[i]);
                    		set.setParameterName(names[i]);
                    		set.setParameterValue(values[i]);
                    		set.setTimePoint(points[i]);
                    		list.add(set);

                    	}
                	}
            	}

            	//页面设置了开启时间   添加开启参数
        		if(openTime!=null && openTime!=""){
        			RuagModelDeviceParamerSet open = new RuagModelDeviceParamerSet();
        			open.setParameterName(onOffParameterName);
        			open.setParameterId(onOffParameterCode);
        			if(ruagModelTemplate.getControlCode().equals("time")) {
        				open.setTimePoint("-"+openTime);
        			}else {
        				open.setTimePoint(openTime);
        			}
        			open.setParameterValue("1");
        			list.add(open);
        		}
        		//页面设置了关闭时间 添加关闭参数
        		if(closeTime!=null && closeTime!=""){
        			RuagModelDeviceParamerSet close = new RuagModelDeviceParamerSet();
        			close.setParameterName(onOffParameterName);
        			close.setParameterId(onOffParameterCode);
        			if(ruagModelTemplate.getControlCode().equals("time")) {
        				close.setTimePoint("+"+closeTime);
        			}else {
        				close.setTimePoint(closeTime);
        			}
        			close.setParameterValue("0");
        			list.add(close);
        		}
        		//保存设置的参数
        		for (String deviceCode : deviceArr) {
        			MdmDevicesUseInfo device = getDevice(deviceCode);
        			MdmDeviceClass deveiceClass = getDeviceClass(device.getClassCode());
        			//进行运行策略配置
        			RuagWorkModelDatail ruagWorkModelDatailNew = new RuagWorkModelDatail();
        			ruagWorkModelDatailNew.setModelTemplateId(ruagWorkModelDatail.getModelTemplateId());
        			ruagWorkModelDatailNew.setWorkModeName(ruagWorkModelDatail.getWorkModeName());
        			ruagWorkModelDatailNew.setClassCode(device.getClassCode());
        			ruagWorkModelDatailNew.setClassName(device.getClassName());
        			ruagWorkModelDatailNew.setDeviceCode(device.getDevicesCode());
        			ruagWorkModelDatailNew.setDeviceName(device.getDevicesName());
        			ruagWorkModelDatailNew.setSpaceCode(device.getSpaceCode());
        			ruagWorkModelDatailNew.setSpaceName(device.getSpaceName());
        			ruagWorkModelDatailNew.setProfCode(deveiceClass.getProfCode());
        			ruagWorkModelDatailNew.setProfName(deveiceClass.getProfName());
        			ruagWorkModelDatailNew.setRemarks(ruagWorkModelDatail.getRemarks());
        			save(ruagWorkModelDatailNew);
        			for (RuagModelDeviceParamerSet paramerSet : list) {
        				RuagModelDeviceParamerSet paramerSetNew = new RuagModelDeviceParamerSet();
        				paramerSetNew.setDeviceCode(paramerSet.getDeviceCode());
        				paramerSetNew.setParameterId(paramerSet.getParameterId());
        				paramerSetNew.setParameterName(paramerSet.getParameterName());
        				paramerSetNew.setParameterValue(paramerSet.getParameterValue());
        				paramerSetNew.setTimePoint(paramerSet.getTimePoint());
        				paramerSetNew.setWorkModelId(ruagWorkModelDatailNew.getId());
        				paramerSetNew.setDeviceCode(deviceCode);
        				paramerSetNew.setEnabledFlag("Y");
        				ruagModelDeviceParamerSetService.save(paramerSetNew);

        			}
        		}
        		result.put("msg", "success");
    		}

		return result;
	}
    /**
     *
        * @Title: getDevice
        * @com.sgai.property.commonService.vo;(查找设备)
        * @param @param deviceCode 设备代码
        * @param @return    参数
        * @return MdmDevicesUseInfo    返回类型
        * @throws
     */
    public MdmDevicesUseInfo getDevice(String deviceCode) {
    	MdmDevicesUseInfo mdmDevicesUseInfo = new MdmDevicesUseInfo();
		mdmDevicesUseInfo.setDevicesCode(deviceCode);
		List<MdmDevicesUseInfo> findList = mdmDevicesUseInfoService.findList(mdmDevicesUseInfo);
		return findList.get(0);
    }
    /**
     *
        * @Title: getDeviceClass
        * @com.sgai.property.commonService.vo;(查找设备类型)
        * @param @param classCode 设备类型代码
        * @param @return    参数
        * @return MdmDeviceClass    返回类型
        * @throws
     */
    public 	MdmDeviceClass getDeviceClass(String classCode) {
    	MdmDeviceClass mdmDeviceClass = new MdmDeviceClass();
		mdmDeviceClass.setClassCode(classCode);
		List<MdmDeviceClass> findList2 = mdmDeviceClassService.findList(mdmDeviceClass);
		return findList2.get(0);
    }
    /**
     *
     * getOwnModels:(查找某个运行策略某一天的设备信息).
     * @param id 策略日程id
     * @return :List<RuagWorkModelDatail>
     * @since JDK 1.8
     * @author 王天尧
     */
   public List<RuagWorkModelDatail> getOwnModels(String id){
	   LoginUser user = UserServletContext.getUserInfo();
	   List<RuagWorkModelDatail> result = new ArrayList<RuagWorkModelDatail>();
	   Map<String,String> params = new HashMap<String,String>();
	   RuagModelCalendar ruagModelCalendar = ruagModelCalendarService.get(id);
	   if(ruagModelCalendar.getChangeFlag().equals("N")) {
		   RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
		   ruagWorkModelDatail.setModelTemplateId(ruagModelCalendar.getModelId());
		   result=findList(ruagWorkModelDatail);
	   }else {
		   params.put("modelCalendarId", id);
		   params.put("comCode", user.getComCode());
		   params.put("moduCode", user.getModuCode());
		   result=dao.findOwnModels(params);
	   }
	   return result;
   }
   /**
    *
       * @Title: updateModels
       * @com.sgai.property.commonService.vo;(更新运行策略设置的参数)
       * @param @param ids
       * @param @param onOffParameterCode
       * @param @param onOffParameterName
       * @param @param openTime
       * @param @param closeTime
       * @param @param parameterNames
       * @param @param parameterIds
       * @param @param parameterValues
       * @param @param timePoints
       * @param @param ruagWorkModelDatail    参数
       * @return void    返回类型
       * @throws
    */
   public void updateModels(
   		String modelIds,String onOffParameterCode,String onOffParameterName,
   		String openTime,String closeTime,
   		String parameterNames,
			String parameterIds,
			String parameterValues,
			String timePoints,
   		RuagWorkModelDatail ruagWorkModelDatail
   		) {
	   RuagModelTemplate ruagModelTemplate = ruagModelTemplateService.get(ruagWorkModelDatail.getModelTemplateId());
   	String[] workModelArr = modelIds.split(",");
   	List<RuagModelDeviceParamerSet> list = new ArrayList<RuagModelDeviceParamerSet>();
   	//取得设置不为空的参数，添加该参数
   	if((!parameterNames.equals(""))&&(!parameterIds.equals(""))&&(!parameterValues.equals(""))&&(!timePoints.equals(""))) {
   		String[] names = parameterNames.split(",");
       	String[] codes = parameterIds.split(",");
       	String[] values = parameterValues.split(",");
       	String[] points = timePoints.split(",");
       	if(ruagModelTemplate.getControlCode().equals("time")) {
       		for(int i=0;i<names.length;i++) {
           		RuagModelDeviceParamerSet set = new RuagModelDeviceParamerSet();
           		set.setParameterId(codes[i]);
           		set.setParameterName(names[i]);
           		set.setParameterValue(values[i]);
           		set.setTimePoint("-"+points[i]);
           		list.add(set);

           	}
       	}else {
       		for(int i=0;i<names.length;i++) {
           		RuagModelDeviceParamerSet set = new RuagModelDeviceParamerSet();
           		set.setParameterId(codes[i]);
           		set.setParameterName(names[i]);
           		set.setParameterValue(values[i]);
           		set.setTimePoint(points[i]);
           		list.add(set);

           	}
       	}

   	}

   	//页面设置了开启时间   添加开启参数
		if(openTime!=null && openTime!=""){
			RuagModelDeviceParamerSet open = new RuagModelDeviceParamerSet();
			open.setParameterName(onOffParameterName);
			open.setParameterId(onOffParameterCode);
			if(ruagModelTemplate.getControlCode().equals("time")) {
				open.setTimePoint("-"+openTime);
			}else {
				open.setTimePoint(openTime);
			}
			open.setParameterValue("1");
			list.add(open);
		}
		//页面设置了关闭时间 添加关闭参数
		if(closeTime!=null && closeTime!=""){
			RuagModelDeviceParamerSet close = new RuagModelDeviceParamerSet();
			close.setParameterName(onOffParameterName);
			close.setParameterId(onOffParameterCode);
			if(ruagModelTemplate.getControlCode().equals("time")) {
				close.setTimePoint("+"+closeTime);
			}else {
				close.setTimePoint(closeTime);
			}
			close.setParameterValue("0");
			list.add(close);
		}
		//保存设置的参数
		for (String workModelId : workModelArr) {
			RuagModelDeviceParamerSet paramerSetNew = new RuagModelDeviceParamerSet();
			paramerSetNew.setWorkModelId(workModelId);
			List<RuagModelDeviceParamerSet> findList = ruagModelDeviceParamerSetService.findList(paramerSetNew);
			for (RuagModelDeviceParamerSet ruagModelDeviceParamerSet : findList) {
				ruagModelDeviceParamerSetService.delete(ruagModelDeviceParamerSet);
			}
			//进行运行策略配置
			for (RuagModelDeviceParamerSet paramerSet : list) {
				RuagModelDeviceParamerSet paramerSetTWo = new RuagModelDeviceParamerSet();
				paramerSetTWo.setDeviceCode(paramerSet.getDeviceCode());
				paramerSetTWo.setParameterId(paramerSet.getParameterId());
				paramerSetTWo.setParameterName(paramerSet.getParameterName());
				paramerSetTWo.setParameterValue(paramerSet.getParameterValue());
				paramerSetTWo.setTimePoint(paramerSet.getTimePoint());
				paramerSetTWo.setWorkModelId(workModelId);
				paramerSetTWo.setEnabledFlag("Y");
				ruagModelDeviceParamerSetService.save(paramerSetTWo);
			}
		}

   }
   /**
    *
       * @Title: spaceTree
       * @com.sgai.property.commonService.vo;(获得设备所关联的位置)
       * @param @param spaceCode
       * @param @return    参数
       * @return List<MdmSpaceTree>    返回类型
       * @throws
    */
   public List<Map<String,Object>> spaceTree(String spaceCode){
	   List<Map<String,Object>> result = Lists.newArrayList();
	   while(!spaceCode.equals("1111")) {
		   Map<String,Object> spaceMap = new HashMap<String,Object>();
		   MdmSpaceTree mdmSpaceTree  = new MdmSpaceTree();
		   mdmSpaceTree.setSpaceCode(spaceCode);
		   List<MdmSpaceTree> findList = mdmSpaceTreeService.findList(mdmSpaceTree);
		   spaceMap.put("id", findList.get(0).getSpaceCode());
		   spaceMap.put("name", findList.get(0).getSpaceName());
		   spaceMap.put("pId", findList.get(0).getParentCode());
		   result.add(spaceMap);
		   spaceCode=findList.get(0).getParentCode();
	   }
	   return result;
   }
}
