package com.like.hrm.employee.domain.model.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.SchoolCareer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class SchoolCareerList {

	/**
	 * 교육이력
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<SchoolCareer> schoolCareerList = new LinkedHashSet<>();
	
	public SchoolCareer get(Long id) {
		return this.schoolCareerList.stream()
								 .filter(e -> e.getId().equals(id))
								 .findFirst()
								 .orElse(null);
	}
	
	public void add(SchoolCareer education) {
		if (this.schoolCareerList == null) {
			this.schoolCareerList = new LinkedHashSet<>();
		}
		
		this.schoolCareerList.add(education);
	}
	
	public void remove(Long id) {
		this.schoolCareerList.removeIf(e -> e.getId().equals(id));
	}
}
