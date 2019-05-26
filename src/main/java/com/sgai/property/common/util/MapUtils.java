package com.sgai.property.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * map处理工具类
 */
public class MapUtils {
    /**
     * 将map转换为键值对的list
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> mapToList(Map<String, Object> params) {
        
    	List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String value = entry.getValue().toString();
                if (value != null && value.length() > 0) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        return pairs;
    }
}
