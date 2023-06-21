
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class BoardingPlane{
    public static void main(String[] args) {
        
        
        ArrayList<Passenger> passengers = new ArrayList<>();
        try
        {
            //DO REPLACE THIS PATH IN YOUR OS
            Scanner sc = new Scanner(new File("/Users/iqmalaizat/Documents/college files/OS/OS_Chapter2/tickets.csv"));
            sc.nextLine(); //SKIPS COLUMN HEADER
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] columns = line.split(",");
                TicketType ticketType = TicketType.BUSINESS_CLASS; // FOR INITIALIZATION
                switch(columns[3])
                {
                    case "FIRST_CLASS":
                    ticketType = TicketType.FIRST_CLASS;
                    break;
                    case "BUSINESS_CLASS":
                    ticketType = TicketType.BUSINESS_CLASS;
                    break;
                    case "ECONOMY_PLUS":
                    ticketType = TicketType.PREMIUM_ECONOMY;
                    break;
                    case "ECONOMY":
                    ticketType = TicketType.ECONOMY;
                    break;
                    
                }

                Passenger p = new Passenger(columns[1], ticketType);
                passengers.add(p);
            }
            ArrangePassenger ap = new ArrangePassenger(passengers);
            

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

        /*
        ;

        passengers.add(new Passenger("Iqmal", TicketType.FIRST_CLASS));
        
        passengers.add(new Passenger("Bin", TicketType.ECONOMY));
        passengers.add(new Passenger("Zamri", TicketType.BUSINESS_CLASS));
        passengers.add(new Passenger("Aizat", TicketType.FIRST_CLASS));

        ArrangePassenger ap = new ArrangePassenger(passengers);
        */
        

    }
}
enum TicketType{
    FIRST_CLASS,
    BUSINESS_CLASS,
    PREMIUM_ECONOMY,
    ECONOMY
}
//priority

class Passenger{
    private String name;
    private TicketType ticketType;

    Passenger(String name, TicketType ticketType)
    {
        this.name = name;
        this.ticketType = ticketType;
    }


    String getName() {return name;}

    TicketType getTicketType() {return ticketType;}

    
}

class ArrangePassenger{
    //For this method we will use priority + FCFS

    ArrayList<Passenger> passengers = new ArrayList<>();
    ArrayList<Passenger> queue = new ArrayList<>();

    ArrangePassenger(ArrayList<Passenger> passengers)
    {
        this.passengers = passengers;
        sort();
        
    }

    void sort()
    {
        //we will print out the boarding order
        for (Passenger passenger : passengers)
        {
            System.out.printf("NAME = %s, Ticket type = %s\n", passenger.getName(), passenger.getTicketType().name());
        }
        //start to sort passengers by priority
        passengers.sort((o1, o2) -> o1.getTicketType().compareTo(o2.getTicketType()));

        for(int i = 0; i < passengers.size(); i++)
        {
            queue.add(passengers.get(i));
        }
        System.out.println("AFTER SORT");
        for (Passenger passenger : queue)
        {
            System.out.printf("NAME = %s, Ticket type = %s\n", passenger.getName(), passenger.getTicketType().name());
        }
    }





}

