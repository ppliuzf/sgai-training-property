package com.sgai.property.commonService.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.constants.Constants;
import com.sgai.property.commonService.vo.DeptEmpVo;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.organ.OrganUnitVo;
import com.sgai.property.commonService.vo.organ.OrganUserVo;
import com.sgai.property.commonService.vo.organ.UserQueryResultVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class DeptServiceImpl {
    private static final Logger logger = LogManager.getLogger(DeptServiceImpl.class);
   // @Value("${orgTree.defaultEiPicUrl}")
    private String defaultEiPicUrl;
    @Autowired
    private OrganServiceImpl organService;
    @Autowired
    private RedisClient redisClient;

    public void syncDept(String companyId){
        //取得根部门信息
        String organToken =organService.getAccessToken(companyId);
        OrganUnitVo organUnitVo=organService.getRootDeptList(companyId);
        if(organUnitVo!=null){
            //取得根部门下的人员信息
            int num=organService.getDeptNum(organUnitVo.getId(),organToken,true);
            logger.info("根部门-->"+ organUnitVo.getOrganUnitName()+" 人数-->"+num);
            DeptVo deptVo=new DeptVo();
            deptVo.setDeptId(organUnitVo.getId());
            deptVo.setDeptName(organUnitVo.getOrganUnitName());
            deptVo.setPathDeptId(organUnitVo.getId().toString());
            deptVo.setPathDeptName(organUnitVo.getOrganUnitName());
            deptVo.setDeptNum(num);
            redisClient.set(MessageFormat.format(Constants.DEPT_ROOT_KEY,companyId), JSON.toJSONString(deptVo));
            redisClient.set(MessageFormat.format(Constants.DEPT_INFO_KEY,companyId)+organUnitVo.getId(), JSON.toJSONString(deptVo));
            getCascadeRootDeptNumInfo(companyId,organToken,deptVo);
        }
    }

    public void getCascadeRootDeptNumInfo(String companyId,String accessToken,DeptVo parentDeptVo){
        List<OrganUnitVo> organUnitVos=organService.getDeptList(companyId,parentDeptVo.getDeptId(),false);
        if(organUnitVos!=null && organUnitVos.size()>0){
            List<DeptVo> deptVos= Lists.newArrayList();
            DeptVo deptVo=null;
            for (OrganUnitVo organUnitVo : organUnitVos) {
                deptVo=new DeptVo();
                //获取部门人数
                UserQueryResultVo vo=organService.getDeptNumInfo(organUnitVo.getId(),accessToken,true);
                int num=vo.getTotalRecords();
                if(vo.getList()!=null && vo.getList().size()>0){
                    deptVo.setFristUserName(vo.getList().get(0).getUserName());
                }
                deptVo.setDeptId(organUnitVo.getId());
                deptVo.setDeptName(organUnitVo.getOrganUnitName());
                deptVo.setPathDeptId(parentDeptVo.getPathDeptId()+"_"+organUnitVo.getId());
                deptVo.setPathDeptName(parentDeptVo.getPathDeptName()+"/"+organUnitVo.getOrganUnitName());
                deptVo.setDeptNum(num);
                redisClient.set(MessageFormat.format(Constants.DEPT_INFO_KEY,companyId)+organUnitVo.getId(), JSON.toJSONString(deptVo));
                deptVos.add(deptVo);
            }
            //获取部门集合信息
            DeptEmpVo deptEmpVo=new DeptEmpVo();
            deptEmpVo.setDeptVoList(deptVos);
            //获取部门下的人员信息
            deptEmpVo.setEmpInfoVos(getDeptEmpbyDeptId(companyId, parentDeptVo));
            redisClient.set(MessageFormat.format(Constants.DEPT_LIST_KEY,companyId)+parentDeptVo.getDeptId(), JSON.toJSONString(deptEmpVo));
            for (DeptVo deptVo1 : deptVos) {
                logger.info(deptVo1.getDeptName()+ "部门路径-->"+ deptVo1.getPathDeptName() +" 人数-->"+deptVo1.getDeptNum());
                getCascadeRootDeptNumInfo(companyId,accessToken,deptVo1);
            }
        }else{
            //如果下级没有部门，则查询部门下是否还有人员信息
            //获取部门集合信息
            DeptEmpVo deptEmpVo=new DeptEmpVo();
            deptEmpVo.setDeptVoList(null);
            //获取部门下的人员信息
            deptEmpVo.setEmpInfoVos(getDeptEmpbyDeptId(companyId, parentDeptVo));
            redisClient.set(MessageFormat.format(Constants.DEPT_LIST_KEY,companyId)+parentDeptVo.getDeptId(), JSON.toJSONString(deptEmpVo));
        }
    }

    private  List<EmpInfoVo> getDeptEmpbyDeptId(String companyId, DeptVo deptVo) {
        List<OrganUserVo> userVoList=organService.getUserByDeptId(companyId,deptVo.getDeptId(),false);
        List<EmpInfoVo>  empInfoVoList=new LinkedList<>();
        EmpInfoVo empInfoVo=null;
        for (OrganUserVo organUserVo : userVoList) {
            empInfoVo=new EmpInfoVo();
            empInfoVo.setEiId(organUserVo.getId());
            empInfoVo.setEiEmail(organUserVo.getEmail());
            //默认头像
            if(StringUtils.isNotEmpty(organUserVo.getPhoto())){
                empInfoVo.setEiHeadPicture(organUserVo.getPhoto());
            }else{
                empInfoVo.setEiHeadPicture(defaultEiPicUrl);
            }
            empInfoVo.setEiEmpName(organUserVo.getUserName());
            empInfoVo.setEiEmpNo(organUserVo.getUserCode());
            if(organUserVo.getFeedIds()!=null && organUserVo.getFeedIds().size()>0){
                empInfoVo.setFeedId(organUserVo.getFeedIds().get(0));
            }
            empInfoVo.setDeptId(deptVo.getDeptId());
            empInfoVo.setDeptName(deptVo.getDeptName());
            empInfoVo.setPathDeptId(deptVo.getPathDeptId());
            empInfoVo.setPathDeptName(deptVo.getPathDeptName());
            empInfoVoList.add(empInfoVo);
        }
        return empInfoVoList;
    }
}
