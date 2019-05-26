package com.sgai.property.common.util;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 通的加密解密工具类
 * @author 137127
 *
 */
public class ToonDesUtils {

    public static final Logger LOG = LoggerFactory.getLogger(ToonDesUtils.class);
    public static final String CHARSET = "UTF-8";
    
    /**
     * 加密
     * @param querryJson 查询的json数据
     * @param md5Secret md5 字段数据
     * @return
     */
    public static String encrypt(String querryJson,String md5Secret,boolean isUrlSafe){
        try {
            byte[] encrypt = DESUtils.encrypt(querryJson.getBytes(CHARSET), md5Secret);
            if(isUrlSafe){
                return Base64.encodeBase64URLSafeString(encrypt);
            }else{
                return Base64.encodeBase64String(encrypt);
            }
        } catch (Exception e) {
            LOG.error("ToonDesUtils encrypt执行失败： "+e.getMessage(),e);
            throw new BusinessException(ReturnType.Unknown,"ToonDesUtils加密操作失败",e);
        }
    }
    
    /**
     * 加密数据
     * @param querryJson
     * @param AppSecret 应用秘钥
     * @return
     */
    public static String encryptWithSecret(String querryJson,String AppSecret,boolean isUrlSafe){
       String md5 = MD5Utils.MD5(AppSecret);
       String md5Info = getMd5Info(md5);
       return encrypt(querryJson, md5Info,isUrlSafe);
    }
    
    /**
     * 解密数据
     * @param base64String
     * @param md5Secret 秘钥
     * @return
     */
    public static String decrypt(String base64String, String md5Secret){
        try {
            //base64String = URLDecoder.decode(base64String, CHARSET);
            byte[] encodeBase64 = Base64.decodeBase64(base64String.getBytes(CHARSET));
            byte[] encrypt = DESUtils.decrypt(encodeBase64, md5Secret);
            return new String(encrypt,CHARSET);
        } catch (Exception e) {
            LOG.error("ToonDesUtils decrypt执行失败： "+e.getMessage(),e);
            throw new BusinessException(ReturnType.Unknown, "ToonDesUtils解密操作失败",e);
        }
    }
    
