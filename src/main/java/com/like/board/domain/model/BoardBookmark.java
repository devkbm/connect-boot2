package com.like.board.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.domain.AuditEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>게시판 즐겨찾기 클래스</p>
 * 
 * [상세내용] <br>
 *   1. <br>
 * [제약조건] <br>
 *   1. <br>
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "GRWBOARDBOOKMARK")
@EntityListeners(AuditingEntityListener.class)
public class BoardBookmark extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1585368113519480228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOOKMARK")
	Long pkBookmark;
	
	/**
	 * 사용자 ID
	 */
	@Column(name="USER_ID")
	String userId;
	
	/**
	 * 게시판 키
	 */
	@Column(name="FK_BOARD")
	Long fkBoard;
	
	/**
	 * 순번
	 */
	@Column(name="SEQ")
	Long seq;
		
	public BoardBookmark(String userId
						,Long fkBoard
						,Long seq) {
		
		this.userId = userId;
		this.fkBoard = fkBoard;
		this.seq = seq;
	}
	
	public void changeSequence(long seq) {
		this.seq = seq;
	}
}
