package com.betbull.playerdata.mapper;

import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.entity.TeamPlayer;
import com.betbull.playerdata.model.PlayerTeamRelation;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;

public class ModelEntityMapper {
	
	public static Player mapPlayerToEntity(PlayerType playerType) {
		Player playerEntity = new Player();
		playerEntity.setPlayerName(playerType.getPlayerName());
		playerEntity.setAge(playerType.getAge());
		playerEntity.setBirthDate(playerType.getBirthDate());
		playerEntity.setNationality(playerType.getNationality());
		playerEntity.setPosition(playerType.getPosition());
		playerEntity.setWeight(playerType.getWeight());
		playerEntity.setHeight(playerType.getHeight());
		playerEntity.setExperience(playerType.getExperience());
		return playerEntity;
	}

	public static PlayerType mapEntitytoPlayer(Player player) {
		PlayerType playerType = new PlayerType();
		playerType.setPlayerId(player.getPlayerId());
		playerType.setPlayerName(player.getPlayerName());
		playerType.setAge(player.getAge());
		playerType.setBirthDate(player.getBirthDate());
		playerType.setExperience(player.getExperience());
		playerType.setNationality(player.getNationality());
		playerType.setPosition(player.getPosition());
		playerType.setWeight(player.getWeight());
		playerType.setHeight(player.getHeight());
		return playerType;
	}
	
	public static Team mapTeamToEntity(TeamType team) {
		Team teamEntity = new Team();
		teamEntity.setName(team.getName());
		teamEntity.setFounded(team.getFounded());
		teamEntity.setCountry(team.getCountry());
		return teamEntity;
	}
	
	public static TeamType mapEntitytoTeam(Team teamEntity) {
		TeamType team = new TeamType();
		team.setTeamId(teamEntity.getTeamId());
		team.setName(teamEntity.getName());
		team.setFounded(teamEntity.getFounded());
		team.setCountry(teamEntity.getCountry());
		return team;
	}
	
	public static PlayerTeamRelation mapPlayerTeamRelation(TeamPlayer teamPlayer) {
		PlayerTeamRelation relation = new PlayerTeamRelation();
		relation.setTeamId(teamPlayer.getTeam().getTeamId());
		relation.setYearJoin(teamPlayer.getYearJoin());
		relation.setYearLeft(teamPlayer.getYearLeft());
		return relation;
	}
	
}
