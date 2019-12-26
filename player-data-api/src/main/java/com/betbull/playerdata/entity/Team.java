package com.betbull.playerdata.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = "team")
@Table(name = "team")
@AllArgsConstructor
@RequiredArgsConstructor 
public class Team implements Serializable {

	private Long teamId;
	private String name;
	private String country;
	private String founded;
	private Set<TeamPlayer> teamPlayers = new HashSet<>();
	
	@Id
	@Column(name = "team_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.team", cascade=CascadeType.ALL)
	public Set<TeamPlayer> getTeamPlayers() {
		return teamPlayers;
	}

	public void setTeamPlayers(Set<TeamPlayer> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
}
