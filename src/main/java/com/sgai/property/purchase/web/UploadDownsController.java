package com.sgai.property.purchase.web;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import com.sgmart.upload.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * description:上传
 */
@RestController
@RequestMapping(value = "/uploadDown")
public class UploadDownsController {

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 图片上传
     * @param request
     * @param file
     */
    @RequestMapping(value = "/uploadImages",method = {RequestMethod.POST})
    @PermessionLimit(limit = false)
    public Response<String> attachUploads(HttpServletRequest request, MultipartFile file) throws Exception {
        Response<String> result = new Response<String>();
        String urlPath = "";
        try{
            urlPath = uploadFileService.uploadFile(file);
        }catch (Exception e){
            result.setCode(ReturnType.Error.getCode());
            result.setMessage(ReturnType.Error.getType());
        }
        result.setCode(ReturnType.Success.getCode());
        result.setData(urlPath);
        return result;
    }

    @RequestMapping(value = "/uploadFile",method = {RequestMethod.POST})
    @PermessionLimit(limit = false)
    public Response<String> uploadFiles(HttpServletRequest request, MultipartFile file) throws Exception {
        Response<String> result = new Response<String>();
        String urlPath = "";
        try{
            urlPath = uploadFileService.uploadFile(file);
        }catch (Exception e){
            result.setCode(ReturnType.Error.getCode());
            result.setMessage(ReturnType.Error.getType());
        }
        result.setCode(ReturnType.Success.getCode());
        result.setData(urlPath);
        return result;
    }

}
