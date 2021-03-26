package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO.SearchAppointmentRegister;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.model.QAppointmentRegister;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentRegisterQueryJpaRepository implements AppointmentRegisterQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public AppointmentRegisterQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<AppointmentRegister> getList(SearchAppointmentRegister searchCondition) {
		return queryFactory
				.selectFrom(QAppointmentRegister.appointmentRegister)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

}
