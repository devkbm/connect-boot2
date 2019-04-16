package com.like.team.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.common.domain.AuditEntity;
import com.like.team.domain.model.id.TeamMemberId;
import com.like.user.domain.model.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTEAMUSER")
public class TeamMember extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -2005013743007739568L;

	@EmbeddedId
	TeamMemberId id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
	private Team team;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="USER_ID", insertable = false, updatable = false)
	private User user;	
	
	public TeamMember(Team team, User user) {
		this.id = new TeamMemberId(team.getTeamId(), user.getUserId());
	}
	
	public TeamMember(TeamMemberId id) {		
		this.id = id;
	}
			
	public Team getTeam() {
		return this.team;
	}
		
	public User getUser() {
		return this.user;
	}
	
}
