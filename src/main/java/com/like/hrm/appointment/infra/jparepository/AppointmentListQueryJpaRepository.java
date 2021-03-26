package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentListDTO.SearchAppointmentList;
import com.like.hrm.appointment.boundary.QQueryAppointmentList;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentList;
import com.like.hrm.appointment.domain.repository.AppointmentListQueryRepository;
import com.like.hrm.employee.domain.model.QEmployee;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentListQueryJpaRepository implements AppointmentListQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public AppointmentListQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<AppointmentList> getList(SearchAppointmentList searchCondition) {
		return queryFactory
				.selectFrom(QAppointmentList.appointmentList)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<QueryAppointmentList> getListDTO(SearchAppointmentList searchCondition) {
		return queryFactory
				.select(new QQueryAppointmentList(QAppointmentList.appointmentList.ledger.id
							                     ,QAppointmentList.appointmentList.listId
							                     ,QAppointmentList.appointmentList.sequence
							                     ,QAppointmentList.appointmentList.empId
							                     ,QEmployee.employee.name
							                     ,QAppointmentList.appointmentList.appointmentCode
							                     ,QAppointmentCode.appointmentCode.codeName
							                     ,QAppointmentList.appointmentList.appointmentFromDate
							                     ,QAppointmentList.appointmentList.appointmentToDate
							                     ,QAppointmentList.appointmentList.finishYn)
						)
				.from(QAppointmentList.appointmentList)
				.join(QEmployee.employee)
				  .on(QAppointmentList.appointmentList.empId.eq(QEmployee.employee.id))
				.join(QAppointmentCode.appointmentCode)
				  .on(QAppointmentList.appointmentList.appointmentCode.eq(QAppointmentCode.appointmentCode.code))
				.where(searchCondition.getBooleanBuilder())
				.fetch();	
	}

}
