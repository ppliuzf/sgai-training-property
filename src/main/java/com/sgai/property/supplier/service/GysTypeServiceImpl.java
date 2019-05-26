package com.sgai.property.supplier.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysSupplierDao;
import com.sgai.property.supplier.dao.IGysTypeDao;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.entity.GysType;
import com.sgai.property.supplier.vo.GysTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GysTypeServiceImpl extends MoreDataSourceCrudServiceImpl<IGysTypeDao, GysType> {

    protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IGysTypeDao gysTypeDao;

    @Autowired
    private IGysSupplierDao gysSupplierDao;

    /**
     * 添加供应商类型
     *
     * @param gysType
     */
    @Transactional
    @Override
    public void save(GysType gysType) {
        gysType.setComCode(UserServletContext.getUserInfo().getComCode());
        if (UserServletContext.getUserInfo().getModuCode() != null && !"".equals(UserServletContext.getUserInfo().getModuCode())) {
            gysType.setModuCode(UserServletContext.getUserInfo().getModuCode());
        }
        //添加供应商类型实体
        super.save(gysType);
    }


    /**
     * 编辑供应商类型
     *
     * @param gysType
     * @return
     */
    @Transactional
    public boolean updateById(String id, GysType gysType) {
        //获取toonCode身上的参数(updatedBy , updateDt)
        gysType.setId(id);
//		gysType.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
//		try {
//			gysType.setUpdatedDt(format.parse(format.format(new Date())));
//		}catch (ParseException e){
//			e.printStackTrace();
//		}
        return this.updateById(gysType);
    }


    /**
     * 分页获取供应商类型列表
     *
     * @param gysType
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<GysTypeVO> findGysTypeList(GysType gysType, int pageNum, int pageSize) {
        //初始化信息：分页信息，供应商输出实体，供应商输出实体集合
        Page<GysTypeVO> pageInfo = new Page<GysTypeVO>();
        List<GysTypeVO> list = new ArrayList<GysTypeVO>();

        //封装分页信息
        Page<GysType> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("CREATED_DT ASC");
        gysType.setPage(page);

        //根据查询条件获取北京通卡信息集合
        List<GysType> gysTypeList = gysTypeDao.findGysTypeList(gysType);
        int count = gysTypeDao.getListCount(gysType);

        for (GysType type : gysTypeList) {
            //封装供应商类型实体
            GysTypeVO gysTypeVO = new GysTypeVO();
            GysSupplier gysSupplier = new GysSupplier();
            //供应商类型id
            gysTypeVO.setId(type.getId());
            //供应商类型名称
            gysTypeVO.setName(type.getName());
            //供应商类型描述
            gysTypeVO.setDescription(type.getDescription());
            //供应商是否删除状态
            gysTypeVO.setIsDelete(type.getIsDelete());
            gysSupplier.setTypeId(type.getId());
            //与供应商类型绑定的供应商数量
            int typeCount = gysSupplierDao.getTypeCount(gysSupplier);
            gysTypeVO.setCount(typeCount);

            list.add(gysTypeVO);
        }

        //封装分页详情
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

    /**
     * 获取所有的实体集合
     *
     * @return
     */
    public List<GysType> findAllList() {
        GysType gysType = new GysType();
        gysType.setComCode(UserServletContext.getUserInfo().getComCode());
        gysType.setModuCode(UserServletContext.getUserInfo().getModuCode());

		/*if(UserServletContext.getUserInfo().getModuCode()!=null && !"".equals(UserServletContext.getUserInfo().getModuCode())){

		}*/
        return gysTypeDao.findAllList(gysType);
    }
}