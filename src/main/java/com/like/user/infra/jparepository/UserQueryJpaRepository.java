package com.like.user.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.user.boundary.UserDTO.SearchUser;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserQueryJpaRepository implements UserQueryRepository {

	private JPAQueryFactory queryFactory;
	private static final QUser qUser = QUser.user;	
	
	public UserQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}

	@Override
	public List<User> getUserList(SearchUser condition) {
		return  queryFactory
				.selectFrom(qUser)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
	
	
}
