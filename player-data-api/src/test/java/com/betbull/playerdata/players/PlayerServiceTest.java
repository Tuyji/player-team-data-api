package com.betbull.playerdata.players;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.model.PlayerTeamRelation;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.repository.PlayerRepository;
import com.betbull.playerdata.service.PlayerService;
import com.betbull.playerdata.service.TeamService;

@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {
	
	@Mock
	PlayerRepository repository;

	@InjectMocks
	PlayerService playerService;
	
	@Mock
	TeamService teamService;
	
	@Test
	public void createPlayer() {
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		PlayerType playerType = new PlayerType();
		playerType.setAge(33);
		playerType.setPlayerId(1L);
		playerType.setPosition("Forward");
		playerType.setPlayerName("Cristiano Ronaldo");
		playerType.setNationality("PT");
		playerType.setExperience("48");
		playerType.setHeight("1.83");
		playerType.setWeight("80");
		
		PlayerTeamRelation relation = new PlayerTeamRelation();
		relation.setTeamId(1L);
		relation.setYearJoin(2016);
		relation.setYearLeft(2019);
		playerType.setPlayerTeamRelations(singletonList(relation));
		
		TeamType teamType = new TeamType();
		teamType.setTeamId(1L);
		teamType.setCountry("England");
		teamType.setName("Liverpool");
		teamType.setFounded("1902");
		
		when(repository.save(any(Player.class))).thenReturn(player);

		teamService.saveTeam(teamType);
		
		PlayerType created = playerService.savePlayer(playerType);

		assertThat(playerType.getPlayerName()).isSameAs(created.getPlayerName());
		
	}
	
	@Test
	public void getPlayer() {
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		when(repository.findById(1L)).thenReturn(Optional.of(player));
		
		Player actualPlayer = playerService.getPlayer("1");
		
		assertEquals(actualPlayer.getPlayerName(), player.getPlayerName());
	}
	
	@Test
	public void getAllPlayers() {
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		List<Player> players = singletonList(player);
		
		when(repository.findAll()).thenReturn(players);
		
		List<PlayerType> allPlayers = playerService.getAllPlayers();
		
		assertEquals(players.size(), allPlayers.size());
	}
	

	@Test
	public void updatePlayer() {
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");

		Player update = new Player();
		update.setAge(33);
		update.setPlayerId(1L);
		update.setPosition("Forward");
		update.setPlayerName("Messi");
		update.setNationality("AR");
		update.setExperience("48");
		update.setHeight("1.83");
		update.setWeight("80");
		
		when(repository.save(any(Player.class))).thenReturn(player);
		
		PlayerType updated = playerService.updatePlayer(update);

		assertNotEquals(player.getPlayerName(), updated.getPlayerName());
	}
	
	@Test
	public void deleteTeam() {
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		playerService.deletePlayer(player);
        verify(repository, times(1)).delete(player);
	}
}
