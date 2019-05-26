package com.sgai.property.video.util;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 描述:图像处理工具类.
 *
 * @author ppliu
 * created in 2019/5/1 18:15
 */
public class ImgeUtil {

    /**
     *
     * @param image 图片.
     * @param imgType 图片类型
     * @return base64串.
     */
    public static String imageToBase64(BufferedImage image, String imgType) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
