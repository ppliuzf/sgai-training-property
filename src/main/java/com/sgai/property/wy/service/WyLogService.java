  
    /**    
    * @Title: LogService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.WyLogDao;
    import com.sgai.property.wy.entity.WyLog;
    import com.sgai.property.wy.support.ExcelStyleUtill;
    import org.apache.poi.hssf.usermodel.*;
    import org.springframework.stereotype.Service;

    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.OutputStream;
    import java.text.SimpleDateFormat;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    /**
        * @ClassName: LogService
        * (这里用一句话描述这个类的作用)
        * @author heibin
        * @date 2018年1月29日
        * @Company 首自信--智慧城市创新中心
        */
    @Service
    public class WyLogService extends CrudServiceExt<WyLogDao, WyLog> {


                /**
                * @Title: findPage
                * (这里用一句话描述这个方法的作用)
                * @param @param page
                * @param @param wyLog
                * @param @return    参数
                * @return Page<WyLog>    返回类型
                * @throws
                */

         public Page<WyLog> findPage(Page<WyLog> page, WyLog wyLog){
             return super.findPage(page, wyLog);
            }


                    /**
         * @throws IOException @Title: export @Description:
         *  @param @param wyLog @param @param response
         * 参数 @return void 返回类型 @throws
         */

                public void export(WyLog wyLog, HttpServletResponse response) throws IOException {
                    OutputStream os = response.getOutputStream();// 取得输出流
                    response.reset();//清空输出流
                    if(wyLog.getType()==1){
                        response.setHeader("Content-disposition", "attacment;filename="+new String("日志记录.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
                    }else{
                        response.setHeader("Content-disposition", "attacment;filename="+new String("日志处理.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
                    }

                    response.setContentType("application/msexcel");// 定义输出类型
                    //数据类型转换
                    List<WyLog> list = super.findList(wyLog);
                    exportWyLog(list, os,wyLog.getType());
                };
                 /**
                 * @throws IOException
                * @Title: exportGoodsBorrow
                * (这里用一句话描述这个方法的作用)
                * @param @param list
                * @param @param os    参数
                * @return void    返回类型
                * @throws
                */
            private void exportWyLog(List<WyLog> list, OutputStream os,int type) throws IOException {
                // 创建工作簿
                        HSSFWorkbook book = new HSSFWorkbook();
                        // 创建Sheet对象
                        HSSFSheet sheet;
                        if(type==2){
                            //日志处理
                            sheet = book.createSheet("日志处理");
                        }else{
                            sheet = book.createSheet("日志记录");
                        }
                        HSSFCellStyle style = book.createCellStyle();
                        ExcelStyleUtill.CellStyle(style, book);
                        HSSFRow row0 = sheet.createRow((short) 0);
                        HSSFCell cell00 = row0.createCell(0);
                        row0.setHeight((short) 1200);
                        cell00.setCellStyle(style);
                        // 增加单元格的高度
                        row0.setHeightInPoints((short) 50);
                        HSSFCell cell01 = row0.createCell(1);
                        cell01.setCellStyle(style);
                        HSSFCell cell02 = row0.createCell(2);
                        cell02.setCellStyle(style);
                        HSSFCell cell03 = row0.createCell(3);
                        cell03.setCellStyle(style);
                        /*HSSFCell cell04 = row0.createCell(4);
                        HSSFCell cell05 = row0.createCell(5);*/
                        HSSFCell cell06 = row0.createCell(6);
                        cell06.setCellStyle(style);
                        HSSFCell cell07 = row0.createCell(7);
                        cell07.setCellStyle(style);
                        HSSFCell cell08 = row0.createCell(8);
                        cell08.setCellStyle(style);
                        /*HSSFCell cell09 = row0.createCell(9);*/
                        //HSSFCell cell010 = row0.createCell(10);
                        // 向单元格中设置值
                        cell00.setCellValue("序号");
                        cell01.setCellValue("时间");
                        cell02.setCellValue("姓名");
                        cell03.setCellValue("地址");
                        /*cell04.setCellValue("部门");*/
                        /*cell05.setCellValue("电话");*/
                        cell06.setCellValue("日志描述");
                        cell07.setCellValue("处理人");
                        cell08.setCellValue("处理描述");
                    //	cell09.setCellValue("备 注");
                        //cell010.setCellValue("经办人");

                        // 向excel中插入数据
                        for (int i = 0; i < list.size(); i++) {
                            HSSFRow row = sheet.createRow(i + 1);
                            HSSFCell cell0 = row.createCell(0);
                            HSSFCell cell1 = row.createCell(1);
                            HSSFCell cell2 = row.createCell(2);
                            HSSFCell cell3 = row.createCell(3);
                            /*HSSFCell cell4 = row.createCell(4);
                            HSSFCell cell5 = row.createCell(5);*/
                            HSSFCell cell6 = row.createCell(6);
                            HSSFCell cell7 = row.createCell(7);
                            HSSFCell cell8 = row.createCell(8);
                            /*HSSFCell cell9 = row.createCell(9);*/
                            //HSSFCell cell10 = row.createCell(10);
                            WyLog wyLog = list.get(i);
                            cell0.setCellValue(i + 1);
                            String createTime=new SimpleDateFormat("yyyy-MM-dd").format(wyLog.getCreateTime());
                            cell1.setCellValue(createTime+wyLog.getHour());
                            cell2.setCellValue(wyLog.getUserRecordName());
                            cell3.setCellValue(wyLog.getArea());
                        //	cell4.setCellValue(wyLog.getUserId());
                            /*cell5.setCellValue(wyLog.getTelePhone());*/
                            cell6.setCellValue(wyLog.getContent());
                            cell7.setCellValue(wyLog.getUserName());
                            cell8.setCellValue(wyLog.getDescribes());
                            //cell9.setCellValue(wyLog.getRemarks());
                            //cell10.setCellValue(goodsBorrow.getManager());
                        }
                        // 将数据写出
                        book.write(os);
                        os.close();
                        //book.close();
            }



                    /**
                    * @Title: deleteWyLog
                    * (这里用一句话描述这个方法的作用)
                    * @param @param ids
                    * @param @return    参数
                    * @return Map<String,Object>    返回类型
                    * @throws
                    */

                public Map<String, Object> deleteWyLog(String ids) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String idss[] = null;
                    if(ids!=null&&!ids.equals("")){
                        idss=ids.split(",");
                    }
                    int result=0;
                    for(String id:idss){
                        if(id!=null&&!id.equals("")){
                            WyLog wyLog=new WyLog();
                            wyLog.setId(id);
                            wyLog.setDelFlag("1");
                            result=dao.deleteWyLog(wyLog);
                        }
                    }
                    if (result > 0) {
                        map.put("msg", "删除成功!");
                    }else {
                        map.put("msg", "删除失败！");
                    }
                    return map;
                }
    }
