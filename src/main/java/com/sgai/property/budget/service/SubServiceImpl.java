package com.sgai.property.budget.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.constants.Constants;
import com.sgai.property.budget.entity.Subject;
import com.sgai.property.budget.entity.TemplateSubjectItem;
import com.sgai.property.budget.vo.SubjectEditParam;
import com.sgai.property.budget.vo.SubjectSaveParam;
import com.sgai.property.budget.vo.SubjectVo;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubServiceImpl {

	@Autowired
	private SubjectServiceImpl subjectService;
	@Autowired
	private TemplateSubjectItemServiceImpl templateSubjectItemService;

	public SubjectVo getSubjectDetail(String subjectId) {
		SubjectVo vo = null;
		if (StringUtils.isNotEmpty(subjectId)) {
			Subject subject = subjectService.getById(subjectId);
			if (subject != null) {
				vo = new SubjectVo();
				BeanUtils.copyProperties(subject, vo);
				if(subject.getLevels().longValue() == 2) {
					Subject subjectLevel1 = subjectService.getById(subject.getParentId());
					if(subjectLevel1 != null) {						
						vo.setLevelOneName(subjectLevel1.getShortName());
					}
				} else if (subject.getLevels().longValue() == 3) {
					Subject subjectLevel2 = subjectService.getById(subject.getParentId());
					if(subjectLevel2 != null) {						
						vo.setLevelTwoName(subjectLevel2.getShortName());
						Subject subjectLevel1 = subjectService.getById(subjectLevel2.getParentId());
						if(subjectLevel1 != null) {						
							vo.setLevelOneName(subjectLevel1.getShortName());
						}
					}
				}
			}
		}
		return vo;
	}

	public List<SubjectVo> getSubjectList() {
		List<SubjectVo> result = null;
		Subject subject = new Subject();
		subject.setIsDelete(0L);
		subject.setModuCode(UserServletContext.getUserInfo().getModuCode());
		subject.setComCode(UserServletContext.getUserInfo().getComCode());
		Page<Subject> page = new Page<>();
		page.setOrderBy(" create_time desc ");
		subject.setPage(page);
		List<Subject> subjectList = subjectService.findList(subject);
		if (!CollectionUtils.isEmpty(subjectList)) {
			result = new ArrayList<>();
			handleSubject(result, subjectList);
		}
		return result;
	}

	public void handleSubject(List<SubjectVo> result, List<Subject> subjectList) {
		List<SubjectVo> level1List = new ArrayList<>();
		List<SubjectVo> level2List = new ArrayList<>();
		List<SubjectVo> level3List = new ArrayList<>();
		for (int i = 0; i < subjectList.size(); i++) {
			Subject subject = subjectList.get(i);
			SubjectVo vo = new SubjectVo();
			BeanUtils.copyProperties(subject, vo);
			if (vo.getLevels().longValue() == 1) {
				level1List.add(vo);
			} else if (vo.getLevels().longValue() == 2) {
				level2List.add(vo);
			} else if (vo.getLevels().longValue() == 3) {
				level3List.add(vo);
			}
		}
		for (int i = 0; i < level2List.size(); i++) {
			SubjectVo subject2 = level2List.get(i);
			List<SubjectVo> list = new ArrayList<>();
			for (int j = 0; j < level3List.size(); j++) {
				SubjectVo subject3 = level3List.get(j);
				if (subject3.getParentId().equals(subject2.getId())) {
					list.add(subject3);
				}
			}
			subject2.setChildList(list);
		}
		for (int i = 0; i < level1List.size(); i++) {
			SubjectVo subject1 = level1List.get(i);
			List<SubjectVo> list = new ArrayList<>();
			for (int j = 0; j < level2List.size(); j++) {
				SubjectVo subject2 = level2List.get(j);
				if (subject2.getParentId().equals(subject1.getId())) {
					list.add(subject2);
				}
			}
			subject1.setChildList(list);
		}
		result.addAll(level1List);
	}

	@Transactional
	public boolean subjectAdd(SubjectSaveParam subject) {
		Long timeStamp = System.currentTimeMillis();
		Subject sub = new Subject();
		BeanUtils.copyProperties(subject, sub);
		if (StringUtils.isNotEmpty(subject.getParentId()) && !(subject.getParentId().equals("-1"))) {
			Subject parent = subjectService.getById(subject.getParentId());
			if (parent != null) {
				sub.setCodeNumber(parent.getCodeNumber() + Constants.Separate.LINE_THROUGH + generateCode(timeStamp));
				sub.setCreateTime(timeStamp);
				sub.setLevels(parent.getLevels() + 1);
				sub.setLongName(parent.getLongName() + Constants.Separate.CLOCKWISE_SLANT + subject.getShortName());
				sub.setOrgId(UserServletContext.getUserInfo().getComCode());
				sub.setUpdateTime(timeStamp);
				

				sub.setComCode(UserServletContext.getUserInfo().getComCode());
				sub.setModuCode(UserServletContext.getUserInfo().getModuCode());
			} else {
				throw new BusinessException(ReturnType.Error, "没有查到父科目信息");
			}
		} else {
			sub.setCodeNumber(generateCode(timeStamp));
			sub.setCreateTime(timeStamp);
			sub.setLevels(1L);
			sub.setLongName(subject.getShortName());
			sub.setOrgId(UserServletContext.getUserInfo().getComCode());
			sub.setUpdateTime(timeStamp);
			

			sub.setComCode(UserServletContext.getUserInfo().getComCode());
			sub.setModuCode(UserServletContext.getUserInfo().getModuCode());
		}
		subjectService.save(sub);
		return true;
	}

	public String generateCode(Long param) {
		return Long.toHexString(param).toUpperCase();
	}

	@Transactional
	public boolean subjectDelete(String subjectId) {
		if (StringUtils.isNotEmpty(subjectId)) {
			Subject subject = new Subject();
			subject.setParentId(subjectId);
			subject.setIsDelete(0L);
			List<Subject> childList = subjectService.findList(subject);
			if (CollectionUtils.isEmpty(childList)) {
				TemplateSubjectItem templateSubjectItem = new TemplateSubjectItem();
				templateSubjectItem.setSubId(subjectId);
				List<TemplateSubjectItem> dataList = templateSubjectItemService.findList(templateSubjectItem);
				if (CollectionUtils.isEmpty(dataList)) {
					Subject sub = new Subject();
					sub.setId(subjectId);
					sub.setIsDelete(1L);
					subjectService.updateById(sub);
				} else {
					throw new BusinessException(ReturnType.Error, "该科目已关联费项，无法删除");
				}
			} else {
				throw new BusinessException(ReturnType.Error, "该科目下存在子科目，无法删除");
			}
		}
		return true;
	}

	@Transactional
	public boolean subjectEdit(SubjectEditParam subject) {
		//subject传入的parentId不对，传的是他本身的id，前台需要更改
		Subject sub = subjectService.getById(subject.getId());
		if (sub != null) {
			sub.setShortName(subject.getShortName());
			sub.setDescription(subject.getDescription());
			//根据parentId设置LongName
			if (sub.getParentId().equals("-1")) {
				sub.setLongName(subject.getShortName());
			} else {
				Subject parent = subjectService.getById(sub.getParentId());
				sub.setLongName(parent.getLongName() + Constants.Separate.CLOCKWISE_SLANT + subject.getShortName());
			}
			subjectService.updateById(sub);
			childrenEdit(sub.getId());
		}
		return true;
	}

	@Transactional
	public void childrenEdit(String subjectId) {

		Subject parent = subjectService.getById(subjectId);

		Subject sub = new Subject();
		sub.setParentId(subjectId);
		List<Subject> children = subjectService.findList(sub);

		if (children.size() > 0) {//存在下级
			for (int i = 0; i < children.size(); i++) {
				Subject child = children.get(i);
				//子级取上级LongName设置自己的LongName
				child.setLongName(parent.getLongName() + Constants.Separate.CLOCKWISE_SLANT + child.getShortName());
				subjectService.updateById(child);
				childrenEdit(child.getId());
			}
		}
	}

}