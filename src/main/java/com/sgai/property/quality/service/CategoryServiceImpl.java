package com.sgai.property.quality.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.QtBeanMapper;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.IQtDictGeneralDao;
import com.sgai.property.quality.dao.IQtProfessionalCategoryDao;
import com.sgai.property.quality.dao.IQtTestItemDao;
import com.sgai.property.quality.dao.IQtTestPlanDao;
import com.sgai.property.quality.entity.QtDictGeneral;
import com.sgai.property.quality.entity.QtProfessionalCategory;
import com.sgai.property.quality.entity.QtTestItem;
import com.sgai.property.quality.entity.QtTestPlan;
import com.sgai.property.quality.vo.CategoryTypeVo;
import com.sgai.property.quality.vo.CategoryVo;
import com.sgai.property.quality.vo.dto.CategoryDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements InitializingBean {

    @Autowired
    QCommonServicelmpl commonService;
    @Autowired
    IQtProfessionalCategoryDao categoryDao;
    @Autowired
    IQtDictGeneralDao dicGeneralDao;
    @Autowired
    IQtTestItemDao testItemDao;
    @Autowired
    IQtTestPlanDao testPlanDao;
    private Map<String, Integer> typeMap;


    static final String CATEGORY_TYPE = "quality_category_type";
    static final String CATEGORY_ICON = "quality_category_icon";

    public boolean createCategory(CategoryDto categoryDto) {

        //判断任务专业名称是否重复
        hasCategoryName(categoryDto.getPcName(), null);

        QtProfessionalCategory pCategory = new QtProfessionalCategory();

        UserServletContext.getUserInfo().getEmCode();
        String creatorName = UserServletContext.getUserInfo().getUserName();

        QtBeanMapper.copy(categoryDto, pCategory);

        pCategory.setPcName(categoryDto.getPcName().trim());
        //设置创建人、创建人名称和创建时间
        pCategory.setCreateTime(System.currentTimeMillis());
        pCategory.setUpdateTime(System.currentTimeMillis());
        pCategory.setValid(Constants.VALID_YES);

        pCategory.setCreateEiId(UserServletContext.getUserInfo().getEmCode());
        pCategory.setCreateName(creatorName);
        pCategory.preInsert();
        pCategory.setComCode(UserServletContext.getUserInfo().getComCode());
        pCategory.setModuCode(UserServletContext.getUserInfo().getModuCode());
        pCategory.setTypeFlag(categoryDto.getType() == null ? typeMap.get(categoryDto.getTaskType()) : categoryDto.getType());
        categoryDao.insert(pCategory);
        return true;
    }

    public boolean delCategory(String pcId) {
        QtTestItem qtTestItem = new QtTestItem();
        qtTestItem.setPcId(pcId);
        qtTestItem.setValid(Constants.VALID_YES);
        int i = testItemDao.getCount(qtTestItem);
        if (i > 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "该任务专业下已有任务项，不能删除");
        }
        QtTestPlan qtTestPlan = new QtTestPlan();
        qtTestPlan.setTiId(pcId);
        qtTestPlan.setValid(Constants.VALID_YES);
        int j = testPlanDao.getCount(qtTestPlan);
        if (j > 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "该任务专业下已有任务模板，不能删除");
        }
        QtProfessionalCategory pCategory = new QtProfessionalCategory();
        pCategory.setId(pcId);
        pCategory.setValid(Constants.VALID_NO); //逻辑删除
        pCategory.preUpdate();
        categoryDao.updateById(pCategory);
        return true;
    }

    public boolean updateCategory(CategoryDto categoryDto) {

        //判断任务专业名称是否重复
        hasCategoryName(categoryDto.getPcName(), categoryDto.getPcId());
        QtProfessionalCategory pCategory = new QtProfessionalCategory();
        QtBeanMapper.copy(categoryDto, pCategory);
        pCategory.setUpdateTime(System.currentTimeMillis());
        pCategory.setId(categoryDto.getPcId());
        pCategory.preUpdate();
        categoryDao.updateById(pCategory);
        return true;
    }

    public Page<CategoryVo> listCategory(Integer pageNum, Integer pageSize, Integer type) {
        Page<CategoryVo> pageInfo = new Page<>(pageNum, pageSize);
        pageInfo.setOrderBy("create_time asc");

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setValid(Constants.VALID_YES);
        categoryVo.setPage(pageInfo);
        if (type == null) {
            categoryVo.setTypeFlag(0);
        } else {
            categoryVo.setTypeFlag(type);
        }
        categoryVo.setComCode(UserServletContext.getUserInfo().getComCode());
        pageInfo.setList(categoryDao.listCategory(categoryVo));
        List<CategoryVo> categoryVos = pageInfo.getList();
        for (CategoryVo vo : categoryVos) {
            if (StringUtils.isNotEmpty(vo.getAsType())) {
                List<CategoryTypeVo> typeVoList = JSON.parseObject(vo.getAsType(), new TypeReference<List<CategoryTypeVo>>() {
                });
                StringBuilder stringBuilder = new StringBuilder();
                for (CategoryTypeVo categoryTypeVo : typeVoList) {
                    stringBuilder.append(categoryTypeVo.getAsName()).append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                vo.setAsType(stringBuilder.toString());
                vo.setTypeVoList(typeVoList);
            }
        }
        pageInfo.setList(categoryVos);
        return pageInfo;
    }

    public CategoryVo detailCategory(String pcId) {

        QtProfessionalCategory pCategory = categoryDao.getById(pcId);
        if (pCategory != null) {
            CategoryVo categoryVo = new CategoryVo();
            QtBeanMapper.copy(pCategory, categoryVo);
            return categoryVo;
        }

        return null;
    }

    public List<Map<String, Object>> getCategoryTypes() {

        QtDictGeneral dictGeneral = new QtDictGeneral();
        dictGeneral.setDgCode(CATEGORY_TYPE);
        dictGeneral.setComCode(UserServletContext.getUserInfo().getComCode());
        List<QtDictGeneral> dictGenerals = dicGeneralDao.findList(dictGeneral);
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (QtDictGeneral dic : dictGenerals) {
            Map<String, Object> map = new HashMap<>();
            map.put("typeId", dic.getDgKey());
            map.put("typeName", dic.getDgValue());
            listMap.add(map);
        }
        return listMap;

    }

    /**
     * 判断任务专业名称是否重复
     *
     * @param categoryName 任务专业名称
     */
    public void hasCategoryName(String categoryName, String id) {

        if (StringUtils.isEmpty(categoryName)) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务专业名称不可为空！");
        }

        QtProfessionalCategory pCategory = new QtProfessionalCategory();
        pCategory.setPcName(categoryName.trim());
        pCategory.setValid(Constants.VALID_YES);
        List<QtProfessionalCategory> categories = categoryDao.findList(pCategory);
        if (CollectionUtils.isNotEmpty(categories)) {
            if (StringUtils.isNotEmpty(id)) {//修改
                if (!categories.get(0).getId().equals(id)) {
                    throw new BusinessException(ReturnType.ParamIllegal, "任务专业名称不可重复");
                }
            } else {//插入
                throw new BusinessException(ReturnType.ParamIllegal, "任务专业名称不可重复");
            }
        }
    }

    public List<Map<String, Object>> getCategoryIcons() {
        QtDictGeneral dictGeneral = new QtDictGeneral();
        dictGeneral.setDgCode(CATEGORY_ICON);
        dictGeneral.setComCode(UserServletContext.getUserInfo().getComCode());
        List<QtDictGeneral> dictGenerals = dicGeneralDao.findList(dictGeneral);
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (QtDictGeneral dic : dictGenerals) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dic.getId());
            map.put("url", dic.getDgValue());
            listMap.add(map);
        }
        return listMap;
    }

    public List<Map<String, Object>> getAllCategory(Integer type) {
        QtProfessionalCategory pCategory = new QtProfessionalCategory();
        pCategory.setValid(Constants.VALID_YES);
        pCategory.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<QtProfessionalCategory> categoryPage = new Page<>(1, Integer.MAX_VALUE);
        categoryPage.setOrderBy("create_time desc");//按创建时间正序
        pCategory.setPage(categoryPage);
        if (type == null) {
            pCategory.setTypeFlag(0);
        } else {
            pCategory.setTypeFlag(type);
        }
        List<QtProfessionalCategory> categories = categoryDao.findList(pCategory);
        List<Map<String, Object>> maps = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(categories)) {
            for (QtProfessionalCategory category : categories) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", category.getId());
                map.put("pcName", category.getPcName());
                maps.add(map);
            }
        }
        return maps;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        typeMap = new HashMap<>();
        typeMap.put("检验类", 0);
        typeMap.put("安保类", 1);
    }
}
