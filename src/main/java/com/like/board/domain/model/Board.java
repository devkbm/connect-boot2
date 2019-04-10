package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.like.board.domain.model.enums.BoardType;
import com.like.common.domain.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"articles","parent"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "GRWBOARD")
@EntityListeners(AuditingEntityListener.class)
public class Board extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -9079904581069883219L;			
	
	/**
	 * 게시판 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOARD")
	Long pkBoard;
	    
	
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="PPK_BOARD")
	Board parent;
	
	/**
	 * 게시판_타입
	 */
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="BOARD_TYPE")
    BoardType boardType;
	
	/**
     * 게시판 명
     */	
	@Column(name="BOARD_NAME")
    String boardName;             
    
    /**
     * 게시판_설명
     */
	@Column(name="BOARD_DESC")
	String boardDescription;
    
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
	LocalDate toDate = LocalDate.of(9999, 12, 31);    
    
    /**
     * 사용여부
     */
	@Builder.Default
	@Column(name="USE_YN")
	Boolean useYn = true;
    
    /**
     * 게시글 갯수
     */
	@Builder.Default
	@Column(name="ARTICLE_CNT")
	long articleCount = 0;
    
    /**
     * 출력순서
     */
	@Builder.Default
	@Column(name="SEQ")
	long sequence = 0;	

	@Singular(value="articles")
    @OneToMany(mappedBy = "board")          
    List<Article> articles;           
    
    public boolean hasParentBoard() {    	    		    		
    	return this.parent != null ? true : false;
	}
    
    public void setParentRoot() {
    	
    }
    
    public void setParent(Board board) {
    	this.parent = board;
    }
    
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);		
		
		if (article.board != this) {	// 무한루프에 빠지지 않도록 체크
			article.setBoard(this);
		}
	}
	
}