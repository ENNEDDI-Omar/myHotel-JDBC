package Entities;

import Enums.RoomType;
import Enums.SeasonType;

public class Pricing {
    private int id;
    private RoomType roomType;
    private SeasonType season;
    private int price;

    // Default constructor
    public Pricing() {}

    // Parameterized constructor
    public Pricing(int id, RoomType roomType, SeasonType season, int price) {
        this.id = id;
        this.roomType = roomType;
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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
                ", roomType=" + getRoomType() +
                ", season=" + getSeason() +
                ", price=" + getPrice() +
                '}';
    }
}
