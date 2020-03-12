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
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.QDeptType;
import com.like.hrm.appointment.domain.model.QJobType;
import com.like.hrm.appointment.domain.model.QLedger;
import com.like.hrm.appointment.domain.model.QLedgerList;
import com.like.hrm.appointment.domain.model.QTypeDetailCode;
import com.like.hrm.appointment.domain.model.TypeDetailCode;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.appointment.domain.repository.AppointmentLedgerRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCode;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaDeptType;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaJobType;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaLedger;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaTypeDetailCode;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentJpaRepository implements AppointmentLedgerRepository, AppointmentCodeRepository {

	@Autowired
	private JPAQueryFactory	queryFactory;	
	
	@Autowired
	private JpaAppointmentCode jpaAppointmentCode;	
		
	@Autowired
	private JpaDeptType jpaDeptType;
	
	@Autowired
	private JpaJobType jpaJobType;
	
	@Autowired
	private JpaTypeDetailCode jpaTypeDetailCode;
	
	@Autowired
	private JpaLedger jpaLedger;
	
	@Override
	public AppointmentCode getAppointmentCode(String codeId) {
		Optional<AppointmentCode> entity = jpaAppointmentCode.findById(codeId);
		
		return entity.isPresent() ? entity.get() : null;
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
	public List<DeptType> getDeptTypeList() {
				
		return queryFactory
				.selectFrom(QDeptType.deptType)				
				.fetch();
	}
	
	@Override
	public DeptType getDeptType(String id) {
		Optional<DeptType> entity = jpaDeptType.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveDeptType(DeptType deptType) {
		jpaDeptType.save(deptType);		
	}

	@Override
	public void deleteDeptType(DeptType deptType) {
		jpaDeptType.delete(deptType);		
	}

	@Override
	public List<JobType> getJobTypeList() {
		
		return queryFactory
				.selectFrom(QJobType.jobType)				
				.fetch();
	}
	
	@Override
	public JobType getJobType(String id) {
		Optional<JobType> entity = jpaJobType.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveJobType(JobType jobType) {
		jpaJobType.save(jobType);		
	}

	@Override
	public void deleteJobType(JobType jobType) {
		jpaJobType.delete(jobType);		
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

	@Override
	public TypeDetailCode getTypeDetailCode(String id) {		 
		Optional<TypeDetailCode> entity = jpaTypeDetailCode.findById(id);
		return entity.orElse(null);
	}

	@Override
	public List<TypeDetailCode> getTypeDetailCodeList(String typeId) {		
		return queryFactory
				.selectFrom(QTypeDetailCode.typeDetailCode)
				.where(QTypeDetailCode.typeDetailCode.typeId.eq(typeId))
				.fetch();
	}

	@Override
	public void saveTypeDetailCode(TypeDetailCode typeDetailCode) {
		jpaTypeDetailCode.save(typeDetailCode);		
	}

	@Override
	public void deleteTypeDetailCode(TypeDetailCode typeDetailCode) {				
		jpaTypeDetailCode.delete(typeDetailCode);		
	}
	
}
