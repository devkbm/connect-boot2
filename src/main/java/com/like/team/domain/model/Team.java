package com.like.team.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;
import com.like.user.domain.model.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "GRWTEAM")
public class Team extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 7255535665611002987L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEAM_ID")
	Long teamId;
	
	@Column(name="TEAM_NAME")
	String teamName;
	
	/*@OneToOne
	@JoinColumn(name="USER_ID")
	private User manager;*/ 
	
	@JsonIgnore
	@OneToMany(mappedBy="team", fetch = FetchType.EAGER)
	List<TeamMember> members = new ArrayList<TeamMember>();			
	
	public Team(String teamName) {
		this.teamName = teamName;		
	}	
	
	public void changeTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public List<TeamMember> getTeamMemberList() {
		return this.members;
	}	
		
	public List<User> getMemberList() {
		/*
		// Java 7
		List<Member> memberList = new ArrayList<>();
		for (JoinTeam joinTeam : this.memberList) {
			memberList.add(joinTeam.getMember());
		}
		
		return memberList;
		*/
		return this.members
				.stream()
				.map(r -> r.getUser())
				.collect(Collectors.toList());
	}
	
	public void addMember(User user)
	{
		this.members.add(new TeamMember(this, user));
	}
	
	public void addMemberList(List<TeamMember> memberList) {
		this.members = memberList;
	}
	
}
