package com.betbull.playerdata.dataloader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.entity.TeamPlayer;
import com.betbull.playerdata.repository.PlayerRepository;
import com.betbull.playerdata.repository.TeamRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
    private TeamRepository teamRepository;
	@Autowired
    private PlayerRepository playerRepository;

	@Override
    public void run(ApplicationArguments args) {
    	loadDatas();
    }

	private void loadDatas() {
		List<Team> teams = new ArrayList<>();
		teams.add(teamRepository.save(new Team(null, "Manchester United", "United Kingdom", "1887", null)));
		teams.add(teamRepository.save(new Team(null, "Liverpool", "United Kingdom", "1900", null)));
		teams.add(teamRepository.save(new Team(null, "Chelsea", "United Kingdom", "1903", null)));
		
		loadPlayers(teams);
	}
	
	private void loadPlayers(List<Team> teams) {
		
		Map<String, List<Player>> playerMap = new HashMap<String, List<Player>>();
		playerMap.put("Manchester United", createPlayersForManchester());
		playerMap.put("Liverpool", createPlayersForLiverpool());
		playerMap.put("Chelsea", createPlayersForChelsea());
		
		createRelations(teams, playerMap);
		
	}

	private void createRelations(List<Team> teams, Map<String, List<Player>> map) {
		for(Team team : teams) {
			List<Player> players = map.get(team.getName());
			players.stream().forEach(player -> saveRelation(player, team));
		}
	}

	private void saveRelation(Player player, Team team) {
		TeamPlayer teamPlayer = new TeamPlayer();
		teamPlayer.setPlayer(player);
		teamPlayer.setTeam(team);
		teamPlayer.setYearJoin(2017);
		teamPlayer.setYearLeft(2019);
		player.setTeamPlayers(new HashSet<TeamPlayer>());
		player.getTeamPlayers().add(teamPlayer);
		playerRepository.save(player);
	}

	private List<Player> createPlayersForChelsea() {
		
		List<Player> players = new ArrayList<>();
		
		players.add(new Player(null, "Philippe SENDEROS", "Forward", 33, LocalDate.parse("1986-01-01"), 
				"PT", "185", "80", "80", null));
		players.add(new Player(null, "Emmanuel SENDEROS", "Center", 25, LocalDate.parse("1986-01-01"), 
				"PT", "177", "70", "60", null));
		players.add(new Player(null, "Justin HOYTE", "Fullback", 22, LocalDate.parse("1986-01-01"), 
				"PT", "175", "70", "48", null));
		players.add(new Player(null, "Ashley COLE", "Quarterback", 26, LocalDate.parse("1986-01-01"), 
				"PT", "190", "80", "48", null));
		players.add(new Player(null, "Johan DJOUROU", "Defensive", 32, LocalDate.parse("1986-01-01"), 
				"PT", "167", "77", "60", null));
		players.add(new Player(null, "Sebastian LARSSON", "Forward", 31, LocalDate.parse("1986-01-01"), 
				"PT", "171", "74", "36", null));
		players.add(new Player(null, "Cesc BREGAS", "Forward", 21, LocalDate.parse("1986-01-01"), 
				"PT", "180", "75", "48", null));
		players.add(new Player(null, "Neil TONGE", "Defensive", 22, LocalDate.parse("1986-01-01"), 
				"PT", "188", "79", "80", null));
		
		
		return players;
	}

	private List<Player> createPlayersForLiverpool() {
		List<Player> players = new ArrayList<>();
		
		players.add(new Player(null, "Mathieu FLAMINI", "Quarterback", 25, LocalDate.parse("1986-01-01"), 
				"PT", "177", "66", "36", null));
		players.add(new Player(null, "Robin VAN PERSIE", "Fullback", 27, LocalDate.parse("1986-01-01"), 
				"PT", "177", "66", "80", null));
		players.add(new Player(null, "Quincy OWUSU", "Defensive", 22, LocalDate.parse("1986-01-01"), 
				"PT", "190", "88", "80", null));
		players.add(new Player(null, "Arturo LUPOLI", "Forward", 34, LocalDate.parse("1986-01-01"), 
				"PT", "190", "83", "36", null));
		players.add(new Player(null, "Stuart TAYLOR", "Defensive", 33, LocalDate.parse("1986-01-01"), 
				"PT", "177", "83", "80", null));
		players.add(new Player(null, "Liam RIDGEWELL", "Forward", 19, LocalDate.parse("1986-01-01"), 
				"PT", "185", "85", "80", null));
		players.add(new Player(null, "Jlloyd SAMUEL", "Forward", 26, LocalDate.parse("1986-01-01"), 
				"PT", "182", "86", "36", null));
		players.add(new Player(null, "Ross WILLIAMS", "Forward", 24, LocalDate.parse("1986-01-01"), 
				"PT", "177", "77", "48", null));
		
		return players;
	}

	private List<Player> createPlayersForManchester() {
		List<Player> players = new ArrayList<>();
		
		players.add(new Player(null, "Gary CAHILL", "Fullback", 27, LocalDate.parse("1986-01-01"), 
				"PT", "170", "82", "80", null));
		players.add(new Player(null, "Aaron HUGHES", "Fullback", 28, LocalDate.parse("1986-01-01"), 
				"PT", "190", "72", "80", null));
		players.add(new Player(null, "Peter BARRY", "Quarterback", 29, LocalDate.parse("1986-01-01"), 
				"PT", "159", "75", "36", null));
		players.add(new Player(null, "Gareth MOORE", "Defensive", 21, LocalDate.parse("1986-01-01"), 
				"PT", "166", "65", "48", null));
		players.add(new Player(null, "Eric FLINDERS", "Quarterback", 21, LocalDate.parse("1986-01-01"), 
				"PT", "167", "62", "48", null));
		players.add(new Player(null, "Steven TURNBULL", "Defensive", 34, LocalDate.parse("1986-01-01"), 
				"PT", "188", "78", "36", null));
		players.add(new Player(null, "Luke AUSTIN", "Forward", 33, LocalDate.parse("1986-01-01"), 
				"PT", "176", "78", "80", null));
		players.add(new Player(null, "Scott ATKINSON", "Fullback", 12, LocalDate.parse("1986-01-01"), 
				"PT", "166", "78", "36", null));

		return players;
	}
}