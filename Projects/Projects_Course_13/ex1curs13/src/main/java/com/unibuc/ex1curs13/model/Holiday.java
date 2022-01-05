package com.unibuc.ex1curs13.model;

public class Holiday {
	private long id;
	private String accommodation;
	private int duration;
	private String transportation;
	private double price;
	private long destinationId;

	public Holiday() {
	}

	public Holiday(String accommodation, int duration, String transportation, double price, long destinationId) {
		this.accommodation = accommodation;
		this.duration = duration;
		this.transportation = transportation;
		this.price = price;
		this.destinationId = destinationId;
	}

	public Holiday(long id, String accommodation, int duration, String transportation, double price, long destinationId) {
		this.id = id;
		this.accommodation = accommodation;
		this.duration = duration;
		this.transportation = transportation;
		this.price = price;
		this.destinationId = destinationId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(long destinationId) {
		this.destinationId = destinationId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id=" + id +
				", accommodation=" + accommodation +
				", duration=" + duration +
				", transportation=" + transportation +
				", price=" + price;
	}
}
