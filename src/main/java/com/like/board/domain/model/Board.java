package com.like.board.domain.model;

import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.domain.model.vo.Period;
import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

/**
 * <p>게시판 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "GRWBOARD")
@EntityListeners(AuditingEntityListener.class)
public class Board extends AuditEntity {		
	
	/**
	 * 게시판 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOARD")
	Long pkBoard;
	    
	/**
	 * 상위 게시판
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
	@JoinColumn(name="PPK_BOARD", nullable = false)
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
    
	@Embedded
    Period period;
	
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

	/**
	 * 게시글 리스트
	 */
	@Singular(value="articles")
    @OneToMany(mappedBy = "board")          
    List<Article> articles;           
    	
	public void modifyEntity(Board parent
						    ,BoardType boardType
						    ,String boardName
						    ,String boardDescription
						    ,Period period
						    ,Boolean useYn
						    ,long sequence) {
		this.parent = parent;
		this.boardType = boardType;
		this.boardName = boardName;
		this.boardDescription = boardDescription;
		this.period = period;
		this.useYn = useYn;
		this.sequence = sequence;		
	}
	
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