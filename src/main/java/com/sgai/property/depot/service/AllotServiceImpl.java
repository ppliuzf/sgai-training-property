package com.sgai.property.depot.service;

import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.depot.constants.Constants;
import com.sgai.property.depot.dao.IWarehouseAllotDao;
import com.sgai.property.depot.dao.IWarehouseAllotExtDao;
import com.sgai.property.depot.dao.IWarehouseAllotImgDao;
import com.sgai.property.depot.dao.IWarehouseAllotMatDao;
import com.sgai.property.depot.entity.WarehouseAllot;
import com.sgai.property.depot.entity.WarehouseAllotImg;
import com.sgai.property.depot.entity.WarehouseAllotMat;
import com.sgai.property.depot.param.WarehouseAllotParam;
import com.sgai.property.depot.vo.WarehouseAllotImgVo;
import com.sgai.property.depot.vo.WarehouseAllotMatVo;
import com.sgai.property.depot.vo.WarehouseAllotVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 145811 on 2018/1/24.
 */
@Service
public class AllotServiceImpl {
    @Autowired
    IWarehouseAllotDao allotDao;

    @Autowired
    IWarehouseAllotExtDao allotMatExtDao;

    @Autowired
    IWarehouseAllotMatDao allotMatDao;

    @Autowired
    IWarehouseAllotImgDao allotImgDao;
    @Autowired
    private BaseCodeService baseCodeService;

    public Boolean saveOrUpdate(WarehouseAllotParam allotParam) {
        if(StringUtils.isBlank(allotParam.getId())){
            insert(allotParam);
        }else{
            update(allotParam);
        }

        return true;
    }

    private int update(WarehouseAllotParam allotParam) {
        WarehouseAllot entity = allotDao.getById(allotParam.getId());
        if(entity == null){
            throw new BusinessException(ReturnType.ParamIllegal, "数据更新失败，查询不到相应数据");
        }

        Date dt = new Date();
        WarehouseAllot allot = new WarehouseAllot();
        CopyHelper.copyObj(allotParam, allot);

        allot.setAllotEmpId(Long.valueOf(UserServletContext.getUserInfo().getUserNo()));
        allot.setAllotEmpName(UserServletContext.getUserInfo().getUserName());
        allot.setAllotDeptName(UserServletContext.getUserInfo().getComName());
        allot.setAllotDatetime(dt);

        allot.setUpdatedDt(dt);
        int rtInt = allotDao.updateById(allot);
        //清理物料子表
      /*  WarehouseAllotMat matDel = new WarehouseAllotMat();
        matDel.setAllotId(allot.getId());
        matDel.setComCode(UserServletContext.getUserInfo().getComCode());
        matDel.setModuCode(UserServletContext.getUserInfo().getModuCode());
        allotMatDao.delete(matDel);*/
        //清理图片
        WarehouseAllotImg imgDel = new WarehouseAllotImg();
        imgDel.setAllotId(allot.getId());
        imgDel.setComCode(UserServletContext.getUserInfo().getComCode());
        imgDel.setModuCode(UserServletContext.getUserInfo().getModuCode());
        allotImgDao.delete(imgDel);

        if(rtInt > 0){
            //物料子表
            if(allotParam.getMatList() != null ){
                List<WarehouseAllotMat> allotMatList = new ArrayList<WarehouseAllotMat>();
                CopyHelper.copyList(allotParam.getMatList(), allotMatList, WarehouseAllotMat.class);

                for(WarehouseAllotMat mat : allotMatList){
                    /*mat.setId(UUID.randomUUID().toString());
                    mat.setAllotId(allot.getId());
                    mat.setCreatedDt(dt);
                    mat.setUpdatedDt(dt);
                    mat.setComCode(UserServletContext.getUserInfo().getComCode());
                    mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    allotMatDao.insert(mat);*/
                    WarehouseAllotMat matDel = new WarehouseAllotMat();
                    matDel.setAllotId(allot.getId());
                    matDel.setMatTypeId(mat.getMatTypeId());
                    matDel.setComCode(UserServletContext.getUserInfo().getComCode());
                    matDel.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    List<WarehouseAllotMat> list = allotMatDao.findList(matDel);
                    if (list.size()>0){
                        for (WarehouseAllotMat warehouseAllotMat:list) {
                            warehouseAllotMat.setMatRealNum(warehouseAllotMat.getMatRealNum()+mat.getMatRealNum());
                            int i = allotMatDao.updateById(warehouseAllotMat);
                            //allotMatDao.update(warehouseAllotMat);
                        }
                    }
                }
              /*  WarehouseAllotMat matDel = new WarehouseAllotMat();
                matDel.setAllotId(allot.getId());
                matDel.setMatTypeId(mat.getMatTypeId());
                matDel.setComCode(UserServletContext.getUserInfo().getComCode());
                matDel.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<WarehouseAllotMat> list = allotMatDao.findList(matDel);
*/
                WarehouseAllotMat w = new WarehouseAllotMat();
                w.setAllotId(allot.getId());
                w.setComCode(UserServletContext.getUserInfo().getComCode());
                w.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<WarehouseAllotMat> list = allotMatDao.findList(w);
                int yeindex = 0 ;
                int noIndex = 0;
                for (WarehouseAllotMat wr: list) {
                    Long neetNum = wr.getMatNeetNum();//所需数量
                    Long realNum = wr.getMatRealNum();//实际数量

                    if(realNum != null){
                        if (neetNum.intValue() == realNum.intValue()){
                            yeindex ++;//全部出库
                        } else if (realNum.intValue() == 0){
                            noIndex ++;
                        }
                    }else{
                        noIndex  ++;
                    }
                }
                if (yeindex == allotMatList.size()){
                    allot.setWhAllotStat(Constants.MT_STAT_3);
                } else if(noIndex == allotMatList.size()){
                    allot.setWhAllotStat(Constants.MT_STAT_1);
                } else {
                    allot.setWhAllotStat(Constants.MT_STAT_2);
                }
                allotDao.updateById(allot);
            }


            //图片
            if(allotParam.getImgList() != null){
                List<WarehouseAllotImg> imgList = new ArrayList<WarehouseAllotImg>();
                CopyHelper.copyList(allotParam.getImgList(), imgList, WarehouseAllotImg.class);

                for(WarehouseAllotImg img : imgList){
                    img.setId(UUID.randomUUID().toString());
                    img.setAllotId(allot.getId());
                    img.setCreatedDt(dt);
                    img.setUpdatedDt(dt);
                    img.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    img.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotImgDao.insert(img);
                }
            }
        }


        return rtInt;
    }

