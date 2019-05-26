package com.sgai.property.budget.service;

import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.dao.IRecordDaoVo;
import com.sgai.property.budget.dao.ITemplateDaoVo;
import com.sgai.property.budget.dao.ITemplateSubjectItemDao;
import com.sgai.property.budget.dao.ITemplateSubjectItemDaoVo;
import com.sgai.property.budget.entity.*;
import com.sgai.property.budget.vo.*;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BudgetServiceImpl {

	@Autowired
	private PRecordServiceImpl recordService;
	@Autowired
	private IRecordDaoVo recordVoDao;
	@Autowired
	private ITemplateSubjectItemDao tsiDao;
	@Autowired
	private ITemplateSubjectItemDaoVo tsiDaoVo;
	@Autowired
	private ITemplateDaoVo templateDaoVo;
	@Autowired
	private TemplateServiceImpl templateService;
	@Autowired
	private RecordSubjectItemServiceImpl rsiService;
	@Autowired
	private RecordTemplateServiceImpl recordTemplateService;
	@Autowired
	private InputServiceImpl inputService;
	@Autowired
	private InputDataServiceImpl inputDataService;

	public Page<RecordVo> budgetRecordList(int pageNum, int pageSize) {
		Page<Record> page = new Page<>(pageNum, pageSize);
		page.setOrderBy(" create_time desc ");
		Record record = new Record();
		record.setIsDelete(0L);
		record.setPage(page);
		record.setComCode(UserServletContext.getUserInfo().getComCode());
		List<Record> recordList = recordService.findList(record);
		List<RecordVo> recordVoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(recordList)) {
			for (int i = 0; i < recordList.size(); i++) {
				RecordVo vo = new RecordVo();
				BeanUtils.copyProperties(recordList.get(i), vo);
				recordVoList.add(vo);
			}
		}
		Page<RecordVo> pageVo = new Page<>();
		BeanUtils.copyProperties(page, pageVo);
		pageVo.setList(recordVoList);
		return pageVo;
	}

	public Page<RecordVo> budgetRecordSearchList(int pageNum, int pageSize,
												 RecordSearchParam searchParam) {
		Page<Record> page = new Page<>(pageNum, pageSize);
		page.setOrderBy(" create_time desc ");
		Record record = new Record();
		if (!StringUtils.isEmpty(searchParam.getRecordName()) 
				&& searchParam.getRecordName().trim().length() > 0) {
			String recordName = searchParam.getRecordName()
					.replaceAll("\\\\", "\\\\\\\\")
					.replaceAll("%", "/%")
					.replaceAll("_", "\\\\_");
			record.setRecordName("%" + recordName + "%");
		}
		if (!StringUtils.isEmpty(searchParam.getCreatorEiEmpName()) 
				&& searchParam.getCreatorEiEmpName().trim().length() > 0) {
			String creatorEiEmpName = searchParam.getCreatorEiEmpName()
					.replaceAll("\\\\", "\\\\\\\\")
					.replaceAll("%", "/%")
					.replaceAll("_", "\\\\_");
			record.setCreatorEiEmpName("%" + creatorEiEmpName + "%");
		}
		if (!StringUtils.isEmpty(searchParam.getYear()) 
				&& searchParam.getYear().trim().length() > 0) {
			record.setYear(searchParam.getYear());
		}
		record.setCycle(searchParam.getCycle());
		record.setState(searchParam.getState());
		record.setIsDelete(0L);
		record.setPage(page);
		record.setComCode(UserServletContext.getUserInfo().getComCode());
		record.setModuCode(UserServletContext.getUserInfo().getModuCode());
		List<Record> recordList = recordVoDao.getRecordBySearch(record);
		List<RecordVo> recordVoList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(recordList)) {
			for (int i = 0; i < recordList.size(); i++) {
				RecordVo vo = new RecordVo();
				BeanUtils.copyProperties(recordList.get(i), vo);
				recordVoList.add(vo);
			}
		}
		Page<RecordVo> pageVo = new Page<>();
		BeanUtils.copyProperties(page, pageVo);
		pageVo.setList(recordVoList);
		return pageVo;
	}

	public List<TemplateResponseByCycle> getTemplateByCycle(Long cycle) {
		List<TemplateResponseByCycle> list = new ArrayList<>();
		List<String> templateIdList = tsiDaoVo.getTemplateIds();
		if (!CollectionUtils.isEmpty(templateIdList)) {
			Map<String, Object> map = new HashMap<>();
			map.put("cycle", cycle);
			map.put("state", 0L);
			map.put("isDelete", 0L);
			map.put("ids", templateIdList);
			
			map.put("comCode", UserServletContext.getUserInfo().getComCode());
			map.put("moduCode", UserServletContext.getUserInfo().getModuCode());
			
			List<Template> templateList = templateDaoVo.getTemplateByCycle(map);
			if (!CollectionUtils.isEmpty(templateList)) {
				for (int i = 0; i < templateList.size(); i++) {
					TemplateResponseByCycle resp = new TemplateResponseByCycle();
					resp.setId(templateList.get(i).getId());
					resp.setTemplateName(templateList.get(i).getTemplateName());
					list.add(resp);
				}
			}
		}
		return list;
	}

	@Transactional
	public String recordAdd(RecordSaveParam param) {
		Record record = new Record();
		try {
			Long timeStamp = System.currentTimeMillis();
			BeanUtils.copyProperties(param, record);
			record.setCreatorEiId(UserServletContext.getUserInfo().getUserNo());
			record.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
			record.setOrgId(UserServletContext.getUserInfo().getComCode());
			record.setCreateTime(timeStamp);
			record.setUpdateTime(timeStamp);
			record.setComCode(UserServletContext.getUserInfo().getComCode());
			record.setModuCode(UserServletContext.getUserInfo().getModuCode());
			
			recordService.save(record);
			if (!StringUtils.isEmpty(record.getId())) {
				Template template = templateService.getById(param.getTemplateId());
				RecordTemplate recordTemplate = new RecordTemplate();
				recordTemplate.setRecordId(record.getId());
				recordTemplate.setTemplateData(template.getContent());
				recordTemplate.setComCode(record.getComCode());
				recordTemplate.setModuCode(record.getModuCode());
				recordTemplateService.save(recordTemplate);

				TemplateSubjectItem tsi = new TemplateSubjectItem();
				
				tsi.setComCode(record.getComCode());
				tsi.setModuCode(record.getModuCode());
				
				tsi.setTemplateId(param.getTemplateId());
				Page<TemplateSubjectItem> page = new Page<>();
				page.setOrderBy(" item_order ");
				tsi.setPage(page);
				
				
				List<TemplateSubjectItem> tsiList = tsiDao.findList(tsi);
				if (!CollectionUtils.isEmpty(tsiList)) {
					for (int i = 0; i < tsiList.size(); i++) {
						TemplateSubjectItem templateData = tsiList.get(i);
						RecordSubjectItem recordData = new RecordSubjectItem();
						BeanUtils.copyProperties(templateData, recordData,
                                "id", "isDelete", "createTime", "updateTime", "remarks", "createdBy",
                                "createdDt", "updatedBy", "updatedDt", "delFlag", "version");
						recordData.setRecordId(record.getId());
						recordData.setCreateTime(timeStamp);
						recordData.setUpdateTime(timeStamp);
						recordData.setComCode(record.getComCode());
						recordData.setModuCode(record.getModuCode());
						rsiService.save(recordData);
					}
				}
			}
		} catch (BeansException e) {
			throw new BusinessException(ReturnType.Error, "发起计划出错");
		}
		return record.getId();
	}

	@Transactional
	public boolean recordEdit(RecordEditParam param) {
		try {
			Long timeStamp = System.currentTimeMillis();
			Record record = new Record();
			BeanUtils.copyProperties(param, record);
			record.setUpdateTime(timeStamp);
			recordService.updateById(record);
		} catch (BeansException e) {
			throw new BusinessException(ReturnType.Error, "编辑计划出错");
		}
		return true;
	}

	public RecordVo budgetDetail(String recordId) {
		Record record = recordService.getById(recordId);
		RecordVo vo = null;
		if (record != null) {
			vo = new RecordVo();
			BeanUtils.copyProperties(record, vo);
		}
		return vo;
	}

	public JSONObject budgetTableHeader(String recordId) {
		RecordTemplate recordTemplate = new RecordTemplate();
		recordTemplate.setRecordId(recordId);
		recordTemplate = recordTemplateService.get(recordTemplate);
		JSONObject vo = null;
		if (recordTemplate != null) {
			vo = JSONObject.parseObject(recordTemplate.getTemplateData());
		}
		return vo;
	}

	@Transactional
	public boolean inputBudget(InputBudgetParam param) {
		try {
			Long timeStamp = System.currentTimeMillis();
			Input input = new Input();
			input.setRecordId(param.getRecordId());
			input.setCreatorType(1L);
			input.setCreatorEiId(UserServletContext.getUserInfo().getUserNo());
			input.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
			input.setCreateTime(timeStamp);
			input.setUpdateTime(timeStamp);

			input.setComCode(UserServletContext.getUserInfo().getComCode());
			input.setModuCode(UserServletContext.getUserInfo().getModuCode());
			inputService.save(input);

			if (!StringUtils.isEmpty(input.getId())) {
				List<InputBudgetData> entryList = param.getEntryList();
				if (!CollectionUtils.isEmpty(entryList)) {
					for (int i = 0; i < entryList.size(); i++) {
						InputBudgetData data = entryList.get(i);
						InputData inputData = new InputData();
						inputData.setBudget(data.getBudget());
						inputData.setInputId(input.getId());
						inputData.setRsiId(data.getRsiId());
						inputData.setCreateTime(timeStamp);
						inputData.setUpdateTime(timeStamp);

						inputData.setComCode(UserServletContext.getUserInfo().getComCode());
						inputData.setModuCode(UserServletContext.getUserInfo().getModuCode());
						inputDataService.save(inputData);
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ReturnType.Error, "录入预算出错");
		}
		return true;
	}

	@Transactional
	public boolean inputExpend(InputExpendParam param) {
		try {
			Long timeStamp = System.currentTimeMillis();
			Input input = new Input();
			input.setRecordId(param.getRecordId());
			input.setCreatorType(2L);
			input.setCreatorEiId(UserServletContext.getUserInfo().getUserNo());
			input.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
			input.setCreateTime(timeStamp);
			input.setUpdateTime(timeStamp);

			input.setComCode(UserServletContext.getUserInfo().getComCode());
			input.setModuCode(UserServletContext.getUserInfo().getModuCode());
			inputService.save(input);

			if (!StringUtils.isEmpty(input.getId())) {
				List<InputExpendData> entryList = param.getEntryList();
				if (!CollectionUtils.isEmpty(entryList)) {
					for (int i = 0; i < entryList.size(); i++) {
						InputExpendData data = entryList.get(i);
						InputData inputData = new InputData();
						inputData.setSurplus(data.getSurplus());
						inputData.setExpend(data.getExpend());
						inputData.setBudget(data.getBudget());
						inputData.setInputId(input.getId());
						inputData.setRsiId(data.getRsiId());
						inputData.setCreateTime(timeStamp);
						inputData.setUpdateTime(timeStamp);

						inputData.setComCode(UserServletContext.getUserInfo().getComCode());
						inputData.setModuCode(UserServletContext.getUserInfo().getModuCode());
						inputDataService.save(inputData);
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ReturnType.Error, "录入支出出错");
		}
		return true;
	}

	public List<RowVo> budgetTableContent(String recordId) {
		RecordSubjectItem rsi = new RecordSubjectItem();
		rsi.setRecordId(recordId);
		Page<RecordSubjectItem> page = new Page<>();
		page.setOrderBy(" item_order ");
		rsi.setPage(page);
		
		rsi.setComCode(UserServletContext.getUserInfo().getComCode());
		rsi.setModuCode(UserServletContext.getUserInfo().getModuCode());
		List<RecordSubjectItem> rsiList = rsiService.findList(rsi);
		List<RowVo> voList = null;
		if (!CollectionUtils.isEmpty(rsiList)) {
			voList = new ArrayList<>();

			Input input = new Input();
			input.setRecordId(recordId);
			Page<Input> page2 = new Page<>();
			page2.setOrderBy(" create_time desc ");
			input.setPage(page2);

			input.setComCode(UserServletContext.getUserInfo().getComCode());
			input.setModuCode(UserServletContext.getUserInfo().getModuCode());
			List<Input> inputList = inputService.findList(input);
			List<InputData> inputDataList = null;
			if (!CollectionUtils.isEmpty(inputList)) {
				input = inputList.get(0);
				InputData inputData = new InputData();
				inputData.setInputId(input.getId());

				inputData.setComCode(input.getComCode());
				inputData.setModuCode(input.getModuCode());
				inputDataList = inputDataService.findList(inputData);
			}

			for (int i = 0; i < rsiList.size(); i++) {
				RecordSubjectItem item = rsiList.get(i);
				RowVo vo = new RowVo();
				BeanUtils.copyProperties(item, vo);
				vo.setRsiId(item.getId());
				if (!CollectionUtils.isEmpty(inputDataList)) {
					for (int j = 0; j < inputDataList.size(); j++) {
						InputData inputData = inputDataList.get(j);
						if (vo.getRsiId().equals(inputData.getRsiId())) {
							vo.setBudget(inputData.getBudget());
							vo.setSurplus(inputData.getSurplus());
							vo.setExpend(inputData.getExpend());
						}
					}
				}
				voList.add(vo);
			}
		}
		if (!CollectionUtils.isEmpty(voList)) {
			for (int i = 0; i < voList.size(); i++) {
				RowVo vo = voList.get(i);
				if (StringUtils.isEmpty(vo.getBudget())) {
					vo.setBudget("");
				}
				if (StringUtils.isEmpty(vo.getSurplus())) {
					vo.setSurplus("");
				}
				if (StringUtils.isEmpty(vo.getExpend())) {
					vo.setExpend("");
				}
			}
		}
		return voList;
	}

	public List<RowVo> inputDetail(String recordId, String inputId) {
		RecordSubjectItem rsi = new RecordSubjectItem();
		rsi.setRecordId(recordId);
		Page<RecordSubjectItem> page = new Page<>();
		page.setOrderBy(" item_order ");
		rsi.setPage(page);
		
		rsi.setComCode(UserServletContext.getUserInfo().getComCode());
		rsi.setModuCode(UserServletContext.getUserInfo().getModuCode());
		List<RecordSubjectItem> rsiList = rsiService.findList(rsi);
		List<RowVo> voList = null;
		if (!CollectionUtils.isEmpty(rsiList)) {
			voList = new ArrayList<>();

			List<InputData> inputDataList = null;
			InputData inputData = new InputData();
			inputData.setInputId(inputId);
			inputData.setComCode(UserServletContext.getUserInfo().getComCode());
			inputData.setModuCode(UserServletContext.getUserInfo().getModuCode());
			
			inputDataList = inputDataService.findList(inputData);

			for (int i = 0; i < rsiList.size(); i++) {
				RecordSubjectItem item = rsiList.get(i);
				RowVo vo = new RowVo();
				BeanUtils.copyProperties(item, vo);
				vo.setRsiId(item.getId());
				if (!CollectionUtils.isEmpty(inputDataList)) {
					for (int j = 0; j < inputDataList.size(); j++) {
						inputData = inputDataList.get(j);
						if (vo.getRsiId().equals(inputData.getRsiId())) {
							vo.setBudget(inputData.getBudget());
							vo.setSurplus(inputData.getSurplus());
							vo.setExpend(inputData.getExpend());
						}
					}
				}
				voList.add(vo);
			}
		}
		if (!CollectionUtils.isEmpty(voList)) {
			for (int i = 0; i < voList.size(); i++) {
				RowVo vo = voList.get(i);
				if (StringUtils.isEmpty(vo.getBudget())) {
					vo.setBudget("");
				}
				if (StringUtils.isEmpty(vo.getSurplus())) {
					vo.setSurplus("");
				}
				if (StringUtils.isEmpty(vo.getExpend())) {
					vo.setExpend("");
				}
			}
		}
		return voList;
	}

}