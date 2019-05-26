package com.sgai.property.purchase.service;
import com.sgai.common.persistence.BoEntity;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.commonService.dao.MoreDataSourceDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * description:
 * Created by llh on 2017/9/6.
 */
@Transactional
public abstract class MoreDataSourceCrudServiceImpl<D extends MoreDataSourceDao<T>, T extends BoEntity<T>> extends CrudServiceExt<D,T> {


    public Page<T> findListPage(T entity, int pageNumber, int pageSize) {
        Page<T> page=new Page<>(pageNumber,pageSize);
        return super.findPage(page,entity);
    }

    public T getById(String id) {
        return dao.getById(id);
    }

    public List<T> getByIds(String[] id) {
        return dao.getByIds(id);
    }


    public List<T> getByListId(List<String> id) {
        return dao.getByListId(id);
    }


    public List<T> findAllList() {
        return dao.findAllList();
    }

    public boolean saveEntity(T entity) {
         super.save(entity);
         return true;
    }

    public boolean updateById(T entity) {
         int i= dao.updateById(entity);
        return i > 0;
    }
//
    public boolean updateByIds(T entity, String[] ids) {
        int i= dao.updateByIds(entity, ids);
        return i > 0;
    }

    public boolean deleteById(String id) {
        int i= dao.deleteById(id);
        return i > 0;
    }

    public boolean deleteByIds(String[] id) {
        int i= dao.deleteByIds(id);
        return i > 0;
    }


    public boolean deleteByListId(List<String> id) {
        int i= dao.deleteByListId(id);
        return i > 0;
    }
}
