package DAO;

import Entities.Reservation;
import java.util.List;

public interface ReservationDAO {
void addReservation(Reservation reservation);
void updateReservation(Reservation reservation);
void deleteReservation(Reservation reservation);
Reservation getReservationById(int reservationId);
List<Reservation> getAllReservations();
List<Reservation> getReservationsByUserId(int userId);
}