package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCode;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentCodeQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentCodeQueryJpaRepository implements AppointmentCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static QAppointmentCode qAppointmentCode = QAppointmentCode.appointmentCode;
	private static QAppointmentCodeDetail qAppointmentCodeDetail = QAppointmentCodeDetail.appointmentCodeDetail;
		
	public AppointmentCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}

	@Override
	public List<AppointmentCode> getAppointmentCodeList(SearchCode dto) {
		return queryFactory.selectFrom(qAppointmentCode)
				   .where(dto.getBooleanBuilder())						   
				   .fetch();
	}
	
	@Override
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return queryFactory
				.selectFrom(qAppointmentCodeDetail)
				.where(dto.getBooleanBuilder())
				.fetch();
	}

}
