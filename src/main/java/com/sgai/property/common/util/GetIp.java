package com.sgai.property.common.util;

import com.sgai.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 袁鹏涛
 * @Date: 2018/6/21 13:57
 * @Description:为指定时间增加规定时间
 */
public class GetIp {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getIpAddrs(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }




    public static String formatTimeEight(String time) throws Exception {
        Date d = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d = sd.parse(time);
        long rightTime = d.getTime() + 8 * 60 * 60 * 1000; //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
        String newtime = sd.format(rightTime);//把得到的新的时间戳再次格式化成时间的格式
        return newtime;
    }




/*
    public static final String getIpAddress(HttpServletRequest request) throws Exception {
        if (request == null) {
            throw new Exception("getIpAddr method HttpServletRequest Object is null");
        } else {
            String ipString = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("WL-Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getRemoteAddr();
            }

            String[] arr = ipString.split(",");
            String[] var3 = arr;
            int var4 = arr.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String str = var3[var5];
                if (!"unknown".equalsIgnoreCase(str)) {
                    ipString = str;
                    break;
                }
            }

            return ipString.contains(":") ? InetAddress.getLocalHost().getHostAddress() : ipString;
        }
    }*/

    public static final String getIpAddress(HttpServletRequest request) throws Exception {
        if (request == null) {
            throw new Exception("getIpAddr method HttpServletRequest Object is null");
        } else {
            String ipString = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getHeader("WL-Proxy-Client-IP");
            }

            if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
                ipString = request.getRemoteAddr();
            }

            String[] arr = ipString.split(",");
            String[] var3 = arr;
            int var4 = arr.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String str = var3[var5];
                if (!"unknown".equalsIgnoreCase(str)) {
                    ipString = str;
                    break;
                }
            }

            return ipString.contains(":") ? InetAddress.getLocalHost().getHostAddress() : ipString;
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("本机的外网IP是："+GetIp.getWebIP("http://220.194.33.252:9090/sys/sysIndex.html"));
        System.out.println("本机的内网IP是："+GetIp.getLocalIP());
    }

    /**
     * 获取外网地址
     * @param strUrl
     * @return
     */
    public static String getWebIP(String strUrl) {
        try {
//连接网页
            URL url = new URL(strUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer();
            String webContent = "";
//读取网页信息
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            br.close();
//网页信息
            webContent = sb.toString();
            int start = webContent.indexOf("[")+1;
            int end = webContent.indexOf("]");
//获取网页中 当前 的 外网IP
            webContent = webContent.substring(start,end);
            return webContent;


        } catch (Exception e) {
            e.printStackTrace();
            return "error open url:" + strUrl;
        }
    }

    public static String getLocalIP() throws Exception{
        String localIP = "";
        InetAddress addr = InetAddress.getLocalHost();
//获取本机IP
        localIP = addr.getHostAddress();
        return localIP;
    }


    public static final String getIpAddr(final HttpServletRequest request)
            throws Exception {
        if (request == null) {
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
        }
        String ipString = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
            ipString = request.getRemoteAddr();
        }
        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipString.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipString = str;
                break;
            }
        }

        return ipString;
    }
}