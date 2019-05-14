package com.like.file.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.file.domain.model.FileInfo;
import com.like.file.infra.file.LocalFileRepository;
import com.like.file.infra.file.LocalFileRepository.FileUploadLocation;
import com.like.file.infra.jparepository.FileInfoJpaRepository;

@Service("fileService")
public class FileService {
		
	@Resource(name="fileInfoJpaRepository")
	private FileInfoJpaRepository fileInfoRepository;	
	
	@Resource(name="localFileRepository")
	private LocalFileRepository localFileRepository;	
	
	@Transactional
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) throws FileNotFoundException, IOException {
									
		String uuid = UUID.randomUUID().toString();
		
		fileTransefer(sourceFile, uuid, FileUploadLocation.LOCAL_PATH);
		
		FileInfo file = createFileInfo(sourceFile, uuid, userId, pgmId);		
												
		return fileInfoRepository.save(file);		
	}
	
	@Transactional
	public List<FileInfo> uploadFile(List<MultipartFile> sourceFiles, String userId, String pgmId) throws FileNotFoundException, IOException {
		
		List<FileInfo> rtn = new ArrayList<FileInfo>();
		
		for (MultipartFile multipartFile : sourceFiles) {			
										
			String uuid = UUID.randomUUID().toString();
			
			fileTransefer(multipartFile, uuid, FileUploadLocation.LOCAL_PATH);
			
			FileInfo file = createFileInfo(multipartFile, uuid, userId, pgmId);	
			
			rtn.add(fileInfoRepository.save(file));
		}
												
		return rtn; 		
	}
		
		
	public FileInfo downloadFile(HttpServletResponse response, String pk) throws FileNotFoundException, IOException {
		
		FileInfo file = getFileInfo(pk);
		
		localFileRepository.fileToStream(new File(file.getPath(), file.getUuid()), response.getOutputStream());
		
		return file;
	}
	
	@Transactional	
	public void downloadFile(FileInfo fileInfo, OutputStream os) throws FileNotFoundException, IOException {		
		File file = new File(fileInfo.getPath(), fileInfo.getUuid());
		
		localFileRepository.fileToStream(file, os);
		
		// 다운로드 카운트 + 1
		fileInfo.plusDownloadCount();
		
		fileInfoRepository.save(fileInfo);
	}		
	
	@Transactional
	public void deleteFile(FileInfo fileInfo) throws Exception {
		
		localFileRepository.deleteFile(fileInfo.getPath(), fileInfo.getUuid());
		
		fileInfoRepository.delete(fileInfo.getPkFile());											
	}
	
	public FileInfo getFileInfo(String id) {
		return fileInfoRepository.getFileInfo(id);
	}
	
	public List<FileInfo> getFileInfoList(List<String> id) {		
		return fileInfoRepository.getFileInfoList(id);
	}
	
	public String fileTransefer(MultipartFile sourceFile, String fileName, FileUploadLocation location) throws FileNotFoundException, IOException {
		return localFileRepository.fileTransfer(sourceFile, fileName, location);
	}
	
	public String downloadBase64(String id) throws FileNotFoundException, IOException {
		FileInfo info = fileInfoRepository.getFileInfo(id);
					
		return localFileRepository.fileToBase64String(info.getPath(), info.getUuid());		
	}
	
	private FileInfo createFileInfo(MultipartFile sourceFile, String uuid, String userId, String pgmId) {
		
		return FileInfo.builder()
					   .uuid(uuid)
				       .path(localFileRepository.getPath())
				       .fileName(sourceFile.getOriginalFilename())
				       .size(sourceFile.getSize())
				       .contentType(sourceFile.getContentType())
				       .userId(userId)
				       .pgmId(pgmId)
				       .build();
	}
	
}
