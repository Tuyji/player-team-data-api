package com.betbull.contractprice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betbull.contractprice.model.ContractPriceDetail;
import com.betbull.contractprice.service.ContractPriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/contractprice")
@Api(value = "contract-price-api")
public class ContractPriceController {

	@Autowired
	ContractPriceService service;

	@ApiOperation(value = "Contract Price", 
			notes = "Get contract price by given player id.", response = ContractPriceDetail.class)
	@GetMapping(value = "/id/{playerId}")
	public ResponseEntity<ContractPriceDetail> getContractPrice(@PathVariable String playerId) {
		return new ResponseEntity<>(service.getContractPrice(playerId), HttpStatus.OK);
	}

}
