package com.like.hrm.anualleave.infra.jparepository;

import org.springframework.stereotype.Repository;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;
import com.like.hrm.anualleave.domain.repository.AnualLeaveRepository;
import com.like.hrm.anualleave.infra.jparepository.springdata.JpaAnualLeave;

@Repository
public class AnualLeaveJpaRepository implements AnualLeaveRepository {

	private JpaAnualLeave jpaAnualLeave;
	
	public AnualLeaveJpaRepository(JpaAnualLeave jpaAnualLeave) {
		this.jpaAnualLeave = jpaAnualLeave;
	}
	
	@Override
	public AnualLeave getAnualLeave(AnualLeaveId id) {
		return jpaAnualLeave.findById(id).orElse(null);
	}

	@Override
	public void saveAnualLeave(AnualLeave entity) {
		jpaAnualLeave.save(entity);
	}

	@Override
	public void deleteAnualLeave(AnualLeave entity) {
		jpaAnualLeave.delete(entity);
	}

}
