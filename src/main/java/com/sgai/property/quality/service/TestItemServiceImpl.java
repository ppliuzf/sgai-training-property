package com.sgai.property.quality.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.IdGen;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.common.util.QtBeanMapper;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.*;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.QtPlanItem;
import com.sgai.property.quality.entity.QtPlanItemEnclosure;
import com.sgai.property.quality.entity.QtTaskResult;
import com.sgai.property.quality.entity.QtTestItem;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.vo.TestItemDetailVo;
import com.sgai.property.quality.vo.TestItemVo;
import com.sgai.property.quality.vo.dto.TestItemDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestItemServiceImpl {

    @Autowired
    QCommonServicelmpl commonService;
    @Autowired
    IQtTestItemDao testItemDao;
    @Autowired
    IQtPlanItemEnclosureDao enclosureDao;
    @Autowired
    EnclosureServiceImpl enclosureService;
    @Autowired
    IPlanServiceImpl planService;

    @Autowired
    IQtTestTaskDao testTaskDao;

    @Autowired
    ITaskDao taskDao;
    @Autowired
    IQtPlanItemDao planItemDao;
    @Autowired
    IQtTaskResultDao taskResultDao;
    @Autowired
    IQtTestPlanDao testPlanDao;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;

    @Transactional
    public boolean createTestItem(TestItemDto testItemDto) {
        //先判断同一个专业范畴下，检验项名称是否重复
        if (StringUtils.isEmpty(testItemDto.getTiName())) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务项名称不可为空！");
        }
        QtTestItem testItem = new QtTestItem();
        testItem.setTiName(testItemDto.getTiName().trim());
        testItem.setPcId(testItemDto.getPcId());
        testItem.setValid(Constants.VALID_YES);
        List<QtTestItem> items = testItemDao.findList(testItem);
        if (CollectionUtils.isNotEmpty(items)) {
            throw new BusinessException(ReturnType.ParamIllegal, "相同的任务专业任务项名称不能重复！");
        }

        //如果是数值型，存的时候要乘以100
        if (testItemDto.getTiIsInput() == 0) {
            long tiMin = (long) (Double.valueOf(testItemDto.getTiMin()) * 100);
            long tiMax = (long) (Double.valueOf(testItemDto.getTiMax()) * 100);
            testItemDto.setTiMin(tiMin + "");
            testItemDto.setTiMax(tiMax + "");
        }
        QtBeanMapper.copy(testItemDto, testItem);

        testItem.setTiName(testItemDto.getTiName().trim());

        Long comId = UserServletContext.getUserInfo().getCompanyId();
        String creatorName = UserServletContext.getUserInfo().getUserName();

        testItem.setComId(comId);
        testItem.setCreateEiId(UserServletContext.getUserInfo().getEmCode());
        testItem.setCreateEiName(creatorName);
        testItem.setTiStatus(0);
        testItem.setCreateTime(System.currentTimeMillis());
        testItem.setUpdateTime(System.currentTimeMillis());
        testItem.preInsert();
        testItem.setComCode(UserServletContext.getUserInfo().getComCode());
        testItem.setTypeFlag(testItemDto.getTaskType());
        testItemDao.insert(testItem);
        /**添加任务项的时候添加qt_test_task表 ,主要是设置状态*/
/*		QtTestTask testTask = new QtTestTask();
		BeanUtils.copyProperties(testItem, testTask);
		testTask.setTtStatus(0);
		testTaskDao.insert(testTask);*/
        //插入附件
        if (StringUtils.isNotEmpty(testItemDto.getUrls())) {

            QtPlanItemEnclosure enclosure = new QtPlanItemEnclosure();
            enclosure.setCreateTime(System.currentTimeMillis());
            enclosure.setUpdateTime(System.currentTimeMillis());
            enclosure.setEnclosure(testItemDto.getUrls());
            enclosure.setTId(testItem.getId());
            enclosure.setTType(QtPlanItemEnclosure.TEST_ITEM);
            enclosure.setValid(Constants.VALID_YES);
            enclosure.preInsert();
            enclosureDao.insert(enclosure);
        }
        return true;
    }

    public boolean delTestItem(String itemId) {
        QtPlanItem planItem = new QtPlanItem();
        planItem.setTiId(itemId);
        planItem.setValid(Constants.VALID_YES);
        int i = planItemDao.getCount(planItem);
        if (i > 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "已有任务模板关联该任务项，不能删除");
        }
        QtTestItem testItem = new QtTestItem();
        testItem.setId(itemId);
        testItem.setValid(Constants.VALID_NO);
        testItemDao.updateById(testItem);
        return true;
    }

    public TestItemDetailVo detailTestItem(String itemId) {


        QtTestItem testItem = testItemDao.getById(itemId);
        String dutyEmpIds = testItem.getTiDutyEmpIds();
        List<String> list_dutyEmpNames = new ArrayList<String>();
        if (StringUtils.isNotBlank(dutyEmpIds)) {
            String[] array_dutyEmpIds = dutyEmpIds.split(",");
            for (int i = 0; i < array_dutyEmpIds.length; i++) {
                CtlEmp sgaiEmp = baseSgaiOrgTreeService.getSgaiEmpById(array_dutyEmpIds[i]);
                String dutyEmpName = sgaiEmp.getLastname();
                list_dutyEmpNames.add(dutyEmpName);
            }
        }
        TestItemDetailVo testItemDetailVo = new TestItemDetailVo();
        QtBeanMapper.copy(testItem, testItemDetailVo);
        testItemDetailVo.setTiId(testItem.getId());
        testItemDetailVo.setTiMin(Double.valueOf(testItem.getTiMin()) / 100 + "");
        testItemDetailVo.setTiMax(Double.valueOf(testItem.getTiMax()) / 100 + "");
        testItemDetailVo.setDutyEmpNames(list_dutyEmpNames);
        //获取附件
        QtPlanItemEnclosure enclosure = new QtPlanItemEnclosure();
        enclosure.setTId(testItem.getId());
        enclosure.setTType(QtPlanItemEnclosure.TEST_ITEM);
        List<QtPlanItemEnclosure> enclosureList = enclosureDao.findList(enclosure);
        if (CollectionUtils.isNotEmpty(enclosureList)) {
            testItemDetailVo.setEnclosures(enclosureList.get(0).getEnclosure());
        }
        return testItemDetailVo;
    }

    public Page<TestItemVo> listTestItem(String itemName, String pcId, Long startDate, Long endDate,
                                         Integer type, Integer pageNum, Integer pageSize) {
        Page<TestItemVo> pageInfo = new Page<>(pageNum, pageSize);
        pageInfo.setOrderBy("create_time desc");

        Long comId = UserServletContext.getUserInfo().getCompanyId();
        TestItemVo testItemVo = new TestItemVo();
        testItemVo.setComId(comId);
        testItemVo.setComCode(UserServletContext.getUserInfo().getComCode());
        testItemVo.setValid(Constants.VALID_YES);//只显示未删除的
        if (StringUtils.isNotEmpty(itemName)) {
            testItemVo.setItemName(itemName);
        }
        if (StringUtils.isNotEmpty(pcId)) {
            testItemVo.setPcId(pcId);
        }
        if (startDate != null) {
            testItemVo.setStartDate(startDate);
        }
        if (endDate != null) {
            //前端只传当天的时间戳，如2018-01-12 0:0:0，正确应该是2018-01-12 23:59:59
            testItemVo.setEndDate(endDate + 60 * 60 * 24 * 1000 - 1);
        }
        if (type == null) {
            testItemVo.setTypeFlag(0);
        } else {
            testItemVo.setTypeFlag(type);
        }
        testItemVo.setPage(pageInfo);
        pageInfo.setList(testItemDao.listTestItem(testItemVo));
        return pageInfo;
    }

    /**
     * 批量插入附件
     *
     * @param urlList
     */
    void batchInsertAttchment(int type, String itemId, List<String> urlList) {
        if (CollectionUtils.isNotEmpty(urlList)) {
            List<QtPlanItemEnclosure> enclosures = new ArrayList<>();
            for (String urlObj : urlList) {
                QtPlanItemEnclosure enclosure = new QtPlanItemEnclosure();
//				enclosure.setId(IdGen.uuid());
                enclosure.setCreateTime(System.currentTimeMillis());
                enclosure.setUpdateTime(System.currentTimeMillis());
                enclosure.setEnclosure(urlObj);
                enclosure.setTId(itemId);
                enclosure.setTType(type);
                enclosure.setValid(Constants.VALID_YES);
                enclosure.preInsert();
                enclosures.add(enclosure);
            }

            //批量插入
            enclosureDao.batchInsert(enclosures);
        }
    }

    /**
     * 判断任务项名称是否重复
     */
    public void hasTestItemName(String testItemName, String id) {

        if (StringUtils.isEmpty(testItemName)) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务项名称不可为空！");
        }
        QtTestItem testItem = new QtTestItem();
