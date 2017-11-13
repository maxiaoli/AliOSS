package com.howie.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import com.howie.transitionService.UploadService;
import com.howie.util.FileUtils;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			UploadPhoto uploadPhoto = new UploadPhoto();
			//现在测试的是直接上传其他文件，现在得到的URL有一点问题
			String path = "F:/pic/5.jpg";
			String url = null;
			url = uploadPhoto.uploadPhotoByPath(path);
			System.out.println(url);
		}*/
		try{
			UploadService uploadService = new UploadService();	
			String path = "F:/pic/s.png";
			String url = null;
			String fileType = FileUtils.getFileTypeByPath(path);
			File file = new File(path);
			String contentType = FileUtils.getFileContentType(file);
			byte[] bytes = null;
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while((n = fis.read(b))!=-1){
				bos.write(b,0,n);
			}
			bytes = bos.toByteArray();
			//url = uploadService.uploadFile(bytes, contentType, fileType);
			url = uploadService.uploadFile(bytes, path);
			System.out.println(url);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv() return code = -2 JDWP exit error AGENT_
		 * 原因是以前执行的线程没有执行完遗留了下来,导致控制台被占用,当前线程堵塞
		 **/
		System.exit(0);
	}

}
