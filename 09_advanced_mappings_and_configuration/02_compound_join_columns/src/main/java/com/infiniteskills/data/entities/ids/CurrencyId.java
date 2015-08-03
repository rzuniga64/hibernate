package com.infiniteskills.data.entities.ids;

import java.io.Serializable;

// Serializable is a marker for JPA when we are using a class to hold an id.
@SuppressWarnings("serial")
public class CurrencyId implements Serializable{

	private String name;
	private String countryName;

	public CurrencyId(){ }
	
	public CurrencyId(String name, String countryName) {
		this.name = name;
		this.countryName = countryName;
	}

	public final String getName() {
		return name;
	}

	public final String getCountryName() {
		return countryName;
	}
}
