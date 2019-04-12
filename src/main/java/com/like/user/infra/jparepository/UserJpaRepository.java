package com.like.user.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.AuthorityDTO;
import com.like.user.dto.UserDTO;
import com.like.user.infra.jparepository.springdata.JpaAuthority;
import com.like.user.infra.jparepository.springdata.JpaUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserJpaRepository implements UserRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaUser jpaUser;
	
	@Autowired
	private JpaAuthority jpaAuthority;
		
	private final QUser qUser = QUser.user;
	private final QAuthority qAuthority = QAuthority.authority;	
	
	@Override
	public User getUser(String userId) throws UsernameNotFoundException {
		Optional<User> entity = jpaUser.findById(userId);
		
		return entity.isPresent() ? entity.get() : null;
	}
	
	@Override
	public List<User> getUserList(UserDTO.QueryCondition condition) {
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
		Optional<User> user = jpaUser.findById(userId);
		
		user.get().addAuthoritiy(authority);
	}

	@Override
	public List<Authority> readAuthority(String userId) {		
		Optional<User> entity = jpaUser.findById(userId); 
		return entity.get().getAuthorityList();
	}

	@Override
	public List<Authority> getAuthorityList(AuthorityDTO.QueryCondition condition) {
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
		Optional<Authority> entity = jpaAuthority.findById(authorityName);
		return entity.isPresent() ? entity.get() : null;
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
