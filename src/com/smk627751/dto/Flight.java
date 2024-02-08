package com.smk627751.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Flight implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int flightNumber;
    private String flightName;
    private int departureTime;
    private int arrivalTime;
    private List<String> routes;
    private int seats;
    private int fare;

    public Flight(int flightNumber,String flightName,int departureTime,int arrivalTime,List<String> routes,int seats,int fare)
    {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.routes = routes;
        this.seats = seats;
        this.fare = fare;
    }
    public int getFlightNumber()
    {
        return flightNumber;
    }
    public List<String> getRoutes()
    {
        return routes;
    }
    public String toString()
    {
        return "Flight no : "+flightNumber+" || name : "+flightName+" || Departure Time:"+ departureTime +" || Arrival Time:"+arrivalTime+"\n" +
                "|| Travel Time :10 || Fare : "+fare+" || seats : "+seats;
    }

    public int getFare() {
        return fare;
    }

    public String getFlightName() {
        return flightName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }
}
