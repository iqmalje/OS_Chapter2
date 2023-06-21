
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.xml.*;

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
                
                
                String string1 = columns[2];
                var dateBought = OffsetDateTime.parse( string1 );


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

                Passenger p = new Passenger(columns[1], ticketType, dateBought);
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
    private OffsetDateTime boughtAt;

    Passenger(String name, TicketType ticketType, OffsetDateTime boughtAt)
    {
        this.name = name;
        this.ticketType = ticketType;
        this.boughtAt = boughtAt;
    }


    String getName() {return name;}

    TicketType getTicketType() {return ticketType;}

    OffsetDateTime getBoughtAt() { return boughtAt; }

    
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
        //FIRST we sort by dates, as the same priority will proritize who came first
        passengers.sort((o1, o2) -> o1.getBoughtAt().compareTo(o2.getBoughtAt()));
        //start to sort passengers by TICKET TYPE
        passengers.sort((o1, o2) -> o1.getTicketType().compareTo(o2.getTicketType()));

        for(int i = 0; i < passengers.size(); i++)
        {
            queue.add(passengers.get(i));
        }
        System.out.println("BOARDING PRIORITY");
        for (Passenger passenger : queue)
        {
            OffsetDateTime boughtAt = passenger.getBoughtAt();
            
            System.out.printf("NAME = %s, Ticket type = %s, Bought At = %d/%d/%d %02d:%02d %s\n", passenger.getName(), passenger.getTicketType().name(), boughtAt.getDayOfMonth(), boughtAt.getMonthValue(), boughtAt.getYear(), boughtAt.getHour(), boughtAt.getMinute(), boughtAt.getHour() < 12 ? "AM" : "PM");
        }
    }





}

