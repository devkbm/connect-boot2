package com.like.file.infra.jparepository.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.file.domain.model.FileInfo;

@Repository
public interface JpaFileInfo extends JpaRepository<FileInfo, String> {
	List<FileInfo> findByPgmId(String pgmId);
}
