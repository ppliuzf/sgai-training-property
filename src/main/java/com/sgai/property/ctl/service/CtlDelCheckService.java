package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlDelCheckDao;
import com.sgai.property.ctl.entity.CtlDelCheck;

    /**
    * @ClassName: CtlDelCheckService
    * @Description: 对象删除规则定义Service
    * @author guanze
    * @date 2017年11月18日
    * @Company 首自信--智慧城市创新中心
    */
@Service
@Transactional
public class CtlDelCheckService extends CrudServiceExt<CtlDelCheckDao, CtlDelCheck> {

	@Autowired
	private CtlDelCheckDao ctlDelCheckDao;

	public CtlDelCheck get(String id) {
		return super.get(id);
	}

	public List<CtlDelCheck> findList(CtlDelCheck ctlDelCheck) {
		return super.findList(ctlDelCheck);
	}

	public Page<CtlDelCheck> findPage(Page<CtlDelCheck> page, CtlDelCheck ctlDelCheck) {
		// 设置分页参数
		ctlDelCheck.setPage(page);
		// 执行分页查询
		page.setList(ctlDelCheckDao.findList(ctlDelCheck));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(CtlDelCheck ctlDelCheck) {
		super.save(ctlDelCheck);
	}

	@Transactional(readOnly = false)
	public void delete(CtlDelCheck ctlDelCheck) {
		super.delete(ctlDelCheck);
	}


	@Transactional(readOnly = false)
	public List<CtlDelCheck> batchDelete(List<CtlDelCheck> objs) {
		return super.batchDelete(objs);
	}

	/**
	 * checkDelCheck:检查数据库表中是否存在数据.
	 * @param ctlDelCheck
	 * @return :List<CtlDelCheck>
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	public List<CtlDelCheck> checkDelCheck(CtlDelCheck ctlDelCheck) {
		return super.dao.checkDelCheck(ctlDelCheck);
	}

	/*******公共方法，删除之前校验表关联关系************************************************************************************/
	/**
	 * checkBeforeDelete:删除表之前检查约束关系
	 * @param oprTableName 被删除数据表名
	 * @param idValue 被删除数据唯一ID
	 * @return :Map<String,Object>
	 * @return1 ("canDelete", true)("description", "该表的删除规则不存在，可以删除")
	 * @return2 ("canDelete", false)("description", "与该表关联的表记录存在，不可删除。错误代码："+promptDesc)("relatTable", tableName)
	 * @return3 ("canDelete", true)("description", "与该表关联的表记录不存在，可以删除")("relatTable", tableName)
	 * @since JDK 1.8
	 * @author guanze
	 */
//	@Transactional(readOnly = false)
//	public Map<String,Object> checkBeforeDelete(String oprTableName,String idValue) {
//		Map<String,Object> cbdMap = new HashMap<String,Object>();
//		String oprTable = oprTableName;
//		if (!oprTableNameExist(oprTable)) {
//			cbdMap.put("canDelete", true);
//			cbdMap.put("description", "该表的删除规则不存在，可以删除");
//			return cbdMap;
//		}
//
//		Map<String,String> relatList = this.findRelatList(oprTable);
//		String tableName = relatList.get("tableName");
//		//String columnName = relatList.get("columnName");
//		String promptDesc = relatList.get("promptDesc");
//		relatList.put("id", idValue);
//
//		if (this.tableRecordExist(relatList)) {
//			cbdMap.put("canDelete", false);
//			cbdMap.put("description", "与该表关联的表记录存在，不可删除。错误代码："+promptDesc);
//			cbdMap.put("relatTable", tableName);
//			return cbdMap;
//		} else {
//			cbdMap.put("canDelete", true);
//			cbdMap.put("description", "与该表关联的表记录不存在，可以删除");
//			cbdMap.put("relatTable", tableName);
//			return cbdMap;
//		}
//	}

	/**
	 * tableRecordExist:根据约束规则检查数据库表中数据是否存在
	 * @param relatList
	 * @return :boolean
	 * @since JDK 1.8
	 * @author guanze
	 */
//	@Transactional(readOnly = false)
//	private boolean tableRecordExist(Map<String,String> relatList) {
//		Integer ii = ctlDelCheckDao.tableRecordExist(relatList);
//		if (ii.intValue() != 0){
//			return true;
//		}
//		return false;
//	}

	/**
	 * findRelatList:检查数据库表中约束规则
	 * @param oprTable
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author guanze
	 */
//	@Transactional(readOnly = false)
//	private Map<String, String> findRelatList(String oprTable) {
//		return ctlDelCheckDao.findRelatList(oprTable);
//	}

	/**
	 * oprTableNameExist:检查数据库表中约束规则是否存在
	 * @param oprTable
	 * @return :boolean
	 * @since JDK 1.8
	 * @author guanze
	 */
//	@Transactional(readOnly = false)
//	private boolean oprTableNameExist(String oprTable) {
//		System.out.println("**********"+oprTable+"********");
//		Map<String,String> mp = new HashMap<String,String>();
//		mp.put("oprTable", oprTable);
//		System.out.println("**********"+mp+"********");
//
//		Integer ii = ctlDelCheckDao.oprTableNameExist(mp);
//		if (ii.intValue() != 0){
//			return true;
//		}
//		return false;
//	}

	/***********************************************************************************************************************/

}
