package Entities;

import Enums.RoomType;

public class Room {
    private int id;
    private String roomNumber;
    private RoomType type;
    private boolean availability;


    public Room() {}

    public Room(int id, String roomNumber, RoomType type, boolean availability) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.availability = availability;
    }

    public int getId() { return id; }


    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }

    public boolean isAvailable() { return availability; }
    public void setAvailable(boolean availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + getId() +
                ", roomNumber='" + getRoomNumber() + '\'' +
                ", type='" + getType() + '\'' +
                ", availability=" + isAvailable() +
                '}';
    }
}
