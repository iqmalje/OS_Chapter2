import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.Luggage;
import Entities.Passenger;
import Entities.TicketType;
import Scheduling.*;


public class Main {
    public static void main(String[] args) {
        while(true)
        {
            ArrayList<Passenger> passengers = new ArrayList<>();
            ArrayList<Luggage> luggages = new ArrayList<>();
            loadPassenger(passengers);
            loadLuggages(luggages);
            System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.printf("Welcome to MAS Airplane Boarding and Buying System! Please pick an option below\n");
            System.out.println("[1] Buy tickets for passengers (FCFS)");
            System.out.println("[2] Boarding List for Passengers (PRIORITY)");
            System.out.println("[3] Boarding List for Luggages (SJF)");
            System.out.println("[4] Ticket Cancellation");
            System.out.printf("\nEnter your options : ");
            Scanner input = new Scanner(System.in);
            Fcfs fcfs = new Fcfs();
            switch (Integer.parseInt(input.nextLine())) {
                case 1:
                    fcfs.buyTicket();
                    break;
                case 2:
                    Priority.sort(passengers);
                    break;
                case 3:
                    ShortestJobNext.lightestLuggageFirst(luggages);;
                    break;
                case 4:
                    BuyTicketFCFS.buyticket();
                    passengers.clear();
                    loadPassenger(passengers);
                    print(passengers);
                    break;
                default:
                    System.out.println("Wrong input, please try again");
                    break;
            }
        }
       
        
    }

    public static void print(ArrayList<Passenger> passengers){
        //we will print out arrival order
        System.out.println("ARIAVAL ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n","NO", "NAME","TICKET TYPE", "SEAT" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().toString(), passenger.getSeat());
        }
    }

    public static void loadPassenger(ArrayList<Passenger> passengers){
        try{
            //DO REPLACE THIS PATH IN YOUR OS
            Scanner sc = new Scanner(new File("Ticket.csv"));
            sc.nextLine(); //SKIPS COLUMN HEADER
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] columns = line.split(",");
                TicketType ticketType = TicketType.BUSINESS_CLASS; // FOR INITIALIZATION
                switch(columns[2])
                {
                    case "FIRST_CLASS":
                    ticketType = TicketType.FIRST_CLASS;
                    break;
                    case "BUSINESS_CLASS":
                    ticketType = TicketType.BUSINESS_CLASS;
                    break;
                    case "PREMIUM_ECONOMY":
                    ticketType = TicketType.PREMIUM_ECONOMY;
                    break;
                    case "ECONOMY":
                    ticketType = TicketType.ECONOMY;
                    break;
                    
                }
                Passenger p = new Passenger(Integer.parseInt(columns[0]),columns[1], ticketType, columns[3], Integer.parseInt(columns[4]) );
                passengers.add(p);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public static void loadLuggages(ArrayList<Luggage> luggages)
    {
        try{
            //DO REPLACE THIS PATH IN YOUR OS
            Scanner sc = new Scanner(new File("Luggage.csv"));
            sc.nextLine(); //SKIPS COLUMN HEADER
            while(sc.hasNextLine())
            {
                
                String line = sc.nextLine();
                String[] columns = line.split(",");
                //Passenger ID,Luggage ID,Luggage Weight (kg),Luggage Color
                luggages.add(new Luggage(Integer.parseInt(columns[0]), (columns[1]), Float.parseFloat(columns[2]), columns[3]));
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}