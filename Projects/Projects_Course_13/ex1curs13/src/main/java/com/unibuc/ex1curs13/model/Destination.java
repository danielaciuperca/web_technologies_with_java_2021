package com.unibuc.ex1curs13.model;

public class Destination {
	private long id;
	private String name;
	private String country;
	private DestinationType destinationType;

	public Destination() {
	}

	public Destination(String name, String country, DestinationType destinationType) {
		this.name = name;
		this.country = country;
		this.destinationType = destinationType;
	}

	public Destination(long id, String name, String country, DestinationType destinationType) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.destinationType = destinationType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public DestinationType getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(DestinationType destinationType) {
		this.destinationType = destinationType;
	}

	@Override
	public String toString() {
		return "Destination: " +
				"name=" + name +
				", country=" + country +
				", destinationType=" + destinationType;
	}
}
