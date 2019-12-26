package com.betbull.playerdata.teams;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.betbull.playerdata.controller.TeamsController;
import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TeamsController.class)
public class TeamsControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TeamService teamService;

	@Test
	public void getAllTeams() throws Exception {

		TeamType team = new TeamType();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		List<TeamType> teams = singletonList(team);

		given(teamService.getAllTeams()).willReturn(teams);

		mvc.perform(get("/api/teams")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getTeamById() throws Exception {

		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");

		given(teamService.getTeamById(team.getTeamId())).willReturn(team);

		mvc.perform(get("/api/teams/id")
				.param("teamId", String.valueOf(team.getTeamId())))
				.andExpect(status()
				.isOk())
				.andDo(print());
	}
	
	@Test
	public void createTeam() throws Exception {

		TeamType team = new TeamType();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		given(teamService.saveTeam(team)).willReturn(team);

		mvc.perform(post("/api/teams/create").content(asJsonString(team))
				 .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
	}
	
	@Test
	public void updateTeam() throws Exception {

		TeamType teamType = new TeamType();
		teamType.setTeamId(1L);
		teamType.setCountry("England");
		teamType.setName("Liverpool");
		teamType.setFounded("1902");
		
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");

		given(teamService.updateTeam(team)).willReturn(teamType);

		mvc.perform(put("/api/teams/update").content(asJsonString(teamType))
				 .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	@Test
	public void deleteTeam() throws Exception {
		
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		given(teamService.deleteTeam(team)).willReturn("Team deleted successfully");

		mvc.perform(delete("/api/teams/delete/1"))
			.andExpect(status().isOk())
			.andReturn();
	}
	
	@Test
	public void getTeamPlayers() throws Exception {
		
		PlayerType playerType = new PlayerType();
		playerType.setAge(33);
		playerType.setPlayerId(1L);
		playerType.setPosition("Forward");
		playerType.setPlayerName("Cristiano Ronaldo");
		playerType.setNationality("PT");
		playerType.setExperience("48");
		playerType.setHeight("1.83");
		playerType.setWeight("80");
		
		List<PlayerType> players = singletonList(playerType);
		
		given(teamService.getTeamPlayers("1", "2019")).willReturn(players);

		mvc.perform(get("/api/teams/players")
				.param("teamId", "1")
				.param("year", "2019"))
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
