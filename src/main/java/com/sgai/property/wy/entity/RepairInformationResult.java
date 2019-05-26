  
    /**    
    * @Title: RepairInformationResult.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年6月25日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import java.util.List;

    /**
        * @ClassName: RepairInformationResult
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年6月25日
        * @Company 首自信--智慧城市创新中心
        */

    public class RepairInformationResult {
        private String name;
        private String count;
        private List<RepairInformationResult>  list;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getCount() {
            return count;
        }
        public void setCount(String count) {
            this.count = count;
        }
        public List<RepairInformationResult> getList() {
            return list;
        }
        public void setList(List<RepairInformationResult> list) {
            this.list = list;
        }

    }
