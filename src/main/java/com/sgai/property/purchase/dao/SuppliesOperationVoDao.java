package com.sgai.property.purchase.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.purchase.entity.MatApplyDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用料操作相关 on 2018/1/11.
 */
@Mapper
public interface SuppliesOperationVoDao extends CrudDao {
    List<MatApplyDetail> findDetailList(MatApplyDetail mat);

    List<MatApplyDetail> suppliesApproves(MatApplyDetail mat);
    
    List<MatApplyDetail> suppliesHasNoOrder(MatApplyDetail mat);

    List<MatApplyDetail> suppliesApprovest(MatApplyDetail mat);
}
