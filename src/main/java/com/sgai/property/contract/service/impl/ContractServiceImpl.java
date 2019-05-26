package com.sgai.property.contract.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.contract.dao.ICGysSupplierDaoMapper;
import com.sgai.property.contract.dao.ICHtContractDao;
import com.sgai.property.contract.dao.IContractDao;
import com.sgai.property.contract.dao.IFileDao;
import com.sgai.property.contract.entity.GysSupplier;
import com.sgai.property.contract.entity.HtContract;
import com.sgai.property.contract.entity.HtFile;
import com.sgai.property.contract.service.ContractHtContractServiceImpl;
import com.sgai.property.contract.service.HtFileServiceImpl;
import com.sgai.property.contract.service.inteface.IContractService;
import com.sgai.property.contract.vo.ContractSearchParams;
import com.sgai.property.contract.vo.HtContractVO;
import com.sgai.property.contract.vo.HtFileVO;
import com.sgai.property.ctl.service.CtlComRuleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements IContractService {
    private static final Logger logger = LogManager.getLogger(ContractServiceImpl.class);
    @Autowired
    private ICGysSupplierDaoMapper gysSupplierDaoMapper;
    @Autowired
    private ContractHtContractServiceImpl htContractService;
    @Autowired
    private ICHtContractDao htContractDao;
    @Autowired
    private HtFileServiceImpl htFileService;
    @Autowired
    private IContractDao contractDao;
    @Autowired
    private IFileDao fileDao;
    @Autowired
    private CtlComRuleService ctlComRuleService;

    @Value("${mat.comCode}")
    private String comCode;
    @Value("${mat.sequCode}")
    private String sequCode;


    /**
     * 新增合同信息
     *
     * @param contractVO
     */
    @Override
    public void save(HtContractVO contractVO) {
        //获取编码
        String num = ctlComRuleService.getNext(comCode, sequCode);
        if (null == num) {
            throw new BusinessException(ReturnType.ParamIllegal, "合同编码获取失败!");
        }
        HtContract htContract = new HtContract();
        BeanUtils.copyProperties(contractVO, htContract);
        htContract.setCreater(contractVO.getCreater());
        htContract.setComCode(UserServletContext.getUserInfo().getComCode());
        htContract.setComName(UserServletContext.getUserInfo().getComName());
        //添加图片
        String[] arr = contractVO.getImages();
        if (null != contractVO && arr.length > 0) {
            int len = arr.length;
            if (len == 0) {
                htContract.setUrlA("");
                htContract.setUrlB("");
                htContract.setUrlC("");
            }
            if (len == 1) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB("");
                htContract.setUrlC("");
            }
            if (len == 2) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB(arr[1]);
                htContract.setUrlC("");
            }
            if (len == 3) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB(arr[1]);
                htContract.setUrlC(arr[2]);
            }
        }
        htContract.setIsDelete(0L);
        htContract.setNo(num);
        htContractService.save(htContract);

        //批量添加附加
        List<HtFileVO> fileVOS = contractVO.getFiles();
        List<HtFile> files = new ArrayList<>();
        if (null != fileVOS && fileVOS.size() > 0) {

            for (HtFileVO htFileVO : fileVOS) {
                if (StringUtils.isNotEmpty(htFileVO.getFileUrl())) {
                    HtFile htFile = new HtFile();
                    htFile.setFileName(htFileVO.getFileName());
                    htFile.setFileUrl(htFileVO.getFileUrl());
                    htFile.setContractId(htContract.getId());
                    htFile.setIsDelete(0L);
                    htFile.setMark(htFileVO.getMark());
                    files.add(htFile);
                    htFileService.save(htFile);
                }
            }
        }
    }


    /**
     * 查询对象
     *
     * @param id
     * @return
     */
    @Override
    public HtContractVO getById(String id) {
        HtContractVO htContractVO = new HtContractVO();
        HtContract htContract = new HtContract();
        List<GysSupplier> suppliers = new ArrayList<GysSupplier>();
        htContract.setId(id);
        htContract.setIsDelete(0L);
        HtContract htContract1 = htContractService.get(htContract);
        if (htContract1 != null) {
            BeanUtils.copyProperties(htContract1, htContractVO);
            if (htContract1.getSupplierIds() != null && !"".equals(htContract1.getSupplierIds())) {
                String[] supplierIds = htContract1.getSupplierIds().split(",");
                for (String supplierId : supplierIds) {
                    GysSupplier gysSupplier = new GysSupplier();
                    gysSupplier.setId(supplierId);
                    GysSupplier supplier = gysSupplierDaoMapper.getById(gysSupplier);
                    if (supplier != null && supplier.getName() != null && !"".equals(supplier.getName())) {
                        suppliers.add(supplier);
                    }
                }
            }
            //图片
            String[] arr = new String[3];
            arr[0] = htContract1.getUrlA();
            arr[1] = htContract1.getUrlB();
            arr[2] = htContract1.getUrlC();
            htContractVO.setImages(arr);
            htContractVO.setSuppliers(suppliers);
            /*if (StringUtils.isEmpty(htContract1.getUrlA()) && StringUtils.isEmpty(htContract1.getUrlB()) && StringUtils.isEmpty(htContract1.getUrlC())) {
               String[] arra = new String[0];
                htContractVO.setImages(arra);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlA()) && StringUtils.isEmpty(htContract1.getUrlB()) && StringUtils.isEmpty(htContract1.getUrlC())) {
                String[] arr = new String[1];
                arr[0] = htContract1.getUrlA();
                htContractVO.setImages(arr);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlB()) && StringUtils.isEmpty(htContract1.getUrlA()) && StringUtils.isEmpty(htContract1.getUrlC())) {
                String[] arr = new String[1];
                arr[0] = htContract1.getUrlB();
                htContractVO.setImages(arr);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlC()) && StringUtils.isEmpty(htContract1.getUrlA()) && StringUtils.isEmpty(htContract1.getUrlB())) {
                String[] arr = new String[1];
                arr[0] = htContract1.getUrlC();
                htContractVO.setImages(arr);
            }

            if (StringUtils.isNotEmpty(htContract1.getUrlA()) && StringUtils.isNotEmpty(htContract1.getUrlB()) && StringUtils.isEmpty(htContract1.getUrlC())) {
                String[] arr = new String[2];
                arr[0] = htContract1.getUrlA();
                arr[1] = htContract1.getUrlB();
                htContractVO.setImages(arr);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlA()) && StringUtils.isEmpty(htContract1.getUrlB()) && StringUtils.isNotEmpty(htContract1.getUrlC())) {
                String[] arr = new String[2];
                arr[0] = htContract1.getUrlA();
                arr[2] = htContract1.getUrlC();
                htContractVO.setImages(arr);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlC()) && StringUtils.isEmpty(htContract1.getUrlA()) && StringUtils.isNotEmpty(htContract1.getUrlB())) {
                String[] arr = new String[2];
                arr[0] = htContract1.getUrlB();
                arr[1] = htContract1.getUrlC();
                htContractVO.setImages(arr);
            }
            if (StringUtils.isNotEmpty(htContract1.getUrlC()) && StringUtils.isNotEmpty(htContract1.getUrlA()) && StringUtils.isNotEmpty(htContract1.getUrlB())) {
                String[] arr = new String[3];
                arr[0] = htContract1.getUrlA();
                arr[1] = htContract1.getUrlB();
                arr[2] = htContract1.getUrlC();
                htContractVO.setImages(arr);
            }*/
            //附件
            HtFile htFile = new HtFile();
            htFile.setContractId(htContract1.getId());
            List<HtFile> files = fileDao.getFileListByNo(htFile);
            List<HtFileVO> htFileVOS = new ArrayList<>();
            if (files != null && files.size() > 0) {
                for (HtFile file : files) {
                    if (StringUtils.isNotEmpty(file.getFileUrl()) && StringUtils.isNotEmpty(file.getFileName())) {
                        HtFileVO htFileVO = new HtFileVO();
                        BeanUtils.copyProperties(file, htFileVO);
                        htFileVO.setId(file.getId());
                        htFileVO.setMark(file.getMark());
                        htFileVOS.add(htFileVO);
                    }
                }
            }
            htContractVO.setFiles(htFileVOS);
        }
        return htContractVO;
    }

    /**
     * 更新合同信息
     *
     * @param htContractVO
     * @return
     */
    @Override
    public boolean updateById(HtContractVO htContractVO) {
        HtContract htContract = new HtContract();
        BeanUtils.copyProperties(htContractVO, htContract);
        htContract.setCreater(htContractVO.getCreater());
        //添加图片
        String[] arr = htContractVO.getImages();
        if (null != htContractVO && arr.length > 0) {
            int len = arr.length;
            if (len == 0) {
                htContract.setUrlA("");
                htContract.setUrlB("");
                htContract.setUrlC("");
            }
            if (len == 1) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB("");
                htContract.setUrlC("");
            }
            if (len == 2) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB(arr[1]);
                htContract.setUrlC("");
            }
            if (len == 3) {
                htContract.setUrlA(arr[0]);
                htContract.setUrlB(arr[1]);
                htContract.setUrlC(arr[2]);
            }
        }
        //全部删除附件
        HtFile htFile = new HtFile();
        htFile.setContractId(htContractVO.getId());
        List<HtFile> files = fileDao.getFileListByNo(htFile);
        if (null != files && files.size() > 0) {
            htFileService.batchDelete(files);
        }
        //附件
        List<HtFileVO> fileVOS = htContractVO.getFiles();
        if (null != fileVOS && fileVOS.size() > 0) {
            for (HtFileVO fileVO : fileVOS) {
                HtFile file = new HtFile();
                BeanUtils.copyProperties(fileVO, file);
                file.setId(fileVO.getId());
                file.setContractId(htContractVO.getId());
                file.setMark(fileVO.getMark());
                file.preInsert();
                fileDao.save(file);
            }
        }
        return htContractService.updateById(htContract);
    }

    /**
     * 删除合同信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateById(String id) {
        HtContract htContract = new HtContract();
        htContract.setId(id);
        htContract.setIsDelete(1L);
        HtContract contract = htContractService.getById(id);
        if (null != contract) {
            HtFile file = new HtFile();
            file.setContractId(id);
            List<HtFile> files = fileDao.getFileListByNo(file);
            if (null != files && files.size() > 0) {
                htFileService.batchDelete(files);
            }
        }
        return htContractService.updateById(htContract);
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<HtContractVO> findListPage(ContractSearchParams contractSearchParams, int pageNum, int pageSize) {

        HtContract htContract = new HtContract();
        if (!"".equals(contractSearchParams.getKeyWord())) {
            htContract.setName(contractSearchParams.getKeyWord());
        }
        if (!"0".equals(contractSearchParams.getTypeId())) {
            htContract.setTypeId(contractSearchParams.getTypeId());
        }
        if (contractSearchParams.getStatusId() != 0) {
            htContract.setStatus(contractSearchParams.getStatusId());
        }
        htContract.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<HtContract> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("created_dt desc");
        htContract.setPage(page);
        List<HtContract> htContracts = contractDao.findList(htContract);
        int count = contractDao.getCount(htContract);
        Page<HtContractVO> pageInfo = new Page<>();
        List<HtContractVO> list = new ArrayList<>();
        if (htContracts.size() > 0) {
            for (HtContract htc : htContracts) {
                HtContractVO htContractVO = new HtContractVO();
                BeanUtils.copyProperties(htc, htContractVO);
                if (htc.getCreatedDt() != null) {
                    htContractVO.setSingDate(htc.getCreatedDt().getTime());
                }

                list.add(htContractVO);
            }
        }
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public String getNumber() {
        return contractDao.getNumber();
    }

    @Override
    public HtContract getContractByName(HtContractVO htContractVO) {
        HtContract htContract = new HtContract();
        htContract.setIsDelete(0L);
        htContract.setName(htContractVO.getName());
        htContract.setModuCode(UserServletContext.getUserInfo().getModuCode());
        htContract.setComCode(UserServletContext.getUserInfo().getComCode());
        return htContractDao.get(htContract);
    }


}
