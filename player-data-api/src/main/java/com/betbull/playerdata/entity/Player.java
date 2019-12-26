package com.betbull.playerdata.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity(name = "player")
@Table(name = "player")
@AllArgsConstructor
@RequiredArgsConstructor
public class Player implements Serializable {

	private Long playerId;
	private String playerName;
	private String position;
	private int age;
	private LocalDate birthDate;
	private String nationality;
	private String height;
	private String weight;
	private String experience;
	private Set<TeamPlayer> teamPlayers = new HashSet<>(0);
	
	@Id
	@Column(name = "player_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.player", cascade=CascadeType.ALL)
	public Set<TeamPlayer> getTeamPlayers() {
		return teamPlayers;
	}

	public void setTeamPlayers(Set<TeamPlayer> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}

}
