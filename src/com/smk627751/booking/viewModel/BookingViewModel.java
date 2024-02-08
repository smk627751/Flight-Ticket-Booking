package com.smk627751.booking.viewModel;

import com.smk627751.booking.view.BookingView;
import com.smk627751.dto.Flight;
import com.smk627751.dto.Passenger;
import com.smk627751.dto.Ticket;
import com.smk627751.flightinfo.view.AddFlightView;
import com.smk627751.flightinfo.viewModel.AddFlightViewModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingViewModel {
    private BookingView bookingView;
    private AddFlightView view;
    private AddFlightViewModel viewModel;
    private List<Ticket> tickets;
    public BookingViewModel(BookingView bookingView)
    {
        this.view = new AddFlightView();
        this.bookingView = bookingView;
        this.viewModel = new AddFlightViewModel(view);
        readTickets();
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
    public void readTickets()
    {
        try {
            FileInputStream fis = new FileInputStream("src/com/smk627751/files/tickets.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            tickets = (List<Ticket>) ois.readObject();
        }
        catch (EOFException e)
        {
            tickets = new ArrayList<>();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeTickets() {
        try {
            FileOutputStream fis = new FileOutputStream("src/com/smk627751/files/tickets.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(tickets);
            readTickets();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Flight> searchFlight(String from, String to)
    {
        return listFlights().stream().filter(flight -> (flight.getRoutes().contains(from) && flight.getRoutes().contains(to))).toList();
    }
    public int booking(String from, String to, Flight flight, List<Passenger> passengers, int total)
    {
        Ticket ticket = new Ticket(flight,from,to,passengers,total);
        tickets.add(ticket);
        writeTickets();
        return ticket.getPNR();
    }
    public Ticket findTicket(int PNRNumber)
    {
        for(Ticket ticket : tickets)
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
        return tickets;
    }
    public int cancelTicket(int PNRNumber)
    {
        Ticket ticket = findTicket(PNRNumber);
        for(Passenger passenger : ticket.getPassengers())
        {
            passenger.setStatus("CANCELLED");
        }
        writeTickets();
        return ticket.getTotal();
    }
    public void searchPassenger()
    {

    }
    public String changeTicketStatus(int choice, int PNRNumber)
    {
        String[] status = new String[]{"CNF","CANCELLED"};
        Ticket ticket = findTicket(PNRNumber);
        for(Passenger passenger : ticket.getPassengers())
        {
            passenger.setStatus(status[choice - 1]);
        }
        writeTickets();
        return status[choice - 1];
    }

    public List<Flight> listFlights()
    {
        return viewModel.getFlights();
    }

    public Passenger findPassenger(int id) {
        for(Ticket ticket : tickets)
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
