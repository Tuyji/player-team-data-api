package com.betbull.contractprice;

import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.betbull.contractprice.builders.ContractPriceDetailBuilder;
import com.betbull.contractprice.controller.ContractPriceController;
import com.betbull.contractprice.model.ContractPriceDetail;
import com.betbull.contractprice.model.PlayerType;
import com.betbull.contractprice.service.ContractPriceService;

@WebMvcTest(ContractPriceController.class)
public class ContractPriceControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ContractPriceService service;
	
	@Test
	public void getContractPrice() throws Exception {
		
		PlayerType player = new PlayerType();
		player.setPlayerId(1L);
		player.setAge(30);
		player.setPlayerName("Cristiano Ronaldo");
		player.setNationality("PT");
		player.setBirthDate(LocalDate.of(1987, 01, 01));
		player.setExperience("48");
		player.setHeight("1.83");
		player.setWeight("80");
		
		ContractPriceDetail contractPriceDetail = new ContractPriceDetailBuilder()
				.setPlayer(player)
				.setCountry("United Kingdom")
				.calculateTransferFee()
				.addCommission()
				.calculateTotalPrice()
				.convertToCurrency()
				.build();

		given(service.getContractPrice("1")).willReturn(contractPriceDetail);
	}

}
