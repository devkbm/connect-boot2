package com.like.hrm.appointment.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.boundary.LedgerDTO.SearchLedger;
import com.like.hrm.appointment.boundary.LedgerDTO.SearchLedgerList;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.QLedger;
import com.like.hrm.appointment.domain.model.QLedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.appointment.domain.repository.AppointmentLedgerRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCode;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaLedger;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentJpaRepository implements AppointmentLedgerRepository, AppointmentCodeRepository {

	@Autowired
	private JPAQueryFactory	queryFactory;	
	
	@Autowired
	private JpaAppointmentCode jpaAppointmentCode;			
	
	@Autowired
	private JpaLedger jpaLedger;
	
	@Override
	public AppointmentCode getAppointmentCode(String codeId) {
		Optional<AppointmentCode> entity = jpaAppointmentCode.findById(codeId);
		
		return entity.orElse(null);
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
	public Ledger getLedger(String id) {
		Optional<Ledger> entity = jpaLedger.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveLedger(Ledger ledger) {
		jpaLedger.save(ledger);		
	}

	@Override
	public void deleteLedger(Ledger ledger) {
		jpaLedger.delete(ledger);		
	}
	
	@Override
	public List<AppointmentCode> getAppointmentCodeList(AppointmentCodeDTO.SearchCode dto) {
		return queryFactory.selectFrom(QAppointmentCode.appointmentCode)
						   .where(dto.getBooleanBuilder())						   
						   .fetch();
	}
	
	@Override
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		//jpaAppointmentCode.findAll(dto.getBooleanBuilder());
				
		return queryFactory
					.selectFrom(QAppointmentCodeDetail.appointmentCodeDetail)
					.where(dto.getBooleanBuilder())
					.fetch();
	}

	@Override
	public List<Ledger> getLedger(SearchLedger searchCondition) {
		return queryFactory
				.selectFrom(QLedger.ledger)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
	@Override
	public List<LedgerList> getLedgerList(SearchLedgerList searchCondition) {

		return queryFactory
				.selectFrom(QLedgerList.ledgerList)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}

}
