package Service;

import DAO.ReservationDAO;
import Entities.Client;
import Entities.Reservation;
import Entities.Room;
import Enums.SeasonType;
import Repository.PricingRepository;
import Repository.ReservationRepository;
import Repository.RoomRepository;
import Utils.ReservationValidation;
import Utils.SeasonUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private ReservationValidation reservationValidation;
    private PricingRepository pricingRepository;

    public ReservationService(ReservationRepository reservationRepository, ReservationValidation reservationValidation, PricingRepository pricingRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationValidation = reservationValidation;
        this.pricingRepository = pricingRepository;
    }

    public void addReservation(Reservation reservation) {
        // Perform any additional validations required before adding a reservation
        reservationValidation.reservationValidationChecking(reservation);
        try {
            reservationRepository.addReservation(reservation);
            System.out.println("Reservation added successfully.");
        } catch (Exception e) {
            System.err.println("Failed to add reservation: " + e.getMessage());
            throw e;
        }
    }

    public void updateReservation(Reservation reservation) {
        reservationValidation.reservationValidationChecking(reservation);
        try {
            reservationRepository.updateReservation(reservation);
            System.out.println("Reservation updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to update reservation: " + e.getMessage());
            throw e;
        }
    }

    public void deleteReservation(int reservationId) {
        try {
            reservationRepository.deleteReservation(reservationId);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            System.err.println("Failed to delete reservation: " + e.getMessage());
            throw e;
        }
    }

    public Reservation getReservationById(int reservationId) {
        try {
            return reservationRepository.getReservationById(reservationId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve reservation: " + e.getMessage());
            throw e;
        }
    }

    public List<Reservation> getAllReservations() {
        try {
            return reservationRepository.getAllReservations();
        } catch (Exception e) {
            System.err.println("Failed to retrieve all reservations: " + e.getMessage());
            throw e;
        }
    }

    public List<Reservation> getReservationsByUserId(int userId) {
        try {
            return reservationRepository.getReservationsByUserId(userId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve reservations for user: " + e.getMessage());
            throw e;
        }
    }

    public BigDecimal calculateTotalPrice(Room room, LocalDate startDate, LocalDate endDate) throws SQLException {
        BigDecimal totalPrice = BigDecimal.ZERO;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            SeasonType season = SeasonUtils.getSeasonForDate(currentDate);
            int dailyPrice = pricingRepository.getPriceByRoomTypeAndSeason(room.getType(), season);
            totalPrice = totalPrice.add(BigDecimal.valueOf(dailyPrice));
            currentDate = currentDate.plusDays(1);
        }

        return totalPrice;
    }

}
