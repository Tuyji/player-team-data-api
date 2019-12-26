package com.betbull.playerdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamType {

	@JsonProperty
	private Long teamId;
	@JsonProperty
	private String name;
	@JsonProperty
	private String country;
	@JsonProperty
	private String founded;

	
}
