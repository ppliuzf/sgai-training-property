
/**
 * @Title: UploadRepairImagesController.java
 * @Package com.sgai.property.wy.web
 * (用一句话描述该文件做什么)
 * @author XJ9001
 * @date 2018年4月18日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.web;

import com.alibaba.fastjson.JSONObject;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.common.configuration.properties.RemoteProperties;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * @ClassName: UploadRepairImagesController
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年4月18日
 * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "/repair/upImg")
public class UploadRepairImagesController {


    @Autowired
    private RemoteProperties systemConfiger;

    @RequestMapping(value = "/uploadImg")
    @PermessionLimit(limit = false)
    public void uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile[] files) throws IOException {

        PrintWriter writer = response.getWriter();
        // 文件保存服务器路径
        String savePath = systemConfiger.getPicRealPath();
        // 访问路径
        String saveUrl = systemConfiger.getPicUrlPath();

        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp,JPG,PNG,GIF,BMP,JPEG");

        // 最大文件大小
        long maxSize = 1000000;
        response.setContentType("text/html; charset=UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            writer.println(getError("请选择文件。"));
            return;
        }

        File uploadDir = new File(savePath);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            writer.println(getError("目录名不正确。"));
            return;
        }

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        String fileName = null;
        String uuName;
        for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            fileName = mFile.getOriginalFilename();
            // 检查文件大小
            if (mFile.getSize() > maxSize) {
                writer.println(getError("上传文件大小超过限制。"));
                return;
            }
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
            UUID uuid = UUID.randomUUID();
            String path = savePath + "/" + uuid.toString() + "." + fileExt;
            uuName = uuid.toString() + "." + fileExt;
            saveUrl = saveUrl + "/" + uuid.toString() + "." + fileExt;

            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
                FileCopyUtils.copy(mFile.getInputStream(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", saveUrl);
            obj.put("pictureNname", uuName);
            writer.println(obj.toString());
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }
}
