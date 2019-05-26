package com.sgai.property.quality.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.TaskUtil;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.*;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.*;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IPlanServiceImpl{
	@Autowired
	private QCommonServicelmpl commonService;
	@Autowired
	private IQtTestPlanDao testPlanDao;
	@Autowired
	private IQtTestItemDao testItemDao;
	@Autowired
	private IQtPlanItemGroupDao planItemGroupDao;
	@Autowired
	private IQtPlanItemDao planItemDao;
	@Autowired
	private IQtProfessionalCategoryDao professionalCategoryDao;
	@Autowired
	private IQtTestTaskDao testTaskDao;
	@Autowired
	private ITaskDao taskDao;

	public Page<PlanVo> list(String name, String pcId, Long startTime, Long endTime,
							 Integer type, Integer pageNum, Integer pageSize) {
		Page pageInfo=new Page<>(pageNum, pageSize);

		PlanVo planVo=new PlanVo();
		if(StringUtils.isNotEmpty(name)) {
			planVo.setName(name);
		}
		if(StringUtils.isNotEmpty(pcId)) {
			planVo.setPcId(pcId);
		}
		planVo.setStartTime(startTime == null ?0L:startTime);
		planVo.setEndTime(endTime == null ? System.currentTimeMillis():(endTime+60*60*24*1000-1));
		planVo.setValid(Constants.VALID_YES);
		planVo.setPage(pageInfo);
		planVo.setComCode(UserServletContext.getUserInfo().getComCode());
		if (type ==null) {
			planVo.setTypeFlag(0);
		}else {
			planVo.setTypeFlag(type);
		}
		pageInfo.setList(testPlanDao.findListByMap(planVo));
		List<PlanVo> list = pageInfo.getList();
		for (PlanVo plan : list) {
			QtProfessionalCategory pc = professionalCategoryDao.getById(plan.getPcId());
			if(pc!=null){
				plan.setProfessionalCategory(pc.getPcName());
			}
		}
		return pageInfo;
	}

	@Transactional
	public Boolean deletePlanById(String id) {
		QtTestPlan testPlan = testPlanDao.getById(id);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id!");
		}
		// 查询在该模板中任务项是否存在
		/*QtPlanItem planItemQuery = new QtPlanItem();
		planItemQuery.setTpId(id);
		planItemQuery.setValid(Constants.VALID_YES);
		List<QtPlanItem> planItemList = planItemDao.findList(planItemQuery);*/
		//查询关联计划的任务模板
		Task task = new Task();
		task.setTemplateId(id);
		List<Task> taskList = taskDao.findListByTemplateId(task);
		if (taskList != null && taskList.size()>0) {
			throw new BusinessException(ReturnType.ParamIllegal, "当前任务模板有任务正在进行，不能删除!");
		}
		
		testPlan.setUpdateTime(System.currentTimeMillis());
		testPlan.setValid(Constants.VALID_NO);
		testPlan.preUpdate();
		testPlanDao.updateById(testPlan);
		//更新时间
		updatePlanTime(testPlan.getId());
		return true;
	}
	@Transactional
	public String addPlan(String name, String pcId, String description, Integer type) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pcId)) {
			throw new BusinessException(ReturnType.ParamIllegal, "参数有误!");
		}
		QtProfessionalCategory pc = professionalCategoryDao.getById(pcId);
		if (pc == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "任务专业不存在!");
		}
		QtTestPlan planQuery = new QtTestPlan();
		planQuery.setTpName(name);
		planQuery.setTiId(pcId);
		planQuery.setValid(Constants.VALID_YES);
		List<QtTestPlan> queryList = testPlanDao.findList(planQuery);
		if(queryList.size()>0){
			throw new BusinessException(ReturnType.Error, "相同的任务专业模板名称不能重复");
		}
		QtTestPlan plan = new QtTestPlan();
		plan.setTpName(name);
		plan.setTiId(pcId);
		plan.setValid(Constants.VALID_YES);
		plan.setTiName(pc.getPcName());
		plan.setTiDescription(description);
		plan.setCreateEiId(UserServletContext.getUserInfo().getEmCode());
		plan.setComId(UserServletContext.getUserInfo().getCompanyId());
		plan.setCreateEiName(UserServletContext.getUserInfo().getUserName());
		plan.setCreateTime(System.currentTimeMillis());
		plan.setUpdateTime(plan.getCreateTime());
		plan.preInsert();
		plan.setComCode(UserServletContext.getUserInfo().getComCode());
		if (type ==null) {
			plan.setTypeFlag(0);
		}else {
			plan.setTypeFlag(type);
		}
		testPlanDao.insert(plan);
		// 添加未分组
		QtPlanItemGroup normalGroup = new QtPlanItemGroup();
		normalGroup.setTpId(plan.getId());
		normalGroup.setPigName("未分组");
		normalGroup.setPigSort(QtPlanItemGroup.NORMAL_SORT);// 默认第一组
		normalGroup.setCreateTime(System.currentTimeMillis());
		normalGroup.setUpdateTime(normalGroup.getCreateTime());
		normalGroup.preInsert();
		planItemGroupDao.insert(normalGroup);
		return plan.getId();
	}

	public PlanItemVo planDetail(String planId) {
		//方案信息
		QtTestPlan plan = testPlanDao.getById(planId);
		if(plan==null){
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id");
		}
		//任务专业信息
		QtProfessionalCategory pc = professionalCategoryDao.getById(plan.getTiId());
		if (pc == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "该模板没有关联任务专业");
		}
		PlanItemVo vo=new PlanItemVo();
		vo.setId(plan.getId());
		vo.setName(plan.getTpName());
		vo.setPcId(pc.getId());
		vo.setPcName(pc.getPcName());
		vo.setDescription(plan.getTiDescription());

		QtPlanItem qtPlanItem = new QtPlanItem();
		qtPlanItem.setTpId(planId);
		Page<QtPlanItem> page=new Page<>(1,Integer.MAX_VALUE);
