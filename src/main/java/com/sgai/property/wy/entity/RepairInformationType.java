  
    /**    
    * @Title: RepairInformationType.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月9日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;

    /**
     * @ClassName: RepairInformationType
     * (这里用一句话描述这个类的作用)
     * @author XJ9001
     * @date 2018年2月9日
     * @Company 首自信--智慧城市创新中心
     */

    public class RepairInformationType extends BoEntity<RepairInformationType> {


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = 1L;

            private String typeId;

            private String typeCode;

            private String parentCode;

            private String parentName;

            public String getParentName() {
                return parentName;
            }

            public void setParentName(String parentName) {
                this.parentName = parentName;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
            }



    }