//		QtProfessionalCategory pCategory = new QtProfessionalCategory();
        testItem.setPcName(testItemName.trim());
        testItem.setValid(Constants.VALID_YES);
        List<QtTestItem> testItems = testItemDao.findList(testItem);
        if (CollectionUtils.isNotEmpty(testItems)) {
            if (StringUtils.isNotEmpty(id)) {//修改
                if (!testItems.get(0).getId().equals(id)) {
                    throw new BusinessException(ReturnType.ParamIllegal, "任务项名称不可重复");
                }
            } else {//插入
                throw new BusinessException(ReturnType.ParamIllegal, "任务项名称不可重复");
            }
        }
    }

    public boolean updateTestItem(TestItemDto testItemDto) {

//		判断任务项名称是否重复
        hasTestItemName(testItemDto.getPcName(), testItemDto.getPcId());
        QtTestItem testItem = new QtTestItem();
        //如果是数值型，存的时候要乘以100
        if (testItemDto.getTiIsInput() == 0) {
            long tiMin = (long) (Double.valueOf(testItemDto.getTiMin()) * 100);
            long tiMax = (long) (Double.valueOf(testItemDto.getTiMax()) * 100);
            testItemDto.setTiMin(tiMin + "");
            testItemDto.setTiMax(tiMax + "");
        }
        QtBeanMapper.copy(testItemDto, testItem);
        testItem.setUpdateTime(System.currentTimeMillis());
        testItem.setId(testItemDto.getPcId());
        testItem.preUpdate();
        testItemDao.updateById(testItem);
        return true;
    }

    public boolean saveExecuteTestItem(TestItemDto testItemDto) {

        if (testItemDto == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "不可为空！");
        }
        if (StringUtils.isEmpty(testItemDto.getId())) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务项id不可为空！");
        }
        if (StringUtils.isEmpty(testItemDto.getRecordId())) {
            throw new BusinessException(ReturnType.ParamIllegal, "计划id不可为空！");
        }
        if (StringUtils.isEmpty(testItemDto.getTpId())) {
            throw new BusinessException(ReturnType.ParamIllegal, "模板id不可为空！");
        }

        QtTestItem testItem = testItemDao.getById(testItemDto.getId());
        if (testItem == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "没有该任务项！");
        }
        if (testItemDto.getTiIsSubmit() == null || testItemDto.getTiIsSubmit() == 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "未选择答案");
        }
        String str = testItemDto.getTiInputResult();
        if (!StringUtils.isNotBlank(str)) {
            throw new BusinessException(ReturnType.ParamIllegal, "未提交答案");
        }
        /**保存时更新taskResultDao表计划任务对应的状态*/

        QtTaskResult qtTaskResult = new QtTaskResult();
        QtBeanMapper.copy(testItemDto, qtTaskResult);
        /**只有保存操作，不会修改*/
