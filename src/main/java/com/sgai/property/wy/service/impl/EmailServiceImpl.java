package com.sgai.property.wy.service.impl;

import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.EmailDao;
import com.sgai.property.wy.entity.Email;
import com.sgai.property.wy.service.EmailService;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ppliu on 2018/1/19.
 */
@Service
public class EmailServiceImpl extends CrudServiceExt<EmailDao,Email> implements EmailService {

    @Autowired
    private EmailDao emailDao;
    public void export(HttpServletResponse response, Email email) throws IOException {
        List<Email> emails=emailDao.findList(email);
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();//清空输出流
        response.setHeader("Content-disposition", "attacment;filename="+new String("邮件管理.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
        response.setContentType("application/msexcel");// 定义输出类型
        //数据类型转换
        exportEmail(emails, os);

    }

    private void exportEmail(List<Email> emails, OutputStream os) throws IOException {
        {
            // 创建工作簿
            HSSFWorkbook book = new HSSFWorkbook();
            // 创建Sheet对象
            HSSFSheet sheet = book.createSheet("邮件管理信息");
			HSSFCellStyle style = book.createCellStyle();
			ExcelStyleUtill.CellStyle(style, book);
			HSSFRow row0 = sheet.createRow((short) 0);
			HSSFCell cell00 = row0.createCell(0);
			row0.setHeight((short) 1200);
			cell00.setCellStyle(style);
			// 增加单元格的高度
			row0.setHeightInPoints((short) 60);
            HSSFCell cell01 = row0.createCell(1);
            cell01.setCellStyle(style);
            HSSFCell cell02 = row0.createCell(2);
            cell02.setCellStyle(style);
            HSSFCell cell03 = row0.createCell(3);
            cell03.setCellStyle(style);
            HSSFCell cell04 = row0.createCell(4);
            cell04.setCellStyle(style);
            HSSFCell cell05 = row0.createCell(5);
            cell05.setCellStyle(style);
            HSSFCell cell06 = row0.createCell(6);
            cell06.setCellStyle(style);
            HSSFCell cell07 = row0.createCell(7);
            cell07.setCellStyle(style);
            HSSFCell cell08 = row0.createCell(8);
            cell08.setCellStyle(style);
            HSSFCell cell09 = row0.createCell(9);
            cell09.setCellStyle(style);
            HSSFCell cell010 = row0.createCell(10);
            cell010.setCellStyle(style);
            HSSFCell cell011 = row0.createCell(11);
            cell011.setCellStyle(style);
           /* HSSFCell cell012 = row0.createCell(12);
            cell012.setCellStyle(style);*/
            // 向单元格中设置值
            cell00.setCellValue("序号");
            cell01.setCellValue("日期");
            cell02.setCellValue("部门");
            cell03.setCellValue("收件人");
            cell04.setCellValue("联系方式");
            cell05.setCellValue("快递公司");
            cell06.setCellValue("快递单号");
            cell07.setCellValue("快递分类");
            cell08.setCellValue("签字时间");
            cell09.setCellValue("交接人");
            cell010.setCellValue("签收人/代取人");
            cell011.setCellValue("备注");

            // 向excel中插入数据
            for (int i = 0; i < emails.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell0 = row.createCell(0);
                HSSFCell cell1 = row.createCell(1);
                HSSFCell cell2 = row.createCell(2);
                HSSFCell cell3 = row.createCell(3);
                HSSFCell cell4 = row.createCell(4);
                HSSFCell cell5 = row.createCell(5);
                HSSFCell cell6 = row.createCell(6);
                HSSFCell cell7 = row.createCell(7);
                HSSFCell cell8 = row.createCell(8);
                HSSFCell cell9 = row.createCell(9);
                HSSFCell cell10 = row.createCell(10);
                HSSFCell cell11 = row.createCell(11);
                cell0.setCellValue(i + 1);
                Email email=emails.get(i);
                cell1.setCellValue(email.getSendTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(email.getSendTime()));
                cell2.setCellValue(email.getDepartmentName());
                cell3.setCellValue(email.getResiverName());
                cell4.setCellValue(email.getPhone());
                cell5.setCellValue(email.getExpressCompanyName());
                cell6.setCellValue(email.getExpressNumber());
                cell7.setCellValue(email.getExpressType());
                cell8.setCellValue(email.getReceiptTime());
                cell9.setCellValue(email.getSuccessorName());
                cell10.setCellValue(email.getSignerName());
                cell11.setCellValue(email.getRemark());
            }
            // 将数据写出
            book.write(os);
            os.close();
            //book.close();
        }
    }
}
