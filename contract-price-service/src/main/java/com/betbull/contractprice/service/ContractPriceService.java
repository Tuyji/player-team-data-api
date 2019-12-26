package com.betbull.contractprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.betbull.contractprice.builders.ContractPriceDetailBuilder;
import com.betbull.contractprice.constants.CommonConstants;
import com.betbull.contractprice.model.ContractPriceDetail;
import com.betbull.contractprice.model.PlayerType;
import com.betbull.contractprice.model.TeamType;

@Service
public class ContractPriceService {

	@Autowired
	RestTemplate restTemplate;
	
	public ContractPriceDetail getContractPrice(String playerId) {
		PlayerType player = getPlayer(playerId);
		Long teamId = player.getPlayerTeamRelations().get(0).getTeamId();
		TeamType team = getTeam(String.valueOf(teamId));
		
		return new ContractPriceDetailBuilder()
				.setPlayer(player)
				.setCountry(team.getCountry())
				.calculateTransferFee()
				.addCommission()
				.calculateTotalPrice()
				.convertToCurrency()
				.build();
	}
	
	private PlayerType getPlayer(String playerId) {
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CommonConstants.PLAYERS_API_URI)
	            .queryParam("playerId", playerId);

	    HttpEntity<?> entity = new HttpEntity<>(headers);

	    HttpEntity<PlayerType> response = restTemplate.exchange(
	            builder.toUriString(), 
	            HttpMethod.GET, 
	            entity, 
	            PlayerType.class);
	    
	    return response.getBody();
	}
	
	private TeamType getTeam(String teamId) {
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CommonConstants.TEAMS_API_URI)
	            .queryParam("teamId", teamId);

	    HttpEntity<?> entity = new HttpEntity<>(headers);

	    HttpEntity<TeamType> response = restTemplate.exchange(
	            builder.toUriString(), 
	            HttpMethod.GET, 
	            entity, 
	            TeamType.class);
	    
	    return response.getBody();
	}
	
}
