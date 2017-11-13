package com.howie.transitionService;

import java.io.IOException;

import com.howie.basicService.OSSDfsService;
import com.howie.util.CommonConstants;
import com.howie.util.FileUtils;
import com.howie.util.SystemConfig;
import com.howie.util.UUIDUtils;

public class UploadService {
	
	private OSSDfsService dfsService;
	//private String userFilePath = "${img.context}test/";
	//private String userFilePath = "${img.context}hospital/yueyang/";
	String userFilePath;
	
	//构造器
	public UploadService(){
		try {
			userFilePath = SystemConfig.getConfigResource("userFilePath");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public Image uploadPhoto(byte[] bytes) throws Exception {
		dfsService = new OSSDfsService();
		byte[] thumbnailByte = null;
		try {
			thumbnailByte = ImageUtil.resize(bytes, CommonConstants.FILE_WIDTH,
					CommonConstants.FILE_HEIGHT);
		} catch (Exception e) {
			throw new Exception(e);
		}
		String filename = userFilePath + UUIDUtils.getUUID();
		System.out.println(filename);
		//这里上传了2次
		dfsService.upload(filename + CommonConstants.FILE_POSTFIX_NAME, bytes);
		dfsService.upload(filename + CommonConstants.FILE_POSTFIX,thumbnailByte);
		
 		 * 文件下载的链接和文件上传返回的数字签名没有关系
		 * downLoadUrl + / filename(filename+UUID).jpg 
		 * downLoadUrl + / filename.160X160.jpg
		 
		return new Image(this.dfsService.getDownloadUrl()
				+ CommonConstants.SINGLE_SLASH + filename
				+ CommonConstants.FILE_POSTFIX_NAME,
				this.dfsService.getDownloadUrl() + CommonConstants.SINGLE_SLASH
						+ filename + CommonConstants.FILE_POSTFIX);
	}*/

	/*public String updateUserHeadPhoto(byte[] bytes)
			throws Exception {
		String head_url = uploadPhoto(bytes).getImageUrl().split(
				CommonConstants.DOUBLE_SLASH)[0]
				+ CommonConstants.DOUBLE_SLASH
				+ CommonConstants.TWSZ_ABB
				+ uploadPhoto(bytes).getImageUrl().split(
						CommonConstants.DOUBLE_SLASH)[1];
		return head_url;
	}*/

	//预留挖坑，直接上传文件的函数，contentType可传可不传
	//这里上传的第二个参数默认为空，触发链接直接下载
	public String uploadFile(byte[] bytes,String contentType,String fileType){
		dfsService = dfsService==null?new OSSDfsService():dfsService;
		String fileName = userFilePath + UUIDUtils.getUUID()+CommonConstants.SINGLE_DOT+fileType;
		//contentType = fileType;
		dfsService.upload(fileName, bytes, contentType);
		String tempUrl = this.dfsService.getDownloadUrl()+CommonConstants.SINGLE_SLASH
				+fileName;
		//返回文件的下载链接,在原有的tempUrl链接里面加入twsz的字段
		String url =  tempUrl.split(CommonConstants.DOUBLE_SLASH)[0]
				+ CommonConstants.DOUBLE_SLASH + CommonConstants.TWSZ_ABB
				+ tempUrl.split(CommonConstants.DOUBLE_SLASH)[1];
		System.out.println(url);
		 return url;
	}
	
	//重写uploadFile方法，contentType通过fileName控制
	public String uploadFile(byte[] bytes, String fileName){
		String fileType = fileName.split("\\.")[1];		//获取文件类型
		//通过fileName去获取对应的contentType类型
		String contentType = FileUtils.contentType(fileType);
		if (contentType != null){
			return uploadFile(bytes,contentType,fileType);
		}else{
			return "非规定类型数据";
		}
	}

	public String getUserFilePath() {
		return userFilePath;
	}


	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}
	
}