    private int insert(WarehouseAllotParam allotParam) {
        if(allotParam == null){
            throw new BusinessException(ReturnType.ParamIllegal, "参数异常：" + JSONObject.toJSONString(allotParam));
        }
        //主表
        Date dt = new Date();
        WarehouseAllot allot = new WarehouseAllot();
        CopyHelper.copyObj(allotParam, allot);

        //调拨编号
        allot.setAllotNo(genNo());
        //赋值
        allot.setId(UUID.randomUUID().toString());
        allot.setAllotEmpId(0L);
        allot.setAllotEmpName(UserServletContext.getUserInfo().getUserName());
        allot.setAllotDeptName(UserServletContext.getUserInfo().getComName());
        allot.setAllotDatetime(dt);

        allot.setCreatedDt(dt);
        allot.setUpdatedDt(dt);
        allot.setComCode(UserServletContext.getUserInfo().getComCode());
        allot.setModuCode(UserServletContext.getUserInfo().getModuCode());
        allot.setHasOrder(0L);
        int rtInt = allotDao.insert(allot);

        if(rtInt > 0){
            //物料子表
            if(allotParam.getMatList() != null ){
                List<WarehouseAllotMat> allotMatList = new ArrayList<WarehouseAllotMat>();
                CopyHelper.copyList(allotParam.getMatList(), allotMatList, WarehouseAllotMat.class);

                for(WarehouseAllotMat mat : allotMatList){
                    mat.setId(UUID.randomUUID().toString());
                    mat.setAllotId(allot.getId());
                    mat.setUpdatedDt(dt);
                    mat.setCreatedDt(dt);
                    mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    mat.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotMatDao.insert(mat);
                }
            }

            //图片
            if(allotParam.getImgList() != null){
                List<WarehouseAllotImg> imgList = new ArrayList<WarehouseAllotImg>();
                CopyHelper.copyList(allotParam.getImgList(), imgList, WarehouseAllotImg.class);

                for(WarehouseAllotImg img : imgList){
                    img.setId(UUID.randomUUID().toString());
                    img.setAllotId(allot.getId());
                    img.setCreatedDt(dt);
                    img.setUpdatedDt(dt);
                    img.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    img.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotImgDao.insert(img);
                }
            }
        }

        return rtInt;
    }



