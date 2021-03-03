package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO.SearchAppointmentRegister;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.model.QAppointmentRegister;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaLedger;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentJpaRepository implements AppointmentRegisterRepository {
	
	private JPAQueryFactory	queryFactory;	
				
	private JpaLedger jpaLedger;
	
	public AppointmentJpaRepository(JPAQueryFactory	queryFactory
								   ,JpaLedger jpaLedger) {
		this.queryFactory = queryFactory;
		this.jpaLedger = jpaLedger;
	}
	
		
	@Override
	public AppointmentRegister get(String id) {		
		return jpaLedger.findById(id).orElse(null);
	}

	@Override
	public void save(AppointmentRegister ledger) {
		jpaLedger.save(ledger);		
	}

	@Override
	public void delete(AppointmentRegister ledger) {
		jpaLedger.delete(ledger);		
	}	

	@Override
	public List<AppointmentRegister> getList(SearchAppointmentRegister searchCondition) {
		return queryFactory
				.selectFrom(QAppointmentRegister.appointmentRegister)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}	

}
