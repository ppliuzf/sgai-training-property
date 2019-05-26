package com.sgai.property.customer.service;

import com.alibaba.fastjson.JSON;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.JXLExcelUtils;
import com.sgai.property.common.util.ToonVisitor;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.dao.ICustomCardInfoDao;
import com.sgai.property.customer.entity.CustomCardInfo;
import com.sgai.property.customer.entity.CustomLevelInfo;
import com.sgai.property.customer.entity.CustomTypeInfo;
import com.sgai.property.customer.entity.PersonalRecordInfo;
import com.sgai.property.customer.vo.PersonalUpLoadDto;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具service
 *
 * @author hou
 * @date 2017-12-25 15:51
 */
@Service
@Transactional
public class CommonServiceImpl {
    private static final Logger Logger = LogManager.getLogger(CommonServiceImpl.class);

    @Autowired
    private TokenServerImpl tokenServer;
    @Autowired
    private CustomInfoServiceImpl customInfoService;
    @Autowired
	private BaseCodeService baseCodeService;
    @Autowired
    private ICustomCardInfoDao customCardInfoDao;
    @Autowired
    private CustomLevelInfoServiceImpl customLevelInfoService;
    @Autowired
    private CustomTypeInfoServiceImpl customTypeInfoService;
    @Autowired
    private PersonalRecordInfoServiceImpl personalRecordInfoService;
    
    @Value("${appInfo.accessId}")
	private String accessId;
	@Value("${appInfo.accessSecret}")
	private String accessSecret;
    /**
	 * MethodName : sendMessage
	 *
	 * @Description : 发送消息通知
	 * @param messageEntityList
	 * @return
	 *
	 * @author zhangxiang
	 * @date: 2017年11月14日 下午1:42:59
	 */
	public boolean sendMessage(List<MessageEntity> messageEntityList) {
		if(messageEntityList != null && messageEntityList.size() > 0) {
			try {
				Response<Boolean> response = null;//commonsRomeotService.sendMessageByQueue(messageEntityList);
				Logger.info("sendMessage:res:{"+response.getCode()+"}");
                return response != null && "0".equals(response.getCode());
			} catch (Exception e) {
				Logger.error(e.getMessage());
			}
		}
		return true;
	}
	
	public String findCodeByToken() {
		ToonVisitor visitor = new ToonVisitor();
		visitor.setFeed_id(UserServletContext.getUserInfo().getFeeId());
		Response<String> response =null;//commonsRomeotService.getCode(accessId, accessSecret, JSON.toJSONString(toonCode));
		Logger.info("----findCodeByToken:--->"+  JSON.toJSONString(response));
		if(response != null) {
			return response.getData();
		}
		return "";
	}

