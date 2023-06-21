import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardingPlane{
    public static void main(String[] args) {
        
        
        ArrayList<Passenger> passengers = new ArrayList<>();
        try
        {
            //DO REPLACE THIS PATH IN YOUR OS
            Scanner sc = new Scanner(new File("tickets.csv"));
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
            ArrangePassenger.sort(passengers);
            

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

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

    public static void sort(ArrayList<Passenger> passengers)
    {
        //we will print out arrival order
        System.out.println("ARIAVAL ORDER");
        System.out.printf("%-4s %-25s %-20s\n","NO", "NAME","TICKET TYPE" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4s %-25s %-20s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().name());
        }
        //start to sort passengers by priority for the boarding order
        passengers.sort((o1, o2) -> o1.getTicketType().compareTo(o2.getTicketType()));
        System.out.println("\n\n");

        //print out the Boarding order
        System.out.println("BOARDING ORDER");
        System.out.printf("%-4s %-25s %-20s\n","NO", "NAME","TICKET TYPE" );
        for (Passenger passenger : passengers)
        {   
            System.out.printf("%-4s %-25s %-20s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().name());
        }
    }





}

