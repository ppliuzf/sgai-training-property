
/**    
* @Title: CallDao.java  
* @Package com.sgai.property.wy.dao
* (用一句话描述该文件做什么)
* @author cui  
* @date 2018年1月29日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.CallCommon;
import com.sgai.property.wy.entity.CallInformation;
import com.sgai.property.wy.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: CallDao
 * (这里用一句话描述这个类的作用)
 * @author cui
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CallDao extends CrudDao<CallInformation> {

	/**
	 * @Title: batchDelete (这里用一句话描述这个方法的作用) @param @param
	 *         idList 参数 @return void 返回类型 @throws
	 */

	void batchDelete(List<String> idList);

	/**
	 * @Title: findName (模糊查询会员姓名) @param @return
	 *         参数 @return List<Member> 返回类型 @throws
	 */

	List<Member> findName(@Param("caller") String caller);

	/**
	 * @Title: findAppointList (查询指定人的信息)@param
	 *         call  @return 参数 @return List<CallCommon> 返回类型 @throws
	 */

	List<CallCommon> findAppointList(CallCommon call);

	/**
	 * @Title: findAddress (查询会员所属事件区域) @param @param
	 *         id @param @return 参数 @return List<Member> 返回类型 @throws
	 */

	List<Member> findAddressList(@Param("id") String id);

	  
	    /**  
	    * @Title: findAddress  
	    * (查询默认指定地址)
	    * @param @param id
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws  
	    */  
	    
	String findAddress(@Param("id") String id);
	
	
	   /**  
	    * @Title: serialNumbers  
	    * (流水号)  (此方法不能保证流水号唯一)
	    * 生成规则 年月日时(24小时)分秒 +5位随机数
	    * 如 2018020812224490414
	    * @return String    
	    * @throws  
	    */  
	static String serialNumbers(){
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return str + rannum;
	}

	  
	    /**  
	    * @Title: queryRole  
	    * (查询用户角色)
	    * @param @param userId
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws  
	    */  
	    
	List<String> queryRole(@Param("userId") String userId);

}
