package com.sgai.property.mdm.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.mdm.dao.MdmSpaceInfoDao;
import com.sgai.property.mdm.dao.MdmSpaceTreeDao;
import com.sgai.property.mdm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 空间关系描述Service
 *
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmSpaceTreeService extends CrudServiceExt<MdmSpaceTreeDao, MdmSpaceTree> {

    @Autowired
    private MdmSpaceInfoDao mdmSpaceInfoDao;
    @Autowired
    private MdmParkInfoService mdmParkInfoService;
    @Autowired
    private MdmAreaStructService mdmAreaStructService;
    @Autowired
    private MdmBuildInfoService mdmBuildInfoService;
    @Autowired
    private MdmFloorInfoService mdmFloorInfoService;
    @Autowired
    private MdmRoomInfoService mdmRoomInfoService;

    public MdmSpaceTree get(String id) {
        return super.get(id);
    }

    public MdmSpaceTree getByCode(MdmSpaceTree mdmSpaceTree) {
        mdmSpaceTree.preGet();
        return dao.getByCode(mdmSpaceTree);
    }

    public List<MdmSpaceTree> getByCodeList(MdmSpaceTree mdmSpaceTree) {
        return dao.getByCodeList(mdmSpaceTree);
    }

    public List<MdmSpaceTree> getByParentCode(MdmSpaceTree mdmSpaceTree) {
        return dao.getByParentCode(mdmSpaceTree);
    }

    public List<MdmSpaceTree> findList(MdmSpaceTree mdmSpaceTree) {
        return super.findList(mdmSpaceTree);
    }

    public Page<MdmSpaceTree> findPage(Page<MdmSpaceTree> page, MdmSpaceTree mdmSpaceTree) {
        return super.findPage(page, mdmSpaceTree);
    }

    @Transactional(readOnly = false)
    public void save(MdmSpaceTree mdmSpaceTree) {
        super.save(mdmSpaceTree);
    }

    @Transactional(readOnly = false)
    public void delete(MdmSpaceTree mdmSpaceTree) {
        super.delete(mdmSpaceTree);
    }


    /**
     * getSpaceList:(返回树形结构的数据类型).
     *
     * @return :List<Map<String,Object>>
     * @author admin
     * @since JDK 1.8
     */
    public List<SpaceDto> getSpaceList(MdmSpaceTree mdmSpaceTree) {
        List<SpaceDto> result = Lists.newArrayList();
        List<Map<String, Object>> list = dao.getSpaceList(mdmSpaceTree);

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                SpaceDto spaceDto = new SpaceDto();
                spaceDto.setId((String) map.get("NODE_CODE"));
                spaceDto.setpId((String) map.get("PARENT_CODE"));
                spaceDto.setName((String) map.get("NODE_NAME"));
                result.add(spaceDto);
            }
        } else {
            MdmSpaceTree mdmSpac = new MdmSpaceTree();
            mdmSpac.setSpaceCode("0");
            mdmSpac.setSpaceName("空间");
            mdmSpac.setNodeType("ROOT");
            mdmSpac.setEnabledFlag('Y');
            this.save(mdmSpac);
            SpaceDto spaceDto = new SpaceDto();
            spaceDto.setId(mdmSpac.getSpaceCode());
            spaceDto.setpId(mdmSpac.getParentCode());
            spaceDto.setId(mdmSpac.getSpaceName());
            result.add(spaceDto);
        }

        return result;
    }

    /**
     * getSpaceList:(返回树形结构的数据类型).
     *
     * @return :List<Map<String,Object>>
     * @author admin
     * @since JDK 1.8
     */
    public List<Map<String, Object>> getSpaceFloorBuild(MdmSpaceTree mdmSpaceTree) {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<Map<String, Object>> list = dao.getSpaceFloorBuild(mdmSpaceTree);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Map<String, Object> newMap = Maps.newHashMap();
                newMap.put("id", map.get("NODE_CODE"));
                newMap.put("pId", map.get("PARENT_CODE"));
                newMap.put("name", map.get("NODE_NAME"));
                result.add(newMap);
            }
        } else {
            MdmSpaceTree mdmSpac = new MdmSpaceTree();
            mdmSpac.setSpaceCode("0");
            mdmSpac.setSpaceName("空间");
            mdmSpac.setNodeType("ROOT");
            mdmSpac.setEnabledFlag('Y');
            this.save(mdmSpac);
            Map<String, Object> newMap = Maps.newHashMap();
            newMap.put("id", mdmSpac.getSpaceCode());
            newMap.put("pId", mdmSpac.getParentCode());
            newMap.put("name", mdmSpac.getSpaceName());
            result.add(newMap);
        }

        return result;
    }


    /**
     * getSpaceInfo:(返回对应类型的空间信息).
     *
     * @param nodeType 空间类型
     * @param arg0     空间code
     * @return :MdmSpaceInfo
     * @author admin
     * @since JDK 1.8
     */
    public MdmSpaceInfo getSpaceInfo(String nodeType, String arg0) {
        LoginUser user = UserServletContext.getUserInfo();
        String comCode = user.getComCode();
        if ("PARK".equals(nodeType))
            return mdmSpaceInfoDao.getParkInfo(arg0, comCode);
        else if ("AREA".equals(nodeType))
            return mdmSpaceInfoDao.getAreaInfo(arg0, comCode);
        else if ("BUILD".equals(nodeType))
            return mdmSpaceInfoDao.getBuildInfo(arg0, comCode);
        else if ("FLOOR".equals(nodeType))
            return mdmSpaceInfoDao.getFloorInfo(arg0, comCode);
        else if ("ROOM".equals(nodeType))
            return mdmSpaceInfoDao.getRoomInfo(arg0, comCode);
        else return null;
    }

    /**
     * saveSpaceInfo:(按类型保存空间信息).
     *
     * @param mdmSpaceInfo :void
     * @author admin
     * @since JDK 1.8
     */
    @SuppressWarnings("null")
    @Transactional(readOnly = false)
    public Map<String, String> saveSpaceInfo(MdmSpaceInfo mdmSpaceInfo, Map<String, String> map) {
        String code = "";
        MdmSpaceTree mdmSpacs = new MdmSpaceTree();
        mdmSpacs.setSpaceCode(mdmSpaceInfo.getSpaceNodeCode());
        mdmSpacs.setComCode(mdmSpaceInfo.getComCode());
        mdmSpacs.setModuCode(mdmSpaceInfo.getModuCode());
        MdmSpaceTree mdmSpaceTree = this.getByCode(mdmSpacs);
        if (mdmSpaceTree == null || (!"".equals(mdmSpaceInfo.getId()) && mdmSpaceInfo.getId() != null)) {
            if ("PARK".equals(mdmSpaceInfo.getSpaceNodeType()))
                code = mdmParkInfoService.saveParkInfo(mdmSpaceInfo);
            else if ("AREA".equals(mdmSpaceInfo.getSpaceNodeType()))
                code = mdmAreaStructService.saveAreaInfo(mdmSpaceInfo);
            else if ("BUILD".equals(mdmSpaceInfo.getSpaceNodeType()))
                code = mdmBuildInfoService.saveBuildInfo(mdmSpaceInfo);
            else if ("FLOOR".equals(mdmSpaceInfo.getSpaceNodeType()))
                code = mdmFloorInfoService.saveFloorInfo(mdmSpaceInfo);
            else if ("ROOM".equals(mdmSpaceInfo.getSpaceNodeType()))
                code = mdmRoomInfoService.saveRoomInfo(mdmSpaceInfo);
        }
        if (mdmSpaceInfo.getId() != null && !"".equals(mdmSpaceInfo.getId())) {
            mdmSpaceTree.setSpaceName(mdmSpaceInfo.getSpaceNodeName());
            this.save(mdmSpaceTree);
            map.put("msg", "successupdate");
        } else {
            if (mdmSpaceTree != null) {
                map.put("msg", "repeat");
            } else {
                MdmSpaceTree mdmSpac = new MdmSpaceTree();
                mdmSpac.setSpaceCode(code);
                mdmSpac.setSpaceName(mdmSpaceInfo.getSpaceNodeName());
                mdmSpac.setParentCode(mdmSpaceInfo.getSpaceParentNodeCode());
                mdmSpac.setNodeType(mdmSpaceInfo.getSpaceNodeType());
                mdmSpac.setEnabledFlag('Y');
                this.save(mdmSpac);
                map.put("msg", "success");
            }

        }
        return map;
    }

    /**
     * deleteNode:(删除结点的内部方法。按照类型删除).
     *
     * @param mdmSpaceTree :void
     * @author admin
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public void deleteNode(MdmSpaceTree mdmSpaceTree) {

        if ("PARK".equals(mdmSpaceTree.getNodeType())) {
            MdmParkInfo park = new MdmParkInfo();
            park.setParkCode(mdmSpaceTree.getSpaceCode());
            mdmParkInfoService.delete(park);
        } else if ("AREA".equals(mdmSpaceTree.getNodeType())) {
            MdmAreaStruct area = new MdmAreaStruct();
            area.setAreaCode(mdmSpaceTree.getSpaceCode());
            mdmAreaStructService.delete(area);
        } else if ("BUILD".equals(mdmSpaceTree.getNodeType())) {
            MdmBuildInfo build = new MdmBuildInfo();
            build.setBuildingCode(mdmSpaceTree.getSpaceCode());
            mdmBuildInfoService.delete(build);
        } else if ("FLOOR".equals(mdmSpaceTree.getNodeType())) {
            MdmFloorInfo floor = new MdmFloorInfo();
            floor.setFloorCode(mdmSpaceTree.getSpaceCode());
            mdmFloorInfoService.delete(floor);
        } else if ("ROOM".equals(mdmSpaceTree.getNodeType())) {
            MdmRoomInfo room = new MdmRoomInfo();
            room.setRoomCode(mdmSpaceTree.getSpaceCode());
            mdmRoomInfoService.delete(room);
        }

    }


    /**
     * deleteNew:(删除结点的入口方法).
     *
     * @param nodeCode :void
     * @author admin
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public void deleteNew(String nodeCode, String comCode, String moduCode) {
        MdmSpaceTree mdmSpace = new MdmSpaceTree();
        mdmSpace.setSpaceCode(nodeCode);
        mdmSpace.setComCode(comCode);
        mdmSpace.setModuCode(moduCode);
        List<MdmSpaceTree> mdmSpaceTree = this.getByCodeList(mdmSpace);
        mdmSpace = mdmSpaceTree.get(0);
        this.deleteNode(mdmSpace);
        this.delete(mdmSpace);


        MdmSpaceTree spaceTree = new MdmSpaceTree();
        spaceTree.setSpaceCode(nodeCode);
        spaceTree.setComCode(comCode);
        spaceTree.setModuCode(moduCode);
        List<MdmSpaceTree> listm = this.getByParentCode(spaceTree);
        if (listm.size() > 0) {
            for (int i = 0; i < listm.size(); i++) {
                deleteNew(listm.get(i).getSpaceCode(), comCode, moduCode);
            }
        }
    }


    /**
     * @param @param  mdmSpaceTree
     * @param @return 参数
     * @return List<Map   <   String   ,   Object>>    返回类型
     * @throws
     * @Title: getUserSpaceLack
     * @Description:
     */

    public List<Map<String, Object>> getUserSpaceLack(LoginUser user) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> spaceList = dao.getSpaceLack(user);
        List<Map<String, Object>> ownList = dao.getSpaceOwn(user);
        Set<String> lackSet = new HashSet<>();
        for (Map<String, Object> allMap : spaceList) {//全部节code
            lackSet.add((String) allMap.get("NODE_CODE"));
        }
        for (Map<String, Object> ownMap : ownList) {
            String nodeCode = (String) ownMap.get("NODE_CODE");
            lackSet.remove(nodeCode);//删除右侧树节点的code
        }
        //(lackSet, spaceList); //添加左侧树节点的父节点

        if (spaceList != null && spaceList.size() > 0) {//封装数据返回
            for (Map<String, Object> map : spaceList) {
                if (lackSet.contains(map.get("NODE_CODE"))) {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("id", map.get("NODE_CODE"));
                    newMap.put("pId", map.get("PARENT_CODE"));
                    newMap.put("name", map.get("NODE_NAME"));
                    result.add(newMap);
                }
            }
        } else {
            MdmSpaceTree spaceTree = new MdmSpaceTree();
            spaceTree.setSpaceCode("0");
            spaceTree.setParentCode(null);
            spaceTree.setSpaceName("空间");
//			this.save(spaceTree);
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("id", spaceTree.getSpaceCode());
            newMap.put("pId", spaceTree.getParentCode());
            newMap.put("name", spaceTree.getSpaceName());
            result.add(newMap);
        }

        return result;
    }

    private void addParentCode(Set<String> lackSet, List<Map<String, Object>> spaceList) {
        if (!lackSet.contains("0") && lackSet.size() > 0) {
            Set<String> parentSet = new HashSet<String>();
            for (String nodeCode : lackSet) {
                for (Map<String, Object> map : spaceList) {
                    String spaceCode = (String) map.get("NODE_CODE");
                    if (nodeCode.equals(spaceCode)) {
                        parentSet.add((String) map.get("PARENT_CODE"));
                    }
                }
            }
            lackSet.addAll(parentSet);
            addParentCode(lackSet, spaceList);
        }
    }

    /**
     * @param @param  mdmSpaceTree
     * @param @return 参数
     * @return List<Map   <   String   ,   Object>>    返回类型
     * @throws
     * @Title: getUserSpaceOwn
     * @Description:
     */

    public List<Map<String, Object>> getUserSpaceOwn(LoginUser user) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> ownList = dao.getSpaceOwn(user);
        for (Map<String, Object> map : ownList) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("id", map.get("NODE_CODE"));
            newMap.put("pId", map.get("PARENT_CODE"));
            newMap.put("name", map.get("NODE_NAME"));
            result.add(newMap);
        }
        return result;
    }

    public void deleteSpaceTree(Map<String, String> param) {
        dao.deleteSpaceByUser(param);
    }

    public void deleteProfTree(Map<String, String> param) {
        dao.deleteProfByUser(param);
    }

    public void saveSpaceTree(List<String> menuCodeList, String userCode) {
        for (String str : menuCodeList) {
            Map<String, String> map = new HashMap<>();
            map.put("id", UUID.randomUUID().toString());
            map.put("spaceCode", str);
            map.put("userCode", userCode);
//			map.put("enabledFlag", "Y");
//			map.put("version", "1");
//			map.put("updatedDt", new Date());
//			map.put("updatedBy", userCode);
//			map.put("createdDt", new Date());
//			map.put("createdBy", userCode);
            dao.saveSpaceTree(map);
        }
    }

    public List<Map<String, Object>> getUserProfLack(LoginUser user) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> lackList = dao.getUserProfLack(user);
        for (Map<String, Object> map : lackList) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("id", map.get("PROF_CODE"));
            newMap.put("pId", null);
            newMap.put("name", map.get("PROF_NAME"));
            result.add(newMap);
        }
        return result;
    }

    public List<Map<String, Object>> getUserProfOwn(LoginUser user) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> ownList = dao.getUserProfOwn(user);
        for (Map<String, Object> map : ownList) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("id", map.get("PROF_CODE"));
            newMap.put("pId", null);
            newMap.put("name", map.get("PROF_NAME"));
            result.add(newMap);
        }
        return result;
    }

    public void saveProfTree(List<String> menuCodeList, String userId) {
        for (String code : menuCodeList) {
            Map<String, String> map = new HashMap<>();
            map.put("id", UUID.randomUUID().toString());
            map.put("profCode", code);
            map.put("userCode", userId);
            dao.saveProfTree(map);
        }
    }
    public MdmSpaceTree getSpaceByCode(MdmSpaceTree mdmSpaceTree){
        return dao.getSpaceByCode(mdmSpaceTree);
    }

}
