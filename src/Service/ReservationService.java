package Service;

import DAO.ReservationDAO;
import Entities.Reservation;
import Utils.ReservationValidation;

import java.util.List;

public class ReservationService {
    private ReservationDAO reservationDAO;
    private ReservationValidation reservationValidation;

    public ReservationService(ReservationDAO reservationDAO, ReservationValidation reservationValidation) {
        this.reservationDAO = reservationDAO;
        this.reservationValidation = reservationValidation;
    }

    public void addReservation(Reservation reservation) {
        // Perform any additional validations required before adding a reservation
        reservationValidation.reservationValidationChecking(reservation);
        try {
            reservationDAO.addReservation(reservation);
            System.out.println("Reservation added successfully.");
        } catch (Exception e) {
            System.err.println("Failed to add reservation: " + e.getMessage());
            throw e;
        }
    }

    public void updateReservation(Reservation reservation) {
        reservationValidation.reservationValidationChecking(reservation);
        try {
            reservationDAO.updateReservation(reservation);
            System.out.println("Reservation updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to update reservation: " + e.getMessage());
            throw e;
        }
    }

    public void deleteReservation(int reservationId) {
        try {
            reservationDAO.deleteReservation(reservationId);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            System.err.println("Failed to delete reservation: " + e.getMessage());
            throw e;
        }
    }

    public Reservation getReservationById(int reservationId) {
        try {
            return reservationDAO.getReservationById(reservationId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve reservation: " + e.getMessage());
            throw e;
        }
    }

    public List<Reservation> getAllReservations() {
        try {
            return reservationDAO.getAllReservations();
        } catch (Exception e) {
            System.err.println("Failed to retrieve all reservations: " + e.getMessage());
            throw e;
        }
    }

    public List<Reservation> getReservationsByUserId(int userId) {
        try {
            return reservationDAO.getReservationsByUserId(userId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve reservations for user: " + e.getMessage());
            throw e;
        }
    }
}
