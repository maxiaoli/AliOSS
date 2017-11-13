package com.howie.util;

import java.io.File;


/*
 * 文件工具类
 * 对文件进行辅助的操作
 * */
public class FileUtils {

	//通过文件路径获取文件类型
	public static String getFileTypeByPath(String path){
		//以"."分隔，用"\\."匹配
		return path.split("\\.")[1];
	}
	
	public static String getFileContentType(File file){
		return contentType(file.getName().substring(file.getName().lastIndexOf(".")));
	}
	
	/** 
     * Description: 判断OSS服务文件上传时文件的contentType 
     * @Version1.0 
     * @param FilenameExtension 文件后缀 
     * @return String  
     */  
     public static String contentType(String FilenameExtension){ 
    	 //FilenameExtension = FilenameExtension.split("\\.")[1];
        if(FilenameExtension.equals("BMP")||FilenameExtension.equals("bmp")){
        	return "image/bmp";
        }  
        if(FilenameExtension.equals("GIF")||FilenameExtension.equals("gif")){
        	return "image/gif";
        }  
        if(FilenameExtension.equals("JPEG")||FilenameExtension.equals("jpeg")||  
           FilenameExtension.equals("JPG")||FilenameExtension.equals("jpg")||     
           FilenameExtension.equals("PNG")||FilenameExtension.equals("png")){
        	return "image/jpeg";
        }  
        if(FilenameExtension.equals("HTML")||FilenameExtension.equals("html")){
        	return "text/html";
        }  
        if(FilenameExtension.equals("TXT")||FilenameExtension.equals("txt")){
        	return "text/plain";
        }  
        if(FilenameExtension.equals("VSD")||FilenameExtension.equals("vsd")){
        	return "application/vnd.visio";
        }  
        if(FilenameExtension.equals("PPTX")||FilenameExtension.equals("pptx")||  
            FilenameExtension.equals("PPT")||FilenameExtension.equals("ppt")){
        	return "application/vnd.ms-powerpoint";
        }  
        if(FilenameExtension.equals("DOCX")||FilenameExtension.equals("docx")||  
            FilenameExtension.equals("DOC")||FilenameExtension.equals("doc")){
        	return "application/msword";
        }  
        if(FilenameExtension.equals("XML")||FilenameExtension.equals("xml")){
        	return "text/xml";
        }  
        if(FilenameExtension.equals("PDF")||FilenameExtension.equals("pdf")){
        	return "application/pdf";
        }
        if(FilenameExtension.equals("SWF")||FilenameExtension.equals("swf")){
        	return "application/x-shockwave-flash";
        }
        if(FilenameExtension.equals("RMVB")||FilenameExtension.equals("rmvb")){
        	return "application/vnd-realmedia-vbr";
        }
        if(FilenameExtension.equals("AVI")||FilenameExtension.equals("avi")){
        	return "vedio/avi";
        }
        if(FilenameExtension.equals("MP4")||FilenameExtension.equals("mp4")){
        	return "video/mpeg4";
        }
        //return "text/html";  		//非以上类型文件，默认为text/html类型
        return "text/html";				//对上传的文件类型进行控制，非允许文件类型返回空
     }  
}
