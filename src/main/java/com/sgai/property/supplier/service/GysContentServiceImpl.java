package com.sgai.property.supplier.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysContentDao;
import com.sgai.property.supplier.dao.IGysSupplierDao;
import com.sgai.property.supplier.entity.GysContent;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.vo.GysContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GysContentServiceImpl extends MoreDataSourceCrudServiceImpl<IGysContentDao, GysContent> {

    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IGysContentDao gysContentDao;
    @Autowired
    private IGysSupplierDao gysSupplierDao;

    /**
     * 添加内容服务
     *
     * @param gysContent
     */
    @Transactional
    @Override
    public void save(GysContent gysContent) {
        gysContent.setComCode(UserServletContext.getUserInfo().getComCode());
        if (UserServletContext.getUserInfo().getModuCode() != null && !"".equals(UserServletContext.getUserInfo().getModuCode())) {
            gysContent.setModuCode(UserServletContext.getUserInfo().getModuCode());
        }
        //添加内容服务实体
        super.save(gysContent);
    }


    /**
     * 根据Id更新供应商内容
     *
     * @param gysContent
     * @return
     */
    @Transactional
    public boolean updateById(String id, GysContent gysContent) {
        //添加查询参数：id
        gysContent.setId(id);
//		gysContent.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
//		try {
//			gysContent.setUpdatedDt(format.parse(format.format(new Date())));
//		}catch (ParseException e){
//			e.printStackTrace();
//		}
        return this.updateById(gysContent);
    }

    /**
     * 分页获取供应商内容列表
     *
     * @param gysContent
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<GysContentVO> findGysContentList(GysContent gysContent, int pageNum, int pageSize) {
        //初始化信息：分页信息，供应商输出实体，供应商内容输出实体集合
        Page<GysContentVO> pageInfo = new Page<GysContentVO>();
        List<GysContentVO> list = new ArrayList<GysContentVO>();

        //封装分页信息
        Page<GysContent> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("CREATED_DT ASC");
        gysContent.setPage(page);

        //根据查询条件获取北京通卡信息集合
        List<GysContent> gysContentList = gysContentDao.findGysContentList(gysContent);
        int count = gysContentDao.getListCount(gysContent);

        for (GysContent content : gysContentList) {
            //封装供应商类型实体
            GysContentVO gysContentVO = new GysContentVO();
            GysSupplier gysSupplier = new GysSupplier();
            //供应商内容唯一标识
            gysContentVO.setId(content.getId());
            //供应商内容名称
            gysContentVO.setName(content.getName());
            //供应商内容描述
            gysContentVO.setDescription(content.getDescription());
            //供应商内容删除状态
            gysContentVO.setIsDelete(content.getIsDelete());
            gysSupplier.setContentId(content.getId());
            //与供应商内容绑定的供应商数量
            int contentCount = gysSupplierDao.getContentCount(gysSupplier);
            gysContentVO.setCount(contentCount);

            list.add(gysContentVO);
        }

        //封装分页详情
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

    /**
     * 查询所有的集合实体
     *
     * @return
     */
    public List<GysContent> findAllList() {
        GysContent gysContent = new GysContent();
        gysContent.setComCode(UserServletContext.getUserInfo().getComCode());
        gysContent.setModuCode(UserServletContext.getUserInfo().getModuCode());

		/*if(UserServletContext.getUserInfo().getModuCode() !=null && !"".equals(UserServletContext.getUserInfo().getModuCode())){

		}*/
        return gysContentDao.findAllList(gysContent);
    }
}