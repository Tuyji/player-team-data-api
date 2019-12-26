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
import org.springframework.web.bind.annotation.RestController;

import com.betbull.playerdata.entity.Player;
import com.betbull.playerdata.model.PlayerType;
import com.betbull.playerdata.model.TeamType;
import com.betbull.playerdata.service.PlayerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/players")
@Api(value = "playerdata-players-api")
public class PlayersController {

	@Autowired
	PlayerService service;

	@GetMapping
	public ResponseEntity<List<PlayerType>> getAllPlayers() {
		return new ResponseEntity<>(service.getAllPlayers(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<PlayerType> getPlayer(@RequestParam String playerId) {
		return new ResponseEntity<>(service.getPlayerWithRelation(playerId), HttpStatus.OK);
	}

	@GetMapping(value = "/teams/{playerId}")
	public ResponseEntity<List<TeamType>> getPlayerTeams(@PathVariable String playerId) {
		return new ResponseEntity<>(service.getPlayerTeams(playerId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<PlayerType> createPlayer(@RequestBody PlayerType player) {
		return new ResponseEntity<>(service.savePlayer(player), HttpStatus.CREATED);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<PlayerType> updatePlayer(@RequestBody PlayerType playerType) {
		Player player = service.getPlayer(String.valueOf(playerType.getPlayerId()));
		return new ResponseEntity<>(service.updatePlayer(player), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{playerId}")
	public ResponseEntity<String> deletePlayer(@PathVariable String playerId) {
		Player player = service.getPlayer(playerId);
		return new ResponseEntity<>(service.deletePlayer(player), HttpStatus.OK);
	}

}
