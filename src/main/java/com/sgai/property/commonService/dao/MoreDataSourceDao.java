package com.sgai.property.commonService.dao;
import com.sgai.common.persistence.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoreDataSourceDao<T> extends CrudDao<T> {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 获取ID集合获取数据
     * @param id
     * @return
     */
    List<T> getByIds(String[] id);
    /**
     * 获取ID集合获取数据
     * @param id
     * @return
     */
    List<T> getByListId(List<String> id);
    /**
     * 查询数据条数
     * @param entity
     * @return
     */
    int getCount(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    int updateById(T entity);


    /**
     * 根据主键批量更新数据
     * @param entity
     * @return
     */
    int updateByIds(@Param("entity") T entity, @Param("ids") String[] ids);


    /**
     * 删除数据(删除逻辑)
     * @param id
     * @see public int delete(T entity)
     * @return
     */
    int deleteById(String id);

    /**
     * 删除数据(删除逻辑)
     * @param
     * @return
     */
    int deleteByIds(String[] id);

    /**
     * 删除数据(删除逻辑)
     * @return
     */
    int deleteByListId(List<String> id);
}
