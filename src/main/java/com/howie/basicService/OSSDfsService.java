package com.howie.basicService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;


/**
 * @author hongyang.jiang
 * @date 2017年 9月20日  上午11:37:35
 */
public class OSSDfsService {

	//private static Logger LOGGER = LoggerFactory.getLogger(OSSDfsService.class);
	
	private ClientConfiguration conf = null;
	
	private static OSSConfig ossConfig = null;
	
	//阿里云的一系列配置
	private String ossEndpoint;
	private String ossAccessId;
	private String ossAccessKey;
	private String defaultBucket;

	/*
	 * 构造方法
	 * */
	public OSSDfsService(){
		
		ossConfig = ossConfig==null?new OSSConfig():ossConfig;
        conf = conf == null ? new ClientConfiguration():conf;				//这里初始化出错
        conf.setConnectionTimeout(ossConfig.getOssConnectionTimeOut());
        conf.setMaxConnections(ossConfig.getOssMaxConnection());
        conf.setMaxErrorRetry(ossConfig.getOssMaxErrorRetry());
        conf.setSocketTimeout(ossConfig.getOssSocketTimeOut());
        
        ossEndpoint = ossConfig.getOssEndpoint();
        ossAccessId = ossConfig.getOssAccessId();
        ossAccessKey = ossConfig.getOssAccessKey();
        defaultBucket = ossConfig.getDefaultBucket();
        
	}

    public ObjectMetadata getObjectMetadata(String key) {
        defaultBucket = ossConfig.getDefaultBucket();
        return getOSSClient().getObjectMetadata(defaultBucket, key);
    }

    private OSSClient getOSSClient() {
    	
        ossConfig = ossConfig==null?new OSSConfig():ossConfig;
        //conf = new ClientConfiguration();
        /*conf.setConnectionTimeout(60000);
        conf.setMaxConnections(50);
        conf.setMaxErrorRetry(3);
        conf.setSocketTimeout(5000);*/
        
        return new OSSClient(ossEndpoint, ossAccessId, ossAccessKey, conf);
    }

    private String uploadFile(String bucketName, String key,
            ObjectMetadata objectMeta, InputStream input) {

        PutObjectResult result = getOSSClient().putObject(bucketName, key,
                input, objectMeta);

        System.out.println(result.getETag());
        return result.getETag();
    }

    public String upload(String key, InputStream is) {
        try {
            byte[] bytes = IOUtils.toByteArray(is);

            return this.upload(key, bytes);

        } catch (Exception e) {
            //LOGGER.info(e.getMessage());
            return "";
        }
    }
    //这里的key只是文件名的一部分
    public String upload(String key, byte[] bytes) {

        // 必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        objectMeta.setContentType("image/jpeg");

        return uploadFile(defaultBucket, key, objectMeta, input);
    }
    
    /**
     *1、编程方式上传，需要正确设置content type
     *2、缺省的content type都为application/octst-stream,访问文件时会触发下载
     */
    public String upload(String key, byte[] bytes,String contentType) {

        //必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        if(contentType!=null){
        	objectMeta.setContentType(contentType);
        }

        return uploadFile(defaultBucket, key, objectMeta, input);
    }

    public String upload(String bucketName, String key, String filename)
            throws FileNotFoundException {

        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);

        return uploadFile(bucketName, key, objectMeta, input);
    }

    public void download(String key, String filename) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(defaultBucket, key);
        File file = new File(filename);
        getOSSClient().getObject(getObjectRequest, file);
    }

    public String upload(String key, InputStream is, String contentType) {
        byte[] bytes = new byte[] {};

        try {
            bytes = IOUtils.toByteArray(is);
        } catch (Exception e) {
            //LOGGER.info(e.getMessage());
            return "";
        }

        // 必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        objectMeta.setContentType(contentType);

        return uploadFile(defaultBucket, key, objectMeta, input);
    }

    /**
     * 获取下载url地址      
     * 
     */
    public String getDownloadUrl() {
        return ossEndpoint /*+ "/" + defaultBucket*/;
    }
	
}
