package com.like.user.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.like.user.boundary.AuthorityDTO;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.infra.jparepository.springdata.JpaAuthority;
import com.like.user.infra.jparepository.springdata.JpaUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserJpaRepository implements UserRepository {

	private final QUser qUser = QUser.user;
	private final QAuthority qAuthority = QAuthority.authority;
	
	private JPAQueryFactory  queryFactory;	
	private JpaUser jpaUser;		
	private JpaAuthority jpaAuthority;
	
	public UserJpaRepository(JPAQueryFactory  queryFactory
							,JpaUser jpaUser
							,JpaAuthority jpaAuthority) {
		this.queryFactory = queryFactory;
		this.jpaUser = jpaUser;
		this.jpaAuthority = jpaAuthority;
	}
		
	
	@Override
	public User getUser(String userId) throws UsernameNotFoundException {				
		return jpaUser.findById(userId).orElse(null);
	}
	
	@Override
	public User getFullUser(String userId) {
		User user = queryFactory.select(qUser)
								.from(qUser)
								.where(qUser.userId.eq(userId))
								.fetchOne();
		user.getMenuGroupList();
		
		return user;
	}
	
	@Override
	public List<User> getUserList(List<String> userIds) {
		return jpaUser.findAllById(userIds);
	}
	
	@Override
	public List<User> getUserList(UserDTO.SearchUser condition) {
		return  queryFactory
				.selectFrom(qUser)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public void saveUser(User user) {
		jpaUser.save(user);
	}
	
	@Override
	public void deleteUser(String userId) {
		jpaUser.deleteById(userId);		
	}
		
	@Override
	public void addUserAuthority(String userId, Authority authority) { 
		jpaUser.findById(userId).ifPresent( user -> user.addAuthoritiy(authority) );			
	}

	@Override
	public List<Authority> readAuthority(String userId) {		
		Optional<User> entity = jpaUser.findById(userId); 
		return entity.get().getAuthorityList();
	}

	@Override
	public List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition) {
		return queryFactory
				.selectFrom(qAuthority)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
			
	@Override
	public List<Authority> getAuthorityList(List<String> authorityNameList) {
		
		return jpaAuthority.findAllById(authorityNameList);
	}

	@Override
	public Authority getAuthority(String authorityName) {		
		return jpaAuthority.findById(authorityName).orElse(null);
	}

	@Override
	public void createAuthority(Authority authority) {
		jpaAuthority.save(authority);
	}

	@Override
	public void deleteAuthority(String authority) {
		jpaAuthority.deleteById(authority);		
	}
	
}