    public Page<WarehouseAllotVo> searchList(String allotNo, int pageNum, int pageSize) {
        Page<WarehouseAllotVo> pageVo = new Page<WarehouseAllotVo>();

        WarehouseAllot allot = new WarehouseAllot();
        if(StringUtils.isNotBlank(allotNo)){
            allot.setAllotNo(allotNo);
        }
        allot.setComCode(UserServletContext.getUserInfo().getComCode());
        allot.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<WarehouseAllot> pageInfo = new Page<WarehouseAllot>(pageNum, pageSize);
        pageInfo.setOrderBy("CREATED_DT desc");
        allot.setPage(pageInfo);
        Page<WarehouseAllot> page = pageInfo.setList(allotDao.findList(allot));
        if(page != null){
            CopyHelper.copyObj(page, pageVo);
            if(page.getList() != null){
                List<WarehouseAllotVo> allotVoList = new ArrayList<WarehouseAllotVo>();
                CopyHelper.copyList(page.getList(), allotVoList, WarehouseAllotVo.class);
                for(WarehouseAllotVo allotVo : allotVoList){
                    //物料
                    WarehouseAllotMat allotMat = new WarehouseAllotMat();
                    allotMat.setAllotId(allotVo.getId());
                    allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    List<WarehouseAllotMat> allotMatList  = allotMatDao.findList(allotMat);
                    if(allotMatList != null){
                        List<WarehouseAllotMatVo> allotMatVoList = new ArrayList<WarehouseAllotMatVo>();
                        CopyHelper.copyList(allotMatList, allotMatVoList, WarehouseAllotMatVo.class);
                        allotVo.setMatVoList(allotMatVoList);
                    }
                    //图片
                    WarehouseAllotImg allotImg = new WarehouseAllotImg();
                    allotImg.setAllotId(allotVo.getId());
                    allotImg.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotImg.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    List<WarehouseAllotImg> allotImgList = allotImgDao.findList(allotImg);
                    if(allotImgList != null){
                        List<WarehouseAllotImgVo> allotImgVoList = new ArrayList<WarehouseAllotImgVo>();
                        CopyHelper.copyList(allotImgList, allotImgVoList, WarehouseAllotImgVo.class);
                        allotVo.setImgVoList(allotImgVoList);
                    }
                }
                pageVo.setList(allotVoList);
            }
        }

        return pageVo;
    }

    public WarehouseAllotVo getById(String id) {
        WarehouseAllotVo allotVo = new WarehouseAllotVo();
        WarehouseAllot allot = allotDao.getById(id);
        if(allot == null){
            throw new BusinessException(ReturnType.ParamIllegal, "查询失败，查询不到相应数据");
        }

        CopyHelper.copyObj(allot, allotVo);

        //物料
        WarehouseAllotMat allotMat = new WarehouseAllotMat();
        allotMat.setAllotId(allotVo.getId());
        allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
        allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehouseAllotMat> allotMatList  = allotMatDao.findList(allotMat);
        if(allotMatList != null){
            List<WarehouseAllotMatVo> allotMatVoList = new ArrayList<WarehouseAllotMatVo>();
            CopyHelper.copyList(allotMatList, allotMatVoList, WarehouseAllotMatVo.class);
            allotVo.setMatVoList(allotMatVoList);
        }
        //图片
        WarehouseAllotImg allotImg = new WarehouseAllotImg();
        allotImg.setAllotId(allotVo.getId());
        allotImg.setComCode(UserServletContext.getUserInfo().getComCode());
        allotImg.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehouseAllotImg> allotImgList = allotImgDao.findList(allotImg);
        if(allotImgList != null){
            List<WarehouseAllotImgVo> allotImgVoList = new ArrayList<WarehouseAllotImgVo>();
            CopyHelper.copyList(allotImgList, allotImgVoList, WarehouseAllotImgVo.class);
            allotVo.setImgVoList(allotImgVoList);
        }
        return allotVo ;
    }

