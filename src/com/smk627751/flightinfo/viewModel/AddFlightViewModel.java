package com.smk627751.flightinfo.viewModel;

import com.smk627751.dto.Flight;
import com.smk627751.flightinfo.view.AddFlightView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddFlightViewModel {
    private AddFlightView view;
    private List<Flight> flights;
    public AddFlightViewModel(AddFlightView view)
    {
        this.view = view;
        readFlights();
    }

    public AddFlightViewModel() {
        readFlights();
    }
    public void readFlights()
    {
        try {
            FileInputStream fis = new FileInputStream("src/com/smk627751/files/flights.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            flights = (List<Flight>) ois.readObject();
//            System.out.println(flights);
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
    public List<Flight> getFlights() {
        readFlights();
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
