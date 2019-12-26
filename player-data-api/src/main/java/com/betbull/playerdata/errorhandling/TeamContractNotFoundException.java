package com.betbull.playerdata.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeamContractNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TeamContractNotFoundException(String playerId) {
		super("Player currently has no contracts with any teams");
	}
}
