package Scheduling;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Entities.Passenger;
import Entities.TicketType;

public class Fcfs {
    public static void sort(ArrayList<Passenger> passengers){
    //print out the Boarding order
    System.out.println("BOARDING ORDER");
    System.out.printf("%-4s %-25s %-20s %-4s\n","NO", "NAME","TICKET TYPE", "SEAT" );
    for (Passenger passenger : passengers)
    {   
        System.out.printf("%-4s %-25s %-20s %-4s\n", passengers.indexOf(passenger)+1 ,passenger.getName(), passenger.getTicketType().toString(), passenger.getSeat());
    }
    }

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

    public static void buyticket(Scanner in) {
        // list of names for random name generation
        System.out.print("Enter the number of passenger:");
        int num = Integer.parseInt(in.nextLine());
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.print("Passenger ["+ i +"] :");
            names.add(in.nextLine());
        }
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
            for (int i = 0; i < num; i++) {
                // generate a random seat
                String seat = getrandseat();
                // check if seat is already taken
                while (seats.contains(seat)) {
                    seat = getrandseat();
                }
                // evaluate the class/priority for the seat
                int snum = Integer.parseInt(seat.substring(1));
                int index = 0;
                if (snum >= 5 && snum <= 10) {
                    index = 1;
                } else if (snum >= 11 && snum <= 21) {
                    index = 2;
                } else {
                    index = 3;
                }
                // store all the generated vlaues one by one on to the queue
                queue.add(new Passenger(lastid + 1 + i, names.get(i), types[index], seat, snum));

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            // write the passangers in queue in the order they were created
            FileWriter writer = new FileWriter("Ticket.csv", true);
            writer.write("\n");
            for (Passenger x : queue) {
                writer.write(x.getID() + "," + x.getName() + "," + x.getTicketType() + "," + x.getSeat() + ","
                        + x.getDistance());
                if (queue.indexOf(x) < num-1) {
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("TICKET BUYING ORDER: " + num +"new passenger bought tickets in the order they were in the queue");
        System.out.println();
        System.out.printf("%-4s %-25s %-20s %-4s\n", "NO", "NAME", "TICKET TYPE", "SEAT");
        for (Passenger passenger : queue) {
            System.out.printf("%-4s %-25s %-20s %-4s\n", queue.indexOf(passenger) + 1, passenger.getName(),
                    passenger.getTicketType().toString(), passenger.getSeat());
        }
    }
}

/*
 * String[] firstnames = {
 * "Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry",
 * "Isabella", "Jack",
 * "Kate", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Ruby", "Samuel",
 * "Taylor",
 * "Uma", "Victor", "Wendy", "Xavier", "Yara", "Zoe", "Andrew", "Bella",
 * "Caleb", "Daisy",
 * "Ethan", "Faith", "George", "Hannah", "Isaac", "Julia", "Kevin", "Lily",
 * "Mason", "Nora",
 * "Oscar", "Penelope", "Quentin", "Rachel", "Simon", "Sophia", "Tristan",
 * "Violet", "Wyatt",
 * "Xander", "Yasmine", "Zachary", "Abigail", "Benjamin", "Charlotte", "Daniel",
 * "Evelyn",
 * "Fiona", "Gabriel", "Harper", "Ian", "Jasmine", "Kai", "Luna", "Matthew",
 * "Nathan", "Oliver",
 * "Piper", "Quincy", "Rose", "Sebastian", "Thea", "Vincent", "Willow",
 * "Xavier", "Yara",
 * "Zara", "Adam", "Beth", "Cameron", "Diana", "Elijah", "Felicity", "Gavin",
 * "Holly", "Isaiah",
 * "Jade", "Kyle", "Lauren", "Michael", "Natalie", "Owen", "Paige", "Quinn",
 * "Rebecca",
 * "Samuel", "Tara", "Tyler", "Victoria", "Wesley", "Xena", "Yvette",
 * "Zachariah", "Alexa",
 * "Brandon", "Chloe", "Dylan", "Emily", "Finn", "Grace", "Hayden", "Ivy",
 * "Jacob", "Jessica",
 * "Kevin", "Kylie", "Logan", "Madison", "Nathan", "Nora", "Oliver", "Olivia",
 * "Parker",
 * "Penelope", "Quentin", "Quinn", "Ryan", "Ruby", "Samantha", "Samuel",
 * "Trinity", "Uma",
 * "Victoria", "Wyatt", "Willow", "Xavier", "Xena", "Yara", "Yasmine",
 * "Zachary", "Zoe",
 * "Aaron", "Brianna", "Caleb", "Danielle", "Ethan", "Emma", "Gavin",
 * "Gabriella", "Henry",
 * "Hailey", "Isaac", "Isabella", "Jack", "Jasmine", "Kai", "Katherine", "Liam",
 * "Lily",
 * "Mason", "Maya", "Nathan", "Natalie", "Owen", "Olivia", "Parker", "Peyton",
 * "Quinn",
 * "Rebecca", "Samuel", "Sophia", "Tristan", "Taylor", "Victor", "Violet",
 * "Wyatt", "Willow",
 * "Xavier", "Ximena", "Yara", "Yasmine", "Zachary", "Zoe"
 * };
 * String[] lastnames = {
 * "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
 * "Davis", "Rodriguez", "Martinez",
 * "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor",
 * "Moore", "Jackson", "Martin",
 * "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez",
 * "Lewis", "Robinson",
 * "Walker", "Young", "Hall", "Allen", "Wright", "King", "Scott", "Green",
 * "Baker", "Adams", "Nelson",
 * "Hill", "Campbell", "Mitchell", "Roberts", "Carter", "Phillips", "Evans",
 * "Turner", "Torres", "Parker",
 * "Collins", "Edwards", "Stewart", "Flores", "Morris", "Nguyen", "Murphy",
 * "Rivera", "Cook", "Rogers",
 * "Morgan", "Peterson", "Cooper", "Reed", "Bailey", "Bell", "Gomez", "Kelly",
 * "Howard", "Ward", "Cox",
 * "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett", "Gray", "James",
 * "Reyes", "Cruz", "Hughes",
 * "Price", "Myers", "Long", "Foster", "Sanders", "Ross", "Morales", "Powell",
 * "Sullivan", "Russell",
 * "Ortiz", "Jenkins", "Gutierrez", "Perry", "Butler", "Barnes", "Fisher",
 * "Henderson", "Coleman",
 * "Simmons", "Patterson", "Jordan", "Reynolds", "Hamilton", "Graham", "Kim",
 * "Gonzales", "Alexander",
 * "Ramos", "Wallace", "Griffin", "West", "Cole", "Hayes", "Chavez", "Gibson",
 * "Bryant", "Ellis", "Stevens",
 * "Murray", "Ford", "Marshall", "Owens", "Mcdonald", "Harrison", "Ruiz",
 * "Kennedy", "Wells", "Alvarez",
 * "Woods", "Mendoza", "Castillo", "Olson", "Webb", "Washington", "Tucker",
 * "Freeman", "Burns", "Henry",
 * "Vasquez", "Snyder", "Simpson", "Crawford", "Jimenez", "Porter", "Mason",
 * "Shaw", "Gordon", "Wagner",
 * "Hunter", "Romero", "Hicks", "Dixon", "Hunt", "Palmer", "Robertson", "Black",
 * "Holmes", "Stone",
 * "Meyer", "Boyd", "Mills", "Warren", "Fox", "Rose", "Rice", "Moreno",
 * "Schmidt", "Patel", "Ferguson",
 * "Nichols", "Herrera", "Medina", "Ryan", "Fernandez", "Weaver", "Daniels",
 * "Stephens", "Gardner",
 * "Payne", "Kelley", "Dunn", "Pierce", "Arnold", "Tran", "Spencer", "Peters",
 * "Hawkins", "Grant",
 * "Hansen", "Castro", "Hoffman", "Hart", "Elliott", "Cunningham", "Knight",
 * "Bradley", "Carroll", "Hudson",
 * "Duncan", "Armstrong", "Berry", "Andrews", "Johnston", "Ray", "Lane",
 * "Riley", "Carpenter", "Perkins",
 * };
 */