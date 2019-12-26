package com.betbull.playerdata.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerType {
	
	@JsonProperty
	private Long playerId;
	@JsonProperty
	private String playerName;
	@JsonProperty
	private String position;
	@JsonProperty
	private int age;
	@JsonProperty
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	@JsonProperty
	private String nationality;
	@JsonProperty
	private String height;
	@JsonProperty
	private String weight;
	@JsonProperty
	private String experience;
	@JsonProperty("playerTeamRelations")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonInclude(value= JsonInclude.Include.NON_NULL)
	private List<PlayerTeamRelation> playerTeamRelations;
}
