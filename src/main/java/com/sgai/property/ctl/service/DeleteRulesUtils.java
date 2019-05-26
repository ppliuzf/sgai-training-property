
    /**
    * @Title: DeleteRulesUtils.java
    * @Package com.sgai.common.utils
    * @Description: **删除规则校验**单元
    * @author guanze
    * @date 2017年11月20日
    * @Company 首自信--智慧城市创新中心
    * @version V1.0
    */

package com.sgai.property.ctl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlDelCheckDao;
import com.sgai.property.ctl.entity.CtlDelCheck;
import com.sgai.property.ctl.entity.CtlUser;

/**
    * @ClassName: DeleteRulesUtils
    * @Description: **数据删除前的规则校验**
    * @author guanze
    * @date 2017年11月20日
    * @Company 首自信--智慧城市创新中心
    */
@Service
@Transactional
public class DeleteRulesUtils extends CrudServiceExt<CtlDelCheckDao, CtlDelCheck> {

	@Autowired
	private CtlDelCheckDao ctlDelCheckDao;

	/*******公共方法，删除之前校验表关联关系************************************************************************************/
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = false)
	public Map<String,String> checkBeforeDelete(Class clazz,String id) {
		List<String> idList = new ArrayList<String>();
		idList.add(id);
		return checkBeforeDelete(clazz,idList);
	}

	/**
	 * checkBeforeDelete:删除表之前检查约束关系，**约束关系**来源Ctl_Del_Check
	 * @param oprTableName **指定删除数据**对应的表名
	 * @param idValue **指定删除数据**对应的唯一Id，对应表中的Id字段
	 * @return Map < String , String >
	 * @return1 ("value", true)("description", "该表的删除规则不存在，可以删除")
	 * @return2 ("value", false)("description", "与该表关联的表记录存在，不可删除。错误代码："+promptDesc)("relatTable", tableName)("id",id)
	 * @return3 ("value", true)("description", "与该表关联的表记录不存在，可以删除")("relatTable", tableName)
	 * @since JDK 1.8
	 * @author guanze
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = false)
	public Map<String,String> checkBeforeDelete(Class clazz,List<String> idList) {
		String oprTableName = getTableName(clazz);
		Map<String,String> resultMap = new HashMap<String,String>();
		if (!oprTableNameExist(oprTableName)) {
			resultMap.put("value", "true");
			resultMap.put("description", "该表的删除规则不存在，可以删除");
			return resultMap;
		}
		List<Map<String,String>> relatList = findRelatList(oprTableName);
		for(Map<String, String> li : relatList) {
			String tableName = li.get("tableName");
			String promptDesc = li.get("promptDesc");
			li.put("idList", idListToString(idList));
			String minId = tableRecordExist(li);
			if (!minId.equals("")) {
				resultMap.put("value", "false");
				resultMap.put("description", "与该表关联的表记录存在，不可删除。错误代码："+promptDesc+" id为："+minId);
				resultMap.put("relatTable", tableName);
				resultMap.put("id", minId);
				return resultMap;
			}
		}
		resultMap.put("value", "true");
		resultMap.put("description", "与该表关联的表记录不存在，可以删除");
		return resultMap;
	}


	    /**
	    * @Title: idListToString
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param idList
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	    */

	private String idListToString(List<String> idList) {
		String resultString = new String("\'"+idList.get(0)+"\'");
		for(int i=1;i<idList.size();i++) {
			resultString += ","+"\'"+idList.get(i)+"\'";
		}
        return resultString;
	}

	/**
	 * tableRecordExist:根据约束规则检查数据库表中数据是否存在
	 * @param relatList
	 * @return :boolean
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	private String tableRecordExist(Map<String,String> relatList) {
		String minId = ctlDelCheckDao.tableRecordExist(relatList);
		if (minId == null){
			return "";
		}
		return minId;
	}

	/**
	 * findRelatList:检查数据库表中约束规则
	 * @param oprTableName
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	private List<Map<String, String>> findRelatList(String oprTableName) {
		return ctlDelCheckDao.findRelatList(oprTableName);
	}

	/**
	 * oprTableNameExist:检查数据库表中约束规则是否存在
	 * @param oprTableName
	 * @return :boolean
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	private boolean oprTableNameExist(String oprTableName) {
		Map<String,String> mp = new HashMap<String,String>();
		mp.put("oprTableName", oprTableName);
		Integer ii = ctlDelCheckDao.oprTableNameExist(mp);
        return ii.intValue() != 0;
    }

	@SuppressWarnings("rawtypes")
	private static String getTableName(Class clazz) {
		StringBuffer name = new StringBuffer(clazz.getName());
		name.delete(0, name.lastIndexOf(".") + 1);
		for (int i = 1; i < name.length(); i++) {
			if (Character.isUpperCase(name.charAt(i))) {
				name.insert(i, "_");
				i++;
			}
		}

		return name.toString().toLowerCase();

	}

	/***********************************************************************************************************************/

	public void testdisplay() {
		List<String> idList= new ArrayList<String>();
		idList.add("a7567b10f6964d5197efba13e911082c");
		idList.add("df63bd6cd208499883214cac4d6c6fbb");
		idList.add("4028817b13492ca50113492f21330001");
		Map<String,String> resultMap = checkBeforeDelete(CtlUser.class,idList);
		System.out.println(resultMap.toString());

	}

	public static void main(String[] args) {
		System.out.println(getTableName(CtlDelCheck.class));

	}

}
