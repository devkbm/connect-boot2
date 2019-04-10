package com.like.team.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "team")
public class Team {

	@Id	
	@Column(name="team_id")
	private String teamId;
	
	@Column(name="team_name")
	private String teamName;
	
	@JsonIgnore
	@OneToMany(mappedBy="team")
	private List<JoinTeam> memberList = new ArrayList<JoinTeam>();			
	
	public Team(String teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	public List<Member> getMemberList() {
		/*List<Member> memberList = new ArrayList<>();
		for (JoinTeam joinTeam : this.memberList) {
			memberList.add(joinTeam.getMember());
		}
		
		return memberList;*/
		return this.memberList
				.stream()
				.map(r -> r.getMember())
				.collect(Collectors.toList());
	}
}
