package Entities;

public class Luggage {
    private int passengerID;
    private float luggageWeight;
    private String luggageColor, luggageId;

    public Luggage(int pid, String lid, float weight, String color)
    {
        passengerID = pid;
        luggageId = lid;
        luggageWeight = weight;
        luggageColor = color;
    }

    public String getLuggageColor() {
        return luggageColor;
    }
    public String getLuggageId() {
        return luggageId;
    }
    public float getLuggageWeight() {
        return luggageWeight;
    }
    public int getPassengerID() {
        return passengerID;
    }
    
}
