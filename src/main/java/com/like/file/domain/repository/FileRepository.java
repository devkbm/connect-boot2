package com.like.file.domain.repository;

import java.util.List;

import com.like.file.domain.model.FileInfo;

public interface FileRepository {
	
	FileInfo getFileInfo(String id);
	
	List<FileInfo> getFileInfoList(List<String> id);
	
	List<FileInfo> getFileInfoList();
	
	void delete(String id);
	
	FileInfo save(FileInfo fileInfo);
}
