package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.board.domain.model.enums.PasswordType;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

@Slf4j
@ToString
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"board","articleChecks","files"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GRWARTICLE")
@EntityListeners(AuditingEntityListener.class)
public class Article extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -6554446667428677065L;
	
	/**
	 * 게시글 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ARTICLE")
	Long pkArticle;	
	
	/**
	 * 게시글 상위키
	 */
	@Column(name="PPK_ARTICLE")
	Long ppkArticle;		
		
	
	/**
	 * 제목
	 */	
	@Column(name="TITLE")
	String title;
    
    /**
     * 내용
     */
	@Column(name="CONTENTS")
    String contents;        
    
    /**
     * 조회 수
     */
	@Column(name="HIT_CNT")
    int hitCount;
        
    /**
     * 시작일자
     */
	@Builder.Default
	@Column(name="FROM_DT")
    LocalDate fromDate = LocalDate.now();
    
    /**
     * 종료일자
     */
	@Builder.Default
	@Column(name="TO_DT")
    LocalDate toDate = LocalDate.of(9999,12,31);
    
    /**
     * 출력순서
     */
	@Column(name="SEQ")
    Integer seq;
    
    /**
     * 게층 횟수
     */
	@Column(name="HIER_DEPTH")
    int depth;
			
	@Column(name="PWD_YN")
	Boolean pwdYn;
	
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="HASH_METHOD")
	PasswordType pwdMethod;
	
	/**
     * 비밀번호
     */
	@Column(name="PWD")
    String pwd;
    
	/**
	 * 게시판 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_BOARD", nullable=false, updatable=false)
	Board board;
    
	@Singular(value="articleChecks")
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks;
                          
	@Singular(value="files")
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    List<AttachedFile> files;
			
	@Formula("(SELECT X.USER_NAME FROM COM.COMUSER X WHERE X.USER_ID = sys_user)")
	String userName;
			
	@Transient
	Boolean editable;
	
	public Long getId() {
		return this.pkArticle;
	}
	
	public Board getBoard() {
		return board;
	}	
	
	public void setBoard(Board board) {
		this.board = board;
		
		if (!board.getArticles().contains(this)) {
			board.getArticles().add(this);
		}
	}
	
	public boolean hasParentArticle() {		
		return this.ppkArticle != this.pkArticle ? true : false;
	}
			
	public Integer getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void updateHitCnt() {
		this.hitCount = this.hitCount + 1;	
	}	
		
	public List<FileInfo> getAttachedFileInfoList() {
		return this.files.stream()						 
				  		 .map(v -> v.fileInfo)
				  		 .collect(Collectors.toList());		
				  
	}
	
	public void setFiles(List<AttachedFile> files) {
		this.files = files;
	}
	
	public Boolean getEditable() {
		
		String sessionId = SecurityContextHolder.getContext().getAuthentication().getName();
					
		return sessionId.equals(this.createdBy) ? true : false;
	}
			
}