    /**
     * 解密 
     * @param base64String
     * @param AppSecret 秘钥
     * @return
     */
    public static String decryptWithSecret(String base64String,String AppSecret){
        String md5 = MD5Utils.MD5(AppSecret);
        String md5Info = getMd5Info(md5);
        return decrypt(base64String, md5Info);
     }
    /**
     * 获取md5的中间数据，并小写输出
     * @param md5
     * @return
     */
    private static String getMd5Info(String md5){
        if(null != md5){
            if(32 == md5.length()){
                return md5.substring(8,16).toLowerCase();
            }else if(16 == md5.length()){
                return md5.substring(0,8).toLowerCase();
            }
        }
        throw new BusinessException(ReturnType.ParamIllegal, "MD5入参异常");
    }
    public static void main(String[] args) {


//        String string = "fEexbhUUTXTECT81vfrIpO7IOXC2DHdsd/rxvbA/yTgjFYgvtGU5uXxpZq5RuG+RvZEETidv2yjrNjhMBXZuLbf4s7Ecy3vhCdneQ8ZQEuT8ZG2qlN6eZrBbanZwf7p25jI7tcaTAKFGMJ7tZQxKVBVOSAiohh4Ij7I213MFAg29nLbnozQyLfdEjcqqLIAd6Qpqq2jihOPmOpAShNccQaEaQMHtDk+KUIBw984NYT7YHA0AmFT9XMVGZNyM58fC6lC8IOe0emwG6hZzsQGlOX+JptfDRtmn4j8R/5eHgGc=";
//        System.out.println(string.length());
//        String base64String = URLEncoder.encode(string, CHARSET);
//
//        //String tt ="LL94AXZLJrojYrD%2BvNcihlB%2BewD%2FypD5%2FUIlw6x3QzxkTmwe44sH7K371mSsbzgLn%2B0xmFzOCCVkGfRsRnPEWHxQcc3oc4o%2B0F5d2xkpqNbKvinvQCp2bsT4Jsl4q%2FW0OfSOAiflfWRYXCz%2BpdPiUYEsKpvIOCpQTL4bfkotFsyjsD5oWAc8kg%3D%3D";
////        System.out.println(base64String.equals(tt));
////        String appSecret = "2f5ec1e2229b4195bc71c1623e5d1937";
////        String decryptWithSecret2 = decryptWithSecret(base64String, appSecret);
////        System.out.println(decryptWithSecret2);
        String tt ="5+7GQM5zuhW68lYup4j2Vmuy7y/fJn++mDsCEoX9VOvLQcKr2AQGxARa+zH1jBTQNNMYfbO41fcqabHUmBrqpZmAGMRVXJZFKdopM69EnsNGoZ/fCdhnUHBV8p8YG09h7fOceAW/lcs747GYVOO86mOAU/cAu3kWSmnfqLr96waqcRR7HMqOj8RBmjTXqvv7dSQK95xJ9MMr1je3CBIZaDZul1CbELXpXWdFpw5czg9izlY6lT32uAzA8U2AJyMD";
        //tt = URLDecoder.decode(tt);
        //        System.out.println(base64String.equals(tt));
//        String appSecret = "f16fd3c83e4247758168493eee71f94e";
        String appSecret = "4dbd0fac1928475ea49fe1a272074a0a";
//        String appSecret1 = "7f0d620016934de08fb5006ced18947b";
//        String appSecret2 = "dc074af938e64148993c2ae499ae67b6";
//        String appSecret3 = "9a648590ea374070a4f9f1a7c6b841eb";
        try {
            String decryptWithSecret1 = decryptWithSecret(tt, appSecret);
            System.out.println(appSecret);
            System.out.println(decryptWithSecret1);
        } catch (Exception e) {
            // : handle exception
        }
//
//        try {
//            String decryptWithSecret2 = decryptWithSecret(tt, appSecret1);
//            System.out.println(appSecret1);
//            System.out.println(decryptWithSecret2);
//        } catch (Exception e) {
//            // : handle exception
//        }
//
//        try {
//            String decryptWithSecret3 = decryptWithSecret(tt, appSecret2);
//            System.out.println(appSecret2);
//            System.out.println(decryptWithSecret3);
//        } catch (Exception e) {
//            // : handle exception
//        }
//
//        try {
//            String decryptWithSecret4 = decryptWithSecret("lZBy8yLlScJ/rigPIYw53VyBRlKc8tOvXu7ZIDPnZ9Xc6Ptp3MFHbHIK+tA5UIXI74M7AzSmf6JbUp9Aopvbv4qUrpbsttd/m0oTFXaHfcnPF6jho63d5b1+VVHZLBjEv+6RGGJNkJ6SGnVaA3rsrKOAAgNQdEgR", appSecret3);
//            System.out.println(appSecret2);
//            System.out.println(decryptWithSecret4);
//        } catch (Exception e) {
//            // : handle exception
//        }
//


        //秘钥
//        String appsecret = "f3ee6736de58460b808c4104b4df0191";
//        //加密前的信息
//        String msg = "{\"frame\":\"af\",\"owner\":{\"company_id\":\"25296\",\"feed_id\":\"s_5147495485202580\"},\"visitor\":{\"user_id\":\"395140\",\"feed_id\":\"s_5147495485202580\"}}";
//        //用秘钥加密信息
//        String encrypt = encryptWithSecret(msg, appsecret, false);
//        System.out.println("code:" + encrypt);
//        String md5 = MD5Utils.MD5(appsecret);
//        String md5Info = getMd5Info(md5);
//        //用秘钥 md5后提取信息加密信息
//        String encrypt1 = encrypt(msg, md5Info, false);
//
//        String result = "By5mgGlaTueqxhME9jNz7hO757pYAiX9JL8k9Jc+niPYB8ND9E47ez8UyygzbnC4b3WGaYtka26ZEFY2s2XQE9QsHT+Tu+p+PxTLKDNucLg7DWuFEJIlpcAjxTuEK+VeRoDloBvWjY1ioLk08bQ8L6dqUN1WcTrodghdIABI90M0onIHo26bwj8UyygzbnC4kPy1+IoWtnQ=";
//        System.out.println(encrypt.equals(result));
//        System.out.println(encrypt1.equals(result));
//        //解密信息
//        String decrypt = decrypt(result, md5Info);
//        System.out.println(decrypt.equals(msg));
//        String decryptWithSecret = decryptWithSecret(result, appsecret);
//
//        String encoded = "By5mgGlaTueqxhME9jNz7hO757pYAiX9JL8k9Jc%2BniPYB8ND9E47ez8UyygzbnC4b3WGaYtka26ZEFY2s2XQE9QsHT%2BTu%2Bp%2BPxTLKDNucLg7DWuFEJIlpcAjxTuEK%2BVeRoDloBvWjY1ioLk08bQ8L6dqUN1WcTrodghdIABI90M0onIHo26bwj8UyygzbnC4kPy1%2BIoWtnQ%3D";
//        try {
//            String encode = URLEncoder.encode(encrypt,CHARSET);
//            System.out.println(encoded.equals(encode));
//            String decode = URLDecoder.decode(encoded, CHARSET);
//            System.out.println(decode.equals(encrypt));
//            decode = URLDecoder.decode(encoded, CHARSET);
//            System.out.println(decode.equals(encrypt));
//        } catch (UnsupportedEncodingException e) {
//            //  Auto-generated catch block
//            e.printStackTrace();
//        }

        
        
//        appSecrect:  4a15ea2459074068890c6463c5c3a367
//        加密前数据：{"frame":"af","owner":{"company_id":"11111111111111","feed_id":"c_11111111111111"},"visitor":{"user_id":"11111111111111","feed_id":"c_11111111111111"}}
//        加密后数据：By5mgGlaTueqxhME9jNz7hO757pYAiX9JL8k9Jc+niPYB8ND9E47ez8UyygzbnC4b3WGaYtka26ZEFY2s2XQE9QsHT+Tu+p+PxTLKDNucLg7DWuFEJIlpcAjxTuEK+VeRoDloBvWjY1ioLk08bQ8L6dqUN1WcTrodghdIABI90M0onIHo26bwj8UyygzbnC4kPy1+IoWtnQ=
//        加密后encode后数据：By5mgGlaTueqxhME9jNz7hO757pYAiX9JL8k9Jc%2BniPYB8ND9E47ez8UyygzbnC4b3WGaYtka26ZEFY2s2
//        XQE9QsHT%2BTu%2Bp%2BPxTLKDNucLg7DWuFEJIlpcAjxTuEK%2BVeRoDloBvWjY1ioLk08bQ8L6dqUN1WcTrodghdIABI90M0onIHo26bwj8UyygzbnC4kPy1%2BIoWtnQ%3D
    }
    
}
