package com.sgai.property.common.util;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES ECB加密模式，PKCS5Padding
 * @author 137127
 *
 */
public class DESUtils {

    public static final Logger LOG = LoggerFactory.getLogger(MD5Utils.class);
    
    public static final String ENCODE_TYPE = "DES";

    public static final String ENCODE_TYPE_PADDING = "DES/ECB/PKCS5Padding";

    public static final String CHARSET = "UTF-8";

    /**
    * 加密
    * @param datasource byte[]
    * @param password String 秘钥
    * @return byte[]
    */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCODE_TYPE);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(ENCODE_TYPE_PADDING);
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Exception e) {
            LOG.error("DESUtils encrypt 失败："+e.getMessage(),e);
            throw new BusinessException(ReturnType.Unknown,"DESUtils encrypt 失败",e);
        }
    }

    /**
    * 解密
    * @param src byte[]
    * @param password String
    * @return byte[]
    * @throws Exception
    */
    public static byte[] decrypt(byte[] src, String password){
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCODE_TYPE);
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(ENCODE_TYPE_PADDING);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            LOG.error("DESUtils decrypt 失败："+e.getMessage(),e);
            throw new BusinessException(ReturnType.Unknown,"DESUtils decrypt 失败",e);
        }
    }

}
