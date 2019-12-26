package com.betbull.contractprice.builders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.Money;

import com.betbull.contractprice.constants.CommonConstants;
import com.betbull.contractprice.constants.Countries;
import com.betbull.contractprice.model.ContractPriceDetail;
import com.betbull.contractprice.model.PlayerType;

public class ContractPriceDetailBuilder {

	private PlayerType player;
	private BigDecimal age;
	private BigDecimal experience;
	private BigDecimal transferFee;
	private BigDecimal commission;
	private BigDecimal totalPrice;
	private String country;
	private String currency;

	public ContractPriceDetailBuilder setPlayer(PlayerType player) {
		this.player = player;
		this.age = new BigDecimal(player.getAge());
		this.experience = new BigDecimal(player.getExperience());
		return this;
	}
	
	public ContractPriceDetailBuilder setCountry(String country) {
		this.country = country;
		return this;
	}

	public ContractPriceDetailBuilder calculateTransferFee() {
		this.transferFee = experience.multiply(CommonConstants.TRANFER_FEE_MULTIPLIER)
				.divide(age, RoundingMode.HALF_UP);
		return this;
	}
	
	public ContractPriceDetailBuilder addCommission() {
		this.commission = this.transferFee.multiply(new BigDecimal(10))
				.divide(new BigDecimal(100));
		return this;
	}
	
	public ContractPriceDetailBuilder calculateTotalPrice() {
		this.totalPrice = this.transferFee.add(this.commission)
				.setScale(2, RoundingMode.CEILING);
		return this;
	}
	
	public ContractPriceDetailBuilder convertToCurrency() {
		String countryIso = Countries.INSTANCE.getCountryMap().get(this.country);
		
		Locale locale = Arrays.stream(Locale.getAvailableLocales())
				.filter(al -> al.getCountry().equals(countryIso))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("No Country Found"));
		
		CurrencyUnit baseCurrency = Monetary.getCurrency("USD");
		this.currency = baseCurrency.getCurrencyCode();
		CurrencyUnit currency = Monetary.getCurrency(locale);
		
		if(! baseCurrency.getCurrencyCode().equals(currency.getCurrencyCode())) {
			Money money = Money.of(totalPrice, baseCurrency);
			CurrencyConversion conversion = MonetaryConversions.getConversion(currency.getCurrencyCode());
		    MonetaryAmount convertedAmount = conversion.apply(money);
		    this.totalPrice = convertedAmount.getNumber().numberValueExact(BigDecimal.class)
		    		.setScale(2, RoundingMode.CEILING);
		    this.currency = currency.getCurrencyCode();
		}
		
		return this;
	}
	
	public ContractPriceDetail build() {
		return new ContractPriceDetail(this.totalPrice.toString(), this.currency, this.player);
	}
}