//		page.setOrderBy("pig_sort desc");
		qtPlanItem.setPage(page);
		qtPlanItem.setValid(Constants.VALID_YES);
		List<QtPlanItem> groupList = planItemDao.findList(qtPlanItem);
		List<QtPlanItem> list=new ArrayList<>();
		if (groupList.size()>0) {
			for (QtPlanItem itemGroup : groupList) {
				list.add(itemGroup);
			}
		}
		//查询方案的分组
		/*QtPlanItemGroup groupQuery=new QtPlanItemGroup();
		groupQuery.setTpId(planId);
		Page<QtPlanItemGroup> page=new Page<>(1,Integer.MAX_VALUE);
		page.setOrderBy("pig_sort desc");
		groupQuery.setPage(page);
		groupQuery.setValid(Constants.VALID_YES);
		List<QtPlanItemGroup> groupList = planItemGroupDao.findList(groupQuery);
		List<PlanItemVo.PlanItem> list=new ArrayList<>();
		for (QtPlanItemGroup itemGroup : groupList) {
			//查询组下关联的检验项
			QtPlanItem query=new QtPlanItem();
			query.setGroupId(itemGroup.getId());
			query.setValid(Constants.VALID_YES);
			Page<QtPlanItem> itemGroupPage=new Page<>(1,Integer.MAX_VALUE);
			itemGroupPage.setOrderBy("group_sort desc");
			query.setPage(itemGroupPage);
			List<QtPlanItem> findList = planItemDao.findList(query);
			for (QtPlanItem item : findList) {
				list.add(createTestItem(itemGroup, item));
			}
		}*/
		vo.setList(list);
		return vo;
	}

	private PlanItemVo.PlanItem createTestItem(QtPlanItemGroup itemGroup, QtPlanItem item) {
		PlanItemVo.PlanItem planItem = new PlanItemVo.PlanItem();
		planItem.setId(item.getId());
		planItem.setHtiId(item.getTiId());
		planItem.setName(item.getPiName());
		planItem.setGroupId(itemGroup.getId());
		planItem.setGroupName(itemGroup.getPigName());
		planItem.setStandard(item.getPiStandard());
		planItem.setAnswerTypeDesc(TaskUtil.getItemTypeDesc(item.getPiIsInput()));
		planItem.setCreateName(item.getCreateName());
		planItem.setCreateTime(item.getCreateTime());
		return planItem;
	}

	public Boolean unlink(String id) {
		QtPlanItem planItem = planItemDao.getById(id);
		if (planItem == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的任务项id");
		}
		planItem.setValid(Constants.VALID_NO);
		planItemDao.updateById(planItem);
		// 设置是否关联检验项
		setHasRelItem(planItem.getTpId());
		//更新时间
		updatePlanTime(planItem.getTpId());
		return true;
	}
	@Transactional
	public Boolean linkPlan(String id, String planId) {
		QtTestItem testItem = testItemDao.getById(id);
		if (testItem == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的任务项");
		}
		QtTestPlan testPlan = testPlanDao.getById(planId);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id");
		}
		// 查询在该模板中任务项是否存在
		QtPlanItem planItemQuery = new QtPlanItem();
		planItemQuery.setTiId(id);// 原始检验项id
		planItemQuery.setTpId(planId);
		List<QtPlanItem> planItemList = planItemDao.findList(planItemQuery);
		// 获取默认未分组信息
		QtPlanItemGroup normalGroup = getNormalGroup(planId);
		// 获取默认组中最大排序号
		Integer maxSort = getGroupMaxSort(normalGroup.getId());
		if (planItemList.size() > 0) {// 存在，进行修改
			QtPlanItem qtPlanItem = planItemList.get(0);
			if (qtPlanItem.getValid().equals(Constants.VALID_YES)) {
				throw new BusinessException(ReturnType.Error, "该任务项已经关联过了！！！");
			}
			qtPlanItem.setGroupId(normalGroup.getId());
			qtPlanItem.setGroupSort(maxSort + 1);// 排序加1
			qtPlanItem.setValid(Constants.VALID_YES);//存在要将数据改为有效
			qtPlanItem.preUpdate();
			planItemDao.updateById(qtPlanItem);
		} else {// 不存在，保存
			QtPlanItem planItem = new QtPlanItem();
			planItem.setPiName(testItem.getTiName());
			planItem.setTpId(planId);
			planItem.setPcId(testItem.getPcId());
			planItem.setPcName(testItem.getPcName());
			planItem.setTiId(testItem.getId());
			planItem.setPiStandard(testItem.getTiStandard());
			planItem.setPiOptions(testItem.getTiOptions());
			planItem.setPiStandardOption(testItem.getTiStandardOption());
			planItem.setPiMax(testItem.getTiMax());
			planItem.setPiMin(testItem.getTiMin());
			planItem.setPiUnit(testItem.getTiUnit());
			planItem.setPiIsInput(testItem.getTiIsInput());
			planItem.setPiLimitTime(testItem.getTiLimitTime());
			planItem.setPiFinshUnit(testItem.getTiFinshUnit());
			planItem.setPostId(testItem.getTiPostId());
			planItem.setPostName(testItem.getTiPostName());
			planItem.setPiRectificationRequirements(testItem.getTiRectificationRequirements());
			planItem.setCreateEiId(testItem.getCreateEiId());
			planItem.setCreateName(testItem.getCreateEiName());
			planItem.setGroupId(normalGroup.getId());
			planItem.setGroupSort(maxSort + 1);// 序号加1
			planItem.setCreateTime(testItem.getCreateTime());
			planItem.setUpdateTime(System.currentTimeMillis());
			planItem.preInsert();
			planItemDao.insert(planItem);
		}
		// 设置是否关联检验项
		setHasRelItem(planId);
		//更新时间
		updatePlanTime(testPlan.getId());
		return true;
	}
	/**关联多个任务项*/
	@Transactional(rollbackFor = Exception.class)
	public Boolean batchLinksPlan(String[] ids, String planId) {
		if (ids != null && ids.length>0) {
			for(int i = 0; i<ids.length;i++){
				String id = ids[i];
				linkPlan(id, planId);
			}
		}else{
			throw  new BusinessException(ReturnType.ParamIllegal, "任务项id为null");
		}
		return true;

	}
	

	/**
	 * 设置该模板是有关联任务项
	 */
	private void setHasRelItem(String planId) {
		QtPlanItem query = new QtPlanItem();
		query.setTpId(planId);
		query.setValid(Constants.VALID_YES);
		/**模板项*/
		int count = planItemDao.getCount(query);
		QtTestPlan updateItem = new QtTestPlan();
		updateItem.setId(planId);
		if (count > 0) {
			updateItem.setHasRelItem(QtTestPlan.HAS_REL_ITEM);
		} else {
			updateItem.setHasRelItem(QtTestPlan.NO_REL_ITEM);
		}
		updateItem.preUpdate();
		/**模板*/
		testPlanDao.updateById(updateItem);
	}
	@Transactional
	public Boolean addGroup(String planId, String name) {
		//
		if (StringUtils.isEmpty(name)) {
			throw new BusinessException(ReturnType.ParamIllegal, "方案名称不能为空！");
		}
		QtTestPlan testPlan = testPlanDao.getById(planId);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的方案id");
		}
		//更新时间
		updatePlanTime(testPlan.getId());
		// 查询是否有相同的组名
		QtPlanItemGroup groupQuery = new QtPlanItemGroup();
		groupQuery.setTpId(planId);
		groupQuery.setPigName(name);
		List<QtPlanItemGroup> groupList = planItemGroupDao.findList(groupQuery);
		// 查到该方案中组的最大序号,然后要将该组的序号改为最大的
		QtPlanItemGroup sortQuery = new QtPlanItemGroup();
		Integer sort = planItemGroupDao.getMaxSort(sortQuery);
		if (groupList.size() > 0) {
			QtPlanItemGroup itemGroup = groupList.get(0);
			if (itemGroup.getValid().equals(Constants.VALID_NO)) {// 存在组相同名称的组但该组无效
				itemGroup.setValid(Constants.VALID_YES);
				itemGroup.setPigSort(sort + 1);
				planItemGroupDao.updateById(itemGroup);
			} else if (itemGroup.getValid().equals(Constants.VALID_YES)) {
				throw new BusinessException(ReturnType.Error, "不能添加相同的组名");
			}

		} else {
			QtPlanItemGroup newGroup = new QtPlanItemGroup();
			newGroup.setTpId(planId);
			newGroup.setPigName(name);
			newGroup.setPigSort(sort + 1);
			newGroup.setCreateTime(System.currentTimeMillis());
			newGroup.setUpdateTime(newGroup.getCreateTime());
			newGroup.preInsert();
			planItemGroupDao.insert(newGroup);
		}
		return true;
	}
	@Transactional
	public Boolean deleteGroup(String groupId) {
		QtPlanItemGroup itemGroup = planItemGroupDao.getById(groupId);
		if (itemGroup == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "组的id不存在");
		}
		if (itemGroup.getPigSort().equals(QtPlanItemGroup.NORMAL_SORT)) {
			throw new BusinessException(ReturnType.Error, "未分组不能删除");
		}
		
		// 获取该组中关联的检查项
		QtPlanItem query = new QtPlanItem();
		query.setGroupId(itemGroup.getId());
		query.setValid(Constants.VALID_YES);
		List<QtPlanItem> item = planItemDao.findList(query);
		if(item.size()>0){
			throw new BusinessException(ReturnType.Error, "当前组下有检验项,请先移动至其他组再进行删除");
		}
		// 将这条数据设置无效
		itemGroup.setValid(Constants.VALID_NO);
		itemGroup.preUpdate();
		planItemGroupDao.updateById(itemGroup);
		//更新时间
		updatePlanTime(itemGroup.getTpId());
