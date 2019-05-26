package com.sgai.property.meeting.service;
import com.sgai.common.persistence.BoEntity;
import com.sgai.common.persistence.CrudDao;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomResource;
import com.sgai.property.meeting.entity.RoomType;
import net.sf.ehcache.constructs.web.PageInfo;

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
//
//
//    public List<T> getByIds(Long[] id) {
//        return super.getByIds(id);
//    }
//
//
//    public List<T> getByListId(List<Long> id) {
//        return super.getByListId(id);
//    }
//
//
//    public T get(T entity) {
//        return super.get(entity);
//    }
//
//
//    public List<T> findAllList() {
//        return super.findAllList();
//    }
//
//
    public boolean saveEntity(T entity) {
         super.save(entity);
         return true;
    }
//
//
    public boolean updateById(T entity) {
         super.save(entity);
         return true;
    }


//
//    public boolean updateByIds(T entity, Long[] ids) {
//        return super.updateByIds(entity, ids);
//    }
//
//    public boolean deleteById(String id) {
//         super.delete(id);
//    }
//
//
//    public boolean delete(T entity) {
//        return super.delete(entity);
//    }
//
//
//    public boolean deleteByIds(Long[] id) {
//        return super.deleteByIds(id);
//    }
//
//
//    public boolean deleteByListId(List<Long> id) {
//        return super.deleteByListId(id);
//    }
}
