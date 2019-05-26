
package com.sgai.property.wy.service;

import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.common.configuration.properties.RemoteProperties;
import com.sgai.property.wy.dao.SysFileDao;
import com.sgai.property.wy.entity.SysFile;
import com.sgai.property.wy.support.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 *
 */
@Service
public class SysFileService extends CrudServiceExt<SysFileDao, SysFile> {
    //private static final String ROOT_PATH = "D:/wy_files";
    //private static final String ROOT_PATH_URL = "http://119.3.200.58:8087/files";
    @Autowired
    private RemoteProperties systemConfiger;
    @Autowired
    private SysFileDao sysFileDao;
    @Transactional(readOnly = false)
    public String uploadFile(HttpServletRequest request, MultipartFile multipartFile, String uploadPeople) {
        ResponseResult r = new ResponseResult();
        InputStream in = null;
        try {
            in = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            r.setSuccess(false);
        }
        String sourceType = request.getParameter("sourceType");
        String sourceKey = request.getParameter("sourceKey");
        String fileName = multipartFile.getOriginalFilename();
        String fileType = multipartFile.getContentType();
        Long fileSize = multipartFile.getSize();
        String newFileName = null;
        try {
            newFileName = upload(systemConfiger.getPicRealPath(), fileName, in);
        } catch (IOException e) {
            e.printStackTrace();
            r.setSuccess(false);
        }
        savaFileMessage(sourceType, sourceKey, fileName, fileType, fileSize, newFileName, uploadPeople);
        return r.toString();
    }

    private SysFile savaFileMessage(String sourceType, String sourceKey, String fileName, String fileType, Long fileSize, String newFileName, String uploadPeople) {
        SysFile sysFile = new SysFile();
        sysFile.setFileName(fileName);
        sysFile.setFileSize(fileSize);
        sysFile.setSourceKey(sourceKey);
        sysFile.setSourceType(sourceType);
        sysFile.setFileType(fileType);
        sysFile.setRealPath(systemConfiger.getPicRealPath() + "/" + newFileName);
        sysFile.setUploadTime(new Date());
        sysFile.setUrlPath(systemConfiger.getPicRealPath() + "/" + newFileName);
        sysFile.setUploadPeople(uploadPeople);
        save(sysFile);
        return sysFile;
    }

    private String upload(String realPath, String fileName, InputStream in) throws IOException {
        //创建文件目录
        File forder = new File(realPath);
        if (!forder.exists()) {
            forder.mkdirs();
        }
        //复制文件到服务器上
        String newFileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));//随机编码+文件类型
        File uploadFile = new File(forder + "/" + newFileName);
        OutputStream out = new FileOutputStream(uploadFile);
        readAndWrite(out, in);
        return newFileName;
    }

    /**
     * 文件下载
     *
     * @param response
     * @param id
     */
    public void downLoad(HttpServletResponse response, String id) {
        SysFile sysFile = get(id);
        String realPath = sysFile.getRealPath();
        String fileName=sysFile.getFileName();
        try {
            response.setHeader("Content-disposition", "attacment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            InputStream in = new FileInputStream(realPath);
            readAndWrite(out, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndWrite(OutputStream out, InputStream in) throws IOException {
        byte[] buffer = new byte[1024 * 1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }

}