/*		// 获取默认未分组信息
		QtPlanItemGroup normalGroup = getNormalGroup(itemGroup.getTpId());
		// 获取默认组最大排序
		Integer maxSort = getGroupMaxSort(normalGroup.getPigId());
		// 将组中的关联项移动至未分组
		for (QtPlanItem planItem : item) {
			planItem.setGroupId(normalGroup.getPigId());
			planItem.setGroupSort(++maxSort);// 加1
			planItemDao.updateById(planItem);
		}*/
		return true;
	}

	private QtPlanItemGroup getNormalGroup(String planId) {
		QtPlanItemGroup result = null;
		QtPlanItemGroup groupQuery = new QtPlanItemGroup();
		groupQuery.setTpId(planId);
		groupQuery.setPigSort(QtPlanItemGroup.NORMAL_SORT);
		List<QtPlanItemGroup> groupList = planItemGroupDao.findList(groupQuery);
		if (groupList.size() > 0) {
			result = groupList.get(0);
		} else {
			throw new BusinessException(ReturnType.Error, "该模板的默认组数据异常");
		}
		return result;
	}

	/**
	 * 获得组中检查项的最大排序号
	 * 
	 * @param groupId
	 * @return
	 */
	private Integer getGroupMaxSort(String groupId) {
		Integer maxSort = 0;
		QtPlanItem sortQuery = new QtPlanItem();
		sortQuery.setGroupId(groupId);
		Integer sort = planItemDao.getMaxSort(sortQuery);
		if (sort != null) {
			maxSort = sort;
		}
		return maxSort;
	}

	public Page<UnLinkItemVo> unlinkList(String planId, String name, Integer type, Integer pageNum,
										 Integer pageSize) {
		QtTestPlan testPlan = testPlanDao.getById(planId);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id");
		}

		Long comId = UserServletContext.getUserInfo().getCompanyId();
		// 先查出已经关联的检验项id
		QtPlanItem qtPlanItem = new QtPlanItem();
		qtPlanItem.setTpId(planId);
		qtPlanItem.setValid(Constants.VALID_YES);
		List<String> relIds = testPlanDao.findRelIds(qtPlanItem);
		Page pageInfo=new Page<>(pageNum, pageSize);

		UnLinkItemVo unLinkItemVo=new UnLinkItemVo();
		if(!StringUtils.isEmpty(name)) {
			unLinkItemVo.setName(name); // 检查项名称
		}
		unLinkItemVo.setPcId(testPlan.getTiId()); // 专业范畴
		unLinkItemVo.setIdList(relIds); // 方案id
		unLinkItemVo.setValid(Constants.VALID_YES);
		unLinkItemVo.setPage(pageInfo);
		unLinkItemVo.setComId(comId);
		if (type ==null) {
			unLinkItemVo.setTypeFlag(0);
		}else {
			unLinkItemVo.setTypeFlag(type);
		}

		// 获取未关联检验项
		pageInfo.setList(testPlanDao.getUnLinkList(unLinkItemVo));
		List<UnLinkItemVo> list = pageInfo.getList();
		for (UnLinkItemVo item : list) {
			item.setAnswerTypeDesc(TaskUtil.getItemTypeDesc(item.getAnswerType()));
		}
		return pageInfo;
	}
	@Transactional
	public Boolean groupUpOrDown(String upId, String downId) {
		QtPlanItemGroup upGroup = planItemGroupDao.getById(upId);
		QtPlanItemGroup downGroup = planItemGroupDao.getById(downId);
		if (upGroup == null || downGroup == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "组id参数错误");
		}
		if (!upGroup.getTpId().equals(downGroup.getTpId())) {
			throw new BusinessException(ReturnType.ParamIllegal, "不属于同一个模板，不能移动");
		}
		if (upGroup.getPigSort().equals(QtPlanItemGroup.NORMAL_SORT)
				|| downGroup.getPigSort().equals(QtPlanItemGroup.NORMAL_SORT)) {
			throw new BusinessException(ReturnType.Error, "未分组不能进行移动");
		}
		//更新时间
		updatePlanTime(upGroup.getTpId());
		//更新序号
		Integer sort = upGroup.getPigSort();
		upGroup.setPigSort(downGroup.getPigSort());
		downGroup.setPigSort(sort);
		planItemGroupDao.updateById(upGroup);
		planItemGroupDao.updateById(downGroup);
		return true;
	}
	@Transactional
	public Boolean itemUpOrDown(String upId, String downId) {
		QtPlanItem upItem = planItemDao.getById(upId);
		QtPlanItem downItem = planItemDao.getById(downId);
		if (upItem == null || downItem == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "任务项id参数错误");
		}
		if(!upItem.getTpId().equals(downItem.getTpId())){
			throw new BusinessException(ReturnType.ParamIllegal, "任务项不属于同一个模板");
		}
		if(!upItem.getGroupId().equals(downItem.getGroupId())){
			throw new BusinessException(ReturnType.ParamIllegal, "任务项不属于同一个组");
		}
		//更新时间
		updatePlanTime(upItem.getTpId());
		//更新序号
		Integer sort = upItem.getGroupSort();
		upItem.setGroupSort(downItem.getGroupSort());
		downItem.setGroupSort(sort);
		planItemDao.updateById(upItem);
		planItemDao.updateById(downItem);
		return true;
	}
	@Transactional
	public Boolean moveToOtherGroup(String itemId, String groupId) {
		QtPlanItem item = planItemDao.getById(itemId);
		QtPlanItemGroup group = planItemGroupDao.getById(groupId);
		// 判断检查项和组是否是同一个方案
		if (item == null || group == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "参数错误");
		}
		if(!item.getTpId().equals(group.getTpId())){
			throw new BusinessException(ReturnType.ParamIllegal, "任务项和组不属于同一模板");
		}
		//设置更新时间
		updatePlanTime(item.getTpId());
		// 获取目标组中最大排序
		Integer maxSort = getGroupMaxSort(groupId);
		item.setGroupId(groupId);
		item.setGroupSort(maxSort + 1);
		planItemDao.updateById(item);
		return true;
	}
	/**
	 * 更新方案的更新时间,用于排序
	 * @param planId
	 */
	private void updatePlanTime(String planId){
		QtTestPlan plan = new QtTestPlan();
		plan.setId(planId);
		plan.setUpdateTime(System.currentTimeMillis());
		testPlanDao.updateById(plan);
	}

	public List<OptionVo> groupList(String itemId) {
		QtPlanItem item = planItemDao.getById(itemId);
		QtPlanItemGroup query = new QtPlanItemGroup();
		if (item == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存的在任务项id");
		}
		query.setTpId(item.getTpId());
		query.setValid(Constants.VALID_YES);
		Page<QtPlanItemGroup> page=new Page<>(1,Integer.MAX_VALUE);
		page.setOrderBy("pig_sort desc");
		query.setPage(page);
		List<OptionVo> optionList = new ArrayList<>();
		List<QtPlanItemGroup> groupList = planItemGroupDao.findList(query);
		for (QtPlanItemGroup group : groupList) {
			OptionVo groupItem = new OptionVo();
			groupItem.setId(group.getId());
			groupItem.setName(group.getPigName());
			if(!item.getGroupId().equals(group.getId())){//当前检查项的组不添加
				optionList.add(groupItem);
			}
		}
		return optionList;
	}

	public List<GroupingVo> grouping(String planId) {
		List<GroupingVo> result = new ArrayList<>();
		// 方案信息
		QtTestPlan plan = testPlanDao.getById(planId);
		if (plan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存的在模板id");
		}
		QtPlanItemGroup groupQuery = new QtPlanItemGroup();
		groupQuery.setTpId(planId);
		groupQuery.setValid(Constants.VALID_YES);
		Page<QtPlanItemGroup> page=new Page<>(1,Integer.MAX_VALUE);
 		page.setOrderBy("pig_sort desc");
		groupQuery.setPage(page);
		List<QtPlanItemGroup> groupList = planItemGroupDao.findList(groupQuery);
		for (QtPlanItemGroup group : groupList) {
			GroupingVo groupVo=new GroupingVo();
			groupVo.setId(group.getId());
			groupVo.setName(group.getPigName());
			// 查询组下关联的检验项
			QtPlanItem query = new QtPlanItem();
			query.setGroupId(group.getId());
			query.setValid(Constants.VALID_YES);
			Page<QtPlanItem> itemPage=new Page<>(1,Integer.MAX_VALUE);
			itemPage.setOrderBy("group_sort desc");
			query.setPage(itemPage);
			List<QtPlanItem> itemList = planItemDao.findList(query);
			List<GroupingVo.GroupItem> list= new ArrayList<>();
			for (QtPlanItem item : itemList) {
				GroupingVo.GroupItem newItem=new GroupingVo.GroupItem();
				newItem.setId(item.getId());
				newItem.setName(item.getPiName());
				newItem.setStandard(item.getPiStandard());
				newItem.setAnswerTypeDesc(TaskUtil.getItemTypeDesc(item.getPiIsInput()));
				list.add(newItem);
			}
			groupVo.setList(list);
			result.add(groupVo);
		}
		return result;
	}

	public TaskStatusUncheckedVo listByTpId(String tpId) {

		QtTestPlan testPlan = testPlanDao.getById(tpId);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id");
		}
		// 查询在该模板中任务项是否存在
		QtPlanItem planItemQuery = new QtPlanItem();
		planItemQuery.setTpId(tpId);
		planItemQuery.setValid(Constants.VALID_YES);
		List<QtPlanItem> planItemList = planItemDao.findList(planItemQuery);
		if (planItemList == null ||planItemList.isEmpty()) {
			throw new BusinessException(ReturnType.ParamIllegal, "模板id没有关联任务项");
		}
		List<String> list  = new ArrayList<String>();
		for(int i =0 ; i<planItemList.size();i++){
			QtPlanItem planItem = planItemList.get(i);
//				createTestItem(itemGroup, planItem)
			String taskId = planItem.getTiId();
			
			list.add(taskId);
		}
		List<QtTestItem> testItemList   = testItemDao.getByListId(list);
		if (testItemList == null || testItemList.isEmpty()) {
			throw new BusinessException(ReturnType.ParamIllegal, "没有相关任务项");
		}
		String uncheckedID =  "";
		List<TaskStatusVo> list_taskStatusVo = new ArrayList<TaskStatusVo>();
		for(int i = 0 ;i <testItemList.size();i++){
			TaskStatusVo taskStatusVo = new TaskStatusVo();
			QtTestItem testItem = testItemList.get(i);
			taskStatusVo.setTaskId(testItem.getId());
			taskStatusVo.setTaskName(testItem.getTiName());
			taskStatusVo.setCreateTime(testItem.getCreateTime());
			taskStatusVo.setStatus(testItem.getTiStatus());
			taskStatusVo.setDesc(TaskUtil.getTaskItemStatusDesc(testItem.getTiStatus()));
			list_taskStatusVo.add(taskStatusVo);
			if ("".equals(uncheckedID)) {
				if (testItem.getTiStatus() ==0) {
					uncheckedID = testItem.getId();
				}
			}
		}
		TaskStatusUncheckedVo uncheckedVo = new TaskStatusUncheckedVo();
		uncheckedVo.setUncheckedTaskId(uncheckedID);
		uncheckedVo.setLists(list_taskStatusVo);
//		return
		
//		if (taskItem == null) {
//			throw new BusinessException(ReturnType.ParamIllegal, "不存在的任务项");
//		}else {
//		}
		return uncheckedVo;
	}

}
