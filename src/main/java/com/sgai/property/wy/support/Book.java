package com.sgai.property.wy.support;

import java.util.List;
import java.util.Map;

public class Book {
    private Map<String,Integer> menu;
    
    private List<Map<String,Object>> page;

	public Map<String, Integer> getMenu() {
		return menu;
	}

	public void setMenu(Map<String, Integer> menu) {
		this.menu = menu;
	}

	public List<Map<String, Object>> getPage() {
		return page;
	}

	public void setPage(List<Map<String, Object>> page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Book [menu=" + menu + ", page=" + page + "]";
	}
    
    
}
