package com.like.team.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWMEMBER")
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="MEMBER_NAME")
	private String memberName;
	
	@JsonIgnore
	@OneToMany(mappedBy="member")
	private List<JoinTeam> teamList = new ArrayList<JoinTeam>();
				
	public Member(String memberName) {
		this.memberName = memberName;	
	}
	
	public List<Team> getTeamList() {
		return this.teamList
				.stream()
				.map(r -> r.getTeam())
				.collect(Collectors.toList());
	}
}
