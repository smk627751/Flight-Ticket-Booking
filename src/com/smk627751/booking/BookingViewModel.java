package com.smk627751.booking;

import com.smk627751.dto.Flight;
import com.smk627751.dto.Passenger;
import com.smk627751.dto.Ticket;
import com.smk627751.repository.Repository;

import java.util.List;

public class BookingViewModel {
    private BookingView bookingView;
    private Repository repo;

    public BookingViewModel(BookingView bookingView)
    {
        this.bookingView = bookingView;
        this.repo = Repository.getInstance();
        repo.readTickets();
    }
    public Flight findFlight(int flightNumber)
    {
        for(Flight flight : listFlights())
        {
            if(flight.getFlightNumber() == flightNumber)
            {
                return flight;
            }
        }
        return null;
    }
    public List<Flight> searchFlight(String from, String to)
    {
        return listFlights().stream().filter(flight -> (flight.getRoutes().contains(from) && flight.getRoutes().contains(to))).toList();
    }
    public int booking(String from, String to, Flight flight, List<Passenger> passengers, int passengerCount, int total)
    {
        flight.setSeat(flight.getSeat() - passengerCount);
        Ticket ticket = new Ticket(flight,from,to,passengers,total);
        repo.getTickets().add(ticket);
        repo.writeFlights();
        repo.writeTickets();
        return ticket.getPNR();
    }
    public Ticket findTicket(int PNRNumber)
    {
        for(Ticket ticket : repo.getTickets())
        {
            if(ticket.getPNR() == PNRNumber)
            {
                return ticket;
            }
        }
        return null;
    }
    public List<Ticket> bookedTickets()
    {
        return repo.getTickets();
    }
    public int cancelTicket(int PNRNumber)
    {
        Ticket ticket = findTicket(PNRNumber);
        Flight flight = findFlight(ticket.getFlight().getFlightNumber());
        flight.setSeat(flight.getSeat() + ticket.getPassengers().size());
        for(Passenger passenger : ticket.getPassengers())
        {
            passenger.setStatus("CANCELLED");
        }
        repo.writeFlights();
        repo.writeTickets();
        return ticket.getTotal();
    }
    public String changeTicketStatus(int choice, int PNRNumber)
    {
        String[] status = new String[]{"CNF","CANCELLED"};
        Ticket ticket = findTicket(PNRNumber);
        for(Passenger passenger : ticket.getPassengers())
        {
            if(status[choice - 1].equals("CNF") && !passenger.getStatus().equals("CNF"))
            {
                Flight flight = findFlight(ticket.getFlight().getFlightNumber());
                flight.setSeat(flight.getSeat() - ticket.getPassengers().size());
            }
            else if(status[choice - 1].equals("CANCELLED") && !passenger.getStatus().equals("CANCELLED"))
            {
                Flight flight = findFlight(ticket.getFlight().getFlightNumber());
                flight.setSeat(flight.getSeat() + ticket.getPassengers().size());
            }
            passenger.setStatus(status[choice - 1]);
        }
        repo.writeFlights();
        repo.writeTickets();
        return status[choice - 1];
    }

    public List<Flight> listFlights()
    {
        return repo.getFlights();
    }

    public Passenger findPassenger(int id) {
        for(Ticket ticket : repo.getTickets())
        {
            for(Passenger passenger : ticket.getPassengers())
            {
                if(passenger.getId() == id)
                {
                    return passenger;
                }
            }
        }
        return null;
    }
}
