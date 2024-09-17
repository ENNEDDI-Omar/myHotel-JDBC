package DAO;

import Entities.Room;
import java.util.List;

public interface RoomDAO {
     void insertRoom(Room room);
     void updateRoom(Room room);
     void deleteRoom(Room room);
     Room getRoom(int id);
     List<Room> getAllRooms();
}
