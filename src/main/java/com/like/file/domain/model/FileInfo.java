package com.like.file.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;
import com.like.file.util.FileIdGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@JsonAutoDetect
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comfileinfo")
@EntityListeners(AuditingEntityListener.class)
public class FileInfo extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = 4108977246168878308L;

	@Id
	@Column(name="pk_file")
	String pkFile;	

	@Column(name="pgm_id")
	String pgmId;
		
	@Column(name="user_id")
	String userId;
	
	@Column(name="content_type")
	String contentType;
		
	@Column(name="uuid")
	String uuid;
	
	@Column(name="file_path")
	String path;
	
	@Column(name="file_nm")
	String fileName;
	
	@Column(name="file_size")
	long size;
	
	@Column(name="download_cnt")
	long downloadCnt;			
		
	@Builder
	public FileInfo(String pgmId, String userId, String contentType, String uuid, String path, String fileName, long size) {		
		this.pkFile = FileIdGenerator.generateFileId();
		this.pgmId = pgmId;
		this.userId = userId;
		this.contentType = contentType;
		this.uuid = uuid;
		this.path = path;
		this.fileName = fileName;
		this.size = size;
	}	
		
	public void plusDownloadCount() {
		this.downloadCnt = this.downloadCnt + 1;
	}

}
