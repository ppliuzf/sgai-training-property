package com.sgai.property.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * 读取properties文件,以及读取一些固定配置
 */
public class Configuration {

	private Properties propertie;
	private FileInputStream inputFile;
	private FileOutputStream outputFile;

	private static Properties defaultProperties;
	
	static {
		defaultProperties = new Properties();
		defaultProperties.setProperty("log4j.debug", "true"); // 是否是debug模式
		defaultProperties.setProperty("http.useSSL", "true"); // 是否使用ssl
		defaultProperties.setProperty("http.connectTimeout", "20000"); // 连接超时
		defaultProperties.setProperty("http.socketTimeout", "120000");
		defaultProperties.setProperty("http.retryCount", "3"); // 重试次数
		defaultProperties.setProperty("http.autoRedirect", "true");
		defaultProperties.setProperty("http.maxConnection", "200"); // 最大连接数
		defaultProperties.setProperty("http.maxConPerRoute", "20"); // 单路由最大连接数
		defaultProperties.setProperty("http.requestType", "numPost"); // 请求方式// numPost/get
		defaultProperties.setProperty("http.charset", "UTF-8"); // 字符集
		defaultProperties.setProperty("https.certificateFile", "D:/est.cer"); // 证书路径
		defaultProperties.setProperty("https.certificateName", "api.open.systoon.com.cer"); // 证书文件
	}

	/**
	 * 获取默认属性
	 * @param key
	 * @return
	 */
	public static String getDefaultProperty(String key) {
		return defaultProperties.getProperty(key);
	}

	public static void setDefaultProperty(String key, String value) {
		defaultProperties.setProperty(key, value);
	}

	public static boolean getDebug() {
		return Boolean.valueOf(defaultProperties.getProperty("log4j.debug"));
	}

	/**
	 * 初始化Configuration类
	 */
	public Configuration() {
		propertie = new Properties();
	}

	/**
	 * 初始化Configuration类
	 * 
	 * @param filePath 要读取的配置文件的路径+名称
	 */
	public Configuration(String filePath) {
		propertie = new Properties();
		try {
			inputFile = new FileInputStream(filePath);
			propertie.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param key 取得其值的键
	 * @return key的值
	 */
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		} else {
			return "";
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param fileName properties文件的路径+文件名
	 * @param key 取得其值的键
	 * @return key的值
	 */
	public String getValue(String fileName, String key) {
		try {
			PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(fileName);
			String desc = configBundle.getString(key);
			return desc.trim();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 读取全部信息
	 * 
	 * @param fileName properties文件的路径+文件名
	 */
	public void readAllProperties(String fileName) {
		try {
			inputFile = new FileInputStream(fileName);
			propertie.load(inputFile);
			inputFile.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 清除properties文件中所有的key和其值
	 */
	public void clear() {
		propertie.clear();
	}

	/**
	 * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替， 当key不存在时，该key的值是value
	 * 
	 * @param key 要存入的键
	 * @param value 要存入的值
	 */
	public void setValue(String key, String value) {
		propertie.setProperty(key, value);
	}

	/**
	 * 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
	 * 
	 * @param fileName 文件路径+文件名称
	 * @param description 对该文件的描述
	 */
	public void saveFile(String fileName, String description) {
		try {
			outputFile = new FileOutputStream(fileName);
			propertie.store(outputFile, description);
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param fileName properties文件的路径+文件名
	 * @param key 取得其值的键
	 * @return key的值
	 */
	public String getValueFromStream(String fileName, String key) {
		FileInputStream intput = null;
		Properties pro = new Properties();
		try {
			intput = new FileInputStream(fileName);
			pro.load(intput);
		} catch (Exception e) {
			return "";
		} finally {
			try {
				intput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pro.getProperty(key);
	}

	public void saveValueAsStream(String outputFileName, String key,
			String value) {
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(outputFileName);
			Properties pro = new Properties();
			pro.setProperty(key, value);
			pro.store(output, "");

		} catch (Exception e) {
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}