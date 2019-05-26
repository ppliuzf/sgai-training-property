package com.sgai.property.contract.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.contract.dao.ICHtContractDao;
import com.sgai.property.contract.entity.HtContract;

@Service
public class ContractHtContractServiceImpl extends MoreDataSourceCrudServiceImpl<ICHtContractDao,HtContract>{
	@Autowired
	private ICHtContractDao htContractDao;

}