
/**    
* @Title: RepairRecordController.java  
* @Package com.sgai.property.wy.web
* (用一句话描述该文件做什么)
* @author XJ9001  
* @date 2018年1月24日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.RepairRecord;
import com.sgai.property.wy.service.RepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RepairRecordController
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年1月24日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping("/repairRecord")
public class RepairRecordController extends BaseController {

	@Autowired
	private RepairRecordService repairRecordService;

	@RequestMapping(value = "/getRecordLists", method = RequestMethod.POST)
	public CommonResponse getListRepairRecord(String repairId,

                                              @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                              @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize)
			throws IOException, ServletException {
		RepairRecord repairRecord = new RepairRecord();
		repairRecord.setRepairId(repairId);
		Page<RepairRecord> page = repairRecordService.findPage(
				new Page<RepairRecord>(pageNo, pageSize), repairRecord);
		return ResponseUtil.successResponse(page);
	}

	/**
	 * 
	 * save
	 * 
	 * @param RepairRecord
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CommonResponse save(RepairRecord repairRecord)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			repairRecordService.save(repairRecord);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	@RequestMapping(value = "/getRecordListTwo", method = RequestMethod.POST)
	public CommonResponse getRecordListTwo(String repairId,

                                           @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize)
			throws IOException, ServletException {
		RepairRecord repairRecord = new RepairRecord();
		repairRecord.setRepairId(repairId);
		Page<RepairRecord> page = repairRecordService.findPage(
				new Page<RepairRecord>(pageNo, pageSize), repairRecord);
		return ResponseUtil.successResponse(page);
	}
}
