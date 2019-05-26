package com.sgai.property.common.util;

import org.apache.commons.codec.binary.Base64;
import springfox.documentation.annotations.ApiIgnore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author ppliu
 * created in 2018/12/14 12:02
 */
public class Base64Util {
    /**
     * Base64字符串转 二进制流
     *
     * @param base64String Base64
     * @return base64String
     */
    @ApiIgnore
    public static byte[] getStringImage(String base64String) {
        if (base64String == null) {
            return null;
        }
        try {
            // Base64解码
            byte[] bytes = Base64.decodeBase64(base64String);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(bytes);
            outputStream.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}
