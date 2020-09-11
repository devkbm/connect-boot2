package com.like.hrm.employee.domain.model.vo;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.License;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class LicenseList {

	/**
	 * 자격면허
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<License> licenseList = new LinkedHashSet<>();
	
	public License get(Long id) {
		
		return this.licenseList.stream()
							   .filter(e -> e.getLicenseId().equals(id))
							   .findFirst()
							   .orElse(null);
	}
	
	public void add(License license) {
		if (this.licenseList == null) {
			this.licenseList = new LinkedHashSet<>();
		}
		
		this.licenseList.add(license);
	}	
	
	public void remove(Long id) {		
		this.licenseList.removeIf(e -> e.getLicenseId().equals(id));			
	}
		
}
