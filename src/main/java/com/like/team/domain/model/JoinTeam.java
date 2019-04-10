package com.like.team.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTEAMMEMBER")
public class JoinTeam {

	@Id 
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="JOIN_DT")
	private LocalDate joinDate;
	
	@Column(name="WITHDRAW_DT")
	private LocalDate withdrawDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;		
	
	@Builder
	public JoinTeam(Team team, Member member, LocalDate joinDate, LocalDate withdrawDate) {		
		this.team = team;
		this.member = member;
		this.joinDate = joinDate;
		this.withdrawDate = withdrawDate;
	}
		
	public void changeTeam(Team team) {
		this.team = team;		
	}
	
	public Member getMember() {
		return this.member;
	}
	
	public Team getTeam() {
		return this.team;
	}
	
}
