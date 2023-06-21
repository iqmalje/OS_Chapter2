package Entities;

public class Passenger {
    private String name;
    private Ticket ticket;
    public Passenger(String name, TicketType type,String seat,int distance) {
        this.name = name;
        this.ticket = new Ticket(type, seat, distance);
    }
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
