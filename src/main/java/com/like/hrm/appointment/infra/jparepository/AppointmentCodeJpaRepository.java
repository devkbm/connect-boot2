package com.like.hrm.appointment.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCode;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentCodeJpaRepository implements AppointmentCodeRepository {

	private JPAQueryFactory	queryFactory;
	
	private JpaAppointmentCode jpaAppointmentCode;
	
	public AppointmentCodeJpaRepository(JPAQueryFactory	queryFactory,
									    JpaAppointmentCode jpaAppointmentCode) {
		this.queryFactory = queryFactory;
		this.jpaAppointmentCode = jpaAppointmentCode;		
	}

	@Override
	public boolean isAppointmentCode(String codeId) {
		return jpaAppointmentCode.existsById(codeId);
	}

	@Override
	public AppointmentCode getAppointmentCode(String codeId) {		
		return jpaAppointmentCode.findById(codeId).orElse(null);
	}

	@Override
	public List<AppointmentCode> getAppointmentCodeList(SearchCode dto) {
		return queryFactory.selectFrom(QAppointmentCode.appointmentCode)
						   .where(dto.getBooleanBuilder())						   
						   .fetch();
	}

	@Override
	public void saveAppintmentCode(AppointmentCode appointmentCode) {
		jpaAppointmentCode.save(appointmentCode);		
	}

	@Override
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		jpaAppointmentCode.delete(appointmentCode);		
	}

	@Override
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return queryFactory
				.selectFrom(QAppointmentCodeDetail.appointmentCodeDetail)
				.where(dto.getBooleanBuilder())
				.fetch();
	}
	
	
}
