package Service;

import Repository.RoomRepository;
import Entities.Room;
import Enums.RoomType;
import java.util.List;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(String roomNumber, RoomType type, boolean availability) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setType(type);
        room.setAvailable(availability);
        roomRepository.insertRoom(room);
        System.out.println("Room added successfully.");
    }

    public void updateRoom(int id, String roomNumber, RoomType type, boolean availability) {
        Room room = new Room(id, roomNumber, type, availability);
        roomRepository.updateRoom(room);
        System.out.println("Room updated successfully.");
    }

    public void deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        roomRepository.deleteRoom(room);
        System.out.println("Room deleted successfully.");
    }

    public Room getRoomById(int id) {
        return roomRepository.getRoom(id);
    }

    public List<Room> listAllRooms() {
        return roomRepository.getAllRooms();
    }

    public void printRoomDetails(Room room) {
        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room not found.");
        }
    }
}
