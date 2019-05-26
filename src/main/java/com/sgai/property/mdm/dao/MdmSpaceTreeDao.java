package com.sgai.property.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.mdm.entity.MdmSpaceTree;

/**
 * 空间关系描述DAO接口
 * @author zhb
 * @version 2017-11-24
 */
@Mapper
public interface MdmSpaceTreeDao extends CrudDao<MdmSpaceTree> {
	List<Map<String,Object>> getSpaceFloorBuild(MdmSpaceTree mdmSpaceTree);
	List<Map<String,Object>> getSpaceList(MdmSpaceTree mdmSpaceTree);
	MdmSpaceTree getByCode(MdmSpaceTree mdmSpaceTree);
	List<MdmSpaceTree> getByCodeList(MdmSpaceTree mdmSpaceTree);
	List<MdmSpaceTree>  getByParentCode(MdmSpaceTree mdmSpaceTree);
	List<Map<String, Object>> getSpaceLack(LoginUser user);
	List<Map<String, Object>> getSpaceOwn(LoginUser user);
	void saveSpaceTree(Map<String, String> map);
	void deleteSpaceByUser(Map<String, String> param);
	List<Map<String, Object>> getUserProfLack(LoginUser user);
	List<Map<String, Object>> getUserProfOwn(LoginUser user);
	void saveProfTree(Map<String, String> map);
	void deleteProfByUser(Map<String, String> param);
	MdmSpaceTree getSpaceByCode(MdmSpaceTree mdmSpaceTree);
}