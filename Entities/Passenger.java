package Entities;

public class Passenger {
    private int ID;
    private String name;
    private Ticket ticket;
    public Passenger(int ID, String name, TicketType type,String seat,int distance) {
        this.name = name;
        this.ID = ID;
        this.ticket = new Ticket(type, seat, distance);
    }

    public int getID() { return ID; }
    public String getName() {
        return name;
    }
    public TicketType getTicketType() {
        return ticket.getTicketType();
    }
    public int getDistance() {
        return ticket.getDistance();
    }
    public String getSeat(){
        return ticket.getSeat();
    }
}
