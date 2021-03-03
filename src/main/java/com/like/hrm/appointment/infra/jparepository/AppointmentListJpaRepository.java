package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.boundary.AppointmentListDTO.SearchAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.model.QAppointmentList;
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
	public AppointmentList get(Long id) {
		return jpaAppointmentList.findById(id).orElse(null);
	}

	@Override
	public void save(AppointmentList entity) {
		jpaAppointmentList.save(entity);		
	}

	@Override
	public void delete(AppointmentList entity) {
		jpaAppointmentList.delete(entity);		
	}

	@Override
	public List<AppointmentList> getList(SearchAppointmentList searchCondition) {
		return queryFactory
				.selectFrom(QAppointmentList.appointmentList)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<QueryAppointmentList> getListDTO(AppointmentListDTO.SearchAppointmentList searchCondition) {
		/*
		return queryFactory
				.select(new QLedgerDTO_QueryLedgerList(QAppointmentList.appointmentList.ledger.ledgerId
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
				.fetch();*/
		return null;
	}

}
