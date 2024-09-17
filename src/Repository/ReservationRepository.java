package Repository;

import DAO.ReservationDAO;
import Entities.Reservation;
import Database.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements ReservationDAO {

    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (room_id, user_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reservation.getRoom().getId());
            stmt.setInt(2, reservation.getClient().getId());
            stmt.setDate(3, java.sql.Date.valueOf(reservation.getStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(reservation.getEndDate()));
            stmt.setBigDecimal(5, reservation.getTotalPrice());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET room_id=?, user_id=?, start_date=?, end_date=?, total_price=? WHERE id=?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getRoom().getId());
            stmt.setInt(2, reservation.getClient().getId());
            stmt.setDate(3, java.sql.Date.valueOf(reservation.getStartDate()));
            stmt.setDate(4, java.sql.Date.valueOf(reservation.getEndDate()));
            stmt.setBigDecimal(5, reservation.getTotalPrice());
            stmt.setInt(6, reservation.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting reservation: " + e.getMessage(), e);
        }
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createReservationFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching reservation by ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservations.add(createReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all reservations: " + e.getMessage(), e);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByUserId(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (Connection connection = DbConnection.getInstance().getConx();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservations.add(createReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving reservations by user ID: " + e.getMessage(), e);
        }
        return reservations;
    }

    private Reservation createReservationFromResultSet(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        // Assume Room and Client are fetched and created elsewhere or use lazy loading if necessary
        reservation.setStartDate(rs.getDate("start_date").toLocalDate());
        reservation.setEndDate(rs.getDate("end_date").toLocalDate());
        reservation.setTotalPrice(rs.getBigDecimal("total_price"));
        return reservation;
    }

}
