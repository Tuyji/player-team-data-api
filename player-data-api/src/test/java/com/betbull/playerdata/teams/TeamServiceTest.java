package com.betbull.playerdata.teams;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.repository.TeamRepository;
import com.betbull.playerdata.service.TeamService;

@ExtendWith(SpringExtension.class)
public class TeamServiceTest {

	@Mock
	TeamRepository repository;

	@InjectMocks
	TeamService service;
	
	@Test
	public void createTeam() {
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		TeamType teamType = new TeamType();
		teamType.setTeamId(1L);
		teamType.setCountry("England");
		teamType.setName("Liverpool");
		teamType.setFounded("1902");
		
		when(repository.save(any(Team.class))).thenReturn(team);

		TeamType created = service.saveTeam(teamType);

		assertThat(created.getCountry()).isSameAs(teamType.getCountry());
		
	}
	
	@Test
	public void updateTeam() {
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		TeamType teamType = new TeamType();
		teamType.setTeamId(1L);
		teamType.setCountry("England");
		teamType.setName("Liverpool");
		teamType.setFounded("1902");

		Team update = new Team();
		update.setTeamId(1L);
		update.setCountry("England");
		update.setName("Arsenal");
		update.setFounded("1905");
		
		when(repository.save(any(Team.class))).thenReturn(team);
		
		TeamType created = service.saveTeam(teamType);
		TeamType updated = service.updateTeam(update);

		assertThat(created).isNotSameAs(updated);
	}
	
	@Test
	public void deleteTeam() {
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		service.deleteTeam(team);
        verify(repository, times(1)).delete(team);
	}
	
	@Test
	public void getTeam() {
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		when(repository.findById(1L)).thenReturn(Optional.of(team));
		
		Team actualTeam = service.getTeamById(1L);
		
		assertThat(actualTeam).isEqualTo(team);
	}
	
	@Test
	public void getAllTeams() {
		Team team = new Team();
		team.setTeamId(1L);
		team.setCountry("England");
		team.setName("Liverpool");
		team.setFounded("1902");
		
		List<Team> teams = singletonList(team);
		
		when(repository.findAll()).thenReturn(teams);
		
		List<TeamType> allTeams = service.getAllTeams();
		
		assertEquals(teams.size(), allTeams.size());
	}
	
}
