package com.betbull.playerdata.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String id) {
		super("Record not found: " + id);
	}
	
	public RecordNotFoundException(long id) {
		super("Record not found: " + String.valueOf(id));
	}

}
