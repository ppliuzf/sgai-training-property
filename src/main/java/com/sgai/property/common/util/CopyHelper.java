package com.sgai.property.common.util;

import java.util.List;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.springframework.cglib.beans.BeanCopier;


public class CopyHelper{
	public static void copyObj(Object src, Object obj){
		if(src == null || obj == null ){
			return;
		}
		BeanCopier copier = BeanCopier.create(src.getClass(), obj.getClass(), false);
		copier.copy(src, obj, null);
	}
	
	public static void copyList(List srcList, List objList, Class<?> clazz) {
		if (srcList == null || objList == null || srcList.size() == 0) {
			return;
		}
		BeanCopier copier = BeanCopier.create(srcList.get(0).getClass(), clazz, false);
		try {
			for (int i = 0; i < srcList.size(); i++) {
				Object t = clazz.newInstance();
				if(srcList.get(i) instanceof List){
				}
				copier.copy(srcList.get(i), t, null);
				objList.add(t);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new BusinessException(ReturnType.Unknown, "");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new BusinessException(ReturnType.Unknown, "");
		}
	}
}