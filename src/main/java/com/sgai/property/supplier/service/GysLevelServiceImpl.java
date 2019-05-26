package com.sgai.property.supplier.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysLevelDao;
import com.sgai.property.supplier.dao.IGysSupplierDao;
import com.sgai.property.supplier.entity.GysLevel;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.vo.GysLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GysLevelServiceImpl extends MoreDataSourceCrudServiceImpl<IGysLevelDao, GysLevel> {
    @Autowired
    private IGysLevelDao gysLevelDao;
    @Autowired
    private IGysSupplierDao gysSupplierDao;

    /**
     * 添加供应商级别
     *
     * @param gysLevel
     */
    @Transactional
    public void save(GysLevel gysLevel) {
        //获取toonCode身上的参数(COM_ID,COM_NAME,FEED_ID,TOON_USER_ID)
        gysLevel.setComCode(UserServletContext.getUserInfo().getComCode());
        gysLevel.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //添加供应商级别实体
        super.save(gysLevel);
    }


    /**
     * 根据Id更新供应商级别
     *
     * @param gysLevel
     * @return
     */
    @Transactional
    public boolean updateById(String id, GysLevel gysLevel) {
        //添加查询参数：id
        gysLevel.setId(id);
        return this.updateById(gysLevel);
    }


    /**
     * 分页获取供应商内容列表
     *
     * @param gysLevel
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<GysLevelVO> findGysLevelList(GysLevel gysLevel, int pageNum, int pageSize) {
        //初始化信息：分页信息，供应商输出实体，供应商等级输出实体集合
        Page<GysLevelVO> pageInfo = new Page<GysLevelVO>();
        List<GysLevelVO> list = new ArrayList<GysLevelVO>();

        //封装分页信息
        Page<GysLevel> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("CREATED_DT ASC");
        gysLevel.setPage(page);

        //根据查询条件获取北京通卡信息集合
        List<GysLevel> gysLevelList = gysLevelDao.findGysLevelList(gysLevel);
        int count = gysLevelDao.getListCount(gysLevel);

        for (GysLevel level : gysLevelList) {
            //封装供应商等级实体
            GysLevelVO gysLevelVO = new GysLevelVO();
            GysSupplier gysSupplier = new GysSupplier();
            //供应商等级唯一标识
            gysLevelVO.setId(level.getId());
            //供应商等级名称
            gysLevelVO.setName(level.getName());
            //供应商等级描述
            gysLevelVO.setDescription(level.getDescription());
            //供应商等级删除状态
            gysLevelVO.setIsDelete(level.getIsDelete());
            gysSupplier.setLevelId(level.getId());
            //与供应商等级绑定的供应商数量
            int levelCount = gysSupplierDao.getLevelCount(gysSupplier);
            gysLevelVO.setLevelCount(levelCount);

            list.add(gysLevelVO);
        }

        //封装分页详情
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

    /**
     * 获取所有的集合实体
     *
     * @return
     */
    public List<GysLevel> findAllList() {
        GysLevel gysLevel = new GysLevel();
        gysLevel.setComCode(UserServletContext.getUserInfo().getComCode());
        if (UserServletContext.getUserInfo().getModuCode() != null) {
            gysLevel.setModuCode(UserServletContext.getUserInfo().getModuCode());
        }
        return gysLevelDao.findAllList(gysLevel);
    }
}