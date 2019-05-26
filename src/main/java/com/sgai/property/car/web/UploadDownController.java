package com.sgai.property.car.web;

import com.sgai.common.utils.IdGen;
import com.sgai.property.car.vo.UploadParam;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.Base64Util;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * description:上传下载
 * Created by llh on 2017/3/20.
 */
@RestController
@RequestMapping(value = "/uploadDown")
@Api(description = "上传下载")
public class UploadDownController extends BaseController {
    private static final Logger logger = LogManager.getLogger(UploadDownController.class);

    //    @Value("${ck.uploadUrl}")
    private String uploadUrl = "http://192.168.144.246:20001/static/ckfinder/core/connector/java/connector.java";
    //    @Value("${ck.visitUrl}")
    private String visitUrl = "/userfiles/files/";

    private static final Long IMAGE_UPLOAD_SIZE_LIMIT = 10485760L;
    private static final String fileType = "gif,png,bmp,jpeg,jpg";


    @ApiOperation(value = "图片上传 - 表单上传,适合pc端", notes = "图片上传，支持批量,成功返回url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken", value = "token", required = true, paramType = "header", dataType = "String")
    })
    @RequestMapping(value = "/uploadImage", method = {RequestMethod.POST})
    public Response<String> uploadReturnUrl( HttpServletRequest request) {
        Response<String> result = new Response<>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 上传文件
                MultipartFile mf = entity.getValue();
                if (mf.getSize() > IMAGE_UPLOAD_SIZE_LIMIT) {
                    result.setMessage(ReturnType.Error.getType());
                    result.setData("您上传的图片大于10M,请重新选择！");
                    result.setCode(ReturnType.Error.getCode());
                    return result;
                }
                String fileName = mf.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                String extName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
                //判断是否为允许上传的文件类型
                if (!fileType.contains(extName.toLowerCase())) {
                    result.setData("文件类型不正确，必须为" + fileType + "的文件！");
                    result.setCode(ReturnType.Error.getCode());
                    result.setMessage(ReturnType.Error.getType());
                    return result;
                }
                String newFileName = IdGen.uuid() + fileExt;
                String url = uploadCkfinder(mf.getBytes(), newFileName);
                stringBuilder.append(url).append(";");
            }
            result.setData(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setData("error");
            result.setCode(ReturnType.ParamIllegal.getCode());
            return result;
        }
        return result;
    }

    @ApiOperation(value = "图片上传 - base64加密上传,app端", notes = "图片上传，支持批量,成功返回url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken", value = "token", required = true, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "dataList", value = "base64位数据List<String>集合", required = true, paramType = "body", dataType = "list")
    })
    @RequestMapping(value = "/uploadBase64AndReturnUrl", method = {RequestMethod.POST})
    public Response<String> uploadBase64AndReturnUrl(
                                                     @RequestBody List<String> dataList) {
        Response<String> result = new Response<>();
        try {
            if (dataList.size() == 0) {
                result.setData("请上传图片！");
                result.setCode(ReturnType.Error.getCode());
                result.setMessage(ReturnType.Error.getType());
                return result;
            }
            StringBuilder stringBuilder = new StringBuilder();
            String fileExt = ".jpg";
            for (String data : dataList) {
                String newFileName = IdGen.uuid() + fileExt;
                String url = uploadCkfinder(Base64Util.getStringImage(data), newFileName);
                stringBuilder.append(url).append(";");
            }
            result.setData(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ReturnType.ParamIllegal.getCode());
            result.setData("error");
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }


    @ApiOperation(value = "图片上传", notes = "图片上传，上传单张图片,成功返回url")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accessToken", value = "token", required = true, paramType = "header", dataType = "String"),
    })
    @RequestMapping(value = "/uploadBase64BackUrl", method = {RequestMethod.POST})
    public Response<String> uploadBase64BackUrl(
                                                @RequestBody UploadParam uploadParam) {
        Response<String> result = new Response<>();
        try {
            String data = uploadParam.getImgStr();
            String fileExt = ".jpg";
            String newFileName = IdGen.uuid() + fileExt;
            String url = uploadCkfinder(Base64Util.getStringImage(data), newFileName);
            result.setData(url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage(e.getMessage());
            result.setCode(ReturnType.ParamIllegal.getCode());
            result.setData("error");
            return result;
        }
        return result;
    }

    private String uploadCkfinder(byte[] data, String imageName) {
        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            String url = uploadUrl + "?command=FileUpload&type=files&langCode=zh-cn&hash=4a21e39dae389bff85cb9a3ca5b97d88&startupPath=&response_type=txt";
            map.add("currentFolder", "/");
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            ByteArrayResource arrayResource = new ByteArrayResource(data) {
                @Override
                public String getFilename() throws IllegalStateException {
                    return imageName;
                }
            };
            map.add("file", arrayResource);
            String resp = restTemplate.postForObject(url, map, String.class);
            return visitUrl + resp.substring(0, resp.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
