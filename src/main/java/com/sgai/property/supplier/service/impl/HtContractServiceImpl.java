package com.sgai.property.supplier.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysSupplierDao;
import com.sgai.property.supplier.dao.IHtContractDao;
import com.sgai.property.supplier.dao.IHtFileDao;
import com.sgai.property.supplier.dao.IHtTypeDao;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.entity.HtContract;
import com.sgai.property.supplier.entity.HtFile;
import com.sgai.property.supplier.entity.HtType;
import com.sgai.property.supplier.service.inteface.HtContractService;
import com.sgai.property.supplier.vo.ContractDetailsVO;
import com.sgai.property.supplier.vo.ContractVO;
import com.sgai.property.supplier.vo.HtFileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class HtContractServiceImpl implements HtContractService {
	@Autowired
	private IHtContractDao htContractDao;
	@Autowired
	private IHtTypeDao htTypeDao;
	@Autowired
	private IGysSupplierDao gysSupplierDao;
	@Autowired
	private IHtFileDao htFileDao;


	@Override
	public Page<ContractVO> findPageList(String id, int pageNum, int pageSize) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy MM dd");

		HtContract htContract = new HtContract();
		htContract.setComCode(UserServletContext.getUserInfo().getComCode());
		if(UserServletContext.getUserInfo().getModuCode()!=null && !"".equals(UserServletContext.getUserInfo().getModuCode())){
			htContract.setModuCode(UserServletContext.getUserInfo().getModuCode());
		}
		htContract.setSupplierIds(id);
		Page<HtContract> page = new Page<>(pageNum,pageSize);
		page.setOrderBy("CREATED_DT desc");
		htContract.setPage(page);
		List<HtContract> contracts = htContractDao.findPageList(htContract);
		int count = htContractDao.getCount(htContract);
		Page<ContractVO> pageInfo = new Page<>(pageNum,pageSize);
		List<ContractVO> supplierVOList = new ArrayList<>();
		if (contracts.size()>0) {
			for (HtContract contract : contracts) {
				ContractVO contractVO = new ContractVO();
				BeanUtils.copyProperties(contract,contractVO);
				HtType type = htTypeDao.getById(contract.getTypeId());
				if (type!=null) {
					contractVO.setTypeName(type.getTypeName());
				}
				contractVO.setCreatTime(simpleDateFormat.format(contract.getCreatedDt()));
				supplierVOList.add(contractVO);
			}
		}
		pageInfo.setCount(count);
		pageInfo.setList(supplierVOList);
		return pageInfo;
	}

	@Override
	public ContractDetailsVO getById(String id) {
		ContractDetailsVO htContractVO = new ContractDetailsVO();
		List<String> supplierNames = new ArrayList<String>();
		HtContract htContract1 =  htContractDao.getById(id);
		if (htContract1 != null) {
			BeanUtils.copyProperties(htContract1,htContractVO);
			if(htContract1.getSupplierIds()!=null && !"".equals(htContract1.getSupplierIds())){
				String [] supplierIds = htContract1.getSupplierIds().split(",");
				for(String supplierId:supplierIds){
					GysSupplier supplier = gysSupplierDao.getById(supplierId);
					if(supplier!=null && supplier.getName()!=null && !"".equals(supplier.getName())){
						supplierNames.add(supplier.getName());
					}
				}
			}
			//图片
			String[] arr = new String[3];
			arr[0] = htContract1.getUrlA();
			arr[1] = htContract1.getUrlB();
			arr[2] = htContract1.getUrlC();
			htContractVO.setImages(arr);
			htContractVO.setSupplierNames(supplierNames);

			//附件
			HtFile htFile = new HtFile();
			htFile.setContractId(htContract1.getId());
			List<HtFile> files = htFileDao.getFileListById(htFile);
			List<HtFileVO> htFileVOS = new ArrayList<>();
			if (files != null && files.size()>0){
				for (HtFile file : files) {
					if (StringUtils.isNotEmpty(file.getFileUrl()) && StringUtils.isNotEmpty(file.getFileName())) {
						HtFileVO htFileVO = new HtFileVO();
						BeanUtils.copyProperties(file,htFileVO);
						htFileVO.setId(file.getId());
						htFileVO.setMark(file.getMark());
						htFileVOS.add(htFileVO);
					}
				}
			}
			htContractVO.setFiles(htFileVOS);
		}
		return  htContractVO;

	}
}