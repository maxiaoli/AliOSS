package com.howie.basicService;

import java.io.IOException;

import com.howie.util.SystemConfig;

public class OSSConfig {

	private String ossEndpoint;			//访问的阿里云路劲
	private String ossAccessId;			//阿里云OSS使用的AccessId
	private String ossAccessKey;		//阿里云OSS使用的OSSAccessKey
	private String defaultBucket;		//bucket名称
	private int ossMaxConnection;		//允许打开的最大HTTP连接数。默认为50
	private int ossMaxErrorRetry;		//可重试的请求失败后最大的重试次数。默认为3次
	private int ossConnectionTimeOut;	//建立连接的超时时间（单位：毫秒）。默认为5000毫秒
	private int ossSocketTimeOut;		//通过打开的连接传输数据的超时时间（单位：毫秒）。默认为50000毫秒
	
	
	public OSSConfig(){
		try{
			this.ossEndpoint = SystemConfig.getConfigResource("oss.endpoint");
			this.ossAccessId = SystemConfig.getConfigResource("oss.access.id");
			this.ossAccessKey = SystemConfig.getConfigResource("oss.access.key");
			this.defaultBucket = SystemConfig.getConfigResource("oss.default.bucket");
			/*this.ossMaxConnection = SystemConfig.getConfigResourceInt("oss.max.connection");
			this.ossMaxErrorRetry = SystemConfig.getConfigResourceInt("oss.max.error.retry");
			this.ossConnectionTimeOut = SystemConfig.getConfigResourceInt("oss.connection.timeout");
			this.ossSocketTimeOut = SystemConfig.getConfigResourceInt("oss.socket.timeout");*/
			//由于是固定参数，写死好了
			this.ossMaxConnection = 50;
			this.ossMaxErrorRetry = 3;
			this.ossConnectionTimeOut = 60000;
			this.ossSocketTimeOut = 5000;
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the ossEndpoint
	 */
	public String getOssEndpoint() {
		return ossEndpoint;
	}


	/**
	 * @param ossEndpoint the ossEndpoint to set
	 */
	public void setOssEndpoint(String ossEndpoint) {
		this.ossEndpoint = ossEndpoint;
	}


	/**
	 * @return the ossAccessId
	 */
	public String getOssAccessId() {
		return ossAccessId;
	}


	/**
	 * @param ossAccessId the ossAccessId to set
	 */
	public void setOssAccessId(String ossAccessId) {
		this.ossAccessId = ossAccessId;
	}


	/**
	 * @return the ossAccessKey
	 */
	public String getOssAccessKey() {
		return ossAccessKey;
	}


	/**
	 * @param ossAccessKey the ossAccessKey to set
	 */
	public void setOssAccessKey(String ossAccessKey) {
		this.ossAccessKey = ossAccessKey;
	}


	/**
	 * @return the defaultBucket
	 */
	public String getDefaultBucket() {
		return defaultBucket;
	}


	/**
	 * @param defaultBucket the defaultBucket to set
	 */
	public void setDefaultBucket(String defaultBucket) {
		this.defaultBucket = defaultBucket;
	}


	/**
	 * @return the ossMaxConnection
	 */
	public int getOssMaxConnection() {
		return ossMaxConnection;
	}


	/**
	 * @param ossMaxConnection the ossMaxConnection to set
	 */
	public void setOssMaxConnection(int ossMaxConnection) {
		this.ossMaxConnection = ossMaxConnection;
	}


	/**
	 * @return the ossMaxErrorRetry
	 */
	public int getOssMaxErrorRetry() {
		return ossMaxErrorRetry;
	}


	/**
	 * @param ossMaxErrorRetry the ossMaxErrorRetry to set
	 */
	public void setOssMaxErrorRetry(int ossMaxErrorRetry) {
		this.ossMaxErrorRetry = ossMaxErrorRetry;
	}


	/**
	 * @return the ossConnectionTimeOut
	 */
	public int getOssConnectionTimeOut() {
		return ossConnectionTimeOut;
	}


	/**
	 * @param ossConnectionTimeOut the ossConnectionTimeOut to set
	 */
	public void setOssConnectionTimeOut(int ossConnectionTimeOut) {
		this.ossConnectionTimeOut = ossConnectionTimeOut;
	}


	/**
	 * @return the ossSocketTimeOut
	 */
	public int getOssSocketTimeOut() {
		return ossSocketTimeOut;
	}


	/**
	 * @param ossSocketTimeOut the ossSocketTimeOut to set
	 */
	public void setOssSocketTimeOut(int ossSocketTimeOut) {
		this.ossSocketTimeOut = ossSocketTimeOut;
	}
	
	
	
}
