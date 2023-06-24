package Scheduling;

import java.util.ArrayList;

import Entities.Passenger;

public class Priority {
    public static void sort(ArrayList<Passenger> passengers){
        
        //start to sort passengers by priority for the boarding order
        passengers.sort((o1, o2) -> o1.getTicketType().compareTo(o2.getTicketType()));
        System.out.println("\n\n");

        //print out the Boarding order
        System.out.println("BOARDING ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n","NO", "NAME","TICKET TYPE", "SEAT" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4d %-25s %-20s %-4s\n", passenger.getID() ,passenger.getName(), passenger.getTicketType().toString(), passenger.getSeat());
        }
    }
}
