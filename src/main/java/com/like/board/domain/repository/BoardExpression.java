package com.like.board.domain.repository;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.QBoard;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class BoardExpression {

	@QueryDelegate(Board.class)
	public static BooleanExpression isRootNode(QBoard board) {
		return board.parent.isNull();
	}
}
