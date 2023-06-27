package Scheduling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Entities.Passenger;
import Entities.TicketType;

public class Fcfs {

    ArrayList<Passenger> passengersBuy = new ArrayList<>();
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
            System.out.println(id);
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

            passengersBuy.add(new Passenger(startingID, name, tt, seatnumber, distance));
            startingID++;
            counter++;
            System.out.println(startingID);
        }

        //now append to tickets.csv
        try{
            FileWriter fw = new FileWriter(new File("Ticket.csv"), true);
            BufferedWriter br = new BufferedWriter(fw);
            for (Passenger passenger : passengersBuy) {
                br.write("\n" + passenger.getID() + "," + passenger.getName() + "," + passenger.getTicketType().toString() + "," + passenger.getSeat() + "," + passenger.getDistance() + "\n") ;
            }
            br.close();
            fw.close();
            System.out.println("Successfully appended!");
            
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        
    }
    public static void sort(ArrayList<Passenger> passengers) {
        System.out.println("\n\n");

        Collections.shuffle(passengers);
        System.out.println("ARRIVAL ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : passengers) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
        // print out the oder the tickets were bought
        System.out.println("BOARDING ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : passengers) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
    }
}
