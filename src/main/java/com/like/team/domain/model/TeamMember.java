package com.like.team.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.user.domain.model.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTEAMUSER")
public class TeamMember {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
		
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	private Team team;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;		
		
	public TeamMember(Team team, User user) {		
		this.team = team;
		this.user = user;
	}
		
	public void changeTeam(Team team) {
		this.team = team;		
	}
	
	public Long getId() {
		return this.id;
	}
		
	public Team getTeam() {
		return this.team;
	}
	
	public User getUser() {
		return this.user;
	}
	
}
