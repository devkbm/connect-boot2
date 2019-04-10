package com.like.team.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member {

	@Id
	@Column(name="member_id")
	private String memberId;
	
	@Column(name="member_name")
	private String memberName;
	
	@JsonIgnore
	@OneToMany(mappedBy="member")
	private List<JoinTeam> teamList = new ArrayList<JoinTeam>();
		
	protected Member() {}
	
	public Member(String memberId, String memberName) {
		this.memberId = memberId;
		this.memberName = memberName;	
	}
	
	public List<Team> getTeamList() {
		return this.teamList
				.stream()
				.map(r -> r.getTeam())
				.collect(Collectors.toList());
	}
}
