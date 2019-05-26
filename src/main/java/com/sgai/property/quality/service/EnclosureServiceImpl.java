package com.sgai.property.quality.service;

import com.sgai.property.quality.dao.IQtPlanItemEnclosureDao;
import com.sgai.property.quality.entity.QtPlanItemEnclosure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnclosureServiceImpl{

	@Autowired
    IQtPlanItemEnclosureDao enclosureDao;
	
	public List<Map<String, Object>> getEnclosures(String tiid, Integer type) {
		QtPlanItemEnclosure enclosure = new QtPlanItemEnclosure();
		enclosure.setTId(tiid);
		enclosure.setTType(type);
		List<QtPlanItemEnclosure> enclosureList = enclosureDao.findList(enclosure);
		List<Map<String, Object>> enclosures = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(enclosureList)){
			for(QtPlanItemEnclosure e:enclosureList){
				Map<String, Object> map = new HashMap<>();
				map.put("id", e.getId());
				map.put("url", e.getEnclosure());
				enclosures.add(map);
			}
		}
		return enclosures;
	}

}
