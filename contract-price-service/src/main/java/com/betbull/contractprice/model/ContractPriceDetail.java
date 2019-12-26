package com.betbull.contractprice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ContractPriceDetail {

	@JsonProperty
	private String contractPrice;
	@JsonProperty
	private String currency;
	@JsonProperty
	private PlayerType playerType;
	
}
