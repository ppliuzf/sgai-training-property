package com.sgai.property.reformer.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * post相关工具类.
 * @author ppliu
 * created in 2018/12/7 14:58
 */
public class PostUtils {
    /**
     * 获取文本类型的请求内容.
     * @param request Http请求.
     * @return 请求内容.
     */
    public static String fetchPostByTextPlain(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len;
            StringBuilder contentBuffer = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                contentBuffer.append(buf, 0, len);
            }
            return contentBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
