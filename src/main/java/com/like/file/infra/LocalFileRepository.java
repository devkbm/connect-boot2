package com.like.file.infra;

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
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class LocalFileRepository {

	@Value("${localFilePath}")
	private String path;
	
	private int BUFFER_SIZE = 4096;
	
	public LocalFileRepository() {		
	}
	
	public String getPath() {
		//return "C:\\temp";		
		return this.path;
	}
	
	public void fileTransfer(MultipartFile sourceFile, String path, String fileName) throws Exception {
						
		if(sourceFile == null || sourceFile.isEmpty()){			
			throw new FileNotFoundException();
		}
					
		try (InputStream is = sourceFile.getInputStream();
			 ReadableByteChannel  cin = Channels.newChannel(is);	
			 FileOutputStream fos = new FileOutputStream(new File(path, fileName));
			 FileChannel cout = fos.getChannel();) {			
					
			 cout.transferFrom(cin, 0, is.available());						 				
		}							
	}
	
	public void fileToStream(File file, OutputStream os) throws Exception {
			
		try (
			FileInputStream fis = new FileInputStream(file);
			FileChannel inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(os);) {										
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}		
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param path 파일 경로
	 * @param name 파일명
	 * @return 삭제 여부
	 * @throws Exception
	 */
	public Boolean deleteFile(String path, String name) throws Exception {
		File file = new File(path, name);
		Boolean result = false;
		if(file.isFile()){
			result = file.delete();
		}
		return result;
	}
	
	public String fileToBase64String(String path, String fileName) throws Exception {
		
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
		int bytesRead = -1;
				
		try (InputStream is = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
							
			buffer = new byte[4906];		
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			byteArray = bos.toByteArray();					
		} 
		
		return byteArray;
	}
	
	
}
