package com.betbull.playerdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.betbull.playerdata.entity.Team;
import com.betbull.playerdata.mapper.ModelEntityMapper;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.service.TeamService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/teams")
@Api(value = "playerdata-teams-api")
public class TeamsController {

	@Autowired
	TeamService teamService;

	@GetMapping
	public ResponseEntity<List<TeamType>> getAllTeams() {
		return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<TeamType> getTeam(@RequestParam String teamId) {
		Team team = teamService.getTeamById(Long.parseLong(teamId));
		return new ResponseEntity<>(ModelEntityMapper.mapEntitytoTeam(team), HttpStatus.OK);
	}
	
	@GetMapping(value = "/players")
	public ResponseEntity<List<PlayerType>> getTeamPlayers(@RequestParam String teamId, 
			@RequestParam String year) {
		return new ResponseEntity<>(teamService.getTeamPlayers(teamId, year), HttpStatus.OK);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<TeamType> createTeam(@RequestBody TeamType team) {
		return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<TeamType> updateTeam(@RequestBody TeamType teamType) {
		Team team = teamService.getTeamById(teamType.getTeamId());
		return new ResponseEntity<>(teamService.updateTeam(team), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{teamId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteTeam(@PathVariable String teamId) {
		Team team = teamService.getTeamById(Long.parseLong(teamId));
		return new ResponseEntity<>(teamService.deleteTeam(team), HttpStatus.OK);
	}

}
