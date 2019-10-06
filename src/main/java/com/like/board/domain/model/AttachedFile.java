package com.like.board.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude= {"article","fileInfo"})
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"article","fileInfo"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWARTICLEFILE")
public class AttachedFile extends AuditEntity implements Serializable {
		
		
	private static final long serialVersionUID = 1933620773768936638L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ARTICLE_FILE")
	Long pkArticleFile;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PK_ARTICLE", nullable = false)
	Article article; 	
		
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PK_FILE", nullable = false)
	FileInfo fileInfo;

	public AttachedFile(Article article, FileInfo fileInfo) {		
		this.article = article;
		this.fileInfo = fileInfo;
	}
		
}


