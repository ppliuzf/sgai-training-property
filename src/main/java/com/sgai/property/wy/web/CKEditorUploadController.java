
/**    
* @Title: CKEditorUploadController.java  
* @Package com.sgai.property.wy.web
* (用一句话描述该文件做什么)
* @author XJ9001  
* @date 2018年2月2日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.web;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.common.configuration.properties.RemoteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName: CKEditorUploadController
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年2月2日
 * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "/news/CKEditor")
public class CKEditorUploadController {
	@Autowired
	private RemoteProperties systemConfiger;

	@RequestMapping(value = "/uploadFile")
	@PermessionLimit(limit = false)
	public void uploadFile(@RequestParam("upload") MultipartFile multipartFile,
                           HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");
		String expandedName = ""; // 文件扩展名
		String uploadContentType = multipartFile.getContentType();

		if (uploadContentType.equals("image/pjpeg")
				|| uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png")
				|| uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else if (uploadContentType.equals("application/x-shockwave-flash")) {
			expandedName = ".swf";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png/.swf文件）');");
			out.println("</script>");
			return;
		}
		if (multipartFile.getSize() > 2000 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件大小不得大于2M');");
			out.println("</script>");
			return;
		}

		InputStream is = multipartFile.getInputStream();

		// 图片上传路径
		// 服务器路径
		// Map<String,String>
		// serverDirMap=referenceTableService.findMapByReferenceId(VSReferenceID.IMAGE_SERVER_URL);
		// 本地地址
		// Map<String,String>
		// localDirMap=referenceTableService.findMapByReferenceId(VSReferenceID.IMAGE_LOCAL_DIR);
		// String uploadPath
		// =serverDirMap.get(VSReferenceID.IMAGE_SERVER_URL)+"/pms-platform/resource/content/img/uploadImg";
		// String uploadPath
		// =localDirMap.get(VSReferenceID.IMAGE_LOCAL_DIR)+"\\pms-platform\\resource\\content\\img\\uploadImg";

		/* String uploadPath =
		 request.getSession().getServletContext().getRealPath("/") +
		 "resource\\news\\uploadImage";*/
		// String uploadPath = "D:/wy_files";
		String uploadPath = systemConfiger.getPicRealPath();
		String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名

		fileName += expandedName;

		File file = new File(uploadPath);

		if (!file.exists()) { // 如果路径不存在，创建
			file.mkdirs();
		}

		File toFile = new File(uploadPath, fileName);

		OutputStream os = new FileOutputStream(toFile);

		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();

		// 返回"图像"选项卡并显示图片 request.getContextPath()为web项目名
	//	System.out.println(request.getContextPath());
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
		// + ",'" + serverDirMap.get("PLATFORM_SERVER_URL") +
		// request.getContextPath() + "/resource/news/uploadImage/" + fileName +
		// "','')");
				+ ",'" + request.getContextPath()
				+ "http://le-xiang.cn:8888/pictrues/"+fileName+ "','')");
		out.println("</script>");

	}

}
