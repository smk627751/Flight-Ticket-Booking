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
            System.out.println("1. Booking\n2. Get PNR status\n3. Search Flight from and to\n4. Booked tickets\n5. Cancel Tickets\n6. Search " +
                    "passenger\n7. Change ticket status of a passenger\n8. List flight routes\n9. Add flight " +
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
                case 3 ->{
                    bookingView.searchFlight();
                }
                case 4 -> {
                    bookingView.bookedTickets();
                }
                case 5 -> {
                    bookingView.cancelTicket();
                }
                case 6 -> {
                    bookingView.searchPassenger();
                }
                case 7 -> {
                    bookingView.changeTicketStatus();
                }
                case 8 -> {
                    System.out.println("Flight Details");
                    System.out.println();
                    int index = 1;
                    for(Flight flight : addFlightView.getFlights())
                    {
                        System.out.println(index++ +". "+flight);
                        System.out.println();
                    }
                }
                case 9 -> {
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
