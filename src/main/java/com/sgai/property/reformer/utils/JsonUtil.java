package com.sgai.property.reformer.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Charsets;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * json转换工具类.
 *
 * @author ppliu
 * created in 2018/7/4 16:14
 */
public class JsonUtil {
    private static final String KEY = "cmVmb3JtZXJyZWZvcm1lcg==";

    //对象转换为json 并加密返回String
    public static String beanFormatToJson(Object object) {

        String json = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);

        Key k = AESUtils.toKey(Base64.decodeBase64(KEY));
        byte[] encryptData = new byte[0];
        try {
            encryptData = AESUtils.encrypt(json.getBytes(Charsets.UTF_8), k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将二进制转化为十六进制
        String encryptStr = AESUtils.parseByte2HexStr(encryptData);
        return encryptStr;
    }

    /**
     * @param str   收到响应的加密串
     * @param clazz 类
     * @param <T>   dto泛型
     * @return dto对象
     */
    public static <T> T jsonFormatToBean(String str, Class<T> clazz) {
        Key k = AESUtils.toKey(Base64.decodeBase64(KEY));
        try {
            byte[] decryptData = AESUtils.decrypt(AESUtils.parseHexStr2Byte(str), k);
            String stoBean = new String(decryptData,Charsets.UTF_8);

            return JSON.parseObject(stoBean, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * json转换带有list字段的bean.
     *
     * @param jsonString json串.
     * @param clazz      类类型
     * @param <T>        泛型
     * @return bean
     */
    public static <T> T jsonToBeanContainList(String jsonString, Class<T> clazz,Class<?> clazz1,String list) {
        Key k = AESUtils.toKey(Base64.decodeBase64(KEY));
        byte[] decryptData = new byte[0];
        try {
           decryptData = AESUtils.decrypt(AESUtils.parseHexStr2Byte(jsonString), k);
            String stoBean = new String(decryptData,Charsets.UTF_8);
            JSONObject jsonObject = JSONObject.fromObject(stoBean);
            Map<String, Class> map = new HashMap<>();
            map.put(list, clazz1);
            return (T) JSONObject.toBean(jsonObject, clazz, map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
