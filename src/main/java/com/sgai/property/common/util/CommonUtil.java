package com.sgai.property.common.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

	/**
	 * 按指定大小，分隔集合，将集合按规定个数分为n个部分
	 * 
	 * @param list
	 * @param len
	 * @return
	 */
	public static List<List<String>> splitList(List<String> list, int len) {
		if (list == null || list.size() == 0 || len < 1) {
			return null;
		}
		List<List<String>> result = new ArrayList<List<String>>();
		int size = list.size();
		int count = (size + len - 1) / len;

		for (int i = 0; i < count; i++) {
			List<String> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}

}
