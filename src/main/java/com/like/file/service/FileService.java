package com.like.file.service;

import java.io.File;
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
import com.like.file.infra.LocalFileRepository;
import com.like.file.infra.jparepository.FileInfoJpaRepository;
import com.like.file.infra.jparepository.springdata.JpaFileInfo;

@Service("fileService")
public class FileService {
		
	@Resource(name="fileInfoJpaRepository")
	private FileInfoJpaRepository fileInfoRepository;	
	
	@Resource(name="localFileRepository")
	private LocalFileRepository localFileRepository;	
	
	@Transactional
	public FileInfo uploadFile(MultipartFile sourceFile, String userId, String pgmId) throws Exception {
																					
		FileInfo file = newFileInfo(sourceFile, userId, pgmId);			
												
		return fileInfoRepository.save(file);		
	}
	
	@Transactional
	public List<FileInfo> uploadFile(List<MultipartFile> sourceFiles, String userId, String pgmId) throws Exception {
		
		List<FileInfo> rtn = new ArrayList<FileInfo>();
		
		for (MultipartFile multipartFile : sourceFiles) {			
																	
			FileInfo file = newFileInfo(multipartFile, userId, pgmId);	
			
			rtn.add(fileInfoRepository.save(file));
		}
												
		return rtn; 		
	}
		
		
	public FileInfo downloadFile(HttpServletResponse response, String pk)
			throws Exception {
		
		FileInfo file = getFileInfo(pk);
		
		localFileRepository.fileToStream(new File(file.getPath(), file.getUuid()), response.getOutputStream());
		
		return file;
	}
	
	@Transactional	
	public void downloadFile(FileInfo fileInfo, OutputStream os) throws Exception {		
		
		localFileRepository.fileToStream(new File(fileInfo.getPath(), fileInfo.getUuid()), os);
		
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
	
	private FileInfo newFileInfo(MultipartFile sourceFile, String userId, String pgmId) throws Exception {
		
		String uuid = UUID.randomUUID().toString();
		
		localFileRepository.fileTransfer(sourceFile, localFileRepository.getPath(), uuid);
																
		FileInfo file = FileInfo.builder()
								.uuid(uuid)
								.path(localFileRepository.getPath())
								.fileName(sourceFile.getOriginalFilename())
								.size(sourceFile.getSize())
								.contentType(sourceFile.getContentType())
								.userId(userId)
								.pgmId(pgmId)
								.build();
		return file;
	}


	public String downloadBase64(String id) throws Exception {
		FileInfo info = fileInfoRepository.getFileInfo(id);
					
		return localFileRepository.fileToBase64String(info.getPath(), info.getUuid());		
	}
	
}
