package com.smk627751.flightinfo.view;

import com.smk627751.dto.Flight;
import com.smk627751.flightinfo.viewModel.AddFlightViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddFlightView {
    private AddFlightViewModel viewModel;
    public AddFlightView()
    {
        this.viewModel = new AddFlightViewModel(this);
    }
    public void addFlight()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("No of schedules : ");
        int n = sc.nextInt();
        for(int i = 1; i <= n; i++)
        {
            System.out.println("Schedule "+i+":");
            System.out.println("Flight number: ");
            int number = sc.nextInt();
            sc.nextLine();
            System.out.println("Flight name: ");
            String name = sc.nextLine();
            sc.nextLine();
            System.out.println("Flight departure Time: ");
            int departureTime = sc.nextInt();
            System.out.println("Flight Arrival time :");
            int arrivalTime = sc.nextInt();
            System.out.println("seats :");
            int seats = sc.nextInt();
            sc.nextLine();
            System.out.println("Flight routes: ");
            List<String> routes = Arrays.stream(sc.nextLine().split(",")).toList();
            System.out.println("Fare");
            int fare = sc.nextInt();
            viewModel.getFlights().add(new Flight(number,name,departureTime,arrivalTime,routes,seats,fare));
            viewModel.writeFlights();
        }
    }
    public List<Flight> getFlights()
    {
        return viewModel.getFlights();
    }
}
