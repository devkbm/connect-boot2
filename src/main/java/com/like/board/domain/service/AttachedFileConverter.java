package com.like.board.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.AttachedFile;
import com.like.file.domain.model.FileInfo;

public class AttachedFileConverter {

	public static List<AttachedFile> convert(Article article, List<FileInfo> fileInfoList) {
		
		return fileInfoList.stream()
				.map( v -> new AttachedFile(article, v) )
				.collect(Collectors.toList());
	}
}
