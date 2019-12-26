package com.betbull.contractprice.constants;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum Countries {

	INSTANCE;
	
	public Map<String, String> getCountryMap() {
		Map<String, String> countries = new HashMap<>();
	    for (String iso : Locale.getISOCountries()) {
	        Locale l = new Locale("", iso);
	        countries.put(l.getDisplayCountry(), iso);
	    }
	    return countries;
	}
}