//		QtTaskResult taskResultQuery = new QtTaskResult();
//		taskResultQuery.setTiId(testItemDto.getId());
//		taskResultQuery.setRecordId(testItemDto.getRecordId());
//		taskResultQuery.setTpId(testItemDto.getTpId());
//		taskResultQuery.setDateTime(testItemDto.getDateTime());
//		List<QtTaskResult> list_taskResult = taskResultDao.findList(taskResultQuery);
        /**id不能改变*/
//		qtTaskResult.setId(null);
        qtTaskResult.setId(IdGen.uuid());
        qtTaskResult.setTiId(testItemDto.getId());
        qtTaskResult.setUpdateTime(System.currentTimeMillis());
        qtTaskResult.setTiCheckTime(System.currentTimeMillis());
        qtTaskResult.setTiExecutorId(UserServletContext.getUserInfo().getEmCode());
        qtTaskResult.setTiExecutorName(UserServletContext.getUserInfo().getUserName());
        taskResultDao.insert(qtTaskResult);
//		taskResultDao.updateByTiIdAndRecordId(qtTaskResult);

        /**查询条数，该计划当天下的所有关联任务项是否执行*/
        QtTaskResult qtTaskResult2 = new QtTaskResult();
        qtTaskResult2.setRecordId(testItemDto.getRecordId());
        qtTaskResult2.setTpId(testItemDto.getTpId());
        qtTaskResult2.setDateTime(testItemDto.getDateTime());
        List<QtTaskResult> list_qtTaskResult = taskResultDao.findList(qtTaskResult2);
        List<String> list_tiId = new ArrayList<String>();
        for (QtTaskResult taskResult_temp : list_qtTaskResult) {
            list_tiId.add(taskResult_temp.getTiId());
        }
        // 先查出已经关联的检验项id
        QtPlanItem qtPlanItem = new QtPlanItem();
        qtPlanItem.setTpId(testItemDto.getTpId());
        qtPlanItem.setValid(Constants.VALID_YES);
        List<String> relIds = testPlanDao.findRelIds(qtPlanItem);
        /**任务项是否全部完成*/
        boolean isComplate = false;
        if (list_tiId.size() > 0) {
            isComplate = list_tiId.containsAll(relIds) && relIds.containsAll(list_tiId);
//			if (list_tiId.compa) {
//				Collections.comp
//			}
//			if (list_qtTaskResult.size() ==relIds.size() ) {
//				isComplate = true;
//			}
        }

//		/**任务项是否全部完成*/
//		boolean isComplate = false; 
//		for(int i =0 ;i<list_qtTaskResult.size();i++){
//			QtTaskResult temp_qtTaskResult = list_qtTaskResult.get(i);
//			if (temp_qtTaskResult.getTiStatus()==0) {
//				break;
//			}
//			/**最后一个元素,如果等于1表示该模板关联的所有任务项都合格*/
//			if (i == list_qtTaskResult.size()-1) {
//				if (temp_qtTaskResult.getTiStatus() !=0) {
//					isComplate = true;
//				}
//			}
//		}

        /**全部完成,修改jh_task表*/
        if (isComplate) {
            Task task = new Task();
            task.setTaskIsComplete(1L);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("recordId", testItemDto.getRecordId());
            map.put("tpId", testItemDto.getTpId());
            map.put("task", task);
            boolean b = taskDao.updateByIdAndTpId(map);
            return b;
        }
        return true;
    }
}