    public Boolean del(String id) {
        WarehouseAllot allot = allotDao.getById(id);
        if(allot == null){
            throw new BusinessException(ReturnType.ParamIllegal, "删除数据失败，不存在该条数据");
        }
        //删除子表
        WarehouseAllotMat alloMat = new WarehouseAllotMat();
        alloMat.setAllotId(id);
        alloMat.setComCode(UserServletContext.getUserInfo().getComCode());
        alloMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        allotMatDao.delete(alloMat);
        //删除主表
        allotDao.deleteById(id);
        return true;
    }


    private String genNo(){
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_allot);
        String seqStr = Long.toString(seq);
        for(int i = seqStr.length(); i < 8; i ++){
            seqStr = "0" + seqStr;
        }
        return "CL-" + seqStr;
    }

    public List<WarehouseAllotVo> searchList4InOut(String allotNo, Long hasOrder) {
        List<WarehouseAllotVo> allotVoList = new ArrayList<WarehouseAllotVo>();
        WarehouseAllot allot = new WarehouseAllot();
        if(StringUtils.isNotBlank(allotNo)){
            allot.setAllotNo(allotNo);
        }
        allot.setWhAllotStat(1L);
        allot.setHasOrder(hasOrder);
        allot.setModuCode(UserServletContext.getUserInfo().getModuCode());
        allot.setComCode(UserServletContext.getUserInfo().getComCode());
        List<WarehouseAllot> allotList = allotMatExtDao.find4InOut(allot);
        if(allotList == null || allotList.size() == 0){
            return allotVoList;
        }

        CopyHelper.copyList(allotList, allotVoList, WarehouseAllotVo.class);
        for(WarehouseAllotVo allotVo : allotVoList){
            //物料
            WarehouseAllotMat allotMat = new WarehouseAllotMat();
            allotMat.setAllotId(allotVo.getId());
            allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
            allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<WarehouseAllotMat> allotMatList  = allotMatDao.findList(allotMat);
            if(allotMatList != null){
                List<WarehouseAllotMatVo> allotMatVoList = new ArrayList<WarehouseAllotMatVo>();
                CopyHelper.copyList(allotMatList, allotMatVoList, WarehouseAllotMatVo.class);
                allotVo.setMatVoList(allotMatVoList);
            }
            //图片
            WarehouseAllotImg allotImg = new WarehouseAllotImg();
            allotImg.setAllotId(allotVo.getId());
            allotImg.setComCode(UserServletContext.getUserInfo().getComCode());
            allotImg.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<WarehouseAllotImg> allotImgList = allotImgDao.findList(allotImg);
            if(allotImgList != null){
                List<WarehouseAllotImgVo> allotImgVoList = new ArrayList<WarehouseAllotImgVo>();
                CopyHelper.copyList(allotImgList, allotImgVoList, WarehouseAllotImgVo.class);
                allotVo.setImgVoList(allotImgVoList);
            }
        }
        return allotVoList;
    }

    /**
     * 查询分页挑拨入库采购单用料明细
     * @param allotNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<WarehouseAllotMatVo> searchList4InOutMat(String allotNo, int pageNum, int pageSize) {
        Page<WarehouseAllotMatVo> page = new Page<WarehouseAllotMatVo>();
        WarehouseAllotMat allotMat = new WarehouseAllotMat();

        allotMat.setAllotId(allotNo);
        allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
        allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehouseAllotMat> allotMatList  = allotMatDao.findList(allotMat);
        List<WarehouseAllotMatVo> allotMatVoList = new ArrayList<WarehouseAllotMatVo>();
        CopyHelper.copyList(allotMatList, allotMatVoList, WarehouseAllotMatVo.class);
        page.setList(allotMatVoList);
        page.setCount(allotMatVoList.size());
        page.setPageNo(pageNum);
        page.setPageSize(pageSize);
        return page;
    }
}
