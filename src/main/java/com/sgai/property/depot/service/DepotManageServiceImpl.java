package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.dao.IWarehouseDao;
import com.sgai.property.depot.entity.Warehouse;
import com.sgai.property.depot.param.InventoryManageParam;
import com.sgai.property.depot.vo.InventoryManageVo;
import com.sgai.property.depot.vo.WarehouseParam;
import com.sgai.property.purchase.dao.IWarehouseMatDao;
import com.sgai.property.purchase.entity.WarehouseMat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * 仓库管理 on 2018/1/23.
 */
@Service
public class DepotManageServiceImpl {
    @Autowired
    IWarehouseDao iWarehouseDao;
    @Autowired
    IWarehouseMatDao iWarehouseMatDao;
    @Autowired
    private BaseCodeService baseCodeService;
    /**
     * describe: 这个人很懒也很帅，但是什么都没写~
     * className: DepotManageServiceImpl
     * methodName: depotLists
     * methodParam: [pageNo, pageSize]
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/23 16:15
     **/
    public Response<Page<Warehouse>> depotLists(String whName, String whAddress, Long whType, int pageNo, int pageSize) {
        Response<Page<Warehouse>> result = new Response<Page<Warehouse>>();
        Page<Warehouse> page = new Page<>(pageNo,pageSize);
        page.setOrderBy("CREATED_DT desc");
        Supplier<Warehouse> supplier = Warehouse::new;
        Warehouse wareHouse = supplier.get();
        if(whName != null && !"".equals(whName)){
            wareHouse.setWhName(whName);
        }
        if(whAddress!=null && !"".equals(whAddress)){
            wareHouse.setWhAddress(whAddress);
        }
        if(whType!=null){
            wareHouse.setWhType(whType);
        }
        wareHouse.setPage(page);
        wareHouse.setComCode(UserServletContext.getUserInfo().getComCode());
        wareHouse.setModuCode(UserServletContext.getUserInfo().getModuCode());
        page.setList(iWarehouseDao.findList(wareHouse));
        result.setData(page);
        return result ;
    }



