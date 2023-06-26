import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.Luggage;
import Entities.Passenger;
import Entities.TicketType;
import Scheduling.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        ArrayList<Luggage> luggages = new ArrayList<>();
        loadPassenger(passengers);
        loadLuggages(luggages);

        System.out.print("Enter [1] FCFS [2] Priority [3] SJN [4] Buy Ticket FCFS:");
        Scanner input = new Scanner(System.in);
        switch (Integer.parseInt(input.nextLine())) {
            case 1:
                Fcfs.sort(passengers);
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
                System.out.println("UwU daddy piwck somwwnethuing bewter next time 0w0");
                break;
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