package com.like.hrm.appointment.infra.jparepository;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentListRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentList;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentListJpaRepository implements AppointmentListRepository{

	private JPAQueryFactory	queryFactory;
	
	private JpaAppointmentList jpaAppointmentList; 
	
	public AppointmentListJpaRepository(JPAQueryFactory	queryFactory
									   ,JpaAppointmentList jpaAppointmentList) {
		this.queryFactory = queryFactory;
		this.jpaAppointmentList = jpaAppointmentList;
	}
	
	@Override
	public LedgerList get(Long id) {
		return jpaAppointmentList.findById(id).orElse(null);
	}

	@Override
	public void save(LedgerList entity) {
		jpaAppointmentList.save(entity);		
	}

	@Override
	public void delete(LedgerList entity) {
		jpaAppointmentList.delete(entity);		
	}

}
