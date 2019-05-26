package com.sgai.property.wf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.utils.excel.ImportExcel;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.wf.dao.WfFlowDefineDao;
import com.sgai.property.wf.entity.WfFlowDefine;

/**
 *
    * ClassName: WfFlowDefineService
    * com.sgai.property.commonService.vo;(事件流程定义业务层)
    * @author yangyz
    * Date 2017年12月5日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class WfFlowDefineService extends CrudServiceExt<WfFlowDefineDao, WfFlowDefine> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	public WfFlowDefine get(String id) {
		return super.get(id);
	}

	public List<WfFlowDefine> findList(WfFlowDefine wfFlowDefine) {
		return super.findList(wfFlowDefine);
	}

	public Page<WfFlowDefine> findPage(Page<WfFlowDefine> page, WfFlowDefine wfFlowDefine) {
		return super.findPage(page, wfFlowDefine);
	}

	@Transactional(readOnly = false)
	public void save(WfFlowDefine wfFlowDefine) {
		super.save(wfFlowDefine);
	}

	@Transactional(readOnly = false)
	public void delete(WfFlowDefine wfFlowDefine) {
		super.delete(wfFlowDefine);
	}

	/**
	 *
	 * saveFlowDefine:(新增或者修改保存事件流程定义信息).
	 * @param wfFlowDefine
	 * @param oldStepCode
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveFlowDefine(WfFlowDefine wfFlowDefine){
		Map<String, Object> map = new HashMap<String, Object>();
		if (wfFlowDefine.getId()!=null && !"".equals(wfFlowDefine.getId())) {
			super.save(wfFlowDefine);
			map.put("msg", "success");
		}else {
			WfFlowDefine info = new WfFlowDefine();
			info.setStepCode(wfFlowDefine.getStepCode());
			List<WfFlowDefine> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				super.save(wfFlowDefine);
				map.put("msg", "success");
			}
		}

		return map;
	}

	/**
	 *
	 * deleteFlowDefine:(批量删除事件流程定义数据).
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author lenovo
	 */
	public Map<String, Object> deleteFlowDefine(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");
		List<WfFlowDefine> list = new ArrayList<WfFlowDefine>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				WfFlowDefine info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(WfFlowDefine.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<WfFlowDefine> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			}else {
				map.put("msg", "删除失败！");
			}
			map.put("result", "success");
		}else {
			map.put("msg", resultMap.get("description"));
			map.put("result", "fail");
		}
		return map;
	}

	/**
	 *
	 * importFlowDefines:(批量导入事件流程定义数据).
	 * @param filePath
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Map<String, Object> importFlowDefines(MultipartFile file) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException{
		Map<String, Object> map = new HashMap<String, Object>();
		ImportExcel ei = new ImportExcel(file, 1, 0);
		List<WfFlowDefine> list = ei.getDataList(WfFlowDefine.class);
		for (WfFlowDefine wfFlowDefine : list){
			String[] arr = (wfFlowDefine.getStepCode()).split("-");
			wfFlowDefine.setFlowCode(StringUtils.trimToEmpty(arr[0]));
			wfFlowDefine.setStepPos(StringUtils.trimToEmpty(arr[1]));
			wfFlowDefine.setStepSeq(StringUtils.trimToEmpty(arr[2]));
			wfFlowDefine.setIsFlag("Y");
			wfFlowDefine.setEnabledFlag("Y");
			wfFlowDefine.setStepLevel(1);
			wfFlowDefine.setRemarks("系统导入");
			super.save(wfFlowDefine);
		}
		map.put("msg", "success");
		return map;
	}

	public List<WfFlowDefine> findFlowList(String flowCode){
		WfFlowDefine define = new WfFlowDefine();
		define.setFlowCode(flowCode);
		define.preGet();
		return dao.findFlowList(define);
	}
}
