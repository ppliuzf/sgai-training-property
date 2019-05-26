package com.sgai.property.quality.service;

import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.constants.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 工具实现类
 *
 * @author 146584
 * @create 2017-11-03 14:24
 */
@Service
public class QCommonServicelmpl{
    private static final Logger logger = LogManager.getLogger(QCommonServicelmpl.class);
    
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private BaseCodeService baseCodeService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;

    public Response<String> uploadImage(HttpServletRequest request) {
        Response<String> result = new Response<>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 上传文件
                MultipartFile mf = entity.getValue();
                if (mf.getSize() > Constants.IMAGE_UPLOAD_SIZE_LIMIT) {
                    result.setCode(ReturnType.Error.getCode());
                    result.setMessage(ReturnType.Error.getType());
                    result.setData("您上传的图片大于10M,请重新选择！");
                    return result;
                }
                String fileName = mf.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                String extName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
                //判断是否为允许上传的文件类型
                if (!Constants.imgType.contains(extName)) {
                    result.setCode(ReturnType.Error.getCode());
                    result.setMessage(ReturnType.Error.getType());
                    result.setData("图片类型不正确，必须为" + Constants.imgType + "的文件！");
                    return result;
                }
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                long id = idWorker.nextId();
//                String newFileName = id + fileExt;
//                String url = ToonUploadDownApi.getInstance().uploadCloudReturnUrl(cloudAppId, accessKeyId,
//                        accessKeySecret, cloudDomainHost, newFileName, mf.getBytes());
//                stringBuilder.append(url).append(";");
            }
            result.setData(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ReturnType.ParamIllegal.getCode());
            result.setMessage(e.getMessage());
            result.setData("error");
            return result;

        }
        return result;
    }

   

//    @Override
//    public Boolean sendToonMessage(MessageEntity entity){
//        MessageEntity msgEntity = new MessageEntity();
//        BeanUtils.copyProperties(entity,msgEntity);
//        msgEntity.setAppId(sendMsgAppId);
//        msgEntity.setAppSecret(sendMsgAppKey);
//        return SendToonMessageUtil.sendToonMessage(msgEntity).getData();
//    }


//	public ToonCode parseToken(String accessToken) {
//		String codeStr = (String)redisClient.get(Constants.TOKEN_KEY_PREFIX + accessToken);
//        ToonCode code = JSONObject.parseObject(codeStr, ToonCode.class);
//        if(code == null){
//        	throw new BusinessException(ReturnType.TokenError, "token解析失败！");
//        }
//        return code;
//	}






   public String getNewCode(Long comId, Long eiId,String appId,String appSecret){
	   String code = "";
       EmpInfoVo emp = baseEmployeeService.getEmpInfoById(comId, eiId.toString());
	   if(emp != null){
	        code = baseCodeService.getNumber(appId,appSecret);
	   }
	   return code;
   }
}
