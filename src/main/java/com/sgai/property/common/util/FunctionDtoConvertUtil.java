package com.sgai.property.common.util;

import com.sgai.property.ctl.dto.FunctionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ppliu
 * created in 2018/12/27 16:37
 */
public class FunctionDtoConvertUtil {
    public static List<Map<String, String>> convertDtoToMap(List<FunctionDto> unOwnedList) {
        List<Map<String, String>> result = new ArrayList<>();
        unOwnedList.stream().forEach(functionDto -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", functionDto.getMenuCode());
            map.put("pId", functionDto.getParentMenuCode());
            map.put("name", functionDto.getMenuName());
            result.add(map);
        });
        return result;
    }
}