    /**
     * 导入excel

     * @param file
     * @return
     */
    public Boolean uploadCustomList(MultipartFile file){

        //创建处理EXCEL的类
        ReadExcel readExcel=new ReadExcel();
        try {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//            StringBuilder stringBuilder = new StringBuilder();
//            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 上传文件
                MultipartFile mf = file;
                if (mf.getSize() > Constants.Excel.Max) {
                    throw new BusinessException(ReturnType.ParamIllegal,"Excel不能大于10M");
                }
                String fileName = mf.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                String extName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
                //判断是否为允许上传的文件类型
                if (!Constants.Excel.Type.contains(extName)) {
                    throw new BusinessException(ReturnType.ParamIllegal,"类型错误,请检查!");
                }
                //获取到httpRequest中的excel文件,将文件转换成输入流
                InputStream inputStream = mf.getInputStream();

                JXLExcelUtils JXLExcelUtils = new JXLExcelUtils();
                String[] uniqueFields = new String[]{"prName"};
                List<PersonalUpLoadDto> listResources = readExcel.getExcelInfo(file);
                listResources.stream().forEach(personalUpLoadDto -> {
//                    PersonalRecordInfo recordInfo = new PersonalRecordInfo();
//                    recordInfo.setPrName(personalUpLoadDto.getPrName());
//                    recordInfo.setPrPhoneFirst(personalUpLoadDto.getPrPhoneFirst());
//                    DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                    LocalDateTime parse = LocalDateTime.parse(personalUpLoadDto.getPrBirth(), ftf);
//                    recordInfo.setPrBirth(LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//                    List<PersonalRecordInfo> list = personalRecordInfoService.findList(recordInfo);
//                    List<String> ids = new ArrayList<>();
//                    list.stream().forEach(personalRecordInfo -> {
//                        ids.add(personalRecordInfo.getId());
//                    });
//
//                    personalRecordInfoService.deleteByListId(ids);

                    PersonalRecordInfo personalInfo = new PersonalRecordInfo();
                    personalInfo.setClId(UUID.randomUUID().toString());
                    personalInfo.setPrName(personalUpLoadDto.getPrName());
                    personalInfo.setPrPhoneFirst(personalUpLoadDto.getPrPhoneFirst());
                    personalInfo.setPrPhoneSecond(personalUpLoadDto.getPrPhoneSecond());
                    personalInfo.setPrEmail(personalUpLoadDto.getPrEmail());
                    personalInfo.setPrSex("男".equals(personalUpLoadDto.getPrSex())?1L:2L);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String prBirth = personalUpLoadDto.getPrBirth();
                    try {
                        Date date = formatter.parse(prBirth);
                        personalInfo.setPrBirth(date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //类别id
                    CustomLevelInfo info = new CustomLevelInfo();
                    info.setLevelName(personalUpLoadDto.getLevelName());
                    info.setComCode(UserServletContext.getUserInfo().getComCode());
                    info.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    CustomLevelInfo levelInfo = customLevelInfoService.get(info);
                    if(levelInfo!=null && levelInfo.getId()!=null && !"".equals(levelInfo.getId())){
                        personalInfo.setClId(levelInfo.getId());
                    }
                    //类型id
                    CustomTypeInfo typeInfo = new CustomTypeInfo();
                    typeInfo.setTypeName(personalUpLoadDto.getTypeName());
                    typeInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                    typeInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    CustomTypeInfo customTypeInfo = customTypeInfoService.get(typeInfo);
                    if(customTypeInfo!=null && customTypeInfo.getId()!=null && !"".equals(customTypeInfo.getId())){
                        personalInfo.setCtId(customTypeInfo.getId());
                    }
                    personalInfo.setPrIsDelete(0L);
                    personalInfo.setCreatedBy(UserServletContext.getUserInfo().getUserName());
                    personalInfo.setCreateTime(System.currentTimeMillis());
                    personalInfo.setCreatedDt(new Date());
                    personalInfo.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
                    personalInfo.setUpdateTime(System.currentTimeMillis());
                    personalInfo.setUpdatedDt(new Date());
                    personalInfo.setComId(UserServletContext.getUserInfo().getComCode());
                    personalInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                    personalInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());

//                    personalInfo.setDeptName(personalUpLoadDto.getDeptName());
                    personalRecordInfoService.save(personalInfo);

                    //封装客户证件信息
                    CustomCardInfo customCardInfo = new CustomCardInfo();
                    customCardInfo.setId(UUID.randomUUID().toString());
                    customCardInfo.setPrId(personalInfo.getId());
                    customCardInfo.setCcCertificateNo(personalUpLoadDto.getCcCertificateNo());
                    customCardInfo.setCcCertificateName(personalUpLoadDto.getCcCertificateName());
                    customCardInfo.setCreateTime(System.currentTimeMillis());
                    customCardInfo.setUpdateTime(System.currentTimeMillis());
                    customCardInfo.setUpdateUserId(UserServletContext.getUserInfo().getEmCode());
                    customCardInfo.setUpdateUserName(UserServletContext.getUserInfo().getUserName());
                    customCardInfo.setCcIsDelete(0L);
                    customCardInfo.setComCode(UserServletContext.getUserInfo().getComCode());
                    customCardInfo.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    customCardInfo.setCreatedBy(UserServletContext.getUserInfo().getUserName());
                    customCardInfo.setCreateTime(System.currentTimeMillis());
                    customCardInfo.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
                    customCardInfo.setUpdateTime(System.currentTimeMillis());
                    customCardInfoDao.insert(customCardInfo);

                });
                return true;
//            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return
     */
    private LinkedHashMap<String, String> converPersonalUpLoadDtoToMap(){
        LinkedHashMap<String,String> resultMap = new LinkedHashMap<>();
        resultMap.put("姓名","prName");
        resultMap.put("手机号","prPhoneFirst");
        resultMap.put("手机号1","prPhoneSecond");
        resultMap.put("邮箱","prEmail");
        resultMap.put("性别","prSex");
        resultMap.put("出生日期","prBirth");
        resultMap.put("客户类型","typeName");
        resultMap.put("客户级别","levelName");
        resultMap.put("证件号码","ccCertificateNo");
        resultMap.put("证件类型","ccCertificateName");

        return resultMap;
    }

    private ArrayList<String> converPersonalUpLoadDtoToList(){
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add("姓名");
        resultList.add("手机号");
        resultList.add("手机号1");
        resultList.add("邮箱");
        resultList.add("性别");
        resultList.add("出生日期");
        resultList.add("客户类型");
        resultList.add("客户级别");
        resultList.add("证件号码");
        resultList.add("证件类型");
//        resultList.add("部门名称");

        return resultList;
    }

    private ArrayList<String> converPersonalUpLoadDtoToList2(){
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add("张三");
        resultList.add("13988888888");
        resultList.add("18900787645");
        resultList.add("zhangsan@163.com");
        resultList.add("男");
        resultList.add("1994-02-05");
        resultList.add("国企客户");
        resultList.add("钻石级别");
        resultList.add("220987199402052489");
        resultList.add("身份证");
//        resultList.add("部门名称");

        return resultList;
    }


    public void downloadExcel(HttpServletResponse response) throws IOException, WriteException {
        //创建工作流
        OutputStream os = null;
        //初始化工作表
        WritableWorkbook workbook = null;
        try {
            response.setContentType("UTF-8");
            //设置弹出对话框
            response.setContentType("application/DOWLOAD");
            //设置工作表的标题
            String fileName = "个人客户档案模板.xls";
            response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            os = response.getOutputStream();
            //创建工作表
            workbook = Workbook.createWorkbook(os);
            //定义工作表 sheet 标题
            WritableSheet ws = workbook.createSheet("个人客户档案模板", 0);
            ws.getSettings().setShowGridLines(true);
            ws.getSettings().setProtected(false);
            //控制列的宽度,如果你要不给一样的宽度,就单独写,i代表的是列的下标,从0开始 ,从左到右
            for(int i=0;i<7;i++){
                ws.setColumnView(i, 20);
            }

            ArrayList<String> fieldList =  converPersonalUpLoadDtoToList();

            ArrayList<String> fieldList2 =  converPersonalUpLoadDtoToList2();

            // 創建标题列名称   （此处可以利用一维数组输出，会更简单）
            Label titleLabel = null;

            for(int j = 0;j < 2;j++){
                if(j==1){
                    for(int i=0 ; i< fieldList.size() ; i++){
                        titleLabel = new Label(i, 0, fieldList.get(i));
                        ws.addCell(titleLabel);
                    }
                }else if(j==2){
                    for(int i=0 ; i< fieldList2.size() ; i++){
                        titleLabel = new Label(i, 0, fieldList2.get(i));
                        ws.addCell(titleLabel);
                    }
                }

            }

        }catch (Exception e){

        }finally {
            workbook.write();
            workbook.close();
            os.close();
        }
    }





}
