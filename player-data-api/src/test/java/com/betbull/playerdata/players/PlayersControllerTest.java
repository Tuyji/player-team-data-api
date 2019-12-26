package com.betbull.playerdata.players;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.betbull.playerdata.controller.PlayersController;
import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.model.PlayerTeamRelation;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlayersController.class)
public class PlayersControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PlayerService playerService;

	@Test
	public void getAllPlayersTest() throws Exception {

		PlayerType player = new PlayerType();
		player.setPlayerId(1L);
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setBirthDate(LocalDate.of(1987, 01, 01));
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");

		List<PlayerType> allPlayers = singletonList(player);

		given(playerService.getAllPlayers()).willReturn(allPlayers);

		mvc.perform(get("/api/players")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getPlayerById() throws Exception {

		Player player = new Player();
		player.setPlayerId(1L);
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setBirthDate(LocalDate.of(1987, 01, 01));
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");

		given(playerService.getPlayer(String.valueOf(player.getPlayerId()))).willReturn(player);

		mvc.perform(get("/api/players/id")
				.param("playerId", String.valueOf(player.getPlayerId())))
				.andExpect(status()
				.isOk())
				.andDo(print());
	}
	
	@Test
	public void createPlayer() throws Exception {

		PlayerType player = new PlayerType();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		PlayerTeamRelation relation = new PlayerTeamRelation();
		relation.setTeamId(1L);
		relation.setYearJoin(2016);
		relation.setYearLeft(2019);
		player.setPlayerTeamRelations(singletonList(relation));

		given(playerService.savePlayer(player)).willReturn(player);

		mvc.perform(post("/api/players/create").content(asJsonString(player))
				 .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
		
	}
	
	@Test
	public void updatePlayer() throws Exception {

		PlayerType playerType = new PlayerType();
		playerType.setAge(33);
		playerType.setPlayerId(1L);
		playerType.setPosition("Forward");
		playerType.setPlayerName("Cristiano Ronaldo");
		playerType.setNationality("PT");
		playerType.setExperience("48");
		playerType.setHeight("1.83");
		playerType.setWeight("80");
		
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");

		given(playerService.updatePlayer(player)).willReturn(playerType);

		mvc.perform(put("/api/players/update").content(asJsonString(playerType))
				 .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
		
	}
	
	@Test
	public void deletePlayer() throws Exception {
		
		Player player = new Player();
		player.setAge(33);
		player.setPlayerId(1L);
		player.setPosition("Forward");
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");

		given(playerService.deletePlayer(player)).willReturn("Player deleted successfully");

		mvc.perform(delete("/api/players/delete/1"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void getPlayerTeams() throws Exception {
		
		TeamType teamType = new TeamType();
		teamType.setTeamId(1L);
		teamType.setCountry("England");
		teamType.setName("Liverpool");
		teamType.setFounded("1902");
		
		List<TeamType> teams = singletonList(teamType);
		
		given(playerService.getPlayerTeams("1")).willReturn(teams);

		mvc.perform(get("/api/players/teams/1"))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
