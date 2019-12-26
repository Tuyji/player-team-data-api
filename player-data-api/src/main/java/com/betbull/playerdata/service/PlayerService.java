package com.betbull.playerdata.service;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.entity.TeamPlayer;
import com.betbull.playerdata.errorhandling.RecordNotFoundException;
import com.betbull.playerdata.errorhandling.TeamContractNotFoundException;
import com.betbull.playerdata.mapper.ModelEntityMapper;
import com.betbull.playerdata.model.PlayerTeamRelation;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	TeamService teamService;

	public Player getPlayer(String playerId) {
		return playerRepository
				.findById(Long.parseLong(playerId))
				.orElseThrow(() -> new RecordNotFoundException(playerId));
	}
	
	public PlayerType getPlayerWithRelation(String playerId) {
		Player player = playerRepository
				.findById(Long.parseLong(playerId))
				.orElseThrow(() -> new RecordNotFoundException(playerId));
		int currentYear = Year.now().getValue();
		TeamPlayer teamPlayer = player.getTeamPlayers().stream().filter(
				tp -> tp.getYearJoin() <= currentYear && tp.getYearLeft() >= currentYear)
				.findAny().orElseThrow(() -> new TeamContractNotFoundException(playerId));
		PlayerType playerType = ModelEntityMapper.mapEntitytoPlayer(player);
		List<PlayerTeamRelation> relations = Collections
				.singletonList(ModelEntityMapper.mapPlayerTeamRelation(teamPlayer));
		playerType.setPlayerTeamRelations(relations);
		return playerType;
	}

	public PlayerType savePlayer(PlayerType player) {
		Player playerEntity = ModelEntityMapper.mapPlayerToEntity(player);
		player.getPlayerTeamRelations().forEach(relation -> 
					createPlayerRelations(relation, playerEntity));
		player.setPlayerId(playerEntity.getPlayerId());
		return player;
	}

	private void createPlayerRelations(PlayerTeamRelation relation, Player playerEntity) {
		Team team = teamService.getTeamById(relation.getTeamId());
		playerEntity.getTeamPlayers().add(createTempPlayerRelation(relation, playerEntity, team));
		playerRepository.save(playerEntity);
	}

	private TeamPlayer createTempPlayerRelation(PlayerTeamRelation relation, 
			Player playerEntity, Team teamEntity) {
		TeamPlayer teamPlayer = new TeamPlayer();
		teamPlayer.setPlayer(playerEntity);
		teamPlayer.setTeam(teamEntity);
		teamPlayer.setYearJoin(relation.getYearJoin());
		teamPlayer.setYearLeft(relation.getYearLeft());
		return teamPlayer;
	}
	
	public PlayerType updatePlayer(Player player) {
		Player updated = new Player();
		updated.setPlayerName(player.getPlayerName());
		updated.setAge(player.getAge());
		updated.setBirthDate(player.getBirthDate());
		updated.setExperience(player.getExperience());
		updated.setNationality(player.getNationality());
		updated.setPosition(player.getPosition());
		updated.setWeight(player.getWeight());
		updated.setHeight(player.getHeight());
		playerRepository.save(updated);
		return ModelEntityMapper.mapEntitytoPlayer(updated);
	}

	public String deletePlayer(Player player) {
		playerRepository.delete(player);
		return "Player deleted successfully";
	}

	public List<PlayerType> getAllPlayers() {
		return playerRepository.findAll().stream()
				.map(entity -> ModelEntityMapper.mapEntitytoPlayer(entity))
				.collect(Collectors.toList());
	}

	public List<TeamType> getPlayerTeams(String playerId) {
		Player player = playerRepository.findById(Long.parseLong(playerId))
				.orElseThrow(() -> new RecordNotFoundException(playerId));

		return player.getTeamPlayers().stream()
				.map(teamPlayer -> ModelEntityMapper.mapEntitytoTeam(teamPlayer.getTeam()))
				.collect(Collectors.toList());
	}

}
