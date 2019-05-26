package com.sgai.property.quality.service.plan;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.quality.dao.plan.ITypeDao;
import com.sgai.property.quality.entity.plan.Type;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import com.sgai.property.quality.vo.plan.TypeParam;
import com.sgai.property.quality.vo.plan.TypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypePCServiceImpl extends MoreDataSourceCrudServiceImpl<ITypeDao,Type> {

	@Autowired
	private PTypeServiceImpl typeService;
	@Autowired
	private ITypeDao typeDao;
	
	public Page<TypeVo> getListPage(int pageNum, int pageSize) {
		Type type =new Type();
		type.setIsDelete(0L);
		Page<Type> page = new Page<Type>(pageNum, pageSize);
		type.setPage(page).setOrderBy("type_code,create_time DESC");
		page.setList(typeDao.findList(type));
		
		Page<TypeVo> pageVo=new Page<TypeVo>();
		BeanUtils.copyProperties(page, pageVo);
		
		List<TypeVo> typeVos =new ArrayList<TypeVo>();
		if (page.getList().size()>0) {
			for (Type ty : page.getList()) {
				TypeVo typeVo =new TypeVo();
				BeanUtils.copyProperties(ty, typeVo);
				typeVos.add(typeVo);
			}
		}
		pageVo.setList(typeVos);
		return pageVo;
	}

	public Type saveType(TypeParam typeParam) {
        Type type =new Type();
        BeanUtils.copyProperties(typeParam, type);
        
        type.setOrgId(UserServletContext.getUserInfo().getComCode());
        type.setTypeCode(1L);
        type.setIsDelete(0L);
        type.setCreateTime(System.currentTimeMillis());
        type.setUpdateTime(System.currentTimeMillis());
        typeService.save(type);
        
        return type;
	}

	public List<Type> getList() {
		Type type =new Type();
		type.setIsDelete(0L);
		List<Type> types= typeDao.findList(type);
		return types;
	}

	public TypeVo getTypeById(String id) {
		TypeVo typeVo =new TypeVo();
		Type type = typeService.getById(id);
		if (type!=null) {
			BeanUtils.copyProperties(type, typeVo);
		}
		return typeVo;
	}

}