package com.sgai.property.customer.dao;

import com.sgai.property.customer.entity.SurveyUserAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计
 *
 * @author Hou
 * @create 2018-04-12 14:14
 */
@Mapper
public interface IUserAnswerStatisticsDao {

    /**
     * 获取出现的次数(单选)
     * @param smId       问卷id
     * @param sqId       问题id
     * @param sqContent  问题选项内容
     * @return
     */
    Double getCount(@Param("smId") String smId, @Param("sqId") String sqId, @Param("sqContent") String sqContent);

    /**
     * 判断有多少人回答了这道题(多选)
     * @param smId
     * @param sqId
     * @return
     */
    Double getCountForMulti(@Param("smId") String smId, @Param("sqId") String sqId);

    List<SurveyUserAnswer> getPhone(@Param("userPhone") String userPhone, @Param("smId") String smId);
}
