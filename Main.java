import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
            switch (Integer.parseInt(input.nextLine())) {
                case 1:
                    Fcfs.buyticket(input);
                    loadPassenger(passengers);
                    System.out.println("\n\nBelow is the ticket bought by passengers (FCFS)");
                    Fcfs.sort(passengers);
                    break;
                case 2:
                    Priority.sort(passengers);
                    break;
                case 3:
                    ShortestJobNext.lightestLuggageFirst(luggages);;
                    break;
                case 4:
                    cancelTicket(passengers, luggages);
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
    public static void cancelTicket(ArrayList<Passenger> passengers, ArrayList<Luggage> luggages)
    {
        Scanner in = new Scanner(System.in);
        //get ticket id
        System.out.printf("\n\nPassenger ID = ");
        int passengerID = in.nextInt();
        in.nextLine();
      
        //remove the passenger with id
        boolean hasDeletedPassenger = passengers.removeIf(p -> p.getID() == passengerID);
        boolean hasDeletedLuggage = luggages.removeIf(l -> l.getPassengerID() == passengerID);
        if(!hasDeletedPassenger && !hasDeletedLuggage) {
            System.out.printf("Error no passenger and luggage with %d found!", passengerID);
            try
            {
                System.in.read();
            }
            catch (Exception e)
            {
                return;
            }
           
            return;
        }
        //rewrite the file
        try
        {
            FileWriter fw = new FileWriter(new File("Ticket.csv"), false);
            fw.write("ID,Name,TicketType,Seat,Distance");
            for (Passenger passenger : passengers) {
                fw.write("\n" + passenger.getID() + "," + passenger.getName() + "," + passenger.getTicketType().toString() + "," + passenger.getSeat() + "," + passenger.getDistance());
            }
            fw.close();
            fw = new FileWriter(new File("Luggage.csv"), false);
            fw.write("Passenger ID,Luggage ID,Luggage Weight (kg),Luggage Color");
            for (Luggage luggage : luggages) {
                fw.write(String.format("\n%d,%s,%f,%s", luggage.getPassengerID(), luggage.getLuggageId(), luggage.getLuggageWeight(), luggage.getLuggageColor())) ;
            }
            fw.close();

            System.out.println("Successfully deleted!");
            System.in.read();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
       

        
    }
}