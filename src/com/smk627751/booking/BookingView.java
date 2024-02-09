package com.smk627751.booking;

import com.smk627751.booking.BookingViewModel;
import com.smk627751.dto.Flight;
import com.smk627751.dto.Passenger;
import com.smk627751.dto.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingView {
    private BookingViewModel bookingViewModel;
    private Scanner sc = new Scanner(System.in);
    public BookingView()
    {
        this.bookingViewModel = new BookingViewModel(this);
    }
    public void bookTicket()
    {
        sc.nextLine();
        System.out.println("From Station: ");
        String from = sc.nextLine();
        System.out.println("To Station: ");
        String to = sc.nextLine();
        int index = 1;
        System.out.println("Available flights");
        System.out.println();
        for(Flight flight : bookingViewModel.searchFlight(from,to))
        {
            System.out.println(index++ +". "+flight);
            System.out.println();
        }
        System.out.println("Enter flight number: ");
        int flightNumber = sc.nextInt();
        Flight flight = bookingViewModel.findFlight(flightNumber);
        if(flight.getSeat() == 0)
        {
            System.out.println("No seats available");
            return;
        }
        System.out.println("Enter number of passenger: ");
        int passengerCount = sc.nextInt();
        if(flight.getSeat() < passengerCount)
        {
            System.out.println("Not enough seats");
            return;
        }
        List<Passenger> passengers = new ArrayList<>();
        for(int i = 1; i <= passengerCount; i++)
        {
            System.out.println("Enter Passenger "+i+" details:");
            System.out.println("Name: ");
            String name = sc.nextLine();
            name += sc.nextLine();
            System.out.println("Age: ");
            byte age = sc.nextByte();
            sc.nextLine();
            System.out.println("Gender: ");
            String gender = sc.nextLine();
            System.out.println("ID: ");
            int id = sc.nextInt();
            passengers.add(new Passenger(name,age,gender,id));
        }
        int total = flight.getFare() * passengerCount;
        System.out.println("Total Fare: "+total);
        System.out.println("Pay: ");
        System.out.println("1. Pay\n2. Cancel\n3. Reschedule");
        int payment = sc.nextInt();
        switch (payment)
        {
            case 1 ->{
                int PNRNumber = bookingViewModel.booking(from,to,flight,passengers,passengerCount,total);
                System.out.println("Ticket(s) booked successfully");
                System.out.println("Your PNRNumber: "+PNRNumber);
            }
            case 3 ->{

            }
        }
    }
    public void bookedTickets()
    {
        System.out.println("Total Tickets booked : "+bookingViewModel.bookedTickets().size());
        System.out.println();
        int index = 1;
        for(Ticket ticket : bookingViewModel.bookedTickets())
        {
            System.out.println("Ticket details "+index++);
            System.out.println(ticket);
            System.out.println();
        }
    }

    public void getPNRStatus()
    {
        System.out.println("Enter the PNR Number: ");
        int PNRNumber = sc.nextInt();
        Ticket ticket = bookingViewModel.findTicket(PNRNumber);
        System.out.println(ticket);
    }

    public void cancelTicket() {
        System.out.println("Enter the PNR Number: ");
        int PNRNumber = sc.nextInt();
        sc.nextLine();
        System.out.println("Do you want to cancel the ticket? :");
        String yesOrNo = sc.nextLine();
        if(yesOrNo.equals("Yes"))
        {
            int total = bookingViewModel.cancelTicket(PNRNumber);
            System.out.println("Ticket Cancelled Successfully.Your refund "+total+" will be processed soon.");
        }
    }

    public void changeTicketStatus() {
        System.out.println("Enter the PNR Number: ");
        int PNRNumber = sc.nextInt();
        System.out.println("1.CNF\n2.CANCEL");
        int choice = sc.nextInt();
        if(choice > 2 || choice <= 0)
        {
            System.out.println("Invalid choice");
            return;
        }
        String status = bookingViewModel.changeTicketStatus(choice,PNRNumber);
        System.out.println("Ticket status updated as \""+status+"\"");
    }

    public void searchPassenger() {
        System.out.println("Enter Passenger ID:");
        int id = sc.nextInt();
        Passenger passenger = bookingViewModel.findPassenger(id);
        System.out.println("Passenger details: ");
        System.out.println(passenger);
    }

    public void searchFlight() {
        System.out.println("From Station: ");
        String from = sc.nextLine();
        System.out.println("To Station: ");
        String to = sc.nextLine();
        for(Flight flight : bookingViewModel.searchFlight(from,to))
        {
            System.out.println(flight);
        }
    }
}
