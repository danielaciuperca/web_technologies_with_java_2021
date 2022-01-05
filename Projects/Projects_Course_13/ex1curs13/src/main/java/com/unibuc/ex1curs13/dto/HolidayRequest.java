package com.unibuc.ex1curs13.dto;

import javax.validation.constraints.*;

public class HolidayRequest {
    @NotBlank
    private String accommodation;
    @NotNull
    @Min(1)
    private int duration;
    @NotBlank
    private String transportation;
    @NotNull
    @Min(1)
    private double price;
    @NotNull
    private long destinationId;

    public HolidayRequest() {
    }

    public HolidayRequest(String accommodation, int duration, String transportation, double price, long destinationId) {
        this.accommodation = accommodation;
        this.duration = duration;
        this.transportation = transportation;
        this.price = price;
        this.destinationId = destinationId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }
}
