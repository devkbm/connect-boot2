package com.like.hrm.employee.domain.model.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.Family;

public class FamilyList {

	/**
	 * 직원 가족이력
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<Family> familyList = new LinkedHashSet<>();
	
	public Family get(Long id) {
		
		return this.familyList.stream()
							   .filter(e -> e.getId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(Family license) {
		if (this.familyList == null) {
			this.familyList = new LinkedHashSet<>();
		}
		
		this.familyList.add(license);
	}	
	
	public void remove(Long id) {		
		this.familyList.removeIf(e -> e.getId().equals(id));			
	}
}
