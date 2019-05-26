  
    /**    
    * @Title: RepairConst.java  
    * @Package com.sgai.property.wy.dto
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年3月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dto;

  
    /**  
 * @ClassName: RepairConst  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年3月1日  
 * @Company 首自信--智慧城市创新中心  
 */

public enum RepairConst {

	 repairSubmit ("已提交","1"), 
	 repairUpdate ("已修改","2"), 
	 repairAccept ( "已受理","3"), 
	 repairAppoint ("已指派","4"), 
	 maintainBegin ( "维修开始","5"), 
	 maintainWait ("等待中","6"), 
	 maintainFinish ( "维修完成","7"), 
	 fcheckQualified ( "功能验收合格","8"), 
	 fcheckNoQualified ( "功能验收不合格","9"), 
	 echeckQualified ( "环境验收合格","10"), 
	 echeckNoQualified ( "环境验收不合格","11"), 
	 check ("已验收","12"), 
	 appraise ("已评价","13"), 
	 cancel ( "已取消","14"), 
	 chargeback ( "已退单","15"), 
	 close ( "已关闭","16"),
	 backNoAppoint("退单未指派","17");
	private String name ; 
    private String index ; 
    private RepairConst( String name , String index ){ 
        this.name = name ; 
        this.index = index ; 
    } 
    public String getName() { 
        return name; 
    } 
    public void setName(String name) { 
        this.name = name; 
    } 
    public String getIndex() { 
        return index; 
    } 
    public void setIndex(String index) { 
        this.index = index; 
    }
    
    public static String getName(String index) {  
        for (RepairConst r : RepairConst.values()) {  
            if (r.getIndex() .equals(index)) {  
                return r.name;  
            }  
        }  
        return null;  
    } 
}
