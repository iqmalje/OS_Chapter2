package Scheduling;

import java.util.ArrayList;

import Entities.Luggage;
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

    //sort based on weight
    public static void lightestLuggageFirst(ArrayList<Luggage> luggages)
    {
        luggages.sort((l1, l2) -> Float.compare(l1.getLuggageWeight(), l2.getLuggageWeight()));
        System.out.println("\n\n");

        //print out the Boarding order
        System.out.println("LUGGAGE BOARDING ORDER");
        //Passenger ID,Luggage ID,Luggage Weight (kg),Luggage Color
        System.out.printf("%-15s %-15s %-15s %-9s\n","PASSENGER ID", "LUGGAGE ID","LUGGAGE WEIGHT (KG)", "COLOR" );
        for (Luggage luggage : luggages)
        {   
            System.out.printf("%-15d %-15s %-15.2f %-9s\n", luggage.getPassengerID(), luggage.getLuggageId(), luggage.getLuggageWeight(), luggage.getLuggageColor());
        }

        try {
            System.in.read();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
