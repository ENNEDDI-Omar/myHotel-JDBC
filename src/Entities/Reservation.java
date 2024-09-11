package Entities;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private Room room;
    private Client client;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    // Default constructor
    public Reservation() {}

    // Parameterized constructor
    public Reservation(Room room, Client client, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.room = room;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Overridden toString method using getters
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + getId() +
                ", room=" + getRoom() +
                ", client=" + getClient() +
                ", startDate=" + getStartDate() +
                ", endDate=" + getEndDate() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
