  
    /**    
    * @Title: ComparatorComment.java  
    * @Package com.sgai.property.wy.support
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年6月11日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.support;

    import com.sgai.property.wy.entity.RepairTargetDto;

    import java.util.Comparator;

    /**
        * @ClassName: ComparatorComment
        * (list 按照某一个元素倒序排序)
        * @author heibin
        * @date 2018年6月11日
        * @Company 首自信--智慧城市创新中心
        */

    public class ComparatorComment implements Comparator {

        public int compare(Object arg0, Object arg1) {
            RepairTargetDto comment0 = (RepairTargetDto) arg0;
            RepairTargetDto comment1 = (RepairTargetDto) arg1;

            // 按score降序
            if (comment0.getCount1() < comment1.getCount1()) {
                return 1;
            } else {
                return 0;
            }
        }}
