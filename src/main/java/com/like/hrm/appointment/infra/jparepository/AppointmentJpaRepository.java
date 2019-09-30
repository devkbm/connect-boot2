package com.like.hrm.appointment.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.DeptType;
import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCode;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaDeptType;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaJobType;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaLedger;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentJpaRepository implements AppointmentRepository {

	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaAppointmentCode jpaAppointmentCode;	
		
	@Autowired
	private JpaDeptType jpaDeptType;
	
	@Autowired
	private JpaJobType jpaJobType;
	
	@Autowired
	private JpaLedger jpaLedger;
	
	@Override
	public List<AppointmentCode> getAppointmentCodeList(Predicate condition) {
		return queryFactory.selectFrom(QAppointmentCode.appointmentCode)
						   .where(condition)
						   .fetch();
	}
	
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
	public DeptType getDeptType(String id) {
		Optional<DeptType> entity = jpaDeptType.findById(id);
		return entity.isPresent() ? entity.get() : null;
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
	public JobType getJobType(String id) {
		Optional<JobType> entity = jpaJobType.findById(id);
		return entity.isPresent() ? entity.get() : null;
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
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public void saveLedger(Ledger ledger) {
		jpaLedger.save(ledger);		
	}

	@Override
	public void deleteLedger(Ledger ledger) {
		jpaLedger.delete(ledger);		
	}
	

}
