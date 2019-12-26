package com.betbull.playerdata.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.entity.TeamPlayer;
import com.betbull.playerdata.errorhandling.RecordNotFoundException;
import com.betbull.playerdata.mapper.ModelEntityMapper;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	public Team getTeamById(Long teamId) {
		return teamRepository.findById(teamId)
				.orElseThrow(() -> new RecordNotFoundException(teamId));
	}
	
	public TeamType saveTeam(TeamType team) {
		Team teamEntity = ModelEntityMapper.mapTeamToEntity(team);
		teamRepository.save(teamEntity);
		team.setTeamId(teamEntity.getTeamId());
		return team;
	}
	
	public TeamType updateTeam(Team team) {
		Team updated = new Team();
		updated.setName(team.getName());
		updated.setFounded(team.getFounded());
		updated.setCountry(team.getCountry());
		teamRepository.save(updated);
		return ModelEntityMapper.mapEntitytoTeam(updated);
	}

	public String deleteTeam(Team team) {
		teamRepository.delete(team);
		return "Team deleted successfully";
	}
	
	public List<TeamType> getAllTeams() {
		return teamRepository.findAll()
				.stream()
				.map(entity -> ModelEntityMapper.mapEntitytoTeam(entity))
				.collect(Collectors.toList());
	}

	public List<PlayerType> getTeamPlayers(String teamId, String year) {
		
		Team team = teamRepository.findById(Long.parseLong(teamId))
				.orElseThrow(() -> new RecordNotFoundException(teamId));
		
		Set<TeamPlayer> teamPlayersFiltered = team.getTeamPlayers()
				.stream()
				.filter(teamPlayer -> condition(teamPlayer, year))
				.collect(Collectors.toSet());

		return teamPlayersFiltered.stream()
				.map(teamPlayer -> ModelEntityMapper.mapEntitytoPlayer(teamPlayer.getPlayer()))
				.collect(Collectors.toList());
	}
	
	private boolean condition(TeamPlayer teamPlayer, String year) {
		if(teamPlayer.getYearJoin() <= Integer.parseInt(year) && 
				Integer.parseInt(year) <= teamPlayer.getYearLeft())
			return true;
		return false;
	}

}
