package com.sgai.property.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgai.property.commonService.vo.organ.CodeMessage;
import com.sgai.property.commonService.vo.organ.CommonResult;

/**
 * @author
 * @ClassName: JSONUtil
 * @Description: 自定义的JSON工具类
 * 
 */
public class JSONUtil {

	public static <T> CommonResult<T> parseObject(String json, Class<T> clazz) {
		CommonResult<T> result = new CommonResult<T>();
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (jsonObject.get("meta") != null) {
			result.setMeta(JSON.parseObject(jsonObject.get("meta").toString(),
					CodeMessage.class));
		}
		if (jsonObject.get("data") != null) {
			result.setData(JSON.parseArray(jsonObject.get("data").toString(),
					clazz));
		}
		return result;
	}
}
