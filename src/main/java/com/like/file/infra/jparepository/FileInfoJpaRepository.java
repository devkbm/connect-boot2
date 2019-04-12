package com.like.file.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.file.domain.model.FileInfo;
import com.like.file.domain.repository.FileRepository;
import com.like.file.infra.jparepository.springdata.JpaFileInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class FileInfoJpaRepository implements FileRepository {
	
	@Autowired
	private JPAQueryFactory  queryFactory;

	@Autowired
	private JpaFileInfo jpaFileInfo;
	
	@Override
	public FileInfo getFileInfo(String id) {
		Optional<FileInfo> entity = jpaFileInfo.findById(id);
		
		return entity.isPresent() ? entity.get() : null;
	}
	
	@Override
	public List<FileInfo> getFileInfoList(List<String> id) {
		return jpaFileInfo.findAllById(id);
	}

	@Override
	public List<FileInfo> getFileInfoList() {
		return jpaFileInfo.findAll();
	}

	public void delete(String id) {
		jpaFileInfo.deleteById(id);
	}
	
	public FileInfo save(FileInfo fileInfo) {
		return jpaFileInfo.saveAndFlush(fileInfo);		
	}
	

}
