package Scheduling;

import java.util.ArrayList;


import Entities.Passenger;

public class ShortestJobNext {
    public static void sort(ArrayList<Passenger> passengers){
        
        // sort by distance to the seat
        passengers.sort((o1, o2) -> ((Integer)o1.getDistance()).compareTo((Integer)o2.getDistance()));
        System.out.println("\n\n");

        //print out the Boarding order
        System.out.println("BOARDING ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n","NO", "NAME","TICKET TYPE", "SEAT" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().toString(), passenger.getSeat());
        }
    }
}