    /**
     *@Author 杨鹏伟 【syswin.600111】
     *@Date 2018/1/11 10:36
     *@ClassName SuppliesOperationServiceImpl
     *@MethodName 生成仓库编号
     *@MethodParameters
     */
    private String getWhNo(){
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_iWarehouse);
        String seqStr = Long.toString(seq);
        for(int i = seqStr.length(); i <= 3; i ++){
            seqStr = "0" + seqStr;
        }
        return "CK-" + seqStr;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~新增或者修改
     * className: DepotManageServiceImpl
     * methodName: addDepot
     * methodParam: [access_token, warehouseParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/23 17:25
     **/
    public Response<Boolean> addOrUpdate(WarehouseParam warehouseParam) {
        Response<Boolean> result = new Response<>();
        //判断仓库名称是否重复
        Warehouse warehouse = new Warehouse();

        int ints = -1;
        if (StringUtils.isNotBlank(warehouseParam.getId() )){
            //ID有值修改
            warehouse.setWhName(warehouseParam.getWhName());
            warehouse.setComCode(UserServletContext.getUserInfo().getComCode());
            warehouse.setModuCode(UserServletContext.getUserInfo().getModuCode());
            Warehouse wh = iWarehouseDao.get(warehouse);
            if (wh != null ){
                if (!wh.getId().equals(warehouseParam.getId())){
                    throw new BusinessException(ReturnType.DB , "仓库名称被占用了！");
                }
            }
            BeanUtils.copyProperties(warehouseParam,warehouse);
            warehouse.setId(warehouseParam.getId());
            warehouse.setWhNo(warehouseParam.getWhNo());
            warehouse.setWhEmpName(UserServletContext.getUserInfo().getUserName());
            warehouse.setWhEmpId(UserServletContext.getUserInfo().getUserNo());
            warehouse.setWhEmpPhone(UserServletContext.getUserInfo().getUserPhone());
            ints = iWarehouseDao.updateById(warehouse);

        }else {
            //新增
            warehouse.setWhName(warehouseParam.getWhName());
            Warehouse wh = iWarehouseDao.get(warehouse);
            if (wh != null ){
                throw new BusinessException(ReturnType.DB , "仓库名称被占用了！");
            }
            BeanUtils.copyProperties(warehouseParam,warehouse);
            warehouse.setWhEmpName(UserServletContext.getUserInfo().getUserName());
            warehouse.setWhEmpId(UserServletContext.getUserInfo().getUserNo());
            warehouse.setWhEmpPhone(UserServletContext.getUserInfo().getUserPhone());
            warehouse.setId(UUID.randomUUID().toString());
            warehouse.setComCode(UserServletContext.getUserInfo().getComCode());
            warehouse.setModuCode(UserServletContext.getUserInfo().getModuCode());
            warehouse.setWhNo(getWhNo());
            warehouse.setCreatedDt(new Date());
            ints = iWarehouseDao.insert(warehouse);
        }
        if (ints == 0 ){
            result.setData(false);
        }
        result.setData(true);
        return result ;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~详情
     * className: DepotManageServiceImpl
     * methodName: depotDetil
     * methodParam: [toonCode, id]
     * return: com.sgai.property.depot.vo.Response<com.sgai.property.depot.entity.Warehouse>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/23 18:46
     **/
    public Response<Warehouse> depotDetil(String id) {
//        Warehouse wh = new Warehouse();
//        wh.setId(id);
        Warehouse warehouse = iWarehouseDao.getById(id);
        if (warehouse == null ){
            throw new BusinessException(ReturnType.DB , "没有详细数据，请联系管理员！");
        }
        Response<Warehouse> result = new Response<>();
        result.setData(warehouse);
        return result ;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~删除
     * className: DepotManageServiceImpl
     * methodName: deleteDepot
     * methodParam: [toonCode, id]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/23 19:46
     **/
    public Response<Boolean> deleteDepot(String id) {
        Response<Boolean> result = new Response<>();
        //查询仓库名称
        Warehouse warehouse = iWarehouseDao.getById(id);
        if (warehouse ==  null ){
            throw new BusinessException(ReturnType.DB,"仓库已删除！");
        }
        WarehouseMat mat = new WarehouseMat();
        mat.setWhId(id);
        mat.setComCode(UserServletContext.getUserInfo().getComCode());
        mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //是否有关联
        List<WarehouseMat> warehouseMat = iWarehouseMatDao.findList(mat);
        if (warehouseMat.size() > 0 ){
            throw new BusinessException(ReturnType.DB , "'"+warehouse.getWhName()+"'有储存物料关联，不能删除！");
        }
        //删除
        int ids = iWarehouseDao.deleteById(id);

        if (ids == 1){
            result.setData(true);
        } else {
            result.setData(false);
            result.setMessage("系统繁忙，删除失败");
        }
        return  result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~根据仓库类型选择仓库
     * className: DepotManageServiceImpl
     * methodName: byWhType
     * methodParam: [whType]
     * return: com.sgai.property.depot.vo.Response<java.util.List<com.sgai.property.depot.entity.Warehouse>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 10:36
     **/
    public Response<List<Warehouse>> byWhType(String whType) {
        Response<List<Warehouse>> result  = new Response<>();

        Warehouse wh = new Warehouse();
        wh.setWhType(Long.parseLong(whType));
        wh.setComCode(UserServletContext.getUserInfo().getComCode());
        wh.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<Warehouse> whlist = iWarehouseDao.findList(wh);
        result.setData(whlist);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~库存列表
     * className: DepotManageServiceImpl
     * methodName: inventoryManages
     * methodParam: [itMp, pageNo, pageSize]
     * return: com.sgai.property.depot.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.depot.vo.InventoryManageVo>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/5 14:55
     **/
    public Response<Page<InventoryManageVo>> inventoryManages(InventoryManageParam itMp, int pageNo, int pageSize) {
        Response<Page<InventoryManageVo>> result = new Response<>();
        Page<InventoryManageVo> page = new Page<>(pageNo,pageSize);
        InventoryManageVo manageVo = new InventoryManageVo();
        if (StringUtils.isNotBlank(itMp.getMatName())){
            String matName= "%"+itMp.getMatName()+"%";
            manageVo.setMatName(matName);
        }
        if (StringUtils.isNotBlank(itMp.getWhName())){
            String whName= "%"+itMp.getWhName()+"%";
            manageVo.setWhName(whName);
        }
        manageVo.setComCode(UserServletContext.getUserInfo().getComCode());
        manageVo.setModuCode(UserServletContext.getUserInfo().getModuCode());
        manageVo.setPage(page);
        page.setList(iWarehouseDao.inventoryManages(manageVo));
        result.setData(page);
        return result;
    }
}
