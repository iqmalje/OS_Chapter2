package Scheduling;

import java.util.ArrayList;
import java.util.Collections;

import Entities.Passenger;

public class Fcfs {

    public static void sort(ArrayList<Passenger> passengers) {
        System.out.println("\n\n");
        // shuffel the passengers to emulate when they may arrive at the gate
        Collections.shuffle(passengers);
        // print this out
        System.out.println("ARRIVAL ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : passengers) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
        // print out the order in which the arrive
        System.out.println("BOARDING ORDER");
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : passengers) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
    }
}
