package com.sgai.property.customer.service;

import com.sgai.property.customer.vo.PersonalUpLoadDto;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojianqun on 2018/1/26.
 */
public class ReadExcel {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<PersonalUpLoadDto> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<PersonalUpLoadDto> personList = new ArrayList<>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2013 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2017(fileName)) {
                isExcel2013 = false;
            }
            personList = createExcel(mFile.getInputStream(), isExcel2013);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2013 excel是2003还是2007版本
     * @return
     */
    public List<PersonalUpLoadDto> createExcel(InputStream is, boolean isExcel2013) {
        List<PersonalUpLoadDto> personList = new ArrayList<>();
        try{
            Workbook wb = null;
            if (isExcel2013) {// 当excel是2013时,创建excel2013
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2017时,创建excel2017
                wb = new XSSFWorkbook(is);
            }
            personList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<PersonalUpLoadDto> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<PersonalUpLoadDto> channelLists = new ArrayList<PersonalUpLoadDto>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            PersonalUpLoadDto personalUpLoadDto = new PersonalUpLoadDto();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prName = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrName(prName.substring(0, prName.length()-2>0?prName.length()-2:1));//姓名
                        }else{
                            personalUpLoadDto.setPrName(cell.getStringCellValue());//姓名
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prPhoneFirst = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrPhoneFirst(Long.parseLong(prPhoneFirst.substring(0, prPhoneFirst.length()-2>0?prPhoneFirst.length()-2:1)));//手机号
                        }else{
                            personalUpLoadDto.setPrPhoneFirst(Long.parseLong(cell.getStringCellValue()));//手机号
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prPhoneSecond = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrPhoneSecond(Long.parseLong(prPhoneSecond.substring(0, prPhoneSecond.length()-2>0?prPhoneSecond.length()-2:1)));//第二个手机号
                        }else{
                            personalUpLoadDto.setPrPhoneSecond(Long.parseLong(cell.getStringCellValue()));//第二个手机号
                        }
                    }else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prEmail = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrEmail(prEmail.substring(0, prEmail.length()-2>0?prEmail.length()-2:1));//邮箱
                        }else{
                            personalUpLoadDto.setPrEmail(cell.getStringCellValue());//邮箱
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prSex = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrSex(prSex.substring(0, prSex.length()-2>0?prSex.length()-2:1));//性别
                        }else{
                            personalUpLoadDto.setPrSex(cell.getStringCellValue());//性别
                        }
                    }else if (c == 5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String prBirth = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setPrBirth(prBirth.substring(0, prBirth.length()-2>0?prBirth.length()-2:1));//出生日期
                        }else{
                            personalUpLoadDto.setPrBirth(cell.getStringCellValue());//出生日期
                        }
                    }else if (c == 6){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String typeName = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setTypeName(typeName.substring(0, typeName.length()-2>0?typeName.length()-2:1));//客户类型
                        }else{
                            personalUpLoadDto.setTypeName(cell.getStringCellValue());//客户类型
                        }
                    }else if (c == 7){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String levelName = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setLevelName(levelName.substring(0, levelName.length()-2>0?levelName.length()-2:1));//客户级别
                        }else{
                            personalUpLoadDto.setLevelName(cell.getStringCellValue());//客户级别
                        }
                    }else if (c == 8){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String ccCertificateNo = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setCcCertificateNo(ccCertificateNo.substring(0, ccCertificateNo.length()-2>0?ccCertificateNo.length()-2:1));//证件号码
                        }else{
                            personalUpLoadDto.setCcCertificateNo(cell.getStringCellValue());//证件号码
                        }
                    }else if (c == 9){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String ccCertificateName = String.valueOf(cell.getNumericCellValue());
                            personalUpLoadDto.setCcCertificateName(ccCertificateName.substring(0, ccCertificateName.length()-2>0?ccCertificateName.length()-2:1));//证件类型
                        }else{
                            personalUpLoadDto.setCcCertificateName(cell.getStringCellValue());//证件类型
                        }
                    }
                }
            }
            // 添加到list
            channelLists.add(personalUpLoadDto);
        }
        return channelLists;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2013(filePath) || isExcel2017(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2013(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2017(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
