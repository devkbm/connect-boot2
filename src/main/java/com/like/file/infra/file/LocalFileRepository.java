package com.like.file.infra.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class LocalFileRepository {

	@Value("${localFilePath}")
	private String localUploadPath;
	
	@Value("${staticUploadPath}")
	private String staticUploadPath;
		
	public static String fileDownLoadUrl;
	
	private int BUFFER_SIZE = 4096;
	
	public LocalFileRepository() {		
	}
	
	public enum FileUploadLocation {
		STATIC_PATH, LOCAL_PATH
	}
	
	public String getPath() {	
		return this.localUploadPath;
	}
	
	public String getStaticUploadPath() {
		return this.staticUploadPath;
	}
	
	@Value("${fileDownLoadUrl}")
	private void setFileDownLoadUrl(String url) {
		this.fileDownLoadUrl = url;
	}	
	
	public File getStaticPathFile(String uuid) {
		return new File(this.staticUploadPath, uuid);
	}
	
	public String fileTransfer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		String path = null;
		File file = null;
		
		if(sourceFile == null || sourceFile.isEmpty()){			
			throw new FileNotFoundException();
		}
		
		if ( location == FileUploadLocation.LOCAL_PATH) {
			path = this.getPath();
		} else {
			path = this.getStaticUploadPath();
		}
		
		file = new File(path, fileName);
								
		streamToFile(sourceFile.getInputStream(), file);	
		
		return file.getPath();
	}
	
	public void streamToFile(InputStream is, File file) throws FileNotFoundException, IOException  {
		try (ReadableByteChannel	cin = Channels.newChannel(is);	
			 FileOutputStream 		fos = new FileOutputStream(file);
			 FileChannel 			cout = fos.getChannel();) {			
						
			 cout.transferFrom(cin, 0, is.available());						 				
		}		
	}
	
	public void fileToStream(File file, OutputStream os) throws FileNotFoundException, IOException {
			
		try (
			FileInputStream fis = new FileInputStream(file);
			FileChannel inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(os);) {										
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}		
	}
	
	
	/**
	 * 파일을 삭제한다.
	 * @param file 
	 * @return
	 * @throws FileNotFoundException
	 */
	public Boolean deleteFile(File file) throws FileNotFoundException {
		Boolean result = false;
		
		if(file == null || !file.exists()) {			
			throw new FileNotFoundException();
		}
		
		result = file.delete();
				
		return result;		
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param path 파일 경로
	 * @param name 파일명
	 * @return 삭제 여부
	 * @throws FileNotFoundException 
	 */
	public Boolean deleteFile(String path, String name) throws FileNotFoundException {		
		return deleteFile(new File(path, name));
	}
	
	public String fileToBase64String(String path, String fileName) throws FileNotFoundException, IOException {
		
		byte[] buffer;
		byte[] byteArray;
		int bytesRead = -1;
		
		try (InputStream is = new FileInputStream(new File(path, fileName));
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
						
			buffer = new byte[this.BUFFER_SIZE];		
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			byteArray = bos.toByteArray();					
		} 
		
		return Base64.getEncoder().encodeToString(byteArray);		
	}	
	
	public static String getBase64String(String path, String fileName) throws FileNotFoundException, IOException {
				
		byte[] byteArray = getByteArray(new File(path, fileName));		
		
		return Base64.getEncoder().encodeToString(byteArray);		
	}	
	
	private static byte[] getByteArray(File file) throws FileNotFoundException, IOException {
		
		byte[] buffer;
		byte[] byteArray;
		int bytesRead 	= -1;
		int bufferSize 	= 4096; 
				
		try (InputStream is = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
							
			buffer = new byte[bufferSize];		
			
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			
			byteArray = bos.toByteArray();					
		} 
		
		return byteArray;
	}
	
	
}
