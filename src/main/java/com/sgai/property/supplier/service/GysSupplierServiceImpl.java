package com.sgai.property.supplier.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.supplier.dao.IGysSupplierDao;
import com.sgai.property.supplier.entity.GysFile;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.vo.GysSupplierParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GysSupplierServiceImpl extends MoreDataSourceCrudServiceImpl<IGysSupplierDao,GysSupplier>{
	@Autowired
	private IGysSupplierDao gysSupplierDao;
	@Autowired
	private GysFileServiceImpl gysFileService;

//	@Value("${mat.comCode}")
//	private String comCode;
//	@Value("${mat.sequCode}")
//	private String sequCode;

	/**
	 * 根据供应商类型查询供应商数量
	 * @param gysSupplier
	 * @return
	 */
	public int getTypeCount(GysSupplier gysSupplier){
		int count = 0;
		if(gysSupplier!=null && gysSupplier.getTypeId()!=null && !"".equals(gysSupplier.getTypeId())){
			count = gysSupplierDao.getTypeCount(gysSupplier);
			return count;
		}else{
			return count;
		}
	}


	/**
	 * 添加供应商
	 * @param gysSupplierParams
	 */
	@Transactional
	public void save(GysSupplierParams gysSupplierParams){
		GysSupplier gysSupplier = new GysSupplier();
		BeanUtils.copyProperties(gysSupplierParams,gysSupplier);
		gysSupplier.setNo(gysSupplierParams.getNo());
		//添加toonCode中的参数
		gysSupplier.setComName(UserServletContext.getUserInfo().getComName());
		//启用
		gysSupplier.setIsEnabled(1L);
		//不删除
		gysSupplier.setIsDelete(0L);
		gysSupplier.setComCode(UserServletContext.getUserInfo().getComCode());
		this.save(gysSupplier);

		//添加之后获取供应商主键，批量添加上传附件
		String id = gysSupplier.getId();
		for(GysFile gysFile : gysSupplierParams.getGysFileList()){
			//如果上传附件的URL为空则不添加，如果不为空则添加
			if(gysFile!=null && gysFile.getFileUrl()!=null){
				GysFile file = new GysFile();
				file.setFileName(gysFile.getFileName());
				file.setFileUrl(gysFile.getFileUrl());
				file.setSupplierId(id);
				gysFileService.save(file);
			}
		}
	}
}