package com.sgai.property.contract.dao;


import com.sgai.property.contract.entity.HtImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IImageDao {
    /**
     * 根据合同编码查询图片
     * @return
     */
    HtImage getImageByNo(HtImage image);
}
