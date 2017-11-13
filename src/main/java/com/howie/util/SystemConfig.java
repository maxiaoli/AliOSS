package com.howie.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取后缀名为properties的文件
 * @author hongyang.jiang
 * 
 * */
public class SystemConfig {
	//***反斜杠表示根目录，绝对路径 
	private static final String CONFIG_PROPERTIES = "/dfs.properties";	
	
	//相对路径,将jar包和配置文件放在同一文件夹下面
	//private static final String CONFIG_PROPERTIES = System.getProperty("user.dir") +  "\\dfs.properties";
	
	public static String getConfigResource(String key) throws IOException{
		Properties properties = new Properties();
		/*ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream in = PropertiesUtils.class.getResourceAsStream(CONFIG_PROPERTIES);
		FileInputStream in = new FileInputStream(CONFIG_PROPERTIES);
		FileInputStream in = this.getClass().getResourcesAsStream(CONFIG_PROPERTIES);*/
		// 方法3：	
		/*BufferedReader br = new BufferedReader(
				new InputStreamReader(ClassLoader.getSystemResourceAsStream(CONFIG_PROPERTIES)));*/
		//方法2：读取jar包内文件
		InputStream in = SystemConfig.class.getResourceAsStream(CONFIG_PROPERTIES);
		/*//方法1：
		InputStream is = this.getClass().getResourceAsStream(CONFIG_PROPERTIES);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));*/
		properties.load(in);
		String value = properties.getProperty(key);
		//编码转换
		value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		in.close();
		return value;
	}
	
	public static int getConfigResourceInt(String key) throws IOException{
		
		Properties properties = new Properties();
		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//InputStream in = loader.getResourceAsStream(CONFIG_PROPERTIES);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(ClassLoader.getSystemResourceAsStream(CONFIG_PROPERTIES)));
		properties.load(br);
		String value = properties.getProperty(key);
		//编码转换
		value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		br.close();
		return Integer.parseInt(value);
	}
	
}
