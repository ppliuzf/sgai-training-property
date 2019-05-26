package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.depot.dao.*;
import com.sgai.property.depot.entity.Inventories;
import com.sgai.property.depot.entity.InventoriesMat;
import com.sgai.property.depot.entity.WarehousRecord;
import com.sgai.property.depot.entity.Warehouse;
import com.sgai.property.depot.param.*;
import com.sgai.property.depot.vo.*;
import com.sgai.property.purchase.dao.IWarehouseMatDao;
import com.sgai.property.purchase.entity.WarehouseMat;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by 145811 on 2018/1/25.
 */
@Service
public class
InventoriesServiceImpl {
    @Autowired
    IInventoriesDao inventoriesDao;

    @Autowired
    IInventoriesExtDao inventoriesExtDao;

    @Autowired
    IInventoriesMatDao inventoriesMatDao;

    @Autowired
    IWarehouseDao warehouseDao;

    @Autowired
    IWarehouseMatDao warehouseMatDao;

    @Autowired
    IWarehousRecordDao warehouseRecordDao;
    @Autowired
    private BaseCodeService baseCodeService;

    private String genNo() {
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_inventories);
        String seqStr = Long.toString(seq);
        for (int i = seqStr.length(); i < 8; i++) {
            seqStr = "0" + seqStr;
        }
        return "PD-" + seqStr;
    }

    public Boolean saveOrUpdate(InventoriesParam param) {
        int dbInt = 0;
        if (StringUtils.isBlank(param.getWhId())) {
            throw new BusinessException(ReturnType.ParamIllegal, "仓库id为空");
        }
        Date dt = new Date();
        if (StringUtils.isBlank(param.getId())) {
            //新增
            Inventories entity = new Inventories();
            CopyHelper.copyObj(param, entity);
            entity.setId(UUID.randomUUID().toString());
            entity.setComCode(UserServletContext.getUserInfo().getComCode());
            entity.setModuCode(UserServletContext.getUserInfo().getModuCode());
            entity.setIvtNo(genNo());
            entity.setUpdatedDt(dt);
            entity.setCreatedDt(dt);

            dbInt = inventoriesDao.insert(entity);
        } else {
            //更新
            Inventories entity = new Inventories();
            CopyHelper.copyObj(param, entity);

            entity.setUpdatedDt(dt);
            entity.setCreatedDt(dt);
            dbInt = inventoriesDao.updateById(entity);
        }
        return dbInt > 0;
    }


    /**
     * 新增盘点信息
     *
     * @param param
     * @return
     */
    public void saveInventoriesNew(InventoisesParamNew param) {
        String ivtNo = genNo();
        if (param != null && param.getWhMatParamList() != null) {
            List<InventoriesWhMatParam> list = param.getWhMatParamList();
            list.stream().forEach(inventoriesWhMatParam->{
                //新增盘点信息
                Supplier<Inventories> supplier = Inventories::new;
                Inventories inventories = supplier.get();
                BeanUtils.copyProperties(param, inventories);
                inventories.setId(UUID.randomUUID().toString());
                inventories.setIvtPiJson(param.getIvtPiJson());
                inventories.setComCode(UserServletContext.getUserInfo().getComCode());
                inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
                inventories.setIvtNo(ivtNo);
                inventories.setUpdatedDt(new Date());
                inventories.setCreatedDt(new Date());
                inventories.setMatStat(1L); //1：未开始；2：盘点中；3已完成
                //保存仓库id
                inventories.setWhId(inventoriesWhMatParam.getWhId());
                //保存仓库名称
                inventories.setWhName(inventoriesWhMatParam.getWhName());
                inventoriesDao.insert(inventories);

                //新增盘点物料信息
                List<InventoiresMatParamNew> matParamNewList = inventoriesWhMatParam.getInventoriesMatList();
                matParamNewList.stream().forEach(matParamNew->{
                    Supplier<InventoriesMat> supp = InventoriesMat::new;
                    InventoriesMat inventoriesMat = supp.get();
                    inventoriesMat.setIvtId(inventories.getIvtNo());
                    inventoriesMat.setMatTypeId(matParamNew.getMatTypeId());
                    inventoriesMat.setWhId(inventories.getWhId());
                    inventoriesMat.setMatName(matParamNew.getMatName());
                    inventoriesMat.setMatNo(matParamNew.getMatNo());
                    inventoriesMat.setId(UUID.randomUUID().toString());
                    inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
                    inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    inventoriesMat.setCreatedBy(UserServletContext.getUserInfo().getUserName());
                    inventoriesMat.setCreatedDt(new Date());
                    if(matParamNew.getMatSpec()!=null && !"".equals(matParamNew.getMatSpec())){
                        inventoriesMat.setMatSpec(matParamNew.getMatSpec());
                    }
                    if(matParamNew.getMatTypeCode()!=null && !"".equals(matParamNew.getMatTypeCode())){
                        inventoriesMat.setMatTypeCode(matParamNew.getMatTypeCode());
                    }

                    inventoriesMatDao.insert(inventoriesMat);
                });
            });

        }

    }


    /**
     * 根据盘点单号查询物料列表
     * @param ivtNo
     * @return
     */
    public Page<InventoiresMatVoNew> findMatListByivtNo(String ivtNo, String whId, Integer pageNum, Integer pageSize){
        Page<InventoiresMatVoNew> pageInfo = new Page<InventoiresMatVoNew>();
        List<InventoiresMatVoNew> voList = new ArrayList<InventoiresMatVoNew>();
        Supplier<InventoriesMat> supplier = InventoriesMat::new;
        InventoriesMat inventoriesMat = supplier.get();
        inventoriesMat.setIvtId(ivtNo);
        inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
        inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        if(whId!=null && !"".equals(whId)){
            inventoriesMat.setWhId(whId);
        }
        List<InventoriesMat> list = inventoriesMatDao.findMatListByivtNo(inventoriesMat);
        //先查询该盘点单号在中间表中是否存在，如果存在先删除再新增，查询
        WarehousRecord record2 = new WarehousRecord();
        record2.setOrderNumber(ivtNo);
        record2.setComCode(UserServletContext.getUserInfo().getComCode());
        record2.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehousRecord> recordList1 = warehouseRecordDao.findList(record2);
        if(recordList1!=null && recordList1.size()>0){
            recordList1.stream().forEach((warehousRecord -> {
                warehouseRecordDao.deleteById(warehousRecord.getId());
            }));
        }
        list.stream().forEach(inventMat -> {
            Supplier<WarehousRecord> supp = WarehousRecord::new;
            WarehousRecord warehousRecord = supp.get();
            warehousRecord.setId(UUID.randomUUID().toString());
            //同步仓库和物料关系的id
            warehousRecord.setOrderId(inventMat.getId());
            //同步盘点单号
            warehousRecord.setOrderNumber(inventMat.getIvtId());
            //同步物料的id
            warehousRecord.setMatTypeId(inventMat.getMatTypeId());
            //同步仓库id
            warehousRecord.setWhId(inventMat.getWhId());
            //同步仓库的名称
            Inventories inventories = new Inventories();
            inventories.setWhId(inventMat.getWhId());
            inventories.setIvtNo(ivtNo);
            inventories.setComCode(UserServletContext.getUserInfo().getComCode());
            inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<Inventories> list1 = inventoriesDao.findList(inventories);
            warehousRecord.setWhName(list1.get(0).getWhName());
            //同步物料名称
            warehousRecord.setMatName(inventMat.getMatName());
            //同步物料的规格
            if(inventMat.getMatSpec()!=null && !"".equals(inventMat.getMatSpec())){
                warehousRecord.setMatSpec(inventMat.getMatSpec());
            }
            //同步物料typeCode
            if(inventMat.getMatTypeCode()!=null && !"".equals(inventMat.getMatTypeCode())){
                warehousRecord.setMatTypeCode(inventMat.getMatTypeCode());
            }
            //同步账存数量
//            InventoriesMat inventoriesMat1 = new InventoriesMat();
//            inventoriesMat1.setIvtId(inventMat.getIvtId());
//            inventoriesMat1.setWhId(inventMat.getWhId());
//            inventoriesMat1.setMatTypeId(inventMat.getMatTypeId());
//            inventoriesMat1.setComCode(inventMat.getComCode());
//            inventoriesMat1.setModuCode(inventMat.getModuCode());
//            Integer matNum = inventoriesMatDao.getRecordCount(inventoriesMat1);
            WarehouseMat warehouseMat =  new WarehouseMat();
            warehouseMat.setWhId(inventMat.getWhId());
            warehouseMat.setMatTypeId(inventMat.getMatTypeId());
            warehouseMat.setComCode(UserServletContext.getUserInfo().getComCode());
            warehouseMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            WarehouseMat warehouseMat1 = warehouseMatDao.get(warehouseMat);
            if(warehouseMat1==null){
                warehousRecord.setMatNum(0L);
            }else{
                warehousRecord.setMatNum(warehouseMat1.getMatNum()==null?0L:warehouseMat1.getMatNum());
            }
            //同步实际量
            warehousRecord.setMatRealNum(inventMat.getMatRealNum()==null ?0L:inventMat.getMatRealNum());
            //同步comCode 和moduCode
            warehousRecord.setComCode(inventMat.getComCode());
            warehousRecord.setModuCode(inventMat.getModuCode()==null ?"":inventMat.getModuCode());
            //物料编号
            warehousRecord.setMatNo(inventMat.getMatNo());
            //同步差异量
            warehousRecord.setMatDiffNum((warehousRecord.getMatNum()-warehousRecord.getMatRealNum()));
            warehouseRecordDao.insert(warehousRecord);
        });

        WarehousRecord record1 = new WarehousRecord();
        record1.setOrderNumber(ivtNo);
        if(whId!=null && !"".equals(whId)){
            record1.setWhId(whId);
        }
        record1.setComCode(UserServletContext.getUserInfo().getComCode());
        record1.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<WarehousRecord> page = new Page<WarehousRecord>(pageNum, pageSize);
        record1.setPage(page);
        List<WarehousRecord> recordList = warehouseRecordDao.findListByIvtNo(record1);
        Integer count = warehouseRecordDao.getRecordCount(record1);
        recordList.stream().forEach((r -> {
            InventoiresMatVoNew inventoiresMatVoNew = new InventoiresMatVoNew();
            inventoiresMatVoNew.setOrderNumber(r.getOrderNumber());
            inventoiresMatVoNew.setWhId(r.getWhId());
            inventoiresMatVoNew.setWhName(r.getWhName());
            inventoiresMatVoNew.setMatTypeId(r.getMatTypeId());
            inventoiresMatVoNew.setMatName(r.getMatName());
            inventoiresMatVoNew.setMatNum(r.getMatNum());
            inventoiresMatVoNew.setMatRealNum(r.getMatRealNum());
            inventoiresMatVoNew.setMatDiffNum(r.getMatDiffNum());
            voList.add(inventoiresMatVoNew);
        }));
        pageInfo.setList(voList);
        pageInfo.setCount(count);
        return pageInfo;
    }


    /**
     * 失去焦点获取物料列表
     * @param ivtNo
     * @param whId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<InventoiresMatVoNew> findMatListByonBlur(String ivtNo, String whId, Integer pageNum, Integer pageSize){
        Page<InventoiresMatVoNew> pageInfo = new Page<InventoiresMatVoNew>();
        List<InventoiresMatVoNew> voList = new ArrayList<InventoiresMatVoNew>();
        WarehousRecord record1 = new WarehousRecord();
        record1.setOrderNumber(ivtNo);
        if(whId!=null && !"".equals(whId)){
            record1.setWhId(whId);
        }
        record1.setComCode(UserServletContext.getUserInfo().getComCode());
        record1.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<WarehousRecord> page = new Page<WarehousRecord>(pageNum, pageSize);
        record1.setPage(page);
        List<WarehousRecord> recordList = warehouseRecordDao.findListByIvtNo(record1);
        Integer count = warehouseRecordDao.getRecordCount(record1);
//        recordList.forEach((r -> {
//            InventoiresMatVoNew inventoiresMatVoNew = new InventoiresMatVoNew();
//            inventoiresMatVoNew.setOrderNumber(r.getOrderNumber());
//            inventoiresMatVoNew.setWhId(r.getWhId());
//            inventoiresMatVoNew.setWhName(r.getWhName());
//            inventoiresMatVoNew.setMatTypeId(r.getMatTypeId());
//            inventoiresMatVoNew.setMatName(r.getMatName());
//            inventoiresMatVoNew.setMatNum(r.getMatNum());
//            inventoiresMatVoNew.setMatRealNum(r.getMatRealNum());
//            inventoiresMatVoNew.setMatDiffNum(r.getMatDiffNum());
//            voList.add(inventoiresMatVoNew);
//        }));

        voList = recordList.stream().map((r->{
            InventoiresMatVoNew inventoiresMatVoNew = new InventoiresMatVoNew();
            inventoiresMatVoNew.setOrderNumber(r.getOrderNumber());
            inventoiresMatVoNew.setWhId(r.getWhId());
            inventoiresMatVoNew.setWhName(r.getWhName());
            inventoiresMatVoNew.setMatTypeId(r.getMatTypeId());
            inventoiresMatVoNew.setMatName(r.getMatName());
            inventoiresMatVoNew.setMatNum(r.getMatNum());
            inventoiresMatVoNew.setMatRealNum(r.getMatRealNum());
            inventoiresMatVoNew.setMatDiffNum(r.getMatDiffNum());
            return inventoiresMatVoNew;
        })).limit(recordList.size()).collect(Collectors.toList());

        pageInfo.setList(voList);
        pageInfo.setCount(count);
        return pageInfo;
    }


    /**
     * 执行盘点后的物料分页数据
     * @param ivtNo
     * @param whId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<InventoiresMatVoNew> excuteInventMatPage(String ivtNo, String whId, Integer pageNum, Integer pageSize){
        Page<InventoiresMatVoNew> pageInfo = new Page<InventoiresMatVoNew>();
        List<InventoiresMatVoNew> voList = new ArrayList<InventoiresMatVoNew>();
        InventoriesMat inventoriesMat = new InventoriesMat();
        inventoriesMat.setIvtId(ivtNo);
        if(whId!=null && !"".equals(whId)){
            inventoriesMat.setWhId(whId);
        }
        inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
        inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<InventoriesMat> page = new Page<InventoriesMat>(pageNum, pageSize);
        inventoriesMat.setPage(page);
        List<InventoriesMat> recordList = inventoriesMatDao.findListByIvtNo(inventoriesMat);
        Integer count = inventoriesMatDao.getRecordCount1(inventoriesMat);
        recordList.stream().forEach((inventoriesMat1 -> {
            InventoiresMatVoNew inventoiresMatVoNew = new InventoiresMatVoNew();
            inventoiresMatVoNew.setOrderNumber(inventoriesMat1.getIvtId());
            inventoiresMatVoNew.setWhId(inventoriesMat1.getWhId());

            //封装仓库名称
            if(inventoriesMat1.getWhId()!=null && !"".equals(inventoriesMat1.getWhId())){
                Inventories invent = new Inventories();
                invent.setWhId(inventoriesMat1.getWhId());
                invent.setComCode(UserServletContext.getUserInfo().getComCode());
                invent.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<Inventories> inventoriesList = inventoriesDao.findList(invent);
                if(inventoriesList!=null && inventoriesList.size()>0){
                    inventoiresMatVoNew.setWhName(inventoriesList.get(0).getWhName());
                }

            }

            inventoiresMatVoNew.setMatTypeId(inventoriesMat1.getMatTypeId());
            inventoiresMatVoNew.setMatName(inventoriesMat1.getMatName());

            if(inventoriesMat1.getMatNum()!=null && inventoriesMat1.getMatNum()!=0L){
                inventoiresMatVoNew.setMatNum(inventoriesMat1.getMatNum());
            }else{
                WarehouseMat warehouseMat =  new WarehouseMat();
                warehouseMat.setWhId(inventoriesMat1.getWhId());
                warehouseMat.setMatTypeId(inventoriesMat1.getMatTypeId());
                warehouseMat.setComCode(UserServletContext.getUserInfo().getComCode());
                warehouseMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                WarehouseMat warehouseMat1 = warehouseMatDao.get(warehouseMat);
                if(warehouseMat1==null){
                    inventoiresMatVoNew.setMatNum(0L);
                }else{
                    inventoiresMatVoNew.setMatNum(warehouseMat1.getMatNum()==null?0L:warehouseMat1.getMatNum());
                }
            }
            inventoiresMatVoNew.setMatRealNum(inventoriesMat1.getMatRealNum()==null?0L:inventoriesMat1.getMatRealNum());
            inventoiresMatVoNew.setMatDiffNum(inventoriesMat1.getMatDiffNum()==null?0L:inventoriesMat1.getMatDiffNum());
            voList.add(inventoiresMatVoNew);
        }));
        pageInfo.setList(voList);
        pageInfo.setCount(count);
        return pageInfo;
    }



    /**
     * 失去焦点更新物料列表
     * @param warehouseMatParam
     */
    public void updateWarehousRecord(WarehouseMatParam warehouseMatParam){
            //查询要更新的物料信息列表
            WarehousRecord record = new WarehousRecord();
            record.setOrderNumber(warehouseMatParam.getOrderNumber());
            record.setWhId(warehouseMatParam.getWhId());
            record.setMatTypeId(warehouseMatParam.getMatTypeId());
            record.setComCode(UserServletContext.getUserInfo().getComCode());
            record.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<WarehousRecord> list = new ArrayList<>();
            list = warehouseRecordDao.findList(record);

            list.stream().forEach(warehousRecord -> {
                WarehousRecord record1 = new WarehousRecord();
                record1.setId(warehousRecord.getId());
                record1.setMatRealNum(warehouseMatParam.getMatRealNum());
                record1.setMatDiffNum(warehouseMatParam.getMatDiffNum());
                warehouseRecordDao.updateById(record1);
            });
    }



    /**
     * 根据盘点单号查询仓库列表
     * @param ivtNo
     * @return
     */
    public List<Inventories> findAllBhByivtNo(String ivtNo){
        Inventories inventories = new Inventories();
        inventories.setIvtNo(ivtNo);
        inventories.setComCode(UserServletContext.getUserInfo().getComCode());
        inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Inventories> list = inventoriesDao.findAllBhByivtNo(inventories);
        return list;
    }


    /**
     * 执行盘点仓库新增
     * @param paramNew
     */
    public void excuteUpdateInventories(ExcuteInventoisesParamNew paramNew){
        if(paramNew.getIvtNo()!=null && !"".equals(paramNew.getIvtNo())){
            Inventories inventories = new Inventories();
            inventories.setIvtNo(paramNew.getIvtNo());
            inventories.setComCode(UserServletContext.getUserInfo().getComCode());
            inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<Inventories> list = inventoriesDao.findList(inventories);
            list.stream().forEach(inventories1 -> {
                //将新增的信息全部编辑到原有的实体信息中
                Inventories inventories2 = new Inventories();
                inventories2.setId(inventories1.getId());
                inventories2.setIvtExecBegin(paramNew.getIvtExecBegin());
                inventories2.setIvtExecEnd(paramNew.getIvtExecEnd());
                inventories2.setIvtExecEmpId(paramNew.getIvtExecEmpId());
                inventories2.setIvtExecEmpName(paramNew.getIvtExecEmpName());
                inventories2.setMatStat(3L);
                inventories2.setIvtDiffReason(paramNew.getIvtDiffReason());
                inventoriesDao.updateById(inventories2);
            });
        }
    }

    /**
     * 编辑盘点信息
     * @param param
     */
    public void updateInventoriesNew(InventoisesParamNew param){
        if (param != null && param.getWhMatParamList() != null) {
            //删除盘点信息和盘点仓库物料信息
            Inventories inventories = new Inventories();
            inventories.setIvtNo(param.getIvtNo());
            inventories.setComCode(UserServletContext.getUserInfo().getComCode());
            inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<Inventories> list = inventoriesDao.findList(inventories);
            if(list!=null && list.size() > 0){
                list.stream().forEach(inventories1 -> {
                    inventoriesDao.delete(inventories1);
                });
            }

            InventoriesMat inventoriesMat = new InventoriesMat();
            inventoriesMat.setIvtId(param.getIvtNo());
            inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
            inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<InventoriesMat> list1 = inventoriesMatDao.findList(inventoriesMat);
            if(list1!=null && list1.size() > 0){
                list1.stream().forEach(inventoriesMat1 -> {
                    inventoriesMatDao.delete(inventoriesMat1);
                });
            }

            //重新插入盘点，仓库，物料信息
            if (param != null && param.getWhMatParamList() != null) {
                String ivtNo = param.getIvtNo();
                List<InventoriesWhMatParam> paramWhMatParamList = param.getWhMatParamList();
                paramWhMatParamList.stream().forEach(inventoriesWhMatParam->{
                    //新增盘点信息
                    Supplier<Inventories> supplier = Inventories::new;
                    Inventories inventories2 = supplier.get();
                    BeanUtils.copyProperties(param, inventories2);
                    inventories2.setId(UUID.randomUUID().toString());
                    inventories2.setIvtPiJson(param.getIvtPiJson());
                    inventories2.setComCode(UserServletContext.getUserInfo().getComCode());
                    inventories2.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    inventories2.setIvtNo(ivtNo);
                    inventories2.setUpdatedDt(new Date());
                    inventories2.setCreatedDt(new Date());
                    inventories2.setMatStat(1L); //1：未开始；2：盘点中；3已完成
                    //保存仓库id
                    inventories2.setWhId(inventoriesWhMatParam.getWhId());
                    //保存仓库名称
                    inventories2.setWhName(inventoriesWhMatParam.getWhName());
                    inventoriesDao.insert(inventories2);

                    //新增盘点物料信息
                    List<InventoiresMatParamNew> matParamNewList = inventoriesWhMatParam.getInventoriesMatList();
                    matParamNewList.stream().forEach(matParamNew->{
                        Supplier<InventoriesMat> supp = InventoriesMat::new;
                        InventoriesMat inventoriesMat2 = supp.get();
                        inventoriesMat2.setIvtId(inventories2.getIvtNo());
                        inventoriesMat2.setMatTypeId(matParamNew.getMatTypeId());
                        inventoriesMat2.setWhId(inventories2.getWhId());
                        inventoriesMat2.setMatName(matParamNew.getMatName());
                        inventoriesMat2.setMatNo(matParamNew.getMatNo());
                        inventoriesMat2.setId(UUID.randomUUID().toString());
                        inventoriesMat2.setComCode(UserServletContext.getUserInfo().getComCode());
                        inventoriesMat2.setModuCode(UserServletContext.getUserInfo().getModuCode());
                        if(matParamNew.getMatSpec()!=null && !"".equals(matParamNew.getMatSpec())){
                            inventoriesMat2.setMatSpec(matParamNew.getMatSpec());
                        }
                        if(matParamNew.getMatTypeCode()!=null && !"".equals(matParamNew.getMatTypeCode())){
                            inventoriesMat2.setMatTypeCode(matParamNew.getMatTypeCode());
                        }

                        inventoriesMatDao.insert(inventoriesMat2);
                    });
                });

            }

        }
    }



    public Page<InventoriesVo> searchPage(Date dt, String ivtNo, Long matStat, String ivtTitle, int pageNum, int pageSize) {
        InventoriesExt inventoriesExt = new InventoriesExt();
        Page<Inventories> pageInfo = new Page<Inventories>(pageNum, pageSize);
        inventoriesExt.setPage(pageInfo);
        if(dt != null){
            String dtStr = DateFormatUtils.format(dt,"yyyy-MM-dd", Locale.PRC);
            Date bTime = null;
            try {
                bTime = DateUtils.parseDate(dtStr, Locale.PRC ,"yyyy-MM-dd");
            } catch (ParseException e) {
                throw new BusinessException(ReturnType.ParamIllegal, "时间类型转换异常");
            }
            bTime = DateUtils.setDays(bTime, 1);
            Date eTime = DateUtils.addMonths(bTime, 1);
            inventoriesExt.setBbTime(bTime);
            inventoriesExt.setEeTime(eTime);
        }
        if(StringUtils.isNotBlank(ivtTitle)){
            inventoriesExt.setIvtTitle(ivtTitle);
        }
        inventoriesExt.setMatStat(matStat);
        inventoriesExt.setComCode(UserServletContext.getUserInfo().getComCode());
        inventoriesExt.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<Inventories> page = pageInfo.setList(inventoriesExtDao.searchList(inventoriesExt));

        Page<InventoriesVo> pageVo = new Page<InventoriesVo>();
        if(page != null){
            CopyHelper.copyObj(page, pageVo);
            if(page.getList() != null){
                List<InventoriesVo> voList = new ArrayList<InventoriesVo>();
                CopyHelper.copyList(page.getList(), voList, InventoriesVo.class);
                pageVo.setList(voList);
            }
        }
        return pageVo;
    }

    public InventoriesVo detail(String id) {
        Inventories entity = inventoriesDao.getById(id);
        if(entity == null){
            throw new BusinessException(ReturnType.ParamIllegal, "查询详情异常");
        }
        InventoriesVo vo = new InventoriesVo();
        CopyHelper.copyObj(entity, vo);
        Warehouse warehouse = warehouseDao.getById(vo.getWhId());
        if(warehouse != null){
            vo.setWhType(warehouse.getWhType());
        }
        vo.setLessCount(inventoriesExtDao.getcountLess(entity));
        vo.setMoreCount(inventoriesExtDao.getcountMore(entity));
        return vo;
    }


    /**
     * 执行盘点新增物料列表同步
     * @param ivtNo
     */
    public void excuteSynchroMatList(String ivtNo){
        //从中间表中根据盘点单查询物料列表
        WarehousRecord warehousRecord = new WarehousRecord();
        warehousRecord.setOrderNumber(ivtNo);
        warehousRecord.setComCode(UserServletContext.getUserInfo().getComCode());
        warehousRecord.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehousRecord> list = warehouseRecordDao.findList(warehousRecord);

        list.stream().forEach(warehousRecord1 -> {
            InventoriesMat inventoriesMat = new InventoriesMat();
            inventoriesMat.setId(warehousRecord1.getOrderId());
            inventoriesMat.setMatRealNum(warehousRecord1.getMatRealNum());
            inventoriesMat.setMatNum(warehousRecord1.getMatNum());
            inventoriesMat.setMatDiffNum(warehousRecord1.getMatDiffNum());
            inventoriesMat.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
            inventoriesMat.setUpdatedDt(new Date());
            inventoriesMatDao.updateById(inventoriesMat);
        });
    }


    /**
     * 新盘点详情-不包括物料列表
     * @param ivtNo
     * @return
     */
    public ExcuteInventoisesVoNew detailNew(String ivtNo){
        //根据盘点单查询仓库列表
        Inventories inventories = new Inventories();
        inventories.setIvtNo(ivtNo);
        inventories.setComCode(UserServletContext.getUserInfo().getComCode());
        inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Inventories> list = inventoriesDao.findList(inventories);

        ExcuteInventoisesVoNew voNew = new ExcuteInventoisesVoNew();
        BeanUtils.copyProperties(list.get(0),voNew);
        String whName = list.stream().map(Inventories::getWhName).collect(joining(","));
        voNew.setWhName(whName);
        return voNew;
    }


    /**
     * 编辑盘点前获取盘点仓库物料详情
     * @param ivtNo
     * @return
     */
    public InventoisesVoNew getInventByIvtNo(String ivtNo){
        //定义封装的实体类
        InventoisesVoNew inventoisesVoNew = new InventoisesVoNew();
        List<InventoriesWhMatVo> whMatVoList = new ArrayList<>();

        Inventories inventories = new Inventories();
        inventories.setIvtNo(ivtNo);
        inventories.setComCode(UserServletContext.getUserInfo().getComCode());
        inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Inventories> list = inventoriesDao.findList(inventories);

        //封装盘点信息
        inventoisesVoNew.setIvtNo(list.get(0).getIvtNo());
        inventoisesVoNew.setIvtTitle(list.get(0).getIvtTitle());
        inventoisesVoNew.setIvtPiJson(list.get(0).getIvtPiJson());
        inventoisesVoNew.setIvtPlanBegin(list.get(0).getIvtPlanBegin());
        inventoisesVoNew.setIvtPlanEnd(list.get(0).getIvtPlanEnd());

        //封装仓库信息
        list.stream().forEach(inventories1 -> {
            List<InventoiresMatVoNew1> matList = new ArrayList<>();
            InventoriesMat inventoriesMat = new InventoriesMat();
            inventoriesMat.setIvtId(ivtNo);
            inventoriesMat.setWhId(inventories1.getWhId());
            inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
            inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<InventoriesMat> list1 = inventoriesMatDao.findListByIvtNo(inventoriesMat);
            list1.stream().forEach((inventoriesMat1 -> {
                InventoiresMatVoNew1 inventoiresMatVoNew1= new InventoiresMatVoNew1();
                inventoiresMatVoNew1.setWhId(inventoriesMat1.getWhId());
                inventoiresMatVoNew1.setMatTypeId(inventoriesMat1.getMatTypeId());
                inventoiresMatVoNew1.setMatName(inventoriesMat1.getMatName());
                inventoiresMatVoNew1.setMatNo(inventoriesMat1.getMatNo()==null?"":inventoriesMat1.getMatNo());
                inventoiresMatVoNew1.setMatSpec(inventoriesMat1.getMatSpec()==null?"":inventoriesMat1.getMatSpec());
                inventoiresMatVoNew1.setMatTypeCode(inventoriesMat1.getMatTypeCode()==null?"":inventoriesMat1.getMatTypeCode());
                matList.add(inventoiresMatVoNew1);
            }));

            InventoriesWhMatVo inventoriesWhMatVo = new InventoriesWhMatVo();
            inventoriesWhMatVo.setWhId(inventories1.getWhId());
            inventoriesWhMatVo.setWhName(inventories1.getWhName());
            //查询仓库类型
            Warehouse warehouse = new Warehouse();
            warehouse.setId(inventories1.getWhId());
            warehouse.setComCode(UserServletContext.getUserInfo().getComCode());
            warehouse.setModuCode(UserServletContext.getUserInfo().getModuCode());
            Warehouse warehouse1= warehouseDao.get(warehouse);
            inventoriesWhMatVo.setWhType(warehouse1.getWhType());

            inventoriesWhMatVo.setInventoriesMatList(matList);

            whMatVoList.add(inventoriesWhMatVo);
        });

        inventoisesVoNew.setWhMatVoList(whMatVoList);

        return inventoisesVoNew;
    }


    public Page<InventoriesMatVo> matPage(String id, int pageNum, int pageSize) {
        Page<InventoriesMatVo> pageVo = new Page<InventoriesMatVo>();
        InventoriesMat mat = new InventoriesMat();
        Page<InventoriesMat> pageInfo = new Page<InventoriesMat>(pageNum, pageSize);
        mat.setPage(pageInfo);
        mat.setIvtId(id);
        mat.setComCode(UserServletContext.getUserInfo().getComCode());
        mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<InventoriesMat> page = pageInfo.setList(inventoriesMatDao.findList(mat));
        if(page != null){
            CopyHelper.copyObj(page, pageVo);
            if(page.getList() != null){
                List<InventoriesMatVo> matVoList = new ArrayList<InventoriesMatVo>();
                CopyHelper.copyList(page.getList(), matVoList, InventoriesMatVo.class);
                pageVo.setList(matVoList);
            }
        }
        return pageVo;
    }

    /**
     * 编辑状态
     * @param ivtNo
     * @return
     */
    public Boolean begin(String ivtNo) {
        Inventories inventories = new Inventories();
        inventories.setIvtNo(ivtNo);
        inventories.setComCode(UserServletContext.getUserInfo().getComCode());
        inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Inventories> list = inventoriesDao.findList(inventories);
        list.stream().forEach(inventories1 -> {
            Inventories entity = inventoriesDao.getById(inventories1.getId());
            if(entity == null){
                throw new BusinessException(ReturnType.ParamIllegal, "查询不到盘点数据");
            }
            if(entity.getMatStat() == 1){
                entity.setMatStat(2L);
                inventoriesDao.updateById(entity);
            }
        });
    	return true;
    }

    public Boolean del(String ivtNo) {
        Inventories inventories = new Inventories();
        inventories.setIvtNo(ivtNo);
        inventories.setComCode(UserServletContext.getUserInfo().getComCode());
        inventories.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Inventories> list = inventoriesDao.findList(inventories);
        if(list!=null && list.size() > 0){
            list.stream().forEach(inventories1 -> {
                inventoriesDao.deleteById(inventories1.getId());
            });
        }

        InventoriesMat inventoriesMat = new InventoriesMat();
        inventoriesMat.setIvtId(ivtNo);
        inventoriesMat.setComCode(UserServletContext.getUserInfo().getComCode());
        inventoriesMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<InventoriesMat> list1 = inventoriesMatDao.findList(inventoriesMat);
        if(list1!=null && list1.size() > 0){
            list1.stream().forEach(inventoriesMat1 -> {
                inventoriesMatDao.deleteById(inventoriesMat1.getId());
            });
        }

        return true;
    }

    public Boolean execSave(InventoriesParam param) {
        if(StringUtils.isBlank(param.getId())){
            throw new BusinessException(ReturnType.ParamIllegal, "盘点参数异常");
        }
        Date dt = new Date();
        //更新主表
        Inventories ivt = new Inventories();
        CopyHelper.copyObj(param, ivt);
        ivt.setUpdatedDt(dt);
        inventoriesDao.updateById(ivt);
        //更新子表
        if(param.getMatParamList() != null){
            InventoriesMat mat;
            for(InventoriesMatParam matParam : param.getMatParamList()){
                if(StringUtils.isBlank(matParam.getId())){
                    throw new BusinessException(ReturnType.ParamIllegal, "物料参数异常");
                }
                mat = new InventoriesMat();
                CopyHelper.copyObj(matParam, mat);
                mat.setUpdatedDt(dt);
                mat.setMatIsDone(1L);
                inventoriesMatDao.updateById(mat);
            }
        }
        //更新盘点状态
        int ivtCount = inventoriesExtDao.getcountUnIvt(ivt);
        if(ivtCount == 0){
            ivt.setMatStat(3L);
            inventoriesDao.updateById(ivt);
        }

        return true;
    }


    /**
     * 根据仓库Id查询该仓库下的物料信息
     * @param whId
     * @return
     */
    public List<WarehouseMat> findMatListByWhId(String whId){
        WarehouseMat warehouseMat = new WarehouseMat();
        warehouseMat.setWhId(whId);
        warehouseMat.setComCode(UserServletContext.getUserInfo().getComCode());
        warehouseMat.setModuCode(UserServletContext.getUserInfo().getModuCode());

        List<WarehouseMat> list = warehouseMatDao.findList(warehouseMat);

        return list;
    }
}
