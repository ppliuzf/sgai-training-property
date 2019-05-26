package com.sgai.property.supplier.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysSupplierDaoMapper;
import com.sgai.property.supplier.entity.*;
import com.sgai.property.supplier.service.*;
import com.sgai.property.supplier.service.inteface.SupplierService;
import com.sgai.property.supplier.vo.SupplierDetailsVO;
import com.sgai.property.supplier.vo.SupplierParams;
import com.sgai.property.supplier.vo.SupplierVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractSupplierServiceImpl implements SupplierService {

    @Autowired
    private IGysSupplierDaoMapper supplierDaoMapper;
    @Autowired
    private GysSupplierServiceImpl gysSupplierService;
    @Autowired
    private GysFileServiceImpl fileService;
    @Autowired
    private GysTypeServiceImpl typeService;
    @Autowired
    private GysContentServiceImpl contentService;
    @Autowired
    private GysLevelServiceImpl levelService;

    /**
     * f分页查询供应商信息
     * @param supplierParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<SupplierVO> findList(SupplierParams supplierParams, int pageNum, int pageSize) {
        GysSupplier gysSupplier = new GysSupplier();
        if (!"".equals(supplierParams.getKeyWord()) && supplierParams.getKeyWord() !=null){
            gysSupplier.setName(supplierParams.getKeyWord());
        }
        if (!"0".equals(supplierParams.getTypeId()) && supplierParams.getTypeId() !=null) {
            gysSupplier.setTypeId(supplierParams.getTypeId());
        }
        if (!"0".equals(supplierParams.getContentId()) && supplierParams.getContentId() !=null) {
            gysSupplier.setContentId(supplierParams.getContentId());
        }
        if (!"0".equals(supplierParams.getLevelId()) && supplierParams.getLevelId() != null) {
            gysSupplier.setLevelId(supplierParams.getLevelId());
        }
        gysSupplier.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<GysSupplier> page = new Page<>(pageNum,pageSize);
        page.setOrderBy("CREATED_DT desc");
        gysSupplier.setPage(page);
        List<GysSupplier> supplierVOS = supplierDaoMapper.findList(gysSupplier);
        int count = supplierDaoMapper.getCount(gysSupplier);
        Page<SupplierVO> pageInfo = new Page<>(pageNum,pageSize);
        List<SupplierVO> supplierVOList = new ArrayList<>();
        if (supplierVOS.size()>0) {
            for (GysSupplier supplier : supplierVOS) {
                SupplierVO supplierVO = new SupplierVO();
                BeanUtils.copyProperties(supplier,supplierVO);

                GysType type = typeService.getById(supplier.getTypeId());
                if (type != null) {
                    supplierVO.setTypeId(type.getId());
                    supplierVO.setTypeName(type.getName());
                }
                GysContent content = contentService.getById(supplier.getContentId());
                if (content != null) {
                    supplierVO.setContentId(content.getId());
                    supplierVO.setContentName(content.getName());
                }
                GysLevel gysLevel = levelService.getById(supplier.getLevelId());
                if (gysLevel !=null) {
                    supplierVO.setLevelId(gysLevel.getId());
                    supplierVO.setLevelName(gysLevel.getName());
                }
                supplierVOList.add(supplierVO);
            }
        }
        pageInfo.setCount(count);
        pageInfo.setList(supplierVOList);
        return pageInfo;
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public SupplierDetailsVO getSupplierDetails(String id) {
        SupplierDetailsVO supplierDetailsVO = new SupplierDetailsVO();

        GysSupplier supplier = gysSupplierService.getById(id);

        GysFile gysFile = new GysFile();
        gysFile.setSupplierId(id);
        List<GysFile> files = fileService.findList(gysFile);

        if (null != supplier) {
            BeanUtils.copyProperties(supplier,supplierDetailsVO);
        }
        if (files.size()>0) {
            supplierDetailsVO.setFileList(files);
        }
        return supplierDetailsVO;
    }

    /**
     * 更新供应商信息
     * @param supplierDetailsVO
     * @return
     */
    @Override
    public boolean updateById(SupplierDetailsVO supplierDetailsVO) {
        GysSupplier supplier = new GysSupplier();
        BeanUtils.copyProperties(supplierDetailsVO,supplier);
        //删除附件
        GysFile gysFile1 = new GysFile();
        gysFile1.setSupplierId(supplierDetailsVO.getId());
        List<GysFile> files = fileService.findList(gysFile1);
        if (files !=null && files.size()>0) {
            fileService.batchDelete(files);
        }
        List<GysFile> fileList = supplierDetailsVO.getFileList();
        if (fileList !=null && fileList.size()>0) {
            for (GysFile file : fileList) {
                if (!"".equals(file.getFileUrl())) {
                    GysFile gysFile = new GysFile();
                    gysFile.setSupplierId(supplierDetailsVO.getId());
                    gysFile.setFileName(file.getFileName());
                    gysFile.setFileUrl(file.getFileUrl());
                    gysFile.setIsDelete(0L);
//                gysFile.preInsert();
                    fileService.save(gysFile);
                }

            }
        }
        return gysSupplierService.updateById(supplier);
    }
}
