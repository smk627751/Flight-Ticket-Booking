package com.smk627751.view;

import com.smk627751.booking.BookingView;
import com.smk627751.dto.Flight;
import com.smk627751.flightinfo.view.AddFlightView;

import java.util.Scanner;

public class View {
    private BookingView bookingView;
    private AddFlightView addFlightView;
    public View()
    {
        this.bookingView = new BookingView();
        this.addFlightView = new AddFlightView();
    }
    public void start()
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Flight Ticket Booking");
            System.out.println("1. Booking\n2. Get PNR status\n3. Booked tickets\n4. Cancel Tickets\n5. Search " +
                    "passenger\n6. Change ticket status of a passenger\n7. List flight routes\n8. Add flight " +
                    "routes\n0. Exit");
            System.out.println("Enter your option:");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1 ->{
                    bookingView.bookTicket();
                }
                case 2 ->{
                    bookingView.getPNRStatus();
                }
                case 3 -> {
                    bookingView.bookedTickets();
                }
                case 4 -> {
                    bookingView.cancelTicket();
                }
                case 5 -> {
                    bookingView.searchPassenger();
                }
                case 6 -> {
                    bookingView.changeTicketStatus();
                }
                case 7 -> {
                    System.out.println("Flight Details");
                    System.out.println();
                    int index = 1;
                    for(Flight flight : addFlightView.getFlights())
                    {
                        System.out.println(index++ +". "+flight);
                        System.out.println();
                    }
                }
                case 8 -> {
                    addFlightView.addFlight();
                }
                case 0 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice");
                }
            }
        }while(true);
    }
}
