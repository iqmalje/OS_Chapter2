package Scheduling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Entities.Luggage;
import Entities.Passenger;
import Entities.TicketType;

public class Fcfs {

    ArrayList<Passenger> passengersBuy = new ArrayList<>();
    ArrayList<Luggage> luggages = new ArrayList<>();
    private int getLastID()
    {
        try
        {
            Scanner sc = new Scanner(new File("Ticket.csv"));
            int id = 0;
            sc.nextLine(); // SKIPS HEADER
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] columns = line.split(",");
                id = Integer.parseInt(columns[0]);
            }
            return id;
        }
        catch (Exception e){
            return -1;
        }
       
    }
    
    public void buyTicket()
    {
        Scanner in = new Scanner(System.in);
        System.out.printf("How many passengers are there : ");
        int passengersQueueAmount = in.nextInt();
        in.nextLine();
        int counter = 0;
        int startingID = getLastID()+1;

        while(counter < passengersQueueAmount)
        {
            System.out.printf("\n\n\n\n\n");
            //1,John Doe,FIRST_CLASS,A1,1
            System.out.printf("Passenger name : ");
            String name = in.nextLine();
            System.out.printf("Ticket type : \n\t[1] FIRST CLASS\n\t[2] BUSINESS CLASS\n\t[3] PREMIUM ECONOMY\n\t[4] ECONOMY\n");
            System.out.print("Enter passenger ticket type = ");
            int ticketType = in.nextInt();
            in.nextLine();
            TicketType tt = TicketType.BUSINESS_CLASS;
            switch(ticketType)
            {
                case 1:
                tt = TicketType.FIRST_CLASS;
                break;
                case 2:
                tt = TicketType.BUSINESS_CLASS;
                break;
                case 3:
                tt = TicketType.PREMIUM_ECONOMY;
                break;
                case 4:
                tt = TicketType.ECONOMY;
                break;
            }
            System.out.print("Seat number : ");
            String seatnumber = in.nextLine();
            System.out.printf("Distance : ");
            int distance = in.nextInt();
            in.nextLine();

            //ask for luggage 
            System.out.print("Luggage ID : ");
            String luggageID = in.nextLine();
            System.out.print("Luggage weight : ");
            float weight = in.nextFloat();
            in.nextLine();
            System.out.print("Luggage color : ");
            String color = in.nextLine();


            passengersBuy.add(new Passenger(startingID, name, tt, seatnumber, distance));
            luggages.add(new Luggage(startingID, luggageID, weight, color));
            startingID++;

            counter++;
        }

        //now append to tickets.csv
        try{
            FileWriter fw = new FileWriter(new File("Ticket.csv"), true);
            BufferedWriter br = new BufferedWriter(fw);
            for (Passenger passenger : passengersBuy) {
                br.write("\n" + passenger.getID() + "," + passenger.getName() + "," + passenger.getTicketType().toString() + "," + passenger.getSeat() + "," + passenger.getDistance()) ;
            }
            br.close();
            fw.close();
            System.out.println("Successfully appended passenger!");
            System.out.println("Now appending luggages");

            fw = new FileWriter(new File("Luggage.csv"), true);
            br = new BufferedWriter(fw);
            //Passenger ID,Luggage ID,Luggage Weight (kg),Luggage Color
            for (int i = 0; i < luggages.size(); i++) {
                var luggage = luggages.get(i);
                br.write(String.format("\n%d,%s,%f,%s", passengersBuy.get(i).getID(), luggage.getLuggageId(), luggage.getLuggageWeight(), luggage.getLuggageColor())) ;
            }
            System.out.println("Successfully appended luggages!");
            br.close();
            fw.close();

            
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        
    }
    public static void sort(ArrayList<Passenger> passengers) {
        System.out.println("\n\n");

        System.out.println("ARRIVAL ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : passengers) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
        
    }
}
