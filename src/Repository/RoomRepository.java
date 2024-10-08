package Repository;

import DAO.RoomDAO;
import Entities.Reservation;
import Entities.Room;
import Database.DbConnection;
import Enums.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository implements RoomDAO {

    @Override
    public void insertRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, type, availability) VALUES (?, ?::room_type, ?)";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setString(2, room.getType().toString());
            stmt.setBoolean(3, room.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_number = ?, type = ?, availability = ? WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setString(2, room.getType().toString());
            stmt.setBoolean(3, room.isAvailable());
            stmt.setInt(4, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(Room room) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Room getRoom(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setType(RoomType.valueOf(rs.getString("type")));
                room.setAvailable(rs.getBoolean("availability"));
                return room;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving room: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public static List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setType(RoomType.valueOf(rs.getString("type")));
                room.setAvailable(rs.getBoolean("availability"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all rooms: " + e.getMessage());
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomByNumber(String roomNumber) throws SQLException {
        String query = "SELECT * FROM rooms WHERE room_number = ?";
        Room room = null;
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery())
        {
            while (rs.next())
            {
                room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setType(RoomType.valueOf(rs.getString("type")));
                room.setAvailable(rs.getBoolean("availability"));
                return room;
            }
        }catch (SQLException e)
        {
            System.out.println("Error retrieving room by number : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getRoomsByType(RoomType type)
    {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * From rooms WHERE type = ?::room_type";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, type.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setType(RoomType.valueOf(rs.getString("type")));
                room.setAvailable(rs.getBoolean("availability"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all rooms type from database: " + e.getMessage());
            e.printStackTrace();
        }
        return rooms;
    }

}
