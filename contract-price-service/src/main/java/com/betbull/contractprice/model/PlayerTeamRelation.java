package com.betbull.contractprice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTeamRelation {
	
	@JsonProperty
	private Long teamId;
	@JsonProperty
	private int yearJoin;
	@JsonProperty
	private int yearLeft;
}
