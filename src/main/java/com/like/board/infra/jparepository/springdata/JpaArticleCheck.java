package com.like.board.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.ArticleCheck;

@Repository
public interface JpaArticleCheck extends JpaRepository<ArticleCheck, Long> {

	//@Query(value = "select * from grbdartc as c where c.fk_article = :fkarticle and c.sys_user = :sysuser",nativeQuery=true)	
	//ArticleCheck findByFkarticleAndSysuser(@Param("fkarticle") Long fkarticle, @Param("sysuser") String userId);
}
