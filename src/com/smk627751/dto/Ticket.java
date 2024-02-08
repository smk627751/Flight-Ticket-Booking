package com.smk627751.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int PNR;
    private Flight flight;
    private String from;
    private String to;
    private List<Passenger> passengers;

    private int total;

    public Ticket(Flight flight,String from, String to, List<Passenger> passengers,int total)
    {
        this.PNR = (int) (Math.random() * (9999999));
        this.flight = flight;
        this.from = from;
        this.to = to;
        this.passengers = passengers;
        this.total = total;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Flight Details\n");
        str.append("----------\n");
        str.append("Flight Number : "+flight.getFlightNumber()+"|| Flight Name: "+flight.getFlightName()+"||\n" +
                "Departure Time:"+flight.getDepartureTime()+" || ArrivalTime :"+flight.getArrivalTime()+" || From :"+from+" || To :"+to+" || PNR No :"+PNR+" || Total Fare : "+total+"\n");
        str.append("Passenger Details\n");
        str.append("----------\n");
        for(Passenger passenger : passengers)
        {
            str.append(passenger).append("\n");
        }
        return str.toString();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getPNR() {
        return PNR;
    }

    public int getTotal() {
        return total;
    }
}
