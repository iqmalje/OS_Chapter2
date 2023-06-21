package Entities;

public class Ticket {
    private TicketType ticketType;
    private String seat;
    private Integer distance;
    public Ticket(TicketType ticketType, String seat, Integer distance) {
        this.ticketType = ticketType;
        this.seat = seat;
        this.distance = distance;
    }
    public TicketType getTicketType() {
        return ticketType;
    }

    public String getSeat() {
        return seat;
    }

    public int getDistance() {
        return distance;
    }

}
