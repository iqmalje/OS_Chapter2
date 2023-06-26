package Scheduling;

import java.util.ArrayList;
import java.util.Collections;

import Entities.Passenger;

public class Fcfs {   

    public static void sort(ArrayList<Passenger> passengers){
        System.out.println("\n\n");

        Collections.shuffle(passengers);
        //print out the oder the tickets were bought
        System.out.println("TICKET BUYING ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n","NO", "NAME","TICKET TYPE", "SEAT" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().toString(), passenger.getSeat());
        }
    }    
}
