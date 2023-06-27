import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Entities.Passenger;
import Entities.TicketType;

public class BuyTicketFCFS {
    public static String getrandseat() {
        Random rand = new Random();
        // generate a random number between 1 - 38
        int seatnum = rand.nextInt(38 - 1) + 1;
        // generate a random char between A - E
        char seatchar = (char) (rand.nextInt(6) + 'A');
        // combine the seatchar,seatnum to produce a seat
        String seat = seatchar + Integer.toString(seatnum);
        // return the value
        return seat;
    }

    public static void buyticket() {
        // list of names for random name generation
        String[] names = {
            "Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Isabella", "Jack",
            "Kate", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Ruby", "Samuel", "Taylor",
            "Uma", "Victor", "Wendy", "Xavier", "Yara", "Zoe", "Andrew", "Bella", "Caleb", "Daisy",
            "Ethan", "Faith", "George", "Hannah", "Isaac", "Julia", "Kevin", "Lily", "Mason", "Nora",
            "Oscar", "Penelope", "Quentin", "Rachel", "Simon", "Sophia", "Tristan", "Violet", "Wyatt",
            "Xander", "Yasmine", "Zachary", "Abigail", "Benjamin", "Charlotte", "Daniel", "Evelyn",
            "Fiona", "Gabriel", "Harper", "Ian", "Jasmine", "Kai", "Luna", "Matthew", "Nathan", "Oliver",
            "Piper", "Quincy", "Rose", "Sebastian", "Thea", "Vincent", "Willow", "Xavier", "Yara",
            "Zara", "Adam", "Beth", "Cameron", "Diana", "Elijah", "Felicity", "Gavin", "Holly", "Isaiah",
            "Jade", "Kyle", "Lauren", "Michael", "Natalie", "Owen", "Paige", "Quinn", "Rebecca",
            "Samuel", "Tara", "Tyler", "Victoria", "Wesley", "Xena", "Yvette", "Zachariah", "Alexa",
            "Brandon", "Chloe", "Dylan", "Emily", "Finn", "Grace", "Hayden", "Ivy", "Jacob", "Jessica",
            "Kevin", "Kylie", "Logan", "Madison", "Nathan", "Nora", "Oliver", "Olivia", "Parker",
            "Penelope", "Quentin", "Quinn", "Ryan", "Ruby", "Samantha", "Samuel", "Trinity", "Uma",
            "Victoria", "Wyatt", "Willow", "Xavier", "Xena", "Yara", "Yasmine", "Zachary", "Zoe",
            "Aaron", "Brianna", "Caleb", "Danielle", "Ethan", "Emma", "Gavin", "Gabriella", "Henry",
            "Hailey", "Isaac", "Isabella", "Jack", "Jasmine", "Kai", "Katherine", "Liam", "Lily",
            "Mason", "Maya", "Nathan", "Natalie", "Owen", "Olivia", "Parker", "Peyton", "Quinn",
            "Rebecca", "Samuel", "Sophia", "Tristan", "Taylor", "Victor", "Violet", "Wyatt", "Willow",
            "Xavier", "Ximena", "Yara", "Yasmine", "Zachary", "Zoe"
        };
        Random random = new Random();
        // load the values of the ticket type
        TicketType[] types = TicketType.values();
        int lastid = 0;
        // store all the seats already taken
        ArrayList<String> seats = new ArrayList<>();
        // store the new passengers as they buy the tickets
        ArrayList<Passenger> queue = new ArrayList<Passenger>(10);
        try {
            // load the passanges from the csv 
            Scanner reader = new Scanner(new File("Ticket.csv"));
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] columns = line.split(",");
                lastid = Integer.parseInt(columns[0]);
                seats.add(columns[3]);
            }
            reader.close();
            // generate 10 new passsagners and store in queue 
            for (int i = 0; i < 10; i++) {
                // generate a random seat
                String seat = getrandseat();
                // check if seat is already taken
                while (seats.contains(seat)) {
                    seat = getrandseat();
                }
                // evaluate the class/priority for the seat
                int num = Integer.parseInt(seat.substring(1)); 
                int index = 0;
                if(num >= 5 && num <= 10){
                    index = 1;
                }else if(num >= 11 && num <= 21){
                    index=2;
                }else{
                    index = 3;
                }
                // store all the generated vlaues one by one on to the queue
                queue.add(new Passenger(lastid+1+ i, names[random.nextInt(183)], types[index],seat,num));
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            // write the passangers in queue in the order they were created
        FileWriter writer = new FileWriter("Ticket.csv",true);
        writer.write("\n");
        for(Passenger x: queue){
        writer.write(x.getID()+","+x.getName()+","+x.getTicketType()+","+x.getSeat()+","+x.getDistance());
        if(queue.indexOf(x) < 9){
            writer.write("\n");
        }
        }
        writer.close();
        } catch (Exception e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}
