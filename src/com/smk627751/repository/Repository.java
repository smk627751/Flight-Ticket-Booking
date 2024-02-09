package com.smk627751.repository;

import com.smk627751.dto.Flight;
import com.smk627751.dto.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository repo;
    private List<Flight> flights;
    private List<Ticket> tickets;
    private Repository()
    {

    }
    public static Repository getInstance()
    {
        if(repo == null)
        {
            repo = new Repository();
        }
        return repo;
    }
    public void readFlights()
    {
        try {
            FileInputStream fis = new FileInputStream("src/com/smk627751/files/flights.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            flights = (List<Flight>) ois.readObject();
        }
        catch (EOFException e)
        {
            flights = new ArrayList<>();
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeFlights() {
        try {
            FileOutputStream fis = new FileOutputStream("src/com/smk627751/files/flights.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(flights);
            readFlights();
        } catch (IOException e) {
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

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
