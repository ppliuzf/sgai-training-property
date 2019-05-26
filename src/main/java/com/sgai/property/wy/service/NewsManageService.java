  
    /**    
    * @Title: NewsManageService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.config.Global;
    import com.sgai.common.persistence.Page;
    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.NewsManageDao;
    import com.sgai.property.wy.entity.NewsManage;
    import com.sgai.property.common.configuration.properties.RemoteProperties;
    import com.sgai.property.wy.support.ResponseResult;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.multipart.MultipartFile;

    import javax.servlet.http.HttpServletRequest;
    import java.io.*;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.UUID;


    /**  
 * @ClassName: NewsManageService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class NewsManageService extends CrudServiceExt<NewsManageDao, NewsManage> {
		  @Autowired
		  private NewsManageDao newsManageDao;
		 // @Resource(name="systemConfiger")
		  @Autowired
		  private RemoteProperties systemConfiger;
		    /**  
		    * @Title: upNewsStatus  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param nm    参数  
		    * @return void    返回类型  
		    * @throws  
		    */  
		@Transactional(readOnly = false)
		public void upNewsStatus(NewsManage nm) {
				newsManageDao.upNewsStatus(nm);
		}
			  
			    /**  
			    * @Title: getWorkloadList  
			    * (这里用一句话描述这个方法的作用)
			    * @param @param page
			    * @param @param newsManage
			    * @param @return    参数  
			    * @return Page<NewsManage>    返回类型  
			    * @throws  
			    */  
			    
			public Page<NewsManage> getWorkloadList(Page<NewsManage> page, NewsManage newsManage) {
				newsManage.setPage(page);
				List<NewsManage> list = dao.getWorkloadList(newsManage);
				page.setList(list);
				return page;
			}
			public Map uploadFile(HttpServletRequest request, MultipartFile multipartFile, String uploadPeople) {
				Map<String, Object> map = new HashMap<String, Object>();
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
		        String rootPath= Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL + "/images/" ;
		        try {
		        	//RemoteProperties remoteProperties=new RemoteProperties();
		        	// newFileName = upload(rootPath, fileName, in);
		        	String a=systemConfiger.getPicRealPath();
		        	newFileName = upload(a, fileName, in);
		        } catch (IOException e) {
		            e.printStackTrace();
		            r.setSuccess(false);
		        }
		       // savaFileMessage(sourceType, sourceKey, fileName, fileType, fileSize, newFileName, uploadPeople);
		        map.put("fileName", fileName);
		        map.put("newFileName",newFileName);
		        map.put("msg", "success");
		        return map;
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
			 private void readAndWrite(OutputStream out, InputStream in) throws IOException {
			        byte[] buffer = new byte[1024 * 1024];
			        int length;
			        while ((length = in.read(buffer)) > 0) {
			            out.write(buffer, 0, length);
			        }
			        in.close();
			        out.close();
			    }

			  
			    /**  
			    * @Title: findCode  
			    * (这里用一句话描述这个方法的作用)
			    * @param @param newsManage2
			    * @param @return    参数  
			    * @return NewsManage    返回类型  
			    * @throws  
			    */  
			    
			public NewsManage findCode(NewsManage newsManage2) {
				return dao.findCode(newsManage2);
			}

				  
				    /**  
				    * @Title: insertCauseHistory  
				    * (这里用一句话描述这个方法的作用)
				    * @param @param nm    参数  
				    * @return void    返回类型  
				    * @throws  
				    */  
				    
				public void insertCauseHistory(NewsManage nm) {
					nm.setHistoryId(UUID.randomUUID().toString());
					dao.insertCauseHistory(nm);
				}

					  
					    /**  
					    * @Title: getHistoryList  
					    * (这里用一句话描述这个方法的作用)
					    * @param @param page
					    * @param @param newsManage
					    * @param @return    参数  
					    * @return Page<NewsManage>    返回类型  
					    * @throws  
					    */  
					    
					public Page<NewsManage> getHistoryList(Page<NewsManage> page, NewsManage newsManage) {
						newsManage.setPage(page);
						List<NewsManage> list = dao.getHistoryList(newsManage);
						page.setList(list);
						return page;
					}
}
