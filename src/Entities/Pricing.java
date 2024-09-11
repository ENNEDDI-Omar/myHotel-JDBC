package Entities;

import Enums.SeasonType;

public class Pricing {
    private int id;
    private Room room;
    private SeasonType season;
    private int price;

    // Default constructor
    public Pricing() {}

    // Parameterized constructor
    public Pricing(int id, Room room, SeasonType season, int price) {
        this.id = id;
        this.room = room;
        this.season = season;
        this.price = price;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public SeasonType getSeason() {
        return season;
    }

    public void setSeason(SeasonType season) {
        this.season = season;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Override toString method using getters
    @Override
    public String toString() {
        return "Pricing{" +
                "id=" + getId() +
                ", room=" + getRoom() +
                ", season=" + getSeason() +
                ", price=" + getPrice() +
                '}';
    }
}
